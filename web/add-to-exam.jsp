<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="model.QuestionBank" %>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>

   <style>
        .scrollable-tbody {
    max-height: 400px; /* Adjust the height as needed */
    overflow-y: auto;
}
        
    </style> 
<%
if(session.getAttribute("questionList") != null){
int examID = (Integer)session.getAttribute("examID");
int subjectID = (Integer)session.getAttribute("subjectID");
%>
<body>
    <div class="container">
        <br><br>
        <h1>Thêm câu hỏi</h1>
            <h2>Chọn câu hỏi:</h2>
            <form action="AddQuestionToExam" method="post">
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

            <button
                class="btn btn-primary has-icon btn-block"
                type="button"
                data-toggle="modal"
                data-target="#threadModal"
                style="margin-top: 20px; margin-bottom: 10px"
                >
                Thêm ngẫu nhiên
            </button>
            <input type="hidden" name="examID" value="<%=examID%>">
            <button
                class="btn btn-primary has-icon btn-block"
                type="submit">Thêm câu hỏi</button>
        </form>

        <div class="modal fade" id="threadModal" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content" style="width: 500px; margin: auto">
                    <form action="AddRandomQuestionToExam" method="POST">
                        <div class="modal-header d-flex align-items-center bg-primary text-white">
                            <h6 class="modal-title mb-0" id="threadModalLabel">Thêm câu ngẫu nhiên</h6>
                        </div>
                        <div class="modal-body">
                            <%
                            if(qbs.size() > 0){
                                int max = qbs.size();
                            %>
                            <input type="hidden" name="examID" value="<%=examID%>"/>
                            <input type="hidden" name="subjectID" value="<%=subjectID%>"/>
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
                            <input type="submit" class="btn btn-primary" style="background-color: #007bff" value="Thêm câu hỏi"/>
                            <%
                                }
                            %>
                        </div>
                    </form>
                </div> 
            </div>                        
        </div> 
    </div>
    <%
        }
    %>
    <script>
        function changeSubject() {
            var subjectID = document.getElementById("examSubject").value;

            var xhr = new XMLHttpRequest();
            xhr.open("POST", "ChangeSubject", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    // Handle the response if needed
                    console.log(xhr.responseText);
                }
            };
            xhr.send("subjectID=" + subjectID);
        }
    </script>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript"></script>
    <jsp:include page="footer.jsp"></jsp:include>
    
    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
