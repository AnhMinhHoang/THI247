<%@ page import="java.util.List" %>
<%@ page import="model.MultipleChoiceQuestion" %>
<%@ page import="DAO.QuestionDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Questions</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1, h2 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        td input[type="text"] {
            width: 95%;
            padding: 5px;
            margin-bottom: 5px;
        }
        td input[type="submit"], td button {
            padding: 5px 10px;
            margin-right: 5px;
        }
        form.new-question {
            margin-top: 20px;
            padding: 20px;
            border: 1px solid #ccc;
            background-color: #f9f9f9;
        }
        form.new-question input[type="text"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 10px;
        }
        form.new-question input[type="submit"] {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
        }
        form.new-question input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
    <script>
        function deleteQuestion(id) {
            if (confirm('Are you sure you want to delete this question?')) {
                document.location.href = 'TeacherServlet?action=delete&id=' + id;
            }
        }
    </script>
</head>
<body>
    <h1>Manage Multiple Choice Questions</h1>
    <%
        QuestionDAO questionDAO = new QuestionDAO();
        List<MultipleChoiceQuestion> questions = questionDAO.getAllMultipleChoiceQuestions();
    %>
    <table>
        <tr>
            <th>ID</th>
            <th>Question</th>
            <th>Choices</th>
            <th>Correct Answer</th>
            <th>Explanation</th>
            <th>Actions</th>
        </tr>
        <%
            for (MultipleChoiceQuestion question : questions) {
        %>
        <form action="TeacherServlet" method="post">
            <tr>
                <td><input type="text" name="id" value="<%= question.getId() %>" readonly></td>
                <td><input type="text" name="questionText" value="<%= question.getQuestionText() %>"></td>
                <td>
                    <input type="text" name="choice1" value="<%= question.getChoices().get(0) %>">
                    <input type="text" name="choice2" value="<%= question.getChoices().get(1) %>">
                    <input type="text" name="choice3" value="<%= question.getChoices().get(2) %>">
                    <input type="text" name="choice4" value="<%= question.getChoices().get(3) %>">
                </td>
                <td><input type="text" name="correctAnswer" value="<%= question.getCorrectAnswer() %>"></td>
                <td><input type="text" name="explain" value="<%= question.getExplain() %>"></td>
                <td>
                    <input type="hidden" name="action" value="update">
                    <input type="submit" value="Update">
                    <button type="button" onclick="deleteQuestion('<%= question.getId() %>')">Delete</button>
                </td>
            </tr>
        </form>
        <%
            }
        %>
    </table>

    <h2>Add New Question</h2>
    <form action="TeacherServlet" method="post" class="new-question">
        <input type="text" name="questionText" placeholder="Question Text" required>
        <input type="text" name="choice1" placeholder="Choice 1" required>
        <input type="text" name="choice2" placeholder="Choice 2" required>
        <input type="text" name="choice3" placeholder="Choice 3" required>
        <input type="text" name="choice4" placeholder="Choice 4" required>
        <input type="text" name="correctAnswer" placeholder="Correct Answer" required>
        <input type="text" name="explain" placeholder="Explanation" required>
        <input type="hidden" name="action" value="create">
        <input type="submit" value="Add Question">
    </form>
</body>
</html>
