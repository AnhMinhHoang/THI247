<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exam Result</title>
</head>
<body >
    <jsp:include page="header.jsp"></jsp:include>
    <div class="container" style="
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        max-width: 800px;
        width: 100%;
        margin-top: 20px;
    ">
        <h2 style="
            color: #333;
            margin-bottom: 20px;
        ">Exam Result</h2>
        
        <c:if test="${not empty errorMessage}">
            <p style="
                font-size: 16px;
                color: #666;
                margin-bottom: 10px;
            ">${errorMessage}</p>
        </c:if>
        
        <c:if test="${empty errorMessage}">
            <p style="
                font-size: 16px;
                color: #666;
                margin-bottom: 10px;
            ">Exam ID: ${examId}</p>
            <p style="
                font-size: 16px;
                color: #666;
                margin-bottom: 10px;
            ">Total Questions: ${numQuestions}</p>
            <p style="
                font-size: 16px;
                color: #666;
                margin-bottom: 10px;
            ">Your Score: ${score}%</p>
            
            <h3 style="
                color: #333;
                margin-bottom: 10px;
            ">Your Answers:</h3>
            <table style="
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            ">
                <tr>
                    <th style="
                        border: 1px solid #ddd;
                        padding: 12px;
                        text-align: left;
                        background-color: #f2f2f2;
                    ">Question</th>
                    <th style="
                        border: 1px solid #ddd;
                        padding: 12px;
                        text-align: left;
                        background-color: #f2f2f2;
                    ">Your Answer</th>
                    <th style="
                        border: 1px solid #ddd;
                        padding: 12px;
                        text-align: left;
                        background-color: #f2f2f2;
                    ">Correct Answer</th>
                    <th style="
                        border: 1px solid #ddd;
                        padding: 12px;
                        text-align: left;
                        background-color: #f2f2f2;
                    ">Explanation</th>
                    <th style="
                        border: 1px solid #ddd;
                        padding: 12px;
                        text-align: left;
                        background-color: #f2f2f2;
                    ">Status</th>
                </tr>
                <c:forEach var="index" begin="0" end="${numQuestions - 1}">
                    <tr>
                        <td style="
                            border: 1px solid #ddd;
                            padding: 12px;
                            text-align: left;
                        ">${questionTexts[index]}</td>
                        <td style="
                            border: 1px solid #ddd;
                            padding: 12px;
                            text-align: left;
                        ">${userAnswers[index]}</td>
                        <td style="
                            border: 1px solid #ddd;
                            padding: 12px;
                            text-align: left;
                        ">${correctAnswers[index]}</td>
                        <td style="
                            border: 1px solid #ddd;
                            padding: 12px;
                            text-align: left;
                        ">${explanations[index]}</td>
                        <td style="
                            border: 1px solid #ddd;
                            padding: 12px;
                            text-align: left;
                            color: ${answerCorrectness[index] ? 'green' : 'red'};
                        ">${answerCorrectness[index] ? 'Correct' : 'Incorrect'}</td>
                    </tr>
                </c:forEach>
            </table>
            <a href="takeExam?examId=${examId}" class="button" style="
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
            ">Retake Exam</a>
        </c:if>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>