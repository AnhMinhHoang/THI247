/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Report;

import DAO.ReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Report;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ViewAllReport", urlPatterns = {"/ViewAllReport"})
public class ViewAllReport extends HttpServlet {

    private ReportDAO reportDAO;

    @Override
    public void init() {
        reportDAO = new ReportDAO(); // Khởi tạo ReportDAO khi Servlet được khởi động
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu GET từ trang hiển thị danh sách báo cáo
        List<Report> reports = reportDAO.getAllReports();

        // Lưu danh sách báo cáo vào request để hiển thị trên trang JSP
        request.setAttribute("reports", reports);

        // Chuyển hướng đến trang hiển thị danh sách báo cáo
        request.getRequestDispatcher("/viewallreport.jsp").forward(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
