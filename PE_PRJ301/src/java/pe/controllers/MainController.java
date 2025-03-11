package pe.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.BlogPosts.BlogPostsDAO;
import pe.BlogPosts.BlogPostsDTO;
import pe.User.UserDAO;
import pe.User.UserDTO;

public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String HOME_PAGE = "bloglist.jsp";
    private static final String SHELF_PAGE = "shelf.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            System.out.println("Action received: " + action);
            HttpSession session = request.getSession();
            BlogPostsDAO blogDAO = new BlogPostsDAO();

            if (action == null) {
                session = request.getSession(false);
                if (session == null || session.getAttribute("usersession") == null) {
                    response.sendRedirect(LOGIN_PAGE);
                    return;
                }
                response.sendRedirect("MainController?action=list");
                return;
            }

            if (action.equals("login")) {
                String username = request.getParameter("user");
                String password = request.getParameter("password");
                UserDAO dao = new UserDAO();
                UserDTO user = dao.login(username, password);
                if (user != null) {
                    session.setAttribute("usersession", user);
                    response.sendRedirect("MainController?action=list");
                    return;
                } else {
                    request.setAttribute("error", "Invalid username or password");
                    url = LOGIN_PAGE;
                }
            } else if (action.equals("logout")) {
                session.invalidate();
                response.sendRedirect(LOGIN_PAGE);
                return;
            } else if (action.equals("list")) {
                String title = request.getParameter("title");
                String publishDate = request.getParameter("publishDate"); // Lấy ngày từ request
                String sortCol = request.getParameter("colSort");

                List<BlogPostsDTO> list = blogDAO.list(title, publishDate, sortCol);
                request.setAttribute("bloglist", list);
                url = HOME_PAGE;

            } else if (action.equals("details")) {
                int id = Integer.parseInt(request.getParameter("id"));
                BlogPostsDTO post = blogDAO.load(id);
                request.setAttribute("blogPost", post);
                url = "blogdetails.jsp";
            } else if (action.equals("edit")) {
                int id = Integer.parseInt(request.getParameter("id"));
                BlogPostsDTO post = blogDAO.load(id);
                request.setAttribute("blogPost", post);
                url = "blogedit.jsp";
            } else if (action.equals("create")) {
                request.setAttribute("blogPost", new BlogPostsDTO());
                url = "blogedit.jsp";
            } else if (action.equals("update")) {
                int id = Integer.parseInt(request.getParameter("postid"));
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                String author = request.getParameter("author");
                Date publishDate = Date.valueOf(request.getParameter("publishdate"));
                BlogPostsDTO post = new BlogPostsDTO(id, title, content, author, publishDate);
                blogDAO.update(post);
                response.sendRedirect("MainController?action=list");
                return;
            } else if (action.equals("insert")) {
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                String author = request.getParameter("author");
                Date publishDate = Date.valueOf(request.getParameter("publishdate"));
                BlogPostsDTO post = new BlogPostsDTO(0, title, content, author, publishDate);
                blogDAO.insert(post);
                response.sendRedirect("MainController?action=list");
                return;
            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                blogDAO.delete(id);
                response.sendRedirect("MainController?action=list");
                return;
            } else if (action.equals("addToShelf")) {
                int id = Integer.parseInt(request.getParameter("id"));
                BlogPostsDTO post = blogDAO.load(id);
                if (post != null) {
                    List<BlogPostsDTO> shelf = (List<BlogPostsDTO>) session.getAttribute("shelf");
                    if (shelf == null) {
                        shelf = new ArrayList<>();
                    }
                    shelf.add(post);
                    session.setAttribute("shelf", shelf);
                }
                response.sendRedirect("MainController?action=list");
                return;
            } else if (action.equals("viewShelf")) {
                url = SHELF_PAGE;
            }
        } catch (Exception e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            url = HOME_PAGE;
        }
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
