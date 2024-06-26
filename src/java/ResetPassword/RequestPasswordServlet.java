/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package ResetPassword;

import DAO.UserDAO;
import Email.EmailSender;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 *
 * @author sonhu
 */
@WebServlet("/RequestPasswordServlet")
public class RequestPasswordServlet extends HttpServlet {
   
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
            out.println("<title>Servlet NewServlet1</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet1 at " + request.getContextPath () + "</h1>");
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
        String token = request.getParameter("token");
        request.setAttribute("token", token);
        request.getRequestDispatcher("requestPassword.jsp").forward(request, response);
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
    // Lấy email từ request
    String email = request.getParameter("email");

    try {
        UserDAO userDAO = new UserDAO();
        // Lấy userId từ địa chỉ email
        int userId = userDAO.getUserIdByEmail(email); 

        // Generate token
        String token = EmailSender.generateToken();

        // Lưu token và thời gian hết hạn vào cơ sở dữ liệu
        Timestamp expiryTime = new Timestamp(System.currentTimeMillis() + (10 * 60 * 1000));
        PasswordResetUtil.saveTokenToDatabase(userId, token, expiryTime);

        // Tạo đường dẫn reset
        String resetLink = PasswordResetUtil.generateResetLink(token);

        // Gửi email reset
        EmailSender.sendResetEmail(email, resetLink);

        // Chuyển hướng đến trang xác nhận
        response.sendRedirect("resetConfirmation.jsp?userId=" + userId); 
    } catch (Exception ex) {
        ex.printStackTrace();
        // Xử lý exception hoặc thông báo lỗi cho người dùng
      
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
    }
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