<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách báo cáo</title>
</head>
<body>
    <h1>Danh sách báo cáo</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>User Being Reported ID</th>
            <th>User Reported ID</th>
            <th>Reasons</th>
            <th>Context</th>
            <th>Date</th>
            <th>Image</th>
        </tr>
        <c:forEach var="report" items="${reports}">
            <tr>
                <td>${report.reportId}</td>
                <td>${report.userId}</td>
                <td>${report.userReportedId}</td>
                <td>
                    <c:forEach var="reason" items="${report.reasons}">
                        ${reason.reasonName}<br>
                    </c:forEach>
                </td>
                <td>${report.reportContext}</td>
                <td>${report.reportDate}</td>
                <td>
                    <c:if test="${not empty report.reportImg}">
                        <img src="${pageContext.request.contextPath}/${report.reportImg}" alt="Report Image" width="100">
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
