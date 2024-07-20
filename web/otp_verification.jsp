
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Xác thực OTP</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                padding: 20px;
            }
            .otp-form {
                max-width: 400px;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .otp-form h2 {
                color: #333;
            }
            .otp-form p {
                margin-bottom: 10px;
            }
            .otp-form input[type="text"] {
                width: calc(100% - 80px);
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
            }
            .otp-form .button-container {
                display: flex;
                justify-content: space-between; /* Căn hai nút sang hai bên */
                align-items: center; /* Căn các phần tử theo trục dọc */
                margin-top: 10px; /* Khoảng cách từ form nhập OTP lên nút */
            }
            .otp-form button[type="submit"] {
                color:#000;
                background-color:#06BBCC;
                border-color:#06BBCC
               
            }
            .otp-form button[type="submit"]:hover {
                background-color: #45a049;
            }
            .otp-form button[type="submit"]:focus {
                border: 1px solid #45a049;
            }
            .error-message {
                color: red;
            }
            .success-message {
                color: green;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="otp-form" style="margin-top: 50px">
                <h2 class="text-primary">Xác nhận mã OTP</h2>
            <% 
                String verificationError = (String) session.getAttribute("verificationError");
                if (verificationError != null) {
                    out.println("<p class='error-message'>" + verificationError + "</p>");
                    session.removeAttribute("verificationError");
                }
            
                String successMessage = (String) session.getAttribute("successMessage");
                if (successMessage != null) {
                    out.println("<p class='success-message'>" + successMessage + "</p>");
                    session.removeAttribute("successMessage");
                }
            
                Long otpExpiryTime = (Long) session.getAttribute("otpExpiryTime");
                if (otpExpiryTime != null && System.currentTimeMillis() > otpExpiryTime) {
                    out.println("<p class='error-message'>OTP has expired. Please request a new OTP.</p>");
                    session.removeAttribute("otpExpiryTime");
                }
            %>
            <form action="VerifyOtpServlet" method="post">
                Nhập OTP:
                <input type="text" name="otp" required>
                
                <div class="button-container">
                    <button class="btn btn-primary" type="submit">Xác minh OTP</button>
                    <button class="btn btn-primary" style="width: 133px" type="submit" class="resend" formnovalidate>
                        <a href="ResendOtpServlet" style="text-decoration: none; color: black">Gửi lại</a>
                    </button>
                </div>
            </form>
           
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
