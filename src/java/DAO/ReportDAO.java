/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBConnection.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Report;
import model.ReportReason;

/**
 *
 * @author ASUS
 */
public class ReportDAO extends DBConnection {

    public void createReport(int userID, int userReportedID, List<Integer> reasonIds, String reportContext, String reportIMG) {
        String insertReportQuery = "INSERT INTO Report (userID, user_reported_id, report_context, report_date, report_img)"
                + " VALUES (?, ?, ?, ?, ?)";

        String insertReasonsQuery = "INSERT INTO ReportReasonMap (report_id, reason_id)"
                + " VALUES (?, ?)";

        try (Connection conn = getConnection(); PreparedStatement psInsertReport = conn.prepareStatement(insertReportQuery, PreparedStatement.RETURN_GENERATED_KEYS); PreparedStatement psInsertReasons = conn.prepareStatement(insertReasonsQuery)) {

            // Thêm thông tin vào bảng Report
            psInsertReport.setInt(1, userID);
            psInsertReport.setInt(2, userReportedID);
            psInsertReport.setString(3, reportContext);
            String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date());
            psInsertReport.setString(4, timeStamp);
            psInsertReport.setString(5, reportIMG);

            psInsertReport.executeUpdate();

            // Lấy report_id được sinh tự động
            int reportId = -1;
            try (java.sql.ResultSet generatedKeys = psInsertReport.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reportId = generatedKeys.getInt(1);
                }
            }

