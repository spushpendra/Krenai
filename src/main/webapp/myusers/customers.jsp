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
          <section class="tables-data todo-app">
            <div class="page-header">
              <h1>      <i class="md md-list"></i>     My Neighbour    </h1>
              <!-- <p class="lead"><a href="#" target="_blank">Find your closers:</a> Yaha par sharma ji ki ladki bhi hai.</p> -->
            </div>

             <div class="col-md-12 filters well white" style="min-height: 0px;">
                <div class="btn-group" role="group" aria-label="Filter todo's">
                  <a href="<c:url value="/dashboard/my-users"/>"><button class="btn btn-default filter <c:if test="${underline == 'subscribers'}">active </c:if> ">My Subscribers </button>
                   <span class="badge blue">${allUsersCount}</span>
                  </a>
                  <a href="<c:url value="/dashboard/my-users/recent"/>"><button class="btn btn-default filter <c:if test="${underline == 'recent'}">active </c:if> ">New Requests</button>
                    <span class="badge orange">${recentlyAddedUsersCount}</span>
                  </a>
                  <a href="<c:url value="/dashboard/my-users/vendee?ref=customers"/>"><button class="btn btn-default filter <c:if test="${underline == 'vendee'}">active </c:if> ">Transaction Users</button>
                    <span class="badge light-green"></span>
                  </a>
                  <!-- <a href="total-orders-null.html"><button class="btn btn-default filter">Society Groups</button>
                    <span class="badge pink">5</span>
                  </a> -->
                  <a href="/dashboard/user/find"><button class="btn btn-default filter">Find More</button></a>
                </div>
              </div>


           <div class="row">
           <c:forEach items="${userFollowList}" var="userFollow">
	              <div class="col-md-3">
	                <div class="card bordered">
	                  <div class="card-content" style="max-height: 140px;min-height: 140px;text-align: center;">
	                    <img src="${userFollow.user.profileImageUrl}"alt="${userFollow.user.firstName}" style="width:100px;    border-radius: 50%;">
	                  </div>
	                  <div class="address-neighbour">
	                  <h4 style="text-align: center;">${userFollow.user.firstName} ${userFollow.user.lastName}</h4>
	                  <p  style="text-align: center;max-height: 23px;overflow: hidden;"><i class="md-pin-drop"></i>
	                  ${userFollow.user.defaultUserAddressBook.googleAddress}</p>
	                  </div>
	                  <div class="card-action clearfix"> <a href="#" class="btn btn-link btn-icon"><i class="md md-favorite "></i></a> 
	                   <div class="pull-right"> <a href="#" class="btn-icon" data-toggle="dropdown"><i class="md md-more-vert"></i>
		                    <ul class="dropdown-menu dropdown-menu-right">
		                          <li role="presentation"><a href="#">Send Message</a></li>
		                          <li><a href="#">Share Products</a></li>
		                          <li class="divider"></li>
		                          <li><a href="#">Remove</a></li>
		                    </ul>
		                   <div class="ripple-wrapper"></div></a> 
		               </div>
	                  </div>
	                </div>
	              </div>
              </c:forEach>

            </div>
          </section>
          <div class="footer-buttons">
            <div class="btn btn-primary btn-round btn-lg" data-title="Find More" data-toggle="tooltip"><i class="md md-add"></i></div>
            </div>
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
</script>