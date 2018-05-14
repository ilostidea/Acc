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
	<script src="<%=request.getContextPath() %>/js/echarts.min.js"></script>
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
            <a href="#" class="list-group-item active">转化率</a>
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
            <div class="col-xs-6 col-lg-12" align="center">
             <!-- 右侧内容区 -->
             

			    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
			    <div id="main" style="width: 800px;height:400px;"></div>
             
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
    <script>
    var myChart = echarts.init(document.getElementById('main'));
    var option = {
    	    title: {
    	        text: '漏斗图',
    	        subtext: '按时间段统计',
    	        left:'center'
    	    },
    	    tooltip: {
    	        trigger: 'item',
    	        formatter: "{a} <br/>{b} : {c}次"
    	    },
    	    toolbox: {
    	        feature: {
    	            dataView: {readOnly: false},
    	            restore: {},
    	            saveAsImage: {}
    	        }
    	    },
    	    legend: {
    	        data: ['访问数','新访问数（无user信息）','注册数'],
    	    	top:80
    	    },
    	    calculable: true,
    	    series: [
    	        {
    	            name:'漏斗图',
    	            type:'funnel',
    	            left: '20%',
    	            top: 120,
    	            //x2: 80,
    	            bottom: 60,
    	            width: '60%',
    	            // height: {totalHeight} - y - y2,
    	            min: 0,
    	            max: 100,
    	            minSize: '0%',
    	            maxSize: '100%',
    	            sort: 'descending',
    	            gap: 2,
    	            label: {
    	                normal: {
    	                    show: true,
    	                    position: 'inside'
    	                },
    	                emphasis: {
    	                    textStyle: {
    	                        fontSize: 20
    	                    }
    	                }
    	            },
    	            labelLine: {
    	                normal: {
    	                    length: 10,
    	                    lineStyle: {
    	                        width: 1,
    	                        type: 'solid'
    	                    }
    	                }
    	            },
    	            itemStyle: {
    	                normal: {
    	                    borderColor: '#fff',
    	                    borderWidth: 1
    	                }
    	            },
    	            data: [
                        {value: 100, name: '访问数'},
    	                {value: 80, name: '新访问数（无user信息）'},
                        {value: 60, name: '注册数'}
    	            ]
    	        }
    	    ]
    	};

    $(document).ready(function(){
        $.get("/stat/admin/convert",function(responseTxt, status) {
            if (status == "success") {
                var datas = responseTxt.data;
                var charData = option.series[0].data;
                charData[0].value = datas[0];
                charData[1].value = datas[1];
                charData[2].value = datas[2];
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        })
    })

	</script>
</body>
</html>