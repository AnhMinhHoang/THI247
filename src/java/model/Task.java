/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Timestamp;
/**
 *
 * @author sonhu
 */
public class Task {
    private int userID;
    private int taskId;
    private String taskContext;
    private Timestamp taskDeadline;

    public Task() {
    }

    public Task(int userID, int taskId, String taskContext, Timestamp taskDeadline) {
        this.userID = userID;
        this.taskId = taskId;
        this.taskContext = taskContext;
        this.taskDeadline = taskDeadline;
    }
       public Task(int userID,  String taskContext, Timestamp taskDeadline) {
        this.userID = userID;
        this.taskContext = taskContext;
        this.taskDeadline = taskDeadline;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskContext() {
        return taskContext;
    }

    public void setTaskContext(String taskContext) {
        this.taskContext = taskContext;
    }

    public Timestamp getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Timestamp taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    @Override
    public String toString() {
        return "Task{" + "userID=" + userID + ", taskId=" + taskId + ", taskContext=" + taskContext + ", taskDeadline=" + taskDeadline + '}';
    }
    
}
