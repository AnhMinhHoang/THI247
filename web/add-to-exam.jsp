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
if(session.getAttribute("questionlist") != null){
int examID = (Integer)session.getAttribute("examID");
int subjectID = (Integer)session.getAttribute("subjectID");
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
        <h1>Thêm câu hỏi</h1>
        <form action="AddQuestionToExam" method="post">
            <h2>Chọn câu hỏi:</h2>
            <div class="question-list">
                <%
                List<QuestionBank> qblist = (List<QuestionBank>)session.getAttribute("questionlist");
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
                <p>No questions available.</p>
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
                Thêm ngẫu nhiên
            </button>
            <input type="hidden" name="examID" value="<%=examID%>">
            <button type="submit">Thêm câu hỏi</button>
        </form>
        <form action="viewallquestion.jsp">
            <button type="submit">Trở về</button>
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
                            if(qblist.size() > 0){
                                int max = qblist.size();
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
                            if(qblist.size() > 0){
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
