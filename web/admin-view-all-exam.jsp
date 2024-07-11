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
                </div>
                <%
                    if(session.getAttribute("currentUser") == null){
                %>
                <a href="login.jsp" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block" style="color:white;">Tham gia ngay<i class="fa fa-arrow-right ms-3"></i></a> 
                <%
                    }
                    else{
                    Users user = (Users)session.getAttribute("currentUser");
                    String role;
                    if(user.getRole() == 1) role = "Admin";
                    else if(user.getRole() == 2) role = "Lecture";
                    else role = "Student";
                %>
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
              <span><%=role%></span>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>
              <a class="dropdown-item d-flex align-items-center" href="profile.jsp">
                <i class="bi bi-person"></i>
                <span>My Profile</span>
              </a>
            </li>
            <li>
              <a class="dropdown-item d-flex align-items-center" href="logout">
                <i class="bi bi-box-arrow-right"></i>
                <span>Sign Out</span>
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
  <main id="main" class="main" style="margin: auto; max-width: 1200px">
    <div class="pagetitle" style="text-align: center;">
        <br>
        <h3 class="text-primary" style="font-weight: bold">Danh sách các bài kiểm tra của hệ thống</h3>
      <br>
    </div><!-- End Page Title -->

    <section class="section dashboard" "
>      <div class="row">
        <!-- Left side columns -->
        <div class="col-lg-12">
          <div class="row">
            <div class="col-12">
              <div class="card recent-sales overflow-auto">
                <div class="card-body">
                    <h3 class="card-title" style="font-weight: bold">Toán<span></span></h3>
                  <table class="table table-borderless datatable">
                    <thead>
                      <tr>
                        <th scope="col" style="text-align: center">Tên bài kiểm tra</th>
                        <th scope="col" style="text-align: center">Tên giáo viên</th>
                        <th scope="col" style="text-align: center">Xem chi tiết</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td style="text-align: center">getUserfullname</td>
                        <td style="text-align: center">getuserEmail</td>
                        <th scope="row" style="text-align: center"><a href="#">bai kiem tra</a></th>
                      </tr> 
                    </tbody>
                  </table>
                </div>
              </div>
                <div class="card recent-sales overflow-auto">
                <div class="card-body">
                    <h3 class="card-title" style="font-weight: bold">Lý<span></span></h3>
                  <table class="table table-borderless datatable">
                    <thead>
                      <tr>
                        <th scope="col" style="text-align: center">Tên bài kiểm tra</th>
                        <th scope="col" style="text-align: center">Tên giáo viên</th>
                        <th scope="col" style="text-align: center">Xem chi tiết</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td style="text-align: center">getUserfullname</td>
                        <td style="text-align: center">getuserEmail</td>
                        <th scope="row" style="text-align: center"><a href="#">bai kiem tra</a></th>
                      </tr> 
                    </tbody>
                  </table>
                </div>
              </div>
                <div class="card recent-sales overflow-auto">
                <div class="card-body">
                    <h3 class="card-title" style="font-weight: bold">Hóa<span></span></h3>
                  <table class="table table-borderless datatable">
                    <thead>
                      <tr>
                        <th scope="col" style="text-align: center">Tên bài kiểm tra</th>
                        <th scope="col" style="text-align: center">Tên giáo viên</th>
                        <th scope="col" style="text-align: center">Xem chi tiết</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td style="text-align: center">getUserfullname</td>
                        <td style="text-align: center">getuserEmail</td>
                        <th scope="row" style="text-align: center"><a href="#">bai kiem tra</a></th>
                      </tr> 
                    </tbody>
                  </table>
                </div>
              </div>
                <div class="card recent-sales overflow-auto">
                <div class="card-body">
                    <h3 class="card-title" style="font-weight: bold">Sinh<span></span></h3>
                  <table class="table table-borderless datatable">
                    <thead>
                      <tr>
                        <th scope="col" style="text-align: center">Tên bài kiểm tra</th>
                        <th scope="col" style="text-align: center">Tên giáo viên</th>
                        <th scope="col" style="text-align: center">Xem chi tiết</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td style="text-align: center">getUserfullname</td>
                        <td style="text-align: center">getuserEmail</td>
                        <th scope="row" style="text-align: center"><a href="#">bai kiem tra</a></th>
                      </tr> 
                    </tbody>
                  </table>
                </div>
              </div>
                <div class="card recent-sales overflow-auto">
                <div class="card-body">
                    <h3 class="card-title" style="font-weight: bold">Sử<span></span></h3>
                  <table class="table table-borderless datatable">
                    <thead>
                      <tr>
                        <th scope="col" style="text-align: center">Tên bài kiểm tra</th>
                        <th scope="col" style="text-align: center">Tên giáo viên</th>
                        <th scope="col" style="text-align: center">Xem chi tiết</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td style="text-align: center">getUserfullname</td>
                        <td style="text-align: center">getuserEmail</td>
                        <th scope="row" style="text-align: center"><a href="#">bai kiem tra</a></th>
                      </tr> 
                    </tbody>
                  </table>
                </div>
              </div>
                <div class="card recent-sales overflow-auto">
                <div class="card-body">
                    <h3 class="card-title" style="font-weight: bold">Địa<span></span></h3>
                  <table class="table table-borderless datatable">
                    <thead>
                      <tr>
                        <th scope="col" style="text-align: center">Tên bài kiểm tra</th>
                        <th scope="col" style="text-align: center">Tên giáo viên</th>
                        <th scope="col" style="text-align: center">Xem chi tiết</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td style="text-align: center">getUserfullname</td>
                        <td style="text-align: center">getuserEmail</td>
                        <th scope="row" style="text-align: center"><a href="#">bai kiem tra</a></th>
                      </tr> 
                    </tbody>
                  </table>
                </div>
              </div>
                <div class="card recent-sales overflow-auto">
                <div class="card-body">
                    <h3 class="card-title" style="font-weight: bold">GDCD<span></span></h3>
                  <table class="table table-borderless datatable">
                    <thead>
                      <tr>
                        <th scope="col" style="text-align: center">Tên bài kiểm tra</th>
                        <th scope="col" style="text-align: center">Tên giáo viên</th>
                        <th scope="col" style="text-align: center">Xem chi tiết</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td style="text-align: center">getUserfullname</td>
                        <td style="text-align: center">getuserEmail</td>
                        <th scope="row" style="text-align: center"><a href="#">bai kiem tra</a></th>
                      </tr> 
                    </tbody>
                  </table>
                </div>
              </div>
                <div class="card recent-sales overflow-auto">
                <div class="card-body">
                    <h3 class="card-title" style="font-weight: bold">Tiếng Anh<span></span></h3>
                  <table class="table table-borderless datatable">
                    <thead>
                      <tr>
                        <th scope="col" style="text-align: center">Tên bài kiểm tra</th>
                        <th scope="col" style="text-align: center">Tên giáo viên</th>
                        <th scope="col" style="text-align: center">Xem chi tiết</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td style="text-align: center">getUserfullname</td>
                        <td style="text-align: center">getuserEmail</td>
                        <th scope="row" style="text-align: center"><a href="#">bai kiem tra</a></th>                      </tr> 
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
        </div>
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



  
