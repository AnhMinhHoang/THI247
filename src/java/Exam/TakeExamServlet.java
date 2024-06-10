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

        if (examIdStr == null || examIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing examId parameter");
            return;
        }

        try {
            int examId = Integer.parseInt(examIdStr);

            // Retrieve questions for the exam from your data source
            List<QuestionBank> questions = new ExamDAO().getQuestionsByExamId(examId);

            if (questions == null || questions.isEmpty()) {
                request.setAttribute("errorMessage", "No questions found for this exam.");
                questions = new ArrayList<>(); // Initialize an empty list to avoid NullPointerException
            }

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

    if (examIdStr == null || examIdStr.isEmpty()) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing examId parameter");
        return;
    }

    try {
        int examId = Integer.parseInt(examIdStr);

        // Retrieve questions for the exam
        List<QuestionBank> questions = new ExamDAO().getQuestionsForExam(examId);

        if (questions == null || questions.isEmpty()) {
            request.setAttribute("errorMessage", "No questions found for this exam.");
            questions = new ArrayList<>();
        }

        // Process the submitted answers
        List<String> userAnswers = new ArrayList<>();
        for (QuestionBank question : questions) {
            String selectedAnswerStr = request.getParameter("answers_" + question.getId());
            userAnswers.add(selectedAnswerStr);
        }

        // Calculate score
        double score = new QuestionDAO().calculateScore(userAnswers, questions);

        // Get correct answers
        List<String> correctAnswers = new QuestionDAO().getCorrectAnswers(questions);


        // Set attributes in request for result page
        request.setAttribute("examId", examIdStr);
        request.setAttribute("numQuestions", questions.size());
        request.setAttribute("score", score);
        request.setAttribute("userAnswers", userAnswers);
        request.setAttribute("correctAnswers", correctAnswers);

        // Forward to examResult.jsp
        request.getRequestDispatcher("examResult.jsp").forward(request, response);
    } catch (NumberFormatException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid examId parameter");
    } catch (SQLException e) {
        throw new ServletException("Database error while retrieving questions for the exam", e);
    }
}




    public String getServletInfo() {
        return "Short description";
    }
}