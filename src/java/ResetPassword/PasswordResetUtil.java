package ResetPassword;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PasswordResetUtil extends DBConnection {


    public static void saveTokenToDatabase(int userID, String token, Timestamp expiryTime) {
        String query = "UPDATE Users SET token = ?, expiry_time = ? WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, token);
            pstmt.setTimestamp(2, expiryTime);
            pstmt.setInt(3, userID);
            int affectedRows = pstmt.executeUpdate();
//            System.out.println("Affected rows: " + affectedRows);
//            System.out.println("Token saved successfully for userID: " + userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String generateResetLink(String token) {
        return "http://localhost:8080/THI247/resetpassword.jsp?token=" + token;
    }

    public static String getUserIdByToken(String token) {
        String userId = null;
        String query = "SELECT userID FROM Users WHERE token = ? AND expiry_time > CURRENT_TIMESTAMP";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, token);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getString("userID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    public static String getTokenByUserId(String userId) {
        String token = null;
        String query = "SELECT token FROM Users WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, Integer.parseInt(userId));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    token = rs.getString("token");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return token;
    }

    public static boolean updatePassword(String userId, String newPassword) {
        String query = "UPDATE Users SET password = ? WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, Integer.parseInt(userId));
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteToken(String token) {
        String query = "UPDATE Users SET token = NULL, expiry_time = NULL WHERE token = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, token);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    


//    public static void requestPasswordReset(String email) {
//        String token = EmailSender.generateToken();
//        UserDAO userDAO = new UserDAO();
//        int userId = userDAO.getUserIdByEmail(email); // Assuming UserDAO is correctly implemented
//
//        if (userId != -1) {
//            Timestamp expiryTime = new Timestamp(System.currentTimeMillis() + (10 * 60 * 1000));
//            saveTokenToDatabase(userId, token, expiryTime);
//
//            String resetLink = generateResetLink(token);
//            EmailSender.sendResetEmail(email, resetLink);
//            System.out.println("Password reset link sent to " + email);
//        } else {
//            System.out.println("User with email " + email + " not found.");
//        }
//    }
//    public static void main(String[] args) {
//        String to = "sonhuynh22002@gmail.com";
//        requestPasswordReset(to);
//    }
}

