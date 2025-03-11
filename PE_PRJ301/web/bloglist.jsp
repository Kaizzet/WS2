<%@page import="java.util.List"%>
<%@page import="pe.BlogPosts.BlogPostsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog Posts List</title>
    </head>
    <body>
        <%@ include file="/menu.jsp" %>
        <h1>Blog Posts List</h1>
        <a href="MainController?action=viewShelf">
    <button>📚 View Shelf</button>
</a>

        
<form action='MainController' method=GET> 
    <input type="hidden" name="action" value="list">
    
    <label>Search by Title:</label>
    <input name="title" type="text" value="<%= request.getParameter("title") != null ? request.getParameter("title") : "" %>">

    <label>Search by Date:</label>
    <input name="publishDate" type="date" value="<%= request.getParameter("publishDate") != null ? request.getParameter("publishDate") : "" %>">

    <input type="submit" value="Search">
</form>

        
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Author</th>
                <th>Publish Date</th>
                <th>Actions</th>
            </tr>
            <%
                List<BlogPostsDTO> list = (List<BlogPostsDTO>) request.getAttribute("bloglist");
                if (list != null) {
                    for (BlogPostsDTO post : list) {

            %>
            <tr>
                <td><a href="MainController?action=details&id=<%= post.getPostid() %>"><%= post.getPostid() %></a></td>
                <td><%= post.getTitle() %></td>
                <td><%= post.getAuthor() %></td>
                <td><%= post.getPublishdate() %></td>
                <td>
                    <a href="MainController?action=edit&id=<%= post.getPostid() %>">Edit</a> |
                    <form action="MainController" method="POST" style="display:inline;">
                        <input name="action" value="delete" type="hidden">
                        <input name="id" value="<%= post.getPostid() %>" type="hidden">
                        <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this post?');">
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <br>
        <a href="MainController?action=create">Create New Blog Post</a>
    </body>
</html>
