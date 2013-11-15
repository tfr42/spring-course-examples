<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head><title>New Guest</title></head>
  <body>
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