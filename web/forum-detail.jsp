<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<jsp:include page="header.jsp"></jsp:include>
    <style type="text/css">
      body {
        margin-top: 20px;
        color: #1a202c;
        text-align: left;
        background-color: #e2e8f0;
      }
      .inner-wrapper {
        position: relative;
        height: calc(100vh - 3.5rem);
        transition: transform 0.3s;
        margin-left: -235px;
      }
      @media (min-width: 992px) {
        .sticky-navbar .inner-wrapper {
          height: calc(100vh - 3.5rem - 48px);
        }
      }

      .inner-main,
      .inner-sidebar {
        position: absolute;
        top: 0;
        bottom: 0;
        display: flex;
        flex-direction: column;
      }
      .inner-sidebar {
        left: 0;
        width: 235px;
        border-right: 1px solid #cbd5e0;
        background-color: #fff;
        z-index: 1;
      }
      .inner-main {
        right: 0;
        left: 235px;
      }
      .inner-main-footer,
      .inner-main-header,
      .inner-sidebar-footer,
      .inner-sidebar-header {
        height: 3.5rem;
        border-bottom: 1px solid #cbd5e0;
        display: flex;
        align-items: center;
        padding: 0 1rem;
        flex-shrink: 0;
      }
      .inner-main-body,
      .inner-sidebar-body {
        padding: 1rem;
        overflow-y: auto;
        position: relative;
        flex: 1 1 auto;
      }
      .inner-main-body .sticky-top,
      .inner-sidebar-body .sticky-top {
        z-index: 999;
      }
      .inner-main-footer,
      .inner-main-header {
        background-color: #fff;
      }
      .inner-main-footer,
      .inner-sidebar-footer {
        border-top: 1px solid #cbd5e0;
        border-bottom: 0;
        height: auto;
        min-height: 3.5rem;
      }
      @media (max-width: 767.98px) {
        .inner-sidebar-body {
                        margin-left: -235px;

        }
        .inner-main-body {
          margin-left: -235px;
        }
        .inner-expand .main-body {
          overflow: hidden;
        }
        .inner-expand .inner-wrapper {
          transform: translate3d(235px, 0, 0);
        }
      }

      .nav .show > .nav-link.nav-link-faded,
      .nav-link.nav-link-faded.active,
      .nav-link.nav-link-faded:active,
      .nav-pills .nav-link.nav-link-faded.active,
      .navbar-nav .show > .nav-link.nav-link-faded {
        color: #3367b5;
        background-color: #c9d8f0;
      }

      .nav-pills .nav-link.active,
      .nav-pills .show > .nav-link {
        color: #fff;
        background-color: #467bcb;
      }
      .nav-link.has-icon {
        display: flex;
        align-items: center;
      }
      .nav-link.active {
        color: #467bcb;
      }
      .nav-pills .nav-link {
        border-radius: 0.25rem;
      }
      .nav-link {
        color: #4a5568;
      }
      .card {
        box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1),
          0 1px 2px 0 rgba(0, 0, 0, 0.06);
      }

      .card {
        position: relative;
        display: flex;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border: 0 solid rgba(0, 0, 0, 0.125);
        border-radius: 0.25rem;
      }

      .card-body {
        flex: 1 1 auto;
        min-height: 1px;
        padding: 1rem;
      }
      #comment-forum{
          justify-content: center;
          align-items: center;
          text-align: center;
          color: #808080;
      }
      #submit-comment{
          width: 950px;
          height: 50px;
      }
      .container{
          margin-top: 15px;
          margin: auto;
      }
     
    </style>
    <%
    int postID = (Integer)request.getAttribute("num");
    Forum forum = new ForumDAO().findPostByID(postID);
    Users user = new UserDAO().findByUserID(forum.getUserID());
    %>
    <div class="container">
      <div class="main-body p-0">
        <div class="inner-wrapper">
          <div class="inner-main">


            <div class="inner-main-body p-2 p-sm-3 collapse forum-content show">
              <!-- bai post -->
              <div class="card mb-2">
                <div class="card-body p-2 p-sm-3">
                  <div class="media forum-item">
                    <a
                      href="user-profiles.jsp"
                      data-target=".forum-content"
                      ><img
                        src=<%=user.getAvatarURL()%>
                        class="mr-3 rounded-circle"
                        width="50"
                        height="50"
                        alt="User"
                    /></a>
                    <div class="media-body">
                      <h6>
                        <a
                          href="user-profiles.jsp"
                          data-target=".forum-content"
                          class="text-body"
                          style="text-decoration: none"
                          ><%=user.getUsername()%></a
                        >
                        <p style="font-style: italic; color: gray; font-size: 12px"><%=forum.getPostDate()%></p></h6>
                        
                        <h3
                          href="#"
                          data-target=".forum-content"
                          class="text-body"
                          style="text-decoration: none; cursor: "
                          ><%=forum.getPostTitle()%></h3>
                      
                      <p class="text-secondary">
                        <%=forum.getPostContext()%>
                      </p>
                      <%
                      if(forum.getPostImg() != null){
                      %>
                      <img src=<%=forum.getPostImg()%> />
                      <%
                          }
                      %>
                    </div>            
                  </div>
                </div>
              </div>
              <!-- ket thuc bai post -->
              <br>
              <h3  id="comment-forum" >Bình luận</h3>
              <br><!-- comment -->
              <!-- phan comment  -->
              <%
                  List<Comments> cmts = new ForumDAO().findAllCommentsByPostID(postID);
                  for(int i = cmts.size() - 1; i >= 0; i--){
                    Comments cmt = cmts.get(i);
                    Users otherUser = new UserDAO().findByUserID(cmt.getUserID());
              %>
              
              <div class="card mb-2" >
                <div class="card-body p-2 p-sm-3">
                  <div class="media forum-item">
                    <a
                      href="user-profiles.jsp"
                      data-target=".forum-content"
                      ><img
                        src=<%=otherUser.getAvatarURL()%>
                        class="mr-3 rounded-circle"
                        width="40"
                        height="40"
                        alt="User"
                    /></a>
                    <div class="media-body">
                      <h6>
                        <a
                          href="user-profiles.jsp"
                          data-target=".forum-content"
                          class="text-body"
                          style="text-decoration: none;"
                          ><%=otherUser.getUsername()%></a
                        >
                        <p style="font-style: italic; color: gray"><%=cmt.getCommentDate()%></p></h6>
                      </h6>
                      <p class="text-body">
                        <%=cmt.getCommentContext()%>
                      </p>
                    </div>
                  </div>
                </div>
              </div>
              <%
                  }
              %>
              <!-- ket thuc phan comment -->
              
              
              <!-- comment cua user hien tai -->
              <%
              Users currentUser = (Users)session.getAttribute("currentUser");
              if(currentUser != null){
                
              %>
              <div class="card mb-2">
                <div class="card-body p-2 p-sm-3">
                  <div class="media forum-item">
                    <a
                      href="#"
                      data-target=".forum-content"
                      ><img
                        src=<%=currentUser.getAvatarURL()%>
                        class="mr-3 rounded-circle"
                        width="50"
                        height="50"
                        alt="User"
                    /></a>
                    <div class="media-body">
                        <form method="POST" action="PostComments">
                            <input id="submit-comment" type="text" name="comment" placeholder="Nhập bình luận"/>
                            <button type="submit" class="btn btn-primary"style="height:50px; position: absolute; padding-left: 20px;">Đăng</button>
                        </form>
                    </div>
                  </div>
                </div>
              </div>
              <%
                  }
              %>
              <!-- ket thuc phan comment cua user hien tai -->

            </div>
              
<!--                          <div class="comment-forum-detail">
              <a
                class="nav-link nav-icon rounded-circle nav-link-faded mr-3 d-md-none"
                href="#"
                data-toggle="inner-sidebar"
                ><i class="material-icons">arrow_forward_ios</i></a
              >
              <form>
                 <span class="input-icon input-icon-sm ml-auto w-auto">
                <input id="submit-comment" type="text" placeholder="Nhập bình luận"/>
                <button type="button" class="btn btn-primary">Đăng</button>
              </form>
              
            </div>-->

          </div>
        </div>
        </div>
      </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
