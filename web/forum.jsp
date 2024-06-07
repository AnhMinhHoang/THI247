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
        .inner-sidebar {
          left: -235px;
        }
        .inner-main {
          left: 0;
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
      .container{
          margin-top: 15px;
      }
    </style>
  </head>
  <body>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css"
      integrity="sha256-46r060N2LrChLLb5zowXQ72/iKKNiw/lAmygmHExk/o="
      crossorigin="anonymous"
    />
       
    <div class="container">
      <div class="main-body p-0">
        <div class="inner-wrapper">
          <div class="inner-sidebar">
            <div class="inner-sidebar-header justify-content-center">
              <button
                class="btn btn-primary has-icon btn-block"
                type="button"
                data-toggle="modal"
                data-target="#threadModal"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  class="feather feather-plus mr-2"
                >
                  <line x1="12" y1="5" x2="12" y2="19"></line>
                  <line x1="5" y1="12" x2="19" y2="12"></line>
                </svg>
                Tạo bài đăng
              </button>
            </div>

            <div class="inner-sidebar-body p-0">
              <div class="p-3 h-100" data-simplebar="init">
                <div class="simplebar-wrapper" style="margin: -16px">
                  <div class="simplebar-height-auto-observer-wrapper">
                    <div class="simplebar-height-auto-observer"></div>
                  </div>
                  <div class="simplebar-mask">
                    <div
                      class="simplebar-offset"
                      style="right: 0px; bottom: 0px"
                    >
                      <div
                        class="simplebar-content-wrapper"
                        style="height: 100%; overflow-y: hidden;"
                      >
                   
                        <div class="simplebar-content" style="padding: 16px">
                          <nav class="nav nav-pills nav-gap-y-1 flex-column">
                            <a
                              href="bs4_forum.html"
                              class="nav-link nav-link-faded has-icon active"
                              >Tất cả bài đăng</a
                            >
                          </nav>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div
                  class="simplebar-track simplebar-horizontal"
                  style="visibility: hidden"
                >
                  <div
                    class="simplebar-scrollbar"
                    style="width: 0px; display: none"
                  ></div>
                </div>
                <div
                  class="simplebar-track simplebar-vertical"
                  style="visibility: visible"
                >
                  <div
                    class="simplebar-scrollbar"
                    style="
                      height: 151px;
                      display: block;
                      transform: translate3d(0px, 0px, 0px);
                    "
                  ></div>
                </div>
              </div>
            </div>
          </div>

          <div class="inner-main">
            <div class="inner-main-header">
              <a
                class="nav-link nav-icon rounded-circle nav-link-faded mr-3 d-md-none"
                href="#"
                data-toggle="inner-sidebar"
                ><i class="material-icons">arrow_forward_ios</i></a
              >
              
              <span class="input-icon input-icon-sm ml-auto w-auto">
                <input
                  type="text"
                  class="form-control form-control-sm bg-gray-200 border-gray-200 shadow-none mb-4 mt-4"
                  placeholder="Search forum"
                />
              </span>
            </div>

            <div class="inner-main-body p-2 p-sm-3 collapse forum-content show">
              <!-- bai post -->
              <%
                  List<Forum> forums = new ForumDAO().getAllPost();
                  for (int i = forums.size() - 1; i >= 0; i--) {
                    Forum forum = forums.get(i);
                    Users user = new UserDAO().findByUserID(forum.getUserID());
                    String str;
                    if(forum.getPostContext().length() > 200){ 
                        str = forum.getPostContext().substring(1, 200) + "...";
                    }
                    else {
                        str = forum.getPostContext();
                    }
              %>
              <div class="card mb-2">
                <div class="card-body p-2 p-sm-3">
                  <div class="media forum-item">
                    <a
                      href="user-profile.jsp"
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
                        <p style="font-style: italic; color: gray; font-size: 12px"><%=forum.getPostDate()%></p>
                        </h6>
                        <h4>
                        <a
                          href="ForumDetail?postID=<%=forum.getPostID()%>"
                          data-target=".forum-content"
                          class="text-body"
                          ><%=forum.getPostTitle()%></a
                        >
                        </h4>
                        <a href="ForumDetail?postID=<%=forum.getPostID()%>" style="text-decoration: none">
                      <p class="text-secondary">
                        <%=str%>
                      </p>
                        </a>
                      
                    </div>
<!--                    <div class="text-muted small text-center align-self-center">
                      <span class="d-none d-sm-inline-block"
                        ><i class="far fa-eye"></i> 19</span
                      >
                      <span><i class="far fa-comment ml-2"></i> 3</span>
                    </div>-->
                  </div>
                </div>
              </div>              <!-- ket thuc bai post -->
            <%
                }
            %>
            </div>
            
          </div>
        </div>
        <div
          class="modal fade"
          id="threadModal"
          tabindex="-1"
          role="dialog"
          aria-labelledby="threadModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <form action="NewPost" method="POST" enctype="multipart/form-data">
                <div class="modal-header d-flex align-items-center bg-primary text-white">
                  <h6 class="modal-title mb-0" id="threadModalLabel">
                    Bài đăng mới
                  </h6>
                  <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-label="Close"
                  >
                    <span aria-hidden="true">×</span>
                  </button>
                </div>
                <div class="modal-body">
                  <div class="form-group">
                    <label for="threadTitle">Tiêu đề</label>
                    <input
                      type="text"
                      class="form-control"
                      id="threadTitle"
                      name="title"
                      placeholder="Tiêu đề"
                      required
                      autofocus
                    />
                    <br>
                    <div class="form-group">
                    <label for="thread-detail">Chi tiết</label>
                    <input
                      type="text"
                      class="form-control"
                      name="context"
                      id="threadTitle"
                      placeholder="Chi tiết"
                      required
                      autofocus
                    />
                  </div>
<!--                    <label for="thread-image">Ảnh</label>
                    <input type="file" name="file" id="imgupload" accept="image/png, image/jpeg" style="display:none" onchange="submitForm()"/>-->
                    <label for="myfile">Select a file:</label>
                    <input type="file" id="myfile" name="file"><br><br>
                  <textarea
                    class="form-control summernote"
                    style="display: none"
                  ></textarea>
                  <div
                    class="custom-file form-control-sm mt-3"
                    style="max-width: 300px"
                  >
                </div>
                </div>
                <div class="modal-footer">
                  <button
                    type="button"
                    class="btn btn-light"
                    data-dismiss="modal"
                  >
                    Hủy
                  </button>
                    <input type="submit" class="btn btn-primary" value="Đăng"/>
                </div>
              
            </div> 
            </form>
          </div>
        </div>
      </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript"></script>
