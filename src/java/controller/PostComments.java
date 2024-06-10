/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import model.Users;

/**
 *
 * @author GoldCandy
 */

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,       // 10MB
                 maxRequestSize = 1024 * 1024 * 50)    // 50MB
public class PostComments extends HttpServlet {
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
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        HttpSession session = request.getSession();
        String context = request.getParameter("comment");
        int postID = (Integer)session.getAttribute("postID");
        Users user = (Users)session.getAttribute("currentUser");
        boolean check = true;
        session.setAttribute("postID", postID);
        
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        if(!context.isBlank()){
            for(Part part: request.getParts()){
                String fileName = getFileName(part);
                if(fileName != null && !fileName.isEmpty()){
                    String filePath = uploadPath + File.separator + fileName;
                    part.write(filePath);
                    String url = UPLOAD_DIRECTORY + "/" + fileName;
                    check = false;
                    new ForumDAO().createNewComment(user.getUserID(), postID, context, url);
                    response.sendRedirect("forum-detail.jsp");
                }
            }
            if(check){
                new ForumDAO().createNewComment(user.getUserID(), postID, context, null);
                response.sendRedirect("forum-detail.jsp");
            }
        }
        else{
            response.sendRedirect("forum-detail.jsp");
        }
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
