package controller;

import DAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

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
    
    if(new UserDAO().findByEmail(email) != null){
        request.setAttribute("errorMessage", "email already exist");
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
    else if(new UserDAO().findByUserName(userName) != null){
        request.setAttribute("errorMessage", "Username already exist");
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
    else{
        boolean isLecturer = false; // Assuming user is not admin by default

        // Perform user registration
        UserDAO userDAO = new UserDAO();
        boolean registered = userDAO.registerUser(userName, passWord, email,  isLecturer);

        if (registered) {
            // Redirect to registration success page
            request.setAttribute("successMessage", "Register successful");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            // Redirect to registration failure page
            request.setAttribute("errorMessage", "Register fail");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}


    @Override
    public String getServletInfo() {
         return "Short description";
    }
}
