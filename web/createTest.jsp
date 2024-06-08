<%@ page import="java.util.List" %>
<%@ page import="model.MultipleChoiceQuestion" %>
<%@ page import="DAO.QuestionDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Test</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
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
        form {
            margin-bottom: 20px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <h1>Create a New Test</h1>
    <form action="CrTestServlet" method="post">
        <label for="examName">Exam Name:</label>
        <input type="text" id="examName" name="examName" required>
        <h2>Select Questions Manually</h2>
        <%
            QuestionDAO questionDAO = new QuestionDAO();
            List<MultipleChoiceQuestion> questions = questionDAO.getAllMultipleChoiceQuestions();
        %>
        <table>
            <tr>
                <th>Select</th>
                <th>ID</th>
                <th>Question</th>
                <th>Choices</th>
                <th>Correct Answer</th>
                <th>Explanation</th>
            </tr>
            <%
                for (MultipleChoiceQuestion question : questions) {
            %>
            <tr>
                <td><input type="checkbox" name="questionIds" value="<%= question.getId() %>"></td>
                <td><%= question.getId() %></td>
                <td><%= question.getQuestionText() %></td>
                <td>
                    <ul>
                        <%
                            for (String choice : question.getChoices()) {
                        %>
                        <li><%= choice %></li>
                        <%
                            }
                        %>
                    </ul>
                </td>
                <td><%= question.getCorrectAnswer() %></td>
                <td><%= question.getExplain() %></td>
            </tr>
            <%
                }
            %>
        </table>
        <h2>Or Generate Random Questions</h2>
        <label for="numQuestions">Number of Questions:</label>
        <input type="number" id="numQuestions" name="numQuestions" min="1" max="<%= questions.size() %>">
        <input type="hidden" name="action" value="generate">
        <br><br>
        <input type="submit" value="Generate Random Test">
        <input type="submit" value="Create Test with Selected Questions">
    </form>
</body>
</html>
