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
                    <form action="BankQuestionServlet" method="post">
                        <input type="hidden" name="action" value="update"/>
                        <input type="hidden" name="id" value="${question.id}"/>
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
</body>
</html>
