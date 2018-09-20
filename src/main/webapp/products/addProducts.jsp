<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" ng-App="myApp">
  
<head>
    <link href="<c:url value='/assets/css/profile.update.css' />" rel="stylesheet" />

    <link href="<c:url value='/assets/css/imageCroppie/style.css' />" rel="stylesheet" />
    <link href="<c:url value='/assets/css/imageCroppie/style-example.css' />" rel="stylesheet" />
    <link href="<c:url value='/assets/css/imageCroppie/jquery.Jcrop.css' />" rel="stylesheet" />  

    <title>Add products - krenai</title>
    <script charset="utf-8" src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <!-- Angular Controller -->
    <script src="<c:url value='/products/addProductsCtrl.js' />"></script>
    <style type="text/css">
    .image-space{
      min-width: 100px;
    }
    .product-toggle{
      display:none;
    }
    .product-img{
    height:50px;
    width:50px;
    }

    .js-example-basic-single{
    width:100%;
    }
    </style>
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
              <h1>      <i class="md md-input"></i> Add new product in my list    </h1>
              <p class="lead"> To improve your rating please add more and more product in your list! ${headerMessageSuccess} </p>
            </div>
            <div class="row m-b-40">
              
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
       <c:if test="${not empty dataErrorMessage}">
      <div class="row">
              <div class="col-lg-12">
                <div class="bs-component">
                  <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <h4>Warning!</h4>
                    <p>${dataErrorMessage}</p>
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
      
      
                  <form id="add-product-form" class="form form-floating"  action="<c:url value="/product/add/mylist"/>" method="post" onsubmit="return finalValidate();" enctype="multipart/form-data">
                    <fieldset>
                      <legend>General Information</legend>
                                 
                          <div class="form-group filled">
                        <label class="control-label">Select Category</label>
                        <select id="categorySelect" name="categorySelect" class="js-example-basic-single" onchange="getSubcategoryViaAjax();" >
                          <option value="0">--Select Category--</option>  
                          <c:forEach var="category" items="${categoryMap}">
                       <option value="${category.key}">${category.value}</option>                 
                  </c:forEach>
                        <option value="-1">-- Add New Category --</option>  
                        </select>
                      </div>

                           <div class="form-group filled">
                        <label class="control-label">Select Sub-Category</label>
                        <select id="subcategorySelect" name="subcategorySelect" class="js-example-basic-single" onchange="getProductViaAjax();">
                          <option value="">-- Select Sub Category --</option>
                        </select>
                      </div>
                      
                       <div class="form-group filled">
                        <label class="control-label">Select Product</label>
                        <select id="productSelect" class="js-example-basic-single" onchange="productToggle();">
                          <option value="other">-- Select Product --</option>
                          <!-- <option value=""> Add New Product </option> -->
                        </select>
                      </div>

                 <div id="product-toggle" class="product-toggle">
                      
                        <div class="form-group filled" id="product-name-section">
                        <label for="inputEmail" class="control-label"></label>
                        <input id="product-name" onkeyup="return productsName();" name="product-name" maxlength="200" type="text" class="form-control" placeholder="Product Names">
                        <input type="hidden" id="hidden-product-id" name="hidden-product-id" />  
                        <div id="product-nameValidation" class="validationText help-block with-errors" ></div>
                         </div>

                      <div class="form-group filled" id="brand-select-section">
                        <label class="control-label">Select Brand</label>
                        <select id="brand-select" name="brand-select" class="js-example-basic-single" onkeyup="brandslct();" onchange="brandSelectInputSwitch();" style="width:100%">
                          <option value="">-- Select Brand --</option>
                          <c:forEach items="${brandIterable}" var="brand">
                           <option value="${brand.brandId}">${brand.brandName}</option>
                          </c:forEach>
                          <option value="other">Add New Brand</option>
                        </select>
                        <div id="brand-selectValidation" class="validationText help-block with-errors"></div> 
                      </div>
                      
                      <div class="form-group filled" id="brand-input-section">
                        <label for="inputEmail" class="control-label"></label>
                        <input id="brand-input" name="brand-input" type="text" class="form-control" onkeyup="brandsName()" placeholder="Brand Name">
                        <div id="brand-inputValidation" class="validationText help-block with-errors"></div> 
                       </div>
                      

                      <div class="form-group filled">
                        <label for="textArea" class="control-label">Product Description</label>
                        <textarea class="form-control vertical " onkeyup="countingChr();" maxlength="1000" rows="3" name="product-description" id="product-description"></textarea> 
                        <div id="product-descriptionValidation" class="validationText help-block with-errors"></div>
                       </div>
                       
                      <div class="form-group">
                        <div class="radio-inline">
                          <label class="filled">
                            <input type="radio" name="trade-type" id="trade-type" value="manufacturer" checked=""> Manufacturer </label>
                        </div>
                        <div class="radio-inline">
                          <label class="filled">
                            <input type="radio" name="trade-type" id="trade-type" value="supplier" checked="true"> Supplier </label>
                        </div>
                      </div>

                      <div class="form-group filled">
                        <select id="mrpAppNoApp" class="js-example-basic-single" onchange="return mrpGreaterZero();" onkeyup="mrpselectable();" style="width:100%">
                          <option value=""> Select MRP Type </option>
                          <option value="Applicable"> MRP Applicable </option>
                          <option value="NotApplicable"> MRP Not Applicable </option>
                        </select>
                        <div id="mrpAppNoAppValidation" class="validationText help-block with-errors"></div>
                      </div>


                      <div class="form-group" id="mrpShowHide" style="display:none">
                        <label for="inputEmail" class="control-label">MRP (including all taxes)</label>
                        <input  type="text" name="mrp" class="form-control" onchange='enablePrice(this.value,this.id);' id="mrp" 
                        onkeyup="$(this).val($(this).val().replace(/[^0-9/.]/g, '')); mrpGreaterZero();" readonly autocomplete="off"> 
                        <div id="mrpValidation" class="validationText help-block with-errors"></div></div>


                      <div class="form-group" id="slpShowHide" style="display:none">
                        <label for="inputEmail" class="control-label">Selling Price</label>
                        <input type="text" class="form-control" onkeyup="$(this).val($(this).val().replace(/[^0-9/.]/g, '')); prodSellPrice();" name="selling-price" id="sellPriceId" readonly> <div id="sellPriceIdValidation" class="validationText help-block with-errors" autocomplete="off"></div></div>

                            <div class="form-group filled">
                        <label class="control-label">Select Tax</label>
                        <select id="tax-select" name="tax-select" class="js-example-basic-single" style="width:100%">
                           <c:forEach var="tax" items="${taxIterable}">
                 <option value="${tax.taxId}">${tax.taxName}</option>                 
            </c:forEach>
                        </select>
                      </div>  


                      <div class="form-group">
                        <label for="inputEmail" class="control-label">Product Available Quantity</label>
                        <input type="text" onkeyup="$(this).val($(this).val().replace(/[^0-9]/g, ''));productQuantity();" name="product-available-quantity" id="product-available-quantity" class="form-control" autocomplete="off"><div id="product-available-quantityValidation" class="validationText help-block with-errors"></div>
                     </div>


                      <div class="form-group">
                        <label for="inputEmail" class="control-label">Minimum Sale Quantity</label>
                        <input type="text" name="minimum-sale-qty" id="minimum-sale-qty" onkeyup="$(this).val($(this).val().replace(/[^0-9]/g, ''));saleQuantityMax();" class="form-control"><div id="minimum-sale-qtyValidation" class="validationText help-block with-errors" autocomplete="off"></div> </div>


                          <div class="form-group filled">
                        <label class="control-label">Stock Status</label>
                        <select id="status-select" name="status-select" class="js-example-basic-single" style="width:100%">
                          <c:forEach var="status" items="${statusIterable}">
                 <option value="${status.statusId}">${status.statusValue}</option>                  
            </c:forEach>
                        </select>
                      </div>  


                      <div class="form-group filled">
                        <label for="inputEmail" class="control-label">Available From Date</label>
                        <input type="date" id="available-from-date"  name="available-from-date" class="form-control"> 
                        <div id="available-from-dateValidation" class="validationText help-block with-errors"></div>
                      </div>

                        


                        <div class="form-group filled">
                           
                              <label class="control-label">Measurement Units</label>
                          <div class="row">

                            <div class="col-md-6 col-md-push-0" id="measurement-select-section">
                        <select class="js-example-basic-single" id="measurement-select" name="measurement-select" style="width:100%">
                          <c:forEach var="measuredUnit" items="${measuredUnitIterable}">
                              <option value="${measuredUnit.shortName}">${measuredUnit.unitName}</option>              
                          </c:forEach>
                        </select>

                            </div>
                            
                            
                            <div class="col-md-6 col-md-push-0" id="measurement-input-section">

                            <input type="text" id="measurement-input"  name="measurement-input" class="form-control" placeholder="Measurment Values"> 
                            </div>
                            <div class="col-md-6 col-md-push-0">
                            <label class="control-label">Measurement Quantity</label>
                            <input type="text" onkeyup="$(this).val($(this).val().replace(/[^0-9/.]/g, ''));measureqty();" id="measurement-value" name="measurement-value" class="form-control" placeholder="Measurment Value">
                            <div id="measurement-valueValidation" class="validationText help-block with-errors"></div> 
                            </div>                            
                            
                          </div>
                        </div>
          </div>


                     </fieldset>
                  
                </div>
              </div>
            </div>
             <div id="product-toggle-2" class="product-toggle">
                <div class="row m-b-40" id="fileupload">
                  
                  <div class="col-md-12">
                    <div class="well white">
                  
                        <fieldset>
                          <legend class="m-b-10">Product Image Upload</legend>
                          <div>
                             <div class="row"  id="image-upload-section">
                              <!-- 
                               <div class="col-md-3">
                               <h4 class="text-success">Custom Upload</h4>
                               <div class="form-group"> -->
                                <div class="cropme"  data-img='myCanvas' style="width:500px;height:515px;"></div>
                                  <!-- <div class="drop-box btn-flat btn-info fileinput-button"> -->
                                  <!-- <img id="product-img-1" src="" style="max-width:220px;">
                                  <p class="text-success" id="productImage1Validation" class="validationText help-block with-errors"><i class="btn btn-lg btn-round btn-success "><span class="md md-add"></span></i>&nbsp;</p> -->
                                  <!-- <div id="productImage1Validation" style="Z-index:999,Position:fixed"  ></div> -->
                                  <input type="hidden" name="productImage1" id="productImagemyCanvas" >

                                  <!-- </div> -->
                            <!--     </div>
                                </div>
                                <div class="col-md-3">
                               <h4 class="text-success">Custom Upload</h4>
                               <div class="form-group">
                             -->
                                  <div class="cropme"  data-img='myCanvas1' style="width:500px;height:515px;"></div>

                                  <!-- <div class="drop-box btn-flat btn-info fileinput-button"> -->
                                  <!-- <img id="product-img-2" src="" style="max-width:220px;"> -->
                                  <!-- <p class="text-success"><i class="btn btn-lg btn-round btn-success "><span class="md md-add"></span></i>&nbsp;</p> -->
                                  <!-- <input type="file" name="productImage2" accept=".jpg,.jpeg,.png,.gif" onchange="loadFile(event,'product-img-2');"  class="fileupload" multiple>
 -->
                                  <input type="hidden" name="productImage2" id="productImagemyCanvas1" >
                                  <!-- </div> -->
                                <!-- </div>
                                </div> -->

                                  <div class="cropme"  data-img='myCanvas2' style="width:500px;height:515px;"></div>
                                  <input type="hidden" name="productImage3" id="productImagemyCanvas2" >

