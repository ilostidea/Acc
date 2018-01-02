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
</head>
<body>

  <body>
    <nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="../../index.jsp">Acc Admin</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="../../index.jsp">统计</a></li>
            <li class="active"><a href="#">财会</a></li>
            <li><a href="#contact">问答</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li role="separator" class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle"><i class="fa fa-bell fa-fw"></i><span class="badge badge-green">3</span></a></li>
			<li id="topbar-chat" class="hidden-xs"><a href="#" class="dropdown-toggle"><i class="fa fa-comments"></i><span class="badge badge-info">3</span></a></li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
				    <img src="" alt="" class="img-responsive img-circle"/>&nbsp;<span class="hidden-xs">Robert John</span>&nbsp;<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#"><i class="fa fa-user"></i>My Profile</a></li>
					<li class="divider"></li>
					<li><a href="Login.html"><i class="fa fa-key"></i>Log Out</a></li>
				</ul>
			</li>
		  </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-left" >

        <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="../standard/manage.jsp" class="list-group-item  active">准则制度</a>
            <a href="../generalPrinciple/manage.jsp" class="list-group-item">基本准则</a>
            <a href="../specificStandard/manage.jsp" class="list-group-item">具体准则</a>
            <a href="../financialReport/manage.jsp" class="list-group-item">财务报表</a>
            <a href="../accElement/manage.jsp" class="list-group-item">科目分类</a>
            <a href="../coa/manage.jsp" class="list-group-item">会计科目</a>
            <a href="../accUsage/manage.jsp" class="list-group-item">科目用法</a>
          </div>
        </div><!--/.sidebar-offcanvas-->
          
        <div class="col-xs-12 col-sm-10 pull-right">
          <p class="pull-left visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="row">
            <div class="col-xs-12 col-lg-12">
            	<h3 align="center">新增会计准则或会计制度</h3>
            	<hr>
				 <div class="form-horizontal">
					<div class="form-group">
							<label class="col-sm-2 control-label" for="name">名称</label>
							<div class="col-sm-4">
							    <input type="text" class="form-control" id="name" name="name" placeholder="请输入名称">
							</div>
							
							<label class="col-sm-2 control-label" for="abbr">简称</label>
							<div class="col-sm-4">
							    <input type="text" class="form-control" id="abbr" name="abbr" placeholder="请输入简称">
							</div>
					</div>
					<div class="form-group">
							<label class="col-sm-2 control-label" for="code">代码</label>
							<div class="col-sm-4">
							    <input type="text" class="form-control" id="code" name="code" placeholder="请输入简称的拼音首字母">
							</div>
							
							<label class="col-sm-2 control-label" for="exeYear">开始执行时间</label>
							<div class="col-sm-4">
							    <input type="number" class="form-control" id="exeYear" name="exeYear" placeholder="请输入开始执行年份">
							</div>
					</div>
					<div class="form-group">
							<label class="col-sm-2 control-label" for="status">状态</label>
							<div class="col-sm-10">
								<div class="checkbox">
									<label><input type="checkbox" id="status" name="status">执行中</label>
								</div>
							</div>
					</div>
					<div style="text-align: center;">
						<input type="hidden" id="id" name="id">
						<button type="button" class="btn btn-defalt" onclick="history.go(-1);">返回</button>
						<button type="button" class="btn btn-success" id="save">提交</button>
					</div>
				</div>
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
    <script src="<%=request.getContextPath() %>/js/messenger.js"></script>
    <script>
	$._messengerDefaults = {
	         extraClasses: 'messenger-fixed messenger-theme-air messenger-on-bottom'
	    };
	$(document).ready(
		function(){
			var id = ${param.id != null ? param.id : -1 };
			if( id != -1 ){
		    	$.get("/accStandard/detail",
		        		  { accStandardID: id },
		        		  function(responseTxt, status, xhr){
		        			  var data = responseTxt.data;
		        			  $("#name").val( data.name );
		        			  $("#abbr").val( data.abbr );
		        			  $("#code").val( data.code );
		        			  $("#exeYear").val( data.exeYear );
		        			  $("#status").prop( "checked", data.status );
		        			  $("#id").val( id );
		        		  },
		        		  "json");
			}
	        
			$("#save").click(function(){
			    var _name = $("#name").val();
			    var _abbr = $("#abbr").val();
			    var _code = $("#code").val();
			    var _exeYear = $("#exeYear").val();
			    var _status = $("#status").prop("checked") ;
			    var _id = $("#id").val( );
			    var url;
				var data
			    if ( _id != undefined && _id != null) {
			    	url = "/accStandard/update";
			    	data = { name : _name , abbr : _abbr , code : _code , exeYear : _exeYear , status : _status, id : _id };
			    } else {
			    	url = "/accStandard/add";
			    	data = { name : _name , abbr : _abbr , code : _code , exeYear : _exeYear , status : _status };
			    }
				$.ajax({
					type : "POST",
					url : url,
					data : JSON.stringify(data),
					contentType : "application/json",
					//dataType : "json",
					success : function(){
						Messenger().post({
							message : "保存成功！即将跳转进入管理界面。",//提示信息
		      				type : 'success',//消息类型。error、info、success
		      				hideAfter : 2,//多长时间消失
		      				showCloseButton : true,//是否显示关闭按钮
		      				hideOnNavigate : false //是否隐藏导航
		      		    });
	      				setTimeout(function(){window.location.href="/jsp/gaap/standard/manage.jsp";}, 1000);
				    }
				});
	    	});
		}
	);
	</script>
</body>
</html>