<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTP Verification</title>
</head>
<body>
    <h2>OTP Verification</h2>
    <% 
        String verificationError = (String) session.getAttribute("verificationError");
        if (verificationError != null) {
            out.println("<p style='color:red;'>" + verificationError + "</p>");
            session.removeAttribute("verificationError");
        }
    %>
    <form action="VerifyOtpServlet" method="post">
        Enter OTP:
        <input type="text" name="otp" required>
        <button type="submit">Verify OTP</button>
    </form>
</body>
</html>
