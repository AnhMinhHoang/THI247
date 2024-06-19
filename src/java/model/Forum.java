/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package model;

/**
 *
 * @author GoldCandy
 */
public class Forum {
    private int postID;
    private int userID;
    private String postTitle;
    private String postContext;
    private String postDate;
    private int postReact;
    private String postImg;

    public Forum() {
    }

    public Forum(int postID, int userID, String postTitle, String postContext, String postDate, int postReact, String postImg) {
        this.postID = postID;
        this.userID = userID;
        this.postTitle = postTitle;
        this.postContext = postContext;
        this.postDate = postDate;
        this.postReact = postReact;
        this.postImg = postImg;
    }

    public String getPostImg() {
        return postImg;
    }

    public void setPostImg(String postImg) {
        this.postImg = postImg;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContext() {
        return postContext;
    }

    public void setPostContext(String postContext) {
        this.postContext = postContext;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public int getPostReact() {
        return postReact;
    }

    public void setPostReact(int postReact) {
        this.postReact = postReact;
    }

    @Override
    public String toString() {
        return "Forum{" + "postID=" + postID + ", userID=" + userID + ", postTitle=" + postTitle + ", postContext=" + postContext + ", postDate=" + postDate + ", postReact=" + postReact + ", postImg=" + postImg + '}';
    }
}
