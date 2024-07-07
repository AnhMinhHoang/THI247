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
<title>List of Planners</title>
</head>
<body>
    <h2>List of Planners</h2>
    
    <table border="1">
        <thead>
            <tr>
                <th>Planner Name</th>
                <th>Create Time</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Tasks</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${planners}" var="planner">
                <tr>
                    <td>${planner.plannerName}</td>
                    <td>${planner.createTime}</td>
                     <td>${planner.startTime}</td>
                    <td>${planner.endTime}</td>
                    <td>
                        <a href="TaskListServlet?plannerId=${planner.plannerId}">View Tasks</a>
                    </td>
                    <td>
                        <a href="editPlannes?plannerId=${planner.plannerId}">Edit</a> |
                        <a href="deletePlanners?plannerId=${planner.plannerId}">Delete</a>
                        <a href="createPlanner.jsp">Create</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <hr>
    
   
</body>
</html>

