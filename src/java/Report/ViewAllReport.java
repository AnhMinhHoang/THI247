import DAO.ReportDAO; // Import your DAO class here
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Report;

@WebServlet(name = "ViewAllReport", urlPatterns = {"/ViewAllReport"})
public class ViewAllReport extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReportDAO reportDAO = new ReportDAO(); // Initialize your DAO class
        List<Report> reports = reportDAO.getAllReports(); // Get all reports from DAO

        request.setAttribute("reports", reports); // Set reports as an attribute in request
        request.getRequestDispatcher("/viewallreport.jsp").forward(request, response); // Forward to JSP
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // Process GET requests
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // Process POST requests
    }

    @Override
    public String getServletInfo() {
        return "View All Report Servlet";
    }
}
