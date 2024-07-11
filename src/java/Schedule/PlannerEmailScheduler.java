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
import model.Task;
import javax.mail.MessagingException;

public class PlannerEmailScheduler {
    private final TaskDAO taskDAO;
    private final UserDAO userDAO;
    private final Map<String, LocalDate> sentEmailsToday; // Map để lưu các email đã gửi và thời điểm gửi

    public PlannerEmailScheduler(TaskDAO taskDAO, UserDAO userDAO) {
        this.taskDAO = taskDAO;
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
            List<Task> tasks = taskDAO.getTasksByUser(userId);

            // Nếu user có các task
            if (!tasks.isEmpty()) {
                String userEmail = userDAO.getEmailByUserId(userId);
                String userName = userDAO.getUserNameByUserId(userId);

                // Kiểm tra xem đã gửi email cho địa chỉ này trong ngày chưa
                if (isGmailAddress(userEmail) && !hasSentEmailToday(userEmail, currentDate.toLocalDate())) {
                    // Tạo nội dung email bằng StringBuilder
                    StringBuilder emailContent = new StringBuilder();
                     emailContent.append("Gửi ").append(userName).append(",\n\n");
                    emailContent.append("Đây là kế hoạch của bạn hôm nay:\n\n");

                    // Duyệt qua từng task của user
                    for (Task task : tasks) {
                        // Lấy ngày tháng năm của deadline và currentDate
                        LocalDate deadline = task.getTaskDeadline().toLocalDateTime().toLocalDate();
                        LocalDate currentDay = currentDate.toLocalDate();

                        // So sánh ngày tháng năm bằng nhau
                        if (deadline.equals(currentDay)) {
                            emailContent.append("Nhiệm vụ: ").append(task.getTaskContext()).append("\n");
                            emailContent.append("Thời gian thực hiện: ").append(task.getTaskDeadline()).append("\n");
                            emailContent.append("---------------------------\n");
                        }
                    }

                    // Gửi email nếu có nội dung
                    if (emailContent.length() > 0) {
                        boolean emailSent = EmailSender.sendPlannerEmail(userEmail, emailContent.toString());

                        if (emailSent) {
                            sentEmailsToday.put(userEmail, currentDate.toLocalDate()); // Lưu email đã gửi và thời gian
                            System.out.println("Gui email thanh cong toi: " + userEmail);
                        } else {
                            System.out.println("Gui email that bai toi: " + userEmail);
                        }
                    } else {
                        System.out.println("Khong co ke hoach nao hom nay cua: " + userEmail);
                    }
                } else {
                    System.out.println("Email da duoc gui cho: " + userEmail + " hom nay.");
                }
            } else {
                System.out.println("No tasks scheduled for today for user: " + userDAO.getEmailByUserId(userId));
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

    // Hàm để liệt kê tất cả thông tin userId, email và các task
    public List<String> listUserTasks() {
        List<String> userInfoList = new ArrayList<>();
        List<Integer> userIds = userDAO.getAllUserIds();

        for (int userId : userIds) {
            String userEmail = userDAO.getEmailByUserId(userId);
            List<Task> tasks = taskDAO.getTasksByUser(userId);

            for (Task task : tasks) {
                StringBuilder info = new StringBuilder();
                info.append("UserId: ").append(userId)
                    .append(", Email: ").append(userEmail)
                    .append(", Task: ").append(task.getTaskContext())
                    .append(", Task Deadline: ").append(task.getTaskDeadline()).append("\n");

                userInfoList.add(info.toString());
            }
        }

        return userInfoList;
    }

    // Hàm để lấy danh sách các email đã gửi trong ngày
    public Set<String> getSentEmailsToday() {
        return new HashSet<>(sentEmailsToday.keySet());
    }

    public static void main(String[] args) {
        TaskDAO taskDAO = new TaskDAO();
        UserDAO userDAO = new UserDAO();
        PlannerEmailScheduler scheduler = new PlannerEmailScheduler(taskDAO, userDAO);

        scheduler.startScheduler();

        // Optional: In ra danh sách user và tasks
        List<String> userTasks = scheduler.listUserTasks();
        for (String info : userTasks) {
            System.out.println(info);
        }

        // Optional: In ra danh sách các email đã gửi trong ngày
        Set<String> sentEmails = scheduler.getSentEmailsToday();
        System.out.println("Emails sent today: " + sentEmails);
    }
}
