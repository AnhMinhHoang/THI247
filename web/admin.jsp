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
                    <a class="nav-link "  href="index.html">
                        <i class="bi bi-grid"></i>
                        <span>Dashboard</span>
                    </a>
                </li><!-- End Dashboard Nav -->

                <li class="nav-item">
                    <a class="nav-link collapsed" href="view-all-user.jsp">
                        <i class="bi bi-people"></i>
                        <span>Tất cả người dùng</span>
                    </a>
                </li>

                </li><!-- End Forms Nav -->

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
        <main id="main" class="main">
            <div class="pagetitle">
                <h1>Dashboard</h1>
            </div><!-- End Page Title -->
            <%
            List<Users> users = new UserDAO().getAllUsers();
            List<Users> teachers = new UserDAO().getAllUsersType(2);
            List<Users> students = new UserDAO().getAllUsersType(3);
            %>
            <section class="section dashboard">
                <div class="row">
                    <!-- Left side columns -->
                    <div class="col-lg-12">
                        <div class="row">
                            <!-- Sales Card -->
                            <div class="col-xxl-4 col-md-6">
                                <div class="card info-card sales-card">
                                    <div class="card-body">
                                        <h5 class="card-title">Tổng số người dùng</h5>
                                        <div class="d-flex align-items-center">
                                            <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                <i class="bi bi-people-fill"></i>
                                            </div>
                                            <div class="ps-3">
                                                <h6><%=users.size()%></h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- End Sales Card -->
                            <!-- Revenue Card -->
                            <div class="col-xxl-4 col-md-6">
                                <div class="card info-card revenue-card">
                                    <div class="card-body">
                                        <h5 class="card-title">Học sinh</h5>
                                        <div class="d-flex align-items-center">
                                            <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                <i class="bi bi-people-fill"></i>
                                            </div>
                                            <div class="ps-3">
                                                <h6><%=students.size()%></h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- End Revenue Card -->
                            <!-- Customers Card -->
                            <div class="col-xxl-4 col-xl-12">

                                <div class="card info-card customers-card">
                                    <div class="card-body">
                                        <h5 class="card-title">Giáo viên <span></span></h5>
                                        <div class="d-flex align-items-center">
                                            <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                <i class="bi bi-people-fill"></i>
                                            </div>
                                            <div class="ps-3">
                                                <h6><%=teachers.size()%></h6>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div><!-- End Customers Card -->



                            <!-- Recent Sales -->

                            <!-- Recent Sales -->
                            <div class="col-12">
                                <div class="card recent-sales overflow-auto">
                                    <div class="card-body">
                                        <h5 class="card-title">Yêu cầu giáo viên<span></span></h5>
                                        <table class="table table-borderless datatable">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Tên người dùng</th>
                                                    <th scope="col">Họ và tên</th>
                                                    <th scope="col">Email</th>
                                                    <th scope="col">Xem chi tiết</th>
                                                    <th scope="col" style="text-align: center">Trạng thái</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!--                        1 yeu cau giao vien-->
                                                <%
                                                List<TeacherRequest> allRequests = new AdminDAO().getAllRequest();
                                                if(allRequests.size() > 0){
                                                for(int i = allRequests.size() - 1; i >= 0; i--){
                                                    TeacherRequest requests = allRequests.get(i);
                                                    Users user = new UserDAO().findByUserID(requests.getUserID());
                                                    if(user.getRole() == 3){
                                                %>
                                                <tr>
                                                    <th scope="row"><%=user.getUsername()%></th>
                                                    <td><%=user.getFullname()%></td>
                                                    <td><%=user.getEmail()%></td>
                                                    <td><span class="badge" style="font-size: 14px"><a href="UserProfile?userID=<%=user.getUserID()%>">Bấm ở đây</a></span></td>
                                                    <td style="text-align: center">
                                                        <a href="TeacherAccept?userID=<%=user.getUserID()%>"><button class="btn btn-primary">Chấp thuận</button></a>
                                                        <a href="TeacherReject?requestID=<%=requests.getRequestID()%>"><button class="btn btn-danger">Từ chối</button></a>
                                                    </td>
                                                </tr>
                                                <%
                                                    }
                                                    }
                                                  }
                                                %>
                                                <!--                      ket thuc 1 yeu cau giao vien-->
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div><!-- End Recent Sales -->
                            <div class="col-12">
                                <div class="card recent-sales overflow-auto">
                                    <div class="card-body">
                                        <h5 class="card-title">Bảng tố cáo<span></span></h5>
                                        <table class="table table-borderless datatable">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Người tố cáo</th>
                                                    <th scope="col">Người bị tố cáo</th>
                                                    <th scope="col">Ngày tố cáo</th>
                                                    <th scope="col">Chi tiết</th>
                                                    <!--                        <th style="margin-left: 100px" scope="col">Tác vụ</th>-->
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!--                        1 report-->
                                                <%
                                                List<Report> reports = new ReportDAO().getAllReports();
                                                for(int i = reports.size() - 1; i >= 0; i--){
                                                    Report report = reports.get(i);
                                                    Users reportedUser = new UserDAO().findByUserID(report.getUserId());
                                                    Users userReport = new UserDAO().findByUserID(report.getUserReportedId());
                                                    String modalDetailId = "threadModalDetail" + i;
                                                %>
                                                <tr>
                                                    <th scope="row"><a href="UserProfile?userID=<%=report.getUserReportedId()%>"><%=userReport.getUsername()%></a></th>
                                                    <th scope="row"><a href="UserProfile?userID=<%=report.getUserId()%>"><%=reportedUser.getUsername()%></a></th>
                                                    <th scope="row"><%=report.getReportDate()%></th>
                                                    <th>
                                                        <button
                                                            class="btn btn-primary has-icon btn-block"
                                                            type="button"
                                                            data-toggle="modal"
                                                            style="width: 50%"
                                                            data-target="#<%=modalDetailId%>"
                                                            >
                                                            Xem chi tiết
                                                        </button>
                                                        <div class="modal fade" id="<%=modalDetailId%>" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog modal-lg" role="document">
                                                                <div class="modal-content" style="width: 80%; margin: auto">
                                                                    <div class="modal-header d-flex align-items-center bg-primary text-white">
                                                                        <h6 class="modal-title mb-0" id="threadModalLabel">Chi tiết tố cáo</h6>
                                                                    </div>
                                                                    <div class="modal-body" style="text-align: left;"> 
                                                                        <p style="font-weight: bold">- Lý do:</p>
                                                                        <%
                                                                        boolean checkOtherReason = false;
                                                                        for(ReportReason reason: report.getReasons()){
                                                                            if (reason.getReasonId() == 7){
                                                                                checkOtherReason = true;
                                                                                continue;
                                                                            }
                                                                        %>
                                                                        <p style="font-weight: 100">+ <%=reason.getReasonName()%></p>
                                                                        <%
                                                                            }
                                                                            if(checkOtherReason){
                                                                        %>
                                                                        <p style="font-weight: bold">- Lý do khác:</p>
                                                                        <p style="font-weight: 100">+ <%=report.getReportContext()%></p>
                                                                        <%
                                                                            }
                                                                            if(report.getReportImg() != null){
                                                                        %>
                                                                        <img src="<%=report.getReportImg()%>" width="80%" alt="alt"/>                                                                             
                                                                        <%
                                                                            }
                                                                        %>
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <input type="button" class="btn btn-primary" data-dismiss="modal"value="Xác nhận">
                                                                    </div>
                                                                </div> 
                                                            </div>                        
                                                        </div> 
                                                    </th>
                                                    <!--                        <td><span class="badge bg-success">Chấp thuận</span></td>
                                                                            <td><span class="badge bg-danger">Từ chối</span></td>
                                                                            <td><span class="badge bg-warning">Đang xử lí...</span></td>-->
                                                </tr>
                                                <%
                                                    }
                                                %>


                                                <!--                    ket thuc 1 report-->

                                            </tbody>
                                        </table>

                                    </div>

                                </div>
                            </div>
                        </div><!-- End Left side columns -->

                        <!-- Right side columns -->
                    </div>
            </section>
        </main><!-- End #main -->


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
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript"></script>




