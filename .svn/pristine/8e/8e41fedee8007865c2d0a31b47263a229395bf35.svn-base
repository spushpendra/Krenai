<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Request Category - Krenai</title>
  <link href="<c:url value='/assets/css/profile.update.css' />" rel="stylesheet" />
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
          <section class="forms-advanced forms-validation">
            <div class="page-header">
		
		
               <div class="row">
                    <div class="col-md-6">
                      <h1>      <i class="md md-input"></i>      Create New Category    </h1>
                       <p class="lead"> Create new Category that is not listed in krenai till now  </p>
                    </div>

                    <div class="col-md-6">
                         <a href="<c:url value="/product/addsubcategory"/>"> <button class="btn btn-success btn-border"><i class="md-cloud-upload"> </i> Request New Sub-Category</button></a> 
                        <a href="<c:url value="/products/add"/>"> <button class="btn btn-success btn-border"><i class="md-cloud-download"> </i> Add New Product</button></a>
                          
                    </div>
                </div>

            </div>

			<!-- 
            <div class="row m-b-40" id="fileupload">
              
              <div class="col-md-12">
                <div class="well white">
                   <fieldset>
                      <legend class="m-b-10">Important File upload</legend>
                      <div>
                         <div class="row">
                           <div class="col-md-4">
                             <div class="form-group">
                                <div class="drop-box btn-flat btn-info fileinput-button">
                                <p class="text-success"><i class="btn btn-lg btn-round btn-success "><span class="md md-add"></span></i>&nbsp;</p>
                                <input type="file" name="category-image" accept=".jpg,.jpeg,.png,.gif" class="fileupload" multiple>
                                </div>
                              </div>
                           </div>
                           <div class="col-md-8">

                             
                           </div>
                           
                         </div>
                        <ul style="clear:both" class="response list-unstyled"></ul>
                      </div>
                    </fieldset>
                  
                </div>
              </div>
            </div>
            -->

			

            <div class="row  m-b-40">
              <div class="col-md-12">
                <div class="well white">
                
                <c:if test="${not empty errorMessage}">
			<div class="row">
              <div class="col-lg-12">
                <div class="bs-component">
                  <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <h4>Error!</h4>
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
			
                  <form class="form form-floating" action="<c:url value="/product/addcategory/requested"/>" onsubmit="return validateTrackRequest();" method="post" enctype="multipart/form-data">
                   
                    <fieldset>
                      <legend>New Category Details</legend>
                      <div class="form-group">
                        <label for="inputEmail" class="control-label">Category Name</label>
                        <input type="text" class="form-control" name="category-name" id="categSub" maxlength="200" onkeyup="$(this).val($(this).val().replace(/[^A-Z/a-z/_/@/&/(/)/ ]/g, ''));categoryName();"> 
                        <div class="help-block with-errors validationText" id="categSubValidate"></div>
                      </div>

                      <div class="form-group">
                        <label for="textArea" class="control-label">Description about product - Minimum 10 words</label>
                        <textarea name="product-description" class="form-control vertical" onkeyup="countingChr();" maxlength="1000" rows="3" id="textArea"></textarea> 
                        <div class="help-block with-errors validationText" id="textAreaValidation"></div>
                      </div>
					 
                      <div class="form-group">
                        <label class="control-label normal">Is Service </label>
                        <div class="radio">
                          <label>
                            <input type="radio" name="isService" id="optionsRadios1" value="product" checked="">Product</label>
                        </div>
                        <div class="radio">
                          <label>
                            <input type="radio" name="isService" id="optionsRadios2" value="service">Service
                          </label>
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <label class="control-label normal">Category </label>
                        <div class="radio">
                          <label>
                            <input type="radio" name="trade-type" id="optionsRadios1" value="supplier" checked="">Supplier</label>
                        </div>
                        <div class="radio">
                          <label>
                            <input type="radio" name="trade-type" id="optionsRadios2" value="manufacturer">Manufacturer </label>
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <label class="control-label normal">Sell Now</label>
                        <div class="switch">
                          <label> No
                            <input type="checkbox" name="availability" checked="checked" value="yes"> <span class="lever"></span> Yes </label>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="col-md-4" id="spinner" ></div>
                            <button type="submit" id="add-shop-btn" class="btn btn-primary">Submit</button>
                            <button type="reset" class="btn btn-default">Cancel</button>
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
    
    <script type="text/javascript">



                            var text_max = 1000;
                            $('#textAreaValidation').text('1000 characters left');

                              function countingChr(){

                                  var max = 1000;
                                  var min = 10;
                                    var len = $.trim($('#textArea').val()).length;
                                    if (len <= min) {
                                        $('#textAreaValidation').text(' Input 10 to 1000 characters for Product Description. ');
                                        $('#textAreaValidation').css("color","#B71C1C");
                                    }

                                    else if(len >= max){
                                      $('#textAreaValidation').text(' You have reached the limit');
                                       $('#textAreaValidation').css("color","#B71C1C");
                                    }

                                    else {
                                        var ch = max - len;
                                        $('#textAreaValidation').text(ch + ' characters left');
                                         $('#textAreaValidation').css("color","#37B50D");
                                    }


                                // var text_length = $('#product-description').val().length;
                                // var text_remaining = text_max - text_length;

                                // $('#product-descriptionValidation').html(text_remaining + ' characters remaining');
                              }




    </script>


    <script type="text/javascript">
                          function categoryName(){
                            var catog=$('#categSub').val();
                            if(catog==''){
                        $("#categSubValidate").text("Please input Category Name").show();
                            $("#categSub").css("border-bottom", "2px  solid #B71C1C");
                            $("#categSub").focus();
                            return false;
                        }
                      else{
                        $("#categSubValidate").text("Please input Category Name").hide(300);
                            $("#categSub").css("border-bottom", "2px  ");
                            return true;

                            }
                          }
                          function descriptionProd(){
                            var categ=$('#textArea').val();
                            
                            
                            if(categ==''){
                                  $("#textAreaValidation").text("Please input description about category Name").show();
                                  $("#textArea").css("border-bottom", "2px  solid #B71C1C");
                                  $("#textArea").focus();
                                  return false;
                              }
                      else{
                        $("#textAreaValidation").text("Please input Category Name").hide();
                            $("#textArea").css("border-bottom", "2px  ");
                            return true;

                            }
                          }

                          function descValue(){
                             
                            var prddd = $.trim($('#textArea').val());
                                if( prddd.length ==0 )
                                {
                                
                                  $("#textAreaValidation").text("Input 10 to 1000 characters .").show();

                                  $("#textArea").css("border-bottom", "2px  solid #FF0000");
                                  $("#textArea").focus();
                                  return false;
                                }

                                else if(prddd.length < 10){
                                  $("#textAreaValidation").text("Input 10 to 1000 characters .").show();

                                  $("#textArea").css("border-bottom", "2px  solid #FF0000");
                                  $("#textArea").focus();
                                  return false;
                                }

                                else if(prddd.length > 1000){
                                  $("#textAreaValidation").text("Input 10 to 1000 characters .").show();

                                  $("#textArea").css("border-bottom", "2px  solid #FF0000");
                                  $("#textArea").focus();
                                  return false;
                                }

                                else{
                                    $("#textAreaValidation").text("Image upload.").hide();

                                    $("#textArea").css("border-bottom", "2px ");

                                    return true;
                                }

                          }

  



                          function validateTrackRequest(){
                        	  spinner();
                            categoryName();
                            descriptionProd();
                            descValue();
                            if(categoryName()){
                              if(descriptionProd()){
                                if(descValue()){
                                  $("#add-shop-btn").attr("disabled",true);
                                return true;
                              }
                            }
                            }
                            $("#add-shop-btn").attr("disabled",false);
                            $("#spinner").empty();

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
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
  <%@ include file="/master/footer.jsp" %>      