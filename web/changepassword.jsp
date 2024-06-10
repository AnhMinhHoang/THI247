<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<%
if(session.getAttribute("currentUser") != null){
Users user = (Users)session.getAttribute("currentUser");
%>
    <div id="intro" class="bg-image shadow-2-strong">
        <div class="mask d-flex align-items-center h-100">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-5 col-md-8" style="border-bottom: 1px solid;">
                        <form class="bg-white rounded shadow-5-strong p-5" action="ChangePassword" method="POST">
                            <h5 class="card-title text-center mb-5 fw-light fs-5">Đổi mật khẩu</h5>
                            <%
                            if(!user.getPassword().isBlank()){
                            %>
                            <div class="form-outline mb-4" data-mdb-input-init>
                                <input type="password" minlength="10" id="form1Example1" class="form-control" name="oldPassword" placeholder="Mật khẩu cũ" required/>
                            </div>
                            <%
                                }
                            else{
                            %>
                            <input type="hidden" name="oldPassword" value=""/>
                            <%
                                }
                            %>
                            <div class="form-outline mb-4" data-mdb-input-init>
                                <input type="password" minlength="8" id="form1Example1" class="form-control" name="newPassword" placeholder="Mật khẩu mới" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Mật khẩu phải có ít nhất 1 chữ cái hoa, 1 chữ cái thường, 1 số và ít nhất là 8 ký tự!" required/>
                            </div>
                            <!-- Password input -->
                            <div class="form-outline mb-4" data-mdb-input-init>
                                <input type="password" minlength="10" id="form1Example2" class="form-control" name="confirmPassword" placeholder="Xác nhận mật khẩu" required/>
                            </div>

                            <!-- 2 column grid layout for inline styling -->
                        <c:if test="${not empty errorMessage}">
                            <p style="color:red">${errorMessage}</p>
                        </c:if>
                        <br>
                        <!-- Submit button -->
                        <button type="submit" class="btn btn-primary btn-block mt-2" style="width: 100%;" data-mdb-ripple-init>
                            Đổi mật khẩu</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
                            <%
                                }
                            %>
<jsp:include page="footer.jsp"></jsp:include>
<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>