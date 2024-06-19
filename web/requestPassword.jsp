
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<!DOCTYPE html>
    <div id="intro" class="bg-image shadow-2-strong">
        <div class="mask d-flex align-items-center h-100">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-5 col-md-8" style="border-bottom: 1px solid;">
                        <form class="bg-white rounded shadow-5-strong p-5" action="RequestPasswordServlet" method="POST">
                            <h5 class="card-title text-center mb-5 fw-light fs-5">Đổi mật khẩu</h5>
                            <input type="hidden" name="oldPassword" value=""/>
                            
                            <!-- Password input -->
                            <div class="form-outline mb-4" data-mdb-input-init>
                                <input type="email" minlength="10" id="email" class="form-control" name="email" placeholder="Nhập email" required/>
                            </div>

                            <!-- 2 column grid layout for inline styling -->
                        <c:if test="${not empty errorMessage}">
                            <p style="color:red">${errorMessage}</p>
                        </c:if>
                        <br>
                        <!-- Submit button -->
                        <button type="submit" class="btn btn-primary btn-block mt-2" style="width: 100%;" data-mdb-ripple-init>
                            Yêu cầu đổi mật khẩu</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>