<!-- 
                                  <div class="cropme"  data-img='myCanvas3' style="width:500px;height:514px;"></div>
                                  <input type="hidden" name="productImage4" id="productImagemyCanvas3" >   -->
                          <!--       <div class="col-md-3">
                               <h4 class="text-success">Custom Upload</h4>
                               <div class="form-group">
                                  <div class="drop-box btn-flat btn-info fileinput-button">
                                  <img id="product-img-3" src="" style="max-width:220px;">
                                  <p class="text-success"><i class="btn btn-lg btn-round btn-success "><span class="md md-add"></span></i>&nbsp;</p>
                                  <input type="file" name="productImage3" accept=".jpg,.jpeg,.png,.gif" class="fileupload" onchange="loadFile(event,'product-img-3');"  multiple>
                                  </div>
                                </div>
                                </div> -->
                              <!--    <div class="col-md-3">
                               <h4 class="text-success">Custom Upload</h4>
                               <div class="form-group">
                                  <div class="drop-box btn-flat btn-info fileinput-button">
                                  <img id="product-img-4" src="" style="max-width:220px;">
                                  <p class="text-success"><i class="btn btn-lg btn-round btn-success "><span class="md md-add"></span></i>&nbsp;</p>
                                  <input type="file" name="productImage4" accept=".jpg,.jpeg,.png,.gif" class="fileupload" onchange="loadFile(event,'product-img-4');"  multiple>
                                  </div>
                                </div>
                                </div>
                                 <div class="col-md-3">
                               <h4 class="text-success">Custom Upload</h4>
                               <div class="form-group">
                                  <div class="drop-box btn-flat btn-info fileinput-button">
                                  <img id="product-img-5" src="" style="max-width:220px;">
                                  <p class="text-success"><i class="btn btn-lg btn-round btn-success "><span class="md md-add"></span></i>&nbsp;</p>
                                  <input type="file" name="productImage5" accept=".jpg,.jpeg,.png,.gif" class="fileupload" onchange="loadFile(event,'product-img-5');"  multiple>
                                  </div>
                                </div>
                                </div>
                                
                                 <div class="col-md-3">
                               <h4 class="text-success">Custom Upload</h4>
                               <div class="form-group">
                                  <div class="drop-box btn-flat btn-info fileinput-button">
                                  <img id="product-img-6" src="" style="max-width:220px;">
                                  <p class="text-success"><i class="btn btn-lg btn-round btn-success "><span class="md md-add"></span></i>&nbsp;</p>
                                  <input type="file" name="productImage6" accept=".jpg,.jpeg,.png,.gif" class="fileupload" onchange="loadFile(event,'product-img-6');"  multiple>
                                  </div>
                                </div>
                                </div>
                                
                                 <div class="col-md-3">
                               <h4 class="text-success">Custom Upload</h4>
                               <div class="form-group">
                                  <div class="drop-box btn-flat btn-info fileinput-button">
                                  <img id="product-img-7" src=""  style="max-width:220px;">
                                  <p class="text-success"><i class="btn btn-lg btn-round btn-success "><span class="md md-add"></span></i>&nbsp;</p>
                                  <input type="file" name="productImage7" accept=".jpg,.jpeg,.png,.gif" class="fileupload" onchange="loadFile(event,'product-img-7');"  multiple>
                                  </div>
                                </div>
                                </div>
                                
                                 <div class="col-md-3">
                               <h4 class="text-success">Custom Upload</h4>
                               <div class="form-group">
                                  <div class="drop-box btn-flat btn-info fileinput-button">
                                  <img id="product-img-8" src=""  style="max-width:220px;">
                                  <p class="text-success"><i class="btn btn-lg btn-round btn-success "><span class="md md-add"></span></i>&nbsp;</p>
                                  <input type="file" name="productImage8" accept=".jpg,.jpeg,.png,.gif" class="fileupload" onchange="loadFile(event,'product-img-8');"  multiple>
                                  </div>
                                </div>
                                </div> -->
                              </div>
                              <div class="row"   id="image-display-section">
                              <div class="col-md-12">
                              <h4 class="text-success">Images Form Krenai Stores</h4>
                                <div class="row">
                                  <div class="col-md-4">
                                    <div id="image-display1" class="product-image-galary">
                                      <i class="md md-cloud-upload text-success"></i>
                                    </div>
                                    
                                  </div>

                                  <div class="col-md-4">
                                    <div id="image-display2" class="product-image-galary">
                                      <i class="md md-cloud-upload text-success"></i>
                                    </div>
                                     
                                  </div>

                                  <div class="col-md-4">
                                    <div id="image-display3" class="product-image-galary">
                                      <i class="md md-cloud-upload text-success"></i>
                                    </div>
                                     

                                  </div>

                                  <div class="col-md-4">
                                   
                                    <div id="image-display4" class="product-image-galary">
                                      <i class="md md-cloud-upload text-success"></i>
                                    </div>

                                    
                                  </div>
                                  <div class="col-md-4">
                                    
                                    <div id="image-display5" class="product-image-galary">
                                      <i class="md md-cloud-upload text-success"></i>
                                    </div>

                                  </div>
                                  <div class="col-md-4">
                                    <div id="image-display6" class="product-image-galary">
                                      <i class="md md-cloud-upload text-success"></i>
                                    </div>

                                  </div>
                                </div>
                              </div>
                            </div>
                            <ul style="clear:both" class="response list-unstyled"></ul>
                          </div>
                        </fieldset>
                      
                    </div>
                  </div>
                </div>
                <div class="row m-b-40" id="wysiwyg">
                  
                  <div class="col-md-12">
                      
                        <fieldset>
                          
                          <div class="form-group">
                          <div class="col-md-4" id="spinner" ></div>
                            <button type="submit" id="add-shop-btn" onclick="spinner();" class="btn btn-primary">Add To Shop</button>
                            <button type="reset" class="btn btn-default">Cancel</button>
                          </div>
                        </fieldset>
                        
                  </div>
                </div>
               </div>
              </form>
           </div>
        
          </section>
        </div>
      </div>
    </main>
