<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%
 ApplicationContext appContext =
  WebApplicationContextUtils.getWebApplicationContext(
               request.getSession().getServletContext());
  String[] names = appContext.getBeanDefinitionNames();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
   	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<title>MVC02</title>
  </head>
  <body>
    <h1>MVC02</h1>
    <ul>
        <li><a href="greeting/add">Add a guest</a>
        <li><a href="greeting/welcome">then get the welcome reception</a>
    </ul>
    <p>RESTful Methods:</p>
    <ul>
        <li><a href="greeting/to/Hans%20Dampf">Say hello to (Plain text)</a>
        <li><a href="greeting/Hans/Dampf">Say hello to (XML)</a>
        <li><a href="greeting/1">Find Guest by ID (XML)</a>
    </ul>
    <br><hr>
    <h3>Spring WebApplicationContext</h3>
    <table border="1"><tr><th>Bean id/name</th><th>Bean instance</th></tr>
    <%
    for (String name : names) {
        out.println("<tr><td>"+name + "</td><td> " + appContext.getBean(name) + "</td></tr>");
    }
    %>
    </table>
  </body>
</html>