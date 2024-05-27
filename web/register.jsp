<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <div id="intro" class="bg-image shadow-2-strong">
        <div class="mask d-flex align-items-center h-100">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-5 col-md-8" style="border-bottom: 1px solid;">
                        <form class="bg-white rounded shadow-5-strong p-5" action="RegisterServlet" method="POST">
                            <!-- Email input -->
                            <h5 class="card-title text-center mb-5 fw-light fs-5">Register</h5>
                            <div class="form-outline mb-4" data-mdb-input-init>
                                <input type="text" id="form1Example1" class="form-control" name="username" placeholder="Username" required/>

                            </div>
                            <div class="form-outline mb-4" data-mdb-input-init>
                                <input type="email" id="form1Example1" class="form-control" name="email" placeholder="Email" required/>

                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-4" data-mdb-input-init>
                                <input type="password" id="form1Example2" class="form-control" name="password" placeholder="Password" required/>
                            </div>

                            <!-- 2 column grid layout for inline styling -->
                        <c:if test="${not empty errorMessage}">
                            <p style="color:red">${errorMessage}</p>
                        </c:if>
                        <c:if test="${not empty successMessage}">
                            <p style="color:green">${successMessage}</p>
                        </c:if>
                        <div class="col">
                            <!-- Simple link -->
                            <span>Already have account,login <a href="login.jsp">here</a></span>
                        </div>
                        <br>
                        <!-- Submit button -->
                        <button type="submit" class="btn btn-primary btn-block mt-2" style="width: 100%;" data-mdb-ripple-init>
                            Register</button>
                    </form>
                </div>
                <div class="button-group text-center mt-4">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=https://www.googleapis.com/auth/userinfo.profile%20https://www.googleapis.com/auth/userinfo.email&redirect_uri=http://localhost:8080/THI247/LoginGoogleHandler&response_type=code&client_id=1029812003567-92uoqu8gm9iuqafta301erqdqjine7pc.apps.googleusercontent.com&approval_prompt=force">
                        <i class="fa-brands fa-google"></i> SIGN UP WITH GOOGLE
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
