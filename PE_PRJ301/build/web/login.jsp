<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="login">
            <input name="user" type="text" placeholder="Username" required>
            <br>
            <input name="password" type="password" placeholder="Password" required>
            <br>
            <input type="submit" value="Login">
        </form>

   
        <% if (request.getAttribute("error") != null) { %>
            <p style="color:red;"><%= request.getAttribute("error") %></p>
        <% } %>


        <% if (request.getAttribute("message") != null) { %>
            <p style="color:green;"><%= request.getAttribute("message") %></p>
        <% } %>

    </body>
</html>
