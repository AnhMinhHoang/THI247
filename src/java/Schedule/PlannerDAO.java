package Schedule;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Planner;
import model.Task;
import java.sql.Statement;
import java.sql.Date;

public class PlannerDAO extends DBConnection {

  // Thêm mới một Planner vào cơ sở dữ liệu
    public boolean createPlanner(Planner planner) {
        String query = "INSERT INTO Planner (userID, planner_name, create_time, start_time, end_time) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, planner.getUserId());
            pstmt.setString(2, planner.getPlannerName());
            pstmt.setDate(3, planner.getCreateTime());
            pstmt.setDate(4, planner.getStartTime());
            pstmt.setDate(5, planner.getEndTime());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        planner.setPlannerId(generatedKeys.getInt(1));
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
public boolean updatePlanner(Planner planner) {
    String query = "UPDATE Planner SET planner_name = ?, end_time = ? WHERE planner_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, planner.getPlannerName());
        pstmt.setDate(2, planner.getEndTime());
        pstmt.setInt(3, planner.getPlannerId());

        int rowsUpdated = pstmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


// Sửa đổi phương thức deletePlanner
public boolean deletePlanner(int plannerId) {
    // Xóa tất cả các Task của Planner trước
    boolean tasksDeleted = deleteTasksByPlanner(plannerId);
    
    // Bạn có thể thêm điều kiện để kiểm tra xem có task nào bị xóa hay không
    // Nếu không có task nào bị xóa (tasksDeleted == false) thì tiếp tục xóa Planner

    String query = "DELETE FROM Planner WHERE planner_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, plannerId);

        int rowsDeleted = pstmt.executeUpdate();
        return rowsDeleted > 0; // Trả về true nếu có ít nhất một dòng bị xóa thành công
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}



    // Lấy danh sách các Planner của một người dùng-dùng để hiện list
    public List<Planner> getPlannersByUser(int userId) {
        List<Planner> planners = new ArrayList<>();
        String query = "SELECT planner_id, planner_name, create_time, start_time, end_time FROM Planner WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int plannerId = rs.getInt("planner_id");
                String plannerName = rs.getString("planner_name");
                Date createTime = rs.getDate("create_time");
                Date startTime = rs.getDate("start_time");
                Date endTime = rs.getDate("end_time");

                Planner planner = new Planner(plannerId, userId, plannerName, createTime,startTime, endTime);
                planners.add(planner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planners;
    }
     // Phương thức lấy Planner bằng ID-dùng để editPlanner
    public Planner getPlannerById(int plannerId) {
        Planner planner = null;
        String query = "SELECT userID, create_time, start_time, end_time, planner_name FROM Planner WHERE planner_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, plannerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("userID");
                String plannerName = rs.getString("planner_name");
                Date createTime = rs.getDate("create_time");
                Date startTime = rs.getDate("start_time");
                Date endTime = rs.getDate("end_time");
                

                planner = new Planner(plannerId, userId, plannerName, createTime,startTime, endTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planner;
    }
    
    
// Phương thức tạo mới một Task
    public boolean createTask(Task task) {
        String query = "INSERT INTO Task (planner_id, task_name, task_date) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, task.getPlannerId());
            pstmt.setString(2, task.getTaskName());
            pstmt.setTimestamp(3, task.getTaskDate());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
// Phương thức để xóa tất cả các Task của một Planner(để khi xóa planner sẽ xóa tất cả các task có trong đó)
public boolean deleteTasksByPlanner(int plannerId) {
    String query = "DELETE FROM Task WHERE planner_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, plannerId);

        int rowsDeleted = pstmt.executeUpdate();
        return rowsDeleted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    // Phương thức đọc danh sách các Task của một Planner
public List<Task> getTasksByPlanner(int plannerId ) {
    List<Task> tasks = new ArrayList<>();
    String query = "SELECT task_id, task_name, task_date FROM Task WHERE planner_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, plannerId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int taskId = rs.getInt("task_id"); // Điều chỉnh tên cột để phù hợp với cơ sở dữ liệu thực tế
            String taskName = rs.getString("task_name");
            Timestamp taskDate = rs.getTimestamp("task_date");

            Task task = new Task(taskId, taskName, plannerId, taskDate);
            tasks.add(task);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return tasks;
}


    // Phương thức cập nhật một Task
    public boolean updateTask(Task task) {
    String query = "UPDATE Task SET task_name = ?, task_date = ? WHERE task_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, task.getTaskName());
        pstmt.setTimestamp(2, task.getTaskDate());
        pstmt.setInt(3, task.getTaskId());

        int rowsUpdated = pstmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
        return false;
    }
}
    // Phương thức xóa một Task từ cơ sở dữ liệu dựa trên task_id
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
   public Task getTaskById(int taskId) {
        Task task = null;
        String query = "SELECT * FROM Task WHERE task_id = ?";

       try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, taskId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int plannerId = rs.getInt("planner_id");
                    String taskName = rs.getString("task_name");
                    Timestamp taskDate = rs.getTimestamp("task_date");
                    task = new Task(taskId, plannerId, taskName, taskDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

 
   
}
