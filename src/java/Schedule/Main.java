package Schedule;

import DAO.UserDAO;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import model.Planner;

public class Main {
    public static void main(String[] args) {
        PlannerDAO plannerDAO = new PlannerDAO();
        UserDAO userDAO = new UserDAO();
        Date now = new Date(System.currentTimeMillis());
        Date currentDate = new Date(now.getTime());
        PlannerEmailScheduler emailScheduler = new PlannerEmailScheduler(plannerDAO, userDAO);

        // Hiển thị thông tin ngày hiện tại
        System.out.println("Current Date: " + currentDate);

        // Lấy và hiển thị thông tin về người dùng, planner và tasks
        List<String> userInfoList = emailScheduler.listUserPlannersAndTasks();
        for (String userInfo : userInfoList) {
            System.out.println(userInfo);
        }

        // Bắt đầu lập lịch và thông báo
        System.out.println("Scheduler started.");

        emailScheduler.startScheduler();

        // Chờ 12 giây để kiểm tra
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