<script src="<c:url value='/assets/js/angular.min.js' />"></script>
<script src="<c:url value='/assets/js/myApp.js' />"></script>
<script src="<c:url value='/assets/js/myFormValidation.js' />"></script>

<script type="text/javascript">


                            var text_max = 1000;
                            $('#product-descriptionValidation').text('1000 characters left');

                              function countingChr(){

                                  var max = 1000;
                                  var min = 10;
                                    var len = $.trim($('#product-description').val()).length;
                                    if (len <= min) {
                                        $('#product-descriptionValidation').text(' Input 10 to 1000 characters for Product Description. ');
                                        $('#product-descriptionValidation').css("color","#B71C1C");
                                    }

                                    else if(len >= max){
                                      $('#product-descriptionValidation').text(' You have reached the limit');
                                      $('#product-descriptionValidation').css("color","#B71C1C");
                                    }

                                    else {
                                        var ch = max - len;
                                        $('#product-descriptionValidation').text(ch + ' characters left');
                                        $('#product-descriptionValidation').css("color","#37B50D");
                                    }


                                // var text_length = $('#product-description').val().length;
                                // var text_remaining = text_max - text_length;

                                // $('#product-descriptionValidation').html(text_remaining + ' characters remaining');
                              }


  </script>

<script type="text/javascript">

