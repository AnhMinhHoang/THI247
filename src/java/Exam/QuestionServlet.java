/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Exam;

import DAO.QuestionDAO;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author sonhu
 */
   import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.MultipleChoiceQuestion;

@WebServlet("/questions")
public class QuestionServlet extends HttpServlet {
   
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


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuestionDAO questionDAO = new QuestionDAO();
        List<MultipleChoiceQuestion> questions = questionDAO.getAllMultipleChoiceQuestions();
        request.setAttribute("questions", questions);
        request.getRequestDispatcher("quiz.jsp").forward(request, response);
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
    QuestionDAO questionDAO = new QuestionDAO();
    List<MultipleChoiceQuestion> questions = questionDAO.getAllMultipleChoiceQuestions();
    
    List<String> userAnswers = new ArrayList<>();
    List<String> correctAnswers = new ArrayList<>(); // Lưu trữ đáp án đúng
    
    for (MultipleChoiceQuestion question : questions) {
        String userAnswerIndex = request.getParameter("answer_" + question.getId());
        int index = Integer.parseInt(userAnswerIndex);
        List<String> choices = question.getChoices();
        String userAnswer = choices.get(index);
        userAnswers.add(userAnswer);
        // Lấy đáp án đúng và lưu trữ
        String correctAnswer = question.getCorrectAnswer();
        correctAnswers.add(correctAnswer);
    }
    
    double score = questionDAO.calculateScore(userAnswers, questions);
    
    // Lưu trữ thông tin câu trả lời của người dùng và đáp án đúng để hiển thị lại trong JSP
   request.setAttribute("userAnswers", userAnswers);
request.setAttribute("correctAnswers", correctAnswers);
    request.setAttribute("score", score);
    request.setAttribute("questions", questions); // Truyền danh sách câu hỏi để hiển thị lại
    request.getRequestDispatcher("quiz.jsp").forward(request, response);
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
