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
import java.sql.Date;
import java.text.SimpleDateFormat;
import model.Planner;
import java.text.ParseException;



/**
 *
 * @author sonhu
 */
@WebServlet("/createPlanners")
public class createPlanners extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
    response.setContentType("text/html;charset=UTF-8");

    // Lấy session hiện tại từ request để lấy thông tin người dùng
    HttpSession session = request.getSession();
    Users user = (Users) session.getAttribute("currentUser");

    // Lấy thông tin từ request để tạo Planner mới
    String plannerName = request.getParameter("plannerName");
    String createTimeStr = request.getParameter("createTime");
    String startTimeStr = request.getParameter("startTime");
    String endTimeStr = request.getParameter("endTime");

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Date createTime;
    Date startTime;
    Date endTime;

    try {
        createTime = new java.sql.Date(dateFormat.parse(createTimeStr).getTime());
        startTime = new java.sql.Date(dateFormat.parse(startTimeStr).getTime());
        endTime = new java.sql.Date(dateFormat.parse(endTimeStr).getTime());
    } catch (ParseException e) {
        // Xử lý ngoại lệ ParseException ở đây (ví dụ: ghi log, thông báo lỗi, hoặc chuyển hướng đến trang lỗi)
        e.printStackTrace();
        request.setAttribute("errorMessage", "Invalid date format");
        request.getRequestDispatcher("createPlanner.jsp").forward(request, response);
        return;
    }

    // Tạo đối tượng Planner mới từ thông tin thu thập được
    Planner newPlanner = new Planner(user.getUserID(), plannerName, createTime, startTime, endTime);

    // Thực hiện thêm Planner vào cơ sở dữ liệu và xử lý kết quả
    PlannerDAO plannerDAO = new PlannerDAO();
    boolean success = plannerDAO.createPlanner(newPlanner);

    if (success) {
        response.sendRedirect("success.jsp");
    } else {
        request.setAttribute("errorMessage", "Failed to create planner");
        request.getRequestDispatcher("createPlanner.jsp").forward(request, response);
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
