package DAO;

import model.QuestionBank;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionDAO extends DBConnection {

public boolean createMultipleChoiceQuestion(QuestionBank question) throws SQLException {
    String sql = "INSERT INTO QuestionBank (subject_id, user_id, question_context, choice_1, choice_2, choice_3, choice_correct, choice_correct_explain) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        int subjectId = question.getSubjectId(); // Sử dụng trực tiếp subjectId từ question
        int userId = question.getUserId(); // Lấy userId từ question

        if (getSubjectNameById(subjectId) == null) {
            // Xử lý nếu không tìm thấy subjectName trong cơ sở dữ liệu
            return false;
        }
        stmt.setInt(1, subjectId);
        
        // Sử dụng userId từ question
        stmt.setInt(2, userId);
        
        stmt.setString(3, question.getQuestionText());
        List<String> choices = question.getChoices();
        stmt.setString(4, choices.get(0));
        stmt.setString(5, choices.get(1));
        stmt.setString(6, choices.get(2));
        stmt.setString(7, question.getCorrectAnswer());
        stmt.setString(8, question.getExplain());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }
}



    public List<QuestionBank> getAllMultipleChoiceQuestions(int userId) {
    List<QuestionBank> questions = new ArrayList<>();
    String query = "SELECT * FROM QuestionBank WHERE user_id = ?";

    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, userId);
        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("question_id");
                int subjectId = resultSet.getInt("subject_id");
                String questionText = resultSet.getString("question_context");
                List<String> choices = Arrays.asList(
                        resultSet.getString("choice_1"),
                        resultSet.getString("choice_2"),
                        resultSet.getString("choice_3"),
                        resultSet.getString("choice_correct")
                );
                String correctAnswer = resultSet.getString("choice_correct");
                String explain = resultSet.getString("choice_correct_explain");

                // Lấy tên của môn học từ subjectId
                String subjectName = getSubjectNameById(subjectId);

                QuestionBank question = new QuestionBank(id, subjectId, userId, questionText, subjectName, choices, correctAnswer, explain);
                questions.add(question);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return questions;
}
   public List<QuestionBank> getAllMultipleChoiceQuestions() {
    List<QuestionBank> questions = new ArrayList<>();
    String query = "SELECT * FROM QuestionBank";

    try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(query)) {
        while (resultSet.next()) {
            int id = resultSet.getInt("question_id");
            int subjectId = resultSet.getInt("subject_id");
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

            // Lấy tên của môn học từ subjectId
            String subjectName = getSubjectNameById(subjectId);

            QuestionBank question = new QuestionBank(id, subjectId, userId, questionText, subjectName, choices, correctAnswer, explain);
            questions.add(question);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return questions;
}


    public boolean updateMultipleChoiceQuestion(QuestionBank question) throws SQLException {
        String sql = "UPDATE QuestionBank SET user_id = ?, question_context = ?, choice_1 = ?, choice_2 = ?, choice_3 = ?, choice_correct = ?, choice_correct_explain = ? WHERE question_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, question.getUserId());
            stmt.setString(2, question.getQuestionText());
            List<String> choices = question.getChoices();
            stmt.setString(3, choices.get(0));
            stmt.setString(4, choices.get(1));
            stmt.setString(5, choices.get(2));
            stmt.setString(6, question.getCorrectAnswer());
            stmt.setString(7, question.getExplain());
            stmt.setInt(8, question.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean deleteMultipleChoiceQuestion(int id) throws SQLException {
        String sql = "DELETE FROM QuestionBank WHERE question_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

  public List<QuestionBank> getQuestionsByUserId(int userId) {
    List<QuestionBank> questions = new ArrayList<>();
    String query = "SELECT * FROM QuestionBank WHERE user_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, userId);
        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("question_id");
                int subjectId = resultSet.getInt("subject_id");
                String questionText = resultSet.getString("question_context");
                List<String> choices = Arrays.asList(
                        resultSet.getString("choice_1"),
                        resultSet.getString("choice_2"),
                        resultSet.getString("choice_3"),
                        resultSet.getString("choice_correct")
                );
                String correctAnswer = resultSet.getString("choice_correct");
                String explain = resultSet.getString("choice_correct_explain");
                QuestionBank question = new QuestionBank(id, subjectId, userId, questionText, choices, correctAnswer, explain);
                questions.add(question);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return questions;
}


    public int getQuestionCountBySubjectId(int subjectId) {
        String query = "SELECT COUNT(*) FROM QuestionBank WHERE subject_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, subjectId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

  public List<QuestionBank> getQuestionsForExam(int examId, int userId) throws SQLException {
    List<QuestionBank> questions = new ArrayList<>();
    String query = "SELECT q.question_id, q.subject_id, q.question_context, q.choice_1, q.choice_2, q.choice_3, q.choice_correct, q.choice_correct_explain, s.subject_name " +
                   "FROM QuestionBank q " +
                   "INNER JOIN Exam_Questions eq ON q.question_id = eq.question_id " +
                   "INNER JOIN Exams e ON eq.exam_id = e.exam_id " +
                   "INNER JOIN Subjects s ON q.subject = s.subject_name " +
                   "WHERE e.exam_id = ? AND e.user_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, examId);
        stmt.setInt(2, userId);
        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("question_id");
                int subjectId = resultSet.getInt("subject_id");
                String questionText = resultSet.getString("question_context");
                List<String> choices = Arrays.asList(
                        resultSet.getString("choice_1"),
                        resultSet.getString("choice_2"),
                        resultSet.getString("choice_3"),
                        resultSet.getString("choice_correct")
                );
                String correctAnswer = resultSet.getString("choice_correct");
                String explain = resultSet.getString("choice_correct_explain");
                String subjectName = resultSet.getString("subject_name");
                QuestionBank question = new QuestionBank(id, subjectId, userId, questionText, subjectName, choices, correctAnswer, explain);
                questions.add(question);
            }
        }
    }
    return questions;
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




public List<String> getAllSubjects() throws SQLException {
    List<String> subjects = new ArrayList<>();
    String query = "SELECT DISTINCT subject_name FROM Subjects";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(query)) {
        while (resultSet.next()) {
            String subject = resultSet.getString("subject_name");
            subjects.add(subject);
        }
    }
    return subjects;
}


public String getSubjectNameById(int subjectId) throws SQLException {
    String subjectName = "";
    String query = "SELECT subject_name FROM Subjects WHERE subject_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, subjectId);
        try (ResultSet resultSet = stmt.executeQuery()) {
            if (resultSet.next()) {
                subjectName = resultSet.getString("subject_name");
            }
        }
    }
    return subjectName;
}

public int getSubjectIdByName(String subject) {
    String query = "SELECT subject_id FROM Subjects WHERE subject_name = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, subject);
        try (ResultSet resultSet = stmt.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("subject_id");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }catch(Exception err){
        System.out.println(err);
    }
    return 0;
}

public List<QuestionBank> getQuestionsBySubject(String subject) throws SQLException {
    List<QuestionBank> questions = new ArrayList<>();
    String query = "SELECT * FROM QuestionBank WHERE subject = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, subject);
        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("question_id");
                int subjectId = resultSet.getInt("subject_id");
                int userId = resultSet.getInt("user_id"); // Chuyển userId từ String sang int
                String questionText = resultSet.getString("question_context");
                List<String> choices = Arrays.asList(
                        resultSet.getString("choice_1"),
                        resultSet.getString("choice_2"),
                        resultSet.getString("choice_3")
                );
                String correctAnswer = resultSet.getString("choice_correct");
                String explanation = resultSet.getString("choice_correct_explain");

                // Lấy tên của môn học từ subjectId
                String subjectName = getSubjectNameById(subjectId);

                // Sử dụng constructor mới với trường subjectName và userId là số nguyên
                QuestionBank question = new QuestionBank(id, subjectId, userId, questionText, subjectName, choices, correctAnswer, explanation);
                questions.add(question);
            }
        }
    }
    return questions;
}
// lấy các câu hỏi trong id môn học
public List<QuestionBank> getQuestionsBySubjectId(int subjectId) throws SQLException {
        List<QuestionBank> questions = new ArrayList<>();
        String query = "SELECT * FROM QuestionBank WHERE subject_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, subjectId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("question_id");
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

                    // Lấy tên của môn học từ subjectId
                    String subjectName = getSubjectNameById(subjectId);

                    QuestionBank question = new QuestionBank(id, subjectId, userId, questionText, subjectName, choices, correctAnswer, explain);
                    questions.add(question);
                }
            }
        }
        return questions;
    }
