<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đổi mật khẩu</title>
</head>
<body>
    <!-- Header -->
    <jsp:include page="header.jsp"></jsp:include>

    <!-- Main Content -->
    <div id="intro" class="bg-image shadow-2-strong">
        <div class="mask d-flex align-items-center h-100">
            <div class="container">
                <!-- Hiển thị thông báo nếu có -->
                <c:if test="${not empty param.message}">
                    <div class="alert alert-info" role="alert">
                        ${param.message}
                    </div>
                </c:if>

                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-5 col-md-8">
                            <form class="bg-white rounded shadow p-5" action="PasswordResetServlet" method="POST" onsubmit="return validatePassword();">
                                <input type="hidden" name="token" value="${param.token}" />
                                <h5 class="card-title text-center mb-5">Đổi mật khẩu</h5>
                                <div class="form-group">
                                    <label for="newPassword">Mật khẩu mới</label>
                                    <input type="password" minlength="8" id="newPassword" class="form-control" name="newPassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Mật khẩu phải có ít nhất 1 chữ cái hoa, 1 chữ cái thường, 1 số và ít nhất là 8 ký tự!" required/>
                                </div>
                                <div class="form-group">
                                    <label for="confirmPassword">Xác nhận mật khẩu</label>
                                    <input type="password" minlength="8" id="confirmPassword" class="form-control" name="confirmPassword" required/>
                                </div>

                                <c:if test="${not empty errorMessage}">
                                    <p class="text-danger">${errorMessage}</p>
                                </c:if>

                                <button type="submit" class="btn btn-primary btn-block mt-3">Đổi mật khẩu</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp"></jsp:include>

    <!-- Custom JavaScript for password matching -->
    <script>
        function validatePassword() {
            var newPassword = document.getElementById("newPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            if (newPassword !== confirmPassword) {
                alert("Mật khẩu xác nhận không khớp!");
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
