package Schedule;

import DAO.UserDAO;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import model.Task;

public class Main {
    public static void main(String[] args) {
        TaskDAO taskDAO = new TaskDAO();
        UserDAO userDAO = new UserDAO();
        Date now = new Date(System.currentTimeMillis());
        Date currentDate = new Date(now.getTime());
        PlannerEmailScheduler emailScheduler = new PlannerEmailScheduler(taskDAO, userDAO);

        // Hiển thị thông tin ngày hiện tại
        System.out.println("Current Date: " + currentDate);

        // Lấy và hiển thị thông tin về người dùng và tasks
        List<String> userInfoList = emailScheduler.listUserTasks();
        for (String userInfo : userInfoList) {
            System.out.println(userInfo);
        }

        // Bắt đầu lập lịch và thông báo
        System.out.println("Scheduler started.");

        emailScheduler.startScheduler();
        try {
            System.out.println("Waiting for scheduler to send emails...");
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hiển thị các email đã gửi
        Set<String> sentEmails = emailScheduler.getSentEmailsToday();
        if (sentEmails.isEmpty()) {
            System.out.println("No emails sent during this run.");
        } else {
            System.out.println("Sent emails:");
            for (String emailInfo : sentEmails) {
                System.out.println(emailInfo);
            }
        }

        // Kết thúc chương trình
        System.out.println("Program execution completed.");
    }
}
