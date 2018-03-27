<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit User</title>
</head>
<body>
<c:url var="saveUrl" value="/user/save/${userAttribute.id }" />
    <form:form modelAttribute="userAttribute" action="${saveUrl}" method="post">
        <table>
            <tr>
                <td>ID:</td>
                <td><form:input path="id" readonly="true"/></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><form:input path="nickName"/></td>
            </tr>
            <tr>
                <td>Mobile:</td>
                <td><form:input path="mobile"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input path="email"/></td>
            </tr>
            <tr>
                <td>password:</td>
                <td><form:input path="passwd"/></td>
            </tr>
            <tr>
                <td>Status:</td>
                <td><form:input path="status"/></td>
            </tr>
        </table>
        <input type="submit" value="Save">
    </form:form>
    <a href="<%=request.getContextPath()%>/user/admin/list">返回到列表</a>
</body>
</html>