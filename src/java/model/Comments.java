/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package model;

/**
 *
 * @author GoldCandy
 */
public class Comments {
    private int commentID;
    private int userID;
    private int postID;
    private String commentContext;
    private String commentDate;
    private int commentReact;
    private String commentURL;

    public Comments() {
    }

    public Comments(int commentID, int userID, int postID, String commentContext, String commentDate, int commentReact, String commentURL) {
        this.commentID = commentID;
        this.userID = userID;
        this.postID = postID;
        this.commentContext = commentContext;
        this.commentDate = commentDate;
        this.commentReact = commentReact;
        this.commentURL = commentURL;
    }

    public int getCommentReact() {
        return commentReact;
    }

    public void setCommentReact(int commentReact) {
        this.commentReact = commentReact;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getCommentContext() {
        return commentContext;
    }

    public void setCommentContext(String commentContext) {
        this.commentContext = commentContext;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentURL() {
        return commentURL;
    }

    public void setCommentURL(String commentURL) {
        this.commentURL = commentURL;
    }

    @Override
    public String toString() {
        return "Comments{" + "commentID=" + commentID + ", userID=" + userID + ", postID=" + postID + ", commentContext=" + commentContext + ", commentDate=" + commentDate + ", commentReact=" + commentReact + ", commentURL=" + commentURL + '}';
    }
}
