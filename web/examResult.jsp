<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exam Result</title>
</head>
<body>
    <h2>Exam Result</h2>
    
    <c:if test="${not empty errorMessage}">
        <p>${errorMessage}</p>
    </c:if>
    
    <c:if test="${empty errorMessage}">
        <p>Exam ID: ${examId}</p>
        <p>Total Questions: ${numQuestions}</p>
        <p>Your Score: ${score}%</p>
        
        <h3>Your Answers:</h3>
        <table border="1">
            <tr>
                <th>Question</th>
                <th>Your Answer</th>
                <th>Correct Answer</th>
            </tr>
            <c:forEach var="index" begin="0" end="${numQuestions - 1}">
                <tr>
                    <td>${questions[index].questionText}</td>
                    <td>${userAnswers[index]}</td>
                    <td>${correctAnswers[index]}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
