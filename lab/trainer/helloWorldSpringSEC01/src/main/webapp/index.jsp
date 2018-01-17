<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SEC01</title>
</head>
<body>
<h1>SEC01</h1>
<div>Welcome '<security:authentication property="principal.username"/>'!</div>
<ul>
<li><a href="greeting/welcome.do">See welcome reception</a></li>
<li><a href="greeting/add.do">Add Guest</a></li>
<li>
    <c:url var="logoutUrl" value="/logout" />
    <form action="${logoutUrl}" id="logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <a href="#" onclick="document.getElementById('logout').submit();">Logout</a></li>
</ul>
</body>
</html>