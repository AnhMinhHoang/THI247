/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sonhu
 */
import java.util.List;

public class MultipleChoiceQuestion {
    private long id;
    private String questionText;
    private List<String> choices;
    private String correctAnswer;
    private String explain;
    // Constructor

    public MultipleChoiceQuestion(long id, String questionText, List<String> choices, String correctAnswer, String explain) {
        this.id = id;
        this.questionText = questionText;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.explain = explain;
    }
  

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestion{" + "id=" + id + ", questionText=" + questionText + ", choices=" + choices + ", correctAnswer=" + correctAnswer + ", explain=" + explain + '}';
    }

    
}
