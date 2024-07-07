<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="model.QuestionBank" %>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>

    <style>
        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"], input[type="number"] {
            margin-bottom: 10px;
            width: 100%;
            box-sizing: border-box;
        }

        input[type="checkbox"] {
            display: inline-block;
            margin-right: 5px;
            vertical-align: middle;
        }

        .question-label {
            display: inline-block;
            vertical-align: middle;
        }

        .question-list {
            max-height: 200px;
            overflow-y: auto;
        }

        button[type="submit"], button[type="button"] {
            display: block;
            width: 100%;
            padding: 10px;
            border: none;
            background-color: #007bff;
            color: #fff;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-top: 10px;
        }

        button[type="submit"]:hover, button[type="button"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: #dc3545;
            margin-top: 10px;
        }


        a{
            overflow-wrap: break-word;
            word-break: break-word;
        }

        p{
            overflow-wrap: break-word;
            word-break: break-word;
        }

        span{
            overflow-wrap: break-word;
            word-break: break-word;
        }
    </style>
<%
if(session.getAttribute("subjectID") != null){
    int subjectID = (Integer)session.getAttribute("subjectID");
    Subjects subject = new ExamDAO().getSubjectByID(subjectID);
%>
<body>
    <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css"
        integrity="sha256-46r060N2LrChLLb5zowXQ72/iKKNiw/lAmygmHExk/o="
        crossorigin="anonymous"
        />
    <div class="container">
        <br><br>
        <h1>Tạo đề thi môn <%=subject.getSubjectName()%></h1>
        <form action="CreateExam" method="post">
            <label for="examName">Tên đề thi:</label>
            <input type="text" id="examName" name="examName" value="" required>
            <br>
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
                            <option selected disabled>Choose...</option>
                            <option value="0">Miễn phí</option>
                            <option value="10">10 coin</option>
                            <option value="20">20 coin</option>
                            <option value="30">30 coin</option>                   
                        </select>

            <!-- Display questions from database -->
            <h2>Select Questions:</h2>
            <div class="question-list">
                <%
                List<QuestionBank> qblist = (List<QuestionBank>)session.getAttribute("questionList");
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
            <input type="hidden" name="subjectID" value="<%=subjectID%>">
            <button type="submit">Create Exam</button>
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
