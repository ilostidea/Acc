<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>
<base href="<%=request.getContextPath()%>/user/admin/add">
<body>

    <jsp:include page="../topbar.jsp"	 flush="true">
    	<jsp:param value="4" name="menu"/>
    </jsp:include>

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-left" >

        <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="<%=request.getContextPath()%>/user/admin/add" class="list-group-item">新增/编辑用户</a>
            <a href="<%=request.getContextPath()%>/user/admin/list"" class="list-group-item active">查询用户</a>
          </div>
        </div><!--/.sidebar-offcanvas-->
          
        <div class="col-xs-12 col-sm-10 pull-right">
          <p class="pull-left visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="row">
            <div class="col-xs-12 col-lg-12">
             <!-- 右侧内容区 -->
            <div class="col-xs-12 col-lg-12">
              <h3 align="center">用户查询</h3>
              	<div class="form-horizontal">
		             <div class="form-group">
							<label class="col-sm-2 control-label" for="account">Mb./Email/NickName</label>
							<div class="col-sm-4">
	              		 		<form id="queryForm" action="<%=request.getContextPath()%>/user/admin/list" method="get">
								    <input type="text" id="account" name="account" class="form-control " onchange="findUser()"/>
					    		</form>
							</div>
							<div class="col-sm-6">
					  			<a class="btn btn-success btn-sm pull-right" href="<%=request.getContextPath()%>/user/admin/add"><i class="fa fa-file"></i> 新增用户</a>
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
							      <th>ID</th>
							      <th>Mobile</th>
							      <th>Email</th>
							      <th>NickName</th>
							      <th>状态</th>
							      <th>操作</th>
							    </tr>
							  </thead>
							  <tbody id="data-list">
							         <c:forEach var="userModel" items="${userList}" >
							            <tr>
			
							                <td>${userModel.id}</td>
							                <td>${userModel.mobile}</td>
							                <td>${userModel.email}</td>
							                <td>${userModel.nickName}</td>
							                <td>${userModel.status}</td>
							                <td>
							                <a href="<%=request.getContextPath()%>/user/show/${userModel.id}" class="text-primary"><i class="far fa-id-card fa-xs" aria-hidden="true"></i></a>&nbsp;&nbsp;
							                <a href="javascript:delUser(${userModel.id});" class="text-danger"><i class="far fa-trash-alt fa-xs" aria-hidden="true"></i></a>&nbsp;&nbsp;
							                <a href="<%=request.getContextPath()%>/user/edit/${userModel.id}" class="text-primary"><i class="far fa-edit fa-xs" aria-hidden="true"></i></a>
							                </td>
							                <%
							                /** 
							                    <td>${userModel.id}</td>
							                    <td>${userModel.mobile}</td>
							                    <td>${userModel.email}</td>
							                    <td>${userModel.nickName}</td>
							                    <td>${userModel.employee.gender}</td>
							                    <td><a href="show/${userModel.id}">详细</a></td>
							                    <td><a href="edit/${userModel.id}">编辑</a></td>
							                    <td><a href="del/${userModel.id}">删除</a></td>
							                    **/
							                %>
			
							            </tr>
							        </c:forEach>
							  </tbody>
							</table>
						</div>
						<div class="col-sm-1"></div>
				    </div>
            </div>
          </div><!--/row-->
          </div>
		    
			<!-- 右侧内容区域结束 -->
			</div>
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

      </div><!--/row-->

    </div><!--/.container-->
    <%--
    <form id="delUserForm" action="<%=request.getContextPath()%>/user/admin/del" method="post">
    	<input type="hidden" name="_method" value="DELETE">
    	<input type="hidden" name="userId"  id="userId" value="">
    </form>
--%>
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
    
    function findUser(){
		var queryForm = $("#queryForm");
		delUserForm.submit();
    }
	
    function delUser(userId){
		<%--
		var delUserForm = $("#delUserForm");
		var userId = $("#userId");
		userId.value = userId;
		delUserForm.submit();
		--%>

    	 $.ajax({
             type: 'delete',
             url: "<%=request.getContextPath()%>/user/admin/del/"+userId,
             data:'',
             success: function(data){
             },
             error: function(data){
             }
         });
    	 setTimeout("window.location.href='<%=request.getContextPath()%>/user/admin/list';",1000);
    }
    </script>
</body>
</html>