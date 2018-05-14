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
	<script src="http://momentjs.cn/downloads/moment-with-locales.min.js"></script><!-- 
	<script src="<%=request.getContextPath() %>/js/ChartUtils.js"></script>-->
	<script src="<%=request.getContextPath() %>/js/Chart.min.js"></script>
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
            <a href="#" class="list-group-item active">访问量统计</a><%--
              <a href="<%=request.getContextPath()%>/jsp/stat/duration.jsp" class="list-group-item">访问时长统计</a>--%>
            <a href="<%=request.getContextPath()%>/jsp/stat/conversion.jsp" class="list-group-item">转化率</a>
            <a href="<%=request.getContextPath()%>/jsp/stat/retention.jsp" class="list-group-item">留存率</a>
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
             
			Chart Type:
			<select id="type">
				<option value="line">Line</option>
				<option value="bar">Bar</option>
			</select>
			<button id="update">update</button>
			<br>
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
    <script>/*
		function randomNumber(min, max) {
			return Math.random() * (max - min) + min;
		}

		function randomBar(date, lastClose) {
			var open = randomNumber(lastClose * 0.95, lastClose * 1.05);
			var close = randomNumber(open * 0.95, open * 1.05);
			return {
				t: date.valueOf(),
				y: close
			};
		}*/

		var chart;

		$('#update').on('click', function() {
			var type = $('#type').val();
			chart.config.type = type;
			chart.update();
		});
		
		$(document).ready(function(){
			$.get("/stat/admin/visit",function(responseTxt, status){
				if(status == "success"){
					var datas = responseTxt.data;
					var dataVisitCount = datas[0];
					var dataIpCount = datas[1];
                    var cookieCount = datas[2];

					//var dateFormat = 'MMMM DD YYYY';
					var date = moment( ).subtract(30, 'd');//alert(date.format(dateFormat));
					var labels = [date];
					while (labels.length < 30) {
						date = date.clone().add(1, 'd');
						labels.push(date);
					}/*
					var data1 = [randomBar(date, 30)];
					var data2 = [randomBar(date, 30)];
					var labels = [date];
					while (data1.length < 60) {
						date = date.clone().add(1, 'd');
						if (date.isoWeekday() <= 5) {
							data1.push(randomBar(date, data1[data1.length - 1].y));
							labels.push(date);
							data2.push(randomBar(date, data2[data2.length - 1].y));
							//labels.push(date);
						}
					}*/

					var color = Chart.helpers.color;
					var config = {
						type: 'line',
						data: {
							labels: labels,
							datasets: [{
								label: '访问次数',
								backgroundColor: color('rgb(255, 99, 132)').alpha(0.5).rgbString(),//red
								borderColor: 'rgb(255, 99, 132)',
								fill: false,
								data: dataVisitCount,
								pointRadius: 1,
								//lineTension: 0,
								borderWidth: 2
							}, {
								label: '访问IP数',
								backgroundColor: color('rgb(54, 162, 235)').alpha(0.5).rgbString(),//blue
								borderColor: 'rgb(54, 162, 235)',
								fill: false,
								data: dataIpCount,
								pointRadius: 1,
								//lineTension: 0,
								borderWidth: 2
							}, {
                                label: '访问客户(Cookie)数',
                                backgroundColor: color('rgb(255, 205, 86)').alpha(0.5).rgbString(),//yellow
                                borderColor: 'rgb(255, 205, 86)',
                                fill: false,
                                data: cookieCount,
                                pointRadius: 1,
                                //lineTension: 0,
                                borderWidth: 2
                            }]
						},
						options: {
							title: {
					            display: true,
								text: '访问量统计',
								fontSize: 18
							},
					        tooltips: {
					            mode: 'index'
					        },
					        bounds: 'ticks',
							scales: {
								xAxes: [{
									type: 'time',
									distribution: 'linear',
									ticks: {
										source: 'labels'
									},
									time: {
										unit: 'day',
										tooltipFormat: 'YYYY年MM月DD日'
									},
									scaleLabel: {
										display: true,
										labelString: '日期'
									}
								}],
								yAxes: [{
									scaleLabel: {
										display: true,
										labelString: '访问数'
									},
					                ticks: {
					                    beginAtZero:true
					                }
								}]
							}
						}
					};
					var ctx = document.getElementById('chart1').getContext('2d');
					chart = new Chart(ctx, config);
				}
			});
		});

	</script>
</body>
</html>