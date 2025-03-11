<%@page import="pe.BlogPosts.BlogPostsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog Post Details</title>
    </head>
    <body>
        <%@ include file="/menu.jsp" %>

        <h2>Blog Post Details</h2>

        <%
            BlogPostsDTO post = (BlogPostsDTO) request.getAttribute("blogPost");
            if (post != null) {
        %>
        <table border="1">
            <tr>
                <td><strong>ID:</strong></td>
                <td><%= post.getPostid() %></td>
            </tr>
            <tr>
                <td><strong>Title:</strong></td>
                <td><%= post.getTitle() %></td>
            </tr>
            <tr>
                <td><strong>Content:</strong></td>
                <td><%= post.getContent() %></td>
            </tr>
            <tr>
                <td><strong>Author:</strong></td>
                <td><%= post.getAuthor() %></td>
            </tr>
            <tr>
                <td><strong>Publish Date:</strong></td>
                <td><%= post.getPublishdate() %></td>
            </tr>
        </table>

        <br>
        <a href="MainController?action=list">Back to List</a> |
        <a href="MainController?action=edit&id=<%= post.getPostid() %>">Edit</a> |
        <a href="MainController?action=addToShelf&id=<%= post.getPostid() %>">Add to Shelf</a> |

        <form action="MainController" method="POST" style="display:inline;">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="id" value="<%= post.getPostid() %>">
            <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this blog post?');">
        </form>

        <%
        } else {
        %>
        <p>Blog post not found.</p>
        <%
            }
        %>
    </body>
</html>
