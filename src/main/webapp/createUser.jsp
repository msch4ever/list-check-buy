<%@ page import="cz.los.service.UserService" %>
<%@ page import="static cz.los.service.UserService.USER_SERVICE" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create User</title>
</head>
<body>
<h1>Create User</h1>
<form action="createUser.jsp" method="post">
    Name: <input type="text" name="name"><br>
    <input type="submit" value="Create">
</form>

<%-- Get the name parameter from the form submission and call createUser method --%>
<%
    if(request.getParameter("name") != null) {
        ServletContext servletContext = config.getServletContext();
        UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        String name = request.getParameter("name");
        userService.createUser(name);
    }
%>
</body>
</html>