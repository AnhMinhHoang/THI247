package DAO;

import model.QuestionBank;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionDAO extends DBConnection {

    public boolean createMultipleChoiceQuestion(QuestionBank question) throws SQLException {
        String sql = "INSERT INTO QuestionBank (subject, user_id, question_context, choice_1, choice_2, choice_3, choice_correct, choice_correct_explain) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, question.getSubject());
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

    // READ
    public List<QuestionBank> getAllMultipleChoiceQuestions() {
        List<QuestionBank> questions = new ArrayList<>();
        String query = "SELECT * FROM QuestionBank";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(query)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    // UPDATE
    public boolean updateMultipleChoiceQuestion(QuestionBank question) throws SQLException {
        String sql = "UPDATE QuestionBank SET subject = ?, user_id = ?, question_context = ?, choice_1 = ?, choice_2 = ?, choice_3 = ?, choice_correct = ?, choice_correct_explain = ? WHERE question_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, question.getSubject());
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

    // DELETE
    public boolean deleteMultipleChoiceQuestion(int id) throws SQLException {
        String sql = "DELETE FROM QuestionBank WHERE question_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Calculate Score
    public double calculateScore(List<String> userAnswers, List<QuestionBank> questions) {
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

    // Get correct answers
   public List<String> getCorrectAnswers(List<QuestionBank> questions) {
    List<String> correctAnswers = new ArrayList<>();
    for (QuestionBank question : questions) {
        correctAnswers.add(question.getCorrectAnswer());
    }
    return correctAnswers;
}

    // GET BY ID
    public QuestionBank getQuestionById(int id) {
        String query = "SELECT * FROM QuestionBank WHERE question_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
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
                    return new QuestionBank(id, subject, userId, questionText, choices, correctAnswer, explain);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
  
   public List<QuestionBank> getAllMultipleChoiceQuestionsByUserId(int userId) throws SQLException {
    List<QuestionBank> questions = new ArrayList<>();
    String query = "SELECT * FROM QuestionBank WHERE user_id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, userId);
        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                QuestionBank question = new QuestionBank(
                        resultSet.getInt("question_id"),
                        resultSet.getString("subject"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("question_context"),
                        Arrays.asList(
                                resultSet.getString("choice_1"),
                                resultSet.getString("choice_2"),
                                resultSet.getString("choice_3")
                        ),
                        resultSet.getString("choice_correct"),
                        resultSet.getString("choice_correct_explain")
                );
                questions.add(question);
            }
        }
    }
    return questions;
}

}
