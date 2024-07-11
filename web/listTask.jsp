<%-- 
    Document   : Timetable
    Created on : 3 thg 7, 2024, 23:27:26
    Author     : sonhu
--%>
<%-- 
    Document   : listPlanners
    Created on : 4 thg 7, 2024, 12:00:00
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
                <th>Tên nhiệm vụ</th>
                <th>Thời gian thực hiện</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tasks}" var="task">
                <tr>
                    <td>${task.taskContext}</td>
                    <td>${task.taskDeadline}</td> 
                    <td>
                        <a href="editTask?taskId=${task.taskId}">Edit</a> |
                        <a href="deleteTask?taskId=${task.taskId}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <hr>
    <a href="createTask.jsp">Create New Task</a> <!-- Di chuyển liên kết tạo mới ra ngoài bảng -->
</body>
</html>
