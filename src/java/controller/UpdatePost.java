/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ForumDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;

/**
 *
 * @author GoldCandy
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,       // 10MB
                 maxRequestSize = 1024 * 1024 * 50)    // 50MB
public class UpdatePost extends HttpServlet {
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
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        String postTitle = request.getParameter("title");
        String postContext = request.getParameter("context");
        int userID = Integer.parseInt(request.getParameter("userID"));
        int postID = Integer.parseInt(request.getParameter("postID"));
        String imgURL = request.getParameter("postIMG");
        if(imgURL.isBlank() || imgURL.equals("null")) imgURL = null;
        HttpSession session = request.getSession();
        session.setAttribute("userID", userID);
        boolean check = true;
        
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        
        for(Part part: request.getParts()){
            String fileName = getFileName(part);
            if(fileName != null && !fileName.isEmpty()){
                String filePath = uploadPath + File.separator + fileName;
                part.write(filePath);
                String url;
                url = UPLOAD_DIRECTORY + "/" + fileName;
                check = false;
                new ForumDAO().updatePostByID(postID, postTitle, postContext, url);
                response.sendRedirect("view-all-post-user.jsp");
            }
        }
        if(check){
            new ForumDAO().updatePostByID(postID, postTitle, postContext, imgURL);
            response.sendRedirect("view-all-post-user.jsp");
        }
        
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
