<%-- 
    Document   : createPlanner
    Created on : 4 thg 7, 2024, 01:20:09
    Author     : sonhu
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Planner</title>
    <style>
        /* Optional: CSS for styling */
        .form-container {
            width: 50%;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .form-container label {
            display: block;
            margin-bottom: 10px;
        }
        .form-container input[type=text], .form-container input[type=date] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-container input[type=submit] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
        }
        .form-container input[type=submit]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Create New Planner</h2>
        <form action="createPlanners" method="post">
            <label for="plannerName">Planner Name:</label>
            <input type="text" id="plannerName" name="plannerName" required>
            <br>
            <label for="createTime">Create Time:</label>
            <input type="text" id="createTime" name="createTime" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.sql.Date(System.currentTimeMillis())) %>" readonly>
            <br>
             <label for="endDate">Start Time:</label>
            <input type="date" id="startTime" name="startTime" required>
            <br>
            <label for="endDate">End Time:</label>
            <input type="date" id="endTime" name="endTime" required>
            <br>
            <input type="submit" value="Create Planner">
        </form>
    </div>
</body>
</html>



