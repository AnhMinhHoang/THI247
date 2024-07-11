<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<jsp:include page="header.jsp"></jsp:include>
    <style>
        .btn-xoa{
            color: black;
        }

    </style>
    <script>
        var container = document.getElementById("tagID");
        var tag = container.getElementsByClassName("tag");
        var current = container.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
    </script>

<%
if(session.getAttribute("currentUser") != null){
Users user = (Users)session.getAttribute("currentUser");
List<Payment> paymentList = new UserDAO().getAllPaymentByID(user.getUserID());
%>
<div class="container-fluid bg-primary py-5 mb-5 page-header">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-10 text-center">
                <h1 class="display-3 text-white animated slideInDown">
                    Lịch sử giao dịch
                </h1>
                <h3 class="text-white animated slideInDown">Dưới đây là danh sách giao dịch của bạn </h3>
            </div>
        </div>
    </div>
</div>
<main id="main" class="main" style="margin-left: 0">
    <section class="section" style="margin: auto;justify-content: center">
        <div class="row">
            <div class="col-lg-12">
                <div class="inner-main-header">
                    <button class="btn btn-light"><a href="Home" style="text-decoration: none; color: Black">Trở về</a></button>
                </div>
                <div class="card">
                    <div class="card-body all">
                        <!-- Table with stripped rows -->
                        <%
                        if(paymentList.size() > 0){
                        %>
                        <table class="table datatable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Số tiền</th>
                                    <th>Ngày thanh toán</th>
                                    <th>Mã thanh toán</th>
                                    <th>Ngân hàng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--bai dang-->
                                <%
                                for(int i = paymentList.size() - 1; i >= 0; i--){
                                    Payment payment = paymentList.get(i);
                                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                                    String money = currencyFormatter.format(payment.getAmount());
                                %>
                                <tr>
                                    <td><%=payment.getPaymentID()%></td>
                                    <td><%=money%></td>
                                    <td><%=payment.getPaymentDate()%></td>
                                    <td><%=payment.getPaymentCode()%></td>
                                    <td><%=payment.getBank()%></td>
                                </tr>
                                <%
                                    }
                                  }
                                  else{
                                %>
                            <h3 class="text-center">Bạn chưa từng thực hiện một giao dịch nào!</h3>
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
<script>
        document.addEventListener("DOMContentLoaded", function (event) {
            var scrollpos = localStorage.getItem('scrollpos');
            if (scrollpos)
                window.scrollTo(0, scrollpos);
        });

        window.onbeforeunload = function (e) {
            localStorage.setItem('scrollpos', window.scrollY);
        };

</script>

<%
    }
%>
<jsp:include page="footer.jsp"></jsp:include>

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
