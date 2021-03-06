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
    <link href="<%=request.getContextPath() %>/tool/mdeditor/css/editormd.css" rel="stylesheet" >
    <link href="<%=request.getContextPath() %>/style/bootstrap-select.min.css" rel="stylesheet" >
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
            <a href="../accElement/manage.jsp" class="list-group-item">科目分类</a>
            <a href="../coa/manage.jsp" class="list-group-item">会计科目</a>
            <a href="../accUsage/manage.jsp" class="list-group-item active">科目用法</a>
          </div>
        </div><!--/.sidebar-offcanvas-->
          
        <div class="col-xs-12 col-sm-10 pull-right">
          <p class="pull-left visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="row">
            <div class="col-xs-12 col-lg-12">
              <h3 align="center">科目用法</h3>
              	<div class="form-horizontal">
					<div class="form-group">
							<label class="col-sm-2 control-label" for="name">会计制度</label>
							<div class="col-sm-4">
							    <select id="standard" name="standard" class="form-control " onchange="getCOA()"></select>
							</div>
							<label class="col-sm-2 control-label" for="coa">会计科目</label>
							<div class="col-sm-4">
                                <select id="coa" class="selectpicker show-tick form-control" data-live-search="true" ></select>
							</div>
				    </div>
					<div class="form-group">
							<label class="col-sm-2 control-label" for="status">状态</label>
							<div class="col-sm-4">
								<div class="checkbox">
									<label><input type="checkbox" id="status" name="status">启用</label>
								</div>
					        </div>
							<div class="col-sm-6">
						    </div>
							
				    </div>
					<div class="form-group">
							<div class="col-sm-1"></div>
							<div id="usages" class="col-sm-10">
								<!-- <textarea id="usages" name="usages" class="form-control" data-provide="markdown" data-iconlibrary="fa" rows="16"></textarea> -->
								<textarea id="usages-markdown-doc" style="display:none;"></textarea>
			                </div>
					</div>
					<div class="form-group" style="text-align: center; height: 60px;">
						<input type="hidden" id="id" name="id">
						<button type="button" class="btn btn-defalt" onclick="history.go(-1);">返回</button>
						<button type="button" class="btn btn-success" id="save">保存</button>
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
    <script src="<%=request.getContextPath() %>/tool/mdeditor/editormd.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap-select.min.js"></script>
    <script>
	$._messengerDefaults = {
         extraClasses: 'messenger-fixed messenger-theme-air messenger-on-bottom'
    }
	
	var mdEditor;
	
	function getCOA(){
		var accStandardID = $("#standard").val();
		$.get("/coa/admin/queryBy",
      		  { accStandardID : accStandardID },
      		  function(responseTxt, status){
				if(status == "success"){
					var data = responseTxt.data; 
					$("#coa").empty();
					if( null != data ){
						var len = data.length;
						var datas = responseTxt.data;
						for(var i = 0; i < len; i++){
							var option = $("<option value=\"" + datas[i].id + "\"></option>");
							option.text( datas[i].code + " " + datas[i].name );
							$("#coa").append( option );
						}
					}
				}
				var coaID = ${param.coaID != null ? param.coaID : -1 };
			    $("#coa").val( coaID );
				$(".selectpicker").selectpicker("refresh");
		      },
		     "json"
		);
	}

	$("#save").click(function(){
	    var _id = $("#id").val( );
		var _accStandardID = $("#standard").val();
	    var _coaID = $("#coa").val();
	    var _usages =  $("#usages-markdown-doc").val();
	    var _status = $("#status").prop("checked");
	    var data, url;
	    var contentType = "application/json";
	    if (  _id != -1 && _id != "") {//如果ID不为空，说明是修改
	    	url = "/accUsage/admin/update";
	    	data = JSON.stringify({
	                id: _id,
	                coa: {
	                    id: _coaID
	                },
	                usages: _usages,
	                status: _status
	            });
	    } else {//ID是空，说明是新增
	    	url = "/accUsage/admin/add";
	    	data = JSON.stringify( {
	    		    coa: {
	                    id: _coaID
	                },
	                usages: _usages,
	                status: _status
	            } );
	    }
		$.ajax({
			type : "POST",
			url : url,
			data : data,
			contentType : contentType,
			dataType : "json",
			success : function(responseTxt, status){
				Messenger().post({
					message : "保存成功！",//提示信息
      				type : 'success',//消息类型。error、info、success
      				hideAfter : 2,//多长时间消失
      				showCloseButton : true,//是否显示关闭按钮
      				hideOnNavigate : false //是否隐藏导航
      		    });
  				setTimeout(function(){window.location.href="/jsp/gaap/accUsage/manage.jsp?accStandardID=" + _accStandardID + "&coaID=" + _coaID;}, 1000);
		    }
		});
	});

	$(document).ready(function(){

		mdEditor = editormd("usages", {//删除了codemirror下部分不常见语言的样式
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
		
		if( id != -1){
	    	$.get("/accUsage/detail",
	        		  { accUsageID: id },
	        		  function(responseTxt, status, xhr){
	        			  var data = responseTxt.data;
						  $("#usages-markdown-doc").val( data.usages );
	        			  $("#status").prop( "checked", data.status );
	        			  $("#id").val( id );
	        		  },
	        		  "json");
		}
		
		$.get("/accStandard/admin/list",function(responseTxt, status){
			if(status == "success"){
				var len = responseTxt.data.length;
				var datas = responseTxt.data;
				for(var i = 0; i < len; i++){
					var option = $("<option value=\"" + datas[i].id + "\"></option>");
					option.text( datas[i].name + "(" + datas[i].exeYear + ")" );
					$("#standard").append( option );
				}
  			    $("#standard").val( accStandardID );
				getCOA();
			}
		});

	});
	</script>
</body>
</html>