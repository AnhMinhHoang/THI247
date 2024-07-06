/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package examStudentController;

import DAO.ExamDAO;
import DAO.StudentExamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.QuestionBank;
import model.Tests;
import model.Users;

/**
 *
 * @author GoldCandy
 */
public class SubmitTest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("currentUser");
        int totalQuestion = Integer.parseInt(request.getParameter("numberOfQuestion"));
        int testID = Integer.parseInt(request.getParameter("testID"));
        int examID = Integer.parseInt(request.getParameter("examID"));
        int rightAnswer = 0;

        for (int i = 0; i < totalQuestion; i++) {
            String answer = request.getParameter("answer" + i);
            if (answer != null && !answer.isBlank()) {
                int questionID = Integer.parseInt(request.getParameter("question" + i));
                QuestionBank qb = new ExamDAO().getQuestionByID(questionID);
                if (answer.equals(qb.getChoiceCorrect())) {
                    rightAnswer++;
                }
            }
        }

        float score = ((float)rightAnswer * 10) / totalQuestion;
        
        //convert score to 0.00 format
        String formattedScore = String.format("%.2f", score);
        float finalScore = Float.parseFloat(formattedScore);

        //create result
        new StudentExamDAO().createResult(user.getUserID(), examID, finalScore, rightAnswer, totalQuestion, testID);
        Tests test = new StudentExamDAO().getTestByTestID(testID);
        
        int resultID = new StudentExamDAO().getLastestResult().getResultID();
        session.setAttribute("resultID", resultID);
        session.setAttribute("examID", examID);
        session.setAttribute("test", test);
        
        response.sendRedirect("result.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