            // Thêm các lý do báo cáo vào bảng ReportReasonMap
            if (reportId != -1) {
                for (int reasonId : reasonIds) {
                    psInsertReasons.setInt(1, reportId);
                    psInsertReasons.setInt(2, reasonId);
                    psInsertReasons.addBatch();
                }
                psInsertReasons.executeBatch();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();

        String query = "SELECT r.report_id, r.userID, r.user_reported_id, r.report_context, r.report_date, r.report_img, "
                + "rr.reason_id, rr.reason_name "
                + "FROM Report r "
                + "LEFT JOIN ReportReasonMap rrm ON r.report_id = rrm.report_id "
                + "LEFT JOIN ReportReason rr ON rrm.reason_id = rr.reason_id";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int reportId = rs.getInt("report_id");
                int userId = rs.getInt("userID");
                int userReportedId = rs.getInt("user_reported_id");
                String reportContext = rs.getString("report_context");
                String reportDate = rs.getString("report_date");
                String reportImg = rs.getString("report_img");
                int reasonId = rs.getInt("reason_id");
                String reasonName = rs.getString("reason_name");

                // Tìm kiếm Report theo reportId trong danh sách
                Report report = findReportById(reports, reportId);
                if (report == null) {
                    // Nếu chưa có report trong danh sách, tạo mới và thêm vào danh sách
                    report = new Report(userId, userReportedId, reportContext, reportDate, reportImg);
                    report.setReportId(reportId);
                    reports.add(report);
                }

                // Thêm lý do báo cáo vào danh sách lý do của report
                if (reasonId != 0 && reasonName != null) {
                    report.addReason(new ReportReason(reasonId, reasonName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

    private Report findReportById(List<Report> reports, int reportId) {
        for (Report report : reports) {
            if (report.getReportId() == reportId) {
                return report;
            }
        }
        return null;
    }
// sửa 

    public void updateReport(int reportId, int userID, int userReportedID, List<Integer> reasonIds, String reportContext, String reportIMG) {
        String updateReportQuery = "UPDATE Report SET userID=?, user_reported_id=?, report_context=?, report_img=? WHERE report_id=?";

        String deleteReasonsQuery = "DELETE FROM ReportReasonMap WHERE report_id=?";
        String insertReasonsQuery = "INSERT INTO ReportReasonMap (report_id, reason_id) VALUES (?, ?)";

        try (Connection conn = getConnection(); PreparedStatement psUpdateReport = conn.prepareStatement(updateReportQuery); PreparedStatement psDeleteReasons = conn.prepareStatement(deleteReasonsQuery); PreparedStatement psInsertReasons = conn.prepareStatement(insertReasonsQuery)) {

            // Cập nhật thông tin báo cáo trong bảng Report
            psUpdateReport.setInt(1, userID);
            psUpdateReport.setInt(2, userReportedID);
            psUpdateReport.setString(3, reportContext);
            psUpdateReport.setString(4, reportIMG);
            psUpdateReport.setInt(5, reportId);

            psUpdateReport.executeUpdate();

            // Xóa các lý do báo cáo cũ từ bảng ReportReasonMap
            psDeleteReasons.setInt(1, reportId);
            psDeleteReasons.executeUpdate();

            // Thêm các lý do báo cáo mới vào bảng ReportReasonMap
            for (int reasonId : reasonIds) {
                psInsertReasons.setInt(1, reportId);
                psInsertReasons.setInt(2, reasonId);
                psInsertReasons.addBatch();
            }
            psInsertReasons.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// xóa

    public void deleteReport(int reportId) {
        String deleteReportQuery = "DELETE FROM Report WHERE report_id=?";
        String deleteReasonsQuery = "DELETE FROM ReportReasonMap WHERE report_id=?";

        try (Connection conn = getConnection(); PreparedStatement psDeleteReport = conn.prepareStatement(deleteReportQuery); PreparedStatement psDeleteReasons = conn.prepareStatement(deleteReasonsQuery)) {

            // Xóa báo cáo từ bảng Report
            psDeleteReport.setInt(1, reportId);
            psDeleteReport.executeUpdate();

            // Xóa các lý do báo cáo từ bảng ReportReasonMap
            psDeleteReasons.setInt(1, reportId);
            psDeleteReasons.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// lấy tất cả report của uersid  

    public static void main(String[] args) {
        ReportDAO reportDAO = new ReportDAO(); // Khởi tạo đối tượng ReportDAO

        // Lấy tất cả các báo cáo từ cơ sở dữ liệu
        List<Report> reports = reportDAO.getAllReports();

        // Dữ liệu mẫu cho việc tạo báo cáo mới
        int userID = 1; // Thay thế bằng userID của người dùng tạo báo cáo
        int userReportedID = 2; // Thay thế bằng userID của người dùng bị báo cáo
        List<Integer> reasonIds = new ArrayList<>(); // Danh sách lý do báo cáo
        reasonIds.add(2); // Ví dụ: Lạm dụng ngôn từ
        reasonIds.add(4); // Ví dụ: Tạo bài đăng sai mục đích
        String reportContext = "Nội dung báo cáo mẫu"; // Thay thế bằng nội dung báo cáo thực tế
        String reportIMG = null; // Thay thế bằng đường dẫn ảnh đính kèm (nếu có)

        // Gọi phương thức tạo báo cáo mới trong ReportDAO
        reportDAO.createReport(userID, userReportedID, reasonIds, reportContext, reportIMG);

        // In ra thông báo thành công
        System.out.println("Báo cáo đã được tạo thành công và lưu vào cơ sở dữ liệu.");
        // Duyệt qua danh sách các báo cáo và in thông tin
        for (Report report : reports) {
            System.out.println("Report ID: " + report.getReportId());
            System.out.println("User ID: " + report.getUserId());
            System.out.println("User Reported ID: " + report.getUserReportedId());
            System.out.println("report Context: " + report.getReportContext());
            System.out.println("report Date: " + report.getReportDate());
            System.out.println("report Img: " + report.getReportImg());

            // In các lý do báo cáo
            System.out.println("Reasons:");
            for (ReportReason reason : report.getReasons()) {
                System.out.println("\tReason ID: " + reason.getReasonId());
                System.out.println("\tReason Name: " + reason.getReasonName());
            }
            System.out.println("----------------------------------");
        }
    }
}
