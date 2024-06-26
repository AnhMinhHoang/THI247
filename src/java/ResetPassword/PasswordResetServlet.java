/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package ResetPassword;

import java.io.IOException;
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
@WebServlet("/PasswordResetServlet")
public class PasswordResetServlet extends HttpServlet {
  
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
        String token = request.getParameter("token");
        String newPassword = request.getParameter("newPassword");
      

      

        // Proceed with password update
        String userId = PasswordResetUtil.getUserIdByToken(token);
        if (userId != null) {
            // Token hợp lệ, tiếp tục xử lý đổi mật khẩu
            boolean updated = PasswordResetUtil.updatePassword(userId, newPassword);

            if (updated) {
                // Xóa token sau khi cập nhật mật khẩu (nếu cần thiết)
                PasswordResetUtil.deleteToken(token);
                response.sendRedirect(request.getContextPath() + "/login.jsp"); // Đổi mật khẩu thành công
            } else {
                request.setAttribute("errorMessage", "Đổi mật khẩu thất bại, mật khẩu nhập không trùng");
                // Forward to resetpassword.jsp with token parameter
                request.getRequestDispatcher("/resetpassword.jsp?token=" + token).forward(request, response);
            }
        } else {
            // Token không hợp lệ hoặc đã hết hạn
            response.sendRedirect(request.getContextPath() + "/invalidToken.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}