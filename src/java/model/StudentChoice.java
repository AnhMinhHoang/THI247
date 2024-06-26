/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author GoldCandy
 */
public class StudentChoice {
    private int answerID;
    private int testID;
    private int questionID;
    private String selectedChoice;

    public StudentChoice() {
    }

    public StudentChoice(int answerID, int testID, int questionID, String selectedChoice) {
        this.answerID = answerID;
        this.testID = testID;
        this.questionID = questionID;
        this.selectedChoice = selectedChoice;
    }

    public String getSelectedChoice() {
        return selectedChoice;
    }

    public void setSelectedChoice(String selectedChoice) {
        this.selectedChoice = selectedChoice;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    @Override
    public String toString() {
        return "StudentChoice{" + "answerID=" + answerID + ", testID=" + testID + ", questionID=" + questionID + ", selectedChoice=" + selectedChoice + '}';
    }
}
