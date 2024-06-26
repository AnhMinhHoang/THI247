<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
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
if(session.getAttribute("subjectID") != null){
int subjectID = (Integer)session.getAttribute("subjectID");
Subjects subject = new ExamDAO().getSubjectByID(subjectID);
List<QuestionBank> qbs = (List<QuestionBank>)session.getAttribute("questionList");
session.setAttribute("backlink", "viewsystemquestion.jsp");
%>
<div class="container-fluid bg-primary py-5 mb-5 page-header">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-10 text-center">
                <h1 class="display-3 text-white animated slideInDown">
                    Câu hỏi <%=subject.getSubjectName()%> có trong hệ thống
                </h1>
                <h3 class="text-white animated slideInDown">Dưới đây là danh sách những câu hỏi về môn <%=subject.getSubjectName()%> của hệ thống </h3>
            </div>
        </div>
    </div>
</div>
<main id="main" class="main" style="margin-left: 0">
    <section class="section" style="margin: auto;justify-content: center">
        <div class="row">
            <div class="col-lg-12">
                <div class="inner-main-header">
                    <button class="btn btn-light"><a href="choosesubjectsystem.jsp" style="text-decoration: none; color: Black">Trở về</a></button>
                </div>
                <div class="card">
                    <div class="card-body all">
                        <!-- Table with stripped rows -->
                        <%
                        if(qbs.size() > 0){
                        %>
                        <table class="table datatable">
                            <thead>
                                <tr>
                                    <th>Câu hỏi</th>
                                    <th>Câu trả lời</th>
                                    <th>Tác vụ</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--bai dang-->
                                <%
                                String context;
                                String answer;
                                for(int i = 0; i < qbs.size(); i++){
                                    QuestionBank qb = qbs.get(i);
                                    if(qb.getQuestionContext().length() > 40) 
                                        context = qb.getQuestionContext().substring(0, 40) + "...";
                                    else context = qb.getQuestionContext();
                                    if(qb.getChoiceCorrect().length() > 60) 
                                        answer = qb.getChoiceCorrect().substring(0, 60) + "...";
                                    else answer = qb.getChoiceCorrect(); 
                                %>
                                <tr>
                                    <td class="question"><%=context%></td>
                                    <td class="answer"><%=answer%></td>
                                    <td style="display: flex; flex-direction: row; text-align: center">
                                        <form action="ViewQuestionDetail" method="POST">
                                            <input type="hidden" name="questionID" value="<%=qb.getQuestionId()%>">
                                            <div class="inner-sidebar-header justify-content-center">
                                                <input type="submit" class="btn btn-primary" value="Xem chi tiết"/>
                                            </div>
                                        </form>     
                                    </td>
                                </tr>
                                <%
                                    }
                                  }
                                  else{
                                %>
                            <h3 class="text-center">Bạn chưa từng tạo một bài kiểm tra nào!</h3>
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
