<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.QuestionBank" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài kiểm tra</title>
    <!-- CSS Styles -->
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
        h1 {
            color: #333;
            text-align: center;
        }
        form {
            margin-top: 20px;
        }
        fieldset {
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 8px;
        }
        legend {
            font-weight: bold;
            padding: 0 10px;
        }
        .question {
            margin-bottom: 20px;
        }
        .question p {
            font-size: 16px;
            font-weight: bold;
        }
        .question label {
            display: block;
            margin-bottom: 10px;
        }
        .question input[type="radio"] {
            margin-right: 10px;
        }
        hr {
            border: 0;
            border-top: 1px solid #ddd;
            margin: 20px 0;
        }
        .submit-button {
            display: block;
            width: 100%;
            padding: 10px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-align: center;
        }
        .submit-button:hover {
            background-color: #0056b3;
        }
        .no-questions {
            text-align: center;
            color: #666;
        }
    </style>
</head>
<%
int examId = (Integer)session.getAttribute("examId");
%>
<body>
    <div class="container">
        <h1>Bài kiểm tra</h1>
        <form action="takeExam" method="post">
            <input type="hidden" name="examId" value="<%=examId%>">
            <fieldset>
                <legend>Câu hỏi kiểm tra</legend>
                <% 
                    List<QuestionBank> questions = (List<QuestionBank>) session.getAttribute("questions");
                    boolean isShuffle = (boolean)session.getAttribute("isShuffle");
                    session.setAttribute("isShuffle", false);
                    if (questions != null && !questions.isEmpty()) {
                        // Lưu số lượng câu hỏi vào request
                        request.setAttribute("numQuestions", questions.size());
                        for (int i = 0; i < questions.size(); i++) {
                            QuestionBank question = questions.get(i);
                            // Trộn lựa chọn
                            List<String> choices = new ArrayList<>(question.getChoices());
                            if(isShuffle == true) {
                                Collections.shuffle(choices);
                                session.setAttribute("choices" + i, choices);
                            } 
                            else{
                                choices = (List<String>)session.getAttribute("choices" + i);
                            }       
                            
                            // Lưu trữ câu trả lời đúng của câu hỏi vào request
                            String correctAnswer = question.getCorrectAnswer();
                            request.setAttribute("correctAnswer_" + question.getId(), correctAnswer);
                %>
                <div class="question">
                    <p><%= question.getQuestionText() %></p>
                    <!-- Lưu trữ ID câu hỏi -->
                    <input type="hidden" name="questionIds" value="<%= question.getId() %>">
                    <% 
                        // Hiển thị các lựa chọn
                        for (String choice : choices) { 
                    %>
                        <label>
                            <input type="radio" name="userAnswer_<%= question.getId() %>" value="<%= choice %>">
                            <%= choice %>
                        </label><br>
                    <% } %>
                </div>
                <% if (questions.indexOf(question) < questions.size() - 1) { %>
                <hr>
                <% } %>
                <% } 
                    } else { %>
                <p class="no-questions">Không có câu hỏi nào được tìm thấy.</p>
                <% } %>
                <!-- Thêm dòng sau để giữ lại examId -->
                <input type="hidden" name="examId" value="<%= examId %>">
                <input type="hidden" name="examName" value="<%= session.getAttribute("examName") %>">
            </fieldset>
            <input type="submit" value="Nộp bài" class="submit-button">
        </form>
    </div>
</body>
</html>
