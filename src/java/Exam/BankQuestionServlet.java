package Exam;

import DAO.QuestionDAO;
import model.QuestionBank;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Users;

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
                case "add":
                    addQuestion(request, response);
                    break;
                case "update":
                    updateQuestion(request, response);
                    break;
                case "delete":
                    deleteQuestion(request, response);
                    break;
                default:
                // Handle other actions
            }
        } else {
            try {
                HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("userId") != null) {
                int userId = (int) session.getAttribute("userId");
                try {
                    // Retrieve questions created by the logged-in user and forward to JSP
                    List<QuestionBank> questions = questionDAO.getAllMultipleChoiceQuestions(userId);

                    for (QuestionBank question : questions) {
                        String subjectName = questionDAO.getSubjectNameById(question.getSubjectId()); // Lấy subjectName từ subjectId
                        question.setSubject(subjectName); // Đặt subjectName vào đối tượng QuestionBank
                    }

                    request.setAttribute("questions", questions);
                List<String> subjects = questionDAO.getAllSubjects();
                request.setAttribute("subjects", subjects);
                RequestDispatcher dispatcher = request.getRequestDispatcher("BankQuestion.jsp");
                dispatcher.forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace(); // Xử lý ngoại lệ
                response.sendRedirect("error.jsp");
            }
            }
            }catch (Exception err){
                System.out.println(err);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            // Retrieve userId from session
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("userId") != null) {
                int userId = (int) session.getAttribute("userId");
                try {
                    // Retrieve questions created by the logged-in user and forward to JSP
                    List<QuestionBank> questions = questionDAO.getAllMultipleChoiceQuestions(userId);

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
            } else {
                // Handle unauthorized access
                response.sendRedirect("login.jsp");
            }
        }
    }

    private void addQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ request
        HttpSession session = request.getSession();
        Users user = (Users)session.getAttribute("currentUser");
        Integer userId = user.getUserID();
        String questionText = request.getParameter("questionText");
        String subject = request.getParameter("subject");
        int subjectID = new QuestionDAO().getSubjectIdByName(subject);
        String choice1 = request.getParameter("choice1");
        String choice2 = request.getParameter("choice2");
        String choice3 = request.getParameter("choice3");
        String correctAnswer = request.getParameter("correctAnswer");
        String explain = request.getParameter("explain");

        // Tạo danh sách các lựa chọn
        List<String> choices = Arrays.asList(choice1, choice2, choice3);

        // Tạo đối tượng QuestionBank
        QuestionBank newQuestion = new QuestionBank(subjectID, userId, questionText, choices, correctAnswer, explain);

        // Gọi phương thức của DAO để thêm câu hỏi vào cơ sở dữ liệu
        try {
            QuestionDAO questionssDAO = new QuestionDAO();
            boolean success = questionssDAO.createMultipleChoiceQuestion(newQuestion);
            if (success) {
                // Nếu thành công, chuyển hướng người dùng đến trang danh sách câu hỏi
                response.sendRedirect("BankQuestionServlet");
            } else {
                // Nếu không thành công, chuyển hướng đến trang lỗi
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra stack trace của lỗi vào console
            System.out.println("SQLException: " + e.getMessage());
            response.sendRedirect("Home");
        }
    }

    private void updateQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String questionText = request.getParameter("questionText");
        String subject = request.getParameter("subject");
        String choice1 = request.getParameter("choice1");
        String choice2 = request.getParameter("choice2");
        String choice3 = request.getParameter("choice3");
        String correctAnswer = request.getParameter("correctAnswer");
        String explain = request.getParameter("explain");

        List<String> choices = Arrays.asList(choice1, choice2, choice3);
        QuestionBank updatedQuestion = new QuestionBank(id, userId, questionText, subject, choices, correctAnswer, explain);

        try {
            QuestionDAO questionDAO = new QuestionDAO();
            boolean success = questionDAO.updateMultipleChoiceQuestion(updatedQuestion);
            if (success) {
                response.sendRedirect("BankQuestionServlet");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra stack trace của lỗi vào console
            System.out.println("SQLException: " + e.getMessage()); // In ra thông điệp lỗi

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
            e.printStackTrace(); // In ra stack trace của lỗi vào console
            System.out.println("SQLException: " + e.getMessage()); // In ra thông điệp lỗi

        }
    }
}