function finalValidate(){

productsName();
brandslct();
brandsName();
prdDesc();
mrpselectable();
mrpGreaterZero();
prodSellPrice();
productQuantity();
saleQuantityMax();
restrictedPastDate();
measureqty();
imgselect();

      if(productsName()){
        if(brandslct()){
        if(brandsName()){
          if(prdDesc()){
            if(mrpselectable()){
         if(mrpGreaterZero()){ 
            if(prodSellPrice()){
                if(productQuantity()){
                  if(saleQuantityMax()){
                       if(restrictedPastDate()){
                        if(measureqty()){
                        if(imgselect()){

                          return true;
                          }
                      }
                        }
                      }
                      }
                    }
                    }
                  }
                  }        
              }
          }
      }

$("#add-shop-btn").attr("disabled",false);
$("#spinner").empty();

  return false;
}


</script>


<script>

function getProductViaAjax(){
  var categoryId=$("#categorySelect").val();
  var subcategoryId=$("#subcategorySelect").val();
  if(subcategoryId==-1){
    window.location="/product/addsubcategory";
  }
  $('#loading-message').css('display','block');
  //alert("top of the methods"+categoryId);
  $.ajax({
    url : '<c:url value="/products/getProductOnSubcategoryChange"/>',
    data : {
      "categoryId":categoryId,
      "subcategoryId":subcategoryId
      },
    type: "POST",
    success : function(data) {
    
    if(data!=null){
     $('#loading-message').css('display','none');
      $('#productSelect').empty();
      $('#productSelect').append($('<option>', {
          value:"other" ,
          text: "-- Select Product --"
      }));
      for(var i=0;i<data.length;i++)
       {
        //options += "<option value = "+data[i].productId+">"+data[i].productName+"</option>"; 
        $('#productSelect').append($('<option>', {
            value:data[i].productId ,
            text: data[i].productName+"("+data[i].packagedQuantity+" "+data[i].packagedUnit+") - Brand("+data[i].brand+")"
        }));
       }
      $('#productSelect').append($('<option>', {
          value:"other" ,
          text:"Add New Product"
      }));
      //alert(options);
        $('#productSelect').append(options);
      }
      else{
        $('#loading-message').css('display','none');
          
        alert("not available");
      
      }
    },
    error: function(data){
      //alert();
    }
  });
}

