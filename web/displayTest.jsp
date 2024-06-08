<%@ page import="java.util.List" %>
<%@ page import="model.MultipleChoiceQuestion" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test Created</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            text-align: center;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin-bottom: 10px;
        }
        .error {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>Test Created</h1>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <p class="error"><%= error %></p>
    <%
        }
    %>
    <%
        List<MultipleChoiceQuestion> selectedQuestions = (List<MultipleChoiceQuestion>) request.getAttribute("selectedQuestions");
        if (selectedQuestions != null) {
            int questionNumber = 1;
            for (MultipleChoiceQuestion question : selectedQuestions) {
    %>
    <div>
        <p><b>Question <%= questionNumber++ %>:</b> <%= question.getQuestionText() %></p>
        <ul>
            <%
                for (String choice : question.getChoices()) {
            %>
            <li><%= choice %></li>
            <%
                }
            %>
        </ul>
        <p><b>Correct Answer:</b> <%= question.getCorrectAnswer() %></p>
        <p><b>Explanation:</b> <%= question.getExplain() %></p>
    </div>
    <hr>
    <%
            }
        } else {
    %>
    <p>No questions selected or generated for the test.</p>
    <%
        }
    %>
</body>
</html>
