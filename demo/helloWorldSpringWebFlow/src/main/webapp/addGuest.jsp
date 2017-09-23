<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head><title>Add Gast</title></head>
  <body>
      <h1 align="center">Add guest</h1>
      <form method="post" action="${flowExecutionUrl}">
        <input type="hidden" name="_eventId" value="continue">
        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
            <table>
              <tr>
                  <td>Firstname:</td>
                  <td><input name="firstname" value="${guestBean.firstname}" required="true"/></td>
              </tr>
              <tr>
                  <td>Lastname:</td>
                  <td><input name="lastname" value="${guestBean.lastname}" required="true"/></td>
              </tr>
              <tr>
                  <td></td>
                  <td><input type="reset" value="Reset" /> <input type="submit" value="Continue" /> </td>
              </tr>
            </table>
      </form>
  </body>
</html>