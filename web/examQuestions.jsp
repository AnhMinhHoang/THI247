<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Question Bank</title>
    <style>
        /* CSS style đã được cải thiện */
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h1 {
            margin-top: 20px;
        }

        table {
            width: 80%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
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

        input[type="text"] {
            width: 100%;
            box-sizing: border-box;
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        button[type="submit"] {
            margin-top: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        .back-button {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>Question Bank</h1>
    
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
                    <form action="ExamQuestionsServlet" method="get">
                        <input type="hidden" name="examId" value="${examId}"/>
                        <input type="hidden" name="questionId" value="${question.id}"/>
                        <td>${question.id}</td>
                        <td>${question.subject}</td>
                        <td>${question.userId}</td>
                        <td><input type="text" name="updatedQuestionText" value="${question.questionText}"/></td>
                        <td>
                           <c:forEach items="${question.choices}" var="choice" varStatus="status">
                               <input type="text" name="updatedChoices" value="${choice}"/><br>
                           </c:forEach>
                        </td>
                        <td><input type="text" name="updatedCorrectAnswer" value="${question.correctAnswer}"/></td>
                        <td><input type="text" name="updatedExplain" value="${question.explain}"/></td>
                        <td>
                            <button type="submit" name="action" value="update">Update</button>
                            <button type="submit" name="action" value="delete">Delete</button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <form action="ExamQuestionsServlet" method="get">
        <input type="hidden" name="examId" value="${examId}"/>
        <input type="text" name="questionText" placeholder="Enter question text"/>
        <input type="text" name="Choice1" placeholder="Choice1"/>
        <input type="text" name="Choice2" placeholder="Choice2"/>
        <input type="text" name="Choice3" placeholder="Choice3"/>
        <input type="text" name="correctAnswer" placeholder="Enter correct answer"/>
        <input type="text" name="explanation" placeholder="Enter explanation"/>
        <button type="submit" name="action" value="add">Add</button>
    </form>
    
    <div class="back-button">
        <form action="Teacher.jsp">
            <button type="submit">Back</button>
        </form>
    </div>
</body>
</html>
