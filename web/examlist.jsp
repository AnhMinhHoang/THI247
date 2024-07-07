<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<jsp:include page="header.jsp"></jsp:include>
    <script>
        var container = document.getElementById("tagID");
        var tag = container.getElementsByClassName("tag");
        var current = container.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
        tag[2].className += " active";
    </script>

    <style>
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
<%
if(session.getAttribute("subjectID") != null){
Users user = (Users)session.getAttribute("currentUser");
int subjectID = (Integer)session.getAttribute("subjectID");
Subjects subject = new ExamDAO().getSubjectByID(subjectID);
List<Exam> exams = new StudentExamDAO().getAllExamBySubjectID(subjectID);
String filter = request.getParameter("filter");
if (filter != null) {
    if (filter.equals("free")) {
        exams.removeIf(exam -> exam.getPrice() > 0);
    } else if (filter.equals("paid")) {
        exams.removeIf(exam -> exam.getPrice() == 0);
    } else if (filter.equals("purchased")) {
        exams.removeIf(exam -> !new StudentExamDAO().checkExamPay(user.getUserID(), exam.getExamID()));
    }
}
%>


<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <br>
            <div class="dropdown">
                <button onclick="dropdown()" class="dropbtn btn btn-primary dropdown-toggle">Sắp xếp</button>
                <div id="myDropdown" class="dropdown-content">
                    <a class="dropdown-item" href="?filter=all">Tất cả các bài kiểm tra</a>
                    <a class="dropdown-item" href="?filter=free">Bài kiểm tra miễn phí</a>
                    <a class="dropdown-item" href="?filter=paid">Bài kiểm tra trả phí</a>
                    <a class="dropdown-item" href="?filter=purchased">Bài kiểm tra đã mua</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Courses Start -->
<div class="container-xxl py-5">
    <div class="container">
        <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h6 class="section-title bg-white text-center text-primary px-3">Môn <%=subject.getSubjectName()%></h6>
            <h1 class="mb-5">Danh sách các bài kiểm tra của môn <%=subject.getSubjectName()%></h1>
        </div>
        <div class="row g-4 justify-content-center">
            <!--                bai kiem tra-->
            <%
            for(int i = exams.size() - 1; i >= 0; i--){
            Exam exam = exams.get(i);
            int price = exam.getPrice();
            String modalID = "threadModal" + i;
            String modalNoID = "threadModalNo" + i;
            %>
            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                <div class="course-item bg-light">
                    <div class="position-relative overflow-hidden">
                        <img class="img-fluid" src="img/course-1.jpg" alt="">
                        <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                            <%
                            if(price == 0 || new StudentExamDAO().checkExamPay(user.getUserID(), exam.getExamID())){
                            %>
                            <a href="ExamDetail?examID=<%=exam.getExamID()%>" class="flex-shrink-0 btn btn-sm btn-primary px-3" 
                               style="border-radius: 30px;">Vào thi</a>
                            <%
                                }else if(price > 0 && user.getBalance() >= price){
                            %>
                            <button
                                class="flex-shrink-0 btn btn-sm btn-primary px-3"
                                style="border-radius:30px"
                                type="button"
                                data-toggle="modal"
                                data-target="#<%= modalID %>"  
                                >
                                Vào thi
                            </button>
                            <div class="modal fade" id="<%= modalID %>" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content" style="width: 500px; margin: auto">
                                        <form action="PurchaseExam" method="POST">
                                            <input type="hidden" name="price" value="<%=price%>"/>
                                            <input type="hidden" name="examID" value="<%=exam.getExamID()%>">
                                            <div class="modal-header d-flex align-items-center bg-primary text-white">
                                                <h6 class="modal-title mb-0" id="threadModalLabel" style="color: white">Xác nhận mua?</h6>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">                       
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-light" data-dismiss="modal" >Hủy</button>
                                                        <input type="submit" class="btn btn-primary" value="Xác nhận"/>
                                                    </div>
                                                </div> 
                                            </div>
                                        </form>
                                    </div> 
                                </div>                        
                            </div>  
                            <%
                                }else{
                            %>
                            <button
                                class="flex-shrink-0 btn btn-sm btn-primary px-3"
                                style="border-radius:30px"
                                type="button"
                                data-toggle="modal"
                                data-target="#<%= modalNoID %>"  
                                >
                                Vào thi
                            </button>
                            <div class="modal fade" id="<%= modalNoID %>" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content" style="width: 500px; margin: auto">
                                        <div class="modal-header d-flex align-items-center bg-primary text-white">
                                            <h6 class="modal-title mb-0" id="threadModalLabel" style="color: white">Bạn không đủ tiền để thanh toán! Nạp thêm tiền?</h6>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">                       
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-light" data-dismiss="modal" >Hủy</button>
                                                    <a href="recharge.jsp"><button class="btn btn-primary">Xác nhận</button></a>
                                                </div>
                                            </div> 
                                        </div>
                                    </div> 
                                </div>                        
                            </div> 
                            <%
                                }
                            %>
                        </div>
                    </div>
                    <div class="text-center p-4 pb-0">
                        <h3 class="mb-0"><%=exam.getExamName()%></h3>
                        <%
                        if(price == 0){
                        %>
                        <h5>Free</h5>
                        <%
                            }else if(price > 0 && new StudentExamDAO().checkExamPay(user.getUserID(), exam.getExamID())){
                        %>
                        <h5 style="color: green"><%=price%> coin(Đã thanh toán)</h5>
                        <%
                            }else{
                        %>
                        <h5><%=price%> coin</h5>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
            <%
                }
            %>
            <!--                bai kiem tra-->
        </div>
    </div>
</div>
<%
    }
%>
<!-- Courses End -->

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

                    document.addEventListener('mouseleave', function (event) {
                        var dropdownContent = document.getElementById("myDropdown");
                        if (event.target.closest('.dropdown') === null && dropdownContent.classList.contains('show')) {
                            dropdownContent.classList.remove('show');
                        }
                    });
</script>

<jsp:include page="footer.jsp"></jsp:include>
<!-- Back to Top -->
<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>