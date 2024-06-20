/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Email;

import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import javax.mail.Authenticator;
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
  public static void sendOTP(String toEmail, String otp) {
        String fromEmail = "tuantpde170492@fpt.edu.vn"; // Thay thế bằng email của bạn
        String password = "xelm aagm oppl exkg"; // Thay thế bằng mật khẩu email của bạn

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toEmail));
            message.setSubject("OTP Verification");
            message.setText("Your OTP for registration is: " + otp);

            Transport.send(message);

            System.out.println("Email sent successfully!");

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
}
