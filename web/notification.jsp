<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<jsp:include page="header.jsp"></jsp:include>
<br>
<div class="row" style="border-radius: 10px">
    <div class="col-lg-12" style="width: 1000px; margin: auto;">
          <div class="card">
            <div class="card-body">
              <h4 class="text-primary">Thông báo hệ thống</h4>
              <!-- Browser Default Validation -->
              <form class="row g-3">     
                <div class="col-md-12">
                  <label class="form-label">Nhập thông báo</label>
                  <input type="text" class="form-control" id="validationDefault03" required>
                </div>
                <div class="col-md-12">
                  <label class="form-label">Thời gian</label>
                  <input type="date" class="form-control" id="validationDefault03" required>
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

