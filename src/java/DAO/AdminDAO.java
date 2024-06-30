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
import model.TeacherRequest;
import model.Tests;

/**
 *
 * @author GoldCandy
 */
public class AdminDAO extends DBConnection{
    
    //add teacher request
    public void addTeacherRequest(int userID, int subjectID, int experience, String academicLevel, String school) {
        String query = "DBCC CHECKIDENT (TeacherRequest, RESEED, 0); DBCC CHECKIDENT (TeacherRequest, RESEED); insert into TeacherRequest(userID, subject_id, experience, academic_level, school) values(?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setInt(2, subjectID);
            ps.setInt(3, experience);
            ps.setString(4, academicLevel);
            ps.setString(5, school);
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    
    public TeacherRequest getRequestByUserID(int userID){
        String query = "SELECT * FROM TeacherRequest Where userID = ?";
        TeacherRequest request = new TeacherRequest();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                request.setRequestID(rs.getInt(1));
                request.setUserID(rs.getInt(2));
                request.setSubjectID(rs.getInt(3));
                request.setExperience(rs.getInt(4));
                request.setAcademicLevel(rs.getString(5));
                request.setSchool(rs.getString(6));
                return request;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<TeacherRequest> getAllRequest(){
        String query = "SELECT * FROM TeacherRequest";
        List<TeacherRequest> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TeacherRequest request = new TeacherRequest();
                request.setRequestID(rs.getInt(1));
                request.setUserID(rs.getInt(2));
                request.setSubjectID(rs.getInt(3));
                request.setExperience(rs.getInt(4));
                request.setAcademicLevel(rs.getString(5));
                request.setSchool(rs.getString(6));
                list.add(request);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void deleteRequest(int requestID){
        String query = "delete from TeacherRequest where request_id = ? DBCC CHECKIDENT (forum_comment, RESEED, 0); DBCC CHECKIDENT (forum_comment, RESEED);";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, requestID);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void banUnbanUser(int userID, boolean isBan){
        String query = "Update Users set is_banned = ? where userID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setBoolean(1, isBan);
            ps.setInt(2, userID);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        TeacherRequest requests = new AdminDAO().getRequestByUserID(2);
        if (requests == null){
            System.out.println("OK");
        }
        else{
            System.out.println("NOT");
        }
    }
}
