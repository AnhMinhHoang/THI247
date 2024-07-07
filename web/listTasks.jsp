<%-- 
    Document   : listTasks
    Created on : 4 thg 7, 2024, 01:20:49
    Author     : sonhu
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Tasks</title>
</head>
<body>
    <h2>List of Tasks</h2>
    
    <table border="1">
        <thead>
            <tr>
                <th>Task Name</th>
                <th>Task Date</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tasks}" var="task">
                <tr>

                    <td>${task.taskName}</td>
                    <td>${task.taskDate}</td>
                    <td>
                        <a href="TaskEditServlet?taskId=${task.taskId}">Edit</a> |
                        <a href="TaskDeleteServlet?taskId=${task.taskId}&plannerId=${task.plannerId}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <hr>
    
   <h2>Create New Task</h2>
    <form action="TaskCreateServlet" method="post">
        <input type="hidden" name="plannerId" value="${param.plannerId}">
        Task Name: <input type="text" name="taskName" required><br>
        Task Date: <input type="date" name="taskDate" required><br>
        Task Time (HH:MM:SS): <input type="time" name="taskTime" required><br>
        <input type="submit" value="Create Task">
    </form>
</body>
</html>

