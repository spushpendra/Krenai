<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Total Orders - Krenai</title>
    <link href="assets/css/vendors.min.css" rel="stylesheet" />
    <link href="assets/css/styles.min.css" rel="stylesheet" />
    <link href="assets/css/push.css" rel="stylesheet" />
 
    <!-- CSS files for image cropping -->
    
    <link href="<c:url value='/assets/css/imageCroppie/style.css' />" rel="stylesheet" />
    <link href="<c:url value='/assets/css/imageCroppie/style-example.css' />" rel="stylesheet" />
    <link href="<c:url value='/assets/css/imageCroppie/jquery.Jcrop.css' />" rel="stylesheet" /> 

    <script charset="utf-8" src="http://maps.google.com/maps/api/js?sensor=true"></script>
  </head>
  <body scroll-spy="" id="top" class=" theme-template-dark theme-pink alert-open alert-with-mat-grow-top-right">
    <div class="loading-message" id="loading-message">
      <button class="btn btn-warning btn-flat">Updating...<div class="ripple-wrapper"></div></button>
    </div>
    <main>
      <%@ include file="/master/sidebar.jsp" %>
      <div class="main-container">
        <nav class="navbar navbar-default navbar-fixed-top">
          <%@ include file="/master/header.jsp" %>
        </nav>
        <div class="main-content" autoscroll="true" bs-affix-target="" init-ripples="">
          <section class="tables-data">
            <div class="page-header">
              <h1>      <i class="md md-list"></i>     Theme    </h1>
              <p class="lead"><a href="https://www.datatables.net/" target="_blank">To make your store beutiful :</a> Select a theme for your store</p>
            </div>
           <div class="row">
              <div class="col-md-12">
                <div class="well white">
                 <c:if test="${not empty errorMessage}">
			<div class="row">
              <div class="col-lg-12">
                <div class="bs-component">
                  <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <h4>Warning!</h4>
                    <p>${errorMessage}</p>
                  </div>
                </div>
              </div>
            </div>
			</c:if>
			
			<c:if test="${not empty successMessage}">
			<div class="row">
              <div class="col-lg-12">
                <div class="bs-component">
                  <div class="alert alert-dismissible alert-success">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <h4>Success!</h4>
                    <p>${successMessage}</p>
                  </div>
                </div>
              </div>
            </div>
			</c:if>
			
                  <form action="<c:url value="/profile/theme/upload"/>" method="post"  enctype="multipart/form-data">
                    <fieldset>
                      <legend class="m-b-10">Custom upload</legend>
                       <div class="form-group">

                        <div class="cropme"  data-img='myCanvas' style="width:980px;height:200px;"></div>
                        <input type="hidden" name="themeImage" id="productImagemyCanvas" >

                          <!-- <div class="drop-box btn-flat btn-info fileinput-button">
                          <p class="text-success"><i class="btn btn-lg btn-round btn-success "><span class="md md-add"></span></i>&nbsp;</p>
                          <input type="file" onchange="this.form.submit();" name="themeImage" accept=".jpg,.jpeg,.png,.gif" class="fileupload" >
                          </div> -->
                        </div>
                        <button class="btn btn-primary" type="submit" disabled id="submitBtn" onclick="spinner();" >Submit</button>
                        <div id="spinner"  style="margin-right:80%;margin-top:-1%;"></div>
                    </fieldset>
                  </form>
                </div>
              </div>

              <div class="col-md-12">
                <div class="card">
                  <div class="card-image"> <img src="<c:url value="/assets/img/shooper-header-1.jpg"/>" class="p-5">
                    <div class="card-title pull-right">Theme 2</div>
                  </div>
                    <div class="card-profile pull-left"><img src="${loggedUser.imagePath}" alt="" style="width:92px; margin: 3px;"></div>

                    <div class="card-action clearfix">
                    <div class="pull-right">
                    <div class="form-group">
                          <div class="checkbox">
                             <label>
                            <form action="<c:url value="/profile/theme"/>" method="post">
                            <input type="hidden" name="themeUrl" value="/assets/img/shooper-header-4.jpg">
                             <input type="checkbox" onclick="this.form.submit();" required="">Select me for your store profile 
                             </form>
                             </label>
                            </div>
                        </div>

                    </div>
                  </div>
                </div>
              </div>
						

        
              <div class="col-md-12">
                <div class="card">
                  <div class="card-image"> <img src="<c:url value="/assets/img/shooper-header-1.jpg"/>" class="p-5">
                    <div class="card-title pull-right">Theme 1</div>
                  </div>
                    <div class="card-profile pull-left"><img src="${loggedUser.imagePath}" alt="" style="width:92px; margin: 3px;"></div>
                    <div class="card-action clearfix">
                    <div class="pull-right">
                    <div class="form-group">
                          <div class="checkbox">
                             <label>
                            <form action="<c:url value="/profile/theme"/>" method="post">
                            <input type="hidden" name="themeUrl" value="/assets/img/shooper-header-1.jpg">
                             <input type="checkbox" onclick="this.form.submit();" required="">Select me for your store profile 
                             </form>
                             </label></div>
                        </div>
                        
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-md-12">
                <div class="card">
                  <div class="card-image"> <img src="<c:url value="/assets/img/shooper-header-2.jpg"/>" class="p-5">
                    <div class="card-title pull-right">Theme 1</div>
                  </div>
                    <div class="card-profile pull-left"><img src="${loggedUser.imagePath}" alt="" style="width:92px; margin: 3px;"></div>
                    <div class="card-action clearfix">
                    <div class="pull-right">
                    <div class="form-group">
                          <div class="checkbox">
                             <label>
                            <form action="<c:url value="/profile/theme"/>" method="post">
                            <input type="hidden" name="themeUrl" value="/assets/img/shooper-header-2.jpg">
                             <input type="checkbox" onclick="this.form.submit();" required="">Select me for your store profile 
                             </form>
                             </label></div>
                        </div>
                        
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-md-12">
                <div class="card">
                  <div class="card-image"> <img src="<c:url value="/assets/img/shooper-header-3.jpg"/>" class="p-5">
                    <div class="card-title pull-right">Theme 1</div>
                  </div>
                    <div class="card-profile pull-left"><img src="${loggedUser.imagePath}" alt="" style="width:92px; margin: 3px;"></div>
                    <div class="card-action clearfix">
                    <div class="pull-right">
                    <div class="form-group">
                          <div class="checkbox">
                            <label>
                             <input type="checkbox" required="">Select me for your store profile </label>
                          </div>
                        </div>
                        
                    </div>
                  </div>
                </div>
              </div>
              
               <div class="col-md-12">
                <div class="card">
                  <div class="card-image"> <img src="<c:url value="/assets/img/shooper-header-3.jpg"/>" class="p-5">  
                    <div class="card-title pull-right">Theme 1</div>
                  </div>
                    <div class="card-profile pull-left"><img src="${loggedUser.imagePath}" alt="" style="width:92px; margin: 3px;"></div>
                    <div class="card-action clearfix">
                    <div class="pull-right">
                    <div class="form-group">
                          <div class="checkbox">
                            <label>
                             <input type="checkbox" required="">Select me for your store profile </label>
                          </div>
                        </div>
                        
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-md-12">
                <div class="card">
                  <div class="card-image"> <img src="<c:url value="/assets/img/shooper-header-4.jpg"/>" class="p-5">
                    <div class="card-title pull-right">Theme 1</div>
                  </div>
                    <div class="card-profile pull-left"><img src="<c:url value="/assets/img/store.png" />" alt="" style="width:92px; margin: 3px;"></div>
                    <div class="card-action clearfix">
                    <div class="pull-right">
                    <div class="form-group">
                          <div class="checkbox">
                            <label>
                            <form action="<c:url value="/profile/theme"/>" method="post">
                            <input type="hidden" name="themeUrl" value="/assets/img/shooper-header-4.jpg">
                             <input type="checkbox" onclick="this.form.submit();" required="">Select me for your store profile 
                             </form>
                             </label>
                          </div>
                        </div>
                        
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-md-12">
                <div class="card">
                  <div class="card-image"> <img src="assets/img/shooper-header-5.jpg" class="p-5">
                    <div class="card-title pull-right">Theme 1</div>
                  </div>
                    <div class="card-profile pull-left"><img src="<c:url value="/assets/img/store.png" />" alt="" style="width:92px; margin: 3px;"></div>
                    <div class="card-action clearfix">
                    <div class="pull-right">
                    <div class="form-group">
                          <div class="checkbox">
                            <label>
                             <input type="checkbox" required="">Select me for your store profile </label>
                          </div>
                        </div>
                        
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-md-12">
                <div class="card">
                  <div class="card-image"> <img src="assets/img/shooper-header-5.jpg" class="p-5">
                    <div class="card-title pull-right">Theme 1</div>
                  </div>
                    <div class="card-profile pull-left"><img src="<c:url value="/assets/img/store.png" />" alt="" style="width:92px; margin: 3px;"></div>
                    <div class="card-action clearfix">
                    <div class="pull-right">
                    <div class="form-group">
                          <div class="checkbox">
                            <label>
                             <input type="checkbox" required="">Select me for your store profile </label>
                          </div>
                        </div>
                        
                    </div>
                  </div>
                </div>
              </div>
              
              <div class="col-md-12">
                <div class="card">
                  <div class="card-image"> <img src="assets/img/shooper-header-6.jpg" class="p-5">
                    <div class="card-title pull-right">Theme 1</div>
                  </div>
                    <div class="card-profile pull-left"><img src="<c:url value="/assets/img/store.png" />" alt="" style="width:92px; margin: 3px;"></div>
                    <div class="card-action clearfix">
                    <div class="pull-right">
                    <div class="form-group">
                          <div class="checkbox">
                            <label>
                             <input type="checkbox" required="">Select me for your store profile </label>
                          </div>
                        </div>
                        
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-md-12">
                <div class="card">
                  <div class="card-image"> <img src="<c:url value="/assets/img/shooper-header-6.jpg" />" class="p-5">  
                    <div class="card-title pull-right">Theme 1</div>
                  </div>
                    <div class="card-profile pull-left"><img src="<c:url value="/assets/img/store.png" />" alt="" style="width:92px; margin: 3px;"></div>
                    <div class="card-action clearfix">
                    <div class="pull-right">
                    <div class="form-group">
                          <div class="checkbox">
                            <label>
                             <input type="checkbox" required="">Select me for your store profile </label>
                          </div>
                        </div>
                        
                    </div>
                  </div>
                </div>
              </div>


            </div>
          </section>
          
        </div>
      </div>
    </main>

