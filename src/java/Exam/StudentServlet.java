package Exam;

import DAO.QuestionDAO;
import model.QuestionBank;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuestionDAO questionDAO = new QuestionDAO();
        List<QuestionBank> questions = questionDAO.getAllMultipleChoiceQuestions();
        request.setAttribute("questions", questions);
        request.getRequestDispatcher("Studentquiz.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDAO questionDAO = new QuestionDAO();
        List<QuestionBank> questions = questionDAO.getAllMultipleChoiceQuestions();

        List<String> userAnswers = new ArrayList<>();
        for (QuestionBank question : questions) {
            String answer = request.getParameter("answers_" + question.getId());
            userAnswers.add(answer);
        }

        double score = questionDAO.calculateScore(userAnswers, questions);
        List<String> correctAnswers = questionDAO.getCorrectAnswers(questions);

        request.setAttribute("questions", questions);
        request.setAttribute("score", score);
        request.setAttribute("correctAnswers", correctAnswers);
        request.setAttribute("showResult", true);
        request.getRequestDispatcher("Studentquiz.jsp").forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
