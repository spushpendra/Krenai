<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Todo - Materialism</title>
    <link href="assets/css/vendors.min.css" rel="stylesheet" />
    <link href="assets/css/styles.min.css" rel="stylesheet" />
    <link href="assets/css/push.css" rel="stylesheet" />
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
          <section class="todo-app forms-validation">
            <div class="page-header">
              <h1>      <i class="md md-camera"></i>Change Password</h1>
              <p class="lead">${passwordIncorectMessage} </p>
            </div>
            <div class="change-password-body row well white">
            <c:if test="${not empty passwordIncorectMessage}">
			<div class="row">
              <div class="col-lg-12">
                <div class="bs-component">
                  <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <h4>Warning!</h4>
                    <p>${passwordIncorectMessage}</p>
                  </div>
                </div>
              </div>
            </div>
			</c:if>
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
			
              <div class="col-md-12">

                <form class="form-floating" action="<c:url value="/profile/password/requested"/>" onsubmit="return changePassword();" method="post">  
              <div class="row">
              <div class="col-md-6">
                
                   <div class="form-group">
                      <label class="control-label">New Password</label>
                      <input name="newPassword" type="password" class="form-control" id="newPasswordId" data-error="This field is required">
                      <div class="help-block with-errors validationText" id="newPasswordIdValidation"></div>
                    </div>
              </div>
              <div class="col-md-6">
                 <div class="form-group">
                      <label class="control-label">Confirm Password</label>
                      <input type="password" class="form-control" id="confirmPassword" data-error="This field is required">
                      <div class="help-block with-errors validationText" id="confirmPasswordValidation"></div>
                  </div>
               
              </div>
              </div>

                <div class="row">
              <div class="col-md-6">
                
               <div class="form-group">
                  <label class="control-label">Current Password</label>    
                  <input type="password" class="form-control" id="oldPasswordId" name="oldPassword">
                  <div class="help-block with-errors validationText" id="oldPasswordIdValidation"></div>
                </div>
                  
              </div>
              <div class="col-md-6">

                 <div class="checkbox-inline">
                    <label>
                      <input type="checkbox" id="loginKeepMe" name="keepme-loggedin"> Keep me Login? </label>
                  </div>

                <button type="submit" class="btn btn-primary" id="btnChangePswd">Change<i class="md md-send"></i></button>

                
              </div>
      
              </div>

              </form>
              
<!--               <form action="<c:url value='/login'/>" method="post" id="loginForm">

              </form>
 -->
              </div>
              
            </div>
          </section>
        </div>
      </div>
    </main>
    
    <script type="text/javascript">

        function changePasswardValue(id){ 
            var paswrd=$("#"+id).val();
               // var textbox = document.getElementById("pass-user-login");
               // var textbox2 = document.getElementById("passwordEnter");
            $("#"+id+"Validation").text("pass-user-login").hide();
            $("#"+id).css("border-bottom", "2px  ");
            if(paswrd=='')
            {
              $("#"+id+"Validation").text("Please input Password.").show();
              $("#"+id).css("border-bottom", "2px  solid #B71C1C");
              $("#"+id).focus();
              return false; 
            }
            if(paswrd.length < 8){
              $("#"+id+"Validation").text("Password length must be atleast 8 characters.").show();
              $("#"+id).css("border-bottom", "2px  solid #B71C1C");
              $("#"+id).focus();
              return false; 
            }
            else{
              $("#"+id+"Validation").text("Password length must be atleast 8 characters.").hide(300);
              return true;
            }
        }

        function mismatchPassword(){
          var newPswd = $("#newPasswordId").val();
          var confPswd = $("#confirmPassword").val();
          if(newPswd != confPswd){
              $("#confirmPasswordValidation").text("New password and confirm password are mismatch.").show();
              $("#confirmPassword").css("border-bottom", "2px  solid #B71C1C");
              $("#confirmPassword").focus();
              return false;
          }
          else{
              $("#confirmPasswordValidation").text("New password and confirm password are mismatch.").hide(300);
              $("#confirmPassword").css("border-bottom", "");
              return true; 
          }
        }

        function changePassword(){
          var newPwd = changePasswardValue('newPasswordId');
          var confPwd = changePasswardValue('confirmPassword');
          var oldPwd = changePasswardValue('oldPasswordId');
          mismatchPassword();

              if(newPwd && confPwd && oldPwd && mismatchPassword()){
                return true;
                $("#btnChangePswd").attr("disabled",true);
              }
              else{
                return false;
                $("#btnChangePswd").attr("disabled",false);
              }
        }
    </script>

  <%@ include file="../master/footer.jsp" %>