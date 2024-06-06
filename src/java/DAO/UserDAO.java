/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN        
 */
import java.sql.*;

public class UserDAO extends DBConnection{

    private static final String LOGIN_QUERY = "SELECT userID, roles FROM Users WHERE email=? AND password=?";
    private static final String USER_TYPE_QUERY = "SELECT roles FROM Users WHERE email=?";

    public boolean checkLogin(String email, String password) {
        boolean loggedIn = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(LOGIN_QUERY);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Đăng nhập thành công
                loggedIn = true;
            } else {
                // Đăng nhập thất bại
                System.out.println("Login failed: Invalid username or password");
            }
        } catch (SQLException e) {
            System.err.println("Login failed due to database error: " + e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return loggedIn;
    }
    
    public Users findByEmail(String email) {
        String query = "SELECT * FROM Users WHERE email=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int iD = rs.getInt("userID");
                    String username = rs.getString("username");
                    String fullname = rs.getString("fullname");
                    String emails = rs.getString("email");
                    int role = rs.getInt("roles");
                    String avatarURL = rs.getString("avatar");
                    int balance = rs.getInt("balance");
                    String passwords = rs.getString("password");
            
                    Users us = new Users(iD, username, fullname, passwords, emails, role, avatarURL, balance);
                    return us;
                }
            }
        } catch (Exception e) {
            System.out.println("SDASD");
        }
        return null;
    }
    
    public Users findByUserName(String username) {
        String query = "SELECT * FROM Users WHERE username=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int iD = rs.getInt("userID");
                    String usernames = rs.getString("username");
                    String fullname = rs.getString("fullname");
                    String emails = rs.getString("email");
                    int role = rs.getInt("roles");
                    String avatarURL = rs.getString("avatar");
                    int balance = rs.getInt("balance");
                    String passwords = rs.getString("password");
            
                    Users us = new Users(iD, usernames, fullname, passwords, emails, role, avatarURL, balance);
                    return us;
                }
            }
        } catch (Exception e) {
            System.out.println("SDASD");
        }
        return null;
    }
    
    public void updateInfo(String email, String username, String fullname, String password){
        String query = "UPDATE Users SET username = ? , fullname = ? , password = ? WHERE email=?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, fullname);
            ps.setString(3, password);
            ps.setString(4, email);
            System.out.println(query);
            try{
                ps.executeUpdate();
            }
            catch(Exception e){
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println("SDASD");
        }
    }
    
    public void updateAvatar(String url, String email){
        String query = "UPDATE Users SET avatar=? WHERE email=?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, url);
            ps.setString(2, email);
            System.out.println(query);
            try{
                ps.executeUpdate();
            }
            catch(Exception e){
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println("SDASD");
        }
    }

    public boolean registerUser(String username, String password, String email, boolean isLecturer) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO users (username, password, email, roles, avatar) VALUES (?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);

            // Xác định user_type dựa trên isAdmin
            int role = isLecturer ? 2 : 3; // Nếu là lecturer thì user_type = 2, ngược lại là user_type = 1
            stmt.setInt(4, role);
            stmt.setString(5, "img/default.png");

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User registered successfully");
                return true;
            } else {
                System.err.println("User registration failed: No rows affected");
            }
        } catch (SQLException e) {
            System.err.println("User registration failed due to database error: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public int getUserType(String email) {
        int role = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(USER_TYPE_QUERY);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                role = rs.getInt("roles");
            }
        } catch (SQLException e) {
            System.err.println("Failed to get user type: " + e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return role;
    }

    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }

    /* public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        // Tạo tài khoản user
        if (userDAO.registerUser("tuan1", "tuan1", "newuser@example.com", false)) {
            System.out.println("User registration successful");
        } else {
            System.out.println("User registration failed");
        }

        // Xác định quyền của người dùng (ví dụ: true nếu là admin, false nếu là user)
        boolean isAdmin = true; // Ví dụ: Đây là quyền admin

        // Nếu người dùng là admin, có thể đăng ký tài khoản admin
        if (isAdmin) {
            if (userDAO.registerUser("admin1", "admin1", "newadmin@example.com", true)) {
                System.out.println("Admin registration successful");
            } else {
                System.out.println("Admin registration failed");
            }
        } else {
            System.out.println("Only admin can register new admins");
        }
    }*/
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        System.out.println(userDAO.checkLogin("anhminhnamly1@gmail.com", "minhvn2004"));
    }
}
