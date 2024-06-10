<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="model.QuestionBank" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Exam</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 600px;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1, h2 {
            color: #333;
            text-align: center;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"] {
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

        button[type="submit"] {
            display: block;
            width: 100%;
            padding: 10px;
            border: none;
            background-color: #007bff;
            color: #fff;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: #dc3545;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Create Exam</h1>
        <% if (request.getParameter("error") != null && request.getParameter("error").equals("1")) { %>
            <p class="error-message">Error: Failed to create exam. Please try again.</p>
        <% } else if (request.getParameter("error") != null && request.getParameter("error").equals("2")) { %>
            <p class="error-message">Error: Database error occurred. Please try again later.</p>
        <% } %>
        <form action="createexam" method="post">
            <label for="examName">Exam Name:</label>
            <input type="text" id="examName" name="examName" required><br><br>

            <!-- Display questions from database -->
            <h2>Select Questions:</h2>
            <% List<QuestionBank> questions = (List<QuestionBank>) request.getAttribute("questions"); %>
            <% if (questions != null) { %>
                <% for (QuestionBank question : questions) { %>
                    <label>
                        <input type="checkbox" id="<%=question.getId()%>" name="selectedQuestions" value="<%=question.getId()%>">
                        <span class="question-label"><%=question.getQuestionText()%></span>
                    </label><br>
                <% } %>
            <% } else { %>
                <p>No questions available.</p>
            <% } %>

            <button type="submit">Create Exam</button>
        </form>
    </div>
</body>
</html>
