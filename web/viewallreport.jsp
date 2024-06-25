<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Report List</title>
</head>
<body>
    <h1>Report List</h1>

    <table border="1">
        <thead>
            <tr>
                <th>Report ID</th>
                <th>User ID</th>
                <th>User Reported ID</th>
                <th>Report Context</th>
                <th>Report Date</th>
                <th>Report Img</th>
                <th>Reasons</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${reports}" var="report">
                <tr>
                    <td>${report.reportId}</td>
                    <td>${report.userId}</td>
                    <td>${report.userReportedId}</td>
                    <td>${report.reportContext}</td>
                    <td>${report.reportDate}</td>
                    <td>${report.reportImg}</td>
                    <td>
                        <ul>
                            <c:forEach items="${report.reasons}" var="reason">
                                <li>${reason.reasonName}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <hr/>

    

</body>
</html>
