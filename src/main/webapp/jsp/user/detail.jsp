<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail User</title>
</head>
<body>
    <c:out value="${user.id }"></c:out>
    <br>
    <c:out value="${user.mobile }"></c:out>
    <br>
    <c:out value="${user.email }"></c:out>
    <br>
    <c:out value="${user.nickName }"></c:out>
    <br>
    <c:out value="${user.status }"></c:out>
    <a href="<%=request.getContextPath()%>/user/list">返回到列表</a>
</body>
</html>