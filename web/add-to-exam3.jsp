<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>
    <br>
    
    <style>
        .scrollable-tbody {
    max-height: 400px; /* Adjust the height as needed */
    overflow-y: auto;
}
        
    </style>   
<%
if(session.getAttribute("subjectID") != null){
    int subjectID = (Integer)session.getAttribute("subjectID");
    Subjects subject = new ExamDAO().getSubjectByID(subjectID);
%>
<div class="row" style="border-radius: 10px">
    <div class="col-lg-12" style="width: 1000px; margin: auto;">
        <a class="btn btn-primary" style="background-color: " href="choosesubject.jsp">Trở về</a>
        <br><br>
        <div class="card">
            <div class="card-body">
                <h4 class="text-primary">Tạo bài kiểm tra môn <%=subject.getSubjectName()%></h4>
                <c:if test="${not empty error}">
                    <p style="color:red">${error}</p>
                </c:if>
                <form class="row g-3" method="POST" action="CreateExam">
                    <div class="col-md-12">
                        <label class="form-label">Tên bài kiểm tra</label>
                        <input type="text" class="form-control" id="examName" name="examName" required>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label">Giá tiền bài kiểm tra</label>
                        <select class="form-select" id="validationDefault04" name="price" required>
                            <option value="0" selected>Miễn phí</option>
                            <option value="10">10 coin</option>
                            <option value="20">20 coin</option>
                            <option value="30">30 coin</option>                   
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label class="form-label">Thời gian</label>
                        <input type="number" min="0" class="form-control" id="validationDefault03" name="examHours" placeholder="Giờ" required>     
                        <input type="number" min="1" class="form-control" id="validationDefault03" name="examMinutes" placeholder="Phút" required>                      

                    </div>
                    <h3 class="text-primary">Chọn câu hỏi:</h3>



                    <div class="inner-main-body scrollable-tbody">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th class="text-primary" scope="col"></th>
                                <th class="text-primary" scope="col">Câu hỏi</th>
                                <th class="text-primary" scope="col">Đáp án</th>
                                <th class="text-primary" scope="col">Tác vụ</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                            List<QuestionBank> qbs = (List<QuestionBank>)session.getAttribute("questionList");
                            String context;
                            String answer;
                            for(int i = qbs.size() - 1; i >= 0; i--){
                                QuestionBank qb = qbs.get(i);
                                if(qb.getQuestionContext().length() > 40){ 
                                    context = qb.getQuestionContext().substring(0, 40) + "...";
                                }
                                else if(qb.getQuestionContext().length() == 0){
                                    context = qb.getQuestionImg();
                                }
                                else context = qb.getQuestionContext();
                                if(qb.getChoiceCorrect().startsWith("uploads/docreader")){
                                    answer = qb.getChoiceCorrect();
                                }
                                else{
                                    if(qb.getChoiceCorrect().length() > 40) 
                                        answer = qb.getChoiceCorrect().substring(0, 40) + "...";
                                    else answer = qb.getChoiceCorrect();
                                }
                                String modalDetailId = "threadModalDetail" + i;
                            %>
                            <tr>
                                <td><input type="checkbox" name="selectedQuestions" value="<%=qb.getQuestionId()%>"></td>
                                    <%
                                    if(context.startsWith("uploads/docreader")){
                                    %>
                                <td style="max-width: 500px"><img src="<%=context%>" width="60%"alt="alt"/></td>
                                    <%
                                        }
                                    else{
                                    %>
                                <td><p><%=context%></p></td>
                                <%
                                    }
                                %>
                                <%
                                if(answer.startsWith("uploads/docreader")){
                                %>
                                <td><img src="<%=answer%>" width="50%" height="50%" alt="alt"/></td>
                                    <%
                                        }
                                    else{
                                    %>
                                <td><%=answer%></td>
                                <%
                                    }
                                %>
                                <td style="display: flex; flex-direction: row; text-align: center">
                                    <button
                                        class="btn btn-primary has-icon btn-block"
                                        type="button"
                                        data-toggle="modal"
                                        data-target="#<%=modalDetailId%>"
                                        >
                                        Xem chi tiết
                                    </button>
                                    <div class="modal fade" id="<%=modalDetailId%>" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content" style="width: 100%; margin: auto">
                                                <div class="modal-header d-flex align-items-center bg-primary text-white">
                                                    <h6 class="modal-title mb-0" id="threadModalLabel">Chi tiết câu hỏi</h6>
                                                </div>
                                                <div class="modal-body" style="text-align: left;"> 
                                                    <p style="font-weight: bold; overflow-wrap:break-word;">Câu hỏi: <span style="font-weight: 100"><%=qb.getQuestionContext()%></span></p>
                                                        <%
                                                        if(qb.getQuestionImg() != null){
                                                        %>
                                                    <img src="<%=qb.getQuestionImg()%>" width="50%" height="50%"/>
                                                    <%
                                                        }
                                                    %>
                                                    <p style="font-weight: bold">Câu trả lời</p>
                                                    <%
                                                    if(qb.getChoice1().startsWith("uploads/docreader")){
                                                    %>
                                                    <br><span style="font-weight: bold">A. </span><img src="<%=qb.getChoice1()%>" height="30" alt="alt"/>
                                                    <%
                                                        }
                                                    else{
                                                    %>
                                                    <p style="overflow-wrap:break-word;"><label style="font-weight: bold">A:</label> <%=qb.getChoice1()%></p>
                                                    <%
                                                        }
                                                    %>
                                                    <%
                                                    if(qb.getChoice2().startsWith("uploads/docreader")){
                                                    %>
                                                    <br><span style="font-weight: bold">B. </span><img src="<%=qb.getChoice2()%>" height="30" alt="alt"/>
                                                    <%
                                                        }
                                                    else{
                                                    %>
                                                    <p style="overflow-wrap:break-word;"><label style="font-weight: bold">B:</label> <%=qb.getChoice2()%></p>
                                                    <%
                                                        }
                                                    %>
                                                    <%
                                                    if(qb.getChoice3().startsWith("uploads/docreader")){
                                                    %>
                                                    <br><span style="font-weight: bold">D. </span><img src="<%=qb.getChoice3()%>" height="30" alt="alt"/>
                                                    <%
                                                        }
                                                    else{
                                                    %>
                                                    <p style="overflow-wrap:break-word;"><label style="font-weight: bold">C:</label> <%=qb.getChoice3()%></p>
                                                    <%
                                                        }
                                                    %>
                                                    <%
                                                    if(qb.getChoiceCorrect().startsWith("uploads/docreader")){
                                                    %>
                                                    <br><span style="font-weight: bold">D. </span><img src="<%=qb.getChoiceCorrect()%>" height="30" alt="alt"/>
                                                    <%
                                                        }
                                                    else{
                                                    %>
                                                    <p style="overflow-wrap:break-word;"><label style="font-weight: bold">D:</label> <%=qb.getChoiceCorrect()%></p>
                                                    <%
                                                        }
                                                    %>
                                                    <%
                                                    if(qb.getChoiceCorrect().startsWith("uploads/docreader")){
                                                    %>
                                                    <br><span style="font-weight: bold">Đáp án: </span><img src="<%=qb.getChoiceCorrect()%>" height="30" alt="alt"/>
                                                    <%
                                                        }
                                                    else{
                                                    %>
                                                    <p style="overflow-wrap:break-word;"><label style="font-weight: bold">Đáp án:</label> <%=qb.getChoiceCorrect()%></p>
                                                    <%
                                                        }
                                                    %>
                                                    <p style="overflow-wrap:break-word;"><label style="font-weight: bold">Giải thích:</label> <%=qb.getExplain()%></p>
                                                    <%
                                                    if(qb.getExplainImg() != null){
                                                    %>
                                                    <img src="<%=qb.getExplainImg()%>" width="50%" height="50%"/>
                                                    <%
                                                        }
                                                    %>
                                                </div>
                                                <div class="modal-footer">
                                                    <input type="button" class="btn btn-primary" data-dismiss="modal"value="Xác nhận">
                                                </div>
                                            </div> 
                                        </div>                        
                                    </div> 
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>

                    </table>
            </div>
                    <input type="hidden" name="subjectID" value="<%=subject.getSubjectID()%>">
                    <button class="btn btn-primary has-icon btn-block" type="submit">Tạo bài kiểm tra</button>
                    <button
                        class="btn btn-primary has-icon btn-block"
                        type="button"
                        data-toggle="modal"
                        data-target="#threadModal"
                        >
                        Tạo đề ngẫu nhiên
                    </button>
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
                                    if(qbs.size() > 0){
                                        int max = qbs.size();
                                    %>
                                    <label for="examName">Tên đề thi:</label>
                                    <input type="text" id="examName" name="examName" required>
                                    <input type="hidden" name="subjectID" value="<%=subjectID%>"/>
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
                                    <br>
                                    <label class="form-label">Giá tiền bài kiểm tra</label>
                                    <select class="form-select" id="validationDefault04" name="price" required>
                                        <option value="0" selected>Miễn phí</option>
                                        <option value="10">10 coin</option>
                                        <option value="20">20 coin</option>
                                        <option value="30">30 coin</option>                   
                                    </select>
                                    <br>
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
                                    if(qbs.size() > 0){
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
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>    
</div>    

<jsp:include page="footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"></script>