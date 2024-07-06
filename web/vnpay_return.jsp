<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="VNPay.Config"%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>   
<style>
    fieldset {
            margin-bottom: 20px;
            border: 2px solid #ccc;
            border-radius: 5px;
            padding: 20px;
        }
        label{
            font-size: 20px
        }
</style>
<%
if(session.getAttribute("currentUser") != null){
%>
<body>
        <%
            //Begin process return from VNPAY
            Map fields = new HashMap();
            for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }

            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            String signValue = Config.hashAllFields(fields);

        %>
        <!--Begin display -->
        <br><br>
        <div class="container">
            
                <fieldset style="width: 900px; margin: auto; ">
                <h3 class="text-primary" style="text-align: center">KẾT QUẢ THANH TOÁN</h3>
                <hr>
                <br>
            <div class="table-responsive">
                <div class="form-group">
                    <label style="font-weight: bold">Mã giao dịch thanh toán:</label>
                    <label><%=request.getParameter("vnp_TxnRef")%></label>
                </div>    
                <div class="form-group">
                    <%
                    int amount = Integer.parseInt(request.getParameter("vnp_Amount")) / 100;
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                    String money = currencyFormatter.format(amount);
                    %>
                    <label style="font-weight: bold">Số tiền:</label>
                    <label><%=money%></label>
                </div>  
                <div class="form-group">
                    <label style="font-weight: bold">Mã lỗi thanh toán:</label>
                    <label><%=request.getParameter("vnp_ResponseCode")%></label>
                </div> 
                <div class="form-group">
                    <label style="font-weight: bold">Mã ngân hàng thanh toán:</label>
                    <label><%=request.getParameter("vnp_BankCode")%></label>
                </div> 
                <div class="form-group">
                    <%
                    String date = request.getParameter("vnp_PayDate");
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
                    date = dateTime.format(outputFormatter);
                    %>
                    <label style="font-weight: bold">Thời gian thanh toán:</label>
                    <label><%=date%></label>
                </div> 
                <div class="form-group">
                    <label style="font-weight: bold">Tình trạng giao dịch:</label>
                    <label>
                        <%
                            if (signValue.equals(vnp_SecureHash)) {
                                if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                                    boolean check = (Boolean)session.getAttribute("savePay");
                                    Users user = (Users)session.getAttribute("currentUser");
                                    if(check){
                                        session.setAttribute("savePay", false);
                                        new UserDAO().createPayment(user.getUserID(), request.getParameter("vnp_TxnRef"), request.getParameter("vnp_BankCode"), amount, date);
                                        new UserDAO().addMoneyToBalance(amount / 1000, user.getUserID());
                                        user = new UserDAO().findByUserID(user.getUserID());
                                        session.setAttribute("currentUser", user);
                                    }
                                    out.print("Thành công");
                                } else {
                                    out.print("Không thành công");
                                }

                            } else {
                                out.print("invalid signature");
                            }
                        %></label>
                </div> 
            </div>
                <a href="Home" style="text-decoration: none"><button class="btn btn-primary">Trở về</button></a>
            </fieldset>
        </div>  
<%
    }
%>
<jsp:include page="footer.jsp"></jsp:include>
    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
