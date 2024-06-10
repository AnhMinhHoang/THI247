/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String dobStr = request.getParameter("dob");
        String formattedDobStr = "";

        if (dobStr != null && !dobStr.isEmpty()) {
            try {
                // Define the date format for parsing
                SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Parse the date string into a Date object
                Date dob = originalFormat.parse(dobStr);

                // Define the date format for formatting
                SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");

                // Format the Date object into the new format
                formattedDobStr = newFormat.format(dob);
            } catch (ParseException e) {
                e.printStackTrace(); // Handle the exception as needed
            }
        }

        HttpSession session = request.getSession();
        Users user = (Users)session.getAttribute("currentUser");
        new UserDAO().updateInfo(user.getEmail(), username, fullname, phone, address, formattedDobStr);
        request.setAttribute("message", "Cập nhật thành công!");
        user = new UserDAO().findByEmail(user.getEmail());
        session.setAttribute("currentUser", user);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