function getSubcategoryViaAjax(){
  var categoryId=$("#categorySelect").val();
  if(categoryId==-1){
    window.location="/product/addcategory";
  }
  $('#loading-message').css('display','block');
  var options="";
  //alert("top of the methods"+categoryId);
  $.ajax({
    url : '<c:url value="/products/getSubcategoryOnCategoryChange"/>',
    data : {
      "categoryId":categoryId
      },
    type: "POST",
    success : function(data) {
    
    if(data!=null){
      $('#loading-message').css('display','none');
      $('#subcategorySelect').empty();
      $('#subcategorySelect').append($('<option>', {
          value:"" ,
          text:"-- Select Sub Category --"
      }));
      for(var i=0;i<data.length;i++)
       {
        //options += "<option value = "+data[i].productId+">"+data[i].productName+"</option>"; 
        $('#subcategorySelect').append($('<option>', {
            value:data[i].subcategoryId ,
            text: data[i].subcategoryName
        }));
       }
      
      //alert(options);
        
        if(data.length==0){
          $('#subcategorySelect').append($('<option>', {
                value:"-1" ,
                text:"Add New Sub Category"
            }));
        }
      }
      else{
        $('#loading-message').css('display','none');
          alert("not available");
      
      }
    },
    error: function(data){
      //alert();
    }
  });
}


function productToggle(){
  var productId=$("#productSelect").val();
  var options="";
  $('#loading-message').css('display','block');
  $('#product-toggle').css("display","block");
  $('#product-toggle-2').css("display","block");
  if(productId.localeCompare("other")!=0){
   // alert("if");
    $("#product-name").css("display","none");
    $("#product-name").attr("disabled",true);
    $("#product-nameValidation").text("product name").hide(300);
    $("#brand-inputValidation").text("Please select brand name").hide();
    $("#product-descriptionValidation").text("Input 10 to 200 characters .").hide();
    // $("#product-name").css("border-bottom", "2px ");

    // $("#brand-input").attr("disabled",true);
    $("#product-description").attr("readonly",true);
    $("#brand-input").css("display","none");
    $("#brand-select-section").css("display","none");
    $("#measurement-select-section").css("display", "none");
    $("#measurement-input-section").css("display", "block");
    $("#image-upload-section").css("display", "none");
    $("#image-display-section").css("display", "block");
    
  }
 else{
   //alert("else");
    $('#hidden-product-id').val("null");
    $("#product-name").css("display","block");
  $("#product-name").attr("disabled",false);


    // $("#product-description").attr("disabled",false);
    $("#brand-input").css("display","none");
    $("#brand-select-section").css("display","block");
    $("#measurement-select-section").css("display", "block");
    $("#measurement-input-section").css("display", "none");
    $('#measurement-input').attr("readonly", false);
    $('#product-description').attr("readonly", false);
    $('#measurement-value').attr("readonly", false);
    $("#image-upload-section").css("display", "block");
    $("#image-display-section").css("display", "none");
    $('#loading-message').css('display','none');
    
  }
  //alert("top of the methods"+categoryId);
  if(productId.localeCompare("other")!=0){
   // alert(productId.localeCompare("other"));
      $.ajax({
        url : '/products/getProductDetails',
        data : {
          "productId":productId
          },
        type: "POST",
        success : function(data) {
        
        if(data!=null){
          $('#loading-message').css('display','none');
            $('#product-description').text(data.productDescription);
          $('#measurement-input').val(data.measurementUnit);
          $('#measurement-value').val(data.measurementValue);
          $('#measurement-input').attr("readonly", true);
          $('#product-description').attr("readonly", true);
          $('#measurement-value').attr("readonly", false);
          $('#hidden-product-id').val(data.productId);
          //alert(data.imagePath1);
          if(data.imagePath1.localeCompare("null")!=0){
            $('#image-display1').html("<img class='product-img' src='"+data.imagePath1+"'>");
            //alert(data.imagePath2);
            
          }
          
          if(data.imagePath2.localeCompare("null")!=0){
            $('#image-display2').html("<img class='product-img' src='"+data.imagePath2+"'>");
          }
          if(data.imagePath3.localeCompare("null")!=0){
            $('#image-display3').html("<img class='product-img' src='"+data.imagePath3+"'>");
            
          }
          if(data.imagePath4.localeCompare("null")!=0){
            $('#image-display4').html("<img class='product-img' src='"+data.imagePath4+"'>");
            
          }
          if(data.imagePath5.localeCompare("null")!=0){
            $('#image-display5').html("<img class='product-img' src='"+data.imagePath5+"'>");
            
          }
          //alert(options);
          }
          else{
            $('#loading-message').css('display','none');
              alert("not available");
          
          }
        },
        error: function(data){
          //alert();
        }
      });
  }
}

