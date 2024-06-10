<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.QuestionBank" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài kiểm tra</title>
    <!-- CSS Styles -->
    <style>
        <!-- CSS styles here -->
    </style>
</head>
<body>
    <div class="container">
        <h1>Bài kiểm tra</h1>
        <form action="takeExam" method="post">
            <fieldset>
                <legend>Câu hỏi kiểm tra</legend>
                <% 
                    List<QuestionBank> questions = (List<QuestionBank>) request.getAttribute("questions");
                    if (questions != null && !questions.isEmpty()) {
                        // Lưu số lượng câu hỏi vào request
                        request.setAttribute("numQuestions", questions.size());
                        for (QuestionBank question : questions) {
                            // Trộn lựa chọn
                            List<String> choices = new ArrayList<>(question.getChoices());
                            Collections.shuffle(choices); 
                            
                            // Lưu trữ câu trả lời đúng của câu hỏi vào request
                            String correctAnswer = question.getCorrectAnswer();
                            request.setAttribute("correctAnswer_" + question.getId(), correctAnswer);
                %>
                <div class="question">
                    <p><%= question.getQuestionText() %></p>
                    <input type="hidden" name="questionIds" value="<%= question.getId() %>">
                    <% 
                        // Hiển thị các lựa chọn
                        for (int i = 0; i < choices.size(); i++) { 
                            // Lấy câu trả lời đúng của câu hỏi từ request
                            String correctAnswerValue = (String) request.getAttribute("correctAnswer_" + question.getId());
                    %>
                        <label><input type="radio" name="answers_<%= question.getId() %>" value="<%= i %>" 
                            <% if (correctAnswerValue.equals(Integer.toString(i))) { %> checked <% } %>
                            > <%= choices.get(i) %></label><br>
                    <% } %>
                </div>
                <% if (questions.indexOf(question) < questions.size() - 1) { %>
                <hr>
                <% } %>
                <% } 
                    } else { %>
                <p>Không có câu hỏi nào được tìm thấy.</p>
                <% } %>

                <!-- Thêm dòng sau để giữ lại examId -->
               <input type="hidden" name="examId" value="<%= request.getParameter("examId") %>">
               <input type="hidden" name="examName" value="<%= request.getParameter("examName") %>">
            </fieldset>
            <input type="submit" value="Nộp bài">
        </form>
    </div>
</body>
</html>
