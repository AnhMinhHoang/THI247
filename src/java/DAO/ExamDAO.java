package DAO;

import model.QuestionBank;
import model.Exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExamDAO extends DBConnection {

    // Phương thức để tạo đề thi
    public boolean createExam(Exam exam, int userId) throws SQLException {
    String sql = "INSERT INTO Exams (exam_name, user_id) VALUES (?, ?)";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, exam.getExamName());
        stmt.setInt(2, userId); // Thêm userId vào câu lệnh SQL
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
// Phương thức để thêm câu hỏi vào đề thi của một người dùng
public boolean addQuestionToExam(int examId, int questionId, int userId) throws SQLException {
    // Kiểm tra xem câu hỏi thuộc về người dùng hiện tại hay không
    String checkOwnershipQuery = "SELECT 1 FROM QuestionBank WHERE question_id = ? AND user_id = ?";
    try (Connection conn = getConnection(); PreparedStatement checkStmt = conn.prepareStatement(checkOwnershipQuery)) {
        checkStmt.setInt(1, questionId);
        checkStmt.setInt(2, userId);
        try (ResultSet resultSet = checkStmt.executeQuery()) {
            if (!resultSet.next()) {
                // Nếu câu hỏi không thuộc về người dùng hiện tại, trả về false
                return false;
            }
        }
    } 
    // Nếu câu hỏi thuộc về người dùng hiện tại, thêm vào đề thi và trả về true
    // Thêm câu hỏi vào bảng Exam_Questions
    String addQuestionQuery = "INSERT INTO Exam_Questions (exam_id, question_id) VALUES (?, ?)";
    try (Connection conn = getConnection(); PreparedStatement addStmt = conn.prepareStatement(addQuestionQuery)) {
        addStmt.setInt(1, examId);
        addStmt.setInt(2, questionId);
        addStmt.executeUpdate();
    }
    return true;
}
public boolean removeQuestionFromExam(int examId, int questionId) throws SQLException {
        String query = "DELETE FROM Exam_Questions WHERE exam_id = ? AND question_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, examId);
            preparedStatement.setInt(2, questionId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }
public boolean updateQuestionInExam(int examId, int questionId, String updatedQuestionText, List<String> updatedChoices, String updatedCorrectAnswer, String updatedExplain) throws SQLException {
    // Kiểm tra xem câu hỏi thuộc về đề thi có mã examId không
    String checkQuestionInExamQuery = "SELECT 1 FROM Exam_Questions WHERE exam_id = ? AND question_id = ?";
    try (Connection conn = getConnection(); PreparedStatement checkStmt = conn.prepareStatement(checkQuestionInExamQuery)) {
        checkStmt.setInt(1, examId);
        checkStmt.setInt(2, questionId);
        try (ResultSet resultSet = checkStmt.executeQuery()) {
            if (!resultSet.next()) {
                // Nếu câu hỏi không thuộc về đề thi, không cập nhật và trả về false
                return false;
            }
        }
    } 
    
    // Cập nhật thông tin của câu hỏi trong bảng QuestionBank
    String updateQuestionQuery = "UPDATE QuestionBank SET question_context = ?, choice_1 = ?, choice_2 = ?, choice_3 = ?, choice_correct = ?, choice_correct_explain = ? WHERE question_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(updateQuestionQuery)) {
        stmt.setString(1, updatedQuestionText);
        stmt.setString(2, updatedChoices.get(0));
        stmt.setString(3, updatedChoices.get(1));
        stmt.setString(4, updatedChoices.get(2));
        stmt.setString(5, updatedCorrectAnswer);
        stmt.setString(6, updatedExplain);
        stmt.setInt(7, questionId);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }
}
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

    // Phương thức để lấy tất cả đề thi theo userid
    public List<Exam> getAllExams(int userId) throws SQLException {
    List<Exam> exams = new ArrayList<>();
    String query = "SELECT * FROM Exams WHERE user_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, userId);
        try (ResultSet resultSet = stmt.executeQuery()) {
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

// Phương thức để xóa đề thi của một người dùng dựa trên examId và userId
public boolean deleteExam(int examId, int userId) throws SQLException {
    String sql = "DELETE FROM Exams WHERE exam_id = ? AND user_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, examId);
        stmt.setInt(2, userId);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }
}

// Phương thức để cập nhật đề thi của một người dùng dựa trên examId và userId
public boolean updateExam(Exam exam, int userId) throws SQLException {
    String sql = "UPDATE Exams SET exam_name = ? WHERE exam_id = ? AND user_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, exam.getExamName());
        stmt.setInt(2, exam.getExamId());
        stmt.setInt(3, userId);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }
}

// Phương thức để xóa đề thi
public boolean deleteExam(int id) throws SQLException {
    try {
        ExamDAO examDAO = new ExamDAO(); // Khai báo và khởi tạo đối tượng ExamDAO
        // Lấy danh sách câu hỏi của đề thi từ bộ nhớ
        List<QuestionBank> examQuestions = examDAO.getQuestionsByExamId(id);
        
        // Xóa các câu hỏi của đề thi khỏi danh sách câu hỏi trong bộ nhớ
        for (QuestionBank question : examQuestions) {
            examDAO.deleteQuestionFromExam(id, question.getId()); // Hãy thêm phương thức này vào ExamDAO để xóa câu hỏi từ bộ nhớ
        }
        
        // Tiến hành xóa đề thi từ cơ sở dữ liệu
        String sql = "DELETE FROM Exams WHERE exam_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
public boolean deleteQuestionFromExam(int examId, int questionId) throws SQLException {
    String sql = "DELETE FROM Exam_Questions WHERE exam_id = ? AND question_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, examId);
        stmt.setInt(2, questionId);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }
}


    // Phương thức để lấy các câu hỏi theo ID đề thi
    public List<QuestionBank> getQuestionsByExamId(int examId) throws SQLException {
    List<QuestionBank> questions = new ArrayList<>();
    
    String query = "SELECT q.*, s.subject_name FROM QuestionBank q INNER JOIN Exam_Questions eq ON q.question_id = eq.question_id INNER JOIN Subjects s ON q.subject_id = s.subject_id WHERE eq.exam_id = ?";

    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, examId);
        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("question_id");
                int subjectId = resultSet.getInt("subject_id");
                String subjectName = resultSet.getString("subject_name"); 
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
                QuestionBank question = new QuestionBank(id, subjectId, userId, questionText, subjectName, choices, correctAnswer, explain); 
                questions.add(question);
            }
        }
    }
    return questions;
}



