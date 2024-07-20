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

        #navigation ul {
            list-style-type: none;
            padding: 0;
        }

        #navigation li {
            margin-bottom: 5px;
        }

        #navigation a {
            text-decoration: none;
            color: #4CAF50;
        }

        #navigation a:hover {
            text-decoration: underline;
        }


    </style>
<%
if(session.getAttribute("examID") != null){
int examID = (Integer)session.getAttribute("examID");
Users user = (Users)session.getAttribute("currentUser");
Tests test = (Tests)session.getAttribute("test");
List<StudentChoice> studentChoices = new StudentExamDAO().getAllSelectedChoice(test.getTestID());
Exam currentExam = new ExamDAO().getExamByID(examID);
List<QuestionBank> qbs = new ExamDAO().getAllQuestionByExamID(examID);
long seed = (Long)session.getAttribute("seed");

HashMap<Integer, String> selectedChoicesMap = new HashMap<>();
for (StudentChoice studentChoice : studentChoices) {
    selectedChoicesMap.put(studentChoice.getQuestionID(), studentChoice.getSelectedChoice());
}
%>
<body>
    <style>
        #navigation {
            display: flex;
            flex-wrap: wrap;
            justify-content: center; /* Adjust as needed for alignment */
            list-style-type: none;
            padding: 0;
        }
        #navigation li {
            margin: 5px; /* Adjust margin between items */
        }
        #navigation a {
            text-decoration: none;
            color: #4CAF50;
            padding: 5px 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            display: inline-block;
        }
        #navigation a:hover {
            background-color: #f0f0f0;
        }
    </style>
    <br><br>
    <div class="text-primary" style="top: 100px; text-align: center; font-size: 30px">
        <span id="timer">00:00</span>
    </div>
    <div class="container">
        <br><br>
        <form id="examForm" action="SubmitTest" method="post">
            <input type="hidden" name="numberOfQuestion" value="<%=qbs.size()%>"/>
            <input type="hidden" name="testID" value="<%=test.getTestID()%>"/>
            <input type="hidden" name="examID" value="<%=examID%>"/>
            <input id="timeLeft" type="hidden" name="timeLeft" value="<%=test.getTimeLeft()%>"/>
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
            %>
            <fieldset id="question<%=number%>">
                <p style="overflow-wrap:break-word; font-weight: bold">Câu <%=number + 1%>: <%=qb.getQuestionContext()%></p>
                <input type="hidden" name="question<%=number%>" id="question<%=number%>" value="<%=qb.getQuestionId()%>"/>
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
                        <input class="form-check-input" onclick="autoSave()" type="radio" name="answer<%=number%>" id="option<%=letter%><%=number%>" value="<%=choice.getChoice()%>" <%if(isSelected){%>checked<%}%>/>
                        <label class="form-check-label" for="option<%=letter%><%=number%>"><%=letter%>: <img src="<%=choice.getChoice()%>" height="30" alt="alt"/></label>
                    </div>
                    <br>
                    <%
                        }
                    else{
                    %>
                    <div class="form-check">
                        <input class="form-check-input" onclick="autoSave()" type="radio" name="answer<%=number%>" id="option<%=letter%><%=number%>" value="<%=choice.getChoice()%>" <%if(isSelected){%>checked<%}%>/>
                        <label class="form-check-label" for="option<%=letter%><%=number%>"><%=letter%>: <%=choice.getChoice()%></label>
                    </div>
                    <%
                        }
                    }
                    %>
                </div>
            </fieldset>
            <br>
            <%
                }
            %>
            <input type="submit" style="background-color: #06BBCC" value="Nộp bài"/>
        </form>
    </div>


    <script>
        var countdownTime = <%=test.getTimeLeft()%>; // Assuming currentExam.getDuration() returns minutes
        let timeLeft = document.getElementById("timeLeft");
        updateTimer();

        function updateTimer() {
            var minutes = Math.floor(countdownTime / 60);
            var seconds = countdownTime % 60;

            document.getElementById('timer').innerHTML = ('0' + minutes).slice(-2) + ':' + ('0' + seconds).slice(-2);

            countdownTime--;
            timeLeft.value = countdownTime;

            if (countdownTime < 0) {
                clearInterval(timerInterval); // Stop the countdown
                timeLeft.value = 0;
                document.getElementById('examForm').submit();
            }
        }

// Call updateTimer every second (1000 milliseconds)
        var timerInterval = setInterval(updateTimer, 1000);
    </script>

    <script>
        //prevent cheat by close tab or navigate to another page
        window.onbeforeunload = function (event) {
            var formData = new FormData(document.getElementById('examForm'));
            var params = new URLSearchParams(formData);

            var url = 'AutoSaveServlet';
            navigator.sendBeacon(url, params); //beacon API when closeing tab

        };

        function autoSave() {
            // Serialize form data
            var formData = new FormData(document.getElementById('examForm'));

            // Send AJAX request to servlet endpoint
            fetch('AutoSaveServlet', {
                method: 'POST',
                body: formData
            })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        console.log('autosave successful!');
                    })
                    .catch(error => {
                        console.error('Error during autosave:', error);
                    });
        }

// Call autoSave function every 5 seconds
        setInterval(autoSave, 5000); // 5000 milliseconds = 5 seconds
    </script>
    <%
            }
    %>
    <jsp:include page="footer.jsp"></jsp:include>

    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>