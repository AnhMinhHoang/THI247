<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"></jsp:include>

<style>
  #main{
    margin: auto;
  }
  #card-height{
    height: 364px;
  }
  #card-center {
    align-items: center;
    justify-content: center;
  }
</style>       
            </div>
        </nav>
        <!-- Navbar End -->
  <main id="main" class="main">
    <section class="section profile">
      <div class="row">
        <div class="col-xl-4">
          <div class="card" id="card-height">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center" id="card-center">
              <img src="assets/img/profile-img.jpg"class="rounded-circle">
              <h2>Kevin Anderson</h2>
              <h3>Web Designer</h3>    
            </div>
          </div>

        </div>

        <div class="col-xl-8">

          <div class="card">
            <div class="card-body pt-3">
              <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered">

                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">Thông tin cá nhân</button>
                </li>
              </ul>
              <div class="tab-content pt-2">

                <div class="tab-pane fade show active profile-overview" id="profile-overview">

                  <h5 class="card-title">Profile Details</h5>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">Họ và Tên</div>
                    <div class="col-lg-9 col-md-8">Kevin Anderson</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Vai trò</div>
                    <div class="col-lg-9 col-md-8">Giáo viên/ Học Sinh</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Address</div>
                    <div class="col-lg-9 col-md-8">A108 Adam Street, New York, NY 535022</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">SĐT</div>
                    <div class="col-lg-9 col-md-8">(436) 486-3538 x29071</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Email</div>
                    <div class="col-lg-9 col-md-8">k.anderson@example.com</div>
                  </div>
                </div>
              </div><!-- End Bordered Tabs -->
            </div>
          </div>
        </div>
      </div>
    </section>

  </main><!-- End #main -->
<jsp:include page="footer.jsp"></jsp:include>

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>