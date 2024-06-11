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
        if (getSubjectNameById(subjectId) == null) {
            // Xử lý nếu không tìm thấy subjectName trong cơ sở dữ liệu
            return false;
        }
        stmt.setInt(1, subjectId);
        stmt.setInt(2, question.getUserId());
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
        String sql = "UPDATE QuestionBank SET subject_id = ?, user_id = ?, question_context = ?, choice_1 = ?, choice_2 = ?, choice_3 = ?, choice_correct = ?, choice_correct_explain = ? WHERE question_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, question.getSubjectId());
            stmt.setInt(2, question.getUserId());
            stmt.setString(3, question.getQuestionText());
            List<String> choices = question.getChoices();
            stmt.setString(4, choices.get(0));
            stmt.setString(5, choices.get(1));
            stmt.setString(6, choices.get(2));
            stmt.setString(7, question.getCorrectAnswer());
            stmt.setString(8, question.getExplain());
            stmt.setInt(9, question.getId());

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

    public List<QuestionBank> getQuestionsForExam(int examId) throws SQLException {
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
                    QuestionBank question = new QuestionBank(id, subjectId, userId, questionText, choices, correctAnswer, explain);
                    questions.add(question);
                }
            }
        }
        return questions;
    }

public List<String> getAllSubjects() throws SQLException {
    List<String> subjects = new ArrayList<>();
    String query = "SELECT DISTINCT subject FROM QuestionBank";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(query)) {
        while (resultSet.next()) {
            String subject = resultSet.getString("subject");
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

