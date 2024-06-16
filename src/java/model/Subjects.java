/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sonhu
 */
public class Subjects {
    private  int subjectId;
    private  String subjectName;
    private String subjectImg;

    public Subjects(int subjectId, String subjectName, String subjectImg) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectImg = subjectImg;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectImg() {
        return subjectImg;
    }

    public void setSubjectImg(String subjectImg) {
        this.subjectImg = subjectImg;
    }
    
}
