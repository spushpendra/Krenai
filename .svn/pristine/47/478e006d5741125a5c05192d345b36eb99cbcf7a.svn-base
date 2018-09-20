<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  
<head>
    <link href="assets/css/vendors.min.css" rel="stylesheet" />
    <link href="assets/css/styles.min.css" rel="stylesheet" />
    <link rel="apple-touch-icon" sizes="57x57" href="<c:url value='/assets/img/favicon/apple-touch-icon-57x57.html' />">
    <script  src="http://maps.google.com/maps/api/js?sensor=true"></script> 
  </head>
  <body class="page-login" init-ripples="">
    <div class="center">
      <div class="card bordered z-depth-2" style="margin:0% auto; max-width:400px; max-height: none;">
        <div class="card-header">
          <div class="brand-logo">
            <img src="assets/img/logo_2.png"> </div>
        </div>

                    <c:if test="${shiroLoginFailure != null}">
                      <div class="alert alert-block alert-danger" >
                        <button data-dismiss="alert" class="close">
                          &times;
                        </button>
                        <i class="fa fa-times-circle"></i>&nbsp;
                        Username and Password are Mismatch. Please Try again..
                      </div>
                    </c:if>
        <div class="card-content trx_push_left">
          <div class="m-b-30">
            <div class="card-title strong pink-text">Login</div>
            <p class="card-title-desc"> Welcome to Krenai! Let's be closely now </p>
          </div>
          <form class="form-floating" method="post" onsubmit="return validateIndexPage();" action= "<c:url value="/login"/>">
            <div class="form-group">
              <label for="inputEmail" class="control-label">Email</label>
              <input type="text" class="form-control" name="username" onchange="return validate();" id="inputUsername">
              <div class="help-block with-errors validationText" id="inputUsernameValidation"></div>
               </div>
            <div class="form-group">
              <label for="inputPassword" class="control-label">Password</label>
              <input type="password" name="password" class="form-control" id="inputPassword" autocomplete="off"> 
              <div class="help-block with-errors validationText" id="inputPasswordValidation"></div>
              </div>
            <div class="form-group">
              <div class="checkbox">
                <label>
                  <input type="checkbox"> Remember me </label>
              </div>
            </div>
             <div class="card-action clearfix">
          <div class="pull-right">


           

            <div class="row">
              <div class="col-md-6">
                 <button type="submit" class="btn btn-link black-text">Forgot password</button>
              </div>
              <div class="col-md-4">
                <input id="sub-btn" class="btn btn-link black-text"  type="submit" value="Login" onclick="return userPasswardValue(this.value,this.id);spinner();Javascript:checkEmail();"/ >
              <input id="sub-btn-demo" style="display:none;" class="btn btn-link black-text"  type="button" value="Login" / >
              </div>
              <div class="col-md-2">
                <div id="spinner" ></div>
              </div>
           </div>

          </div>
        </div>
        
          </form>
        </div>
       
      </div>
    </div>
    
   
   
  </body>
	<script src="<c:url value='assets/js/vendors.min.js' />"></script>
    <script src="<c:url value='assets/js/app.min.js' />"></script>
     <script src="<c:url value='assets/js/myFormValidation.js' />"></script>    
    <script type="text/javascript">

    function validateEmail(email) { 
  
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function validate(){
  
  $("#inputUsernameValidation").text("");
  var email = $("#inputUsername").val();
  if (validateEmail(email)) {

    $("#inputUsernameValidation").text("Please input correct email address.").hide();
            $("#inputUsername").css("border-bottom", "1px ");
            return true;
  } else {
    $("#inputUsernameValidation").text("Please input correct email address.").show();
    $("#inputUsername").css("border-bottom", "1px  solid #FF0000");
    $("#inputUsername").focus();
              return false;
  }

}
function validateIndexPage(){
  $("#sub-btn").css("display","none");
  $("#sub-btn-demo").css("display","block");
  spinner();
  validate();
  userPasswardValue();
  if(validate()){
    if(userPasswardValue()){
      return true;
    }
  }
  $("#sub-btn-demo").css("display","none");
  $("#sub-btn-demo").css("display","block");
  $("#spinner").empty(300);
  return false;
}

    </script>

                   <script>
       function spinner(){
         // $("#add-shop-btn").attr("disabled",true);
         $("#add-product-form").submit();
         $("#spinner").css("text-align","right");
         
        $("#spinner").append("<svg width='32px' height='32px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' class='uil-ring-alt'><rect x='0' y='0' width='100' height='100' fill='none' class='bk'></rect><circle cx='50' cy='50' r='40' stroke='#f0f0f0' fill='none' stroke-width='10' stroke-linecap='round'></circle><circle cx='50' cy='50' r='40' stroke='#e91e63' fill='none' stroke-width='6' stroke-linecap='round'><animate attributeName='stroke-dashoffset' dur='2s' repeatCount='indefinite' from='0' to='502'></animate><animate attributeName='stroke-dasharray' dur='2s' repeatCount='indefinite' values='150.6 100.4;1 250;150.6 100.4'></animate></circle></svg>");
        }
             
       </script>
</html>