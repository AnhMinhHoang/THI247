/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Exam;

import DAO.ExamDAO;
import DAO.QuestionDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.QuestionBank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author sonhu
 */
@WebServlet("/takeExam")
public class TakeExamServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve examId from request parameters
        String examIdStr = request.getParameter("examId");
        try {
            int examId = Integer.parseInt(examIdStr);
            // Retrieve questions for the exam from your data source
            List<QuestionBank> questions = new ExamDAO().getQuestionsByExamId(examId);
            // Set attributes in request
            request.setAttribute("examId", examIdStr);
            request.setAttribute("questions", questions);

            // Forward request to JSP page
            request.getRequestDispatcher("takeExam.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid examId parameter");
        } catch (SQLException e) {
            throw new ServletException("Database error while retrieving questions for the exam", e);
        }
    }

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String examIdStr = request.getParameter("examId");
    String[] questionIds = request.getParameterValues("questionIds");

    try {
        int examId = Integer.parseInt(examIdStr); 
        // Retrieve questions for the exam
        List<QuestionBank> questions = new ExamDAO().getQuestionsForExam(examId);
        // Process the submitted answers
        double score = 0.0;
        int numCorrect = 0;
        List<String> userAnswers = new ArrayList<>();
        List<String> correctAnswers = new ArrayList<>();
        List<String> explanations = new ArrayList<>();
        List<String> questionTexts = new ArrayList<>();
        List<Boolean> answerCorrectness = new ArrayList<>();

        for (String questionId : questionIds) {
            String userAnswer = request.getParameter("userAnswer_" + questionId);
            String correctAnswer = null;
            String explanation = null;
            String questionText = null;

            // Get the correct answer and explanation for this question
            for (QuestionBank question : questions) {
                if (question.getId() == Integer.parseInt(questionId)) {
                    correctAnswer = question.getCorrectAnswer();
                    explanation = question.getExplain();
                    questionText = question.getQuestionText();
                    break;
                }
            }

            // Compare user's answer with correct answer
            boolean isCorrect = userAnswer != null && userAnswer.equals(correctAnswer);
            if (isCorrect) {
                numCorrect++;
            }

            userAnswers.add(userAnswer);
            correctAnswers.add(correctAnswer);
            explanations.add(explanation);
            questionTexts.add(questionText);
            answerCorrectness.add(isCorrect);
        }

        // Calculate score
        if (!questions.isEmpty()) {
            score = ((double) numCorrect / questions.size()) * 100.0;
        }

        // Set attributes in request for result page
        request.setAttribute("examId", examIdStr);
        request.setAttribute("numQuestions", questions.size());
        request.setAttribute("score", score);
        request.setAttribute("userAnswers", userAnswers);
        request.setAttribute("correctAnswers", correctAnswers);
        request.setAttribute("explanations", explanations);
        request.setAttribute("questionTexts", questionTexts);
        request.setAttribute("answerCorrectness", answerCorrectness);

        // Forward to examResult.jsp
        request.getRequestDispatcher("examResult.jsp").forward(request, response);
    } catch (NumberFormatException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid examId parameter");
    } catch (SQLException e) {
        throw new ServletException("Database error while retrieving questions for the exam", e);
    }
}



@Override
    public String getServletInfo() {
        return "Short description";
    }
}