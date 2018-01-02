<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List User</title>
</head>
<body style="text-align: center;">
    <a href="add">Add</a>
    <table align="center" border="1px">
        <tr>
            <td>ID</td>
            <td>Mobile</td>
            <td>Email</td>
            <td>Name</td>
            <!-- <td>Gender</td>  -->
            <td colspan="3">Operate</td>
        </tr>
        <c:forEach var="userModel" items="${userList}" >
            <tr>
            
                <td>${userModel.id}</td>
                <td>${userMode.mobile}</td>
                <td>${userModel.email}</td>
                <td>${userModel.nickName}</td>
                <td><a href="show/${userModel.id}">详细</a></td>
                <td><a href="edit/${userModel.id}">编辑</a></td>
                <td><a href="del/${userModel.id}">删除</a></td>
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
    </table>
    <a href="<%=request.getContextPath()%>/">返回到欢迎页</a>
</body>
</html>