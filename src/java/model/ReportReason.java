/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class ReportReason {
    private int reasonId;
    private String reasonName;

    public ReportReason() {
    }

     public ReportReason(int reasonId, String reasonName) {
        this.reasonId = reasonId;
        this.reasonName = reasonName;
    }

    // Getters and Setters
    public int getReasonId() {
        return reasonId;
    }

    public void setReasonId(int reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    @Override
    public String toString() {
        return "ReportReason{" + "reasonId=" + reasonId + ", reasonName=" + reasonName + '}';
    }
    
}
