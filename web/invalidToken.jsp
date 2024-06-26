
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yêu Cầu Đặt Lại Mật Khẩu Đã Được Gửi</title>
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
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
      <div class="container">
        <h2>Token không hợp lệ hoặc đã hết hạn</h2>
        <p>Vui lòng thử lại yêu cầu đổi mật khẩu hoặc liên hệ với quản trị viên.</p>
        <a href="requestPassword.jsp" class="btn">Quay lại yêu cầu đổi mật khẩu</a>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
    
    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

</body>
</html>