function brandSelectInputSwitch(){
  var brandSelected=$("#brand-select").val();
  if(brandSelected.localeCompare("other")==0){
    $("#brand-input").css("display","block");
    $("#brand-input").attr("disabled",false);
  }else{
    $("#brand-input").css("display","none");
    $('#brand-input').val("null");
    $("#brand-input").attr("disabled",true);

  }
}



</script>
<script>


function productsName(){
   // $("#product-nameValidation").text("product name").hide(300);
  var prdName= $("#product-name").val();
  
  var prdselect = $("#productSelect").val();
      if( prdselect == 'other'){ 
              //     $("#product-name").css("display","none");
              //     $("#product-name").attr("disabled",true);
              //     $("#product-nameValidation").text("product name").hide(300);

              //     $("#product-name").css("border-bottom", "2px ");

              //     return true;
              // }

           if(prdName =='')
              {
                  
                $("#product-nameValidation").text("Please enter product name").show();

                $("#product-name").css("border-bottom", "2px  solid #FF0000");
                $("#product-name").focus();
                return false;
              }

             else {
                  $("#product-nameValidation").text("product name").hide(300);

                  $("#product-name").css("border-bottom", "2px ");

                  return true;
              }
            }
      // }
      else{ 
             $("#product-nameValidation").text("product name").hide(300);

                  $("#product-name").css("border-bottom", "2px ");

                  return true;

      }
    
}

function brandslct(){
    var brndname = $("#brand-select").val();
    var prdselect = $("#productSelect").val();

    if(prdselect=='other'){
       if(brndname =='')
                {
           
                  $("#brand-selectValidation").text("Please select brand name").show();

                  $("#brand-select").css("border-bottom", "2px  solid #FF0000");
                  $("#brand-select").focus();
                  return false;
                }

                else{
                    $("#brand-selectValidation").text("brand name").hide(300);

                    $("#brand-select").css("border-bottom", "1px ");

                    return true;
                }
    }
    else{
               $("#brand-selectValidation").text("brand name").hide(300);

                    $("#brand-select").css("border-bottom", "1px ");

                    return true;

    }

}


function measureqty(){
    var msrqty = $("#measurement-value").val();
    var prdselect = $("#productSelect").val();

    if(prdselect=='other'){
       if(msrqty =='')
                {
           
                  $("#measurement-valueValidation").text("Please input measurement quantity").show();

                  $("#measurement-value").css("border-bottom", "2px  solid #FF0000");
                  $("#measurement-value").focus();
                  return false;
                }

                else{
                    $("#measurement-valueValidation").text("quantity").hide(300);

                    $("#measurement-value").css("border-bottom", "1px ");

                    return true;
                }
    }
    else{
               $("#measurement-valueValidation").text("brand name").hide(300);

                    $("#measurement-value").css("border-bottom", "1px ");

                    return true;

    }

}


function brandsName(){
  var prdName= $("#brand-input").val();
  var brndname = $("#brand-select").val();
   var prdselect = $("#productSelect").val();
      if( prdselect == 'other'){
            if( brndname == 'other'){ 
                if(prdName =='')
                {
           
                  $("#brand-inputValidation").text("Please input brand name").show();

                  $("#brand-input").css("border-bottom", "2px  solid #FF0000");
                  $("#brand-input").focus();
                  return false;
                }

                else{
                    $("#brand-inputValidation").text("brand name").hide(300);

                    $("#brand-input").css("border-bottom", "1px ");

                    return true;
                }
              }
      else{ 
         $("#brand-inputValidation").text("brand name").hide(300);

            $("#brand-input").css("border-bottom", "2px ");

            return true;
      }
    }
    else {
      $("#brand-inputValidation").text("brand name").hide(300);

            $("#brand-input").css("border-bottom", "2px ");

            return true;
    }
     
}

