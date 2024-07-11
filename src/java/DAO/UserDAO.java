package DAO;

import static DAO.DBConnection.getConnection;
import model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Exam;
import model.Payment;

public class UserDAO extends DBConnection {

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
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
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
                    Boolean Ban = rs.getBoolean("is_banned");

                    Users us = new Users(iD, username, fullname, passwords, emails, role, avatarURL, balance, phone, address, dob, Ban);
                    return us;
                }
            }
        } catch (Exception e) {
            System.out.println("SDASD");
        }
        return null;
    }

    public Users verifiedByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ? AND otp_verified = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setBoolean(2, true); // Only select if otp_verified is true

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Users users = new Users();
                    users.setUserID(rs.getInt("userID"));
                    users.setEmail(rs.getString("email"));
                    users.setOtp_verified(true);
                    return users;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int getUserIdByEmail(String email) {
        int userId = -1; // Default value if user_id is not found

        String query = "SELECT userID FROM Users WHERE email = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
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
    
    public List<Users> getAllUsers() {
        String query = "SELECT * FROM Users where roles > 1";
        List<Users> users = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
                    Boolean Ban = rs.getBoolean("is_banned");

                    Users us = new Users(iD, usernames, fullname, passwords, emails, role, avatarURL, balance, phone, address, dob, Ban);
                    users.add(us);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return users;
    }
    
    public List<Users> getAllUsersType(int roles) {
        String query = "SELECT * FROM Users where roles = ?";
        List<Users> users = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, roles);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
                    Boolean Ban = rs.getBoolean("is_banned");

                    Users us = new Users(iD, usernames, fullname, 
                            passwords, emails, role, avatarURL, 
                            balance, phone, address, dob, Ban);
                    users.add(us);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return users;
    }

    public Users findByUserName(String username) {
        String query = "SELECT * FROM Users WHERE username=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
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
                    Boolean Ban = rs.getBoolean("is_banned");

                    Users us = new Users(iD, usernames, fullname, passwords, emails, role, avatarURL, balance, phone, address, dob, Ban);
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
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
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
                    Boolean Ban = rs.getBoolean("is_banned");

                    Users us = new Users(iD, usernames, fullname, passwords, emails, role, avatarURL, balance, phone, address, dob, Ban);
                    return us;
                }
            }
        } catch (Exception e) {
            System.out.println("SDASD");
        }
        return null;
    }

    public void updateInfo(String email, String username, String fullname, String phone, String address, String dob) {
        String query = "UPDATE Users SET username = ? , fullname = ?, phone = ?, localaddress = ?, dob = ? WHERE email=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, fullname);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, dob);
            ps.setString(6, email);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println("SDASD");
        }
    }
    
    public void updateRole(int userID, int role){
        String query = "UPDATE Users SET roles = ? WHERE userID=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, role);
            ps.setInt(2, userID);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println("SDASD");
        }
    }

    public void changePassWordByEmail(String email, String password) {
        String query = "UPDATE Users SET password=? WHERE email=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, password);
            ps.setString(2, email);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println("SDASD");
        }
    }

    public void updateAvatar(String url, String email) {
        String query = "UPDATE Users SET avatar=? WHERE email=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, url);
            ps.setString(2, email);
            System.out.println(query);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
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
            String sql = "DBCC CHECKIDENT (Users, RESEED, 0); DBCC CHECKIDENT (Users, RESEED); INSERT INTO Users (username, password, email, roles, avatar, balance, is_banned) VALUES (?, ?, ?, ?, ?, 0, 0)";

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
    
    public void createPayment(int userID, String paymentCode, String bank, int amount, String paymentDate){
        String query = "DBCC CHECKIDENT (Payment, RESEED, 0); DBCC CHECKIDENT (Payment, RESEED); insert into Payment(userID, payment_code, bank, amount, payment_date)"
                + "values(?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setString(2, paymentCode);
            ps.setString(3, bank);
            ps.setInt(4, amount);
            ps.setString(5, paymentDate);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    
    public void addMoneyToBalance(int amount, int userID){
        String query = "Update Users set balance = balance + ? where userID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, amount);
            ps.setInt(2, userID);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    
    public void subtractMoneyToBalance(int amount, int userID){
        String query = "Update Users set balance = balance - ? where userID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, amount);
            ps.setInt(2, userID);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    
    public List<Payment> getAllPaymentByID(int userID) {
        String query = "select * from Payment where userID = ?";
        List<Payment> paymentList = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentID(rs.getInt(1));
                payment.setUserID(rs.getInt(2));
                payment.setAmount(rs.getInt(3));
                payment.setPaymentDate(rs.getString(4));
                payment.setPaymentCode(rs.getString(5));
                payment.setBank(rs.getString(6));
                paymentList.add(payment);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return paymentList;
    }
    
    public List<Payment> getAllPayment() {
        String query = "select * from Payment";
        List<Payment> paymentList = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentID(rs.getInt(1));
                payment.setUserID(rs.getInt(2));
                payment.setAmount(rs.getInt(3));
                payment.setPaymentDate(rs.getString(4));
                payment.setPaymentCode(rs.getString(5));
                payment.setBank(rs.getString(6));
                paymentList.add(payment);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return paymentList;
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
    public String getUserNameByUserId(int userId) {
    String username = null;
    String query = "SELECT username FROM Users WHERE userID = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            username = rs.getString("username");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return username;
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
        // Địa chỉ email cần tìm userId
        int a = 10;
        double b = a * 0.7;
        System.out.println(b);
    }
}
