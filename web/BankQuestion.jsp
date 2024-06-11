<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Questions</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h2 {
            color: #333;
            margin-top: 20px;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            overflow: hidden;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f9f9f9;
        }
        input[type="text"], select {
            width: calc(100% - 12px);
            padding: 8px;
            border-radius: 3px;
            border: 1px solid #ccc;
            margin-bottom: 15px;
        }
        input[type="submit"], button {
            padding: 10px 20px;
            border: none;
            background-color: #007bff;
            color: #fff;
            border-radius: 3px;
            cursor: pointer;
        }
        input[type="submit"]:hover, button:hover {
            background-color: #0056b3;
        }
        a {
            color: #007bff;
            text-decoration: none;
            cursor: pointer;
        }
        a:hover {
            text-decoration: underline;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        /* Thêm CSS cho ô User ID */
        input#userId {
            width: 50px; /* Điều chỉnh kích thước ô User ID */
            word-wrap: break-word; /* Cho phép text tràn ra nhiều dòng */
        }
    </style>
</head>
<body>
    <h2>List of Questions</h2>
    <form action="BankQuestionServlet" method="get">
        Filter by Subject:
        <select name="subjectFilter">
            <option value="">All</option>
            <c:forEach items="${availableSubjects}" var="subject">
                <option value="${subject}">${subject}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Filter">
    </form>
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
                    <td><input type="text" name="userId" id="userId" value="${question.userId}"></td>
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
        <label for="userId">User ID:</label>
        <input type="text" name="userId" id="userId"><br>
        <label for="questionText">Question:</label>
        <input type="text" name="questionText" id="questionText"><br>
        <label for="subject">Subject:</label>
        <input type="text" name="subject" id="subject"><br>
        <label for="choice1">Choice 1:</label>
        <input type="text" name="choice1" id="choice1"><br>
        <label for="choice2">Choice 2:</label>
        <input type="text" name="choice2" id="choice2"><br>
        <label for="choice3">Choice 3:</label>
        <input type="text" name="choice3" id="choice3"><br>
        <label for="correctAnswer">Correct Answer:</label>
        <input type="text" name="correctAnswer" id="correctAnswer"><br>
        <label for="explain">Explanation:</label>
        <input type="text" name="explain" id="explain"><br>
        <input type="submit" value="Add Question">
    </form>
</body>
</html>

</html>
