package pe.BlogPosts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DBUtils;

public class BlogPostsDAO {

    /*
     * Lấy danh sách blog với từ khóa tìm kiếm và cột sắp xếp
     */
    public List<BlogPostsDTO> list(String keyword, String sortCol) throws ClassNotFoundException {
        List<BlogPostsDTO> list = new ArrayList<>();
        String sql = "SELECT postid, title, content, author, publishdate FROM BlogPosts";

        // Xử lý tìm kiếm
        if (keyword != null && !keyword.isEmpty()) {
            sql += " WHERE title LIKE ? OR content LIKE ? OR author LIKE ?";
        }

        // Xử lý sắp xếp
        if (sortCol != null && !sortCol.isEmpty()) {
            sql += " ORDER BY " + sortCol + " ASC";
        }

        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            if (keyword != null && !keyword.isEmpty()) {
                stmt.setString(1, "%" + keyword + "%");
                stmt.setString(2, "%" + keyword + "%");
                stmt.setString(3, "%" + keyword + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    BlogPostsDTO post = new BlogPostsDTO();
                    post.setPostid(rs.getInt("postid"));
                    post.setTitle(rs.getString("title"));
                    post.setContent(rs.getString("content"));
                    post.setAuthor(rs.getString("author"));
                    post.setPublishdate(rs.getDate("publishdate"));
                    list.add(post);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error in BlogPostsDAO.list(): " + ex.getMessage());
            ex.printStackTrace();
        }
        return list;
    }

    /*
     * Lấy thông tin chi tiết của một bài viết theo ID
     */
    public BlogPostsDTO load(int id) throws ClassNotFoundException {
        String sql = "SELECT postid, title, content, author, publishdate FROM BlogPosts WHERE postid = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BlogPostsDTO post = new BlogPostsDTO();
                    post.setPostid(rs.getInt("postid"));
                    post.setTitle(rs.getString("title"));
                    post.setContent(rs.getString("content"));
                    post.setAuthor(rs.getString("author"));
                    post.setPublishdate(rs.getDate("publishdate"));
                    return post;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error in BlogPostsDAO.load(): " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    /*
     * Thêm bài viết mới và trả về ID của bài viết vừa thêm
     */
    public int insert(BlogPostsDTO post) throws ClassNotFoundException {
        String sql = "INSERT INTO BlogPosts (title, content, author, publishdate) VALUES (?, ?, ?, ?)";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getAuthor());
            stmt.setDate(4, post.getPublishdate());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error in BlogPostsDAO.insert(): " + ex.getMessage());
            ex.printStackTrace();
        }
        return -1; // Nếu lỗi, trả về -1
    }

    /*
     * Cập nhật bài viết
     */
    public boolean update(BlogPostsDTO post) throws ClassNotFoundException {
        String sql = "UPDATE BlogPosts SET title = ?, content = ?, author = ?, publishdate = ? WHERE postid = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getAuthor());
            stmt.setDate(4, post.getPublishdate());
            stmt.setInt(5, post.getPostid());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error in BlogPostsDAO.update(): " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    /*
     * Xóa bài viết theo ID
     */
    public boolean delete(int id) throws ClassNotFoundException {
        String sql = "DELETE FROM BlogPosts WHERE postid = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error in BlogPostsDAO.delete(): " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
}
