package Exam;

import DAO.QuestionDAO;
import model.QuestionBank;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/BankQuestionServlet")
public class BankQuestionServlet extends HttpServlet {
    private QuestionDAO questionDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        questionDAO = new QuestionDAO();
    }

   


@Override
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
        try {
            List<QuestionBank> questions = questionDAO.getAllMultipleChoiceQuestions();
            for (QuestionBank question : questions) {
                String subjectName = questionDAO.getSubjectNameById(question.getSubjectId()); // Lấy subjectName từ subjectId
                question.setSubject(subjectName); // Đặt subjectName vào đối tượng QuestionBank
            }
            request.setAttribute("questions", questions);
            RequestDispatcher dispatcher = request.getRequestDispatcher("BankQuestion.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
            response.sendRedirect("error.jsp");
        }
    }
}

    
    @Override
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

private void updateQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String subject = request.getParameter("subject");
    int userId = Integer.parseInt(request.getParameter("userId")); // Chuyển đổi userId thành số nguyên nếu userId là số nguyên
    String questionText = request.getParameter("questionText"); // Giữ questionText dưới dạng chuỗi
    String choice1 = request.getParameter("choice1");
    String choice2 = request.getParameter("choice2");
    String choice3 = request.getParameter("choice3");
    String correctAnswer = request.getParameter("correctAnswer");
    String explain = request.getParameter("explain");

    List<String> choices = List.of(choice1, choice2, choice3);

    QuestionBank question = new QuestionBank(id, userId, questionText, subject, choices, correctAnswer, explain);

    try {
        boolean updated = questionDAO.updateMultipleChoiceQuestion(question);
        if (updated) {
            // Redirect back to the page displaying the questions
            response.sendRedirect("BankQuestionServlet");
        } else {
            // Handle update failure
            // You can redirect to an error page or display an error message
            // You can also forward the request to the doGet method to display the questions again
            response.sendRedirect("error.jsp");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQL exception
        // You can redirect to an error page or display an error message
        // You can also forward the request to the doGet method to display the questions again
        response.sendRedirect("error.jsp");
    }
}

    private void addQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String questionText = request.getParameter("questionText");
        String subject = request.getParameter("subject");
        String choice1 = request.getParameter("choice1");
        String choice2 = request.getParameter("choice2");
        String choice3 = request.getParameter("choice3");
        String correctAnswer = request.getParameter("correctAnswer");
        String explain = request.getParameter("explain");

        List<String> choices = List.of(choice1, choice2, choice3);

        QuestionBank question = new QuestionBank(0, 0, userId, questionText, subject, choices, correctAnswer, explain);

        try {
            boolean added = questionDAO.createMultipleChoiceQuestion(question);
            if (added) {
                response.sendRedirect("BankQuestionServlet");
            } else {
                // Handle add failure
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }

    

    private void deleteQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            boolean deleted = questionDAO.deleteMultipleChoiceQuestion(id);
            if (deleted) {
                response.sendRedirect("BankQuestionServlet");
            } else {
                // Handle delete failure
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }
}
