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
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DBConnection{

    private static final String LOGIN_QUERY = "SELECT userID, roles FROM Users WHERE email=? AND password=? AND otp_verified > 0";
    private static final String USER_TYPE_QUERY = "SELECT roles FROM Users WHERE email=?";
    
    public String checkLogin(String email, String password) {
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
                return "Ok";
            } else {
                System.out.println("Login failed: Invalid username or password");
            }
        } catch (SQLException e) {
            System.err.println("Login failed due to database error: " + e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return null;
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
                    String phone = rs.getString("phone");
                    String address = rs.getString("localaddress");
                    String dob = rs.getString("dob");
                   
            
                    Users us = new Users(iD, username, fullname, passwords, emails, role, avatarURL, balance, phone, address, dob);
                    return us;
                }
            }
        } catch (Exception e) {
            System.out.println("SDASD");
        }
        return null;
    }
      public Users verifiedByEmail(String email) {
        Users user = null;
        String query = "SELECT * FROM users WHERE email = ? AND otp_verified = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, email);
            stmt.setBoolean(2, true); // Only select if otp_verified is true
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new Users();
                    user.setUserID(rs.getInt("user_id"));
                    user.setEmail(rs.getString("email"));
                    user.setOtp_verified(rs.getBoolean("otp_verified"));
                    // Set other properties as needed
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }

    public int getUserIdByEmail(String email) {
    int userId = -1; // Default value if user_id is not found

    String query = "SELECT userID FROM Users WHERE email = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, email);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                userId = rs.getInt("userID");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return userId;
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
                    String phone = rs.getString("phone");
                    String address = rs.getString("localaddress");
                    String dob = rs.getString("dob");
                
                    Users us = new Users(iD, usernames, fullname, passwords, emails, role, avatarURL, balance, phone, address, dob);
                    return us;
                }
            }
        } catch (Exception e) {
            System.out.println("SDASD");
        }
        return null;
    }
    
    public Users findByUserID(int userID) {
        String query = "SELECT * FROM Users WHERE userID=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);

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
            
                    String phone = rs.getString("phone");
                    String address = rs.getString("localaddress");
                    String dob = rs.getString("dob");
                   
                    
                    Users us = new Users(iD, usernames, fullname, passwords, emails, role, avatarURL, balance, phone, address, dob);
                    return us;
                }
            }
        } catch (Exception e) {
            System.out.println("SDASD");
        }
        return null;
    }
    
    public void updateInfo(String email, String username, String fullname, String phone, String address, String dob){
        String query = "UPDATE Users SET username = ? , fullname = ?, phone = ?, localaddress = ?, dob = ? WHERE email=?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, fullname);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, dob);
            ps.setString(6, email);
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
    
    public void changePassWordByEmail(String email, String password){
        String query = "UPDATE Users SET password=? WHERE email=?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, password);
            ps.setString(2, email);
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
            String sql = "DBCC CHECKIDENT (Users, RESEED, 0); DBCC CHECKIDENT (Users, RESEED); INSERT INTO Users (username, password, email, roles, avatar) VALUES (?, ?, ?, ?, ?)";

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
public String getEmailByUserId(int userId) {
        String email = null;
        String query = "SELECT email FROM Users WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    
    public List<Integer> getAllUserIds() {
        List<Integer> userIds = new ArrayList<>();
        String query = "SELECT userID FROM Users";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                userIds.add(rs.getInt("userID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIds;
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
//       public static void main(String[] args) {
//        // Địa chỉ email cần tìm userId
//        new UserDAO().checkLogin("student1@gmail.com", "student123");
//    }
    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAO();
//
//        // Kiểm tra phương thức checkLogin
//        String loginResult = userDAO.checkLogin("student1@gmail.com", "student123");
//        System.out.println("Login result: " + loginResult);
//
//        // Kiểm tra phương thức findByEmail
//        Users userByEmail = userDAO.findByEmail("student1@gmail.com");
//        if (userByEmail != null) {
//            System.out.println("User found by email: " + userByEmail.getUsername());
//        } else {
//            System.out.println("User not found by email.");
//        }
//
//        // Kiểm tra phương thức getUserIdByEmail
//        int userId = userDAO.getUserIdByEmail("student1@gmail.com");
//        System.out.println("User ID: " + userId);
//
//        // Kiểm tra phương thức findByUserID
//        Users userById = userDAO.findByUserID(userId);
//        if (userById != null) {
//            System.out.println("User found by ID: " + userById.getUsername());
//        } else {
//            System.out.println("User not found by ID.");
//        }
//
//        // Kiểm tra phương thức registerUser
//        boolean registrationResult = userDAO.registerUser("newuser", "password123", "newuser@example.com", false);
//        System.out.println("User registration: " + (registrationResult ? "successful" : "failed"));
//
//        // Kiểm tra phương thức getUserType
//        int userType = userDAO.getUserType("newuser@example.com");
//        System.out.println("User type: " + userType);
//    }
}
}