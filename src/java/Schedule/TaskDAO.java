package Schedule;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import java.sql.Statement;
import java.sql.Timestamp;

public class TaskDAO extends DBConnection {

  // Thêm mới một Planner vào cơ sở dữ liệu
    public boolean createTask(Task task) {
        String query = "INSERT INTO Task (userID, task_context, task_deadline) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, task.getUserID());
            pstmt.setString(2, task.getTaskContext());
            pstmt.setTimestamp(3, task.getTaskDeadline());
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        task.setTaskId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

  // Cập nhật thông tin của một Planner trong cơ sở dữ liệu
public boolean updateTask(Task task) {
    String query = "UPDATE Task SET task_context = ?, task_deadline = ? WHERE task_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, task.getTaskContext());
        pstmt.setTimestamp(2, task.getTaskDeadline());
        pstmt.setInt(3, task.getTaskId());

        int rowsUpdated = pstmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


// Xóa một Task
    public boolean deleteTask(int taskId) {
        String query = "DELETE FROM Task WHERE task_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, taskId);

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    // Lấy danh sách các Task của một người dùng
    public List<Task> getTasksByUser(int userId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT task_id, task_context, task_deadline FROM Task WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int taskId = rs.getInt("task_id");
                String taskContext = rs.getString("task_context");
                Timestamp taskDeadline = rs.getTimestamp("task_deadline"); 

                Task task = new Task(userId, taskId, taskContext, taskDeadline);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Lấy Task bằng ID
    public Task getTaskById(int taskId) {
        Task task = null;
        String query = "SELECT userID, task_context, task_deadline FROM Task WHERE task_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, taskId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("userID");
                String taskContext = rs.getString("task_context");
                Timestamp taskDeadline = rs.getTimestamp("task_deadline"); 

                task = new Task(taskId, userId, taskContext, taskDeadline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
 
}
}
