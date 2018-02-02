<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User</title>
</head>
<base href="<%=request.getContextPath()%>/user/admin/add">
<body>
    <form:form modelAttribute="sysUser" method="post">
        <div style="line-height: 20px;position: relative;text-align: center;">
        	<!--<form:errors path="*"></form:errors><br/><br/>-->

	        Mobile:&nbsp;&nbsp;<form:input path="mobile"/><br/>
        	<font color="red"><form:errors path="mobile"></form:errors></font><br/>
	        <br>
	        Email:&nbsp;<form:input path="email"/><br/>
        	<font color="red"><form:errors path="email"></form:errors></font><br/>
	        <br>
	        Name:&nbsp;&nbsp;<form:input path="nickName"/><br/>
        	<font color="red"><form:errors path="nickName"></form:errors></font><br/>
	        <br>
	        PassWord:<form:input path="passwd"/><br/>
        	<font color="red"><form:errors path="passwd"></form:errors></font><br/>
	        <br>
		    <input type="hidden" name="status" value="1" />
	        <input type="submit" value="提交">
	        <br>
    		<a href="<%=request.getContextPath()%>/user/list">返回到列表</a>
        </div>
    </form:form>
</body>
</html>