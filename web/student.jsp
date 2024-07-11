
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>
    <script>
        var container = document.getElementById("tagID");
        var tag = container.getElementsByClassName("tag");
        var current = container.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
        tag[2].className += " active";
    </script>
    
<%
if(session.getAttribute("currentUser") != null){
Users user = (Users)session.getAttribute("currentUser");
if(user.getRole() == 3){
Tests test = new StudentExamDAO().getLatestTest(user.getUserID());
    if(test == null){
%>   
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h6 class="section-title bg-white text-center text-primary px-3">Kiểm tra</h6>
                <h1 class="mb-5">Vui lòng chọn một tác vụ</h1>
            </div>
            <br>
            <div class="row g-4 justify-content-center">
                <div class="col-lg-4 col-md-6 wow fadeInUp">
                    <div class="course-item bg-light">
                        <div class="position-relative overflow-hidden"></div>
                        <div class="text-center p-4 pb-0">
                            <a href="choosesubjectstudent.jsp" style="text-decoration: none">
                                <h3 class="mb-0">Kiểm tra</h3>
                            </a>  
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="row g-4 justify-content-center">
                <div class="col-lg-4 col-md-6 wow fadeInUp">
                    <div class="course-item bg-light">
                        <div class="position-relative overflow-hidden"></div>
                        <div class="text-center p-4 pb-0">
                            <a href="testhistory.jsp" style="text-decoration: none">
                                <h3 class="mb-0">Lịch sử làm bài</h3>
                            </a>  
                        </div>
                    </div>
                </div>
            </div>
            <br>
        </div>
    </div>
    <%
        }
}
}
    %>
<jsp:include page="footer.jsp"></jsp:include>

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>