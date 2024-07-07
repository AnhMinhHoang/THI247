package model;

import java.sql.Date; // Import thư viện Date thay vì Timestamp

public class Planner {
    private int plannerId;
    private int userId;
    private String plannerName;
    private Date createTime;
    private Date startTime;
    private Date endTime; // Sử dụng Date thay vì Timestamp

    // Constructors
    public Planner() {
    }

    public Planner(int plannerId, String plannerName,Date startTime, Date endTime) {
        this.plannerId = plannerId;
        this.plannerName = plannerName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
       
    public Planner(int userId, String plannerName, Date createTime,Date startTime, Date endTime) {
        this.userId = userId;
        this.plannerName = plannerName;
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Planner(int plannerId, int userId, String plannerName, Date createTime,Date startTime, Date endTime) {
        this.plannerId = plannerId;
        this.userId = userId;
        this.plannerName = plannerName;
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }
  
    // Getters and setters
    public int getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(int plannerId) {
        this.plannerId = plannerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlannerName() {
        return plannerName;
    }

    public void setPlannerName(String plannerName) {
        this.plannerName = plannerName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Planner{" + "plannerId=" + plannerId + ", userId=" + userId + ", plannerName=" + plannerName + ", createTime=" + createTime + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }

    
}
