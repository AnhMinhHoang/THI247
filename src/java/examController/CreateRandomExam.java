/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package examController;

import DAO.ExamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.QuestionBank;
import model.Users;

/**
 *
 * @author GoldCandy
 */
public class CreateRandomExam extends HttpServlet {

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
        int number = Integer.parseInt(request.getParameter("numQuestions"));
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        int examHours = Integer.parseInt(request.getParameter("examHours"));
        int examMinutes = Integer.parseInt(request.getParameter("examMinutes"));
        int price = Integer.parseInt(request.getParameter("price"));
        String examName = request.getParameter("examName");
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("currentUser");
        List<QuestionBank> lists = new ExamDAO().getRandomQuestByAmount(number, subjectID);
        int num = new ExamDAO().getMaxQuestion(user.getUserID(), subjectID);

        if (number > num) {
            request.setAttribute("error", "Vượt quá số câu hỏi có trong ngân hàng câu hỏi, số câu hỏi của môn " + new ExamDAO().getSubjectByID(subjectID).getSubjectName() + " tối đa là: " + num);
            request.getRequestDispatcher("create-exam.jsp").forward(request, response);
        } else {
            int examTime = (examHours * 3600) + (examMinutes * 60);
            if (user.getRole() == 1) {
                new ExamDAO().addExam(examName, 1, subjectID, examTime, price);
                int examID = new ExamDAO().getLastestExam().getExamID();
                for (QuestionBank list : lists) {
                    new ExamDAO().addQuestionToExam(list.getQuestionId(), examID);
                }

                response.sendRedirect("view-all-exam.jsp");
            } else {
                new ExamDAO().addExam(examName, user.getUserID(), subjectID, examTime, price);
                int examID = new ExamDAO().getLastestExam().getExamID();
                for (QuestionBank list : lists) {
                    new ExamDAO().addQuestionToExam(list.getQuestionId(), examID);
                }

                response.sendRedirect("teacher.jsp");
            }
        }
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
