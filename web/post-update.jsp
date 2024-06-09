<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<jsp:include page="header.jsp"></jsp:include>

<%
int postID = (Integer)session.getAttribute("postID");
Forum forum = new ForumDAO().findPostByID(postID);
String postIMG = (String)session.getAttribute("postIMG");
%>

<div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="text-white animated slideInDown">Chỉnh sửa bài đăng</h1>
                </div>
            </div>
        </div>
    </div>
<body>
    <section class="section">
      <div class="row">
          <div class="col-lg-6" style="margin: auto">

                <form action="UpdatePost" method="POST" enctype="multipart/form-data">
          <div class="card">
            <div class="card-body">
                    <input type="hidden" name="userID" value="<%=forum.getUserID()%>"/>
                    <input type="hidden" name="postID" value="<%=postID%>"/>
                    <input type="hidden" name="postIMG" value="<%=postIMG%>"/>
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label" >Tiêu đề</label>
                  <div class="col-sm-10">
                    <input type="text" name="title" class="form-control" value="<%=forum.getPostTitle()%>">
                    </div>
                </div>
                  <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">Nội dung</label>
                  <div class="col-sm-10">
                      <textarea class="form-control" name="context" rows="5" style="resize: none; overflow: hidden;"><%=forum.getPostContext()%></textarea>
                  </div>
                </div>
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">Ảnh</label>
                  <div class="col-sm-10">
                      <input class="form-control" name="file" type="file" accept="image/*" id="formFile" onchange="readURL(this);" value="aa">
                      <img id="img" src="<%=forum.getPostImg()%>" alt="Không có ảnh nào trong bài post" width="500px" height="200px"/>
                  </div>
                </div>
<!--                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">Ngày đăng</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" value="getPostDate" disabled>
                  </div>
                </div>
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">L??t t??ng tác</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" value="getPostReact" disabled>
                  </div>
                -->
                <div class="row" style="margin: auto">
                  <div class="col-sm-10">
                    <input type="submit" class="btn btn-primary" value="Cập nhật">
                  </div>
                </div>
            </div>
          </div>
              </form><!-- End General Form Elements -->
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
  <script>
      function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
      $('#img').attr('src', e.target.result).width(500).height(200);
    };

    reader.readAsDataURL(input.files[0]);
  }
}
  </script>
  
<script>
  var textarea = document.getElementById("submit-comment");
  textarea.addEventListener("input", function () {
      this.style.height = "auto";
      this.style.height = (this.scrollHeight) + "px";
  });
</script>
<jsp:include page="footer.jsp"></jsp:include>