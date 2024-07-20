<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@ page import="model.QuestionBank" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>
<script>
        var container = document.getElementById("tagID");
        var tag = container.getElementsByClassName("tag");
        var current = container.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
        tag[2].className += " active";
    </script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
        }

        fieldset {
            margin-bottom: 20px;
            border: 2px solid #ccc;
            border-radius: 5px;
            padding: 20px;
        }

        legend {
            font-weight: bold;
            font-size: 1.2em;
            color: #333;
        }

        input[type="radio"]{
            margin-bottom: 10px;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }


    </style>
<%
if(session.getAttribute("resultID") != null){
int resultID = (Integer)session.getAttribute("resultID");
int examID = (Integer)session.getAttribute("examID");
Exam exam = new ExamDAO().getExamByID(examID);
Result result = new StudentExamDAO().getResultByID(resultID);
Tests test = (Tests)session.getAttribute("test");
new StudentExamDAO().updateTestTime(test.getTestID(), 0);
%>
<body>
    <div class="container">
        <br><br>
        <h2 style="text-align: center">Kết quả</h2>
            <fieldset>
                <legend>Bài kiểm tra <%=exam.getExamName()%></legend>
                <p style="font-weight: bold">Điểm số: <%=result.getScore()%></p>

                <p style="overflow-wrap:break-word;"><label style="font-weight: bold">Số câu đúng: </label> <%=result.getRightAnswer()%>/<%=result.getTotalQuestion()%></p>
            </fieldset>
            <div>
                <button class="btn btn-primary"><a href="ExamDetail?examID=<%=examID%>" style="text-decoration: none; color:white">Thi lại</a></button>
                <button class="btn btn-primary"><a href="student.jsp" style="text-decoration: none; color:white">Trở về</a></button>
                <button class="btn btn-primary"><a href="PassDataResultDetail?testID=<%=test.getTestID()%>" style="text-decoration: none; color:white">Xem chi tiết</a></button>
            </div>
    </div>
    <%
        }
    %>
    <jsp:include page="footer.jsp"></jsp:include>
    
    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>