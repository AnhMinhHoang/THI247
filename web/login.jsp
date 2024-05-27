<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<style>
        input {
            height: 50px;
        }
</style>
    <div id="intro" class="bg-image shadow-2-strong">
        <div class="mask d-flex align-items-center h-100">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-5 col-md-8" style="border-bottom: 1px solid">
   
                        <form class="bg-white rounded shadow-5-strong p-5" action="login" method="POST">
                            <!-- Email input -->
                            <h5 class="card-title text-center mb-5 fw-light fs-5">Login</h5>
                            <div class="form-outline mb-4" data-mdb-input-init>
                                <input type="email" id="form1Example1" class="form-control" placeholder="Email" name="email" />
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-4" data-mdb-input-init>
                                <input type="password" id="form1Example2" class="form-control" placeholder="Password" name="password"/>
                            </div>
                            <c:if test="${not empty errorMessage}">
                                <p style="color:red">${errorMessage}</p>
                            </c:if>
                            <!-- 2 column grid layout for inline styling -->
                            <div class="row mb-4">
                                <div class="col d-flex">
                                    <!-- Checkbox -->
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="form1Example3" />
                                        <label class="form-check-label" for="form1Example3">
                                            Remember password
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <!-- Simple link -->
                                <span>You don't have any account yet,register 
                                    <a href="register.jsp">here</a></span>
                            </div>
                            <br>
                            <!-- Submit button -->
                            <button type="submit" class="btn btn-primary btn-block" style="width: 100%"
                                data-mdb-ripple-init>
                                Sign in
                            </button>
                        </form>
                    </div>
                    <div class="button-group text-center mt-4">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=https://www.googleapis.com/auth/userinfo.profile%20https://www.googleapis.com/auth/userinfo.email&redirect_uri=http://localhost:8080/THI247/LoginGoogleHandler&response_type=code&client_id=1029812003567-92uoqu8gm9iuqafta301erqdqjine7pc.apps.googleusercontent.com&approval_prompt=force">
                            <i class="fa-brands fa-google"></i> SIGN IN WITH GOOGLE
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
