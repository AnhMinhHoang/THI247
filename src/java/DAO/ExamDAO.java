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
import model.QuestionBank;
import model.Subjects;
import model.Tests;

/**
 *
 * @author GoldCandy
 */
public class ExamDAO extends DBConnection {
    //Delete question
    public void deleteQuestion(int questionID){
        String query = "delete from ExamQuestion where question_id = ?"
                + "delete from StudentChoice where question_id = ?"
                + "delete from QuestionBank where question_id = ? DBCC CHECKIDENT (QuestionBank, RESEED, 0); DBCC CHECKIDENT (QuestionBank, RESEED);";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, questionID);
            ps.setInt(2, questionID);
            ps.setInt(3, questionID);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
        
    }
    
    //Add exam
    public void addExam(String examName, int userID, int subjectID, int examTime, int price) {
        String query = "DBCC CHECKIDENT (Exam, RESEED, 0); DBCC CHECKIDENT (Exam, RESEED); insert into Exam(exam_name, create_date, userID, subject_id, timer, price) values(?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, examName);
            String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date());
            ps.setString(2, timeStamp);
            ps.setInt(3, userID);
            ps.setInt(4, subjectID);
            ps.setInt(5, examTime);
            ps.setInt(6, price);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    //Get all exam by userID
    public List<Exam> getAllExamByUserID(int userID) {
        String query = "SELECT * FROM Exam Where userID = ?";
        List<Exam> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Exam exam = new Exam();
                exam.setExamID(rs.getInt(1));
                exam.setExamName(rs.getString(2));
                exam.setCreateDate(rs.getString(3));
                exam.setUserID(rs.getInt(4));
                exam.setSubjectID(rs.getInt(5));
                exam.setTimer(rs.getInt(6));
                exam.setPrice(rs.getInt(7));
                list.add(exam);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //Get exam by examID
    public Exam getExamByID(int examID) {
        String query = "SELECT * FROM Exam Where exam_id = ?";
        Exam exam = new Exam();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, examID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                exam.setExamID(rs.getInt(1));
                exam.setExamName(rs.getString(2));
                exam.setCreateDate(rs.getString(3));
                exam.setUserID(rs.getInt(4));
                exam.setSubjectID(rs.getInt(5));
                exam.setTimer(rs.getInt(6));
                exam.setPrice(rs.getInt(7));
                return exam;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //Get all question from exam by exam id
    public List<QuestionBank> getAllQuestionByExamID(int examID) {
        String query = "SELECT * from QuestionBank join ExamQuestion on QuestionBank.question_id = ExamQuestion.question_id where exam_id = ?";
        List<QuestionBank> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, examID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuestionBank qb = new QuestionBank();
                qb.setQuestionId(rs.getInt(1));
                qb.setSubjectId(rs.getInt(2));
                qb.setQuestionContext(rs.getString(3));
                qb.setChoice1(rs.getString(4));
                qb.setChoice2(rs.getString(5));
                qb.setChoice3(rs.getString(6));
                qb.setChoiceCorrect(rs.getString(7));
                qb.setExplain(rs.getString(8));
                qb.setQuestionImg(rs.getString(9));
                qb.setExplainImg(rs.getString(10));
                qb.setUserID(rs.getInt(11));
                list.add(qb);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //Update exam
    //Delete exam
    public void deleteExamByExamID(int examID) {
        List<Tests> list = new StudentExamDAO().getTestByExamID(examID);
        for(Tests test: list){
            new StudentExamDAO().deleteStudentChoiceByTestID(test.getTestID());
        }
        String query = "delete from ExamPayment where exam_id = ?"
                + "delete from Result where exam_id = ? delete from Tests where exam_id = ?"
                + "DELETE FROM ExamQuestion WHERE exam_id = ?; DELETE FROM Exam WHERE exam_id = ?; DBCC CHECKIDENT (Exam, RESEED, 0); DBCC CHECKIDENT (Exam, RESEED);";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, examID);
            ps.setInt(2, examID);
            ps.setInt(3, examID);
            ps.setInt(4, examID);
            ps.setInt(5, examID);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Delete question in exam
    public void deleteQuestionInExam(int questionID, int examID) {
        String query = "ALTER TABLE ExamQuestion DROP CONSTRAINT fk_question_id_examquestion; "
                + "DELETE FROM ExamQuestion WHERE exam_id = ? and question_id = ?;"
                + "delete from StudentChoice where question_id = ?"
                + " DBCC CHECKIDENT (ExamQuestion, RESEED, 0); DBCC CHECKIDENT (ExamQuestion, RESEED);"
                + "ALTER TABLE ExamQuestion ADD CONSTRAINT fk_question_id_examquestion FOREIGN KEY (question_id) REFERENCES QuestionBank(question_id);";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, examID);
            ps.setInt(2, questionID);
            ps.setInt(3, questionID);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Add question to certain exam
    public void addQuestionToExam(int questionID, int examID) {
        String query = "DBCC CHECKIDENT (ExamQuestion, RESEED, 0); DBCC CHECKIDENT (ExamQuestion, RESEED); insert into ExamQuestion(question_id, exam_id) values(?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, questionID);
            ps.setInt(2, examID);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    
    //Add question to questionBank by userID and subjectID
    public void addQuestionToQuestionBank(QuestionBank qb) {
        String query = "DBCC CHECKIDENT (QuestionBank, RESEED, 0); DBCC CHECKIDENT (QuestionBank, RESEED); insert into QuestionBank(subject_id, question_context, question_choice_1, "
                + "question_choice_2, question_choice_3, question_choice_correct, question_explain, "
                + "question_img, question_explain_img, userID) "
                + "Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, qb.getSubjectId());
            ps.setString(2, qb.getQuestionContext());
            ps.setString(3, qb.getChoice1());
            ps.setString(4, qb.getChoice2());
            ps.setString(5, qb.getChoice3());
            ps.setString(6, qb.getChoiceCorrect());
            ps.setString(7, qb.getExplain());
            ps.setString(8, qb.getQuestionImg());
            ps.setString(9, qb.getExplainImg());
            ps.setInt(10, qb.getUserID());
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    //Get latest exam
    public Exam getLastestExam() {
        Exam exam = new Exam();
        String query = "Select top 1 * from Exam order by exam_id desc";
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                exam.setExamID(rs.getInt(1));
                exam.setExamName(rs.getString(2));
                exam.setCreateDate(rs.getString(3));
                exam.setUserID(rs.getInt(4));
                exam.setSubjectID(rs.getInt(5));
                exam.setTimer(rs.getInt(6));
                exam.setPrice(rs.getInt(7));
                return exam;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    //change exam name UPDATE Exam SET exam_name = ? WHERE exam_id = ?
    public void changeExamName(int examID, String examName){
        String query = "UPDATE Exam SET exam_name = ? WHERE exam_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, examName);
            ps.setInt(2, examID);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    
    //change exam time
    public void changeExamTime(int examID, int timer){
        String query = "UPDATE Exam SET timer = ? WHERE exam_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, timer);
            ps.setInt(2, examID);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    //Add result
    //DOC/Excel reader
    //Get random question (SELECT * FROM forum_comment ORDER BY NEWID())
    public List<QuestionBank> getRandomQuestByAmount(int amount, int subjectID) {
        String query = "SELECT top " + amount + " * FROM QuestionBank Where subject_id = ? ORDER BY NEWID()";
        List<QuestionBank> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, subjectID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuestionBank qb = new QuestionBank();
                qb.setQuestionId(rs.getInt(1));
                qb.setSubjectId(rs.getInt(2));
                qb.setQuestionContext(rs.getString(3));
                qb.setChoice1(rs.getString(4));
                qb.setChoice2(rs.getString(5));
                qb.setChoice3(rs.getString(6));
                qb.setChoiceCorrect(rs.getString(7));
                qb.setExplain(rs.getString(8));
                qb.setQuestionImg(rs.getString(9));
                qb.setExplainImg(rs.getString(10));
                qb.setUserID(rs.getInt(11));
                list.add(qb);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    //get random quest by amount and not duplicate by subject id, amount and exam id
    public List<QuestionBank> getRandomQuestByAmountND(int amount, int subjectID, int exam_id, int userID) {
        String query;
        List<QuestionBank> list = new ArrayList<>();
        if(userID != 1){
            query = "select top "+ amount + " * from QuestionBank "
                    + "left join ExamQuestion on QuestionBank.question_id = ExamQuestion.question_id and exam_id = ? "
                    + "WHERE ExamQuestion.question_id IS NULL and subject_id = ? and (userID = 1 or userID = ?) ORDER BY NEWID()";
            try (Connection con = getConnection()) {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, exam_id);
                ps.setInt(2, subjectID);
                ps.setInt(3, userID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    QuestionBank qb = new QuestionBank();
                    qb.setQuestionId(rs.getInt(1));
                    qb.setSubjectId(rs.getInt(2));
                    qb.setQuestionContext(rs.getString(3));
                    qb.setChoice1(rs.getString(4));
                    qb.setChoice2(rs.getString(5));
                    qb.setChoice3(rs.getString(6));
                    qb.setChoiceCorrect(rs.getString(7));
                    qb.setExplain(rs.getString(8));
                    qb.setQuestionImg(rs.getString(9));
                    qb.setExplainImg(rs.getString(10));
                    qb.setUserID(rs.getInt(11));
                    list.add(qb);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else{
            query = "select top "+ amount + " * from QuestionBank "
                    + "left join ExamQuestion on QuestionBank.question_id = ExamQuestion.question_id and exam_id = ? "
                    + "WHERE ExamQuestion.question_id IS NULL and subject_id = ? and userID = 1 ORDER BY NEWID()";
            try (Connection con = getConnection()) {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, exam_id);
                ps.setInt(2, subjectID);
                ps.setInt(3, userID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    QuestionBank qb = new QuestionBank();
                    qb.setQuestionId(rs.getInt(1));
                    qb.setSubjectId(rs.getInt(2));
                    qb.setQuestionContext(rs.getString(3));
                    qb.setChoice1(rs.getString(4));
                    qb.setChoice2(rs.getString(5));
                    qb.setChoice3(rs.getString(6));
                    qb.setChoiceCorrect(rs.getString(7));
                    qb.setExplain(rs.getString(8));
                    qb.setQuestionImg(rs.getString(9));
                    qb.setExplainImg(rs.getString(10));
                    qb.setUserID(rs.getInt(11));
                    list.add(qb);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    //Get all subject
    public List<Subjects> getAllSubject() {
        String query = "select * from Subjects";
        List<Subjects> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subjects subject = new Subjects();
                subject.setSubjectID(rs.getInt(1));
                subject.setSubjectName(rs.getString(2));
                subject.setSubjectImg(rs.getString(3));
                list.add(subject);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //Get subject by subjectID
    public Subjects getSubjectByID(int subjectID) {
        String query = "select * from Subjects where subject_id = ?";
        Subjects subject = new Subjects();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, subjectID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subject.setSubjectID(rs.getInt(1));
                subject.setSubjectName(rs.getString(2));
                subject.setSubjectImg(rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return subject;
    }

    //Get all question from exam
    public int getQuestionAmount(int examID) {
        int sum = 0;
        String query = "select Count(exam_id) from ExamQuestion where exam_id = ?";
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, examID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sum += rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sum;
    }

    //Get max number of question from subject with userID
    public int getMaxQuestion(int userID, int subjectID) {
        String query;
        int sum = 0;
        if(userID != 1){
            query = "select Count(subject_id) from QuestionBank where subject_id = ? and userID = 1";
            sum = 0;
            try (Connection con = getConnection()) {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, subjectID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    sum += rs.getInt(1);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        query = "select Count(subject_id) from QuestionBank where subject_id = ? and userID = ?";
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, subjectID);
            ps.setInt(2, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sum += rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sum;
    }
    
    //Get all system question from subject using subject ID
    public List<QuestionBank> getAllSystemQuestionByID(int subjectID) {
        String query = "select * from QuestionBank where userID = 1 and subject_id = ?";
        List<QuestionBank> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, subjectID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuestionBank qb = new QuestionBank();
                qb.setQuestionId(rs.getInt(1));
                qb.setSubjectId(rs.getInt(2));
                qb.setQuestionContext(rs.getString(3));
                qb.setChoice1(rs.getString(4));
                qb.setChoice2(rs.getString(5));
                qb.setChoice3(rs.getString(6));
                qb.setChoiceCorrect(rs.getString(7));
                qb.setExplain(rs.getString(8));
                qb.setQuestionImg(rs.getString(9));
                qb.setExplainImg(rs.getString(10));
                qb.setUserID(rs.getInt(11));
                list.add(qb);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<QuestionBank> getAllSystemQuestion() {
        String query = "select * from QuestionBank where userID = 1";
        List<QuestionBank> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuestionBank qb = new QuestionBank();
                qb.setQuestionId(rs.getInt(1));
                qb.setSubjectId(rs.getInt(2));
                qb.setQuestionContext(rs.getString(3));
                qb.setChoice1(rs.getString(4));
                qb.setChoice2(rs.getString(5));
                qb.setChoice3(rs.getString(6));
                qb.setChoiceCorrect(rs.getString(7));
                qb.setExplain(rs.getString(8));
                qb.setQuestionImg(rs.getString(9));
                qb.setExplainImg(rs.getString(10));
                qb.setUserID(rs.getInt(11));
                list.add(qb);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    //Get all question from user using userID
    public List<QuestionBank> getAllUserQuestionByID(int subjectID, int userID) {
        String query = "select * from QuestionBank where userID = ? and subject_id = ?";
        List<QuestionBank> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, subjectID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuestionBank qb = new QuestionBank();
                qb.setQuestionId(rs.getInt(1));
                qb.setSubjectId(rs.getInt(2));
                qb.setQuestionContext(rs.getString(3));
                qb.setChoice1(rs.getString(4));
                qb.setChoice2(rs.getString(5));
                qb.setChoice3(rs.getString(6));
                qb.setChoiceCorrect(rs.getString(7));
                qb.setExplain(rs.getString(8));
                qb.setQuestionImg(rs.getString(9));
                qb.setExplainImg(rs.getString(10));
                qb.setUserID(rs.getInt(11));
                list.add(qb);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //Get all question from subject using subject ID
    public List<QuestionBank> getAllQuestionByID(int subjectID, int userID) {
        String query;
        List<QuestionBank> list = new ArrayList<>();
        if(userID != 1){
            query = "select * from QuestionBank where userID = 1 and subject_id = ?";
            try (Connection con = getConnection()) {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, subjectID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    QuestionBank qb = new QuestionBank();
                    qb.setQuestionId(rs.getInt(1));
                    qb.setSubjectId(rs.getInt(2));
                    qb.setQuestionContext(rs.getString(3));
                    qb.setChoice1(rs.getString(4));
                    qb.setChoice2(rs.getString(5));
                    qb.setChoice3(rs.getString(6));
                    qb.setChoiceCorrect(rs.getString(7));
                    qb.setExplain(rs.getString(8));
                    qb.setQuestionImg(rs.getString(9));
                    qb.setExplainImg(rs.getString(10));
                    qb.setUserID(rs.getInt(11));
                    list.add(qb);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        query = "select * from QuestionBank where userID = ? and subject_id = ?";
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, subjectID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuestionBank qb = new QuestionBank();
                qb.setQuestionId(rs.getInt(1));
                qb.setSubjectId(rs.getInt(2));
                qb.setQuestionContext(rs.getString(3));
                qb.setChoice1(rs.getString(4));
                qb.setChoice2(rs.getString(5));
                qb.setChoice3(rs.getString(6));
                qb.setChoiceCorrect(rs.getString(7));
                qb.setExplain(rs.getString(8));
                qb.setQuestionImg(rs.getString(9));
                qb.setExplainImg(rs.getString(10));
                qb.setUserID(rs.getInt(11));
                list.add(qb);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //Get question that not selected to exam
    public List<QuestionBank> getAllQuestionNotInExam(int subjectID, int userID, int examID) {
        String query;
        List<QuestionBank> list = new ArrayList<>();
        if(userID != 1){
            query = " select * from QuestionBank "
                    + "left join ExamQuestion on QuestionBank.question_id = ExamQuestion.question_id and exam_id = ? "
                    + "WHERE ExamQuestion.question_id IS NULL and subject_id = ? and userID = 1";
            try (Connection con = getConnection()) {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, examID);
                ps.setInt(2, subjectID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    QuestionBank qb = new QuestionBank();
                    qb.setQuestionId(rs.getInt(1));
                    qb.setSubjectId(rs.getInt(2));
                    qb.setQuestionContext(rs.getString(3));
                    qb.setChoice1(rs.getString(4));
                    qb.setChoice2(rs.getString(5));
                    qb.setChoice3(rs.getString(6));
                    qb.setChoiceCorrect(rs.getString(7));
                    qb.setExplain(rs.getString(8));
                    qb.setQuestionImg(rs.getString(9));
                    qb.setExplainImg(rs.getString(10));
                    qb.setUserID(rs.getInt(11));
                    list.add(qb);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        query = "select * from QuestionBank left join ExamQuestion on QuestionBank.question_id = ExamQuestion.question_id and exam_id = ? WHERE ExamQuestion.question_id IS NULL and subject_id = ? and userID = ?";
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, examID);
            ps.setInt(2, subjectID);
            ps.setInt(3, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuestionBank qb = new QuestionBank();
                qb.setQuestionId(rs.getInt(1));
                qb.setSubjectId(rs.getInt(2));
                qb.setQuestionContext(rs.getString(3));
                qb.setChoice1(rs.getString(4));
                qb.setChoice2(rs.getString(5));
                qb.setChoice3(rs.getString(6));
                qb.setChoiceCorrect(rs.getString(7));
                qb.setExplain(rs.getString(8));
                qb.setQuestionImg(rs.getString(9));
                qb.setExplainImg(rs.getString(10));
                qb.setUserID(rs.getInt(11));
                list.add(qb);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //Get question by questionID
    public QuestionBank getQuestionByID(int questionID) {
        String query = "select * from QuestionBank where question_id = ?";
        QuestionBank qb = new QuestionBank();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, questionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                qb.setQuestionId(rs.getInt(1));
                qb.setSubjectId(rs.getInt(2));
                qb.setQuestionContext(rs.getString(3));
                qb.setChoice1(rs.getString(4));
                qb.setChoice2(rs.getString(5));
                qb.setChoice3(rs.getString(6));
                qb.setChoiceCorrect(rs.getString(7));
                qb.setExplain(rs.getString(8));
                qb.setQuestionImg(rs.getString(9));
                qb.setExplainImg(rs.getString(10));
                qb.setUserID(rs.getInt(11));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return qb;
    }

    public static void main(String[] args) {
//        List<QuestionBank> subjects = new ExamDAO().getRandomQuestByAmount(5, 8);

        new ExamDAO().deleteExamByExamID(9);

//        for (QuestionBank subject : subjects) {
//            System.out.println(subject.toString());
//        }
    }
}
