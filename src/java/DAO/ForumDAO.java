/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.Date;  
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Forum;
import model.Users;

/**
 *
 * @author GoldCandy
 */
public class ForumDAO extends DBConnection{

    public void createNewPost(int userID, String postTitle, String postContext, String postIMG){
        String query = "insert into forum_post(userID,post_title,post_context,post_date,post_img)"
                + "values(?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setString(2, postTitle);
            ps.setString(3, postContext);
            String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date());
            ps.setString(4, timeStamp);
            ps.setString(5, postIMG);
            try{
                ps.executeUpdate();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }catch(Exception err){
            System.out.println(err);
        }
    }
    
    public List<Forum> getAllPost(){
        String query = "select * from forum_post";
        List<Forum> list = new ArrayList<>();
        try(Connection con = getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Forum forum = new Forum();
                forum.setPostID(rs.getInt(1));
                forum.setUserID(rs.getInt(2));
                forum.setPostTitle(rs.getString(3));
                forum.setPostContext(rs.getString(4));
                forum.setPostDate(rs.getString(5));
                forum.setPostApproved(rs.getBoolean(6));
                forum.setPostReact(rs.getInt(7));
                forum.setPostImg(rs.getString(8));
                list.add(forum);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
    
    public static void main(String args[]) {
        List<Forum> forums = new ForumDAO().getAllPost();
        for (Forum forum : forums) {
            Users user = new UserDAO().findByUserID(forum.getUserID());
            if(forum.getPostContext().length() > 200){ 
                        String str = forum.getPostContext().substring(1, 200) + "...";
                    }
            else {
                String str = forum.getPostContext();
            }
        }
    }
}
