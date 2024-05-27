/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Email;

/**
 *
 * @author Dai Nhan
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailService implements IJavaMail {

   public boolean send(String to, String subject, String messageContent) {
    // Get properties object
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", EmailProperty.HOST_NAME);
    props.put("mail.smtp.socketFactory.port", EmailProperty.SSL_PORT);
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.port", EmailProperty.SSL_PORT);

    // get Session
    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(EmailProperty.APP_EMAIL, EmailProperty.APP_PASSWORD);
        }
    });

    // compose message
    try {
        MimeMessage message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject); // Đặt tiêu đề của email
        message.setText(messageContent); // Đặt nội dung của email

        // send message
        Transport.send(message);
        return true;
    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}
}