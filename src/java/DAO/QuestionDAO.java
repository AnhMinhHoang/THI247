/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;

import model.MultipleChoiceQuestion;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuestionDAO extends DBConnection{


    

    // CREATE
    public boolean createMultipleChoiceQuestion(MultipleChoiceQuestion question) {
        String sql = "INSERT INTO MultipleChoiceQuestions (question_text, choice1, choice2, choice3, choice4, correct_answer) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, question.getQuestionText());
            List<String> choices = question.getChoices();
            for (int i = 0; i < 4; i++) {
                stmt.setString(i + 2, choices.get(i));
            }
            stmt.setString(6, question.getCorrectAnswer());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ
     public List<MultipleChoiceQuestion> getAllMultipleChoiceQuestions() {
        List<MultipleChoiceQuestion> questions = new ArrayList<>();
        String query = "SELECT * FROM MultipleChoiceQuestions";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String questionText = resultSet.getString("question_text");
                List<String> choices = Arrays.asList(
                        resultSet.getString("choice1"),
                        resultSet.getString("choice2"),
                        resultSet.getString("choice3"),
                        resultSet.getString("choice4")
                );
                String correctAnswer = resultSet.getString("correct_answer");

                MultipleChoiceQuestion question = new MultipleChoiceQuestion(id, questionText, choices, correctAnswer);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    // UPDATE
    public boolean updateMultipleChoiceQuestion(MultipleChoiceQuestion question) {
        String sql = "UPDATE MultipleChoiceQuestions SET question_text = ?, choice1 = ?, choice2 = ?, choice3 = ?, choice4 = ?, correct_answer = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, question.getQuestionText());
            List<String> choices = question.getChoices();
            for (int i = 0; i < 4; i++) {
                stmt.setString(i + 2, choices.get(i));
            }
            stmt.setString(6, question.getCorrectAnswer());
            stmt.setLong(7, question.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteMultipleChoiceQuestion(int id) {
        String sql = "DELETE FROM MultipleChoiceQuestions WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
   public double calculateScore(List<String> userAnswers, List<MultipleChoiceQuestion> questions) {
        int totalQuestions = questions.size();
        int correctAnswers = 0;

        for (int i = 0; i < totalQuestions; i++) {
            MultipleChoiceQuestion question = questions.get(i);
            String userAnswer = userAnswers.get(i);
            if (userAnswer.equals(question.getCorrectAnswer())) {
                correctAnswers++;
            }
        }

        return (double) correctAnswers / totalQuestions * 100;
    }


    // READ - Get correct answers
public List<String> getCorrectAnswers(List<MultipleChoiceQuestion> questions) {
    List<String> correctAnswers = new ArrayList<>();
    for (MultipleChoiceQuestion question : questions) {
        correctAnswers.add(question.getCorrectAnswer());
    }
    return correctAnswers;
}


    

   public static void main(String[] args) {
    // Create a new QuestionDAO instance
    QuestionDAO questionDAO = new QuestionDAO();
   

    // Create a MultipleChoiceQuestion object
//    List<String> choices = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
//    MultipleChoiceQuestion question = new MultipleChoiceQuestion(3, "aaa", choices, "d");
//
//    // Add the question to the database
//    boolean success = questionDAO.createMultipleChoiceQuestion(question);
//    if (success) {
//        System.out.println("Question created successfully!");
//    } else {
//        System.out.println("Failed to create question.");
//    }
//
//    // Fetch and print all questions from the database
//    List<MultipleChoiceQuestion> questions = questionDAO.getAllMultipleChoiceQuestions();
//    for (MultipleChoiceQuestion q : questions) {
//        System.out.println("ID: " + q.getId());
//        System.out.println("Question: " + q.getQuestionText());
//        System.out.println("Choices: " + q.getChoices());
//        System.out.println("Correct Answer: " + q.getCorrectAnswer());
//    }


//        // Fetch all multiple-choice questions from the database
//        List<MultipleChoiceQuestion> questions = questionDAO.getAllMultipleChoiceQuestions();
//
//        // Display questions and allow the user to select answers
//        List<String> userAnswers = QuestionDAO.getUserAnswers(questions);
//
//        // Calculate and display the user's score
//        double score = questionDAO.calculateScore(userAnswers, questions);
//        System.out.println("Your score: " + score + "%");
   }
}




