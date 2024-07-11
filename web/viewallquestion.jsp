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
if(session.getAttribute("examID") != null){
int examID = (Integer)session.getAttribute("examID");
Exam currentExam = new ExamDAO().getExamByID(examID);
List<QuestionBank> qbs = new ExamDAO().getAllQuestionByExamID(examID);
session.setAttribute("backlink", "viewallquestion.jsp");
Users user = (Users)session.getAttribute("currentUser");
String backlink = "ViewAllExamTeacher.jsp";
if(user.getRole() == 1) backlink = "view-all-exam.jsp";
int hour = currentExam.getTimer() / 3600;
int minute = (currentExam.getTimer() % 3600) / 60;
%>
<div class="container-fluid bg-primary py-5 mb-5 page-header">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-10 text-center">
                <h1 class="display-3 text-white animated slideInDown">
                    <%=currentExam.getExamName()%>
                </h1>
                <button data-toggle="modal" data-target="#editname" style="background-color: transparent; border: none">
                    <i class="fas fa-pen animated slideInDown" style="color: white"></i>
                </button>
                <div class="modal fade" id="editname" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content" style="width: 500px; margin: auto">
                            <form action="ChangeExamName" method="POST">
                                <div class="modal-header d-flex align-items-center bg-primary text-white">
                                    <h6 class="modal-title mb-0" id="threadModalLabel">Đổi tên đề thi</h6>
                                </div>
                                <div class="modal-body">
                                    <input type="hidden" name="examID" value="<%=examID%>"/>
                                    <label for="examName">Tên đề thi:</label>
                                    <input style="width: 70%" type="text" id="examName" name="examName" value="<%=currentExam.getExamName()%>" required>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-light" data-dismiss="modal" value="Huỷ">
                                    <input type="submit" class="btn btn-primary" value="Đổi tên"/>
                                </div>
                            </form>
                        </div> 
                    </div>                        
                </div> 
                <h3 class="text-white animated slideInDown">Dưới đây là danh sách những câu hỏi trong bài kiểm tra <%=currentExam.getExamName()%></h3>
            </div>
        </div>
    </div>
</div>
<main id="main" class="main" style="margin-left: 0">
    <section class="section" style="margin: auto;justify-content: center">
        <div class="row">
            <div class="col-lg-12">
                <div style="display: flex">
                    <form action="PassDataExamAdd" method="POST">
                        <input type="hidden" name="examID" value="<%=examID%>"/>
                        <button class="btn btn-light"><a href="<%=backlink%>" style="text-decoration: none; color: Black">Trở về</a></button>
                        <input class="btn btn-light" type="submit" value="Thêm câu hỏi">
                    </form>
                    <button style="margin-left: 5px" class="btn btn-light" data-toggle="modal" data-target="#editTime">
                        Thay đổi số giờ
                    </button>
                </div>
                <div class="modal fade" id="editTime" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content" style="width: 500px; margin: auto">
                            <form action="ChangeExamTime" method="POST">
                                <div class="modal-header d-flex align-items-center bg-primary text-white">
                                    <h6 class="modal-title mb-0" id="threadModalLabel">Đổi số giờ thi</h6>
                                </div>
                                <div class="modal-body">
                                    <input type="hidden" name="examID" value="<%=examID%>"/>
                                    <div style="display: flex; align-items: center;">
                                        <label for="examName" style="margin-right: 10px; margin-top: 20px">Tổng thời gian làm bài:</label>
                                        <div style="display: flex; align-items: center;">
                                            <div style="display: flex; flex-direction: column; align-items: center; margin-right: 10px;">
                                                <label for="examMinutes" style="text-align: center;">Giờ</label>
                                                <input type="number" name="examHours" min="0" value="<%=hour%>" required style="width: 50px; text-align: center;">
                                            </div>
                                            <div style="display: flex; flex-direction: column; align-items: center;">
                                                <label for="examSeconds" style="text-align: center;">Phút</label>
                                                <input type="number" name="examMinutes" min="1" value="<%=minute%>" required style="width: 50px; text-align: center;">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-light" data-dismiss="modal" value="Huỷ">
                                    <input type="submit" class="btn btn-primary" value="Đổi thời gian"/>
                                </div>
                            </form>
                        </div> 
                    </div>                        
                </div> 
                <div class="card">
                    <div class="card-body">
                        <!-- Table with stripped rows -->
                        <%
                        if(qbs.size() > 0){
                        %>
                        <table class="table datatable">
                            <thead>
                                <tr>
                                    <th>Câu hỏi</th>
                                    <th>Câu trả lời</th>
                                    <th style="padding-left: 70px">Tác vụ</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--bai dang-->
                                <%
                                String context;
                                String answer;
                                for(int i = 0; i < qbs.size(); i++){
                                    QuestionBank qb = qbs.get(i);
                                    if(qb.getQuestionContext().length() > 40){ 
                                        context = qb.getQuestionContext().substring(0, 40) + "...";
                                    }
                                    else if(qb.getQuestionContext().length() == 0){
                                        context = qb.getQuestionImg();
                                    }
                                    else context = qb.getQuestionContext();
                                    if(qb.getChoiceCorrect().startsWith("uploads/docreader")){
                                        answer = qb.getChoiceCorrect();
                                    }
                                    else{
                                        if(qb.getChoiceCorrect().length() > 60) 
                                            answer = qb.getChoiceCorrect().substring(0, 60) + "...";
                                        else answer = qb.getChoiceCorrect();
                                    }
                                    String modalId = "threadModal" + i;
                                %>
                                <tr>
                                    <%
                                    if(context.startsWith("uploads/docreader")){
                                    %>
                                    <td><img src="<%=context%>" width="30%" height="30%" alt="alt"/></td>
                                    <%
                                        }
                                    else{
                                    %>
                                    <td><p><%=context%></p></td>
                                    <%
                                        }
                                    %>
                                    <%
                                    if(answer.startsWith("uploads/docreader")){
                                    %>
                                    <td><img src="<%=answer%>" width="50%" height="50%" alt="alt"/></td>
                                    <%
                                        }
                                    else{
                                    %>
                                    <td><%=answer%></td>
                                    <%
                                        }
                                    %>
                                    <td style="display: flex; flex-direction: row; text-align: center">
                                        <form action="ViewQuestionDetail" method="POST">
                                            <input type="hidden" name="questionID" value="<%=qb.getQuestionId()%>">
                                            <div class="inner-sidebar-header justify-content-center">
                                                <input type="submit" class="btn btn-primary" value="Xem chi tiết"/>
                                            </div>
                                        </form>
                                        <div class="inner-sidebar-header justify-content-center" style="background-color: red; border-radius: 5px">
                                            <button
                                                class="btn btn-xoa"
                                                type="button"
                                                data-toggle="modal"
                                                data-target="#<%= modalId %>"  
                                                >
                                                Xoá
                                            </button>
                                        </div>

                                        <div class="modal fade" id="<%= modalId %>" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
                                            <div class="modal-dialog modal-lg" role="document">
                                                <div class="modal-content" style="width: 500px; margin: auto">
                                                    <form action="DeleteQuestionInExam" method="POST">
                                                        <input type="hidden" name="examID" value="<%=examID%>">
                                                        <input type="hidden" name="questionID" value="<%=qb.getQuestionId()%>">
                                                        <div class="modal-header d-flex align-items-center bg-primary text-white">
                                                            <h6 class="modal-title mb-0" id="threadModalLabel">Xác nhận xóa câu hỏi?</h6>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="form-group">                       
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-light" data-dismiss="modal" >Hủy</button>
                                                                    <input type="submit" class="btn btn-primary" style="background-color: red" value="Xóa câu hỏi"/>
                                                                </div>
                                                            </div> 
                                                        </div>
                                                    </form>
                                                </div> 
                                            </div>                        
                                        </div>      




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
