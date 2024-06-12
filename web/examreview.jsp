<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Exam" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exams List</title>
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
    <form action="createexam" method="get">
        <input type="submit" value="Add Exam">
    </form>
</body>
</html>
