<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@ page import="model.QuestionBank" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>
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
if(session.getAttribute("questionID") != null){
int questionID = (Integer)session.getAttribute("questionID");
QuestionBank qb = new ExamDAO().getQuestionByID(questionID);
String link = (String)session.getAttribute("backlink");
%>
<body>
    <%
    Us
    %>
    <div class="container">
        <br><br>
        <button class="btn btn-light"><a href="<%=link%>" style="text-decoration: none; color: black">Back</a></button>
            <fieldset>
                <legend>Câu hỏi</legend>
                <p style="overflow-wrap:break-word;"><%=qb.getQuestionContext()%></p>
                <p style="font-weight: bold">Câu trả lời</p>
                <%
                if(qb.getQuestionImg() != null){
                %>
                <img src="<%=qb.getQuestionImg()%>" width="400px" height="400px"/>
                <%
                    }
                %>

                <p style="overflow-wrap:break-word;"><label style="font-weight: bold">A:</label> <%=qb.getChoice1()%></p>

                <p style="overflow-wrap:break-word;"><label style="font-weight: bold">B:</label> <%=qb.getChoice2()%></p>

                <p style="overflow-wrap:break-word;"><label style="font-weight: bold">C:</label> <%=qb.getChoice3()%></p>

                <p style="overflow-wrap:break-word;"><label style="font-weight: bold">D:</label> <%=qb.getChoiceCorrect()%></p>

                <p style="overflow-wrap:break-word;"><label style="font-weight: bold">Đáp án:</label> <%=qb.getChoiceCorrect()%></p>
                <p style="overflow-wrap:break-word;"><label style="font-weight: bold">Giải thích:</label> <%=qb.getExplain()%></p>
                <%
                if(qb.getExplainImg() != null){
                %>
                <img src="<%=qb.getExplainImg()%>" width="400px" height="400px"/>
                <%
                    }
                %>
            </fieldset>
    </div>
    <%
        }
    %>
    <jsp:include page="footer.jsp"></jsp:include>