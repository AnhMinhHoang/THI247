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
    private float score;
    private String submitDate;
    private int rightAnswer;
    private int totalQuestion;
    private int testID;

    public Result() {
    }

    public Result(int resultID, int userID, int examID, float score, String submitDate, int rightAnswer, int totalQuestion, int testID) {
        this.resultID = resultID;
        this.userID = userID;
        this.examID = examID;
        this.score = score;
        this.submitDate = submitDate;
        this.rightAnswer = rightAnswer;
        this.totalQuestion = totalQuestion;
        this.testID = testID;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    @Override
    public String toString() {
        return "Result{" + "resultID=" + resultID + ", userID=" + userID + ", examID=" + examID + ", score=" + score + ", submitDate=" + submitDate + ", rightAnswer=" + rightAnswer + ", totalQuestion=" + totalQuestion + ", testID=" + testID + '}';
    }
}
