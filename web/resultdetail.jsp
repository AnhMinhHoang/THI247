<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@ page import="model.QuestionBank" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>
<script>
        var container = document.getElementById("tagID");
        var tag = container.getElementsByClassName("tag");
        var current = container.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
        tag[2].className += " active";
    </script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
        }

        fieldset {
            margin-bottom: 20px;
            border: 2px solid #ccc;
            border-radius: 5px;
            padding: 20px;
        }

        legend {
            font-weight: bold;
            font-size: 1.2em;
            color: #333;
        }

        input[type="radio"]{
            margin-bottom: 10px;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }


    </style>
<%
if(session.getAttribute("test") != null){
Tests test = (Tests)session.getAttribute("test");
List<StudentChoice> studentChoices = new StudentExamDAO().getAllSelectedChoice(test.getTestID());
List<QuestionBank> qbs = new ExamDAO().getAllQuestionByExamID(test.getExamID());
long seed = test.getSeed();

HashMap<Integer, String> selectedChoicesMap = new HashMap<>();
for (StudentChoice studentChoice : studentChoices) {
    selectedChoicesMap.put(studentChoice.getQuestionID(), studentChoice.getSelectedChoice());
}
%>
<body>
    <div class="container">
        <br><br>
        <button class="btn btn-light"><a href="testhistory.jsp" style="text-decoration: none; color: black">Back</a></button>
        <%
        int number = -1;
            for(int i = 0; i < qbs.size(); i++){
                QuestionBank qb = qbs.get(i);
                String selectedChoice = selectedChoicesMap.get(qb.getQuestionId());
                List<Choice> choices = new ArrayList<>();
                choices.add(new Choice(qb.getChoice1()));
                choices.add(new Choice(qb.getChoice2()));
                choices.add(new Choice(qb.getChoice3()));
                choices.add(new Choice(qb.getChoiceCorrect()));
                Collections.shuffle(choices, new Random(seed + i));
                number++;
                boolean isWrong = selectedChoice == null || !selectedChoice.equals(qb.getChoiceCorrect());
        %>
        <fieldset>
            <%
            if(isWrong){
            %>
            <p style="overflow-wrap:break-word; font-weight: bold">
                <span><i class="fa fa-close" style="color:red"></i>
                </span>Câu <%=number + 1%>: <%=qb.getQuestionContext()%></p>
                <%
                    }
                else{
                %>
            <p style="overflow-wrap:break-word; font-weight: bold">
                <span><i class="fa fa-check" style="color:green"></i>
                </span>Câu <%=number + 1%>: <%=qb.getQuestionContext()%></p>
                <%
                    }
                %>
                <%
                if(qb.getQuestionImg() != null){
                %>
            <img src="<%=qb.getQuestionImg()%>" width="50%" height="50%"/>
            <%
                }
            %>
            <div id="answer">
                <%
                for (int j = 0; j < choices.size(); j++) {
                    Choice choice = choices.get(j);
                    char letter = (char)('A' + j);
                    boolean isSelected = selectedChoice != null && selectedChoice.equals(choice.getChoice());
                %>
                <%
                if(choice.getChoice().startsWith("uploads/docreader")){
                %>
                <div class="form-check">
                    <%
                    if(isSelected){
                    boolean isCorrect = isSelected && selectedChoice.equals(qb.getChoiceCorrect());
                    if(isCorrect){
                    %>
                    <input class="form-check-input" type="radio" 
                           name="answer<%=number%>" id="option<%=letter%><%=number%>" 
                           value="<%=choice.getChoice()%>" <%if(isSelected){%>checked<%}%> style="opacity: 1" disabled/>
                    <label style="color: green; opacity: 1; font-weight: bold" class="form-check-label" 
                           for="option<%=letter%><%=number%>"><%=letter%>: <img src="<%=choice.getChoice()%>" height="30" alt="alt"/></label>
                    <%
                        }
                    else if(!isCorrect){
                    %>
                    <input class="form-check-input" type="radio" 
                           name="answer<%=number%>" id="option<%=letter%><%=number%>" 
                           value="<%=choice.getChoice()%>" <%if(isSelected){%>checked<%}%> style="opacity: 1" disabled/>
                    <label style="color: red; opacity: 1; font-weight: bold" class="form-check-label" 
                           for="option<%=letter%><%=number%>"><%=letter%>: <img src="<%=choice.getChoice()%>" height="30" alt="alt"/></label>
                    <%
                        }
                    }
                    else{
                        if(choice.getChoice().equals(qb.getChoiceCorrect())){
                    %>
                    <input class="form-check-input" type="radio" 
                           name="answer<%=number%>" id="option<%=letter%><%=number%>" 
                           value="<%=choice.getChoice()%>" style="opacity: 1" disabled/>
                    <label style="opacity: 1; color: green; font-weight: bold" class="form-check-label" 
                           for="option<%=letter%><%=number%>"><%=letter%>: <img src="<%=choice.getChoice()%>" height="30" alt="alt"/></label>
                    <%
                        }
                        else{
                    %>
                    <input class="form-check-input" type="radio" 
                           name="answer<%=number%>" id="option<%=letter%><%=number%>" 
                           value="<%=choice.getChoice()%>" style="opacity: 1" disabled/>
                    <label style="opacity: 1;" class="form-check-label" 
                           for="option<%=letter%><%=number%>"><%=letter%>: <img src="<%=choice.getChoice()%>" height="30" alt="alt"/></label>
                    <%
                        }
                    %>
                    <%
                        }
                    %>
                </div>
                <br>
                <%
                    }
                    else{
                %>
                <div class="form-check">
                    <%
                    if(isSelected){
                    boolean isCorrect = isSelected && selectedChoice.equals(qb.getChoiceCorrect());
                    if(isCorrect){
                    %>
                    <input class="form-check-input" type="radio" 
                           name="answer<%=number%>" id="option<%=letter%><%=number%>" 
                           value="<%=choice.getChoice()%>" <%if(isSelected){%>checked<%}%> style="opacity: 1" disabled/>
                    <label style="color: green; opacity: 1; font-weight: bold" class="form-check-label" for="option<%=letter%><%=number%>"><%=letter%>: <%=choice.getChoice()%></label>
                    <%
                        }
                    else if(!isCorrect){
                    %>
                    <input class="form-check-input" type="radio" 
                           name="answer<%=number%>" id="option<%=letter%><%=number%>" 
                           value="<%=choice.getChoice()%>" <%if(isSelected){%>checked<%}%> style="opacity: 1; color: red;" disabled/>
                    <label style="color: red; opacity: 1; font-weight: bold" class="form-check-label" for="option<%=letter%><%=number%>"><%=letter%>: <%=choice.getChoice()%></label>
                    <%
                        }
                    }
                    else{
                        if(choice.getChoice().equals(qb.getChoiceCorrect())){
                    %>
                    <input class="form-check-input" type="radio" 
                           name="answer<%=number%>" id="option<%=letter%><%=number%>" 
                           value="<%=choice.getChoice()%>" style="opacity: 1" disabled/>
                    <label style="opacity: 1; color: green; font-weight: bold" class="form-check-label" 
                           for="option<%=letter%><%=number%>"><%=letter%>: <%=choice.getChoice()%></label>
                    <%
                        }
                        else{
                    %>
                    <input class="form-check-input" type="radio" 
                           name="answer<%=number%>" id="option<%=letter%><%=number%>" 
                           value="<%=choice.getChoice()%>" style="opacity: 1" disabled/>
                    <label style="opacity: 1;" class="form-check-label" 
                           for="option<%=letter%><%=number%>"><%=letter%>: <%=choice.getChoice()%></label>
                    <%
                        }
                    %>
                    <%
                        }
                    %>
                </div>
                <br>
                <%
                    }
                }
                %>
            </div>
            <p style="overflow-wrap:break-word;"><label style="font-weight: bold">Giải thích:</label> <%=qb.getExplain()%></p>
            <%
            if(qb.getExplainImg() != null){
            %>
            <img src="<%=qb.getExplainImg()%>" width="50%" height="50%"/>
            <%
                }
            %>
        </fieldset>
        <%
            }
        %>
    </div>
    <%
        }
    %>
    <jsp:include page="footer.jsp"></jsp:include>
    
    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>