<%@page import="com.bit.acc.service.intfs.UserService"%>
<%@page import="org.apache.shiro.subject.PrincipalCollection"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.bit.acc.model.SysUser"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h2>Hello World!</h2>
	<%
		Subject subject = SecurityUtils.getSubject();
		PrincipalCollection col = subject.getPrincipals();
		UserService userService = (UserService) WebApplicationContextUtils.getRequiredWebApplicationContext( session.getServletContext() ).getBean("sysUserService");
		List<SysUser> userModel = userService.findByAccount( col == null || col.isEmpty() ? "" : String.valueOf( col.getPrimaryPrincipal() ) );
	%>
	<%=( userModel==null || userModel.size() ==0 ?"":userModel.get(0).getNickName() )%>
	<%
		if (col == null || col.isEmpty()) {
	%>> 欢迎游客访问，
	<a href="<%=request.getContextPath()%>/jsp/user/login.jsp">点击登录</a>
	<a href="<%=request.getContextPath()%>/user/add">注册</a>
	<br />
	
	<%
		} else if (!subject.isAuthenticated()) {
	%>
	欢迎[<%=String.valueOf(col.getPrimaryPrincipal())%>]登录，
	<a href="<%=request.getContextPath()%>/jsp/user/login.jsp">点击登录</a>
	<a href="<%=request.getContextPath()%>/user/add">注册</a>
	<br />
	<%
		} else {
	%>
	欢迎[<%=String.valueOf(col.getPrimaryPrincipal())%>]登录，
	<a href="<%=request.getContextPath()%>/user/logout">点击退出</a>
	<br />
	<a href="<%=request.getContextPath()%>/user/admin/list">点击查看用户列表</a>
	<%
		}
	%>
	<shiro:authenticated>
		<shiro:hasRole name="admin">
		   我是管理员
		</shiro:hasRole>
	</shiro:authenticated>
</body>
</html>
