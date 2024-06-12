<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Question Bank</title>
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
        <!-- Add input fields for choices -->
        <input type="text" name="correctAnswer" placeholder="Enter correct answer"/>
        <input type="text" name="explanation" placeholder="Enter explanation"/>
        <button type="submit" name="action" value="add">Add</button>
    </form>
</body>
</html>
