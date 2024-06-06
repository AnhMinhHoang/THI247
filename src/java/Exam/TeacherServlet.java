/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Exam;

import DAO.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import model.MultipleChoiceQuestion;

/**
 *
 * @author sonhu
 */
@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
   
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
            out.println("<title>Servlet CrquizServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CrquizServlet at " + request.getContextPath () + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        QuestionDAO questionDAO = new QuestionDAO();

        try {
            if ("create".equals(action)) {
                String questionText = request.getParameter("questionText");
                List<String> choices = Arrays.asList(
                        request.getParameter("choice1"),
                        request.getParameter("choice2"),
                        request.getParameter("choice3"),
                        request.getParameter("choice4")
                );
                String correctAnswer = request.getParameter("correctAnswer");
                String explain = request.getParameter("explain");

                MultipleChoiceQuestion question = new MultipleChoiceQuestion(0, questionText, choices, correctAnswer, explain);
                questionDAO.createMultipleChoiceQuestion(question);

            } else if ("update".equals(action)) {
                long id = Long.parseLong(request.getParameter("id"));
                String questionText = request.getParameter("questionText");
                List<String> choices = Arrays.asList(
                        request.getParameter("choice1"),
                        request.getParameter("choice2"),
                        request.getParameter("choice3"),
                        request.getParameter("choice4")
                );
                String correctAnswer = request.getParameter("correctAnswer");
                String explain = request.getParameter("explain");

                MultipleChoiceQuestion question = new MultipleChoiceQuestion(id, questionText, choices, correctAnswer, explain);
                questionDAO.updateMultipleChoiceQuestion(question);

            }
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid number format for ID.");
            e.printStackTrace();
        } catch (Exception e) {
            response.getWriter().write("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        response.sendRedirect("Teacherquiz.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        QuestionDAO questionDAO = new QuestionDAO();

        try {
            if ("delete".equals(action)) {
                String idStr = request.getParameter("id");
                if (idStr != null) {
                    int id = Integer.parseInt(idStr);
                    questionDAO.deleteMultipleChoiceQuestion(id);
                }
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid number format for ID.");
            e.printStackTrace();
        } catch (Exception e) {
            response.getWriter().write("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        response.sendRedirect("Teacherquiz.jsp");
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
