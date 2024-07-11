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
    </style>   
<%
int id = (Integer)session.getAttribute("userID");
Users user = new UserDAO().findByUserID(id);
Users currentUser = (Users)session.getAttribute("currentUser");
TeacherRequest requests = new AdminDAO().getRequestByUserID(id);
Subjects subject = new Subjects();
if(requests != null){
    subject = new ExamDAO().getSubjectByID(requests.getSubjectID());
}
String role;
if(user.getRole() == 1) role = "Admin";
else if(user.getRole() == 2) role = "Giáo viên";
else role = "Học sinh";
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
                                
                                <%
                                if(user.getRole() == 2 || user.getRole() == 3 && requests != null && currentUser.getRole() == 1){
                                %>
                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Trình độ học vấn</div>
                                    <div class="col-lg-9 col-md-8"><%=requests.getAcademicLevel()%></div>
                                </div>
                                
                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Kinh nghiệm làm việc</div>
                                    <div class="col-lg-9 col-md-8"><%=requests.getExperience()%> năm</div>
                                </div>
                                
                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Đã từng/ Đang công tác tại trường</div>
                                    <div class="col-lg-9 col-md-8"><%=requests.getSchool()%></div>
                                </div>
                                <%
                                    }
                                %>
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
                <!-- start of modal -->
                <button
                    class="btn btn-primary has-icon btn-block"
                    type="button"
                    data-toggle="modal"
                    data-target="#Report"
                    >Report</button>
                <div
                    class="modal fade"
                    id="Report"
                    tabindex="-1"
                    role="dialog"
                    aria-labelledby="threadModalLabel"
                    aria-hidden="true"
                    >
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <form action="NewReport" id="reportForm" method="POST" enctype="multipart/form-data">
                                <input type="hidden" name="link" value="user-profiles.jsp"/>
                                <input type="hidden" name="otherUserID" value="<%=id%>"/>
                                <div class="modal-header d-flex align-items-center bg-primary text-white">
                                    <h6 class="modal-title mb-0" id="threadModalLabel">
                                        Report
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
                                        <label for="threadTitle">Lý do báo cáo</label>
                                        <div class="checkbox-group" style="
                                             display: flex;
                                             flex-wrap: wrap;
                                             gap: 10px; /* Khoảng cách giữa các checkbox */
                                             margin-top: 20px;">
                                            <label class="checkbox-container" style="width: 30%;">
                                                <input type="checkbox" name="reasons" value="1"/>
                                                <span class="checkmark"></span>
                                                Lạm dụng ngôn từ
                                            </label>
                                            <label class="checkbox-container" style="width: 30%;">
                                                <input type="checkbox" name="reasons" value="2"/>
                                                <span class="checkmark"></span>
                                                Hành vi gây rối diễn đàn
                                            </label>
                                            <label class="checkbox-container" style="width: 30%;">
                                                <input type="checkbox" name="reasons" value="3"/>
                                                <span class="checkmark"></span>
                                                Tạo bài đăng sai mục đích 
                                            </label>
                                            <label class="checkbox-container" style="width: 30%;">
                                                <input type="checkbox" name="reasons" value="4"/>
                                                <span class="checkmark"></span>
                                                Bài đăng Không liên quan 
                                            </label>
                                            <label class="checkbox-container" style="width: 30%;">
                                                <input type="checkbox" name="reasons" value="5"/>
                                                <span class="checkmark"></span>
                                                Spam Bình luận quảng cáo trong diễn đàn 
                                            </label>
                                            <label class="checkbox-container" style="width: 30%;">
                                                <input type="checkbox" name="reasons" value="6"/>
                                                <span class="checkmark"></span>
                                                Bình luận mang tính phản cảm
                                            </label>
                                            <label class="checkbox-container" style="width: 30%;">
                                                <input type="checkbox" name="reasons" value="7" class="reason-checkbox"/>
                                                <span class="checkmark"></span>
                                                lý do báo cáo khác
                                            </label>
                                        </div>
                                        <br>
                                        <div class="form-group" id="details-container" style="display: none;">
                                            <label for="thread-detail">Chi tiết</label>
                                            <textarea
                                                type="text"
                                                class="form-control"
                                                name="context"
                                                id="thread-detail"
                                                placeholder="Chi tiết"
                                                rows="5"
                                                style="resize: none; overflow: hidden;"></textarea>
                                        </div>
                                        <div id="image-preview-container">
                                            <label for="myfile">Chọn ảnh:</label>
                                            <input id="image-upload" type="file" name="image" accept="image/*">
                                            <br>
                                            <div id="image-preview-wrapper" style="position: relative;">
                                                <img id="image-preview" src="#" width="400" height="400" alt="Preview Image" style="display:none;">
                                                <button id="delete-image"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>
                                        <br>
                                        <br><br>
                                        <textarea class="form-control summernote" style="display: none"></textarea>
                                        <div class="custom-file form-control-sm mt-3" style="max-width: 300px"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <button onclick="removeURL(this)"
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
                <!-- End of modal -->
            </div>
        </div>
    </div>
</section>

</main><!-- End #main -->
<!-- required!!!! modal -->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"></script>
<script>
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

                                            document.getElementById('delete-image').addEventListener('click', function (event) {
                                                event.preventDefault(); // Prevent default behavior (page reload)

                                                var imgElement = document.getElementById('image-preview');
                                                imgElement.src = '#'; // Clear the preview
                                                imgElement.style.display = 'none';

                                                // Hide delete button
                                                document.getElementById('delete-image').style.display = 'none';

                                                // Reset file input
                                                document.getElementById('image-upload').value = '';
                                            });

                                            function removeURL() {
                                                var imgElement = document.getElementById('image-preview');
                                                imgElement.src = '#'; // Clear the preview
                                                imgElement.style.display = 'none';
                                                document.getElementById('image-upload').value = '';
                                            }

                                            // Function to show/hide the "Chi tiết" textarea
                                            document.querySelectorAll('.reason-checkbox').forEach(checkbox => {
                                                checkbox.addEventListener('change', () => {
                                                    const detailsContainer = document.getElementById('details-container');
                                                    const anyChecked = Array.from(document.querySelectorAll('.reason-checkbox')).some(cb => cb.checked);
                                                    detailsContainer.style.display = anyChecked ? 'block' : 'none';

                                                    // Toggle required attribute
                                                    document.getElementById('thread-detail').required = anyChecked;
                                                });
                                            });
                                            document.getElementById('reportForm').addEventListener('submit', function (event) {
                                                var checkboxes = document.querySelectorAll('input[name="reasons"]');
                                                var isChecked = false;

                                                for (var i = 0; i < checkboxes.length; i++) {
                                                    if (checkboxes[i].checked) {
                                                        isChecked = true;
                                                        break;
                                                    }
                                                }

                                                if (!isChecked) {
                                                    alert('Vui lòng chọn ít nhất một lý do báo cáo.');
                                                    event.preventDefault(); // Ngăn chặn việc nộp biểu mẫu
                                                }
                                            });
</script>
<jsp:include page="footer.jsp"></jsp:include>

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
