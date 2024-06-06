<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.MultipleChoiceQuestion" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DAO.QuestionDAO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
        }
        form {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 600px;
            margin: 0 auto;
        }
        .question {
            margin-bottom: 20px;
        }
        .question h2 {
            margin-top: 0;
        }
        .choices {
            list-style-type: none;
            padding-left: 0;
        }
        .choices li {
            margin-bottom: 10px;
        }
        .submit-button {
            display: block;
            margin-top: 20px;
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        .submit-button:hover {
            background-color: #45a049;
        }
        .answer {
            margin-top: 10px;
            font-weight: bold;
        }
        .correct-answer {
            color: green;
        }
        .incorrect-answer {
            color: red;
        }
        .explanation {
            margin-top: 5px;
            font-style: italic;
        }
        .score {
            margin-top: 20px;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Quiz</h1>
    <form action="StudentServlet" method="post">
        <% 
            List<MultipleChoiceQuestion> questions = (List<MultipleChoiceQuestion>) request.getAttribute("questions");
            List<String> userAnswers = (List<String>) request.getAttribute("userAnswers");
            List<String> correctAnswers = (List<String>) request.getAttribute("correctAnswers");
            List<String> explain = (List<String>) request.getAttribute("explain");
            boolean submitted = request.getMethod().equalsIgnoreCase("POST");
            
            if (questions != null && !questions.isEmpty()) {
                for (int i = 0; i < questions.size(); i++) {
                    MultipleChoiceQuestion question = questions.get(i);
        %>
                    <div class="question">
                        <h2><%= question.getQuestionText() %></h2>
                        <ul class="choices">
                            <% 
                                List<String> choices = question.getChoices();
                                for (int j = 0; j < choices.size(); j++) {
                                    String choice = choices.get(j);
                            %>
                                    <li>
                                        <input type="radio" name="answer_<%= question.getId() %>" value="<%= j %>" <% 
                                            if (submitted && userAnswers != null && !userAnswers.isEmpty() && userAnswers.get(i).equals(choice)) { 
                                        %>checked<% } %>> 
                                        <%= choice %>
                                    </li>
                            <% } %>
                        </ul>
                        <% 
                            if (submitted && userAnswers != null && !userAnswers.isEmpty()) { 
                                String userAnswer = userAnswers.get(i);
                                String correctAnswer = correctAnswers.get(i);
                                boolean isCorrect = userAnswer.equals(correctAnswer);
                                String answerClass = isCorrect ? "correct-answer" : "incorrect-answer";
                        %>
                                <p class="answer <%= answerClass %>"><strong>Correct answer:</strong> <%= correctAnswer %></p>
                                <p class="explanation"><strong>Explanation:</strong> <%= explain.get(i) %></p>
                        <% } %>
                    </div>
        <% 
                }
            } else {
        %>
                <p>No questions available.</p>
        <% } %>
        <button type="submit" class="submit-button">Submit</button>
    </form>
    
    <% 
        Double score = (Double)request.getAttribute("score");
        if (score != null) { 
    %>
        <div class="score">Score: <%= score %>%</div>
    <% } %>
</body>
</html>
