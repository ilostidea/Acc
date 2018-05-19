<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Acc管理工作台</title>
	<link href="<%=request.getContextPath() %>/style/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/style/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/style/offcanvas.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/style/fontawesome-all.css" rel="stylesheet" >
    <link href="<%=request.getContextPath() %>/style/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<style type="text/css">
		canvas {
			-moz-user-select: none;
			-webkit-user-select: none;
			-ms-user-select: none;
		}
	</style>
</head>
<body>

    <jsp:include page="../topbar.jsp"	 flush="true">
    	<jsp:param value="1" name="menu"/>
    </jsp:include>

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-left" >

        <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="<%=request.getContextPath()%>/jsp/stat/customer.jsp" class="list-group-item">注册客户数</a>
            <a href="<%=request.getContextPath()%>/jsp/stat/visits.jsp" class="list-group-item">访问量统计</a><%--
              <a href="<%=request.getContextPath()%>/jsp/stat/duration.jsp" class="list-group-item">访问时长统计</a>--%>
            <a href="<%=request.getContextPath()%>/jsp/stat/conversion.jsp" class="list-group-item">转化率</a>
            <a href="#" class="list-group-item active">留存率</a>
              <a href="<%=request.getContextPath()%>/jsp/stat/bounce.jsp" class="list-group-item">跳出率</a><!--
            <a href="#" class="list-group-item">客户偏好统计</a>
            <a href="#" class="list-group-item">使用情况分析</a> -->
            <a href="<%=request.getContextPath()%>/admin/druid/*" class="list-group-item" target="_blank">数据池</a>
          </div>
        </div><!--/.sidebar-offcanvas-->
          
        <div class="col-xs-12 col-sm-10 pull-right">
          <p class="pull-left visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="row">
            <div class="col-xs-6 col-lg-12">
             <!-- 右侧内容区 -->
            <div class="form-group">
                <label class="col-sm-2 control-label" for="type">Chart Type:</label>
                <div class="col-sm-4">
                    <select id="type">
                        <option value="line">Line</option>
                        <option value="bar">Bar</option>
                    </select>
                    <button id="update">update</button>
                </div>
                <label class="col-sm-2 control-label" for="dtp_input">Date Picking:</label>
                <div class="input-group date form_date col-md-4" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                    <input id="dtp_input" class="form-control" size="16" type="text" value="" readonly onchange="javascript:queryRetention();">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
			<br>

             <canvas id="chart1"></canvas>
             
             <!-- 右侧内容区域结束 -->
            </div>
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

      </div><!--/row-->

    </div><!--/.container-->

    <footer class="footer" >
        <p class="text-muted">acc admin &copy; 2017</p>
    </footer>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- script src="../../js/jquery-3.2.1.slim.js"></script> -->
    <script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=request.getContextPath() %>/js/ie10-viewport-bug-workaround.js"></script>
    <script src="<%=request.getContextPath() %>/js/offcanvas.js"></script>
    <script src="<%=request.getContextPath() %>/js/Chart.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script>

        $('.form_date').datetimepicker({
            language:  'zh-CN',
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            pickerPosition: 'bottom-left'
        });

        var chart;

        $('#update').on('click', function() {
            var type = $('#type').val();
            chart.config.type = type;
            chart.update();
        });

        function queryRetention(){
            var fromDateStr = $("#dtp_input").val();
            var fullDate = fromDateStr.split("-");
            var fromDate = new Date(fullDate[0], fullDate[1]-1, fullDate[2]);
            //fromDate.setFullYear(fullDate[0], fullDate[1]-1, fullDate[2]);
            $.get("/stat/admin/retention",
                { from : fromDate },
                function(responseTxt, status){
                    if(status == "success"){
                        var datas = responseTxt.data;
                        var daysLater = datas[0];
                        var basicVisitCount = datas[1];
                        var daysLaterVisitCount = datas[2];
                        var retentionRate = datas[3];

                        for(var i=0; i<retentionRate.length; i++){
                            retentionRate[i] = retentionRate[i] / 100;
                        }

                        var color = Chart.helpers.color;
                        var config = {
                            type: 'line',
                            data: {
                                labels: daysLater,
                                datasets: [ {
                                    label: '基准日访问Cookie数',
                                    backgroundColor: color('rgb(255, 99, 132)').alpha(0.5).rgbString(),//red
                                    borderColor: 'rgb(255, 99, 132)',
                                    fill: false,
                                    data: basicVisitCount,
                                    yAxisID: 'y-axis-2',
                                    pointRadius: 1,
                                    //lineTension: 0,
                                    borderWidth: 2
                                }, {
                                    label: '日后访问Cookie数',
                                    backgroundColor: color('rgb(54, 162, 235)').alpha(0.5).rgbString(),//blue
                                    borderColor: 'rgb(54, 162, 235)',
                                    fill: false,
                                    data: daysLaterVisitCount,
                                    yAxisID: 'y-axis-2',
                                    pointRadius: 1,
                                    //lineTension: 0,
                                    borderWidth: 2
                                }, {
                                    label: '留存率',
                                    backgroundColor: color('rgb(255, 205, 86)').alpha(0.5).rgbString(),//yellow
                                    borderColor: 'rgb(255, 205, 86)',
                                    fill: false,
                                    data: retentionRate,
                                    yAxisID: 'y-axis-1',
                                    pointRadius: 1,
                                    //lineTension: 0,
                                    borderWidth: 2
                                } ]
                            },
                            options: {
                                title: {
                                    display: true,
                                    text: '留存统计',
                                    fontSize: 18
                                },
                                tooltips: {
                                    mode: 'index'
                                },
                                bounds: 'ticks',
                                scales: {
                                    xAxes: [{
                                        distribution: 'linear',
                                        ticks: {
                                            source: 'labels'
                                        },
                                        scaleLabel: {
                                            display: true,
                                            labelString: '天数'
                                        }
                                    }],
                                    yAxes: [{
                                        id: 'y-axis-1',
                                        position: 'left',
                                        scaleLabel: {
                                            display: true,
                                            labelString: '留存率(%)'
                                        },
                                        ticks: {
                                            beginAtZero:true
                                        }
                                    },{
                                        id: 'y-axis-2',
                                        position: 'right',
                                        scaleLabel: {
                                            display: true,
                                            labelString: '访问数'
                                        },
                                        ticks: {
                                            beginAtZero:true
                                        },
                                        gridLines: {
                                            drawOnChartArea: false, // only want the grid lines for one axis to show up
                                        }
                                    }]
                                }
                            }
                        };
                        var ctx = document.getElementById('chart1').getContext('2d');
                        if( chart == null)
                            chart = new Chart(ctx, config);
                        else{
                            chart.config = config;
                            chart.update();
                        }
                    }
                },
                "json"
            );
        }

        $(document).ready(function(){
            var today = new Date();
            var dateBefore = new Date(today - 1000 * 60 * 60 * 24 * 30);//最后一个数字30可改，30天
            var year  = dateBefore.getFullYear();
            var month  = dateBefore.getMonth() + 1;
            if( month < 10)
                month = '0' + month;
            var day   = dateBefore.getDate();
            if( day < 10)
                day = '0' + day;
            $("#dtp_input").val(year+"-"+month+"-"+day);
            queryRetention();
        });

	</script>
</body>
</html>