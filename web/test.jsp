<%-- 
    Document   : test
    Created on : Jun 24, 2024, 7:31:25 PM
    Author     : GoldCandy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*, org.apache.poi.xwpf.usermodel.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        List<QuestionBank>qbs = (List<QuestionBank>)session.getAttribute("listtest");
        for(QuestionBank qb: qbs){
        %>
        <p>Câu hỏi: <%=qb.getQuestionContext()%></p>
        <%
        if(qb.getQuestionImg() != null){
        %>
        <img src="<%=qb.getQuestionImg()%>" width="50%" height="50%" alt="alt"/>
        <%
            }
        %>
        <%
        if(qb.getChoice1().startsWith("uploads/docreader")){
        %>
        <br><span>A. </span><img src="<%=qb.getChoice1()%>" height="30" alt="alt"/>
        <%
            }
        else{
        %>
        <p>A. <%=qb.getChoice1()%></p>
        <%
            }
        %>
        <%
        if(qb.getChoice2().startsWith("uploads/docreader")){
        %>
        <br><span>B. </span><img src="<%=qb.getChoice2()%>" height="30" alt="alt"/>
        <%
            }
        else{
        %>
        <p>B. <%=qb.getChoice2()%></p>
        <%
            }
        %>
        <%
        if(qb.getChoice3().startsWith("uploads/docreader")){
        %>
        <br><span>C. </span><img src="<%=qb.getChoice3()%>" height="30" alt="alt"/>
        <%
            }
        else{
        %>
        <p>C. <%=qb.getChoice3()%></p>
        <%
            }
        %>
        <%
        if(qb.getChoiceCorrect().startsWith("uploads/docreader")){
        %>
        <br><span>D. </span><img src="<%=qb.getChoiceCorrect()%>" height="30" alt="alt"/>
        <%
            }
        else{
        %>
        <p>D. <%=qb.getChoiceCorrect()%></p>
        <%
            }
        %>
        <p>Giải thích: <%=qb.getExplain()%></p>
        <%
        if(qb.getExplainImg() != null){
        %>
        <img src="<%=qb.getExplainImg()%>" width="50%" height="50%" alt="alt"/>
        <%
            }
        }
        %>
        <p>Okela <%=qbs.size()%></p>
    </body>
</html>
