package OTP;

import DAO.DBConnection;
import DAO.UserDAO;
import Email.EmailSender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

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
    public static void saveOtpToDatabase(int userID, String otp, Timestamp expiryTime, boolean otp_verified) {
        String query = "UPDATE Users SET otp_code = ?, expiry_time = ?, otp_verified = ? WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, otp);
            pstmt.setTimestamp(2, expiryTime);
             pstmt.setBoolean(3, otp_verified);
            pstmt.setInt(4, userID);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);
            System.out.println("OTP saved successfully for userID: " + userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Phương thức kiểm tra và xác minh OTP
    public static boolean verifyOtp(String email, String otp) {
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.getUserIdByEmail(email);

        if (userId != -1) {
            String storedOtp = getStoredOtp(userId);
            Timestamp expiryTime = getOtpExpiryTime(userId);

            if (storedOtp != null && storedOtp.equals(otp) && !isOtpExpired(expiryTime)) {
                // Xác minh thành công, cập nhật trạng thái otp_verified = true và xóa OTP khỏi cơ sở dữ liệu
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

    // Phương thức lấy thời gian hết hạn của OTP từ cơ sở dữ liệu
    public static Timestamp getOtpExpiryTime(int userId) {
        Timestamp expiryTime = null;
        String query = "SELECT expiry_time FROM Users WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                expiryTime = rs.getTimestamp("expiry_time");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expiryTime;
    }

    // Phương thức kiểm tra xem OTP đã hết hạn chưa
    public static boolean isOtpExpired(Timestamp expiryTime) {
        if (expiryTime == null) {
            return true;
        }
        long now = System.currentTimeMillis();
        long expiryMillis = expiryTime.getTime();
        return now > expiryMillis;
    }

    // Phương thức cập nhật trạng thái otp_verified trong cơ sở dữ liệu
    private static void updateOtpVerificationStatus(int userId, boolean otpVerified) {
        String query = "UPDATE Users SET otp_verified = ? WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setBoolean(1, otpVerified);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức xóa OTP khỏi cơ sở dữ liệu sau khi đã xác minh thành công
    private static void deleteOtpFromDatabase(int userId) {
        String query = "UPDATE Users SET otp_code = NULL, expiry_time = NULL WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public static void requestOtpReset(String email) {
        String otp = generateOTP();
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.getUserIdByEmail(email); // Assuming UserDAO is correctly implemented

        if (userId != -1) {
            Timestamp expiryTime = new Timestamp(System.currentTimeMillis() + (5 * 60 * 1000));
            saveOtpToDatabase(userId, otp, expiryTime, false);

            
            EmailSender.sendOtpToEmail(email, otp);
            System.out.println("Password reset link sent to " + otp);
        } else {
            System.out.println("User with email " + otp + " not found.");
        }
    }

     public static void main(String[] args) {
        String to = "sonhuynh22002@gmail.com";
        requestOtpReset(to);
    }
}
