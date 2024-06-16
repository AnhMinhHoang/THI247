/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package model;

/**
 *
 * @author GoldCandy
 */
public class Exam {
    private int examID;
    private String examName;
    private String createDate;
    private int userID;
    private int subjectID;

    public Exam() {
    }

    public Exam(int examID, String examName, String createDate, int userID, int subjectID) {
        this.examID = examID;
        this.examName = examName;
        this.createDate = createDate;
        this.userID = userID;
        this.subjectID = subjectID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Exam{" + "examID=" + examID + ", examName=" + examName + ", createDate=" + createDate + ", userID=" + userID + ", subjectID=" + subjectID + '}';
    }
}
