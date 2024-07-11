<%-- 
    Document   : editPlanner
    Created on : 5 thg 7, 2024, 17:10:05
    Author     : sonhu
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

    <form action="editTask" method="post">
        <input type="hidden" name="taskId" value="${param.taskId}" />
        <label for="taskContext">Task Context:</label>
        <input type="text" id="taskContext" name="taskContext" value="${task.taskContext}" required /><br/>

        <label for="taskDate">Task Date (YYYY-MM-DD):</label>
        <input type="date" id="taskDate" name="taskDate" value="${task.taskDeadline.toLocalDateTime().toLocalDate()}" required /><br/>

        <label for="taskTime">Task Time (HH:MM):</label>
        <input type="time" id="taskTime" name="taskTime" value="${task.taskDeadline.toLocalDateTime().toLocalTime()}" required /><br/>

        <input type="submit" value="Update Task" />
    </form>

    <a href="TaskListServlet">Back to Task List</a>
</body>
</html>
