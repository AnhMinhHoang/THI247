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
//        // Thay thế userId và các thông tin câu hỏi tùy theo nhu cầu của bạn
//        int userId = 7;
//        int questionIdToUpdate = 2; // ID của câu hỏi cần cập nhật
//
//        // Tạo một đối tượng QuestionBank mới với thông tin cập nhật
//        QuestionBank question = new QuestionBank(2, 2, 7, "What is Java?", "Programming", Arrays.asList("A", "B", "C"), "A", "Java is a programming language.");
//
//        // Khởi tạo đối tượng QuestionDAO
//        QuestionDAO questionDAO = new QuestionDAO();
//
//        try {
//            // Gọi phương thức updateMultipleChoiceQuestion
//            boolean updated = questionDAO.updateMultipleChoiceQuestion(question);
//            if (updated) {
//                System.out.println("Cập nhật câu hỏi thành công");
//            } else {
//                System.out.println("Cập nhật câu hỏi thất bại");
//            }
//        } catch (SQLException e) {
//            System.out.println("Lỗi SQL: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}

//public class Main {
//    public static void main(String[] args) {
//        // Tạo một câu hỏi mới
//        int subjectId = 1; // Thay thế 1 bằng id thực của một môn học trong cơ sở dữ liệu của bạn
//        int userId = 1; // Thay thế 1 bằng id thực của một người dùng trong cơ sở dữ liệu của bạn
//        String questionText = "What is the capital of France?";
//        List<String> choices = Arrays.asList("London", "Paris", "Berlin", "Rome");
//        String correctAnswer = "Paris";
//        String explain = "Paris is the capital of France.";
//        
//        QuestionBank newQuestion = new QuestionBank(0, subjectId, userId, questionText, choices, correctAnswer, explain);
//
//        // Thử nghiệm hàm createMultipleChoiceQuestion
//        try {
//            QuestionDAO questionDAO = new QuestionDAO();
//            boolean success = questionDAO.createMultipleChoiceQuestion(newQuestion);
//            if (success) {
//                System.out.println("Question created successfully.");
//            } else {
//                System.out.println("Failed to create question.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("An error occurred while creating question.");
//        }
//    }
//}
//public class Main {
//    public static void main(String[] args) {
//        try {
//            // Tạo một đối tượng QuestionDAO
//            QuestionDAO questionDAO = new QuestionDAO();
//
//            // Gọi phương thức getAllSubjects() để lấy danh sách các môn học từ cơ sở dữ liệu
//            List<String> subjects = questionDAO.getAllSubjects();
//
//            // In ra danh sách các môn học
//            System.out.println("Danh sách các môn học:");
//            for (String subject : subjects) {
//                System.out.println(subject);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Đã xảy ra lỗi khi truy vấn cơ sở dữ liệu.");
//        }
//    }
//}

//public class Main {
//    public static void main(String[] args) {
//        int userId = 7; // Thay đổi userId tùy theo người dùng cần kiểm tra
//        try {
//            // Tạo đối tượng ExamDAO
//            ExamDAO examDAO = new ExamDAO();
//            
//            // Gọi phương thức để lấy danh sách các bài kiểm tra của userId
//            List<Exam> exams = examDAO.getExamsByUserId(userId);
//            
//            // Hiển thị thông tin về các bài kiểm tra
//            System.out.println("Danh sách các bài kiểm tra của userId " + userId + ":");
//            for (Exam exam : exams) {
//                System.out.println("Exam ID: " + exam.getExamId() + ", Exam Name: " + exam.getExamName() + ", User ID: " + exam.getUserId());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Xử lý ngoại lệ nếu cần
//        }
//    }
//}
public class Main {
    public static void main(String[] args) {
        int examId = 6; // Thay đổi giá trị này bằng examId thực tế bạn muốn kiểm tra
        int userId = 7; // Thay đổi giá trị này bằng userId thực tế của người dùng đã đăng nhập

        // Gọi hàm getQuestionsForExam và in ra các câu hỏi
        try {
            ExamDAO examDAO = new ExamDAO();
            List<QuestionBank> questions = examDAO.getQuestionsForExam(examId, userId);
            for (QuestionBank question : questions) {
                System.out.println("Question ID: " + question.getId());
                System.out.println("Subject ID: " + question.getSubjectId());
                System.out.println("Question Text: " + question.getQuestionText());
                System.out.println("Subject Name: " + question.getSubject());
                System.out.println("Choices: " + question.getChoices());
                System.out.println("Correct Answer: " + question.getCorrectAnswer());
                System.out.println("Explanation: " + question.getExplain());
                System.out.println("----------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}