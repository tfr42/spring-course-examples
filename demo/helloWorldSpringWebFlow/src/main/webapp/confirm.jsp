<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head><title>Confirm</title></head>
  <body>
	  <h1 align="center">Please confirm</h1>
	  Do you want to add guest ${guestBean.firstname} ${guestBean.lastname}?
      <form method="post" action="${flowExecutionUrl}">
          <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
          <input type="submit" name="_eventId_revise" value="Revise" />
          <input type="submit" name="_eventId_cancelled" value="Cancel" />
          <input type="submit" name="_eventId_confirmed" value="Confirm" />
      </form>
  </body>
</html>