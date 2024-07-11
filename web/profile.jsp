<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    input {
        height: 50px;
    }
    a{
        overflow-wrap: break-word;
        word-break: break-word;
    }

    p{
        overflow-wrap: break-word;
        word-break: break-word;
    }
</style>
<jsp:include page="header.jsp"></jsp:include>

    <script>
        var container = document.getElementById("tagID");
        var tag = container.getElementsByClassName("tag");
        var current = container.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
    </script>



    <div class="container">
        <div class="main-body">
        <%
                Users user = (Users)session.getAttribute("currentUser");
                TeacherRequest requests = new AdminDAO().getRequestByUserID(user.getUserID());
                Subjects subject = new Subjects();
                if(requests != null){
                    subject = new ExamDAO().getSubjectByID(requests.getSubjectID());
                }
                if(user != null){
                String role;
                if(user.getRole() == 1) role = "Admin";
                else if(user.getRole() == 2) role = "Giáo viên";
                else role = "Học sinh";
                String password = "";
                for(int i = 0; i < user.getPassword().length(); i++){
                    password += "*";
                }
        %>

        <!-- Breadcrumb -->
        <!-- <nav aria-label="breadcrumb" class="main-breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
            <li class="breadcrumb-item"><a href="javascript:void(0)">User</a></li>
            <li class="breadcrumb-item active" aria-current="page">User Profile</li>
          </ol>
        </nav> -->
        <!-- /Breadcrumb -->
        <br><br>
        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="<%=user.getAvatarURL()%>" alt="Admin" class="rounded-circle p-1 bg-primary" width="150" height="150">
                            <div class="mt-3">
                                <h4><%=user.getUsername()%></h4>
                                <p class="text-secondary mb-1"><%=role%><%if(user.getRole() == 2){%> môn <%=subject.getSubjectName()%><% }%></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card mt-3">
                    <%
                    if(user.getRole() == 3){
                    %>
                    <div class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                        <h4>Yêu cầu giáo viên</h4>
                        <%
                        if(requests == null){
                        %>
                        <h4><a style="text-decoration: none" href="teacher-request.jsp">Ở đây</a></h4>
                        <%
                            }else{
                        %>
                        <a href="teacher-request.jsp" style="text-decoration: none;"><h5 class="text-warning">Đang xử lí</h5></a>
                        <%
                            }
                        %>
                    </div>
                    <%
                        }
                    %>
                    <div class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                        <h4>Recent post</h4>
                        <h4><a style="text-decoration: none" href="ViewAllPostUser?userID=<%=user.getUserID()%>">View all post</a></h4>
                    </div>
                    <%
                    List<Forum> forums = new ForumDAO().getAllPostFromUserID(user.getUserID());
                    if(forums.size() > 0){
                        String str;
                        int size;
                        if(forums.size() > 5) size = forums.size() - 5;
                        else size = 0;
                        for(int i = forums.size() - 1; i >= size; i--){
                        Forum forum = forums.get(i);
                        if(forum.getPostTitle().length() > 60) 
                            str = forum.getPostTitle().substring(1, 60) + "...";
                            else str = forum.getPostTitle();
                    %>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <a
                                href="ForumDetail?postID=<%=forum.getPostID()%>"
                                data-target=".forum-content"
                                class="text-body"
                                ><%=str%></a
                            >
                        </li>
                    </ul>
                    <%
                        }
                    }
                    else{
                    %>
                    <div class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                        Bạn chưa đăng bài viết nào!
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Username</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%=user.getUsername()%>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Họ và tên</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%= (user.getFullname() != null) ? user.getFullname() : "" %>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Số điện thoại</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%=(user.getPhone() != null) ? user.getPhone() : ""%>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Nơi ở</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%= (user.getAddress() != null) ? user.getAddress() : "" %>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Ngày sinh</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%= (user.getDob() != null) ? user.getDob() : "" %>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Email</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%=user.getEmail()%>
                            </div>
                        </div>
                        <hr>
                        <%
                            if(user.getRole() == 2){
                            
                        %>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Trình độ học vấn</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%=requests.getAcademicLevel()%>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Kinh nghiệm làm việc</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%=requests.getExperience()%> năm
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Đã từng/ Đang công tác tại trường</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%=requests.getSchool()%>
                            </div>
                        </div>
                        <hr>
                        <%
                            }
                        %>
                        <c:if test="${not empty message}">
                            <p style="color:green">${message}</p>
                        </c:if>
                        <div class="row">
                            <div class="col-sm-12">
                                <a class="btn btn-info " href="editprofile.jsp">Chỉnh sửa</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>
</div>
<%
    }
%>

<jsp:include page="footer.jsp"></jsp:include>

<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
