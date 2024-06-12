<%-- 
    Document   : editExam
    Created on : 12 thg 6, 2024, 02:02:07
    Author     : sonhu
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa đề thi</title>
</head>
<body>
    <h1>Chỉnh sửa đề thi</h1>
    <form action="EditExamServlet" method="post">
        <input type="hidden" name="examId" value="${exam.examId}">
        Tên đề thi: <input type="text" name="examName" value="${exam.examName}"><br>
        <input type="submit" value="Lưu">
    </form>
    <a href="Teacher.jsp">Quay lại</a>
</body>
</html>

