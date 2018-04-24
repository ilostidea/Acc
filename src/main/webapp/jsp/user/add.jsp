<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <a href="#" class="list-group-item active">新增/编辑用户</a>
            <a href="<%=request.getContextPath()%>/user/admin/list" class="list-group-item">查询用户</a>
          </div>
        </div><!--/.sidebar-offcanvas-->
          
        <div class="col-xs-12 col-sm-10 pull-right">
          <p class="pull-left visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="row">
            <div class="col-xs-6 col-lg-12">
             <!-- 右侧内容区 -->
				<form:form modelAttribute="sysUser" method="POST" >
				<form:errors path="*"></form:errors><br/><br/>
            	<h3 align="center">新增用户</h3>
            	<hr>
				<div class="form-horizontal">
					
					<div class="form-group">
							<label class="col-sm-2 control-label" for="mobile">手机</label>
							<div class="col-sm-4">
								<form:input path="mobile" id="mobile" class="form-control" placeholder="请输入手机"/>
								<font color="red"><form:errors path="mobile"></form:errors></font>
							</div>
							<label class="col-sm-2 control-label" for="email">邮箱</label>
							<div class="col-sm-4">
							    <form:input path="email" id="email" class="form-control" placeholder="请输入邮箱"/>
							    <font color="red"><form:errors path="email"></form:errors></font>
							</div>
				    </div>
					<div class="form-group">
							<label class="col-sm-2 control-label" for="nickName">昵称</label>
							<div class="col-sm-4">
								<form:input path="nickName" id="nickName" class="form-control" placeholder="请输入昵称"/>
								<font color="red"><form:errors path="nickName"></form:errors></font>
							</div>
							
							<label class="col-sm-2 control-label" for="passwd">密码</label>
							<div class="col-sm-4">
								<form:input path="passwd" id="passwd" class="form-control" placeholder="请输入密码"/>
								<font color="red"><form:errors path="passwd"></form:errors></font>
					        </div>
				    </div>
					<div style="text-align: center; height: 60px;">
						<input type="hidden" name="status" value="1" />
						<button type="button" class="btn btn-defalt" onclick="window.location.href='<%=request.getContextPath()%>/user/admin/list';">用户列表</button>
						<input type="submit"  class="btn btn-success" id="save" value="提交">
					</div>
				</div>
				</form:form>
				
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
</body>
</html>