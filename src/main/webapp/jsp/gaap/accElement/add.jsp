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

    <jsp:include page="../../topbar.jsp"	 flush="true">
    	<jsp:param value="2" name="menu"/>
    </jsp:include>

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-left" >

        <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="../standard/manage.jsp" class="list-group-item">准则制度</a>
            <a href="../generalPrinciple/manage.jsp" class="list-group-item">基本准则</a>
            <a href="../specificStandard/manage.jsp" class="list-group-item">具体准则</a>
            <a href="../financialReport/manage.jsp" class="list-group-item">财务报表</a>
            <a href="../accElement/manage.jsp" class="list-group-item active">科目分类</a>
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
            	<h3 align="center">新增会计科目分类</h3>
            	<hr>
				 <div class="form-horizontal">
					
					<div class="form-group">
							<label class="col-sm-2 control-label" for="standard">会计制度</label>
							<div class="col-sm-4">
							    <select id="standard" name="standard" class="form-control"></select>
							</div>
							<label class="col-sm-2 control-label" for="name">名称</label>
							<div class="col-sm-4">
							    <input type="text" class="form-control" id="name" name="name" placeholder="请输入名称">
							</div>
				    </div>
					<div class="form-group">
							<label class="col-sm-2 control-label" for="code">代码</label>
							<div class="col-sm-4">
							    <input type="text" class="form-control" id="code" name="code" placeholder="请输入代码">
							</div>
							
							<label class="col-sm-2 control-label" for="status">状态</label>
							<div class="col-sm-4">
								<div class="checkbox">
									<label><input type="checkbox" id="status" name="status">启用</label>
								</div>
					        </div>
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
    <script>
	$._messengerDefaults = {
	         extraClasses: 'messenger-fixed messenger-theme-air messenger-on-bottom'
	    };
	
	var mdEditor;
	
	$(document).ready(
		function(){
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
		    	$.get("/accElement/admin/detail",
		        		  { accElementID: id },
		        		  function(responseTxt, status, xhr){
		        			  var data = responseTxt.data;
		        			  $("#name").val( data.name );
		        			  $("#code").val( data.code );
		        			  $("#status").prop( "checked", data.status );
		        			  $("#id").val( id );
		        		  },
		        		  "json");
			}
	        
			$("#save").click(function(){
			    var _name = $("#name").val();
			    var _code = $("#code").val();
			    var _status = $("#status").prop("checked") ;
			    var _id = $("#id").val( );
				var _accStandardID = $("#standard").val();
			    var url, data;
			    if ( _id != -1 && _id != null) {
			    	url = "/accElement/admin/update";
			    	data = { name : _name , code : _code , status : _status , accountingStandard :  { id : _accStandardID } , id : _id };
			    } else {alert("add");
			    	url = "/accElement/admin/add";
			    	data = { name : _name , code : _code , status : _status , accountingStandard :  { id : _accStandardID } };
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
	      				setTimeout(function(){window.location.href="/jsp/gaap/accElement/manage.jsp?accStandardID=" + _accStandardID;}, 1000);
				    }
				});
	    	});
		}
	);
	</script>
</body>
</html>