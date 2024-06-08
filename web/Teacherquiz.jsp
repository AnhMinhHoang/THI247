<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Question Management</title>
</head>
<body>
    <h2>List of Multiple Choice Questions</h2>
    <table border="1">
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
                <form action="TeacherServlet" method="post">
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
                        <a href="TeacherServlet?action=delete&id=${question.id}">Delete</a>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>

    <h2>Add New Question</h2>
    <form action="TeacherServlet" method="post">
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
