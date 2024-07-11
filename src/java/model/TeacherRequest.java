/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author GoldCandy
 */
public class TeacherRequest {
    private int requestID;
    private int userID;
    private int subjectID;
    private int experience;
    private String academicLevel;
    private String school;

    public TeacherRequest() {
    }

    public TeacherRequest(int requestID, int userID, int subjectID, int experience, String academicLevel, String school) {
        this.requestID = requestID;
        this.userID = userID;
        this.subjectID = subjectID;
        this.experience = experience;
        this.academicLevel = academicLevel;
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    @Override
    public String toString() {
        return "TeacherRequest{" + "requestID=" + requestID + ", userID=" + userID + ", subjectID=" + subjectID + ", experience=" + experience + ", academicLevel=" + academicLevel + ", school=" + school + '}';
    }
}