// function mrpApplNotAppl(){
//   var mrpSelect = $("#mrpAppNoApp").val();
//   var mrpfield = $("#mrp").val();
//       if(mrpSelect == 'Applicable'){
//         $("#mrp").attr("disabled",false);
//           mrpGreaterZero();      
//       }
//       else if(mrpSelect == 'NotApplicable'){
//         $("#mrp").attr("disabled",true);
//       }
// }

function mrpGreaterZero(){
  var mrpRate = $("#mrp").val();
  var mrpSelect = $("#mrpAppNoApp").val();
  if(mrpSelect == 'Applicable'){
    $("#mrpShowHide").show();
    $("#slpShowHide").show();
    $("#mrp").attr("readonly",false);
    $("#sellPriceId").attr('readonly', false);

      if(mrpRate <= 0 )
      {
 
        $("#mrpValidation").text("Value should be greater than zero").show();

        $("#mrp").css("border-bottom", "2px  solid #FF0000");
        $("#mrp").focus();
        return false;
      }

      else{
          $("#mrpValidation").text("Value should be greater than zero").hide(300);

          $("#mrp").css("border-bottom", "2px ");

          return true;
      }
    }
    else if(mrpSelect == 'NotApplicable'){
      $("#slpShowHide").show();
      $("#mrpShowHide").css('display','none');
      $("#sellPriceId").attr('readonly', false);
      $("#mrp").val('0');
      $("#mrpValidation").text("Value should be greater than zero").hide(300);

          $("#mrp").css("border-bottom", "2px ");

          return true;
    }
    else if(mrpSelect == ''){
      $("#slpShowHide").css('display','none');
      $("#mrpShowHide").css('display','none');
    }
     
}


function mrpselectable(){
  var prdAvailqut = $('#mrpAppNoApp').val();
      if( prdAvailqut =='' )
      {
      
        $("#mrpAppNoAppValidation").text("Please select mrp type.").show();

        $("#mrpAppNoApp").css("border-bottom", "2px  solid #FF0000");
        $("#mrpAppNoApp").focus();
        return false;
      }
      else{
          $("#mrpAppNoAppValidation").text("Mrp type").hide(300);

          $("#mrpAppNoApp").css("border-bottom", "2px ");

          return true;
      }
}


function productQuantity(){
  var prdAvailqut = $('#product-available-quantity').val();
      if( prdAvailqut =='' )
      {
      
        $("#product-available-quantityValidation").text("Value should be greater than zero").show();

        $("#product-available-quantity").css("border-bottom", "2px  solid #FF0000");
        $("#product-available-quantity").focus();
        return false;
      }
      else{
          $("#product-available-quantityValidation").text("Value should be greater than zero").hide(300);

          $("#product-available-quantity").css("border-bottom", "2px ");

          return true;
      }
}


function saleQuantityMax(){
      var maxValue=parseInt($('#minimum-sale-qty').val());
      var prodVal =parseInt($('#product-available-quantity').val());

      if( maxValue =='' ){
       
        $("#minimum-sale-qtyValidation").text("Please enter sale quantity.").show();

        $("#minimum-sale-qty").css("border-bottom", "2px  solid #FF0000");
        $("#minimum-sale-qty").focus();
        return false;
      }

           else if( maxValue > prodVal ){

        $("#minimum-sale-qtyValidation").text("Sale Quantity's value not more than available product quantity ").show();

        $("#minimum-sale-qty").css("border-bottom", "2px  solid #FF0000");
        $("#minimum-sale-qty").focus();
        return false;

      }

      else{
          $("#minimum-sale-qtyValidation").text("Sale Quantity's value must be 1 - 10").hide(300);
          $("#minimum-sale-qty").css("border-bottom", "2px ");
          return true;
      }

}

  function prodSellPrice(){
  var prdName= $("#sellPriceId").val();
  var mrpval = parseFloat($("#mrp").val());
  if(prdName =='')
      {
 
        $("#sellPriceIdValidation").text("Please enter selling price greater than zero").show();

        $("#sellPriceId").css("border-bottom", "2px  solid #FF0000");
        $("#sellPriceId").focus();
        return false;
      }

  else if(mrpval =='0'){
      // if(prdName =='')
      // {
 
      //   $("#sellPriceIdValidation").text("Please enter selling price").show();

      //   $("#sellPriceId").css("border-bottom", "2px  solid #FF0000");
      //   $("#sellPriceId").focus();
      //   return false;
      // }

       if(prdName < mrpval){
      $("#sellPriceIdValidation").text("Selling price must be greater than mrp").show();

        $("#sellPriceId").css("border-bottom", "2px  solid #FF0000");
        $("#sellPriceId").focus();
        return false; 
      }

      else{
          $("#sellPriceIdValidation").text("selling price").hide(300);

          $("#sellPriceId").css("border-bottom", "2px ");

          return true;
      }
    }

    else if(mrpval > '0' ){
        if(prdName =='')
      {
 
        $("#sellPriceIdValidation").text("Please enter selling price").show();

        $("#sellPriceId").css("border-bottom", "2px  solid #FF0000");
        $("#sellPriceId").focus();
        return false;
      }

      else if(prdName > mrpval){
      $("#sellPriceIdValidation").text("Selling price must be less than mrp").show();

        $("#sellPriceId").css("border-bottom", "2px  solid #FF0000");
        $("#sellPriceId").focus();
        return false; 
      }

      else{
          $("#sellPriceIdValidation").text("selling price").hide(300);

          $("#sellPriceId").css("border-bottom", "2px ");

          return true;
      } 
    }
     
}


