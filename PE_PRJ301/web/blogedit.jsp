<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pe.BlogPosts.BlogPostsDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.blogPost.postid == 0 ? "Create New Blog Post" : "Edit Blog Post"}</title>
    </head>
    <body>
        <jsp:include page="/menu.jsp" flush="true" /> 
        <h1>${requestScope.blogPost.postid == 0 ? "Create New Blog Post" : "Edit Blog Post"}</h1>
        
        <p>Login user: ${sessionScope.usersession.username}</p>
        
        <c:if test="${not empty requestScope.error}">
            <p style="color: red;">${requestScope.error}</p>
        </c:if>
        
        <form action="MainController" method="POST">
            <c:if test="${requestScope.blogPost.postid != 0}">
                <input type="hidden" name="postid" value="${requestScope.blogPost.postid}">
            </c:if>
            
            <table>
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
                        <input type="text" name="author" value="${requestScope.blogPost.author == null ? sessionScope.usersession.username : requestScope.blogPost.author}" required>
                    </td>
                </tr>
                
                <tr>
                    <td>Publish Date</td>
                    <td>
                        <input type="date" name="publishdate" value="${requestScope.blogPost.publishdate != null ? requestScope.blogPost.publishdate : ''}" required>
                    </td>
                </tr>
                
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="action" value="${requestScope.blogPost.postid == 0 ? 'insert' : 'update'}">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form>
        
        <br>
        <a href="MainController?action=list">Back to List</a>
    </body>
</html>
