<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<jsp:include page="header.jsp"></jsp:include>
    <br>
    <div class="row" style="border-radius: 10px">
        <div class="col-lg-12" style="width: 1000px; margin: auto;">
            <div class="card">
                <div class="card-body">
                    <h4 class="text-primary">Tùy chỉnh</h4>
                    <form class="row g-3">     
                        <div class="col-md-6">
                            <label class="form-label">Tỉ lệ thu nhập của giáo viên</label>
                            <input id="teacher_rate" type="number" onkeydown="return event.keyCode !== 69" class="form-control" id="validationDefault03" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Tỉ lệ thu nhập của hệ thống</label>
                            <input id="admin_rate" type="number" onkeydown="return event.keyCode !== 69" class="form-control" id="validationDefault03" required>
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

<script>

    $(document).ready(function () {
        $("#submit_button").click(Add);
    });
    function Add() {
        let teacher_rate = $("#teacher_rate").val();
        let admin_rate = $("#admin_rate").val();
        if (isNaN(teacher_rate) || isNaN(admin_rate)) {
            alert("Vui lòng nhập đúng định dạng");
        } else if (teacher_rate + admin_rate != 100) {
            alert("Vui lòng nhập đúng tỉ lệ");
        } else {
            let formData = new FormData();
            formData.append('teacher_rate', teacher_rate);
            formData.append('admin_rate', admin_rate);
            fetch('Home', {
                method: 'POST',
                body: formData
            })
                    .then(response => response.json())
                    .then(data => {
                        console.log(data);
                    })
                    .catch(error => {
                        console.error(error);
                    });
        }
    }


</script>
<jsp:include page="footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


