<jsp:include page="header.jsp"></jsp:include>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script><!--
                                                                                                                                                                                 
    -->
    <script>
        var container = document.getElementById("tagID");
        var tag = container.getElementsByClassName("tag");
        var current = container.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
        tag[1].className += " active";
    </script>
    <style type="text/css">
        body {
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

    <style>
        .chat-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px;
            border: 1px solid #ccc;
        }

        .image-upload {
            margin-right: 10px;
        }

        #submit-comment {
            flex: 1;
            margin-right: 10px;
        }

        .btn-primary {
            height: 50px;
        }
        #image-preview {
            max-width: 400px; /* Adjust max width as needed */
            max-height: 400px; /* Adjust max height as needed */
        }

        #image-preview-wrapper {
            position: relative;
            display: inline-block; /* Ensure it wraps around the image */
        }

        #delete-image {
            position: absolute;
            top: 5px; /* Adjust as needed */
            right: 5px; /* Adjust as needed */
            background: transparent;
            border: none;
            color: white;
            font-size: 18px;
            cursor: pointer;
            display: none; /* Initially hidden */
        }


        #image-preview-wrapper2 {
            position: relative;
            display: inline-block; /* Ensure it wraps around the image */
        }

        #delete-image2 {
            position: absolute;
            top: 5px; /* Adjust as needed */
            right: 5px; /* Adjust as needed */
            background: transparent;
            border: none;
            color: white;
            font-size: 18px;
            cursor: pointer;
            display: none; /* Initially hidden */
        }
    </style>

    <style>
        .dropbtn {
            color: black;
            padding: 16px;
            font-size: 16px;
            background-color: transparent;
            border: none;
            cursor: pointer;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: transparent;
            min-width: 160px;
            overflow: auto;
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 7px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown a:hover {
            background-color: #ddd;
        }
        button.btn.btn-xoa:hover{
            background-color: #ddd;
        }

        .show {
            display: block;
        }
    </style>


<%
int postID = (Integer)session.getAttribute("postID");
Forum forum = new ForumDAO().findPostByID(postID);
Users user = new UserDAO().findByUserID(forum.getUserID());
%>
<div class="container">
    <div class="main-body p-0">
        <div class="">
            <div class="">


                <div class=" p-2 p-sm-3 forum-content">
                    <!-- bai post -->
                    <div class="card mb-2">
                        <div class="card-body p-2 p-sm-3">
                            <div class="media forum-item">
                                <%
                                            boolean check = false;
                                            if(session.getAttribute("currentUser") != null){
                                                Users realUser = (Users)session.getAttribute("currentUser");
                                                if(realUser.getUserID() == user.getUserID()){
                                                    check = true;
                                                }
                                            }
                                                if(check == true){
                                %>
                                <a
                                    href="profile.jsp"
                                    data-target=".forum-content"
                                    ><img
                                        src="<%=user.getAvatarURL()%>"
                                        class="mr-3 rounded-circle"
                                        width="50"
                                        height="50"
                                        alt="User"
                                        /></a>
                                <div class="media-body">
                                    <h6>
                                        <a
                                            href="profile.jsp"
                                            data-target=".forum-content"
                                            class="text-body"
                                            style="text-decoration: none"
                                            ><%=user.getUsername()%></a>
                                        <p style="font-style: italic; color: gray; font-size: 12px"><%=forum.getPostDate()%></p></h6>
                                        <%
                                            }
                                        else{
                                        %>
                                    <a
                                        href="UserProfile?userID=<%=forum.getUserID()%>"
                                        data-target=".forum-content"
                                        ><img
                                            src="<%=user.getAvatarURL()%>"
                                            class="mr-3 rounded-circle"
                                            width="50"
                                            height="50"
                                            alt="User"
                                            /></a>
                                    <div class="media-body">
                                        <h6>
                                            <a
                                                href="UserProfile?userID=<%=forum.getUserID()%>"
                                                data-target=".forum-content"
                                                class="text-body"
                                                style="text-decoration: none"
                                                ><%=user.getUsername()%></a>
                                            <p style="font-style: italic; color: gray; font-size: 12px"><%=forum.getPostDate()%></p></h6>
                                            <%
                                                }
                                            %>
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
                                        <img src="<%=forum.getPostImg()%>" height="300px" width="700px"/>
                                        <%
                                            }
                                        %>
                                    </div>            
                                </div>
                            </div>
                        </div>
                        <!-- ket thuc bai post -->
                        <br>
                        <h3  id="comment-forum" >Bình lu?n</h3>
                        <br><!-- comment -->
                        <!-- phan comment  -->
                        <%
                            List<Comments> cmts = new ForumDAO().findAllCommentsByPostID(postID);
                            for(int i = cmts.size() - 1; i >= 0; i--){
                              check = false;
                              Comments cmt = cmts.get(i);
                              String img = cmt.getCommentURL();
                              String modalId = "threadModal" + i;
                              String EditModalId = "threadModalE" + i;
                              Users otherUser = new UserDAO().findByUserID(cmt.getUserID());
                              if(session.getAttribute("currentUser") != null){
                                    Users realUser = (Users)session.getAttribute("currentUser");
                                    if(realUser.getUserID() == cmt.getUserID()){
                                        check = true;
                                    }
                                }
                        %>

                        <div class="card mb-2" >
                            <div class="card-body p-2 p-sm-3">
                                <%
                                    if(check == true){
                                %>
                                <div class="media forum-item" style="float: right">
                                    <div class="dropdown">
                                        <button onclick="myFunction('<%=cmt.getCommentID()%>')" class="dropbtn">...</button>
                                        <div id="myDropdown<%=cmt.getCommentID()%>" class="dropdown-content">
                                            <button 
                                                class="btn btn-xoa" 
                                                href="#home" 
                                                type="button"
                                                data-toggle="modal"
                                                data-target="#<%=EditModalId%>"
                                                >
                                                S?a</button>
                                            <br>
                                            <button
                                                class="btn btn-xoa"
                                                type="button"
                                                data-toggle="modal"
                                                data-target="#<%= modalId %>"  
                                                >
                                                Xoá
                                            </button>
                                        </div>
                                    </div>
                                    <!-- modal for delete -->
                                    <div class="modal fade" id="<%= modalId %>" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content" style="width: 500px; margin: auto">
                                                <form action="DeleteComment" method="POST">
                                                    <input type="hidden" name="commentID" value="<%=cmt.getCommentID()%>">
                                                    <input type="hidden" name="postID" value="<%=postID%>">
                                                    <div class="modal-header d-flex align-items-center bg-primary text-white">
                                                        <h6 class="modal-title mb-0" id="threadModalLabel">Xác nh?n xóa bình lu?n?</h6>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="form-group">                       
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-light" data-dismiss="modal" >H?y</button>
                                                                <input type="submit" class="btn btn-primary" style="background-color: red" value="Xoá bình lu?n"/>
                                                            </div>
                                                        </div> 
                                                    </div>
                                                </form>
                                            </div> 
                                        </div>                        
                                    </div>

                                    <!-- modal for edit -->

                                    <div
                                        class="modal fade"
                                        id="<%=EditModalId%>"
                                        tabindex="-1"
                                        role="dialog"
                                        aria-labelledby="threadModalELabel"
                                        aria-hidden="true"
                                        >
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <form action="UpdateComment" method="POST" enctype="multipart/form-data">
                                                    <div class="modal-header d-flex align-items-center bg-primary text-white">
                                                        <h6 class="modal-title mb-0" id="threadModalLabel">
                                                            S?a bình lu?n
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
                                                            <div class="form-group">
                                                                <label for="thread-detail">Chi ti?t</label>
                                                                <textarea
                                                                    type="text"
                                                                    class="form-control"
                                                                    name="context"
                                                                    id="threadTitle"
                                                                    placeholder="Chi ti?t"
                                                                    required
                                                                    autofocus
                                                                    rows="5" 
                                                                    style="resize: none; overflow: hidden;"
                                                                    ><%=cmt.getCommentContext()%></textarea>
                                                            </div>
                                                            <!--                    <label for="thread-image">?nh</label>
                                                                                <input type="file" name="file" id="imgupload" accept="image/png, image/jpeg" style="display:none" onchange="submitForm()"/>-->
                                                            <div id="image-preview-container">
                                                                <label for="myfile">Ch?n ?nh:</label>
                                                                <input id="image-upload" type="file" name="image" accept="image/*">
                                                                <br>
                                                                <div id="image-preview-wrapper" style="position: relative;">
                                                                    <img id="image-preview" src="<%=cmt.getCommentURL()%>" alt="Ch?a có ?nh nào ???c ch?n">
                                                                    <%if(cmt.getCommentURL() != null){%>
                                                                    <button id="delete-image" style="display: inline-block"><i class="fa fa-times"></i></button>
                                                                        <%
                                                                            }
                                                                        else if(cmt.getCommentURL() == null){
                                                                        %>
                                                                    <button id="delete-image" style="display: none"><i class="fa fa-times"></i></button>
                                                                        <%
                                                                            }
                                                                        %>
                                                                </div>

                                                            </div>
                                                            <br>
                                                            <br><br>
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
                                                            <button onclick="removeURL('<%=img%>')"
                                                                    type="button"
                                                                    class="btn btn-light"
                                                                    data-dismiss="modal"
                                                                    >
                                                                H?y
                                                            </button>
                                                            <input type="hidden" name="postID" value="<%=postID%>"/>
                                                            <input type="hidden" name="commentID" value="<%=cmt.getCommentID()%>"/>
                                                            <input type="hidden" id="imgURL" name="imgURL" value="<%=cmt.getCommentURL()%>"/>
                                                            <input type="submit" class="btn btn-primary" value="C?p nh?t"/>
                                                        </div>

                                                    </div> 
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>  
                                <div class="media forum-item">
                                    <a
                                        href="profile.jsp"
                                        data-target=".forum-content"
                                        ><img
                                            src="<%=otherUser.getAvatarURL()%>"
                                            class="mr-3 rounded-circle"
                                            width="40"
                                            height="40"
                                            alt="User"
                                            /></a>
                                    <div class="media-body">
                                        <h6>
                                            <a
                                                href="profile.jsp"
                                                data-target=".forum-content"
                                                class="text-body"
                                                style="text-decoration: none;"
                                                ><%=otherUser.getUsername()%></a
                                            >

                                            <p style="font-style: italic; color: gray; font-size: 12px"><%=cmt.getCommentDate()%></p>
                                        </h6>
                                        <p class="text-body">
                                            <%=cmt.getCommentContext()%>
                                        </p>
                                        <%
                                                if(cmt.getCommentURL() != null){
                                        %>
                                        <img src="<%=cmt.getCommentURL()%>" width="700" height="400"/>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                                <div class="ml-auto">
                                    <div class="media-body">
                                        <%
                                            }
                                        else{
                                        %>
                                        <div class="media forum-item">
                                            <a
                                                href="UserProfile?userID=<%=cmt.getUserID()%>"
                                                data-target=".forum-content"
                                                ><img
                                                    src="<%=otherUser.getAvatarURL()%>"
                                                    class="mr-3 rounded-circle"
                                                    width="40"
                                                    height="40"
                                                    alt="User"
                                                    /></a>
                                            <div class="media-body">
                                                <h6>
                                                    <a
                                                        href="UserProfile?userID=<%=cmt.getUserID()%>"
                                                        data-target=".forum-content"
                                                        class="text-body"
                                                        style="text-decoration: none;"
                                                        ><%=otherUser.getUsername()%></a
                                                    >
                                                    <p style="font-style: italic; color: gray; font-size: 12px"><%=cmt.getCommentDate()%></p>
                                                </h6>
                                                <p class="text-body">
                                                    <%=cmt.getCommentContext()%>
                                                </p>
                                                <%
                                                if(cmt.getCommentURL() != null){
                                                %>
                                                <img src="<%=cmt.getCommentURL()%>" width="700" height="400"/>
                                                <%
                                                    }
                                                %>
                                                <%
                                                    }
                                                %>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%
                                    }
                                %>
                                <%
                                if(cmts.size() == 0){
                                %>
                                <h5  id="comment-forum" >Ch?a có bình lu?n nào</h5>
                                <%
                                    }
                                %>
                                <!-- ket thuc phan comment -->


                                <!-- comment cua user hien tai -->
                                <%
                                Users currentUser = (Users)session.getAttribute("currentUser");
                                if(currentUser != null){
                
                                %>
                                <div class="card mb-2" style="position: sticky; bottom: 0px">
                                    <div class="card-body p-2 p-sm-3">
                                        <div class="media forum-item">
                                            <a
                                                href="#"
                                                data-target=".forum-content"
                                                ><img
                                                    src="<%=currentUser.getAvatarURL()%>"
                                                    class="mr-3 rounded-circle"
                                                    width="50"
                                                    height="50"
                                                    alt="User"
                                                    /></a>
                                            <div class="card mb-2">
                                                <form method="POST" action="PostComments" enctype="multipart/form-data">
                                                    <div class="chat-container">
                                                        <label for="image-upload2" class="custom-file-upload">
                                                            <i class="fa fa-image" style="cursor: pointer;"></i>
                                                        </label>
                                                        <input id="image-upload2" type="file" name="image" accept="image/*" style="display:none;">

                                                        <textarea id="submit-comment" required name="comment" placeholder="Nh?p bình lu?n" rows="1" style="resize: none; overflow: hidden;"></textarea>
                                                        <button type="submit" class="btn btn-primary">
                                                            <i class="fa fa-paper-plane"></i>
                                                        </button>
                                                        <br>
                                                    </div>
                                                    <div id="image-preview-wrapper2" style="position: relative;">
                                                        <img id="image-preview2" src="#" alt="Ch?a có ?nh nào ???c ch?n" width="400px" height="200px" style="display: none;">
                                                        <button id="delete-image2" style="display: none"><i class="fa fa-times"></i></button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <%
                                        }
                                    %>
                                    <!-- ket thuc phan comment cua user hien tai -->

                                </div>
                            </div>

                        </div>

                    </div>
                </div>    
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<script>
    var textarea = document.getElementById("submit-comment");
    textarea.addEventListener("input", function () {
        this.style.height = "auto";
        this.style.height = (this.scrollHeight) + "px";
    });
    function removeURL(url) {
        var imgElement = document.getElementById('image-preview');
        imgElement.src = url; // Clear the preview
        document.getElementById('image-upload').value = '';
    }
</script>



<script>
    /* When the user clicks on the button, 
     toggle between hiding and showing the dropdown content */
    function myFunction(commentID) {
        var dropdown = document.getElementById("myDropdown" + commentID);
        dropdown.classList.toggle("show");
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
    }
</script>

<script>
    // Function to handle file input change event
    document.getElementById('image-upload').addEventListener('change', function (event) {
        var file = event.target.files[0];
        var reader = new FileReader();

        reader.onload = function (e) {
            var imgElement = document.getElementById('image-preview');
            imgElement.src = e.target.result;
            imgElement.style.display = 'block';

            // Show delete button
            document.getElementById('delete-image').style.display = 'inline-block';
        }

        reader.readAsDataURL(file);
    });

    // Function to handle delete image button click
    document.getElementById('delete-image').addEventListener('click', function (event) {
        event.preventDefault(); // Prevent default behavior (page reload)

        var imgElement = document.getElementById('image-preview');
        imgElement.src = ''; // Clear the preview
        imgElement.style.display = 'none';

        // Hide delete button
        document.getElementById('delete-image').style.display = 'none';

        // Reset file input
        document.getElementById('image-upload').value = '';
        document.getElementById('imgURL').value = '';
    });

    document.addEventListener("DOMContentLoaded", function (event) {
        var scrollpos = localStorage.getItem('scrollpos');
        if (scrollpos)
            window.scrollTo(0, scrollpos);
    });

    window.onbeforeunload = function (e) {
        localStorage.setItem('scrollpos', window.scrollY);
    };

</script>

<script>
    document.getElementById('image-upload2').addEventListener('change', function (event) {
        var file = event.target.files[0];
        var reader = new FileReader();

        reader.onload = function (e) {
            var imgElement = document.getElementById('image-preview2');
            imgElement.src = e.target.result;
            imgElement.style.display = 'block'; // Show the image preview
            document.getElementById('delete-image2').style.display = 'inline-block'; // Show the delete button
        };

        if (file) {
            reader.readAsDataURL(file); // Read the uploaded file as a data URL
        }
    });

    document.getElementById('delete-image2').addEventListener('click', function (event) {
        event.preventDefault(); // Prevent the default button behavior (page reload)

        var imgElement = document.getElementById('image-preview2');
        imgElement.src = '#'; // Clear the image preview
        imgElement.style.display = 'none'; // Hide the image preview
        document.getElementById('delete-image2').style.display = 'none'; // Hide the delete button

        // Clear the file input
        document.getElementById('image-upload2').value = '';
    });
</script>
>>>>>>> origin/master
