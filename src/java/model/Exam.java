/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sonhu
 */
public class Exam {
    private int examId;
    private String examName;
    private String createDate;
    private int userId;
    private int subjectId;

    public Exam(int examId, String examName, String createDate, int userId, int subjectId) {
        this.examId = examId;
        this.examName = examName;
        this.createDate = createDate;
        this.userId = userId;
        this.subjectId = subjectId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Exam{" + "examId=" + examId + ", examName=" + examName + ", createDate=" + createDate + ", userId=" + userId + ", subjectId=" + subjectId + '}';
    }
    
}
