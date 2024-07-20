<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %>
<jsp:include page="header.jsp"></jsp:include>

    <script>
        var container = document.getElementById("tagID");
        var tag = container.getElementsByClassName("tag");
        var current = container.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
    </script>

    <style>
        input {
            height: 50px;
        }
    </style>
    <!-- Breadcrumb -->
    <!-- <nav aria-label="breadcrumb" class="main-breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
        <li class="breadcrumb-item"><a href="javascript:void(0)">User</a></li>
        <li class="breadcrumb-item active" aria-current="page">User Profile</li>
      </ol>
    </nav> -->
    <!-- /Breadcrumb -->
    <br><br>
<%
Users user = (Users)session.getAttribute("currentUser");
if(user != null){
String role;
if(user.getRole() == 1) role = "Admin";
else if(user.getRole() == 2) role = "Lecture";
else role = "Student";
%>
<div class="container">
    <div class="main-body">
        <div class="row">
            <div class="col-lg-4">
                <div class="card">
                    <div class="card-body">
                        <form id="updateForm" action="avatarUpdate" method="POST" enctype="multipart/form-data">
                            <div class="d-flex flex-column align-items-center text-center">
                                <div class="containers">
                                    <input type="file" name="file" id="imgupload" accept="image/*" style="display:none" onchange="submitForm()"/>
                                    <img src="<%=user.getAvatarURL()%>" alt="Admin" class="rounded-circle p-1 bg-primary image" width="150" height="150" onclick="UpdateImage()">
                                    <div class="middle" onclick="UpdateImage()">
                                        <i class="fas fa-pen"></i>
                                    </div>
                                </div>
                                <style>
                                    .middle {
                                        transition: .5s ease;
                                        opacity: 0;
                                        position: absolute;
                                        top: 17%;
                                        left: 50%;
                                        transform: translate(-50%, -50%);
                                        -ms-transform: translate(-50%, -50%);
                                        text-align: center;
                                    }
                                    .containers:hover .image {
                                        opacity: 0.3;
                                        cursor: pointer;
                                    }

                                    .containers:hover .middle {
                                        opacity: 1;
                                        cursor: pointer;
                                    }
                                </style>
                                <script>
                                    function UpdateImage() {
                                        document.getElementById("imgupload").click();
                                    }
                                    function submitForm() {
                                        document.getElementById("updateForm").submit();
                                    }
                                </script>
                                <div class="mt-3">
                                    <h4><%=user.getUsername()%></h4>
                                    <p class="text-secondary mb-1"><%=role%></p>                                   
                                </div>
                            </div>
                        </form>
                        <hr class="my-4">
                        <div style="justify-content: center; display: flex">
                            <button
                                class="btn btn-primary has-icon px-4"
                                type="button"
                                style="justify-content: center; margin: auto"
                                >
                                <a href="changepassword.jsp" style="text-decoration: none; color: white">Đổi mật khẩu</a>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-body">
                        <form action="update" method="POST">
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Username</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" name="username" value="<%=user.getUsername()%>">
                                </div>
                                <c:if test="${not empty message_username}">
                                    <p style="color:red">${message_username}</p>
                                </c:if>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Họ và tên</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" name="fullname" value="<%= (user.getFullname() != null) ? user.getFullname() : "" %>">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Số điện thoại</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="tel" id="phone" name="phone" pattern="[0-9]{10}" value="<%=(user.getPhone() != null) ? user.getPhone() : ""%>">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Nơi ở</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" name="address" value="<%= (user.getAddress() != null) ? user.getAddress() : "" %>">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Ngày sinh</h6>
                                </div>
                                <%
                                // Assuming user is already available and has a getDob() method that returns a String
                                String dobStr = user.getDob();
                                String formattedDobStr = "";

                                if (dobStr != null && !dobStr.isEmpty()) {
                                    try {
                                        // Define the original date format (e.g., dd-MM-yyyy)
                                        SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");

                                        // Parse the date string into a Date object
                                        Date dob = originalFormat.parse(dobStr);

                                        // Define the date format for formatting
                                        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");

                                        // Format the Date object into the new format
                                        formattedDobStr = newFormat.format(dob);
                                    } catch (ParseException e) {
                                        e.printStackTrace(); // Handle the exception as needed
                                    }
                                }
                                %>
                                <div class="col-sm-9 text-secondary">
                                    <input type="date" class="form-control" name="dob" value="<%= formattedDobStr %>">
                                </div>
                            </div>
                            <!--                          //       <div class="row mb-3">
                                                                <div class="col-sm-3">
                                                                    <h6 class="mb-0">Old Password</h6>
                                                                </div>
                                                                <div class="col-sm-9 text-secondary">
                                                                    <input type="password" class="form-control" name="oldPassword" value="">
                                                                </div>
                                                                <c:if test="${not empty message_password}">
                                                                    <p style="color:red">${message_password}</p>
                                                                </c:if>
                                                            </div>
                                                            <div class="row mb-3">
                                                                <div class="col-sm-3">
                                                                    <h6 class="mb-0">New Password</h6>
                                                                </div>
                                                                <div class="col-sm-9 text-secondary">
                                                                    <input type="password" class="form-control" name="newPassword" value="">
                                                                </div>
                                                            </div>
                                                            <div class="row mb-3">
                                                                <div class="col-sm-3">
                                                                    <h6 class="mb-0">Confirm Password</h6>
                                                                </div>
                                                                <div class="col-sm-9 text-secondary">
                                                                    <input type="password" class="form-control" name="confirmPassword" value="">
                                                                </div>
                                                                <c:if test="${not empty message_confirm}">
                                                                    <p style="color:red">${message_confirm}</p>
                                                                </c:if>
                                                            </div>-->
                            <div class="row">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-9 text-secondary" style="display: flex; justify-content: space-between">
                                    <input type="submit" class="btn btn-primary px-4" value="Lưu">
                                </div>
                            </div>
                        </form>
                    </div>
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
