package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Exam {
    private int examId;
    private String examName;
    private Timestamp createdAt;
    private List<QuestionBank> questions;

    public Exam(int examId, String examName, int userId) {
        this.examId = examId;
        this.examName = examName;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.questions = new ArrayList<>();
    }

    public Exam(String examName) {
        this.examName = examName;
        this.questions = new ArrayList<>(); // Khởi tạo questions ở đây để đảm bảo không null
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
        return "Exam{" + "examId=" + examId + ", examName=" + examName + ", createdAt=" + createdAt + ", questions=" + questions + '}';
    }
    
}
