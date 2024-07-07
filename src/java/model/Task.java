package model;

import java.sql.Timestamp;

public class Task {
    private  int userID;
    private int taskId;
    private int plannerId;
    private String taskName;
    private Timestamp taskDate;

    public Task(int userID, int taskId, int plannerId, String taskName, Timestamp taskDate) {
        this.userID = userID;
        this.taskId = taskId;
        this.plannerId = plannerId;
        this.taskName = taskName;
        this.taskDate = taskDate;
    }
      public Task(int userID, int plannerId, String taskName, Timestamp taskDate) {    
        this.userID = userID;
          this.plannerId = plannerId;
        this.taskName = taskName;
        this.taskDate = taskDate;
    }
       public Task(int taskId, String taskName,int plannerId, Timestamp taskDate) {    
       this.taskId = taskId;
       this.plannerId = plannerId;
        this.taskName = taskName;
        this.taskDate = taskDate;
    }
     
       public Task(int taskId, String taskName, Timestamp taskDate) {    
       this.taskId = taskId;
        this.taskName = taskName;
        this.taskDate = taskDate;
    }
       public Task(String taskName, Timestamp taskDate) {    
      
        this.taskName = taskName;
        this.taskDate = taskDate;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(int plannerId) {
        this.plannerId = plannerId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Timestamp getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Timestamp taskDate) {
        this.taskDate = taskDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
}
