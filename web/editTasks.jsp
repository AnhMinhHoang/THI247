<%-- 
    Document   : editTasks
    Created on : 4 thg 7, 2024, 12:00:00
    Author     : sonhu
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Task</title>
</head>
<body>
    <h2>Edit Task</h2>
    
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
   <form action="TaskEditServlet" method="post">
    <input type="hidden" name="taskId" value="${param.taskId}" />
        
        <label for="taskName">Task Name:</label><br>
        <input type="text" id="taskName" name="taskName" value="${task.taskName}" required><br><br>
        
        <label for="taskDate">Task Date:</label><br>
        <input type="date" id="taskDate" name="taskDate" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${task.taskDate}' />" required><br><br>
        
        <label for="taskTime">Task Time:</label><br>
        <input type="time" id="taskTime" name="taskTime" value="<fmt:formatDate pattern='HH:mm' value='${task.taskDate}' />" required><br><br>
        
        <input type="submit" value="Save">
    </form>
</body>
</html>


