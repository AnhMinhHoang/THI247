package DAO;

import java.util.Arrays;
import model.QuestionBank;
import java.util.List;
import model.Exam;
import java.sql.SQLException;
import java.util.Scanner;

//public class Main {
//    public static void main(String[] args) {
//        QuestionDAO questionDAO = new QuestionDAO();
//
//        // Test getAllMultipleChoiceQuestions
//        System.out.println("All Multiple Choice Questions:");
//        List<QuestionBank> allQuestions = questionDAO.getAllMultipleChoiceQuestions();
//        for (QuestionBank question : allQuestions) {
//            System.out.println(question);
//        }
//
//        // Test getQuestionById
//        int testId = 1; // Change this ID to test with different question IDs
//        System.out.println("\nQuestion with ID " + testId + ":");
//        QuestionBank questionById = questionDAO.getQuestionById(testId);
//        if (questionById != null) {
//            System.out.println(questionById);
//        } else {
//            System.out.println("No question found with ID " + testId);
//        }
//    }
//}


//public class Main {
//    public static void main(String[] args) throws SQLException {
//        QuestionDAO questionDAO = new QuestionDAO();
//        ExamDAO examDAO = new ExamDAO();
//
//        // Lấy tất cả câu hỏi từ database
//        List<QuestionBank> allQuestions = questionDAO.getAllMultipleChoiceQuestions();
//
//        // Kiểm tra xem có đủ câu hỏi để tạo các đề thi không
//        if (allQuestions.size() < 2) {
//            System.out.println("Không đủ câu hỏi để tạo các đề thi");
//            return;
//        }
//
//        // Lấy câu hỏi từ danh sách để sử dụng ID thực tế
//        QuestionBank dbQuestion1 = allQuestions.get(0);
//        QuestionBank dbQuestion2 = allQuestions.get(1);
//
//        // Tạo đề thi 1 với 1 câu hỏi
//        Exam exam1 = new Exam("Exam 1");
//        exam1.setQuestions(Arrays.asList(dbQuestion1));
//        examDAO.createExam(exam1);
//
//        // Tạo đề thi 2 với 2 câu hỏi
//        Exam exam2 = new Exam("Exam 2");
//        exam2.setQuestions(Arrays.asList(dbQuestion1, dbQuestion2));
//        examDAO.createExam(exam2);
//
//        // Hiển thị tất cả đề thi
//        List<Exam> allExams = examDAO.getAllExams();
//        for (Exam exam : allExams) {
//            System.out.println(exam);
//            List<QuestionBank> examQuestions = examDAO.getQuestionsByExamId(exam.getExamId());
//            for (QuestionBank question : examQuestions) {
//                System.out.println(question);
//            }
//        }
//    }
//}

public class Main {
    public static void main(String[] args) {
        // Khởi tạo một đối tượng ExamDAO
        ExamDAO examDAO = new ExamDAO();

        // ID của đề thi bạn muốn lấy câu hỏi
        int examId = 8; // Thay đổi thành ID của đề thi bạn muốn thử

        try {
            // Gọi phương thức getQuestionsByExamId để lấy danh sách câu hỏi cho đề thi
            List<QuestionBank> questions = examDAO.getQuestionsByExamId(examId);

            // In ra thông tin về các câu hỏi
            System.out.println("Danh sách câu hỏi cho đề thi có ID " + examId + ":");
            for (QuestionBank question : questions) {
                System.out.println("ID: " + question.getId());
                System.out.println("Subject: " + question.getSubject());
                System.out.println("Question Text: " + question.getQuestionText());
                System.out.println("Choices: " + question.getChoices());
                System.out.println("Correct Answer: " + question.getCorrectAnswer());
                System.out.println("Explanation: " + question.getExplain());
                System.out.println();
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ nếu có lỗi khi thực hiện truy vấn
            System.err.println("Lỗi khi thực hiện truy vấn: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

