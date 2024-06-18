/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author GoldCandy
 */
public class Result {
    private int resultID;
    private int userID;
    private int examID;
    private int score;

    public Result() {
    }

    public Result(int resultID, int userID, int examID, int score) {
        this.resultID = resultID;
        this.userID = userID;
        this.examID = examID;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getResultID() {
        return resultID;
    }

    public void setResultID(int resultID) {
        this.resultID = resultID;
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

    @Override
    public String toString() {
        return "Result{" + "resultID=" + resultID + ", userID=" + userID + ", examID=" + examID + ", score=" + score + '}';
    }
}
