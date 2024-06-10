<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exam Result</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
    }
    .container {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        max-width: 800px;
        width: 100%;
    }
    h2 {
        color: #333;
    }
    p {
        font-size: 16px;
        color: #666;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    table, th, td {
        border: 1px solid #ddd;
    }
    th, td {
        padding: 12px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    .correct {
        color: green;
    }
    .incorrect {
        color: red;
    }
    .button {
        display: inline-block;
        padding: 10px 20px;
        margin-top: 20px;
        font-size: 16px;
        color: #fff;
        background-color: #007bff;
        border: none;
        border-radius: 4px;
        text-align: center;
        text-decoration: none;
        cursor: pointer;
    }
    .button:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Exam Result</h2>
        
        <c:if test="${not empty errorMessage}">
            <p>${errorMessage}</p>
        </c:if>
        
        <c:if test="${empty errorMessage}">
            <p>Exam ID: ${examId}</p>
            <p>Total Questions: ${numQuestions}</p>
            <p>Your Score: ${score}%</p>
            
            <h3>Your Answers:</h3>
            <table>
                <tr>
                    <th>Question</th>
                    <th>Your Answer</th>
                    <th>Correct Answer</th>
                    <th>Explanation</th>
                    <th>Status</th>
                </tr>
                <c:forEach var="index" begin="0" end="${numQuestions - 1}">
                    <tr>
                        <td>${questionTexts[index]}</td>
                        <td>${userAnswers[index]}</td>
                        <td>${correctAnswers[index]}</td>
                        <td>${explanations[index]}</td>
                        <td class="${answerCorrectness[index] ? 'correct' : 'incorrect'}">
                            ${answerCorrectness[index] ? 'Correct' : 'Incorrect'}
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <a href="takeExam?examId=${examId}" class="button">Retake Exam</a>
        </c:if>
    </div>
</body>
</html>
