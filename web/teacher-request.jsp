<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<jsp:include page="header.jsp"></jsp:include>
<br>
<div class="row" style="border-radius: 10px">
    <div class="col-lg-12" style="width: 1000px; margin: auto;">
          <div class="card">
            <div class="card-body">
              <h4 class="text-primary">Yêu cầu trở thành giáo viên</h4>
              <p>Khi được trở thành giáo viên, bạn sẽ được quyền tạo bài kiểm tra, được làm quen với hệ thống ôn thi trực tuyến dưới vai trò là giáo viên, tiếp cận các phương pháp giảng dạy cũng như kiểm tra hiện đại
              <p>Tuy nhiên bạn cần phải chấp hành một số quy định mới nghiêm ngặt hơn dành cho giáo viên, nhằm mục đích </p>

              <!-- Browser Default Validation -->
              <form class="row g-3" method="POST" action="AddTeacherRequest">
                <div class="col-md-4">
                  <label class="form-label">Kinh nghiệm dạy học(năm)</label>
                  <input type="number" class="form-control" id="validationDefault01" name="exp" required>
                </div>
              
                <div class="col-md-4">
                  <label class="form-label">Môn học</label>
                  <select class="form-select" id="validationDefault04" name="subject" required>
                    <option selected disabled value="">Choose...</option>
                    <option value="1">Toán</option>
                    <option value="2">Lý</option>
                    <option value="3">Hóa</option>
                    <option value="4">Sinh</option>
                    <option value="5">Anh</option>
                    <option value="6">Địa</option>
                    <option value="7">Sử</option>
                    <option value="8">GDCD</option>                    
                  </select>
                </div>
                  <div class="col-md-4">
                  <label class="form-label">Trình độ học vấn</label>
                  <select class="form-select" id="validationDefault04" name="level" required>
                    <option selected disabled value="">Choose...</option>
                    <option value="Đại học & Cao đẳng">Đại học & Cao đẳng</option>
                    <option value="Thạc sĩ">Thạc sĩ</option>
                    <option value="Tiến sĩ">Tiến sĩ</option>

                  </select>
                </div>
                <div class="col-md-12">
                  <label class="form-label">Đã từng/Đang công tác ở trường</label>
                  <input type="text" class="form-control" id="validationDefault03" name="school" required>
                </div>
                
                <div class="col-12">
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>
                    <label class="form-check-label">
                      Chấp hành các thông báo, quy định do Ban quản trị đưa ra
                    </label>
                  </div>
                </div>
                  <div class="col-12">
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>
                    <label class="form-check-label">
                      Cam kết thông tin trên là đúng sự thật. Nếu trong quá trình xác minh có sai phạm, Ban quản trị có thể tước quyền Giáo viên bất kì lúc nào
                    </label>
                  </div>
                </div>
                  <div class="col-12">
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>
                    <label class="form-check-label">
                      Không đưa thông tin ngoài lề, không liên quan vào bài kiểm tra của mình
                    </label>
                  </div>
                </div>
                  <div class="col-12">
                  <button style="margin: auto" class="btn btn-primary" type="submit">Xác nhận</button>
                </div>
              </form>
            </div>
          </div>

        </div>
      </div>    
</div>    
    
<jsp:include page="footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"></script>
<script>

