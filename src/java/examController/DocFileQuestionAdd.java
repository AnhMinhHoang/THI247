/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package examController;

import DAO.ExamDAO;
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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.QuestionBank;
import model.Users;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author GoldCandy
 */
@MultipartConfig
public class DocFileQuestionAdd extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.InterruptedException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("currentUser");
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        Part filepart = request.getPart("docFile");
        InputStream fileContent = filepart.getInputStream();
        List<QuestionBank> qbs = new ArrayList<>();

        try (XWPFDocument document = new XWPFDocument(fileContent)) {
            Map<Integer, XWPFPictureData> imageMap = new HashMap<>();

            int paragraphIndex = 0;
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                extractImagesFromRuns(paragraph.getRuns(), imageMap, paragraphIndex);
                paragraphIndex++;
            }
            System.out.println(paragraphIndex);

            StringBuilder currentQuestion = new StringBuilder();
            StringBuilder choice1 = new StringBuilder();
            StringBuilder choice2 = new StringBuilder();
            StringBuilder choice3 = new StringBuilder();
            StringBuilder choice4 = new StringBuilder();
            StringBuilder correctAnswer = new StringBuilder();
            StringBuilder explanation = new StringBuilder();
            String questionImage = null;
            String explainImage = null;
            boolean isExplain = false;
            paragraphIndex = 0;

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String text = paragraph.getText().trim();
                text = superTrim(text);
                if (text.startsWith("Câu")) {
                    if (currentQuestion.length() > 0 || questionImage != null) {
                        StringBuilder temp;
                        switch (correctAnswer.toString()) {
                            case "A" -> {
                                temp = choice1;
                                choice1 = choice4;
                                choice4 = temp;
                            }
                            case "B" -> {
                                temp = choice2;
                                choice2 = choice4;
                                choice4 = temp;
                            }
                            case "C" -> {
                                temp = choice3;
                                choice3 = choice4;
                                choice4 = temp;
                            }
                            default -> {
                            }
                        }
                        QuestionBank qb = new QuestionBank();
                        qb.setUserID(user.getUserID());
                        qb.setSubjectId(subjectID);
                        qb.setQuestionContext(currentQuestion.toString());
                        qb.setChoice1(choice1.toString());
                        qb.setChoice2(choice2.toString());
                        qb.setChoice3(choice3.toString());
                        qb.setChoiceCorrect(choice4.toString());
                        qb.setExplain(explanation.toString());
                        qb.setQuestionImg(questionImage);
                        qb.setExplainImg(explainImage);
//                        qbs.add(qb);
                        new ExamDAO().addQuestionToQuestionBank(qb);
                    }
                    currentQuestion.setLength(0);
                    currentQuestion.append(text.substring(6).trim());
                    choice1 = new StringBuilder();
                    choice2 = new StringBuilder();
                    choice3 = new StringBuilder();
                    choice4 = new StringBuilder();
                    correctAnswer = new StringBuilder();
                    explanation = new StringBuilder();
                    questionImage = null;
                    explainImage = null;
                    isExplain = false;

                } else if (text.startsWith("A.")) {
                    if (imageMap.containsKey(paragraphIndex)) {
                        XWPFPictureData picture = imageMap.get(paragraphIndex);
                        String imagePath = savePicture(picture);
                        Thread.sleep(50);
                        System.out.println("A. "+paragraphIndex);
                        choice1.append(imagePath);
                    } else {
                        choice1.append(text.substring(2).trim());
                    }
                } else if (text.startsWith("B.")) {
                    if (imageMap.containsKey(paragraphIndex)) {
                        XWPFPictureData picture = imageMap.get(paragraphIndex);
                        String imagePath = savePicture(picture);
                        Thread.sleep(50);
                        System.out.println("B. "+paragraphIndex);
                        choice2.append(imagePath);
                    } else {
                        choice2.append(text.substring(2).trim());
                    }
                } else if (text.startsWith("C.")) {
                    if (imageMap.containsKey(paragraphIndex)) {
                        XWPFPictureData picture = imageMap.get(paragraphIndex);
                        String imagePath = savePicture(picture);
                        Thread.sleep(50);
                        System.out.println("C. "+paragraphIndex);
                        choice3.append(imagePath);
                    } else {
                        choice3.append(text.substring(2).trim());
                    }
                } else if (text.startsWith("D.")) {
                    if (imageMap.containsKey(paragraphIndex)) {
                        XWPFPictureData picture = imageMap.get(paragraphIndex);
                        String imagePath = savePicture(picture);
                        Thread.sleep(50);
                        System.out.println("D. "+paragraphIndex);
                        choice4.append(imagePath);
                    } else {
                        choice4.append(text.substring(2).trim());
                    }
                } else if (text.startsWith("Đáp án:")) {
                    correctAnswer.append(text.substring(7).trim());
                } else if (text.startsWith("Giải thích:")) {
                    explanation.append(text.substring(11).trim());
                    isExplain = true;
                } else if (text.isEmpty()) {
                    if (imageMap.containsKey(paragraphIndex)) {
                        XWPFPictureData picture = imageMap.get(paragraphIndex);
                        String imagePath = savePicture(picture);
                        Thread.sleep(50);
                        if (!isExplain && questionImage == null) {
                            questionImage = imagePath;
                        } else if (isExplain && explainImage == null) {
                            explainImage = imagePath;
                        }
                    }
                }
                paragraphIndex++;
            }
            if (currentQuestion.length() > 0 || questionImage != null) {
                StringBuilder temp;
                switch (correctAnswer.toString()) {
                    case "A" -> {
                        temp = choice1;
                        choice1 = choice4;
                        choice4 = temp;
                    }
                    case "B" -> {
                        temp = choice2;
                        choice2 = choice4;
                        choice4 = temp;
                    }
                    case "C" -> {
                        temp = choice3;
                        choice3 = choice4;
                        choice4 = temp;
                    }
                    default -> {
                    }
                }
                QuestionBank qb = new QuestionBank();
                qb.setUserID(user.getUserID());
                qb.setSubjectId(subjectID);
                qb.setQuestionContext(currentQuestion.toString());
                qb.setChoice1(choice1.toString());
                qb.setChoice2(choice2.toString());
                qb.setChoice3(choice3.toString());
                qb.setChoiceCorrect(choice4.toString());
                qb.setExplain(explanation.toString());
                qb.setQuestionImg(questionImage);
                qb.setExplainImg(explainImage);
//                qbs.add(qb);
                new ExamDAO().addQuestionToQuestionBank(qb);
            }
        }
        session.setAttribute("subjectID", subjectID);
        session.setAttribute("listtest", qbs);
        response.sendRedirect("viewuserquestion.jsp");
    }

    private String superTrim(String string) {
        string = string.replaceAll("(^\\h*)|(\\h*$)", "");
        return string;
    }

    private void extractImagesFromRuns(List<XWPFRun> runs, Map<Integer, XWPFPictureData> imageMap, int paragraphIndex) {
        for (XWPFRun run : runs) {
            for (XWPFPicture picture : run.getEmbeddedPictures()) {
                imageMap.put(paragraphIndex, picture.getPictureData());
            }
        }
    }

    private String savePicture(XWPFPictureData picture) throws IOException {
        String imageFileName = System.currentTimeMillis() + "." + picture.suggestFileExtension();
        String directoryPath = getServletContext().getRealPath("") + File.separator + "uploads/docreader";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String imagePath = directoryPath + File.separator + imageFileName;
        String url = "uploads/docreader" + "/" + imageFileName;

        File imageFile = new File(imagePath);
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            fos.write(picture.getData());
        }

        return url;
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
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(DocFileQuestionAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(DocFileQuestionAdd.class.getName()).log(Level.SEVERE, null, ex);
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
