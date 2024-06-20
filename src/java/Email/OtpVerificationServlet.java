/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Email;

import DAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author sonhu
 */
@WebServlet("/VerifyOtpServlet")
public class OtpVerificationServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
//       String otpEntered = request.getParameter("otp");
//        HttpSession session = request.getSession();
//        String email = (String) session.getAttribute("otp");
//        // Kiểm tra xem OTP nhập vào có khớp với mã OTP đã lưu trong session không
//        if (OTP.verifyOtp(email, otpEntered)) {
//            // Xác thực thành công, redirect đến trang chủ hoặc trang thành công
//            session.setAttribute("otp_verified", true);
//            response.sendRedirect("Home");
//        } else {
//            // Xác thực thất bại, redirect lại trang nhập OTP với thông báo lỗi
//            session.setAttribute("verificationError", "Invalid OTP. Please try again.");
//            response.sendRedirect("otp_verification.jsp");
//        }
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
        String otpEntered = request.getParameter("otp");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
 System.out.println("Received POST request with otppppp: " + otpEntered + ", email: " + email); // Log in
        // Verify OTP
        if (OTP.verifyOtp(email, otpEntered)) {
            // OTP verified successfully
            session.setAttribute("otp_verified", true);
            response.sendRedirect("Home");
        } else {
            // OTP verification failed
            session.setAttribute("verificationError", "Invalid OTP. Please try again.");
            response.sendRedirect("otp_verification.jsp");
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
