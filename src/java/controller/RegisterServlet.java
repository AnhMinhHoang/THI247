package controller;

import DAO.UserDAO;
import Email.EmailSender;
import OTP.OTP;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;

@WebServlet(name = "RegisterServlet", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Điều hướng người dùng đến trang đăng ký
        response.sendRedirect("register.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        String email = request.getParameter("email");

        UserDAO userDAO = new UserDAO();

        try {
            if (userDAO.findByEmail(email) != null) {
                request.setAttribute("errorMessage", "Email already exists");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            if (userDAO.findByUserName(userName) != null) {
                request.setAttribute("errorMessage", "Username already exists");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            boolean isLecturer = false; // Assuming user is not admin by default

            // Register user
            boolean registered = userDAO.registerUser(userName, passWord, email, isLecturer);

            if (registered) {
                // Generate OTP and save to database
                String otp = OTP.generateOTP();
                int userId = userDAO.getUserIdByEmail(email);
                Timestamp expiryTime = new Timestamp(System.currentTimeMillis() + (5 * 60 * 1000));
            OTP.saveOtpToDatabase(userId, otp, expiryTime, false);

                // Send OTP to user's email
                EmailSender.sendOtpToEmail(email, otp);

                // Store email in session
                HttpSession session = request.getSession();
                session.setAttribute("email", email);

                // Redirect to OTP verification page
                request.setAttribute("successMessage", "Register successful. Please check your email for OTP.");
                request.getRequestDispatcher("otp_verification.jsp").forward(request, response);
            } else {
                // Registration failed
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}