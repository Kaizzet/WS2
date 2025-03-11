<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pe.BlogPosts.BlogPostsDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Blog Post</title>
    </head>
    <body>
        
        <jsp:include page="/menu.jsp" flush="true" /> 
        <h1>Edit Blog Post</h1>
        
        <p>Login user: ${sessionScope.usersession.username}</p>
        
        <form action="MainController" method="POST">
            <table>
                <tr>
                    <td>ID</td>
                    <td>
                        <input type="text" name="postid" value="${requestScope.blogPost.postid}" required readonly>
                    </td>
                </tr>
                
                <tr>
                    <td>Title</td>
                    <td>
                        <input type="text" name="title" value="${requestScope.blogPost.title}" required>
                    </td>
                </tr>
                
                <tr>
                    <td>Content</td>
                    <td>
                        <textarea name="content" rows="5" cols="40" required>${requestScope.blogPost.content}</textarea>
                    </td>
                </tr>
                
                <tr>
                    <td>Author</td>
                    <td>
                        <input type="text" name="author" value="${requestScope.blogPost.author}" required>
                    </td>
                </tr>
                
                <tr>
                    <td>Publish Date</td>
                    <td>
                        <input type="date" name="publishdate" value="${requestScope.blogPost.publishdate}" required>

                    </td>
                </tr>
                
                <tr>
                    <td colspan="2">
                        <input name="action" value="${requestScope.blogPost.postid == 0 ? 'insert' : 'update'}" type="hidden">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form>
        
        <br>
        <a href="MainController?action=list">Back to List</a>
        
    </body>
</html>