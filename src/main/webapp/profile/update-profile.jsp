<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

      
       <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Validation - Materialism</title>
        <!-- CSS files for image cropping -->
    
    <link href="<c:url value='/assets/css/imageCroppie/style.css' />" rel="stylesheet" />
    <link href="<c:url value='/assets/css/imageCroppie/style-example.css' />" rel="stylesheet" />
    <link href="<c:url value='/assets/css/imageCroppie/jquery.Jcrop.css' />" rel="stylesheet" /> 

    <!-- this is a special css for this page -->
    <link href="<c:url value='/assets/css/profile.update.css' />" rel="stylesheet" />
  <script charset="utf-8" src="http://maps.google.com/maps/api/js?sensor=true"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    
  </head>
  <body scroll-spy="" id="top" class=" theme-template-dark theme-pink alert-open alert-with-mat-grow-top-right">
    <main>
      <%@ include file="../master/sidebar.jsp" %>
     
      <div class="main-container">
        <nav class="navbar navbar-default navbar-fixed-top">
          <%@ include file="../master/header.jsp" %>
      
        </nav>
        <div class="main-content" autoscroll="true" bs-affix-target="" init-ripples="">
          <section class="forms-validation">
            
            <div class="row m-b-40">
              
              <div class="col-md-12">
                <div class="card">
                  <div class="card-image"> <img src="${supplier.supplierShop.shopTheme}" height="150px" class="p-5">
                    <div class="card-title pull-right">${supplier.supplierShop.shopName }</div>
                  </div>
                  <!-- <form action="<c:url value="/profile/theme/upload"/>" method="post"  enctype="multipart/form-data"> -->
                    <div id="profileimges">
                      <div class="card-profile pull-right">
                        <!-- <div class="cropme"  data-img='myCanvas' style="border-radius:50%; max-width:102px; margin: 3px;"></div> -->
                      <img src="${loggedUser.imagePath}" class="cropme" id="updateImagePath" data-img='myCanvas' alt="" style="border-radius:50%; width:150px;height:150px;">
                          <input type="hidden" name="profileimage" id="productImagemyCanvas" >
                          <input type="hidden" name="supplier" id="supplierIdImgId" value="${supplier.supplierId}">
                          <div id="spinner" ></div>
                      </div>
                    </div>
