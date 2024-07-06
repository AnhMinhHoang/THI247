<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<jsp:include page="header.jsp"></jsp:include>
    <br>
<%
Users user = (Users)session.getAttribute("currentUser");
TeacherRequest requests = new AdminDAO().getRequestByUserID(user.getUserID());
Subjects subject = new ExamDAO().getSubjectByID(8);
%>
<div class="row" style="border-radius: 10px">
    <div class="col-lg-12" style="width: 1000px; margin: auto;">
        <a class="btn btn-primary" style="background-color: " href="choosesubject.jsp">Trở về</a>
        <br><br>
        <div class="card">
            <div class="card-body">
                <h4 class="text-primary">Tạo bài kiểm tra</h4>

                <%
                if(requests == null){
                %>
                <form class="row g-3" method="POST" action="AddTeacherRequest">
                    <div class="col-md-12">
                        <label class="form-label">Tên bài kiểm tra</label>
                        <input type="number" class="form-control" id="validationDefault01" name="exp" required>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label">Giá tiền bài kiểm tra</label>
                        <select class="form-select" id="validationDefault04" name="subject" required>
                            <option selected disabled>Choose...</option>
                            <option value="0">Miễn phí</option>
                            <option value="10">10 coin</option>
                            <option value="20">20 coin</option>
                            <option value="30">30 coin</option>                   
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label class="form-label">Thời gian</label>
                        <input type="number" class="form-control" id="validationDefault03" name="school" placeholder="Giờ" required>     
                        <input type="number" class="form-control" id="validationDefault03" name="school" placeholder="Phút" required>                      

                    </div>
                    <h3 class="text-primary">Chọn câu hỏi:</h3>
                    <div class="question-list">
                        <%
                        List<QuestionBank> qblist = new ExamDAO().getAllQuestionByID(6, 1);
                        if(qblist.size() > 0){
                        for(int i = qblist.size() - 1; i >= 0; i--){
                            QuestionBank qb = qblist.get(i);
                        %>
                        <label>
                            <input type="checkbox" name="selectedQuestions" value="<%=qb.getQuestionId()%>">
                            <span class="question-label"><%=qb.getQuestionContext()%></span>
                        </label><br>
                        <%
                            }   
                        }
                        else{
                        %>
                        <p>Không có câu hỏi nào trong ngân hàng câu hỏi.</p>
                        <%
                            }
                        %>
                    </div>
                    <button
                        class="btn btn-primary has-icon btn-block"
                        type="button"
                        data-toggle="modal"
                        data-target="#threadModal"
                        >
                        Tạo đề ngẫu nhiên
                    </button>
                    <input type="hidden" name="subjectID" value="temporary">
                    <button class="btn btn-primary has-icon btn-block" type="submit">Create Exam</button>
                </form>

                <div class="modal fade" id="threadModal" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content" style="width: 500px; margin: auto">
                            <form action="CreateRandomExam" method="POST">
                                <div class="modal-header d-flex align-items-center bg-primary text-white">
                                    <h6 class="modal-title mb-0" id="threadModalLabel">Tạo đề <%=subject.getSubjectName()%> ngẫu nhiên</h6>
                                </div>
                                <div class="modal-body">
                                    <%
                                    if(qblist.size() > 0){
                                        int max = qblist.size();
                                    %>
                                    <label for="examName">Tên đề thi:</label>
                                    <input type="text" id="examName" name="examName" required>
                                    <input type="hidden" name="subjectID" value="<%=6%>"/>
                                    <div style="display: flex; align-items: center;">
                                        <label for="examName" style="margin-right: 10px; margin-top: 20px">Tổng thời gian làm bài:</label>
                                        <div style="display: flex; align-items: center;">
                                            <div style="display: flex; flex-direction: column; align-items: center; margin-right: 10px;">
                                                <label for="examMinutes" style="text-align: center;">Giờ</label>
                                                <input type="number" name="examHours" min="0" required style="width: 50px; text-align: center;">
                                            </div>
                                            <div style="display: flex; flex-direction: column; align-items: center;">
                                                <label for="examSeconds" style="text-align: center;">Phút</label>
                                                <input type="number" name="examMinutes" min="1" required style="width: 50px; text-align: center;">
                                            </div>
                                        </div>
                                    </div>
                                    <label for="price">Giá bài kiểm tra(0-20):</label>
                                    <input type="number" max="20" min="0" id="price" name="price" value="0" required>
                                    <label for="numQuestions">Số lượng câu hỏi(1-<%=max%>):</label>
                                    <input type="number" id="numQuestions" name="numQuestions" min="1" max="<%=max%>" value="1">
                                    <%
                                        }
                                    else{
                                    %>
                                    <p>Không còn câu hỏi nào trong ngân hàng câu hỏi!</p>
                                    <%
                                        }
                                    %>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-light" data-dismiss="modal" style="background-color: red" value="Huỷ">
                                    <%
                                    if(qblist.size() > 0){
                                    %>
                                    <input type="submit" class="btn btn-primary" style="background-color: #007bff" value="Tạo đề thi"/>
                                    <%
                                        }
                                    %>
                                </div>
                            </form>
                        </div> 
                    </div>                        
                </div> 

                <%
                    }else{
                    subject = new ExamDAO().getSubjectByID(requests.getSubjectID());
                %>
                <div class="row g-3">   
                    <div class="col-md-4">
                        <label class="form-label">Kinh nghiệm dạy học(năm)</label>
                        <input type="number" class="form-control" id="validationDefault01" name="exp" value="<%=requests.getExperience()%>" disabled>
                    </div>

                    <div class="col-md-4">
                        <label class="form-label">Môn học</label>
                        <input type="text" class="form-control" name="school" value="<%=subject.getSubjectName()%>" disabled>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Trình độ học vấn</label>
                        <input type="text" class="form-control" name="school" value="<%=requests.getAcademicLevel()%>" disabled>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label">Đã từng/Đang công tác ở trường</label>
                        <input type="text" class="form-control" id="validationDefault03" name="school" value="<%=requests.getSchool()%>" disabled>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>

    </div>
</div>    
</div>    

<jsp:include page="footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"></script>
<script>

