<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<jsp:include page="header.jsp"></jsp:include>
<%
if(session.getAttribute("currentUser")!= null){
Users user = (Users)session.getAttribute("currentUser");
NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
%>
    <br>
    <div class="row" style="border-radius: 10px">
        <div class="col-lg-12" style="width: 1000px; margin: auto;">
            <div class="card">
                <div class="card-body">
                    <div style="display: flex; justify-content: space-between">
                        <h4 class="text-primary">Thanh toán</h4>
                        <a href="paymenthistory.jsp" style="text-decoration: none"><h5 class="text-primary">xem lịch sử thanh toán</h5></a>
                    </div>
                    <form class="row g-3" action="vnpayajax" id="frmCreateOrder" method="post">     
                        <div class="col-md-12">
                            <label class="form-label">Số dư hiện tại</label>
                            <input type="number" class="form-control" value="<%=user.getBalance()%>" disabled>
                        </div>
                        <div class="col-md-6">
                            <label for="amount">Số tiền</label>
                            <select class="form-select" id="amount" name="amount" required>
                            <option value="10000" selected><%=currencyFormatter.format(10000)%> => 10 coin</option>
                            <option value="20000"><%=currencyFormatter.format(20000)%> => 20 coin</option>
                            <option value="50000"><%=currencyFormatter.format(50000)%> => 50 coin</option>
                            <option value="100000"><%=currencyFormatter.format(100000)%> => 100 coin</option>
                            <option value="200000"><%=currencyFormatter.format(200000)%> => 200 coin</option>
                            <option value="500000"><%=currencyFormatter.format(500000)%> => 500 coin</option>
                                               
                        </select>
                        </div>
                        <div class="col-12">
                            <button id="submit_button" style="margin: auto" class="btn btn-primary" type="submit">Xác nhận</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>    
</div>    
<%
    }
%>
<jsp:include page="footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
<script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>

<script type="text/javascript">
    $("#frmCreateOrder").submit(function () {
        var postData = $("#frmCreateOrder").serialize();
        var submitUrl = $("#frmCreateOrder").attr("action");
        $.ajax({
            type: "POST",
            url: submitUrl,
            data: postData,
            dataType: 'JSON',
            success: function (x) {
                console.log("Response:", x); // Added console log for debugging
                if (x.code === '00') {
                    if (window.vnpay) {
                        vnpay.open({width: 768, height: 600, url: x.data});
                    } else {
                        location.href = x.data;
                    }
                } else {
                    alert(x.Message);
                    console.log("Error message:", x.Message); // Added console log for debugging
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("An error occurred. Please try again.");
                console.log("AJAX Error:", textStatus, errorThrown); // Added console log for debugging
            }
        });
        return false;
    });
</script>