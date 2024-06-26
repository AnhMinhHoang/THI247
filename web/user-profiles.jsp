<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<jsp:include page="header.jsp"></jsp:include>
    <script>
        var container = document.getElementById("tagID");
        var tag = container.getElementsByClassName("tag");
        var current = container.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
    </script>
    <style>
        #main{
            margin: auto;
        }
        #card-height{
            height: 364px;
        }
        #card-center {
            align-items: center;
            justify-content: center;
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
<%
int id = (Integer)session.getAttribute("userID");
Users user = new UserDAO().findByUserID(id);
String role;
if(user.getRole() == 1) role = "Admin";
else if(user.getRole() == 2) role = "Lecture";
else role = "Student";
%>
</div>
</nav>
<!-- Navbar End -->
<main id="main" class="main">
    <section class="section profile">
        <div class="row">
            <div class="col-xl-4">
                <div class="card" id="card-height">
                    <div class="card-body profile-card pt-4 d-flex flex-column align-items-center" id="card-center">
                        <img src="<%=user.getAvatarURL()%>"class="rounded-circle" width="130px" height="130px">
                        <h2><%=user.getUsername()%></h2>
                        <h3><%=role%></h3>    
                    </div>
                </div>
                <div class="card mt-3">
                    <div class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                        <h4>Recent post</h4>
                        <h4><a href="ViewAllPost?userID=<%=user.getUserID()%>">View all post</a></h4>
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
                        <%=user.getUsername()%> chưa đăng bài viết nào!
                    </div>
                    <%
                        }
                    %>
                </div>

            </div>



            <div class="col-xl-8">

                <div class="card">
                    <div class="card-body pt-3">
                        <!-- Bordered Tabs -->
                        <div class="tab-content pt-2">

                            <div class="tab-pane fade show active profile-overview" id="profile-overview">

                                <h5 class="card-title">Thông tin cá nhân</h5>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label ">Họ và Tên</div>
                                    <div class="col-lg-9 col-md-8"><%= (user.getFullname() != null) ? user.getFullname() : "" %></div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Vai trò</div>
                                    <div class="col-lg-9 col-md-8"><%=role%></div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Ngày Sinh</div>
                                    <div class="col-lg-9 col-md-8"><%= (user.getDob() != null) ? user.getDob() : "" %></div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Nơi ở</div>
                                    <div class="col-lg-9 col-md-8"><%= (user.getAddress() != null) ? user.getAddress() : "" %></div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">SĐT</div>
                                    <div class="col-lg-9 col-md-8"><%= (user.getPhone() != null) ? user.getPhone() : "" %></div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Email</div>
                                    <div class="col-lg-9 col-md-8"><%=user.getEmail()%></div>
                                </div>
                            </div>
                        </div><!-- End Bordered Tabs -->
                    </div>
                </div>
            </div>
        </div>
    </section>

</main><!-- End #main -->
<jsp:include page="footer.jsp"></jsp:include>

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>