// lấy câu hỏi từ user id và subject id
public List<QuestionBank> getQuestionsBySubjectIdAndUserId(int subjectId, int userId) throws SQLException {
    List<QuestionBank> questions = new ArrayList<>();
    String query = "SELECT * FROM QuestionBank WHERE subject_id = ? AND user_id = ?";

    try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, subjectId);
        statement.setInt(2, userId);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("question_id");
                String questionText = resultSet.getString("question_context");
                List<String> choices = Arrays.asList(
                        resultSet.getString("choice_1"),
                        resultSet.getString("choice_2"),
                        resultSet.getString("choice_3")
                );
                String correctAnswer = resultSet.getString("choice_correct");
                String explain = resultSet.getString("choice_correct_explain");

                QuestionBank question = new QuestionBank(id, subjectId, userId, questionText, choices, correctAnswer, explain);
                questions.add(question);
            }
        }
    }

    return questions;
}


   public double calculateScore(List<String> userAnswers, List<QuestionBank> questions) {
    if (userAnswers.size() != questions.size()) {
        throw new IllegalArgumentException("Number of user answers does not match number of questions");
    }

    int totalQuestions = questions.size();
    int correctAnswers = 0;

    for (int i = 0; i < totalQuestions; i++) {
        QuestionBank question = questions.get(i);
        String userAnswer = userAnswers.get(i);
        if (userAnswer.equals(question.getCorrectAnswer())) {
            correctAnswers++;
        }
    }

    return (double) correctAnswers / totalQuestions * 100;
}




    public List<String> getCorrectAnswers(List<QuestionBank> questions) {
        List<String> correctAnswers = new ArrayList<>();
        for (QuestionBank question : questions) {
            correctAnswers.add(question.getCorrectAnswer());
        }
        return correctAnswers;
    }
}

