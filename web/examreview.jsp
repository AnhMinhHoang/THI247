<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Exam" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exams List</title>
    <style>
        /* CSS style đã được thêm vào */
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            border: 1px solid #ccc;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        form {
            display: inline;
        }

        .add-exam-form {
            text-align: center;
            margin-top: 20px;
        }

        .back-button {
            text-align: center;
            margin-top: 20px;
        }

        .back-button button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .back-button button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Exams List</h1>
    <table border="1">
        <tr>
            <th>Exam ID</th>
            <th>Exam Name</th>
            <th>User ID</th>
            <th>Actions</th>
        </tr>
        <% List<Exam> exams = (List<Exam>) request.getAttribute("exams");
        for (Exam exam : exams) { %>
        <tr>
            <td><%= exam.getExamId() %></td>
            <td><%= exam.getExamName() %></td>
            <td><%= exam.getUserId() %></td>
            <td>
                <form action="ExamQuestionsServlet" method="get">
                    <input type="hidden" name="examId" value="<%= exam.getExamId() %>">
                    <input type="submit" value="Edit">
                </form>
                <form action="DeleteExamServlet" method="post">
                    <input type="hidden" name="examId" value="<%= exam.getExamId() %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <div class="add-exam-form">
        <form action="createexam" method="get">
            <input type="submit" value="Add Exam">
        </form>
    </div>
    <div class="back-button">
        <form action="Teacher.jsp">
            <button type="submit">Back</button>
        </form>
    </div>
</body>
</html>
