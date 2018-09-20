<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Doucument - Krenai</title>
    <link href="assets/css/vendors.min.css" rel="stylesheet" />
    <link href="assets/css/styles.min.css" rel="stylesheet" />
    <script charset="utf-8" src="http://maps.google.com/maps/api/js?sensor=true"></script>
  </head>
  <body scroll-spy="" id="top" class=" theme-template-dark theme-pink alert-open alert-with-mat-grow-top-right">
    <main>
      <%@ include file="../master/sidebar.jsp" %>
      <div class="main-container">
        <nav class="navbar navbar-default navbar-fixed-top">
          <%@ include file="../master/header.jsp" %>
        </nav>
        <div class="main-content" autoscroll="true" bs-affix-target="" init-ripples="">
          <section class="cards">
            <div class="page-header">
             <c:if test="${not empty failedMessage}">
      <div class="row">
              <div class="col-lg-12">
                <div class="bs-component">
                  <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <h4>Error!</h4>
                    <p>${failedMessage}</p>
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
              <h1>      <i class="md md-photo"></i>      Documents    </h1>
            </div>
           
            <div class="row">
              <div class="col-md-4">
                <div class="card">
                  <div class="card-image"> 
                  <c:if test="${empty supplierPancardDetails}"> 
                  <img src="<c:url value="/assets/img/photos/4.jpg"/>">
                  </c:if>
                   <c:if test="${not empty supplierPancardDetails}">
                    <img src="${supplierPancardDetails.documentPath}">
                  </c:if>
                  <div class="card-title"> 
                    <c:if test="${empty supplierPancardDetails}">
                    <form id="pan-card-select-form" action="<c:url value="/registration/document/upload/pancard" />"  enctype="multipart/form-data" method="post">
                    <div class="form-group"> <span class="btn btn-info fileinput-button">                
                        <span>PAN CARD Upload</span>
                          <input type="file" name="document_image" id="pan-card-select" onchange="panCardChange(this.id);"  class="fileupload"> </span>
                        </div>
                        </form>
                        </c:if>
                    </div>
                  </div>
                  <div class="card-content">
                    <div class="card-profile pull-right">
                    <c:if test="${supplierPancardDetails.status.statusValue == 'verified'}">
                    <img src="<c:url value="/assets/img/icons/ballicons/check.png" />" alt="" style="width:75px;">
                    </c:if>
                    <c:if test="${supplierPancardDetails.status.statusValue == 'not verified'}">
                    <img src="<c:url value="/assets/img/icons/ballicons/unverified.png" />" alt="" style="width:75px;">
                    </c:if>
                    </div>
                    <h5 class="text-muted"> Address Proof Letter</h5>
                  </div>
                </div>
              </div>
              <div class="col-md-4">
                <div class="card">
                  <div class="card-image"> 
                  <c:if test="${empty supplierTINOrCSTDetails}"> 
                  <img src="<c:url value="/assets/img/photos/41.jpg"/>">
                  </c:if>
                   <c:if test="${not empty supplierTINOrCSTDetails}">
                    <img src="${supplierTINOrCSTDetails.documentPath}">
                  </c:if>
                  <div class="card-title"> 
                    <c:if test="${empty supplierTINOrCSTDetails}">
                    <form id="tin-select-form"  action="<c:url value="/registration/document/upload/tin" />" method="post" enctype="multipart/form-data" >
                    <div class="form-group"> <span class="btn btn-info fileinput-button">                
                        <span>TIN/CST Reg. Doc. Upload</span>
                          <input type="file" name="document_image" id="tin-select" onchange="panCardChange(this.id);" class="fileupload"> </span>
                        </div>
                        </form>
                        </c:if>
                    </div>
                  </div>
                  <div class="card-content">
                    <div class="card-profile pull-right">
                    <c:if test="${supplierTINOrCSTDetails.status.statusValue == 'verified'}">
                    <img src="<c:url value="/assets/img/icons/ballicons/check.png" />" alt="" style="width:75px;">
                    </c:if>
                    <c:if test="${supplierTINOrCSTDetails.status.statusValue == 'not verified'}">
                    <img src="<c:url value="/assets/img/icons/ballicons/unverified.png" />" alt="" style="width:75px;">
                    </c:if>
                    </div>
                   <h5 class="text-muted">   Pan Card Verification </h5>
                  </div>
                </div>
              </div>
              
             <div class="col-md-4">
                <div class="card">
                  <div class="card-image"> <c:if test="${empty supplierAddressProofDetails}"> 
                  <img src="<c:url value="/assets/img/photos/42.jpg"/>">
                  </c:if>
                   <c:if test="${not empty supplierAddressProofDetails}">
                    <img src="${supplierAddressProofDetails.documentPath}">
                  </c:if>
                  <div class="card-title"> 
                  <c:if test="${empty supplierAddressProofDetails}">
                    <form id="address-select-form"  action="<c:url value="/registration/document/upload/address" />" enctype="multipart/form-data" method="post">
                    <div class="form-group"> <span class="btn btn-info fileinput-button">                
                        <span>Address Proof Doc. Upload</span>
                          <input type="file" name="document_image" id="address-select" onchange="panCardChange(this.id);"  class="fileupload"> </span>
                        </div>
                        </form>
                        </c:if>
                    </div>
                  </div>
                  <div class="card-content">
                    <div class="card-profile pull-right">
                    <c:if test="${supplierAddressProofDetails.status.statusValue == 'verified'}">
                    <img src="<c:url value="/assets/img/icons/ballicons/check.png" />" alt="" style="width:75px;">
                    </c:if>
                    <c:if test="${supplierAddressProofDetails.status.statusValue == 'not verified'}">
                    <img src="<c:url value="/assets/img/icons/ballicons/unverified.png" />" alt="" style="width:75px;">
                    </c:if>
                    </div>
                    <h5 class="text-muted">   Store Registration</h5>
                  </div>
                </div>
              </div>
              
            </div>
              
            
            

          </section>
        </div>
      </div>
    </main>
  </body>
  <script>
  function panCardChange(a){
	  formId = a+"-form";
	  $('#'+formId).submit();
	  //alert(formId);
	  
  }
  </script>
  <%@ include file="../master/footer.jsp" %>