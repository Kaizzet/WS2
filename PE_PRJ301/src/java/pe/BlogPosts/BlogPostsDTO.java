package pe.BlogPosts;

import java.sql.Date;

public class BlogPostsDTO {
    private int postid;
    private String title;
    private String content;
    private String author;
    private Date publishdate; // Đúng kiểu dữ liệu

    public BlogPostsDTO() {
    }

    public BlogPostsDTO(int postid, String title, String content, String author, Date publishdate) {
        this.postid = postid;
        this.title = title;
        this.content = content;
        this.author = author;
        this.publishdate = publishdate;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishdate() { // Trả về kiểu Date
        return publishdate;
    }

    public void setPublishdate(Date publishdate) { // Nhận vào kiểu Date
        this.publishdate = publishdate;
    }
}
