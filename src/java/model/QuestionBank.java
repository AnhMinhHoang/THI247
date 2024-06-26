/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sonhu
 */
public class QuestionBank {
    private int userID;
    private int questionId;
    private int subjectId;
    private String questionContext;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choiceCorrect;
    private String explain;
    private String QuestionImg;
    private String explainImg;

    public QuestionBank() {
    }

    public QuestionBank(int userID, int questionId, int subjectId, String questionContext, String choice1, String choice2, String choice3, String choiceCorrect, String explain, String QuestionImg, String explainImg) {
        this.userID = userID;
        this.questionId = questionId;
        this.subjectId = subjectId;
        this.questionContext = questionContext;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choiceCorrect = choiceCorrect;
        this.explain = explain;
        this.QuestionImg = QuestionImg;
        this.explainImg = explainImg;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getQuestionContext() {
        return questionContext;
    }

    public void setQuestionContext(String questionContext) {
        this.questionContext = questionContext;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoiceCorrect() {
        return choiceCorrect;
    }

    public void setChoiceCorrect(String choiceCorrect) {
        this.choiceCorrect = choiceCorrect;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getQuestionImg() {
        return QuestionImg;
    }

    public void setQuestionImg(String QuestionImg) {
        this.QuestionImg = QuestionImg;
    }

    public String getExplainImg() {
        return explainImg;
    }

    public void setExplainImg(String explainImg) {
        this.explainImg = explainImg;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "QuestionBank{" + "userID=" + userID + ", questionId=" + questionId + ", subjectId=" + subjectId + ", questionContext=" + questionContext + ", choice1=" + choice1 + ", choice2=" + choice2 + ", choice3=" + choice3 + ", choiceCorrect=" + choiceCorrect + ", explain=" + explain + ", QuestionImg=" + QuestionImg + ", explainImg=" + explainImg + '}';
    }
}
