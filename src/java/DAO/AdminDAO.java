/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBConnection.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
}
