<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<style>
    .navbar-right li > a span.badge {
        position: absolute;
        top: 8px;
        right: 25px;
        padding: 2px 5px;
        color: #ffffff;
    }
	.badge-default,
	.label-default {
		background-color: #999 !important;
	}
	.badge-primary,
	.label-primary {
		background-color: #dc6767 !important;
	}
	.badge-red,
	.label-red {
		background-color: #bf4346 !important;
	}
	.badge-orange,
	.label-orange {
		background-color: #e9662c !important;
	}
	.badge-green,
	.label-green {
		background-color: #488c6c !important;
	}
	.badge-yellow,
	.label-yellow {
		background-color: #f2994b !important;
	}
	.badge-blue,
	.label-blue {
		background-color: #0a819c !important;
	}
	.badge-violet,
	.label-violet {
		background-color: #9351ad !important;
	}
	.badge-pink,
	.label-pink {
		background-color: #bf3773 !important;
	}
	.badge-grey,
	.label-grey {
		background-color: #4b5d67 !important;
	}
	.badge-dark,
	.label-dark {
		background-color: #594857 !important;
	}
	.label-success,
	.badge-success {
		background-color: #5cb85c;
		background-image: none !important;
	}
	.label-warning,
	.badge-warning {
		background-color: #f0ad4e;
		background-image: none !important;
	}
	.label-danger,
	.badge-danger {
		background-color: #d9534f;
		background-image: none !important;
	}
	.label-info,
	.badge-info {
		background-color: #5bc0de;
		background-image: none !important;
	}
</style>
<nav class="navbar navbar-fixed-top navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="../index.jsp">Acc Admin</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li id="menu1"><a href="<%=request.getContextPath() %>/jsp/index.jsp">统计</a></li>
				<li id="menu2"><a href="<%=request.getContextPath() %>/jsp/gaap/index.jsp">财会</a></li>
				<li id="menu3"><a href="<%=request.getContextPath() %>/jsp/qna/index.jsp">问答</a></li>
				<li id="menu4"><a href="<%=request.getContextPath() %>/jsp/user/index.jsp">用户</a></li>
				<li >
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
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"><i class="fa fa-bell fa-fw"></i><span class="badge badge-warning">3</span></a>
				</li>
				<li id="topbar-chat" class="hidden-xs">
					<a href="#" class="dropdown-toggle"><i class="fa fa-comments"></i><span class="badge badge-info">3</span></a>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<img src="" alt="" class="img-responsive img-circle" />&nbsp;<span class="hidden-xs">Robert John</span>&nbsp;<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="fa fa-user"></i>My Profile</a></li>
						<li class="divider"></li>
						<li><a href="Login.html"><i class="fa fa-key"></i>Log Out</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<!-- /.container -->
</nav>
<!-- /.navbar -->
<script>
<%
  String seq = request.getParameter("menu");
  String menu = "menu" + seq;
%>
var menu = document.getElementById("<%=menu%>");
menu.className = "active";
</script>