/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Email;

//import Schedule.TimetableDAO;
//import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
//import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author sonhu
 */
public class EmailSender {
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

   public static String generateOTP() {
        // Tạo mã OTP ngẫu nhiên
        Random random = new Random();
        int otpLength = 6; // Độ dài của mã OTP
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10)); // Tạo số ngẫu nhiên từ 0 đến 9
        }

        return otp.toString();
    }
    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
  public static boolean sendPlannerEmail(String toEmail, String emailContent) {
    final String username = "tuantpde170492@fpt.edu.vn"; // Replace with your email
    final String password = "xelm aagm oppl exkg"; // Replace with your password or app-specific password

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Planner Email");

        // Set email content
        message.setText(emailContent);

        Transport.send(message);

        System.out.println("Email sent successfully");
        return true;
    } catch (MessagingException e) {
        e.printStackTrace();
        return false;
    }
}

}
