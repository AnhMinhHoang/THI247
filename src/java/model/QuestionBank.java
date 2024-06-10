package model;

import java.util.List;

public class QuestionBank {
    private int id;
    private String subject;
    private int userId;
    private String questionText;
    private List<String> choices;
    private String correctAnswer;
    private String explain;

    public QuestionBank(int id, String subject, int userId, String questionText, List<String> choices, String correctAnswer, String explain) {
        this.id = id;
        this.subject = subject;
        this.userId = userId;
        this.questionText = questionText;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.explain = explain;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    // toString() method
    @Override
    public String toString() {
        return "QuestionBank{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", userId=" + userId +
                ", questionText='" + questionText + '\'' +
                ", choices=" + choices +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", explain='" + explain + '\'' +
                '}';
    }
}
