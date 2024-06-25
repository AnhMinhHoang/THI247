package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private int reportId;
    private int userId;
    private int userReportedId;
    private String reportContext;
    private String reportDate;
    private String reportImg;
    private List<ReportReason> reasons;

    public Report(int userId, int userReportedId, String reportContext, String reportDate, String postImg) {
        this.userId = userId;
        this.userReportedId = userReportedId;
        this.reportContext = reportContext;
        this.reportDate = reportDate;
        this.reportImg = reportImg;
        this.reasons = new ArrayList<>();
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserReportedId() {
        return userReportedId;
    }

    public void setUserReportedId(int userReportedId) {
        this.userReportedId = userReportedId;
    }

    public String getReportContext() {
        return reportContext;
    }

    public void setReportContext(String reportContext) {
        this.reportContext = reportContext;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportImg() {
        return reportImg;
    }

    public void setReportImg(String reportImg) {
        this.reportImg = reportImg;
    }

    
    public List<ReportReason> getReasons() {
        return reasons;
    }

    // Method to add a reason to the list
    public void addReason(ReportReason reason) {
        this.reasons.add(reason);
    }
}
