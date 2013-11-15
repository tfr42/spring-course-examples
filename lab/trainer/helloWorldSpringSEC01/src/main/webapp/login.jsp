<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<c:if test="${not empty param.login_error}">
		<div class="errors">
			Your login attempt was not successful, try again.<br /><br />
			Reason: <%= ((AuthenticationException) session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
		</div>
	</c:if>
</div>

<div class="section">
	<form name="f" action="<c:url value="/loginProcess" />" method="post">
		<fieldset>
			<div class="field">
				<div class="label"><label for="j_username">Username:</label></div>
				<div class="output">
					<input type="text" name="j_username" id="j_username" />
				</div>
			</div>
			<div class="field">
				<div class="label"><label for="j_password">Password:</label></div>
				<div class="output">
					<input type="password" name="j_password" id="j_password" />
				</div>
			</div><!-- 
			<div class="field">
				<div class="label"><label for="remember_me"><fmt:message key="login.rememberMe"/>:</label></div>
				<div class="output">
					<input type="checkbox" name="_spring_security_remember_me" id="remember_me" />
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