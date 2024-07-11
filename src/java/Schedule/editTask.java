/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Schedule;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import model.Task;
import model.Users;

/**
 *
 * @author sonhu
 */
@WebServlet(name = "editTask", urlPatterns = {"/editTask"})
public class editTask extends HttpServlet {

    private final TaskDAO taskDAO = new TaskDAO();

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editPlannes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editPlannes at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        Task task = taskDAO.getTaskById(taskId);
        if (task != null) {
            request.setAttribute("task", task);
            request.getRequestDispatcher("editTask.jsp").forward(request, response);
        } else {
            response.sendRedirect("TaskListServlet");
        }
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

        // Lấy thông tin từ request để chỉnh sửa Task
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String taskContext = request.getParameter("taskContext");
        String taskDateStr = request.getParameter("taskDate");
        String taskTimeStr = request.getParameter("taskTime");

        // Kiểm tra nếu các tham số là null hoặc trống
        if (taskContext == null || taskContext.isEmpty() || taskDateStr == null || taskDateStr.isEmpty() || taskTimeStr == null || taskTimeStr.isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin nhiệm vụ.");
            request.getRequestDispatcher("editTask.jsp").forward(request, response);
            return;
        }

        // Gán giây mặc định là 00
        Timestamp taskDeadline = Timestamp.valueOf(taskDateStr + " " + taskTimeStr + ":00");

        // Tạo đối tượng Task mới từ thông tin thu thập được
        Task updateTask = new Task(user.getUserID(), taskId, taskContext, taskDeadline);

        // Cập nhật Task trong cơ sở dữ liệu
        boolean success = taskDAO.updateTask(updateTask);

        if (success) {
            response.sendRedirect("TaskListServlet");
        } else {
            request.setAttribute("errorMessage", "Cập nhật nhiệm vụ thất bại");
            request.getRequestDispatcher("editTask.jsp").forward(request, response);
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
