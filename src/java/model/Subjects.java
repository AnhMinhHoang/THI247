/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author GoldCandy
 */
public class Subjects {
    private int subjectID;
    private String subjectName;
    private String subjectImg;

    public Subjects() {
    }

    public Subjects(int subjectID, String subjectName, String subjectImg) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.subjectImg = subjectImg;
    }

    public String getSubjectImg() {
        return subjectImg;
    }

    public void setSubjectImg(String subjectImg) {
        this.subjectImg = subjectImg;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "Subjects{" + "subjectID=" + subjectID + ", subjectName=" + subjectName + ", subjectImg=" + subjectImg + '}';
    }
}
