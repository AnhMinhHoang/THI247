<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.QuestionBank" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
        }

        fieldset {
            margin-bottom: 20px;
            border: 2px solid #ccc;
            border-radius: 5px;
            padding: 20px;
        }

        legend {
            font-weight: bold;
            font-size: 1.2em;
            color: #333;
        }

        p {
            margin-bottom: 10px;
        }

        input[type="radio"] {
            margin-right: 10px;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .result {
            margin-top: 20px;
            text-align: center;
            font-size: 1.2em;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Quiz</h1>
        <form action="StudentServlet" method="post">
            <fieldset>
                <legend>Quiz Questions</legend>
                <%
                    List<QuestionBank> questions = (List<QuestionBank>) request.getAttribute("questions");
                    double totalScore = 0;
                    for (QuestionBank question : questions) {
                        double questionScore = 0;
                %>
                    <p><%= question.getQuestionText() %></p>
                    <input type="hidden" name="questionIds" value="<%= question.getId() %>">
                    <%
                        List<String> choices = question.getChoices();
                        String userChoice = request.getParameter("answers_" + question.getId());
                        if (userChoice != null) { // Chỉ hiển thị nếu người dùng đã chọn
                            String correctAnswer = question.getCorrectAnswer();
                            String explain = question.getExplain();
                            boolean isCorrect = userChoice.equals(correctAnswer);
                            if (isCorrect) {
                                questionScore = 1;
                            }
                            totalScore += questionScore;
                            for (String choice : choices) {
                                if (choice.equals(userChoice)) {
                    %>
                                    <input type="radio" name="answers_<%= question.getId() %>" value="<%= choice %>" checked><%= choice %><br>
                    <%
                                } else {
                    %>
                                    <input type="radio" name="answers_<%= question.getId() %>" value="<%= choice %>"><%= choice %><br>
                    <%
                                }
                            }
                    %>
                            <%-- Hiển thị choice correct và kết quả --%>
                            <p>Correct choice: <%= correctAnswer %></p>
                            <p>Result: <%= isCorrect ? "Correct" : "Incorrect" %></p>
                            <%-- Hiển thị giải thích nếu là sai --%>
                            <% if (!isCorrect) { %>
                                <p>Explain: <%= explain %></p>
                            <% } %>
                            <%-- Hiển thị điểm của câu hỏi --%>
                            <p>Question Score: <%= questionScore * 100 %> %</p>
                    <%
                        } else {
                            for (String choice : choices) {
                    %>
                                <input type="radio" name="answers_<%= question.getId() %>" value="<%= choice %>"><%= choice %><br>
                    <%
                            }
                        }
                    %>
                <%
                    }
                    // Tính tổng điểm
                    double totalMaxScore = 100.0; // Số điểm tối đa của bài kiểm tra
                    totalScore = (totalScore / questions.size()) * totalMaxScore;
                %>
            </fieldset>
            <%-- Hiển thị tổng điểm --%>
            <p class="result">Total Score: <%= totalScore %> %</p>
            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>
