/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Exam;

import DAO.ExamDAO;
import DAO.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.sql.SQLException;
import java.util.Arrays;
import model.QuestionBank;
/**
 *
 * @author sonhu
 */
@WebServlet("/ExamQuestionsServlet")
public class ExamQuestionsServlet extends HttpServlet {

    private ExamDAO examDAO;
    private QuestionDAO questionDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        examDAO = new ExamDAO();
        questionDAO = new QuestionDAO();
    }  
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    addQuestionToExam(request, response);
                    break;
                case "remove":
                    removeQuestionFromExam(request, response);
                    break;
                case "update":
                    updateQuestionInExam(request, response);
                    break;
                default:
                    // Handle other actions
            }
        } else {
            // Load questions for the selected exam
            int examId = Integer.parseInt(request.getParameter("examId"));
            try {
                List<QuestionBank> questions = examDAO.getQuestionsForExam(examId);
                request.setAttribute("questions", questions);
                request.getRequestDispatcher("examQuestions.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
private void addQuestionToExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int examId = Integer.parseInt(request.getParameter("examId"));
    int questionId = Integer.parseInt(request.getParameter("questionId"));

    HttpSession session = request.getSession();
    int userId = (int) session.getAttribute("userId");

    try {
        boolean added = examDAO.addQuestionToExam(examId, questionId, userId);
        if (added) {
            response.sendRedirect("ExamQuestionsServlet?examId=" + examId);
        } else {
            // Handle add failure
        }
    } catch (SQLException e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}

    private void removeQuestionFromExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int examId = Integer.parseInt(request.getParameter("examId"));
        int questionId = Integer.parseInt(request.getParameter("questionId"));

        try {
            boolean deleted = examDAO.removeQuestionFromExam(examId, questionId);
            if (deleted) {
                response.sendRedirect("ExamQuestionsServlet?examId=" + examId);
            } else {
                // Handle delete failure
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }



    private void updateQuestionInExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int examId = Integer.parseInt(request.getParameter("examId"));
    int questionId = Integer.parseInt(request.getParameter("questionId"));
    String updatedQuestionText = request.getParameter("updatedQuestionText");
    String updatedChoice1 = request.getParameter("updatedChoice1");
    String updatedChoice2 = request.getParameter("updatedChoice2");
    String updatedChoice3 = request.getParameter("updatedChoice3");
    String updatedCorrectAnswer = request.getParameter("updatedCorrectAnswer");
    String updatedExplain = request.getParameter("updatedExplain");
    
    // Tạo danh sách các lựa chọn mới
    List<String> updatedChoices = Arrays.asList(updatedChoice1, updatedChoice2, updatedChoice3);

    try {
        boolean updated = examDAO.updateQuestionInExam(examId, questionId, updatedQuestionText, updatedChoices, updatedCorrectAnswer, updatedExplain);
        if (updated) {
            response.sendRedirect("ExamQuestionsServlet?examId=" + examId);
        } else {
            // Handle update failure
        }
    } catch (SQLException e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}






    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}