<script type="text/javascript">
function spinner(){
         // $("#add-shop-btn").attr("disabled",true);
         // $("#").submit();
         $("#spinner").css("text-align","right");
         
        $("#spinner").append("<svg width='32px' height='32px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' class='uil-ring-alt'><rect x='0' y='0' width='100' height='100' fill='none' class='bk'></rect><circle cx='50' cy='50' r='40' stroke='#f0f0f0' fill='none' stroke-width='10' stroke-linecap='round'></circle><circle cx='50' cy='50' r='40' stroke='#e91e63' fill='none' stroke-width='6' stroke-linecap='round'><animate attributeName='stroke-dashoffset' dur='2s' repeatCount='indefinite' from='0' to='502'></animate><animate attributeName='stroke-dasharray' dur='2s' repeatCount='indefinite' values='150.6 100.4;1 250;150.6 100.4'></animate></circle></svg>");
        }
</script>
<%@ include file="/master/footer.jsp" %>



<script src="<c:url value='/assets/js/imageCroppie/jquery-1.10.2.min.js' />"></script>
<script src="<c:url value='/assets/js/imageCroppie/jquery.Jcrop.js' />"></script>
<script src="<c:url value='/assets/js/imageCroppie/jquery.SimpleCropper.js' />"></script>

<script>
        // Init Simple Cropper
        $('.cropme').simpleCropper();
        // $('#secndCrop').simpleCropper2();
        
      </script>