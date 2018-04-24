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
            <a href="#" class="list-group-item active">查询用户</a>
          </div>
        </div><!--/.sidebar-offcanvas-->
          
        <div class="col-xs-12 col-sm-10 pull-right">
          <p class="pull-left visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="row">
            <div class="col-xs-12 col-lg-12">
             <!-- 右侧内容区 -->

            	<h3 align="center">用户详情</h3>
            	<hr>
				<div class="form-horizontal">
					
					<div class="form-group">
							<div class="col-sm-2" style="text-align:right;font-weight:bold;">ID:</div>
							<div class="col-sm-10" >
								<c:out value="${user.id }"></c:out>
							</div>
				    </div>
					<div class="form-group">
							<div class="col-sm-2" style="text-align:right;font-weight:bold;">手机:</div>
							<div class="col-sm-4" >
								<c:out value="${user.mobile }"></c:out>
							</div>
							<div class="col-sm-2" style="text-align:right;font-weight:bold;">邮箱:</div>
							<div class="col-sm-4">
								<c:out value="${user.email }"></c:out>
							</div>
				    </div>
					<div class="form-group">
							<div class="col-sm-2" style="text-align:right;font-weight:bold;">昵称:</div>
							<div class="col-sm-4">
    							<c:out value="${user.nickName }"></c:out>
							</div>
							<div class="col-sm-2" style="text-align:right;font-weight:bold;">状态:</div>
							<div class="col-sm-4">
    							<c:out value="${user.status }"></c:out>
					        </div>
				    </div>
					<div style="text-align: center; height: 60px;">
						<button type="button" class="btn btn-defalt" onclick="window.location.href='<%=request.getContextPath()%>/user/admin/list';">返回用户列表</button>
					</div>
				</div>

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