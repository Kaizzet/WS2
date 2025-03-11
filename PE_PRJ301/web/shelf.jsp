<%@page import="java.util.List"%>
<%@page import="pe.BlogPosts.BlogPostsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Shelf</title>
    </head>
    <body>
        <%@ include file="/menu.jsp" %>
        <h1>My Shelf</h1>
        
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Author</th>
                <th>Publish Date</th>
                <th>Actions</th>
            </tr>
            <%
                List<BlogPostsDTO> shelf = (List<BlogPostsDTO>) session.getAttribute("shelf");
                if (shelf != null && !shelf.isEmpty()) {
                    for (BlogPostsDTO post : shelf) {
            %>
            <tr>
                <td><%= post.getPostid() %></td>
                <td><%= post.getTitle() %></td>
                <td><%= post.getAuthor() %></td>
                <td><%= post.getPublishdate() %></td>
                <td>
                    <a href="MainController?action=details&id=<%= post.getPostid() %>">View</a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5">Your shelf is empty.</td>
            </tr>
            <%
                }
            %>
        </table>
        <br>
        <a href="MainController?action=list">Back to List</a>
    </body>
</html>
