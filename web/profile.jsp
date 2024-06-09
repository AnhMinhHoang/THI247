<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
        input {
            height: 50px;
        }
</style>
<jsp:include page="header.jsp"></jsp:include>
  
    

    <div class="container">
        <div class="main-body">
            <%
                    Users user = (Users)session.getAttribute("currentUser");
                    if(user != null){
                    String role;
                    if(user.getRole() == 1) role = "Admin";
                    else if(user.getRole() == 2) role = "Lecture";
                    else role = "Student";
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
                          <p class="text-secondary mb-1"><%=role%></p>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="card mt-3">
                      <div class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                          <h4>Recent post</h4>
                          <h4><a href="view-all-post.jsp">View all post</a></h4>
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
                            str = forum.getPostTitle().substring(1, 200) + "...";
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
                          <h6 class="mb-0">Full Name</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                          <%=user.getFullname()%>
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
                        <div class="row">
                        <div class="col-sm-3">
                          <h6 class="mb-0">Password</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <%=password%>
                        </div>
                      </div>
                        <hr>
                        <c:if test="${not empty message}">
                                <p style="color:green">${message}</p>
                        </c:if>
                      <div class="row">
                        <div class="col-sm-12">
                          <a class="btn btn-info " href="editprofile.jsp">Edit</a>
                        </div>
                      </div>
                    </div>
                  </div>
    
                  <!-- <div class="row gutters-sm">
                    <div class="col-sm-6 mb-3">
                      <div class="card h-100">
                        <div class="card-body">
                          <h6 class="d-flex align-items-center mb-3"><i class="material-icons text-info mr-2">assignment</i>Project Status</h6>
                          <small>Web Design</small>
                          <div class="progress mb-3" style="height: 5px">
                            <div class="progress-bar bg-primary" role="progressbar" style="width: 80%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                          <small>Website Markup</small>
                          <div class="progress mb-3" style="height: 5px">
                            <div class="progress-bar bg-primary" role="progressbar" style="width: 72%" aria-valuenow="72" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                          <small>One Page</small>
                          <div class="progress mb-3" style="height: 5px">
                            <div class="progress-bar bg-primary" role="progressbar" style="width: 89%" aria-valuenow="89" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                          <small>Mobile Template</small>
                          <div class="progress mb-3" style="height: 5px">
                            <div class="progress-bar bg-primary" role="progressbar" style="width: 55%" aria-valuenow="55" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                          <small>Backend API</small>
                          <div class="progress mb-3" style="height: 5px">
                            <div class="progress-bar bg-primary" role="progressbar" style="width: 66%" aria-valuenow="66" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-sm-6 mb-3">
                      <div class="card h-100">
                        <div class="card-body">
                          <h6 class="d-flex align-items-center mb-3"><i class="material-icons text-info mr-2">assignment</i>Project Status</h6>
                          <small>Web Design</small>
                          <div class="progress mb-3" style="height: 5px">
                            <div class="progress-bar bg-primary" role="progressbar" style="width: 80%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                          <small>Website Markup</small>
                          <div class="progress mb-3" style="height: 5px">
                            <div class="progress-bar bg-primary" role="progressbar" style="width: 72%" aria-valuenow="72" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                          <small>One Page</small>
                          <div class="progress mb-3" style="height: 5px">
                            <div class="progress-bar bg-primary" role="progressbar" style="width: 89%" aria-valuenow="89" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                          <small>Mobile Template</small>
                          <div class="progress mb-3" style="height: 5px">
                            <div class="progress-bar bg-primary" role="progressbar" style="width: 55%" aria-valuenow="55" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                          <small>Backend API</small>
                          <div class="progress mb-3" style="height: 5px">
                            <div class="progress-bar bg-primary" role="progressbar" style="width: 66%" aria-valuenow="66" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div> -->
    
    
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
