<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Question Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }

        h2 {
            margin-top: 10px;
            margin-bottom: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 8px;
            border: 1px solid #ddd;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        form {
            margin-bottom: 20px;
        }

        input[type="text"] {
            width: calc(100% - 16px);
            padding: 6px;
            box-sizing: border-box;
            margin-bottom: 8px;
        }

        input[type="submit"], a {
            display: inline-block;
            padding: 6px 12px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover, a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>List of Multiple Choice Questions</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Subject</th>
            <th>User ID</th>
            <th>Question</th>
            <th>Choices</th>
            <th>Correct Answer</th>
            <th>Explanation</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${questions}" var="question">
            <tr>
                <form action="BankQuestionServlet" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="${question.id}">
                    <td>${question.id}</td>
                    <td><input type="text" name="subject" value="${question.subject}"></td>
                    <td><input type="text" name="userId" value="${question.userId}"></td>
                    <td><input type="text" name="questionText" value="${question.questionText}"></td>
                    <td>
                        <input type="text" name="choice1" value="${question.choices[0]}"><br>
                        <input type="text" name="choice2" value="${question.choices[1]}"><br>
                        <input type="text" name="choice3" value="${question.choices[2]}">
                    </td>
                    <td>
                        <input type="text" name="correctAnswer" value="${question.correctAnswer}">
                    </td>
                    <td><input type="text" name="explain" value="${question.explain}"></td>
                    <td>
                        <input type="submit" value="Update">
                        <a href="BankQuestionServlet?action=delete&id=${question.id}">Delete</a>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>

    <h2>Add New Question</h2>
    <form action="BankQuestionServlet" method="post">
        <input type="hidden" name="action" value="add">
        Subject: <input type="text" name="subject"><br>
        User ID: <input type="text" name="userId"><br>
        Question: <input type="text" name="questionText"><br>
        Choice 1: <input type="text" name="choice1"><br>
        Choice 2: <input type="text" name="choice2"><br>
        Choice 3: <input type="text" name="choice3"><br>
        Correct Answer: <input type="text" name="correctAnswer"><br>
        Explanation: <input type="text" name="explain"><br>
        <input type="submit" value="Add Question">
    </form>
</body>
</html>
