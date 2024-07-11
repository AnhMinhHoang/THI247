package Email;

import OTP.OTP;
import DAO.UserDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Users;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import java.sql.Timestamp;

@WebServlet(urlPatterns = {"/LoginGoogleHandler"})
public class LoginGoogleHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        UserGoogleDto user = getUserInfo(accessToken);
        UserDAO userDAO = new UserDAO();
        Users userFound = userDAO.findByEmail(user.getEmail());
        HttpSession session = request.getSession();

        if (userFound == null) {
            // Người dùng chưa tồn tại, thêm vào cơ sở dữ liệu và gửi OTP để xác minh
            boolean inserted = userDAO.registerUser(user.getGiven_name(), "", user.getEmail(), false);
            if (inserted) {
                userFound = userDAO.findByEmail(user.getEmail());
                sendOtp(userFound, session, response);
            } else {
                response.sendRedirect("404.jsp");
            }
        } else {
            // Người dùng đã tồn tại, kiểm tra trạng thái otp_verified
            Users verifiedUser = userDAO.verifiedByEmail(user.getEmail());
            if (verifiedUser != null && verifiedUser.isOtp_verified()) {
                // Đặt các thuộc tính trong phiên và chuyển hướng đến trang chủ
                session.setAttribute("currentUser", userFound);
                if(userFound.isBan()) response.sendRedirect("banned.jsp");
                else response.sendRedirect("Home");
            } else {
                // Người dùng tồn tại nhưng chưa xác minh OTP, kiểm tra xem OTP có hết hạn không
                Timestamp expiryTime = OTP.getOtpExpiryTime(userFound.getUserID());
                if (OTP.isOtpExpired(expiryTime)) {
                    // OTP đã hết hạn, gửi lại OTP mới
                    sendOtp(userFound, session, response);
                } else {
                    // OTP chưa hết hạn, chuyển hướng đến trang xác minh OTP
                    session.setAttribute("email", user.getEmail());
                    response.sendRedirect("otp_verification.jsp");
                }
            }
        }
    }

    private void sendOtp(Users user, HttpSession session, HttpServletResponse response) throws IOException {
        String otp = OTP.generateOTP();
        Timestamp expiryTime = new Timestamp(System.currentTimeMillis() + (5 * 60 * 1000));
        OTP.saveOtpToDatabase(user.getUserID(), otp, expiryTime, false);
        EmailSender.sendOtpToEmail(user.getEmail(), otp);
        session.setAttribute("email", user.getEmail());
        response.sendRedirect("otp_verification.jsp");
    }


    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);

        return googlePojo;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the +
    // sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}