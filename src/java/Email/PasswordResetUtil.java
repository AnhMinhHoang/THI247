package Email;

import java.sql.*;
import java.util.UUID;

public class PasswordResetUtil {

    private static final String DB_URL = "jdbc:sqlserver://MSI:1433;databaseName=Login_Register;encrypt=true;trustServerCertificate=true";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "tuan";

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    public static void saveTokenToDatabase(String email, String token) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO password_reset_tokens (email, token, expiry_time) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.setString(2, token);
                pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis() + 3600000)); // Token hết hạn sau 1 giờ
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String generateResetLink(String token) {
        return "https://example.com/reset-password?token=" + token;
    }

    public static String getUserIdByToken(String token) {
        String userId = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT user_id FROM password_reset_tokens WHERE token = ? AND expiry_time > NOW()";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, token);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        userId = rs.getString("user_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    public static boolean updatePassword(String userId, String newPassword) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE users SET password = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, newPassword);
                pstmt.setInt(2, Integer.parseInt(userId));
                int affectedRows = pstmt.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteToken(String token) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM password_reset_tokens WHERE token = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, token);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void requestPasswordReset(String email) {
        String token = generateToken();
        saveTokenToDatabase(email, token);
        String resetLink = generateResetLink(token);
        System.out.println("Password reset link: " + resetLink);
    }

   public static void main(String[] args) {
    String to = "sonhuynh22002@gmail.com";
    String token = generateToken();
    String resetLink = generateResetLink(token);
    String subject = "Đổi mật khẩu";
    String message = "Bạn vừa yêu cầu đổi mật khẩu. Vui lòng truy cập vào liên kết sau để tiếp tục quá trình đổi mật khẩu: " + resetLink;
    
    // Gửi email với nội dung chứa liên kết đổi mật khẩu
    IJavaMail emailService = new EmailService();
    emailService.send(to, subject, message);
}


}
