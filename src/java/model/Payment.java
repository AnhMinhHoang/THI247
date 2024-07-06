/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author GoldCandy
 */
public class Payment {
    private int paymentID;
    private int userID;
    private String paymentCode;
    private String bank;
    private int amount;
    private String paymentDate;

    public Payment() {
    }

    public Payment(int paymentID, int userID, String paymentCode, String bank, int amount, String paymentDate) {
        this.paymentID = paymentID;
        this.userID = userID;
        this.paymentCode = paymentCode;
        this.bank = bank;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentID=" + paymentID + ", userID=" + userID + ", paymentCode=" + paymentCode + ", bank=" + bank + ", amount=" + amount + ", paymentDate=" + paymentDate + '}';
    }
}
