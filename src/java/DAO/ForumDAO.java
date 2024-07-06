/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Comments;
import model.Forum;

/**
 *
 * @author GoldCandy
 */
public class ForumDAO extends DBConnection {

    public void createNewPost(int userID, String postTitle, String postContext, String postIMG) {
        String query = "DBCC CHECKIDENT (forum_post, RESEED, 0); DBCC CHECKIDENT (forum_post, RESEED); insert into forum_post(userID,post_title,post_context,post_date,post_img)"
                + "values(?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setString(2, postTitle);
            ps.setString(3, postContext);
            String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date());
            ps.setString(4, timeStamp);
            ps.setString(5, postIMG);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void createNewComment(int userID, int postID, String commentContent, String url) {
        String query = "DBCC CHECKIDENT (forum_comment, RESEED, 0); DBCC CHECKIDENT (forum_comment, RESEED); insert into forum_comment(userID,post_id,comment_context,comment_date,comment_react, commentImg)"
                + "values(?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setInt(2, postID);
            ps.setString(3, commentContent);
            String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date());
            ps.setString(4, timeStamp);
            ps.setInt(5, 0);
            ps.setString(6, url);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void deleteCommentByID(int commentID) {
        String query = "delete from forum_comment where comment_id = ? DBCC CHECKIDENT (forum_comment, RESEED, 0); DBCC CHECKIDENT (forum_comment, RESEED);";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, commentID);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateCommentByID(int commentID, String context, String URL) {
        String query = "update forum_comment set comment_context = ?, commentImg = ? where comment_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, context);
            ps.setString(2, URL);
            ps.setInt(3, commentID);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updatePostByID(int postID, String title, String context, String URL) {
        String query = "update forum_post set post_title = ?, post_context = ?, post_img = ? where post_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, title);
            ps.setString(2, context);
            ps.setString(3, URL);
            ps.setInt(4, postID);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deletePostByID(int postID) {
        String query = "delete from forum_comment where post_id=? delete from forum_post where post_id=? DBCC CHECKIDENT (forum_post, RESEED, 0); DBCC CHECKIDENT (forum_post, RESEED);";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, postID);
            ps.setInt(2, postID);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Forum> getAllPost() {
        String query = "select * from forum_post";
        List<Forum> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Forum forum = new Forum();
                forum.setPostID(rs.getInt(1));
                forum.setUserID(rs.getInt(2));
                forum.setPostTitle(rs.getString(3));
                forum.setPostContext(rs.getString(4));
                forum.setPostDate(rs.getString(5));
                forum.setPostReact(rs.getInt(6));
                forum.setPostImg(rs.getString(7));
                list.add(forum);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Forum> getAllPostFromUserID(int userID) {
        String query = "select * from forum_post WHERE userID = ?";
        List<Forum> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Forum forum = new Forum();
                forum.setPostID(rs.getInt(1));
                forum.setUserID(rs.getInt(2));
                forum.setPostTitle(rs.getString(3));
                forum.setPostContext(rs.getString(4));
                forum.setPostDate(rs.getString(5));
                forum.setPostReact(rs.getInt(6));
                forum.setPostImg(rs.getString(7));
                list.add(forum);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Comments> findAllCommentsByPostID(int id) {
        String query = "select * from forum_comment WHERE post_id=?";
        List<Comments> cmts = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comments cmt = new Comments();
                cmt.setCommentID(rs.getInt(1));
                cmt.setUserID(rs.getInt(2));
                cmt.setPostID(rs.getInt(3));
                cmt.setCommentContext(rs.getString(4));
                cmt.setCommentDate(rs.getString(5));
                cmt.setCommentReact(rs.getInt(6));
                cmt.setCommentURL(rs.getString(7));
                cmts.add(cmt);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return cmts;
    }

    public Forum findPostByID(int id) {
        String query = "select * from forum_post WHERE post_id=?";
        Forum forum = new Forum();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                forum.setPostID(rs.getInt(1));
                forum.setUserID(rs.getInt(2));
                forum.setPostTitle(rs.getString(3));
                forum.setPostContext(rs.getString(4));
                forum.setPostDate(rs.getString(5));
                forum.setPostReact(rs.getInt(6));
                forum.setPostImg(rs.getString(7));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return forum;
    }

    public static void main(String args[]) {
        List<Forum> forums = new ForumDAO().getAllPost();
        System.out.println(forums.size());
    }
}
