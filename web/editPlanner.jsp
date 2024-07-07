<%-- 
    Document   : editPlanner
    Created on : 5 thg 7, 2024, 16:59:05
    Author     : sonhu
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Planner</title>
</head>
<body>
    <h1>Edit Planner</h1>
    <c:if test="${not empty planner}">
        <form action="editPlannes" method="post">
            <input type="hidden" name="plannerId" value="${planner.plannerId}">
            <label for="plannerName">Planner Name:</label>
            <input type="text" id="plannerName" name="plannerName" value="${planner.plannerName}" required><br><br>
            <label for="endTime">Start Time:</label>
            <input type="date" id="startTime" name="startTime" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${planner.startTime}' />" required><br><br>
            <label for="endTime">End Time:</label>
            <input type="date" id="endTime" name="endTime" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${planner.endTime}' />" required><br><br>
            <button type="submit">Update Planner</button>
        </form> 
    </c:if>
    <c:if test="${empty planner}">
        <p>Planner not found or invalid planner ID.</p>
    </c:if>
</body>
</html>
