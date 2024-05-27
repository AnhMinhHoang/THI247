/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

/**
 *
 * @author GoldCandy
 */
public class UpdateController extends HttpServlet {

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
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        if(oldPassword == null) oldPassword = "";
        String newPassword = request.getParameter("newPassword");
        if(newPassword == null) newPassword = "";
        String confirmPassword = request.getParameter("confirmPassword");
        if(confirmPassword == null) confirmPassword = "";
        
        HttpSession session = request.getSession();
        Users user = (Users)session.getAttribute("currentUser");
        if(user.getPassword().isEmpty()){
            response.sendRedirect("registerGmail.jsp");
        }
        
        if(!oldPassword.isEmpty() && !newPassword.isEmpty() && !confirmPassword.isEmpty()){
            if(!oldPassword.equals(user.getPassword())){
                request.setAttribute("message_password", "Wrong password");
                request.getRequestDispatcher("editprofile.jsp").forward(request, response);
            }
            if(!newPassword.equals(confirmPassword)){
                request.setAttribute("message_confirm", "Password is not match");
                request.getRequestDispatcher("editprofile.jsp").forward(request, response);
            }
            else{
                new UserDAO().updateInfo(user.getEmail(), username, fullname, newPassword);
                request.setAttribute("message", "Success");
                user = new UserDAO().findByEmail(user.getEmail());
                session.setAttribute("currentUser", user);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
        }
        else{
            new UserDAO().updateInfo(user.getEmail(), username, fullname, user.getPassword());
            request.setAttribute("message", "Successe");
            user = new UserDAO().findByEmail(user.getEmail());
            session.setAttribute("currentUser", user);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
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
