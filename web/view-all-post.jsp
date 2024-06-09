<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>THI247 - My Post</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  <link href="assets/css/style.css" rel="stylesheet">

</head>

<body>

    <main id="main" class="main" style="margin-left: 0">
      <section class="section" style="margin: auto;justify-content: center">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Bài ??ng g?n ?ây</h5>
              <p>D??i ?ây là danh sách nh?ng bài vi?t c?a b?n ? trên di?n ?àn</p>
              <!-- Table with stripped rows -->
              <table class="table datatable">
                <thead>
                  <tr>
                    <th style="text-align: center">Tiêu ??</th>
                    <th style="text-align: center">N?i dung</th>
                    <th style="text-align: center" data-type="date" data-format="YYYY/DD/MM">Ngày ??ng</th>
                    <th style="text-align: center">L??t t??ng tác</th>
                    <th style="padding-left: 33px">Tác v?</th>
                  </tr>
                </thead>
                <tbody>
                    <!--bai dang-->
                  <tr>
                    <td style="text-align: center">getPostTitle</td>
                    <td style="text-align: center">getPostContext cut...</td>
                    <td style="text-align: center">getPostDate</td>
                    <td style="text-align: center">getPostReact</td>
                    <td style="display: flex; flex-direction: row; text-align: center">

                            <div class="inner-sidebar-header justify-content-center">
                                <a href="post-update.jsp">
                                    <input type="submit" class="btn btn-primary" value="S?a"/>
                                </a>
                            </div>
                            
                        
                        <div class="inner-sidebar-header justify-content-center" style="background-color: red; border-radius: 5px">
                                <button
                                  class="btn btn-xoa"
                                  type="button"
                                  data-toggle="modal"
                                  data-target="#threadModal"  
                                >
                                  Xóa
                                </button>
                            </div>
          
            <div class="modal fade" id="threadModal" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content" style="width: 500px; margin: auto">
<!--                        doi ten servlet-->
                        <form action="NewPost" method="POST" enctype="multipart/form-data">
                        <div class="modal-header d-flex align-items-center bg-primary text-white">
                            <h6 class="modal-title mb-0" id="threadModalLabel">Xác nh?n xóa bài ??ng ?</h6>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">                       
                                    <div class="modal-footer">
                                      <button type="button" class="btn btn-light" data-dismiss="modal" >H?y</button>
                                        <input type="submit" class="btn btn-primary" style="background-color: red" value="Xóa"/>
                                    </div>
                                </div> 
                            </div>
                        </form>
                    </div> 
                </div>                        
            </div>      
                        
                        
                        
                        
                    </td>
                  </tr>
                 <!--ket thuc bai dang-->
                </tbody>
              </table>
              <!-- End Table with stripped rows -->
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
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript"></script>
</body>

</html>