package Exam;

import DAO.QuestionDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.QuestionBank;


import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
    private QuestionDAO questionDAO = new QuestionDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    addQuestion(request, response);
                    break;
                case "update":
                    updateQuestion(request, response);
                    break;
                default:
                    // Handle other actions
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "delete":
                    deleteQuestion(request, response);
                    break;
                default:
                    // Handle other actions
            }
        } else {
            // Retrieve questions and forward to JSP
            List<QuestionBank> questions = questionDAO.getAllMultipleChoiceQuestions();
            request.setAttribute("questions", questions);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Teacherquiz.jsp");
            dispatcher.forward(request, response);
        }
    }

private void addQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String subject = request.getParameter("subject");
    int userId = Integer.parseInt(request.getParameter("userId"));
    String questionText = request.getParameter("questionText");
    String choice1 = request.getParameter("choice1");
    String choice2 = request.getParameter("choice2");
    String choice3 = request.getParameter("choice3");
    String correctAnswer = request.getParameter("correctAnswer");
    String explain = request.getParameter("explain");

    List<String> choices = new ArrayList<>();
    choices.add(choice1);
    choices.add(choice2);
    choices.add(choice3);

    // Sử dụng constructor đã được định nghĩa trong lớp QuestionBank
    QuestionBank question = new QuestionBank(0, subject, userId, questionText, choices, correctAnswer, explain);

    try {
        boolean added = questionDAO.createMultipleChoiceQuestion(question);
        if (added) {
            response.sendRedirect("TeacherServlet");
        } else {
            // Xử lý khi thêm câu hỏi thất bại
        }
    } catch (SQLException e) {
        // Xử lý ngoại lệ SQL
        e.printStackTrace();
    }
}



    private void updateQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String subject = request.getParameter("subject");
        int userId = Integer.parseInt(request.getParameter("userId"));
        String questionText = request.getParameter("questionText");
        String choice1 = request.getParameter("choice1");
        String choice2 = request.getParameter("choice2");
        String choice3 = request.getParameter("choice3");
        String correctAnswer = request.getParameter("correctAnswer");
        String explain = request.getParameter("explain");

        List<String> choices = List.of(choice1, choice2, choice3);

        QuestionBank question = new QuestionBank(id, subject, userId, questionText, choices, correctAnswer, explain);

        try {
            boolean updated = questionDAO.updateMultipleChoiceQuestion(question);
            if (updated) {
                response.sendRedirect("TeacherServlet");
            } else {
                // Handle update failure
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            boolean deleted = questionDAO.deleteMultipleChoiceQuestion(id);
            if (deleted) {
                response.sendRedirect("TeacherServlet");
            } else {
                // Handle delete failure
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
