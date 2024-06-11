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
import model.Exam;
import model.QuestionBank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sonhu
 */
@WebServlet("/createexam")
public class CreateExamServlet extends HttpServlet {
   
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuestionDAO questionDAO = new QuestionDAO();
        List<QuestionBank> questions = questionDAO.getAllMultipleChoiceQuestions();

        if (questions == null || questions.isEmpty()) {
            response.sendRedirect("no-questions.jsp");
            return;
        }

        request.setAttribute("questions", questions);
        request.getRequestDispatcher("create-exam.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String examName = request.getParameter("examName");
    String[] selectedQuestions = request.getParameterValues("selectedQuestions");

    if (selectedQuestions == null || selectedQuestions.length == 0) {
        response.sendRedirect("create-exam.jsp?error=no-questions-selected");
        return;
    }

    Exam exam = new Exam(examName);
    QuestionDAO questionDAO = new QuestionDAO();
    List<QuestionBank> allQuestions = questionDAO.getAllMultipleChoiceQuestions();
    List<QuestionBank> selectedQuestionList = new ArrayList<>();

    for (String questionId : selectedQuestions) {
        int id = Integer.parseInt(questionId);
        for (QuestionBank question : allQuestions) {
            if (question.getId() == id) {
                selectedQuestionList.add(question);
                break;
            }
        }
    }

    exam.setQuestions(selectedQuestionList);

    ExamDAO examDAO = new ExamDAO();
    try {
        boolean success = examDAO.createExam(exam);
        if (success) {
            response.sendRedirect("exam-created.jsp");
        } else {
            response.sendRedirect("create-exam.jsp?error=1");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        response.sendRedirect("create-exam.jsp?error=2");
    }
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}