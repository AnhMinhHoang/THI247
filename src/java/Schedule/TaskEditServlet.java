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
import model.Task;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author sonhu
 */
@WebServlet(name = "TaskEditServlet", urlPatterns = {"/TaskEditServlet"})
public class TaskEditServlet extends HttpServlet {

    private final PlannerDAO plannerDAO = new PlannerDAO();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        Task task = plannerDAO.getTaskById(taskId);
         
        if (task != null) {
            request.setAttribute("task", task);
            request.getRequestDispatcher("editTasks.jsp").forward(request, response);
        } else {
            response.sendRedirect("TaskListServlet?taskId=" + taskId);
        }
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
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String taskName = request.getParameter("taskName");
        String taskDateStr = request.getParameter("taskDate");
        String taskTimeStr = request.getParameter("taskTime");

        // Validate and parse taskDate and taskTime
            Timestamp taskDate = Timestamp.valueOf(taskDateStr + " " + taskTimeStr + ":00");
            Task updateTask = new Task(taskId, taskName, taskDate);

            boolean success = plannerDAO.updateTask(updateTask);

            if (success) {
                response.sendRedirect("TaskListServlet?plannerId=" + updateTask.getPlannerId());
            } else {
                request.setAttribute("errorMessage", "Failed to update task");
                request.getRequestDispatcher("editTasks.jsp").forward(request, response);
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
