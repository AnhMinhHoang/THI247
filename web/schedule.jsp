<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*, Schedule.*"%>
<jsp:include page="header.jsp"></jsp:include>
    <script>
        var container = document.getElementById("tagID");
        var tag = container.getElementsByClassName("tag");
        var current = container.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
        tag[3].className += " active";
    </script>
<%
if(session.getAttribute("currentUser") != null){
    Users user = (Users)session.getAttribute("currentUser");
    List<Task> listTask = new TaskDAO().getTasksByUser(user.getUserID());
%>
<body>
    <div class="container">       
        <main id="main" class="main">
            <div class="pagetitle" style="margin-top: 50px">
                <h1>To-do List</h1>   
            </div>
            <section class="section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Hãy lên một lịch trình học tập và làm việc thật khoa học nhé !</h4>
                                <table class="table datatable">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nhiệm vụ</th>
                                            <th>Thời gian</th>
                                            <th>Tác vụ</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                        if(listTask.size() > 0){
                                        for(int i = listTask.size() - 1; i >= 0; i--){
                                            Task task = listTask.get(i);
                                            String modalId = "threadModal" + i;
                                        %>
                                        <tr>
                                            <td><%=task.getTaskId()%></td>
                                            <td><%=task.getTaskContext()%></td>
                                            <td><%=task.getTaskDeadline()%></td>
                                            <td>
                                                <button
                                                    class="btn btn-danger"
                                                    type="button"
                                                    data-toggle="modal"
                                                    data-target="#<%= modalId %>"  
                                                    >
                                                    Xoá Lịch
                                                </button>
                                                <div class="modal fade" id="<%= modalId %>" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content" style="width: 500px; margin: auto">
                                                            <form action="deleteTask" method="POST">
                                                                <input type="hidden" name="taskId" value="<%=task.getTaskId()%>">
                                                                <div class="modal-header d-flex align-items-center bg-primary text-white">
                                                                    <h6 class="modal-title mb-0" id="threadModalLabel">Xác nhận xóa lịch trình?</h6>
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
                                        }else{
                                        %>
                                    <td>Bạn chưa có lịch trình nào!</td>
                                    <%
                                        }
                                    %>
                                    </tbody>
                                </table>


                                <div>

                                    <div class="inner-sidebar-header justify-content-center">
                                        <button class="btn btn-primary has-icon btn-block" type="button" data-toggle="modal" data-target="#threadModal">Thêm</button>
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
                                                <form action="createTask" method="post">
                                                    <div class="modal-header d-flex align-items-center bg-primary text-white">
                                                        <h6 class="modal-title mb-0" id="threadModalLabel">
                                                            Thêm Task
                                                        </h6>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="form-group">
                                                            <label for="taskContext">Task Context:</label>
                                                            <input type="text" id="taskContext" name="taskContext" required /><br/>

                                                            <label for="taskDate">Task Date (YYYY-MM-DD):</label>
                                                            <input type="date" id="taskDate" name="taskDate" required /><br/>

                                                            <label for="taskTime">Task Time (HH:MM):</label>
                                                            <input type="time" id="taskTime" name="taskTime" required /><br/>

                                                            <input class="btn button-primary" type="submit" value="Thêm task" />
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div></div>
                        </div>
                    </div>
                </div>
            </section>
        </main><!-- End #main -->    
    </div>
    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

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

</body>
<%
    }
%>
<jsp:include page="footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"></script>
