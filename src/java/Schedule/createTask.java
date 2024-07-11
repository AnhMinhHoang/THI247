/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Schedule;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;
import java.sql.Timestamp;
import model.Task;

/**
 *
 * @author sonhu
 */
@WebServlet("/createTask")
public class createTask extends HttpServlet {

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
        // Chuyển tiếp đến trang createTask.jsp
        request.getRequestDispatcher("createTask.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("currentUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String taskContext = request.getParameter("taskContext");
        String taskDateStr = request.getParameter("taskDate");
        String taskTimeStr = request.getParameter("taskTime");

        if (taskContext == null || taskContext.isEmpty() || taskDateStr == null || taskDateStr.isEmpty() || taskTimeStr == null || taskTimeStr.isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin nhiệm vụ.");
            request.getRequestDispatcher("createTask.jsp").forward(request, response);
            return;
        }

        try {
            Timestamp taskDeadline = Timestamp.valueOf(taskDateStr + " " + taskTimeStr + ":00");
            Task newTask = new Task(user.getUserID(), taskContext, taskDeadline);

            TaskDAO taskDAO = new TaskDAO();
            boolean success = taskDAO.createTask(newTask);

            if (success) {
                request.setAttribute("successMessage", "Tạo nhiệm vụ thành công.");
            } else {
                request.setAttribute("errorMessage", "Tạo nhiệm vụ thất bại.");
            }
            request.getRequestDispatcher("createTask.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", "Định dạng ngày giờ không hợp lệ.");
            request.getRequestDispatcher("createTask.jsp").forward(request, response);
        }
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
