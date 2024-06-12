package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Exam {
    private int examId;
    private String examName;
    private Timestamp createdAt;
    private int userId; // Thêm thuộc tính userId để định danh người tạo đề thi
    private List<QuestionBank> questions;

    public Exam(int examId, String examName, int userId) {
        this.examId = examId;
        this.examName = examName;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.userId = userId;
        this.questions = new ArrayList<>();
    }
    public Exam(String examName) {
        this.examName = examName;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.questions = new ArrayList<>();
    }
public Exam(String examName, int userId, Timestamp createdAt) {
        this.examName = examName;
        this.userId = userId;
        this.createdAt = createdAt;
        this.questions = new ArrayList<>();
    }
    public Exam(String examName, int userId) {
        this.examName = examName;
        this.userId = userId;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.questions = new ArrayList<>();
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<QuestionBank> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionBank> questions) {
        this.questions = questions;
    }

    public void addQuestion(QuestionBank question) {
        if (question != null) {
            this.questions.add(question);
        }
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", examName='" + examName + '\'' +
                ", createdAt=" + createdAt +
                ", userId=" + userId +
                ", questions=" + questions +
                '}';
    }
}
