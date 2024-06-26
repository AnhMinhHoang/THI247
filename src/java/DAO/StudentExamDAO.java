/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBConnection.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Exam;
import model.Result;
import model.StudentChoice;
import model.Tests;

/**
 *
 * @author GoldCandy
 */
public class StudentExamDAO {

    //Get all exam by subjectID
    public List<Exam> getAllExamBySubjectID(int subjectID) {
        String query = "SELECT * FROM Exam Where subject_id = ?";
        List<Exam> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, subjectID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Exam exam = new Exam();
                exam.setExamID(rs.getInt(1));
                exam.setExamName(rs.getString(2));
                exam.setCreateDate(rs.getString(3));
                exam.setUserID(rs.getInt(4));
                exam.setSubjectID(rs.getInt(5));
                list.add(exam);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    //get all finished test by userID
    public List<Tests> getAllTestByUserID(int userID){
        String query = "SELECT * FROM Tests Where userID = ? and time_left = 0";
        List<Tests> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tests test = new Tests();
                test.setTestID(rs.getInt(1));
                test.setUserID(rs.getInt(2));
                test.setExamID(rs.getInt(3));
                test.setTimeLeft(rs.getInt(4));
                test.setSeed(rs.getLong(5));
                list.add(test);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    //get latest Test by userID and timer > 0
    public Tests getLatestTest(int userID){
        String query = "SELECT * FROM Tests Where userID = ? and time_left > 0";
        Tests test = new Tests();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                test.setTestID(rs.getInt(1));
                test.setUserID(rs.getInt(2));
                test.setExamID(rs.getInt(3));
                test.setTimeLeft(rs.getInt(4));
                test.setSeed(rs.getLong(5));
                return test;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    //get test by testID
    public Tests getTestByTestID(int testID){
        String query = "SELECT * FROM Tests Where test_id = ?";
        Tests test = new Tests();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, testID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                test.setTestID(rs.getInt(1));
                test.setUserID(rs.getInt(2));
                test.setExamID(rs.getInt(3));
                test.setTimeLeft(rs.getInt(4));
                test.setSeed(rs.getLong(5));
                return test;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //Create new test
    public void createTest(int userID, int examID, int timeLeft, long seed) {
        String query = "DBCC CHECKIDENT (Tests, RESEED, 0); DBCC CHECKIDENT (Tests, RESEED); insert into Tests(userID, exam_id, time_left, shuffle_seed) values(?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setInt(2, examID);
            ps.setInt(3, timeLeft);
            ps.setLong(4, seed);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void updateTestTime(int testID, int timeLeft) {
        String query = "update Tests set time_left = ? where test_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, timeLeft);
            ps.setInt(2, testID);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    //delete test by test ID
    public void deleteTest(int testID) {
        String query = "DELETE FROM Tests WHERE test_id = ? DBCC CHECKIDENT (Tests, RESEED, 0); DBCC CHECKIDENT (Tests, RESEED);";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, testID);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    //Get selected choice by test id
    public StudentChoice getSelectedChoice(int testID, int questionID) {
        String query = "SELECT * FROM StudentChoice Where test_id = ? and question_id = ?";
        StudentChoice sc = new StudentChoice();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, testID);
            ps.setInt(2, questionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sc.setAnswerID(rs.getInt(1));
                sc.setTestID(rs.getInt(2));
                sc.setQuestionID(rs.getInt(3));
                sc.setSelectedChoice(rs.getString(4));
                return sc;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<StudentChoice> getAllSelectedChoice(int testID) {
        String query = "SELECT * FROM StudentChoice Where test_id = ?";
        List<StudentChoice> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, testID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentChoice sc = new StudentChoice();
                sc.setAnswerID(rs.getInt(1));
                sc.setTestID(rs.getInt(2));
                sc.setQuestionID(rs.getInt(3));
                sc.setSelectedChoice(rs.getString(4));
                list.add(sc);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //Add selected choice to studentChoice
    public void addSelectedChoice(int testID, int questionID, String selectedChoice) {
        String query = "DBCC CHECKIDENT (StudentChoice, RESEED, 0); DBCC CHECKIDENT (StudentChoice, RESEED); insert into StudentChoice(test_id, question_id, selected_choice) values(?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, testID);
            ps.setInt(2, questionID);
            ps.setString(3, selectedChoice);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void updateSelectedChoice(int answer_id, String selectedChoice) {
        String query = "update StudentChoice set selected_choice = ? where answer_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, selectedChoice);
            ps.setInt(2, answer_id);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    //delete all selected choice after complete test by test ID
    public void deleteAllSelectedChoice(int testID) {
        String query = "DELETE FROM StudentChoice WHERE test_id = ? DBCC CHECKIDENT (StudentChoice, RESEED, 0); DBCC CHECKIDENT (StudentChoice, RESEED);";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, testID);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    //create result after test is done
    public void createResult(int userID, int examID, float score, int rightAnswer, int totalQuestion, int testID) {
        String query = "DBCC CHECKIDENT (Result, RESEED, 0); DBCC CHECKIDENT (Result, RESEED); "
                + "insert into Result(userID, exam_id, submit_date, right_answer, total_question, score, test_id) "
                + "values(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setInt(2, examID);
            String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date());
            ps.setString(3, timeStamp);
            ps.setInt(4, rightAnswer);
            ps.setInt(5, totalQuestion);
            ps.setFloat(6, score);
            ps.setInt(7, testID);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    //get lastest result
    public Result getLastestExam() {
        Result result = new Result();
        String query = "Select top 1 * from Result order by result_id desc";
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.setResultID(rs.getInt(1));
                result.setUserID(rs.getInt(2));
                result.setExamID(rs.getInt(3));
                result.setSubmitDate(rs.getString(4));
                result.setRightAnswer(rs.getInt(5));
                result.setTotalQuestion(rs.getInt(6));
                result.setScore(rs.getFloat(7));
                result.setTestID(rs.getInt(8));
                return result;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    //get result by result ID
    public Result getResultByID(int resultID){
        Result result = new Result();
        String query = "Select * from Result where result_id = ?";
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, resultID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.setResultID(rs.getInt(1));
                result.setUserID(rs.getInt(2));
                result.setExamID(rs.getInt(3));
                result.setSubmitDate(rs.getString(4));
                result.setRightAnswer(rs.getInt(5));
                result.setTotalQuestion(rs.getInt(6));
                result.setScore(rs.getFloat(7));
                result.setTestID(rs.getInt(8));
                return result;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    //get result by test ID
    public Result getResultByTestID(int testID){
        Result result = new Result();
        String query = "Select * from Result where test_id = ?";
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, testID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.setResultID(rs.getInt(1));
                result.setUserID(rs.getInt(2));
                result.setExamID(rs.getInt(3));
                result.setSubmitDate(rs.getString(4));
                result.setRightAnswer(rs.getInt(5));
                result.setTotalQuestion(rs.getInt(6));
                result.setScore(rs.getFloat(7));
                result.setTestID(rs.getInt(8));
                return result;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Result> getAllResultByUserID(int userID){
        String query = "SELECT * FROM Result Where userID = ?";
        List<Result> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Result result = new Result();
                result.setResultID(rs.getInt(1));
                result.setUserID(rs.getInt(2));
                result.setExamID(rs.getInt(3));
                result.setSubmitDate(rs.getString(4));
                result.setRightAnswer(rs.getInt(5));
                result.setTotalQuestion(rs.getInt(6));
                result.setScore(rs.getFloat(7));
                result.setTestID(rs.getInt(8));
                list.add(result);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //get all result of the test
    //get all result from user
    public static void main(String[] args) {
        String text = "Câu 1:";
        int num = 1;
        String a = "Câu " +num+":";
        if(text.startsWith("Câu " +num+":")) System.out.println("OK");
        System.out.println(a);
    }
}
