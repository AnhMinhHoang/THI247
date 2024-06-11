package model;

import java.util.List;

public class QuestionBank {
    private int id;
    private int subjectId;
    private int userId;
    private String questionText;
    private String subject; // Thêm trường cho tên môn học
    private List<String> choices;
    private String correctAnswer;
    private String explain;
    
    public QuestionBank(int subjectId, int userId, String questionText, List<String> choices, String correctAnswer, String explain) {
    this.subjectId = subjectId;
    this.userId = userId;
    this.questionText = questionText;
    this.choices = choices;
    this.correctAnswer = correctAnswer;
    this.explain = explain;
}
    
public QuestionBank(int id, int subjectId, int userId, String questionText, List<String> choices, String correctAnswer, String explain) {
    this.id = id;
    this.subjectId = subjectId;
    this.userId = userId;
    this.questionText = questionText;
    this.choices = choices;
    this.correctAnswer = correctAnswer;
    this.explain = explain;
}


    public QuestionBank(int id, int subjectId, int userId, String questionText, String subject, List<String> choices, String correctAnswer, String explain) {
        this.id = id;
        this.subjectId = subjectId;
        this.userId = userId;
        this.questionText = questionText;
        this.subject = subject;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.explain = explain;
    }
     public QuestionBank(int id, int userId, String questionText, String subject, List<String> choices, String correctAnswer, String explain) {
        this.id = id;
        this.userId = userId;
        this.questionText = questionText;
        this.subject = subject;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.explain = explain;
    }
     public QuestionBank(int id, String questionText, String subject, List<String> choices, String correctAnswer, String explain) {
        this.id = id;
        this.questionText = questionText;
        this.subject = subject;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.explain = explain;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    // Getters and Setters
    // Đã bao gồm trong mã tự động của IDE, bạn có thể tạo các phương thức Getter và Setter cho các trường mới.

    // toString() method
    @Override
    public String toString() {
        return "QuestionBank{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", userId=" + userId +
                ", questionText='" + questionText + '\'' +
                ", subject='" + subject + '\'' +
                ", choices=" + choices +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", explain='" + explain + '\'' +
                '}';
    }
}
