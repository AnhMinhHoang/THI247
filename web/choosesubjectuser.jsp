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
<%
List<Subjects> subjects = new ExamDAO().getAllSubject();
%>

    <!-- Courses Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h6 class="section-title bg-white text-center text-primary px-3">Môn học</h6>
                <h1 class="mb-5">Danh sách các môn học</h1>
            </div>
            <div class="row g-4 justify-content-center">
<!--                bai kiem tra-->
                <%
                for(Subjects subject: subjects){
                %>
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="course-item bg-light">
                        <div class="position-relative overflow-hidden">
                            <img class="img-fluid" src="img/course-1.jpg" alt="">
                            <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
                                <a href="ChangeSubjectUser?subjectID=<%=subject.getSubjectID()%>" class="flex-shrink-0 btn btn-sm btn-primary px-3" style="border-radius: 30px;">Xem câu hỏi</a>
                            </div>
                        </div>
                        <div class="text-center p-4 pb-0">
                            <h3 class="mb-0"><%=subject.getSubjectName()%></h3>
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
    <!-- Courses End -->
    <jsp:include page="footer.jsp"></jsp:include>
    <!-- Back to Top -->
    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
