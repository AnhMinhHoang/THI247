package Schedule;

import DAO.UserDAO;
import Email.EmailSender;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Planner;
import model.Task;
import javax.mail.MessagingException;

public class PlannerEmailScheduler {
    private final PlannerDAO plannerDAO;
    private final UserDAO userDAO;
    private final Map<String, LocalDate> sentEmailsToday; // Map để lưu các email đã gửi và thời điểm gửi

    public PlannerEmailScheduler(PlannerDAO plannerDAO, UserDAO userDAO) {
        this.plannerDAO = plannerDAO;
        this.userDAO = userDAO;
        this.sentEmailsToday = new HashMap<>();
    }

    public void startScheduler() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                checkAndSendEmails();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 10, TimeUnit.SECONDS); // Kiểm tra mỗi 10 giây
    }

    private void checkAndSendEmails() throws MessagingException {
        List<Integer> userIds = userDAO.getAllUserIds();
        Date currentDate = new Date(System.currentTimeMillis());

        System.out.println("Checking and sending emails at: " + currentDate);

        // Duyệt qua từng user
        for (int userId : userIds) {
            List<Planner> planners = plannerDAO.getPlannersByUser(userId);

            // Nếu user có các planner
            if (!planners.isEmpty()) {
                String userEmail = userDAO.getEmailByUserId(userId);

                // Kiểm tra xem đã gửi email cho địa chỉ này trong ngày chưa
                if (isGmailAddress(userEmail) && !hasSentEmailToday(userEmail, currentDate.toLocalDate())) {
                    // Tạo nội dung email bằng StringBuilder
                    StringBuilder emailContent = new StringBuilder();
                    emailContent.append("Dear User,\n\n");
                    emailContent.append("Here are your scheduled planners and tasks for today:\n\n");

                    // Duyệt qua từng planner của user
                    for (Planner planner : planners) {
                        // Lấy ngày tháng năm của startTime và currentDate
                        LocalDate startTime = planner.getStartTime().toLocalDate();
                        LocalDate currentDay = currentDate.toLocalDate();

                        // So sánh ngày tháng năm bằng nhau
                        if (startTime.equals(currentDay)) {
                            emailContent.append("Planner Name: ").append(planner.getPlannerName()).append("\n");
                            emailContent.append("Start Time: ").append(planner.getStartTime()).append("\n");
                            emailContent.append("End Time: ").append(planner.getEndTime()).append("\n");

                            // Lấy danh sách tasks của planner
                            List<Task> tasks = plannerDAO.getTasksByPlanner(planner.getPlannerId());

                            // Nếu có tasks, thêm chúng vào emailContent
                            if (!tasks.isEmpty()) {
                                emailContent.append("Tasks:\n");
                                for (Task task : tasks) {
                                    emailContent.append("- Task Name: ").append(task.getTaskName()).append("\n");
                                    emailContent.append("  Task Date: ").append(task.getTaskDate()).append("\n");
                                }
                            }

                            emailContent.append("\n");
                        }
                    }

                    // Gửi email nếu có nội dung
                    if (emailContent.length() > 0) {
                        boolean emailSent = EmailSender.sendPlannerEmail(userEmail, emailContent.toString());

                        if (emailSent) {
                            sentEmailsToday.put(userEmail, currentDate.toLocalDate()); // Lưu email đã gửi và thời gian
                            System.out.println("Email successfully sent to: " + userEmail);
                        } else {
                            System.out.println("Failed to send email to: " + userEmail);
                        }
                    } else {
                        System.out.println("No planners scheduled for today for user: " + userEmail);
                    }
                } else {
                    System.out.println("Email already sent to user: " + userEmail + " for today.");
                }
            }
        }
    }

    // Hàm để kiểm tra xem địa chỉ email có phải là Gmail
    private boolean isGmailAddress(String email) {
        return email.endsWith("@gmail.com");
    }

    // Hàm để kiểm tra xem đã gửi email cho địa chỉ này trong ngày hôm nay chưa
    private boolean hasSentEmailToday(String email, LocalDate today) {
        LocalDate lastSentDate = sentEmailsToday.get(email);
        return lastSentDate != null && lastSentDate.equals(today);
    }

    // Hàm để liệt kê tất cả thông tin userId, email, planner và các task
    public List<String> listUserPlannersAndTasks() {
        List<String> userInfoList = new ArrayList<>();
        List<Integer> userIds = userDAO.getAllUserIds();

        for (int userId : userIds) {
            String userEmail = userDAO.getEmailByUserId(userId);
            List<Planner> planners = plannerDAO.getPlannersByUser(userId);

            for (Planner planner : planners) {
                StringBuilder info = new StringBuilder();
                info.append("UserId: ").append(userId)
                    .append(", Email: ").append(userEmail)
                    .append(", Planner: ").append(planner.getPlannerName())
                    .append(", Start Time: ").append(planner.getStartTime())
                    .append(", End Time: ").append(planner.getEndTime())
                    .append(", Tasks: ");

                List<Task> tasks = plannerDAO.getTasksByPlanner(planner.getPlannerId());
                for (Task task : tasks) {
                    info.append("[Task Name: ").append(task.getTaskName())
                        .append(", Task Date: ").append(task.getTaskDate()).append("] ");
                }

                userInfoList.add(info.toString());
            }
        }

        return userInfoList;
    }

    // Hàm để lấy danh sách các email đã gửi trong ngày
    public Set<String> getSentEmailsToday() {
        return new HashSet<>(sentEmailsToday.keySet());
    }

}