<!--                     <button class="btn type="submit" btn-primary">Submit</button>
                  </form> -->
                    <div class="card-action clearfix">
                    <div class="pull-left">
                      <a href="<c:url value="/profile/theme"/>"><button class="btn btn-round btn-flat btn-primary"><i class="md  md-mode-edit f20"></i></button></a>
                      <a href="#"><button class="btn btn-round btn-flat btn-primary"><i class="md  md-share f20"></i></button></a>
                    </div>
                  </div>

                 </div>
              </div>


            </div>

            <div class="row  m-b-40">
              <div class="col-md-12">
                <div class="well white" id="forms-validation-container">
                <c:if test="${not empty errorMessage}">
      <div class="row">
              <div class="col-lg-12">
                <div class="bs-component">
                  <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">�</button>
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
                    <button type="button" class="close" data-dismiss="alert">�</button>
                    <h4>Success!</h4>
                    <p>${successMessage}</p>
                  </div>
                </div>
              </div>
            </div>
      </c:if>
      <div id="imguploadmessage">
      </div>
                  <div>
                   <form class="form-floating" action="<c:url value="/profile/update"/>" onSubmit="return validateProfileForm();" method="post" id="form-validation">
                      <fieldset>
                        <legend>Informations</legend>
                        <div class="form-group">
                          <label class="control-label">Full Name</label>
                          <input type="text" class="form-control" id="nameFull" name="fullname"  data-error="This field is required" value="${supplier.fullName}" onkeyup="$(this).val($(this).val().replace(/[^A-Z/a-z/ ]/g, ''));">
                          <div class="help-block with-errors validationText" id="nameFullValidate"></div>
                        </div>
                        <div class="form-group">
                          <label class="control-label">Email</label>
                          <input type="text" class="form-control" id="emailId" onchange="return validates();" name="emailId" data-error="That email address is invalid" value="${supplier.emailId}"><div ></div>
                          <div class="help-block with-errors validationText" id="emailIdValidate"></div>
                        </div>
                        <div class="form-group">
                          <label class="control-label">Mobiles</label>
                          <input type="text" class="form-control" onblur="return mobileLength();" name="contactNo" id="contactNo" value="${supplier.contactNo}"  data-error="This field is required" onkeyup="$(this).val($(this).val().replace(/[^0-9/]/g, ''));" maxlength="10">

                          <div class="help-block with-errors validationText"  id="contactNoValidate"></div>
                        </div>

                        <legend>Store Details</legend>
                        <div class="form-group">
                          <label class="control-label">Store Name</label>
                          <input type="text" class="form-control"  name="shopName" id="shopName" value="${supplier.supplierShop.shopName }" data-error="That url is invalid">
                          <div class="help-block with-errors validationText" id="shopNameValidate"></div>
                        </div>
                        <div class="form-group">
                          <label class="control-label">Phone Number</label>
                          <input type="text" class="form-control" onblur="return phoneLength();"  name="shopContactNo" id="shopContactNo" value="${supplier.supplierShop.shopContactNo}" data-error="Numeric values from 0-***" onkeyup="$(this).val($(this).val().replace(/[^0-9/]/g, ''));" maxlength="10"><div ></div>
                          <div class="help-block with-errors validationText" id="shopContactNoValidate"></div>
                        </div>
            
                         <div class="form-group">
                          <label class="control-label">Address Line 1</label>
                          <input type="text" class="form-control" name="addressLine1" id="addressLine1" value="${supplier.address.addressLine1}"  data-error="Numeric values from 0-***">
                          <div class="help-block with-errors validationText" id="addressLine1Validate" ></div>
                        </div>

                     
                         <div class="form-group">
                          <label class="control-label">Address Line 2</label>
                          <input type="text" class="form-control" name="addressLine2" value="${supplier.address.addressLine2}" required="" data-error="Numeric values from 0-***">
                          <div class="help-block with-errors"></div>
                        </div>

                     
                         <div class="form-group">
                          <label class="control-label">City</label>
                          <input type="text" class="form-control" name="city" id="city" value="${supplier.address.city}" data-error="Numeric values from 0-***">
                          <div class="help-block with-errors validationText" id="cityValidate"></div>
                        </div>
                        
                        <div class="form-group">
                          <label class="control-label">Krenai Address</label>
                          <input type="text" class="form-control" disabled="true" value="${supplier.address.googleAddress}" data-error="Numeric values from 0-***">
                          <div class="help-block with-errors validationText" id="cityValidate"></div>
                        </div>

                     
                        <div class="form-group filled">
                          <label class="control-label">Store Type</label>
                          <select class="form-control" id="storeType" data-error="This field is required">
                            <option value="${supplier.supplierShop.store.storeId}">${supplier.supplierShop.store.storeType}</option>
                          </select>
                          <div class="help-block with-errors validationText" id="storeTypeValidate"></div>
                        </div>

                        <legend>Store Opening-Closing days and time</legend>

                         <div class="form-group">
                        <div class="checkbox" id="tab1" onchange="return checkBoxes();">
                        <ul class="days" >
                          <li>
                          <label>
                            <input type="checkbox" id="checkAll" onchange="enableNDisableAll();"> All </label>  
                          </li>
                          <li>
                          <label>
                            <input type="checkbox" name="workingdays" id="sunCheck" class="checkbox1" <c:if test="${ supplier.supplierShop.sunday}">checked="checked"</c:if> value="sunday"> Sun </label> 
                          </li>
                          <li>
                          <label>
                            <input type="checkbox" name="workingdays" id="monCheck" class="checkbox1"<c:if test="${ supplier.supplierShop.monday}">checked="checked"</c:if>value="monday"> Mon </label> 
                          </li>
                          <li>
                          <label>
                            <input type="checkbox" name="workingdays" id="tueCheck" class="checkbox1" <c:if test="${ supplier.supplierShop.tuesday}">checked="checked"</c:if> value="tuesday"> Tue </label> 
                          </li>
                          <li>
                          <label>
                            <input type="checkbox" name="workingdays" id="wedCheck" class="checkbox1" <c:if test="${ supplier.supplierShop.wednesday}">checked="checked"</c:if> value="wednesday"> Wed </label> 
                          </li>
                          <li>
                          <label>
                            <input type="checkbox" name="workingdays" id="thuCheck" class="checkbox1" <c:if test="${ supplier.supplierShop.thursday}">checked="checked"</c:if> value="thursday"> Thur </label>  
                          </li>
                          <li>
                          <label>
                            <input type="checkbox" name="workingdays" id="friCheck" class="checkbox1" <c:if test="${ supplier.supplierShop.friday}">checked="checked"</c:if> value="friday"> Fri </label> 
                          </li>
                          <li>
                          <label>
                            <input type="checkbox" name="workingdays" id="satCheck" class="checkbox1" <c:if test="${ supplier.supplierShop.saturday}">checked="checked"</c:if> value="saturday"> Sat </label> 
                          </li>
                        </ul>
                      </div>
                      <div class="help-block with-errors validationText"  id="checkBoxValidate"></div>
                      </div>

                      <div class="row">
                        <div class="col-md-6">
                              <div class="form-group filled">
                              <input type="hidden" id="openingHidden" value="${supplier.supplierShop.shopOpeningTime}">
                              <input type="hidden" id="closingHidden" value="${supplier.supplierShop.shopClosingTime}">
                          <label class="control-label">Opening Time</label>
                          <select class="form-control" id="openingTime" name="openingtime" value="${supplier.supplierShop.shopOpeningTime}" required="" data-error="This field is required">
                            <option value="06:00 AM">6 AM</option>
                            <option value="07:00 AM">7 AM</option>
                            <option value="08:00 AM">8 AM</option>
                            <option value="09:00 AM">9 AM</option>
                            <option value="10:00 AM">10 AM</option>
                            <option value="11:00 AM">11 AM</option>
                            <option value="12:00 PM">12 PM</option>
                            <option value="01:00 PM">1 PM</option>
                            <option value="02:00 PM">2 PM</option>
                            <option value="03:00 PM">3 PM</option>
                            <option value="04:00 PM">4 PM</option>
                            <option value="05:00 PM">5 PM</option>
                            <option value="06:00 PM">6 PM</option>
                            <option value="07:00 PM">7 PM</option>
                            <option value="08:00 PM">8 PM</option>
                            <option value="09:00 PM">9 PM</option>
                            <option value="10:00 PM">10 PM</option>
                            <option value="11:00 PM">11 PM</option>
                            <option value="12:00 AM">12 AM</option>
                            <option value="01:00 AM">1 AM</option>
                          </select>
                          <div class="help-block with-errors"></div>
                        </div>
                        </div>

                        <div class="col-md-6">
                              <div class="form-group filled">
                          <label class="control-label">Closing Time</label>
                          <select class="form-control" id="closingTime" onchange="return setTiming();" name="closingtime" required="" data-error="This field is required">
                          <option value="06:00 AM">6 AM</option>
                            <option value="07:00 AM">7 AM</option>
                            <option value="08:00 AM">8 AM</option>
                            <option value="09:00 AM">9 AM</option>
                            <option value="10:00 AM">10 AM</option>
                            <option value="11:00 AM">11 AM</option>
                            <option value="12:00 PM">12 PM</option>
                            <option value="01:00 PM">1 PM</option>
                            <option value="02:00 PM">2 PM</option>
                            <option value="03:00 PM">3 PM</option>
                            <option value="04:00 PM">4 PM</option>
                            <option value="05:00 PM">5 PM</option>
                            <option value="06:00 PM">6 PM</option>
                            <option value="07:00 PM">7 PM</option>
                            <option value="08:00 PM">8 PM</option>
                            <option value="09:00 PM">9 PM</option>
                            <option value="10:00 PM">10 PM</option>
                            <option value="11:00 PM">11 PM</option>
                            <option value="12:00 AM">12 AM</option>
                            <option value="01:00 AM">1 AM</option>
                          </select>
                          <div class="help-block with-errors"></div>
                        </div>
                        </div>

                      </div>

                        <div class="form-group">
                          <button type="submit" onclick="spinner()" class="btn btn-primary">Submit</button>
                          <button type="reset" class="btn btn-default">Reset</button>
                          <div class="col-md-4" id="spinner" ></div>
                        </div>
                      </fieldset>
                    </form>
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
  openAt = ($('#openingHidden').val());
  $('#openingTime').val(openAt);
  closeAt = ($('#closingHidden').val());
  $('#closingTime').val(closeAt);
  </script>
