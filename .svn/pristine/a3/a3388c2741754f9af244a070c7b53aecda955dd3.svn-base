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
    <title>Bank Details - Krenai</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script charset="utf-8" src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <style type="text/css">
   </style>
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
            <div class="page-header">
              <h1>      <i class="md md-input"></i>    Update Website Store Name   </h1>
              <p class="lead">Select Unique store name to be viewed as your personal store website.<a href="https://www.krenai.com/" target="_blank">Your Store Website</a> </p>
            </div>
            <div class="row  m-b-40">
              <div class="col-md-12">
                <div class="well white" id="forms-validation-container">
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
                  <div>
                 
                      <fieldset>
                       
                        <div class="row">
                          <div class="col-md-11">
                             <legend>Store Details</legend>
                          </div>
                          <div class="col-md-1"></div>
                        </div>
                        <div class="row">
                          <div class="col-md-11">
                            <div class="form-group">
                              <label class="control-label">Store Name</label>
                              <input type="text" class="form-control"  value="${loggedUser.supplierShop.shopName}" disabled="disabled" >
                              <div class="help-block with-errors"></div>
                            </div>
                          </div>
                          <div class="col-md-1"></div>
                        </div>

                         <div class="row">
                          <div class="col-md-9">
                            <div class="form-group web-input">
                              <label class="control-label">Enter Desired Website Address</label>
                              <input type="text" class="form-control" id="web" required="" value="www.krenai.com/${loggedUser.uniqueStoreName}" >
                              <div class="help-block with-errors"></div>
                            </div>
                          </div>
                          
                          <div class="col-md-3">
                          	<div class="form-group">
                          	<button class="btn btn-primary" style="margin-top: 30px;" onclick="verify();">Verify</button>
							<button class="btn btn-primary" style="margin-top: 30px;" onclick="update();">Update</button>
							</div>
                          </div>
                        </div>

					<div class="form-group sug" style="display:none;">
                        <label class="control-label">Choose From Available Suggestions</label>
						<div class="checkbox" id="tab1" >
                       		 <ul class="suggestions" style="list-style: none;"> 
                       		 </ul>
                      </div>
                      <div class="help-block with-errors validationText"  id="checkBoxValidate"></div>
                    </div>
						
                        
                           
                        
                          <div class="col-md-1"></div>
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
  $("#web").keydown(function(e) {
	  var oldvalue=$(this).val();
	  var field=this;
	  setTimeout(function () {
	    if(field.value.indexOf('www.krenai.com/') !== 0) {
	      $(field).val(oldvalue);
	    } 
	  }, 0);
	});
  $("input[name='suggestion']").on('ifChecked', function(event){
 		alert();
 		});
   $("input[name='suggestion']").on('ifUnchecked', function(event){
		alert();
 		});
  function verify(){
	  var oldvalue=$("#web").val().substring(15);
	  if(oldvalue.length>4){
		  for (i=0; i < oldvalue.length+15; i++) {
			    $("#web").val($("#web").val().replace(' ','_'));
			}
		  $("#web").next().html("<svg width='20px' height='20px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' class='uil-ring-alt'><rect x='0' y='0' width='100' height='100' fill='none' class='bk'></rect><circle cx='50' cy='50' r='40' stroke='#f0f0f0' fill='none' stroke-width='10' stroke-linecap='round'></circle><circle cx='50' cy='50' r='40' stroke='#e91e63' fill='none' stroke-width='6' stroke-linecap='round'><animate attributeName='stroke-dashoffset' dur='2s' repeatCount='indefinite' from='0' to='502'></animate><animate attributeName='stroke-dasharray' dur='2s' repeatCount='indefinite' values='150.6 100.4;1 250;150.6 100.4'></animate></circle></svg>");
			 $.ajax({
			   url: "/supplier/store/findweb",
			   type: "POST",
			   data: {
				   "or_": oldvalue
			   },
			   success: function(data){
				   if(data=="available"){
					  
					   $("#web").next().html("<small style='color:green;'>Available</small>");
				   }
				   else{
					   $("#web").next().html("<small style='color:red;'>Not Available.</small>");
					   if(data.length>0){
						   $(".sug").css("display","block");
						   $(".suggestions").empty();
						   for(i=0; i<data.length; i++){
							  //alert(data[i]);
							  $(".suggestions").append("<li> <label> <input type='checkbox' name='suggestion' class='checkbox1'  value='sunday'> "+data[i]+" </label> </li>");
						  }
					   }
				   }
			   },
			   error: function(data){
				   
			   }
		   });
	  }
	  else{
		  $("#web").next().html("<small style='color:red;'>Enter More Than 4 Characters</small>");
	  }
  }
  
  function update(){
	  var oldvalue=$("#web").val().substring(15);
	  for (i=0; i < oldvalue.length+15; i++) {
		    $("#web").val($("#web").val().replace(' ','_'));
		}
	  if(oldvalue.length>4){
		  $("#web").next().html("<svg width='20px' height='20px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' class='uil-ring-alt'><rect x='0' y='0' width='100' height='100' fill='none' class='bk'></rect><circle cx='50' cy='50' r='40' stroke='#f0f0f0' fill='none' stroke-width='10' stroke-linecap='round'></circle><circle cx='50' cy='50' r='40' stroke='#e91e63' fill='none' stroke-width='6' stroke-linecap='round'><animate attributeName='stroke-dashoffset' dur='2s' repeatCount='indefinite' from='0' to='502'></animate><animate attributeName='stroke-dasharray' dur='2s' repeatCount='indefinite' values='150.6 100.4;1 250;150.6 100.4'></animate></circle></svg>");
		  $.ajax({
			   url: "/supplier/store/webname/update",
			   type: "POST",
			   data: {
				   "or_": oldvalue
			   },
			   success: function(data){
				   if(data!="failed"){
					   
					   $("#web").next().html("<small style='color:green;'>"+data+" is updated</small>");
				   }
				   else{
					   $("#web").next().html("<small style='color:red;'>Not Available.</small>");
				   }
				 
			   },
			   error: function(data){
				   
			   }
		   });
	  }
	  else{
		  $("#web").next().html("<small style='color:red;'>Enter More Than 4 Characters</small>");
	  }
  }
   </script>
  <%@ include file="../master/footer.jsp" %>