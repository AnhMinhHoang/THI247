<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f2f5;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        position: relative;
    }
    .container {
       
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        padding: 30px;
        max-width: 450px;
        width: 100%;
        text-align: center;
        margin: auto;
    }
    h2 {
        color: #333333;
        margin-bottom: 20px;
    }
    p {
        color: #555555;
        margin: 15px 0;
    }
    .btn {
        display: inline-block;
        padding: 12px 25px;
        margin-top: 20px;
        border-radius: 5px;
        background-color: #007bff;
        color: #ffffff;
        text-decoration: none;
        font-weight: bold;
        transition: background-color 0.3s ease, transform 0.3s ease;
    }
    .btn:hover {
        background-color: #0056b3;
        transform: scale(1.05);
    }
    .back-to-top {
        position: fixed;
        bottom: 20px;
        right: 20px;
        background-color: #007bff;
        color: #ffffff;
        border-radius: 50%;
        padding: 10px;
        text-align: center;
        text-decoration: none;
        transition: background-color 0.3s ease, transform 0.3s ease;
    }
    .back-to-top:hover {
        background-color: #0056b3;
        transform: scale(1.1);
    }
</style>
<body>
    <div class="container">
        <h2>Yêu Cầu Đặt Lại Mật Khẩu Đã Được Gửi</h2>
        <p>Một email với hướng dẫn để đặt lại mật khẩu của bạn đã được gửi tới địa chỉ email của bạn.</p>
        <p>Vui lòng kiểm tra email và làm theo các hướng dẫn.</p>
        <a href="login.jsp" class="btn">Quay lại Đăng nhập</a>
    </div>
    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
</body>
</html>
