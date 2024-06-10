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

//public class Main {
//    public static void main(String[] args) {
//        // Khởi tạo một đối tượng ExamDAO
//        ExamDAO examDAO = new ExamDAO();
//
//        // ID của đề thi bạn muốn lấy câu hỏi
//        int examId = 8; // Thay đổi thành ID của đề thi bạn muốn thử
//
//        try {
//            // Gọi phương thức getQuestionsByExamId để lấy danh sách câu hỏi cho đề thi
//            List<QuestionBank> questions = examDAO.getQuestionsByExamId(examId);
//
//            // In ra thông tin về các câu hỏi
//            System.out.println("Danh sách câu hỏi cho đề thi có ID " + examId + ":");
//            for (QuestionBank question : questions) {
//                System.out.println("ID: " + question.getId());
//                System.out.println("Subject: " + question.getSubject());
//                System.out.println("Question Text: " + question.getQuestionText());
//                System.out.println("Choices: " + question.getChoices());
//                System.out.println("Correct Answer: " + question.getCorrectAnswer());
//                System.out.println("Explanation: " + question.getExplain());
//                System.out.println();
//            }
//        } catch (SQLException e) {
//            // Xử lý ngoại lệ nếu có lỗi khi thực hiện truy vấn
//            System.err.println("Lỗi khi thực hiện truy vấn: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}

//public class Main {
//
//    public static void main(String[] args) throws IOException {
//        // Tạo một đối tượng QuestionDAO
//        QuestionDAO questionDAO = new QuestionDAO();
//
//        // Lấy tất cả các câu hỏi từ cơ sở dữ liệu
//        List<QuestionBank> questions = questionDAO.getAllMultipleChoiceQuestions();
//        // Tạo một danh sách câu trả lời người dùng (chỉ làm mẫu, cần phải thay đổi theo thực tế)
//        List<String> userAnswers = getUserAnswers(questions);
//        // Tính điểm
//        double score = questionDAO.calculateScore(userAnswers, questions);
//        // In ra màn hình
//        System.out.println("Điểm số: " + score);
//        // Lấy danh sách câu trả lời đúng
//        List<String> correctAnswers = questionDAO.getCorrectAnswers(questions);
//        // In ra màn hình
//        System.out.println("Câu trả lời đúng:");
//        for (int i = 0; i < questions.size(); i++) {
//            System.out.println("Câu " + (i + 1) + ": " + correctAnswers.get(i));
//        }
//    }
//
//    public static List<String> getUserAnswers(List<QuestionBank> questions) throws IOException {
//        List<String> userAnswers = new ArrayList<>();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        for (int i = 0; i < questions.size(); i++) {
//            System.out.print("Nhập câu trả lời cho câu " + (i + 1) + ": ");
//            String answer = reader.readLine();
//            userAnswers.add(answer);
//        }
//        return userAnswers;
//    }
//}

public class Main {
    public static void main(String[] args) {
        // Đây là một ví dụ về cách sử dụng phương thức getQuestionsForExam để lấy danh sách câu hỏi cho một đề thi cụ thể.
        int examId = 11; // Đổi examId thành ID của đề thi mà bạn muốn lấy câu hỏi
        ExamDAO examDAO = new ExamDAO();

        try {
            List<QuestionBank> questions = examDAO.getQuestionsForExam(examId);
            if (questions != null && !questions.isEmpty()) {
                System.out.println("Danh sách câu hỏi cho đề thi có examId = " + examId + ":");
                for (QuestionBank question : questions) {
                    System.out.println("Câu hỏi ID: " + question.getId());
                    System.out.println("Nội dung câu hỏi: " + question.getQuestionText());
                    System.out.println("Đáp án đúng: " + question.getCorrectAnswer());
                    System.out.println("Giải thích: " + question.getExplain());
                    System.out.println("-----------------------");
                }
            } else {
                System.out.println("Không tìm thấy câu hỏi cho đề thi có examId = " + examId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}