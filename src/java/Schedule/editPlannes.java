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
import java.sql.Date;
import java.text.SimpleDateFormat;
import model.Planner;
import java.text.ParseException;

/**
 *
 * @author sonhu
 */
@WebServlet(name = "editPlannes", urlPatterns = {"/editPlannes"})
public class editPlannes extends HttpServlet {

      private final PlannerDAO plannerDAO = new PlannerDAO();

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
        int plannerId = Integer.parseInt(request.getParameter("plannerId"));
        Planner planner = plannerDAO.getPlannerById(plannerId);
System.out.println("Received plannerId: " + plannerId);
        if (planner != null) {
            request.setAttribute("planner", planner);
            request.getRequestDispatcher("editPlanner.jsp").forward(request, response);
        } else {
            response.sendRedirect("ListPlannersServlet");
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

        // Retrieve parameters from request
        int plannerId = Integer.parseInt(request.getParameter("plannerId"));
        String plannerName = request.getParameter("plannerName");
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date endTime;
        Date startTime;
        try {
            startTime = new java.sql.Date(dateFormat.parse(startTimeStr).getTime());
            endTime = new java.sql.Date(dateFormat.parse(endTimeStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid date format");
            request.getRequestDispatcher("editPlanner.jsp").forward(request, response);
            return;
        }

        // Create Planner object with updated information
        Planner updatedPlanner = new Planner(plannerId, plannerName,startTime, endTime);

        // Update Planner in the database
        boolean success = plannerDAO.updatePlanner(updatedPlanner);

        if (success) {
            response.sendRedirect("PlannerListServlet");
        } else {
            request.setAttribute("errorMessage", "Failed to update planner");
            request.getRequestDispatcher("editPlanner.jsp").forward(request, response);
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