function imgselect(){
  var imgqlty = $('#productImage1').val();
  var prdselect = $("#productSelect").val();
      if( prdselect == 'other'){ 
      if( imgqlty =='' )
      {
      
        $("#productImage1Validation").append("Atleast one image must be upload.").show();

        $("#productImage1").css("border-bottom", "2px  solid #FF0000");
        $("#productImage1").focus();
        return false;
      }
      else{
          $("#productImage1Validation").text("Image upload.").hide(300);

          $("#productImage1").css("border-bottom", "2px ");

          return true;
      }
    }

      else{
          $("#productImage1Validation").text("Image upload.").hide(300);

          $("#productImage1").css("border-bottom", "2px ");

          return true;
      }
}

function prdDesc(){
  var prddd = $.trim($('#product-description').val());
   var prdselect = $("#productSelect").val();
      if( prdselect == 'other'){
          if( prddd.length ==0 )
          {
          
            $("#product-descriptionValidation").text("Input 10 to 1000 characters .").show();

            $("#product-description").css("border-bottom", "2px  solid #FF0000");
            $("#product-description").focus();
            return false;
          }

          else if(prddd.length < 10){
            $("#product-descriptionValidation").text("Input 10 to 1000 characters .").show();

            $("#product-description").css("border-bottom", "2px  solid #FF0000");
            $("#product-description").focus();
            return false;
          }

          else if(prddd.length > 1000){
            $("#product-descriptionValidation").text("Input 10 to 1000 characters .").show();

            $("#product-description").css("border-bottom", "2px  solid #FF0000");
            $("#product-description").focus();
            return false;
          }

          else{
              $("#product-descriptionValidation").text("Image upload.").hide(300);

              $("#product-description").css("border-bottom", "2px ");

              return true;
          }
      }
      else{ 
            $("#product-descriptionValidation").text("Image upload.").hide(300);

            $("#product-description").css("border-bottom", "2px ");

            return true;
      }
}

 


 </script>
 
 <script>
  var loadFile = function(event,img) {
    var output = document.getElementById(img);
     output.src = URL.createObjectURL(event.target.files[0]);
    //alert(URL.createObjectURL(event.target.files[0]);
  };
</script>

 <script>
       function spinner(){
         $("#add-shop-btn").attr("disabled",true);
         $("#add-product-form").submit();
         $("#spinner").css("text-align","right");
         
        $("#spinner").append("<svg width='32px' height='32px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' class='uil-ring-alt'><rect x='0' y='0' width='100' height='100' fill='none' class='bk'></rect><circle cx='50' cy='50' r='40' stroke='#f0f0f0' fill='none' stroke-width='10' stroke-linecap='round'></circle><circle cx='50' cy='50' r='40' stroke='#e91e63' fill='none' stroke-width='6' stroke-linecap='round'><animate attributeName='stroke-dashoffset' dur='2s' repeatCount='indefinite' from='0' to='502'></animate><animate attributeName='stroke-dasharray' dur='2s' repeatCount='indefinite' values='150.6 100.4;1 250;150.6 100.4'></animate></circle></svg>");
        }
             
       </script>
                      
<%@ include file="/master/footer.jsp" %>


    <!-- <link href="<c:url value='/assets/css/select2.min.css' />" rel="stylesheet" /> -->

<script src="<c:url value='/assets/js/select2.min.js' />"></script>

<script type="text/javascript">
  
    $(".js-example-basic-single").select2();

</script>

<script src="<c:url value='/assets/js/imageCroppie/jquery-1.10.2.min.js' />"></script>
<script src="<c:url value='/assets/js/imageCroppie/jquery.Jcrop.js' />"></script>
<script src="<c:url value='/assets/js/imageCroppie/jquery.SimpleCropperProductsImage.js' />"></script>

<script>
        // Init Simple Cropper
        $('.cropme').simpleCropper();
        // $('#secndCrop').simpleCropper2();
        
      </script>