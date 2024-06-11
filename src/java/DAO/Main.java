package DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import model.QuestionBank;
import java.util.List;
import model.Exam;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

//public class Main {
//    public static void main(String[] args) {
//        ExamDAO examDAO = new ExamDAO();
//
//        try {
//            // Tạo đề thi mới
//            Exam newExam = new Exam("Final Exam");
//            examDAO.createExam(newExam);
//            System.out.println("Created new exam with ID: " + newExam.getExamId());
//
//            // Lấy tất cả các đề thi và in ra thông tin
//            List<Exam> exams = examDAO.getAllExams();
//            System.out.println("All exams:");
//            for (Exam exam : exams) {
//                System.out.println("Exam ID: " + exam.getExamId() + ", Name: " + exam.getExamName() + ", Created At: " + exam.getCreatedAt());
//            }
//
//            // Lấy đề thi theo ID và in ra thông tin
//            int examId = 1; // Assuming exam ID 1 exists
//            Exam retrievedExam = examDAO.getExamById(examId);
//            if (retrievedExam != null) {
//                System.out.println("Retrieved exam with ID " + examId + ": " + retrievedExam.getExamName());
//            } else {
//                System.out.println("Exam with ID " + examId + " not found.");
//            }
//
//            // Lấy tất cả câu hỏi cho một đề thi và in ra thông tin
//            List<QuestionBank> questionsForExam = examDAO.getQuestionsForExam(examId);
//System.out.println("Questions for exam " + examId + ":");
//for (QuestionBank question : questionsForExam) {
//    System.out.println("Question ID: " + question.getId() + ", Subject: " + question.getSubject() + ", Text: " + question.getQuestionText());
//}
//
//            // Xóa đề thi
//            boolean isDeleted = examDAO.deleteExam(examId);
//            if (isDeleted) {
//                System.out.println("Exam with ID " + examId + " deleted successfully.");
//            } else {
//                System.out.println("Failed to delete exam with ID " + examId);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
public class Main {
    public static void main(String[] args) {
        QuestionDAO questionDAO = new QuestionDAO();

        // Test createMultipleChoiceQuestion method
//        try {
//            QuestionBank newQuestion = new QuestionBank(0, 1, 1, "What is the capital of France?", "Geography",
//                    List.of("Paris", "London", "Berlin"), "Paris", "Paris is the capital of France");
//            boolean questionCreated = questionDAO.createMultipleChoiceQuestion(newQuestion);
//            if (questionCreated) {
//                System.out.println("Question created successfully.");
//            } else {
//                System.out.println("Failed to create question.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        // Test getAllMultipleChoiceQuestions method
        List<QuestionBank> allQuestions = questionDAO.getAllMultipleChoiceQuestions();
        System.out.println("All questions:");
        for (QuestionBank question : allQuestions) {
            System.out.println(question);
        }

//        // Test updateMultipleChoiceQuestion method
//        try {
//            QuestionBank updatedQuestion = new QuestionBank(1, 1, 1, "What is the capital of France?", "Geography",
//                    List.of("Paris", "London", "Berlin"), "Paris", "Paris is the capital of France");
//            boolean questionUpdated = questionDAO.updateMultipleChoiceQuestion(updatedQuestion);
//            if (questionUpdated) {
//                System.out.println("Question updated successfully.");
//            } else {
//                System.out.println("Failed to update question.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Test deleteMultipleChoiceQuestion method
//        try {
//            boolean questionDeleted = questionDAO.deleteMultipleChoiceQuestion(1);
//            if (questionDeleted) {
//                System.out.println("Question deleted successfully.");
//            } else {
//                System.out.println("Failed to delete question.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Test getQuestionsByUserId method
//        List<QuestionBank> userQuestions = questionDAO.getQuestionsByUserId(1);
//        System.out.println("Questions by user:");
//        for (QuestionBank question : userQuestions) {
//            System.out.println(question);
//        }
//
//        // Test getQuestionCountBySubjectId method
//        int questionCount = questionDAO.getQuestionCountBySubjectId(1);
//        System.out.println("Question count by subject ID: " + questionCount);
//
//        // Test getQuestionsForExam method
//        try {
//            List<QuestionBank> examQuestions = questionDAO.getQuestionsForExam(1);
//            System.out.println("Questions for exam:");
//            for (QuestionBank question : examQuestions) {
//                System.out.println(question);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Test calculateScore method
//        List<String> userAnswers = List.of("Paris", "London", "Berlin");
//        double score = questionDAO.calculateScore(userAnswers, allQuestions);
//        System.out.println("Score: " + score);
//
//        // Test getCorrectAnswers method
//        List<String> correctAnswers = questionDAO.getCorrectAnswers(allQuestions);
//        System.out.println("Correct answers:");
//        for (String answer : correctAnswers) {
//            System.out.println(answer);
//        }
    }
}