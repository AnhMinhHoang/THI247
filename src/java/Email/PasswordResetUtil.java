package Email;

import DAO.DBConnection;
import DAO.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.UUID;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PasswordResetUtil extends DBConnection {

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

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

    public static void requestPasswordReset(String email) {
        String token = generateToken();
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.getUserIdByEmail(email); // Assuming UserDAO is correctly implemented

        if (userId != -1) {
            Timestamp expiryTime = new Timestamp(System.currentTimeMillis() + (10 * 60 * 1000));
            saveTokenToDatabase(userId, token, expiryTime);

            String resetLink = generateResetLink(token);
            sendResetEmail(email, resetLink);
            System.out.println("Password reset link sent to " + email);
        } else {
            System.out.println("User with email " + email + " not found.");
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

    public static void sendResetEmail(String recipientEmail, String resetLink) {
        final String username = "tuantpde170492@fpt.edu.vn"; // Thay bằng email của bạn
        final String password = "xelm aagm oppl exkg"; // Thay bằng mật khẩu của bạn

        // Cấu hình thuộc tính của mail server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo một phiên làm việc
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject("Password Reset Request");
            message.setText("Please click the link below to reset your password:\n\n" + resetLink);

            // Gửi email
            Transport.send(message);

            System.out.println("Email sent to " + recipientEmail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        String to = "sonhuynh22002@gmail.com";
        requestPasswordReset(to);
    }
}