package DAO;

import static DAO.DBConnection.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Report;
import model.ReportReason;

public class ReportDAO extends DBConnection {

    // Tạo báo cáo mới
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
            try (ResultSet generatedKeys = psInsertReport.getGeneratedKeys()) {
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

    // Lấy tất cả các báo cáo
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

                Report report = findReportById(reports, reportId);
                if (report == null) {
                    report = new Report(userId, userReportedId, reportContext, reportDate, reportImg);
                    report.setReportId(reportId);
                    reports.add(report);
                }

                if (reasonId != 0 && reasonName != null) {
                    report.addReason(new ReportReason(reasonId, reasonName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

    // Lấy các báo cáo theo userID
    public List<Report> getReportsByUserId(int userId) {
        List<Report> reports = new ArrayList<>();

        String query = "SELECT r.report_id, r.userID, r.user_reported_id, r.report_context, r.report_date, r.report_img, "
                + "rr.reason_id, rr.reason_name "
                + "FROM Report r "
                + "LEFT JOIN ReportReasonMap rrm ON r.report_id = rrm.report_id "
                + "LEFT JOIN ReportReason rr ON rrm.reason_id = rr.reason_id "
                + "WHERE r.userID = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int reportId = rs.getInt("report_id");
                    int userReportedId = rs.getInt("user_reported_id");
                    String reportContext = rs.getString("report_context");
                    String reportDate = rs.getString("report_date");
                    String reportImg = rs.getString("report_img");
                    int reasonId = rs.getInt("reason_id");
                    String reasonName = rs.getString("reason_name");

                    Report report = findReportById(reports, reportId);
                    if (report == null) {
                        report = new Report(userId, userReportedId, reportContext, reportDate, reportImg);
                        report.setReportId(reportId);
                        reports.add(report);
                    }

                    if (reasonId != 0 && reasonName != null) {
                        report.addReason(new ReportReason(reasonId, reasonName));
                    }
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

    // Cập nhật báo cáo
    public void updateReport(int reportId, int userID, int userReportedID, List<Integer> reasonIds, String reportContext, String reportIMG) {
        String updateReportQuery = "UPDATE Report SET userID=?, user_reported_id=?, report_context=?, report_img=? WHERE report_id=?";

        String deleteReasonsQuery = "DELETE FROM ReportReasonMap WHERE report_id=?";
        String insertReasonsQuery = "INSERT INTO ReportReasonMap (report_id, reason_id) VALUES (?, ?)";

        try (Connection conn = getConnection(); PreparedStatement psUpdateReport = conn.prepareStatement(updateReportQuery); PreparedStatement psDeleteReasons = conn.prepareStatement(deleteReasonsQuery); PreparedStatement psInsertReasons = conn.prepareStatement(insertReasonsQuery)) {

            psUpdateReport.setInt(1, userID);
            psUpdateReport.setInt(2, userReportedID);
            psUpdateReport.setString(3, reportContext);
            psUpdateReport.setString(4, reportIMG);
            psUpdateReport.setInt(5, reportId);

            psUpdateReport.executeUpdate();

            psDeleteReasons.setInt(1, reportId);
            psDeleteReasons.executeUpdate();

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

    // Xóa báo cáo
    public void deleteReport(int reportId) {
        String deleteReportQuery = "DELETE FROM Report WHERE report_id=?";
        String deleteReasonsQuery = "DELETE FROM ReportReasonMap WHERE report_id=?";

        try (Connection conn = getConnection(); PreparedStatement psDeleteReport = conn.prepareStatement(deleteReportQuery); PreparedStatement psDeleteReasons = conn.prepareStatement(deleteReasonsQuery)) {

            psDeleteReport.setInt(1, reportId);
            psDeleteReport.executeUpdate();

            psDeleteReasons.setInt(1, reportId);
            psDeleteReasons.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method để kiểm tra
}
