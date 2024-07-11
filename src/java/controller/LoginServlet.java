/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

    private static final String SUCCESS_USER = "Home";
    private static final String SUCCESS_ADMIN = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("elearning-html-template/login.html");
    }

  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            UserDAO userDAO = new UserDAO();
            String status = userDAO.checkLogin(email, password);

            if (status != null) {
                // Lưu thông tin người dùng vào session
                HttpSession session = request.getSession();
                int role = userDAO.getUserType(email);
                Users user = userDAO.findByEmail(email);
                session.setAttribute("currentUser", user);
                session.setMaxInactiveInterval(600); // Thời gian hết hiệu lực của session

                // Chuyển hướng đến trang tương ứng với vai trò người dùng
                if (role == 3 || role == 2) {
                    // Người dùng thường (user)
                    if(user.isBan()) response.sendRedirect("banned.jsp");
                    else response.sendRedirect("Home");
                } else if (role == 1) {
                    // Quản trị viên (admin)
                    response.sendRedirect("admin.jsp");
                }
            } else {
                request.setAttribute("errorMessage", "Wrong email or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
