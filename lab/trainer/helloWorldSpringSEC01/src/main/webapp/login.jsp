<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.springframework.security.web.WebAttributes" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<h1>Login</h1>

<div class="section">
    <table class="search" border="0">
        <tr><th>Username</th><th>Password</th><th align="left">Role</th></tr>
        <tr><td>efall</td><td>pass</td><td>ROLE_USER</td></tr>
        <tr><td>agramm</td><td>kg</td><td>ROLE_USER</td></tr>
        <tr><td>hdampf</td><td>luft</td><td>ROLE_USER,ROLE_ADMIN</td></tr>
    </table>        
</div>

<div class="section">
	<c:if test="${param.error != null}">
		<div class="errors">
			Your login attempt was not successful, try again.<br /><br />
			Reason: <%= ((AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)).getMessage() %>
		</div>
	</c:if>
</div>

<div class="section">
	<form name="f" action="<c:url value="/login" />" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<fieldset>
			<div class="field">
				<div class="label"><label for="username">Username:</label></div>
				<div class="output">
					<input type="text" name="username" id="username" />
				</div>
			</div>
			<div class="field">
				<div class="label"><label for="password">Password:</label></div>
				<div class="output">
					<input type="password" name="password" id="password" />
				</div>
			</div>
			<!--
			<div class="field">
				<div class="label"><label for="remember_me">Remember me:</label></div>
				<div class="output">
					<input type="checkbox" name="remember-me" id="remember_me" />
				</div>
			</div>
            -->
            <div class="form-buttons">
                <div class="button">
                    <input name="submit" id="submit" type="submit" value="Login" />
                </div>
            </div>
		</fieldset>
	</form>
</div>
</body>
</html>