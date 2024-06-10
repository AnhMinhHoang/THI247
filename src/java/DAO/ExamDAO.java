package DAO;

import model.QuestionBank;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Exam;

public class ExamDAO extends DBConnection {


    // Phương thức để tạo đề thi
    public boolean createExam(Exam exam) throws SQLException {
        String sql = "INSERT INTO Exams (exam_name) VALUES (?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, exam.getExamName());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                return false;
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int examId = generatedKeys.getInt(1);
                    exam.setExamId(examId);
                    // Lưu danh sách câu hỏi vào bảng Exam_Questions
                    saveExamQuestions(exam);
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    // Phương thức để lưu câu hỏi vào đề thi
    private void saveExamQuestions(Exam exam) throws SQLException {
        String sql = "INSERT INTO Exam_Questions (exam_id, question_id) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (QuestionBank question : exam.getQuestions()) {
                stmt.setInt(1, exam.getExamId());
                stmt.setInt(2, question.getId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    // Phương thức để lấy tất cả đề thi
    public List<Exam> getAllExams() throws SQLException {
        List<Exam> exams = new ArrayList<>();
        String query = "SELECT * FROM Exams";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("exam_id");
                String name = resultSet.getString("exam_name");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Exam exam = new Exam(name);
                exam.setExamId(id);
                exam.setCreatedAt(createdAt);
                exams.add(exam);
            }
        }
        return exams;
    }

    // Phương thức để lấy đề thi theo ID
    public Exam getExamById(int id) throws SQLException {
        String query = "SELECT * FROM Exams WHERE exam_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("exam_name");
                    Timestamp createdAt = resultSet.getTimestamp("created_at");
                    Exam exam = new Exam(name);
                    exam.setExamId(id);
                    exam.setCreatedAt(createdAt);
                    return exam;
                }
            }
        }
        return null;
    }

    // Phương thức để xóa đề thi
    public boolean deleteExam(int id) throws SQLException {
        String sql = "DELETE FROM Exams WHERE exam_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Phương thức để lấy các câu hỏi theo ID đề thi
    public List<QuestionBank> getQuestionsByExamId(int examId) throws SQLException {
        List<QuestionBank> questions = new ArrayList<>();
        String query = "SELECT q.* FROM QuestionBank q INNER JOIN Exam_Questions eq ON q.question_id = eq.question_id WHERE eq.exam_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, examId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("question_id");
                    String subject = resultSet.getString("subject");
                    int userId = resultSet.getInt("user_id");
                    String questionText = resultSet.getString("question_context");
                    List<String> choices = Arrays.asList(
                            resultSet.getString("choice_1"),
                            resultSet.getString("choice_2"),
                            resultSet.getString("choice_3"),
                            resultSet.getString("choice_correct")
                    );
                    String correctAnswer = resultSet.getString("choice_correct");
                    String explain = resultSet.getString("choice_correct_explain");
                    QuestionBank question = new QuestionBank(id, subject, userId, questionText, choices, correctAnswer, explain);
                    questions.add(question);
                }
            }
        }
        return questions;
    }
   public List<QuestionBank> getQuestionsForExam(int examId) throws SQLException {
    List<QuestionBank> questions = new ArrayList<>();
    String query = "SELECT q.* FROM QuestionBank q INNER JOIN Exam_Questions eq ON q.question_id = eq.question_id WHERE eq.exam_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, examId);
        System.out.println("Fetching questions for examId: " + examId);
        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("question_id");
                String subject = resultSet.getString("subject");
                int userId = resultSet.getInt("user_id");
                String questionText = resultSet.getString("question_context");
                List<String> choices = Arrays.asList(
                        resultSet.getString("choice_1"),
                        resultSet.getString("choice_2"),
                        resultSet.getString("choice_3"),
                        resultSet.getString("choice_correct")
                );
                String correctAnswer = resultSet.getString("choice_correct");
                String explain = resultSet.getString("choice_correct_explain");
                QuestionBank question = new QuestionBank(id, subject, userId, questionText, choices, correctAnswer, explain);
                questions.add(question);
            }
        }
    }
    return questions;
}




    public List<Exam> getExamsByUserId(int userId) throws SQLException {
    List<Exam> exams = new ArrayList<>();
    String query = "SELECT * FROM Exams WHERE user_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, userId);
        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int examId = resultSet.getInt("exam_id");
                String examName = resultSet.getString("exam_name");
                Exam exam = new Exam(examId, examName, userId);
                exams.add(exam);
            }
        }
    }
    return exams;
}


}
