/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author GoldCandy
 */
public class Tests {
    private int testID;
    private int userID;
    private int examID;
    private int timeLeft;
    private long seed;

    public Tests() {
    }

    public Tests(int testID, int userID, int examID, int timeLeft, long seed) {
        this.testID = testID;
        this.userID = userID;
        this.examID = examID;
        this.timeLeft = timeLeft;
        this.seed = seed;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    @Override
    public String toString() {
        return "Tests{" + "testID=" + testID + ", userID=" + userID + ", examID=" + examID + ", timeLeft=" + timeLeft + ", seed=" + seed + '}';
    }
}
