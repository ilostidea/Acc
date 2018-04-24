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
    <link href="<%=request.getContextPath() %>/style/messenger-theme-air.css" rel="stylesheet" > <%--
	<link href="<%=request.getContextPath() %>/style/bootstrap-markdown.min.css" rel="stylesheet"> --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
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
            <a href="#" class="list-group-item active">科目用法</a>
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
							    <select id="standard" name="standard" class="form-control " onchange="getCOA();"></select>
							</div>
							<label class="col-sm-2 control-label" for="coa">会计科目</label>
							<div class="col-sm-4">
                                <select id="coa" class="selectpicker show-tick form-control" data-live-search="true" onchange="getUsage()"></select>
							</div>
				    </div>
					<div class="form-group">
							<div class="col-sm-6"></div>
							<div class="col-sm-6">
						        <input type="hidden" id="id" name="id">
							    <a class="btn btn-success btn-sm pull-right" href="#" onclick="toadd()"><i class="fa fa-file"></i> 新增科目</a>
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
							      <th>用途</th>
							      <th>状态</th>
							      <th>操作</th>
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
    <script src="<%=request.getContextPath() %>/js/messenger.js"></script><%-- 
    <script src="<%=request.getContextPath() %>/js/bootstrap-markdown.js"></script>
    <script src="<%=request.getContextPath() %>/js/bootstrap-markdown.zh.js"></script>
    <script src="<%=request.getContextPath() %>/js/marked.js"></script> --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/i18n/defaults-zh_CN.min.js"></script>
    <script>
	$._messengerDefaults = {
         extraClasses: 'messenger-fixed messenger-theme-air messenger-on-bottom'
    };
	
	function toadd(){
		var accStandardID = $("#standard").val();
		var coaID = $("#coa").val();
    	window.location.href = "/jsp/gaap/accUsage/add.jsp?accStandardID=" + accStandardID + "&coaID=" + coaID;
	}
	
    function rm(id, rowId){
    	$.post("/accUsage/admin/del",
      		  { coaID: id },
      		  function(data,status,xhr){
      			  var tr = $("#"+rowId);
      			  tr.remove();
      			  if ( status == "success")
	      			  Messenger().post({
	      				  message: "删除成功！",//提示信息
	      				  type: "success",//消息类型。error、info、success
	      				  hideAfter: 2,//多长时间消失
	      				  showCloseButton:true,//是否显示关闭按钮
	      				  hideOnNavigate: false //是否隐藏导航
	      		      });
      			  else
	      			  Messenger().post({
	      				  message: data.meta.message,//提示信息
	      				  type: "error",//消息类型。error、info、success
	      				  hideAfter: 2,//多长时间消失
	      				  showCloseButton:true,//是否显示关闭按钮
	      				  hideOnNavigate: false //是否隐藏导航
	      		      });
      		  },
      		  "json"
      	);
    }
    
    function edit(id){
		var accStandardID = $("#standard").val();
		var coaID = $("#coa").val();
    	window.location.href = "/jsp/gaap/accUsage/add.jsp?id=" + id + "&accStandardID=" + accStandardID + "&coaID=" + coaID;
    }
	
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

				var accStandardID = ${param.accStandardID != null ? param.accStandardID : -1 };
				var coaID = ${param.coaID != null ? param.coaID : -1 };
				if( accStandardID != -1 )
				  $("#standard").val( accStandardID );
				if( coaID != -1 )
			      $("#coa").val( coaID );
				$(".selectpicker").selectpicker("refresh");
				
				getUsage();
		      },
		     "json"
		);
	}
	
	function getUsage(){
		var coaID = $("#coa").val();
		$("#data-list").empty();
		if ( coaID !=null )
			$.get("/accUsage/queryBy",
	      		  { coaID : coaID },
	      		  function(responseTxt, status){
					if(status == "success"){
	    				var len = responseTxt.data.length;
	    				var datas = responseTxt.data;
	    				for(var i = 0; i < len; i++){
	    					var tr = $("<tr id=\"tr" + i + "\"></tr>");
	    					var td0 = $("<td></td>").text(datas[i].usages);
	    					var td1 = $("<td></td>").text(datas[i].status?"启用":"禁用");
	    					var td2 = $("<td></td>").html("<a href=\"javascript:rm(" + datas[i].id + ", 'tr" + i + "');\" class=\"text-danger\"><i class=\"far fa-trash-alt fa-xs \" aria-hidden=\"true\"></i></a>" +
	    					                                             "&nbsp;&nbsp;" + 
	    					                                             "<a href=\"javascript:edit(" + datas[i].id + ");\" class=\"text-primary\"><i class=\"far fa-edit fa-xs \" aria-hidden=\"true\"></i></a>");
	    					$(tr).append( td0, td1, td2 );
	    					$("#data-list").append(tr);
	    				}
					}
			      },
			     "json"
			);
		
	}

	$(document).ready(function(){
		
		$.get("/accStandard/admin/list",function(responseTxt, status){
			if(status == "success"){
				var len = responseTxt.data.length;
				var datas = responseTxt.data;
				for(var i = 0; i < len; i++){
					var option = $("<option value=\"" + datas[i].id + "\"></option>");
					option.text( datas[i].name + "(" + datas[i].exeYear + ")" );
					$("#standard").append( option );
				}
				getCOA();
			}
		});

	});
	</script>
</body>
</html>