<%@ include file="../master/footer.jsp" %>
<script src="<c:url value='assets/js/myFormValidation.js' />"></script>    

<script type="text/javascript">
  function enableNDisableAll()
{
  if($('#checkAll').is(':checked'))
  {

    $("input[id='sunCheck']").prop('checked', true);
    $("input[id='monCheck']").prop('checked', true);
    $("input[id='tueCheck']").prop('checked', true);
    $("input[id='wedCheck']").prop('checked', true);
    $("input[id='thuCheck']").prop('checked', true);
    $("input[id='friCheck']").prop('checked', true);
    $("input[id='satCheck']").prop('checked', true);
  }
  else
  {
    $("input[id='sunCheck']").prop('checked', false);
    $("input[id='monCheck']").prop('checked', false);
    $("input[id='tueCheck']").prop('checked', false);
    $("input[id='wedCheck']").prop('checked', false);
    $("input[id='thuCheck']").prop('checked', false);
    $("input[id='friCheck']").prop('checked', false);
    $("input[id='satCheck']").prop('checked', false);
  }
}


function validateProfileForm(){
      var myName=$('#nameFull').val();
      var myEmailId=$('#emailId').val();
      var myContactno=$('#contactNo').val();
      var myShopName=$('#shopName').val();
      var myShopContactNo=$('#shopContactNo').val();
      var myAddressLine=$('#addressLine1').val();
      var myCity=$('#city').val();
      var myStoreType=$('#storeType').val();     
      mobileLength();
      phoneLength();
      checkBoxes();
      $("#nameFullValidate").text("Please input username").hide();
            $("#nameFull").css("border-bottom", "1px "); 
      $("#emailIdValidate").text("Please input username").hide();
            $("#emailId").css("border-bottom", "1px  ");
        $("#contactNoValidate").text("Please input username").hide();
            $("#shopContactNo").css("border-bottom", "1px  ");
        $("#shopNameValidate").text("Please input username").hide();
            $("#shopName").css("border-bottom", "1px  ");
        $("#shopContactNoValidate").text("Please input username").hide();
            $("#shopContactNo").css("border-bottom", "1px ");
        $("#addressLine1Validate").text("Please input username").hide();
            $("#addressLine1").css("border-bottom", "1px  ");
            $("#cityValidate").text("Please input username").hide();
            $("#city").css("border-bottom", "1px  ");
            $("#storeTypeValidate").text("Please input username").hide();
            $("#storeType").css("border-bottom", "1px  ");

      if(myName==''){ 
        $("#nameFullValidate").text("Please input your name").show();
            $("#nameFull").css("border-bottom", "2px  solid #B71C1C");
            $("#nameFull").focus();
            return false;
      }
            if(myEmailId==''){
        $("#emailIdValidate").text("Please input your email id").show();
            $("#emailId").css("border-bottom", "2px  solid #B71C1C");
            $("#emailId").focus();
            return false;
      }
            if(myContactno==''){
        $("#contactNoValidate").text("Please input Contact no").show();
            $("#shopContactNo").css("border-bottom", "2px  solid #B71C1C");
            $("#shopContactNo").focus();
            return false;
      }
            if(myShopName==''){
        $("#shopNameValidate").text("Please input Your Shop name").show();
            $("#shopName").css("border-bottom", "2px  solid #B71C1C");
            $("#shopName").focus();
            return false;
      }
            if(myShopContactNo==''){
            $("#shopContactNoValidate").text("Please input your contact no.").show();
            $("#shopContactNo").css("border-bottom", "2px  solid #B71C1C");
            $("#shopContactNo").focus();
            return false;
      }
            if(myAddressLine==''){
            $("#addressLine1Validate").text("Please input your Address").show();
            $("#addressLine1").css("border-bottom", "2px  solid #B71C1C");
            $("#addressLine1").focus();
            return false;
      }
            if(myCity==''){
            $("#cityValidate").text("Please input your City name").show();
            $("#city").css("border-bottom", "1px  solid #B71C1C");
            $("#city").focus();
            return false;
      }
            if(myStoreType==''){
            $("#storeTypeValidate").text("Please Select Store Type").show();
            $("#storeType").css("border-bottom", "1px  solid #B71C1C");
            $("#storeType").focus();
            return false;
      }

      if(mobileLength()){ 
          if(phoneLength()){
            if(checkBoxes()){
            return true;
          }
          }

      }

      $("#spinner").empty();
      return false;
}

  function validateEmail(email) { 
  
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function validates(){
  
  $("#emailIdValidate").text("");
  var email = $("#emailId").val();
  if (validateEmail(email)) {

    $("#emailIdValidate").text("Please input correct email address.").hide();
    $("#emailId").css("border-bottom", "2px  solid #fff");
            return true;
  } else {
    $("#emailIdValidate").text("Please input correct email address.").show();
    $("#emailId").css("border-bottom", "2px  solid #B71C1C");
              return false;
  }

}

function mobileLength(){
          // var mobNo=parseFloat($('#contactNo').val());
             var mobNo = document.getElementById("contactNo");
  if(mobNo.value.length < 10){ 
            $("#contactNoValidate").text("Mobile no must be atleast 10 digits.").show();
            $("#contactNo").css("border-bottom", "2px  solid #B71C1C");
            $("#contactNo").focus();
            return false; 
      }
  else{
    $("#contactNoValidate").text("Mobile no must be atleast 10 digits.").hide();
            $("#contactNo").css("border-bottom", "2px  ");
            return true; 
  }
}

function phoneLength(){
          // var phoneNo=parseFloat($('#shopContactNo').val());        
             var phoneNo = document.getElementById("shopContactNo");
             // if($(this).val().length > 1)
  if(phoneNo.value.length < 10){ 
            $("#shopContactNoValidate").text("Phone no must be atleast 10 digits.").show();
            $("#shopContactNo").css("border-bottom", "2px  solid #B71C1C");
            $("#shopContactNo").focus();
            return false; 
      }
  else{
    $("#shopContactNoValidate").text("Phone no must be atleast 10 digits.").hide();
            $("#shopContactNo").css("border-bottom", "2px  ");
            return true; 
  }
}

function checkCheckBoxes(theForm) {
  if (
  theForm.workingdays.checked == false &&
  theForm.workingdays.checked == false ) 
  {
    alert ('You didn\'t choose any of the checkboxes!');
    return false;
  } else {  
    return true;
  }
}

function checkBoxes(){
        i=0;
      if($('#sunCheck').is(':checked')){
          i++;
      }
      if($('#monCheck').is(':checked')){
          i++;
      }
      if($('#tueCheck').is(':checked')){
          i++;
      }
      if($('#wedCheck').is(':checked')){
          i++;
      }
      if($('#thuCheck').is(':checked')){
          i++;
      }
      if($('#friCheck').is(':checked')){
          i++;
      }
      if($('#satCheck').is(':checked')){
          i++;
      }

       if(i>=2){
        $("#checkBoxValidate").text("Select atleast two days.").hide();
        $("#tab1").css("border-bottom", "2px ");
        return true;
       }
      else{
                $("#checkBoxValidate").text("Select atleast two days.").show();
                $("#tab1").css("border-bottom", "2px  solid #B71C1C");
                $("#tab1").focus();
                return false;
          }
}

// function setTiming(){
//   var openTime=$('#openingTime').val();
//   var closeTime=$('#closingTime').val();

//     if( openTime==12){}

// }

function spinner(){
         // $("#add-shop-btn").attr("disabled",true);
         // $("#").submit();
         $("#spinner").css("text-align","right");
         
        $("#spinner").append("<svg width='32px' height='32px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' class='uil-ring-alt'><rect x='0' y='0' width='100' height='100' fill='none' class='bk'></rect><circle cx='50' cy='50' r='40' stroke='#f0f0f0' fill='none' stroke-width='10' stroke-linecap='round'></circle><circle cx='50' cy='50' r='40' stroke='#e91e63' fill='none' stroke-width='6' stroke-linecap='round'><animate attributeName='stroke-dashoffset' dur='2s' repeatCount='indefinite' from='0' to='502'></animate><animate attributeName='stroke-dasharray' dur='2s' repeatCount='indefinite' values='150.6 100.4;1 250;150.6 100.4'></animate></circle></svg>");
        }
</script>

<script src="<c:url value='/assets/js/imageCroppie/jquery-1.10.2.min.js' />"></script>
<script src="<c:url value='/assets/js/imageCroppie/jquery.Jcrop.js' />"></script>
<script src="<c:url value='/assets/js/imageCroppie/jquery.SimpleCropperChangeSupplier.js' />"></script>

<script>
        // Init Simple Cropper
        $('.cropme').simpleCropper();
        // $('#secndCrop').simpleCropper2();
        
      </script>