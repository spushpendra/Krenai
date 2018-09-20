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
              <h1>      <i class="md md-input"></i>      official Bank Details    </h1>
              
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
                  <c:if test="${empty bankDetails}">
                    <form action="<c:url value="/bank/details/update"/>" method="post" class="form-floating" id="form-validation">
                     </c:if>
                      <fieldset>
                       
                        <div class="row">
                          <div class="col-md-11">
                             <legend>Bank Details</legend>
                          </div>
                          <div class="col-md-1"></div>
                        </div>
                        <div class="row">
                          <div class="col-md-11">
                            <div class="form-group">
                              <label class="control-label">Bank Name</label>
                              <input type="text" class="form-control" required="" name="bankName" value="${bankDetails.bankName}"onkeyup="$(this).val($(this).val().replace(/[^A-Z/a-z/ ]/g, ''));" <c:if test="${not empty bankDetails}">readonly=""</c:if> >
                              <div class="help-block with-errors"></div>
                            </div>
                          </div>
                          <div class="col-md-1"></div>
                        </div>

                         <div class="row">
                          <div class="col-md-11">
                            <div class="form-group">
                              <label class="control-label">Branch</label>
                              <input type="text" class="form-control" required="" name="branchName" value="${bankDetails.branchName}" onkeyup="$(this).val($(this).val().replace(/[^A-Z/a-z/ ]/g, ''));"  <c:if test="${not empty bankDetails}">readonly=""</c:if> >
                              <div class="help-block with-errors"></div>
                            </div>
                          </div>
                          <div class="col-md-1"></div>
                        </div>

                         <div class="row">
                            <div class="col-md-11">
                              <div class="form-group">
                                <label class="control-label">Account Number</label>
                                <input type="text" class="form-control" name="accountNo" value="${bankDetails.accountNo}" required="" onkeyup="$(this).val($(this).val().replace(/[^0-9/ ]/g, ''));" <c:if test="${not empty bankDetails}">readonly=""</c:if> >
                                <div class="help-block with-errors"></div>
                              </div>
                            </div>
							
                            <div class="col-md-1">
                            <c:if test="${bankDetails.status.statusValue == 'verified'}">
                              <i class="md  md-done-all f20 btn btn-round btn-flat btn-success" data-title="Verified"></i>
                              </c:if>
                              <c:if test="${bankDetails.status.statusValue == 'not verified'}">
                              <i class="md md-close f20 btn btn-round btn-flat btn-warning"  data-title="Not Verified"></i>
                              </c:if>    
                            </div>
                        </div>

                            <div class="row">
                            <div class="col-md-11">
                              <div class="form-group">
                                <label class="control-label"> Account Holder Name</label>
                                <input type="text" class="form-control"  name="acountHolderName" value="${bankDetails.acountHolderName}" required="" onkeyup="$(this).val($(this).val().replace(/[^A-Z/a-z/ ]/g, ''));" <c:if test="${not empty bankDetails}">readonly=""</c:if> >
                                <div class="help-block with-errors">
                              </div>
                            </div>
                           </div>
              
                            <div class="col-md-1">
                           
                            </div>
                        </div>

                        <div class="row">
                          <div class="col-md-11">
                              <div class="form-group">
                                <label class="control-label"> IFSC CODE</label>
                                <input type="text" class="form-control"  name="ifscCode" value="${bankDetails.ifscCode}" required="" data-error="This field is required" <c:if test="${not empty bankDetails}">readonly=""</c:if> >
                                <div class="help-block with-errors">
                              </div>
                        </div> 
                          </div>
                          <div class="col-md-1"></div>
                        </div>
                         <div class="row">
                           <div class="col-md-11">
                             <legend>VAT/TIN Number</legend>
                           </div>
                           <div class="col-md-1">
                             
                           </div>
                         </div>

                          <div class="row">
                          <div class="col-md-11">
                               <div class="form-group">
                                <label class="control-label normal">Do you have valid VAT/TIN number?</label>
                                <div class="switch">
                                  <label class="filled"> No 
                                    <input type="checkbox" value="false" id="haveTIN-VAT" onchange="changeHaveTin();"> <span class="lever"></span> Yes </label>
                                </div>
                              </div>
                          </div> 
                          <div class="col-md-1"></div>
                        </div>
                        
                        <div class="row">
                          <div class="col-md-11">
                                <div class="form-group" id="vat-tin-no" style="display:none;">
                                  <label class="control-label">VAT/TIN Number</label>
                                  <input type="text" class="form-control"  name="tinORvatNo" value="${bankDetails.tinORvatNo}" required="" data-error="That email address is invalid" <c:if test="${not empty bankDetails}">readonly=""</c:if> >
                                  <div class="help-block with-errors">
                                </div>
                        </div>
 
                          </div>
                          <div class="col-md-1"></div>
                        </div>
                      
                         <div class="row">
                          <div class="col-md-11">
                             <div class="form-group">
                                <label class="control-label">Address Proof</label>
                                <input type="text" class="form-control"  name="addressProofDocumentNo" value="${bankDetails.addressProofDocumentNo}" required="" data-error="This field is required" <c:if test="${not empty bankDetails}">readonly=""</c:if> >
                                <div class="help-block with-errors"></div>
                              </div>
                              <c:if test="${empty bankDetails}">
                              <div class="form-group">
                                <button type="submit" class="btn btn-primary">Submit</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                              </div>
                              </c:if>   
                          </div> 
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
   	function changeHaveTin(){
   		value = $('#haveTIN-VAT').val();
   		if(value.localeCompare("false")==0){
   			$('#haveTIN-VAT').val("true");
   			$('#vat-tin-no').css('display','block');
   		}else{
   			$('#haveTIN-VAT').val("false");
   			$('#vat-tin-no').css('display','none');
   		}
   	}
   </script>
  <%@ include file="../master/footer.jsp" %>