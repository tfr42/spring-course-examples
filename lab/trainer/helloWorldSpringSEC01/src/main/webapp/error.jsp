<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Page</title>
</head>
<body>
<h2>Oops! This is an authorization Error!</h2>
 
<h3>Debug Information:</h3>
<p>Exception message: '${exception.message}'</p>
<p>The user '<security:authentication property="principal.username"/>' in role '<security:authentication property="authorities"/>' is missing privileges.
<hr width="50%"> 
<strong>Exception Stack Trace</strong><br>
<c:forEach items="${exception.stackTrace}" var="ste">
	<pre>
      ${ste}
	</pre>    
</c:forEach>
</body>
</html>