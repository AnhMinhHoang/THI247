/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Exam;

import DAO.QuestionDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.QuestionBank;
import java.sql.SQLException;
/**
 *
 * @author sonhu
 */
@WebServlet("/QuestionFilterServlet")
public class QuestionFilterServlet extends HttpServlet {
   private QuestionDAO questionDAO;

    @Override
    public void init() throws ServletException {
        super.init();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Lấy userId từ session
    HttpSession session = request.getSession();
    int userId = (int) session.getAttribute("userId");

    // Lấy subjectName được chọn từ biểu mẫu
    String subjectName = request.getParameter("subject");

    // Truy vấn cơ sở dữ liệu để lấy danh sách câu hỏi theo subjectName và userId
    List<QuestionBank> questions = null;
    try {
        int subjectId = questionDAO.getSubjectIdByName(subjectName); // Lấy subjectId tương ứng với subjectName
        questions = questionDAO.getQuestionsBySubjectIdAndUserId(subjectId, userId); // Lấy danh sách câu hỏi theo subjectId và userId
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý ngoại lệ
    }

    // Lấy danh sách các môn học để hiển thị lại trong trang JSP
    List<String> subjects = null;
    try {
        subjects = questionDAO.getAllSubjects(); // Lấy danh sách các môn học
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý ngoại lệ
    }

    // Truyền danh sách câu hỏi và danh sách môn học đến JSP để hiển thị
    request.setAttribute("questions", questions);
    request.setAttribute("subjects", subjects);
    RequestDispatcher dispatcher = request.getRequestDispatcher("BankQuestion.jsp");
    dispatcher.forward(request, response);
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
