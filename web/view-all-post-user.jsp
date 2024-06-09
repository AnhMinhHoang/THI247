<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<jsp:include page="header.jsp"></jsp:include>
<%
int id = (Integer)request.getAttribute("userID");
Users user = new UserDAO().findByUserID(id);
List<Forum> forums = new ForumDAO().getAllPostFromUserID(user.getUserID());
%>
    <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-3 text-white animated slideInDown">Bài đăng</h1>
                    <h3 class="text-white animated slideInDown">Dưới đây là danh sách những bài viết của bạn ở trên diễn đàn</h3>
                </div>
            </div>
        </div>
    </div>
    <main id="main" class="main" style="margin-left: 0">
      <section class="section" style="margin: auto;justify-content: center">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <!-- Table with stripped rows -->
               <%
              if(forums.size() > 0){
              %>
              <table class="table datatable">
                <thead>
                  <tr>
                    <th>
                      Tiêu đề
                    </th>
                    <th>Nội dung</th>
                    <th data-type="date" data-format="YYYY/DD/MM">Ngày đăng</th>
                    <th>Lượt tương tác</th>
                    <th>Tác vụ</th>
                  </tr>
                </thead>
                <tbody>
                    <!--bai dang-->
                    <%
                String title;
                String content;
                for(int i = forums.size() - 1; i >= 0; i--){
                    Forum forum = forums.get(i);
                    if(forum.getPostTitle().length() > 40) 
                        title = forum.getPostTitle().substring(1, 40) + "...";
                    else title = forum.getPostTitle();
                    if(forum.getPostContext().length() > 60) 
                        content = forum.getPostContext().substring(1, 60) + "...";
                    else content = forum.getPostContext();
                %>
                  <tr>
                    <td><a
                          href="ForumDetail?postID=<%=forum.getPostID()%>"
                          data-target=".forum-content"
                          class="text-body"
                          ><%=title%></a
                        ></td>
                    <td><%=content%></td>
                    <td><%=forum.getPostDate()%></td>
                    <td><%=forum.getPostReact()%></td>
                    <td>
                        <span>
                            <a href="url" target="target">Sửa</a>
                        </span>
                        <span>/</span>
                        <span>
                            <a href="url" target="target">Xóa</a>
                        </span>

                    </td>
                  </tr>
                  <%
                      }
                    }
                    else{
                  %>
                <h3 class="text-center"><%=user.getUsername()%> chưa từng đăng một bài đăng nào!</h3>
                  <%
                      }
                  %>
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
   <jsp:include page="footer.jsp"></jsp:include>
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

<!--
</body>

</html>-->