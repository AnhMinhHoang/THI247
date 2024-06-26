/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package OTP;

import OTP.OTP;
import DAO.UserDAO;
import Email.EmailSender;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
/**
 *
 * @author sonhu
 */
@WebServlet(name="ResendOtpServlet", urlPatterns={"/ResendOtpServlet"})
public class ResendOtpServlet extends HttpServlet {
   
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
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        if (email != null) {
            // Generate new OTP
            String otp = OTP.generateOTP();
            int userId = new UserDAO().getUserIdByEmail(email);
            Timestamp expiryTime = new Timestamp(System.currentTimeMillis() + (1 * 60 * 1000));
            OTP.saveOtpToDatabase(userId, otp, expiryTime, false);

            // Send OTP to user's email
            EmailSender.sendOtpToEmail(email, otp);

            // Update session with new OTP and expiry time
            session.setAttribute("otpExpiryTime", expiryTime.getTime());

            // Redirect back to OTP verification page with success message
            session.setAttribute("successMessage", "New OTP sent successfully. Please check your email.");
            response.sendRedirect("otp_verification.jsp");
        } else {
            session.setAttribute("errorMessage", "Error: Email not found.");
            response.sendRedirect("otp_verification.jsp");
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
        processRequest(request, response);
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
