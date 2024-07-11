<%-- 
    Document   : createTask
    Created on : 5 thg 7, 2024, 16:59:05
    Author     : sonhu
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Task</title>
</head>
<body>
    <h2>Create New Task</h2>

    <c:if test="${not empty successMessage}">
        <p style="color: green;">${successMessage}</p>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <form action="createTask" method="post">
        <label for="taskContext">Task Context:</label>
        <input type="text" id="taskContext" name="taskContext" required /><br/>

        <label for="taskDate">Task Date (YYYY-MM-DD):</label>
        <input type="date" id="taskDate" name="taskDate" required /><br/>

        <label for="taskTime">Task Time (HH:MM):</label>
        <input type="time" id="taskTime" name="taskTime" required /><br/>

        <input type="submit" value="Create Task" />
    </form>

    <a href="TaskListServlet">Back to Task List</a>
</body>
</html>
