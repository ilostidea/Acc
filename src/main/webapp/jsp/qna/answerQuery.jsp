<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<title>Acc管理工作台</title>
	<link href="<%=request.getContextPath() %>/style/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/style/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/style/offcanvas.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/style/fontawesome-all.css" rel="stylesheet" >
    <link href="<%=request.getContextPath() %>/style/messenger.css" rel="stylesheet" >
    <link href="<%=request.getContextPath() %>/style/messenger-theme-air.css" rel="stylesheet" >
	<link href="<%=request.getContextPath() %>/style/bootstrap-markdown.min.css" rel="stylesheet">
</head>
<body>

    <jsp:include page="../topbar.jsp"	 flush="true">
    	<jsp:param value="3" name="menu"/>
    </jsp:include>

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-left" >

        <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="questionQuery.jsp" class="list-group-item">问题管理</a>
            <a href="answerQuery.jsp" class="list-group-item active">回答管理</a>
          </div>
        </div><!--/.sidebar-offcanvas-->
          
        <div class="col-xs-12 col-sm-10 pull-right">
          <p class="pull-left visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="row">
            <div class="col-xs-12 col-lg-12">
              <h3 align="center">答案查询</h3>
              	<div class="form-horizontal">
					<div class="form-group">
							<label class="col-sm-2 control-label" for="userName">回答人</label>
							<div class="col-sm-3">
							    <input type="text" class="form-control input-sm" id="userName" name="userName" placeholder="请输入昵称、手机号或邮箱">
							</div>
							<label class="col-sm-2 control-label" for="answer">回答</label>
							<div class="col-sm-3">
							    <input type="text" class="form-control input-sm" id="answer" name="answer" placeholder="请输入回答中包含的文字">
							</div>
							<div class="col-sm-2"></div>
				    </div>
					<div class="form-group">
							<label class="col-sm-2 control-label" for="status">状态</label>
							<div class="col-sm-3">
							    <select id="status" name="status" class="form-control input-sm">
							        <option value="true">启用</option>
							        <option value="false">停用</option>
							    </select>
							</div>
							<label class="col-sm-2 control-label" for="accused">是否被投诉</label>
							<div class="col-sm-3">
							    <select id="accused" name="accused" class="form-control input-sm">
							        <option value="true">是</option>
							        <option value="false">否</option>
							    </select>
					        </div>
							<div class="col-sm-2">
							    <a class="btn btn-success btn-sm pull-right" href="#" onclick="query()"><i class="fa fa-file"></i> 查询</a>
							</div>
				    </div>
				    <hr>
					<div class="form-group">
						<div class="col-sm-1"></div>
						<div class="col-sm-10">
			               <table class="table table-striped">
							  <caption></caption>
							  <thead>
							    <tr>
							      <th>问题</th>
							      <!-- th>开始执行年份</th> -->
							      <th>回答</th>
							      <th>统计</th>
							    </tr>
							  </thead>
							  <tbody id="data-list">
							  <!-- 查询出来的数据显示在这里 -->
							  </tbody>
							</table>
						</div>
						<div class="col-sm-1"></div>
				    </div>
            </div>
          </div><!--/row-->
          </div>
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
    <script src="<%=request.getContextPath() %>/js/messenger.js"></script>
    <script src="<%=request.getContextPath() %>/js/bootstrap-markdown.js"></script>
    <script src="<%=request.getContextPath() %>/js/bootstrap-markdown.zh.js"></script>
    <script src="<%=request.getContextPath() %>/js/marked.js"></script>
    <script>
	$._messengerDefaults = {
         extraClasses: 'messenger-fixed messenger-theme-air messenger-on-bottom'
    }

	function query(){
		var userName = $("#userName").val();
		var answer = $("#answer").val();
		var status = $("#status").val();
		var accused = $("#accused").val();
		$.get("/answer/admin/answer",
	    		  { userName : userName, answer : answer, status : status, accused : accused },
	    		  function(responseTxt, status){
					if(status == "success"){
						var len = responseTxt.data.length;
						var datas = responseTxt.data;
						for(var i = 0; i < len; i++){
							var tr = $("<tr id=\"tr" + i + "\" onclick=\"location.href='answerMngmt.jsp?id="+datas[i].id+"'\" style=\"cursor:pointer;\"></tr>");
							var td0 = $("<td></td>").text(datas[i].questionTitle);
							var td2 = $("<td></td>").text(datas[i].answer);
							var td3 = $("<td></td>").text("追问数：" + datas[i].pumpCount );
							$(tr).append(td0, /* td1, */ td2, td3);
							$("#data-list").append(tr);
						}
					}
				},
			   "json"
	    );
	}

	$(document).ready(function(){
		query();
	});
	</script>
</body>
</html>