/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package examController;

import DAO.ExamDAO;
import DAO.ForumDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;
import model.QuestionBank;
import model.Users;

/**
 *
 * @author GoldCandy
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,       // 10MB
                 maxRequestSize = 1024 * 1024 * 50)    // 50MB
public class AddQuestionBank extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "uploads/avaUploads";

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        String context = request.getParameter("context");
        String explain = request.getParameter("explain");
        Users user = (Users)session.getAttribute("currentUser");
        String correct = request.getParameter("rightanswer");
        String A = request.getParameter("A");
        String B = request.getParameter("B");
        String C = request.getParameter("C");
        String D = request.getParameter("D");
        String url1 = "";
        String url2 = "";
        
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        
        QuestionBank qb = new QuestionBank();
        
        for(Part part: request.getParts()){
            String fileName = getFileName(part);
            if(fileName != null && !fileName.isEmpty()){
                String filePath = uploadPath + File.separator + fileName;
                part.write(filePath);
                if(url1.isBlank()) url1 = UPLOAD_DIRECTORY + "/" + fileName;
                else url2 = UPLOAD_DIRECTORY + "/" + fileName;
            }
        }
        
        if(url1.isBlank()) url1 = null;
        if(url2.isBlank()) url2 = null;
        
        switch(correct){
            case "A" -> {
                qb = getQuestion(user.getUserID(), 0, subjectID, context, B, C, D, A, explain, url1, url2);
            }
            case "B" -> {
                qb = getQuestion(user.getUserID(), 0, subjectID, context, A, C, D, B, explain, url1, url2);
            }
            case "C" -> {
                qb = getQuestion(user.getUserID(), 0, subjectID, context, A, B, D, C, explain, url1, url2);
            }
            case "D" -> {
                qb = getQuestion(user.getUserID(), 0, subjectID, context, A, B, C, D, explain, url1, url2);
            }
        }
        
        new ExamDAO().addQuestionToQuestionBank(qb);
        List<QuestionBank> list = new ExamDAO().getAllUserQuestionByID(subjectID, user.getUserID());
        session.setAttribute("questionList", list);
        response.sendRedirect("viewuserquestion.jsp");
    }
    
    private QuestionBank getQuestion(int UserID, int questionId, int subjectId, String questionContext, String choice1, String choice2, String choice3, String choiceCorrect, String explain, String QuestionImg, String explainImg){
        return new QuestionBank(UserID, questionId, subjectId, questionContext, choice1, choice2, choice3, choiceCorrect, explain, QuestionImg, explainImg);
    }
    
    private String getFileName(Part part){
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for(String token: tokens){
            if(token.trim().startsWith("filename")){
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
