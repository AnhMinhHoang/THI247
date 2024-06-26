package Email;

import DAO.DBConnection;
import DAO.UserDAO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class OTP extends DBConnection {

    // Phương thức tạo mã OTP ngẫu nhiên
    public static String generateOTP() {
        Random random = new Random();
        int otpLength = 6;
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }

    // Phương thức lưu mã OTP vào cơ sở dữ liệu
    public static void saveOtpToDatabase(int userID, String otp_code, boolean otp_verified) {
        String query = "UPDATE Users SET otp_code = ?, otp_verified = ? WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, otp_code);
            pstmt.setBoolean(2, otp_verified);
            pstmt.setInt(3, userID);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);
            System.out.println("OTP saved successfully for userID: " + userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức yêu cầu và gửi OTP qua email
    public static void requestAndSendOtp(String email) {
        String otp_code = generateOTP();
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.getUserIdByEmail(email);

        if (userId != -1) {
            // Lưu mã OTP vào cơ sở dữ liệu
            saveOtpToDatabase(userId, otp_code, false);

            // Gửi email chứa mã OTP đến người dùng
            sendOtpToEmail(email, otp_code);

            System.out.println("OTP sent to " + email);
        } else {
            System.out.println("User with email " + email + " not found.");
        }
    }

    // Phương thức gửi email chứa mã OTP
    public static void sendOtpToEmail(String recipientEmail, String otp_code) {
        final String username = "tuantpde170492@fpt.edu.vn"; // Thay bằng email của bạn
        final String password = "xelm aagm oppl exkg"; // Thay bằng mật khẩu của bạn

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject("OTP Verification");
            message.setText("Your OTP for verification is: " + otp_code);

            Transport.send(message);

            System.out.println("Email sent to " + recipientEmail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // Phương thức xác minh OTP
    public static boolean verifyOtp(String email, String otp_code) {
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.getUserIdByEmail(email);

        if (userId != -1) {
            String storedOtp = getStoredOtp(userId);

            if (storedOtp != null && storedOtp.equals(otp_code)) {
                // Xác minh thành công, cập nhật trạng thái otp_verified = true và xóa mã OTP khỏi cơ sở dữ liệu
                updateOtpVerificationStatus(userId, true);
                deleteOtpFromDatabase(userId);
                return true;
            }
        }
        return false;
    }

    // Phương thức lấy mã OTP đã lưu trong cơ sở dữ liệu
    private static String getStoredOtp(int userId) {
        String otp = null;
        String query = "SELECT otp_code FROM Users WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                otp = rs.getString("otp_code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otp;
    }

    // Phương thức cập nhật trạng thái otp_verified trong cơ sở dữ liệu
    private static void updateOtpVerificationStatus(int userId, boolean otp_verified) {
        String query = "UPDATE Users SET otp_verified = ? WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setBoolean(1, otp_verified);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức xóa mã OTP từ cơ sở dữ liệu sau khi đã xác minh thành công
    private static void deleteOtpFromDatabase(int userId) {
        String query = "UPDATE Users SET otp_code = NULL WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public static void main(String[] args) {
        // Gửi OTP đến email để test
        String to = "sonhuynh22002@gmail.com";
        OTP.requestAndSendOtp(to);

        // Nhập OTP từ bàn phím hoặc set trực tiếp
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter OTP: ");
        String otp = scanner.nextLine();

        // Thay email ở đây bằng email bạn dùng để nhận OTP
        String email = "sonhuynh22002@gmail.com";
        
        // Thực hiện xác minh OTP
        boolean verified = OTP.verifyOtp(email, otp);
        
        if (verified) {
            System.out.println("OTP verified successfully.");
        } else {
            System.out.println("OTP verification failed.");
        }

        scanner.close();
    }
}