// Phương thức để lấy các câu hỏi cho đề thi
public List<QuestionBank> getQuestionsForExam(int examId) throws SQLException {
    List<QuestionBank> questions = new ArrayList<>();
    String query = "SELECT q.*, s.subject_name FROM QuestionBank q INNER JOIN Exam_Questions eq ON q.question_id = eq.question_id INNER JOIN Subjects s ON q.subject_id = s.subject_id WHERE eq.exam_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, examId);
        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("question_id");
                String subjectName = resultSet.getString("subject_name"); // Lấy subjectName từ ResultSet
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
                // Sử dụng khởi tạo mới của QuestionBank với subjectName thay vì subjectId
                QuestionBank question = new QuestionBank(id, 0, userId, questionText, subjectName, choices, correctAnswer, explain);
                questions.add(question);
            }
        }
    }
    return questions;
}
public List<QuestionBank> getQuestionsForExam(int examId, int userId) throws SQLException {
    List<QuestionBank> questions = new ArrayList<>();
    String query = "SELECT q.*, s.subject_name FROM QuestionBank q " +
                   "INNER JOIN Exam_Questions eq ON q.question_id = eq.question_id " +
                   "INNER JOIN Subjects s ON q.subject_id = s.subject_id " +
                   "WHERE eq.exam_id = ? AND q.user_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, examId);
        stmt.setInt(2, userId);
        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("question_id");
                int subjectId = resultSet.getInt("subject_id");
                String subjectName = resultSet.getString("subject_name");
                String questionText = resultSet.getString("question_context");
                List<String> choices = Arrays.asList(
                        resultSet.getString("choice_1"),
                        resultSet.getString("choice_2"),
                        resultSet.getString("choice_3"),
                        resultSet.getString("choice_correct")
                );
                String correctAnswer = resultSet.getString("choice_correct");
                String explain = resultSet.getString("choice_correct_explain");
                QuestionBank question = new QuestionBank(id, subjectId, userId, questionText, subjectName, choices, correctAnswer, explain);
                questions.add(question);
            }
        }
    }
    return questions;
}


    // Phương thức để lấy các đề thi theo ID của người dùng
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
