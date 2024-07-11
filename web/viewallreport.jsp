<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>

<html>
<head>
    <meta charset="UTF-8">

    <style>
        a {
            overflow-wrap: break-word;
            word-break: break-word;
        }
        p {
            overflow-wrap: break-word;
            word-break: break-word;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border-bottom: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .image-modal {
            display: none;
            position: fixed;
            z-index: 9999;
            padding-top: 100px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.9);
        }
        .image-modal-content {
            margin: auto;
            display: block;
            width: 95%;
            max-width: 1200px;
        }
        .image-modal img {
            display: block;
            width: 100%;
            height: auto;
        }
        .image-modal-close {
            position: absolute;
            top: 15px;
            right: 35px;
            color: #f1f1f1;
            font-size: 40px;
            font-weight: bold;
            transition: 0.3s;
        }
        .image-modal-close:hover,
        .image-modal-close:focus {
            color: #bbb;
            text-decoration: none;
            cursor: pointer;
        }
    </style>

    <script>
        function openImageModal(imgSrc) {
            document.getElementById("imageModal").style.display = "block";
            document.getElementById("imgModal").src = imgSrc;
        }

        function closeImageModal() {
            document.getElementById("imageModal").style.display = "none";
        }
    </script>
</head>
<body>
    <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-3 text-white animated slideInDown">Danh sách báo cáo</h1>
                </div>
            </div>
        </div>
    </div>
    <main id="main" class="main" style="margin-left: 0">
        <section class="section" style="margin: auto;justify-content: center">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <table class="table datatable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>User Being Reported ID</th>
                                        <th>User Reported ID</th>
                                        <th>Reasons</th>
                                        <th>Context</th>
                                        <th>Date</th>
                                        <th>Image</th>
                                    </tr>
                                </thead>
                                <tbody>
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
                                                    <img src="${pageContext.request.contextPath}/${report.reportImg}" alt="Report Image" width="100"
                                                         onclick="openImageModal('${pageContext.request.contextPath}/${report.reportImg}')">
                                                    <div id="imageModal" class="image-modal">
                                                        <span class="image-modal-close" onclick="closeImageModal()">&times;</span>
                                                        <img class="image-modal-content" id="imgModal">
                                                    </div>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
