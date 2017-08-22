<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add a new guest</title>
</head>
<body>

<h1>Hello <security:authentication property="principal.username"/>, enter a new guest, please:</h1>

<form:form modelAttribute="addGuestForm" method="post">
  <table>
   <tr>
	  <td>Firstname:</td>
	  <td><form:input path="firstname" /><form:errors path="firstname"/></td>
  </tr>
  <tr>
	  <td>Lastname:</td>
	  <td><form:input path="lastname" /><form:errors path="lastname"/></td>
  </tr>
  <tr>
	  <td></td>
	  <td><input type="reset" value="Reset" /> <input type="submit" value="Invite" /> </td>
		  </tr>
		  <tr>
  	<td colspan="2" align="center">${msg}</td>
  </tr>
 </table>
</form:form>
<p align="center">
<a href="<c:url value="/index.jsp"/>">Home</a></p>
</body>
</html>