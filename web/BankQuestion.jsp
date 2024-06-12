<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<h1>Lọc Câu Hỏi</h1>
<form id="filterForm" action="QuestionFilterServlet" method="post">
    <label for="subject">Chọn một môn học:</label>
    <select name="subject" id="subject">
        <c:forEach items="${subjects}" var="subject">
            <option value="${subject}">${subject}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="userId" value="${userId}">
    <button type="submit">Lọc</button>
</form>

<script>
    // JavaScript để lưu trữ và khôi phục giá trị đã chọn
    document.addEventListener("DOMContentLoaded", function() {
        var subjectDropdown = document.getElementById("subject");
        var selectedSubject = localStorage.getItem("selectedSubject");

        // Khôi phục giá trị đã chọn (nếu có)
        if (selectedSubject) {
            subjectDropdown.value = selectedSubject;
        }

        // Lưu giá trị môn học đã chọn khi form được gửi đi
        document.getElementById("filterForm").addEventListener("submit", function() {
            localStorage.setItem("selectedSubject", subjectDropdown.value);
        });
    });
</script>
    <table border="1">
        <thead>
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
        </thead>
        <tbody>
            <c:forEach items="${questions}" var="question">
                <tr>
                    <form action="BankQuestionServlet" method="get">
                        <input type="hidden" name="action" value="update"/>
                        <input type="hidden" name="id" value="${question.id}"/>
                        <input type="hidden" name="subject" value="${question.subject}"/>
                        <td>${question.id}</td>
                        <td>${question.subject}</td>
                        <td><input type="text" name="userId" value="${question.userId}" readonly/></td>
                        <td><input type="text" name="questionText" value="${question.questionText}"/></td>
                        <td>
                            <input type="text" name="choice1" value="${question.choices[0]}"/><br>
                            <input type="text" name="choice2" value="${question.choices[1]}"/><br>
                            <input type="text" name="choice3" value="${question.choices[2]}"/>
                        </td>
                        <td><input type="text" name="correctAnswer" value="${question.correctAnswer}"/></td>
                        <td><input type="text" name="explain" value="${question.explain}"/></td>
                        <td>
                            <input type="submit" value="Update"/>
                            
                            <a href="BankQuestionServlet?action=delete&id=${question.id}">Delete</a>
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <body>
    <body>
    <h2>Add Question</h2>
<form action="BankQuestionServlet" method="get">
    <input type="hidden" name="action" value="add">
    <label for="questionText">Question Text:</label><br>
    <input type="text" id="questionText" name="questionText" ><br>
    
    <!-- Dropdown list to select subject -->
    <label for="subject">Subject:</label><br>
    <select id="subject" name="subject">
        <c:forEach var="subject" items="${subjects}">
            <option value="${subject}">${subject}</option>
        </c:forEach>
    </select><br>
    
    <label for="choice1">Choice 1:</label><br>
    <input type="text" id="choice1" name="choice1"><br>
    
    <label for="choice2">Choice 2:</label><br>
    <input type="text" id="choice2" name="choice2"><br>
    
    <label for="choice3">Choice 3:</label><br>
    <input type="text" id="choice3" name="choice3"><br>
    
    <label for="correctAnswer">Correct Answer:</label><br>
    <input type="text" id="correctAnswer" name="correctAnswer"><br>
    
    <label for="explain">Explanation:</label><br>
    <input type="text" id="explain" name="explain" ><br>
    
    <input type="submit" value="Submit">
</form>

</body>
</html>
