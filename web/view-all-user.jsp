<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>THI247</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/THI247.png" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.1.1/css/all.css">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            />

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="assets/css/admin-css.css" rel="stylesheet">
    </head>
    <body>
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css"
            integrity="sha256-46r060N2LrChLLb5zowXQ72/iKKNiw/lAmygmHExk/o="
            crossorigin="anonymous"
            />
        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
            <a href="Home" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
                <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>THI247</h2>
            </a>
            <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto p-4 p-lg-0" id="tagID">
                    <a href="Home" class="nav-item nav-link tag active">Trang Chủ</a>
                    <a href="forum.jsp" class="nav-item nav-link tag">Diễn Đàn</a>
                    <a href="teacher.jsp" class="nav-item nav-link tag">Kiểm Tra</a>
                    <a href="schedule.jsp" class="nav-item nav-link tag">Thời gian biểu</a>
                    <%
                        if(session.getAttribute("currentUser") == null){
                    %>
                    <a href="login.jsp" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Tham gia ngay<i class="fa fa-arrow-right ms-3"></i></a> 
                        <%
                            }
                            else{
                            Users user = (Users)session.getAttribute("currentUser");
                            TeacherRequest requests = new AdminDAO().getRequestByUserID(user.getUserID());
                            Subjects subject = new Subjects();
                            if(requests != null)
                                subject = new ExamDAO().getSubjectByID(requests.getSubjectID());
                            String role;
                            if(user.getRole() == 1) role = "Admin";
                            else if(user.getRole() == 2) role = "Giáo viên";
                            else role = "Học sinh";
                        %>
                    <a href="recharge.jsp" class="nav-item nav-link tag">
                        <i class="fas fa-coins"></i>
                        <span id="user-balance"><%=user.getBalance()%></span> 
                        <i class="fas fa-plus-circle"></i> 
                    </a>
                </div>
                <li class="nav-item dropdown pe-3 no">
                    <style>
                        .no{
                            display: block;
                        }
                    </style>
                    <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                        <img src="<%=user.getAvatarURL()%>" alt="Profile" width="50" height="50" class="rounded-circle">
                        <span class="d-none d-md-block dropdown-toggle ps-2"><%=user.getUsername()%></span>
                    </a><!-- End Profile Iamge Icon -->

                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                        <li class="dropdown-header">
                            <h6><%=user.getUsername()%></h6>
                            <span><%=role%> <%if(user.getRole() == 2){%>môn <%=subject.getSubjectName()%><% }%></span>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <%
                        if(user.getRole() == 1){
                        %>
                        <li>
                            <a class="dropdown-item d-flex align-items-center" href="admin.jsp">
                                <i class="bi bi-person"></i>
                                <span>Quản lý</span>
                            </a>
                        </li>
                        <%
                            }
                        %>

                        <li>
                            <a class="dropdown-item d-flex align-items-center" href="profile.jsp">
                                <i class="bi bi-person"></i>
                                <span>Thông tin</span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item d-flex align-items-center" href="logout">
                                <i class="bi bi-box-arrow-right"></i>
                                <span>Đăng xuất</span>
                            </a>
                        </li>

                    </ul><!-- End Profile Dropdown Items -->
                </li>
                <%
                    }
                %>
            </div>
        </nav>
        <!-- Navbar End -->

        <aside id="sidebar" class="sidebar">
            <ul class="sidebar-nav" id="sidebar-nav">
                <li class="nav-item">
                    <a class="nav-link collapsed"  href="admin.jsp">
                        <i class="bi bi-grid"></i>
                        <span>Dashboard</span>
                    </a>
                </li><!-- End Dashboard Nav -->

                <li class="nav-item">
                    <a class="nav-link" href="view-all-user.jsp">
                        <i class="bi bi-person"></i>
                        <span>Tất cả người dùng</span>
                    </a>
                </li>
                <!-- End Forms Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" href="view-all-payment.jsp">
                        <i class="bi bi-gem"></i><span>Giao dịch trong hệ thống</span>
                    </a>
                </li><!-- End Tables Nav -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="view-all-exam.jsp">
                        <i class="bi bi-journal-check"></i><span>Quản lí kiểm tra</span>
                    </a>

                </li>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="view-all-question.jsp">
                        <i class="bi bi-journal-check"></i><span>Quản lí câu hỏi</span>
                    </a>
                </li>
                <!-- End Icons Nav -->
            </ul>
        </aside><!-- End Sidebar-->

        <style>
            .dropdown:hover .dropdown-menu {
                display: none;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }
            .show {
                display: block;
            }

        </style>

        <main id="main" class="main">
            <h2 class="text-primary">Tất cả người dùng trong hệ thống</h2>
            <div class="dropdown">
                <button onclick="dropdown()" class="dropbtn btn btn-primary dropdown-toggle" style="color: white">Sắp xếp</button>
                <div id="myDropdown" class="dropdown-content">
                    <a class="dropdown-item" href="?filter=all">Tất cả người dùng</a>
                    <a class="dropdown-item" href="?filter=ban">Người dùng đã bị ban</a>
                    <a class="dropdown-item" href="?filter=unban">Người dùng không bị ban</a>
                </div>
            </div>
                <br><br>
            <div class="container">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="text-primary" scope="col">ID</th>
                            <th class="text-primary" scope="col">Avatar</th>
                            <th class="text-primary" scope="col">Tên người dùng</th>
                            <th class="text-primary" scope="col">Họ và tên</th>
                            <th class="text-primary" scope="col">Chức vụ</th>
                            <th class="text-primary" scope="col">Thông tin cá nhân</th>
                            <th class="text-primary" scope="col">Tác vụ</th>
                        </tr>
                    </thead>
                    <tbody>


                        <!--            list all user    -->
                        <%
                        List<Users> users = new UserDAO().getAllUsers();
                        String filter = request.getParameter("filter");
                        if (filter != null) {
                            if (filter.equals("all")) {
                                users = new UserDAO().getAllUsers();
                            } else if(filter.equals("ban")){
                                users = new AdminDAO().getAllStatusUser(true);
                            }
                            else{
                                users = new AdminDAO().getAllStatusUser(false);
                            }
                        }
                        String role;
                        if(users.size() > 0){
                            for(Users user: users){
                                if(user.getRole() == 1) role = "Admin";
                                else if(user.getRole() == 2) role = "Giáo viên";
                                else role = "Học sinh";
                        %>
                        <tr>
                            <td><%=user.getUserID()%></td>
                            <td><img src="<%=user.getAvatarURL()%>" width="50" height="50" alt="alt" class="rounded-circle"/></td>
                            <td><%=user.getUsername()%></td>
                            <td><%if (user.getFullname() != null){%><%=user.getFullname()%><% }else%> </td>
                            <td><%=role%></td>
                            <td><span class="badge" style="font-size: 14px"><a href="UserProfile?userID=<%=user.getUserID()%>">Xem chi tiết</a></span></td>
                            <%
                            if(user.isBan()){
                            %>
                            <td><a href="BanUnbanUser?userID=<%=user.getUserID()%>&isBan=false"><button class="btn btn-primary" style="border-radius: 25px">Unban</button></a></td>
                            <%
                                }else{
                            %>
                            <td><a href="BanUnbanUser?userID=<%=user.getUserID()%>&isBan=true"><button class="btn btn-ban" style="background-color: red; border-radius: 25px">Ban</button></a></td>
                            <%
                                }
                            %>
                        </tr>

                        <%
                            }
                            }
                        %>
                        <!--                ket thuc list all user-->


                        <!--<tr>
                                        <td>abc</td>
                                        <td>getUserImg</td>
                                        <td>getUsername</td>
                                        <td>getFullname</td>
                                        <td>getRole</td>
                                        <td>redirect to profile</td>
                                        <td><input type="submit" class="btn btn-primary" value="Unban"/></td>
                        
                                    </tr>-->
                    </tbody>

                </table>
            </div>
        </main><!-- End #main -->
        <script>
            /* When the user clicks on the button, 
             toggle between hiding and showing the dropdown content */
            function dropdown() {
                document.getElementById("myDropdown").classList.toggle("show");
            }

// Close the dropdown if the user clicks outside of it
            window.onclick = function (event) {
                if (!event.target.matches('.dropbtn')) {
                    var dropdowns = document.getElementsByClassName("dropdown-content");
                    var i;
                    for (i = 0; i < dropdowns.length; i++) {
                        var openDropdown = dropdowns[i];
                        if (openDropdown.classList.contains('show')) {
                            openDropdown.classList.remove('show');
                        }
                    }
                }
            };
        </script>


        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/chart.js/chart.umd.js"></script>
        <script src="assets/vendor/echarts/echarts.min.js"></script>
        <script src="assets/vendor/quill/quill.js"></script>
        <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
        <script src="assets/vendor/tinymce/tinymce.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>







