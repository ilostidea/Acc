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
    <link href="<%=request.getContextPath() %>/tool/mdeditor/css/editormd.css" rel="stylesheet" />
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
            <li><a href="../../qna/index.jsp">问答</a></li>
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
            <a href="../standard/manage.jsp" class="list-group-item">准则制度</a>
            <a href="../generalPrinciple/manage.jsp" class="list-group-item">基本准则</a>
            <a href="../specificStandard/manage.jsp" class="list-group-item active">具体准则</a>
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
            	<h3 align="center">新增具体准则</h3>
            	<hr>
				 <div class="form-horizontal">
					
					<div class="form-group">
							<label class="col-sm-2 control-label" for="standard">会计制度</label>
							<div class="col-sm-4">
							    <select id="standard" name="standard" class="form-control"></select>
							</div>
							
							<label class="col-sm-2 control-label" for="isPreface">是否前言</label>
							<div class="col-sm-4">
								<div class="checkbox">
									<label><input type="checkbox" id="isPreface" name="isPreface">启用</label>
								</div>
					        </div>
				    </div>
					<div class="form-group">
							<label class="col-sm-2 control-label" for="title">基本准则</label>
							<div class="col-sm-4">
							    <input type="text" class="form-control" id="title" name="title" placeholder="请输入名称">
							</div>
							
							<label class="col-sm-2 control-label" for="status">状态</label>
							<div class="col-sm-4">
								<div class="checkbox">
									<label><input type="checkbox" id="status" name="status">启用</label>
								</div>
					        </div>
				    </div>
					<div class="form-group">
							<div class="col-sm-1"></div>
							<div id="specifics" class="col-sm-10">
								<!-- <textarea id="specifics" name="specifics" class="form-control" data-provide="markdown" data-iconlibrary="fa" rows="12"></textarea> -->
								<textarea id="specifics-markdown-doc" style="display:none;"></textarea>
			                </div>
							<div class="col-sm-1"></div>
					</div>
					<div style="text-align: center; height: 60px;">
						<input type="hidden" id="id" name="id">
						<button type="button" class="btn btn-defalt" onclick="history.go(-1);">返回</button>
						<button type="button" class="btn btn-success" id="save">保存</button>
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
    <script src="<%=request.getContextPath() %>/tool/mdeditor/editormd.js"></script>
    <script>
	$._messengerDefaults = {
	         extraClasses: 'messenger-fixed messenger-theme-air messenger-on-bottom'
	    };
	
	var mdEditor;
	
	$(document).ready(
		function(){

			mdEditor = editormd("specifics", {//删除了codemirror下部分不常见语言的样式
	            width: "90%",
	            height: 600,
	            markdown : "",
	            path : '<%=request.getContextPath()%>/tool/mdeditor/lib/',
	            tex  : true,
	            placeholder          :  "请输入markdown语法格式的内容！",
	            autoFocus            :  false,
	            emoji                   : true,
	            //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为 true
	            //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为 true
	            //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为 true
	            //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为 0.1
	            //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为 #fff
	            imageUpload : true,
	            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
	            imageUploadURL : "./php/upload.php?test=dfdf",
	            
	            /*
	             上传的后台只需要返回一个 JSON 数据，结构如下：
	             {
	                success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
	                message : "提示的信息，上传成功或上传失败及错误信息等。",
	                url     : "图片地址"        // 上传成功时才返回
	             }
	             */
	        });
			
			var id = ${param.id != null ? param.id : -1 };
			var accStandardID = ${param.accStandardID != null ? param.accStandardID : -1 };

			$.get("/accStandard/admin/list",function(responseTxt, status){
				if(status == "success"){
					var len = responseTxt.data.length;
					var datas = responseTxt.data;
					for(var i = 0; i < len; i++){
						var option = $("<option value=\"" + datas[i].id + "\"></option>");
						option.text( datas[i].name + "(" + datas[i].exeYear + ")" );
						$("#standard").append( option );
					}
      			  //$("#standard option[value='" + accStandardID + "']").prop( "selected", true );//also works
    			  $("#standard").val( accStandardID );
				}
			});
			
			if( id != -1 ){
		    	$.get("/sp/detail",
		        		  { spID: id },
		        		  function(responseTxt, status, xhr){
		        			  var data = responseTxt.data;
		        			  $("#title").val( data.title );
		        			  $("#specifics-markdown-doc").val( data.specifics );
		        			  $("#isPreface").prop( "checked", data.isPreface );
		        			  $("#status").prop( "checked", data.status );
		        			  $("#id").val( id );
		        		  },
		        		  "json");
			}
	        
			$("#save").click(function(){
			    var _title = $("#title").val();
			    var _specifics = $("#specifics-markdown-doc").val();
			    var _status = $("#status").prop("checked") ;
			    var _isPreface = $("#isPreface").prop("checked") ;
			    var _id = $("#id").val( );
				var _accStandardID = $("#standard").val();
			    var url, data;
			    if ( _id != -1 &&  _id != undefined && _id != null) {
			    	url = "/sp/admin/update";
			    	data = { title : _title , specifics : _specifics , isPreface : _isPreface , status : _status , accountingStandard :  { id : _accStandardID } , id : _id };
			    } else {alert("add");
			    	url = "/sp/admin/add";
			    	data = { title : _title , specifics : _specifics , isPreface : _isPreface , status : _status , accountingStandard :  { id : _accStandardID } };
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
	      				setTimeout(function(){window.location.href="/jsp/gaap/specificStandard/manage.jsp?accStandardID=" + _accStandardID;}, 1000);
				    }
				});
	    	});
		}
	);
	</script>
</body>
</html>