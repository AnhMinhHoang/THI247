<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<jsp:include page="header.jsp"></jsp:include>

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
                                                <th>Thời gian</th>
                                                <th>Nhiệm vụ</th>
                                                <th>Độ ưu tiên</th>
                                                <th>Trạng thái</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <input type="date" >
                                                </td>
                                                <td>9958</td>
                                                <td>Curicó</td>
                                                <td>2005/02/11</td>
                                            </tr>
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
                                                    <form action="NewPost" method="POST" enctype="multipart/form-data">
                                                        <div class="modal-header d-flex align-items-center bg-primary text-white">
                                                            <h6 class="modal-title mb-0" id="threadModalLabel">
                                                                Bài đăng mới
                                                            </h6>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="form-group">
                                                                <label for="threadTitle">Thời gian</label>
                                                                <input
                                                                    type="date"
                                                                    class="form-control"
                                                                    id="threadTitle"
                                                                    name="schedule_date"
                                                                    placeholder="Nhập thời gian"
                                                                    required    
                                                                    autofocus
                                                                    />
                                                                <br>
                                                                <div class="form-group">
                                                                    <label for="thread-detail">Nhiệm vụ</label>
                                                                    <input
                                                                        type="text"
                                                                        class="form-control"
                                                                        name="task"
                                                                        placeholder="Nhiệm vụ"
                                                                        required
                                                                        autofocus
                                                                    />                                                             
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="thread-detail">Độ ưu tiên</label>
                                                                    <input
                                                                        type=""
                                                                        class="form-control"
                                                                        name="task"
                                                                    />                                                             
                                                                </div>
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
                                    </div>



                                    <span>Bạn có thể thay đổi tên của thời khóa biểu ở </span><a href="#"><span>đây</span></a>
                                </div>
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

<jsp:include page="footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"></script>
