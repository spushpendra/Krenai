<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" ng-App="myApp">
  
<head>
    <title>Add products - krenai</title>
    <script charset="utf-8" src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <!-- Angular Controller -->
    <script src="<c:url value='/products/addProductsCtrl.js' />"></script>
    <style type="text/css">
    .image-space{
      min-width: 100px;
    }
    
    .product-img{
    height:50px;
    width:50px;
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
          <section class="forms-advanced">
            <div class="page-header">
              <h1>      <i class="md md-input"></i> Add new product in my list    </h1>
              <p class="lead"> To improve your rating please add more and more product in your list! </p>
            </div>
            <div class="row m-b-40">
              <div class="col-md-3 col-md-push-9" id="general-elements-intro">
                <h5>Product Basic Information</h5>
                <p>If your category or sub category is not available in lisitng. You can request a new category or sub category by click below. <p><a href="request-category.html">Request new category</a></p><p><a href="request-category.html">Request new sub category</a></p>.</p>
              </div>
              <div class="col-md-8 col-md-pull-3">
                <div class="well white">
                  <form class="form" action="<c:url value="/product/add/mylist"/>" method="post" enctype="multipart/form-data">
                    <fieldset>
                      <legend>General Information</legend>
                                 
                         <div class="form-group filled" id="category-name-section">
                        <label for="inputEmail" class="control-label"></label>
                        <input id="category-name" name="category-name" type="text" class="form-control" placeholder="Category Name">
                         
                       </div>

                           <div class="form-group filled" id="category-name-section">
                        <label for="inputEmail" class="control-label"></label>
                        <input id="subcategory-name" name="subcategory-name" type="text" class="form-control" placeholder="Sub Category Name">
                          
                       </div>

                      
                      <div class="form-group filled" id="category-name-section">
                        <label for="inputEmail" class="control-label"></label>
                        <input id="product-name" name="product-name" type="text" class="form-control" placeholder="Product Name">
                          
                       </div>


                 <div id="product-toggle" class="product-toggle">
                      
                      	<div class="form-group filled" id="product-name-section">
                        <label for="inputEmail" class="control-label"></label>
                        <input id="product-name" name="product-name" type="text" class="form-control" placeholder="Product Name">
                        <input type="hidden" id="hidden-product-id" name="hidden-product-id" />  
                       </div>

                      <div class="form-group filled" id="brand-select-section">
                        <label class="control-label">Select Brand</label>
                        <select id="brand-select" name="brand-select" onchange="brandSelectInputSwitch();" class="form-control" >
                          <option value="">Select</option>
                          <option value="other">Others</option>
                        </select>
                      </div>
                      
                      <div class="form-group filled" id="brand-input-section">
                        <label for="inputEmail" class="control-label"></label>
                        <input id="brand-input" name="brand-input" type="text" class="form-control" placeholder="Brand Name"> 
                       </div>
                      

                      <div class="form-group filled">
                        <label for="textArea" class="control-label">Product Description</label>
                        <textarea class="form-control vertical " rows="3" name="product-description" id="product-description"></textarea> 
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
                        <label for="inputEmail" class="control-label"></label>
                        <input  type="text" name="mrp" class="form-control" id="mrp" placeholder="MRP (including all taxes)"> </div>


                      <div class="form-group filled">
                        <label for="inputEmail" class="control-label"></label>
                        <input type="text" class="form-control" name="selling-price" id="selling-price" placeholder="Selling Price"> </div>

                            <div class="form-group filled">
                        <label class="control-label">Select Tax</label>
                        <select id="tax-select" name="tax-select" class="form-control">
                           <c:forEach var="tax" items="${taxIterable}">
						     <option value="${tax.taxId}">${tax.taxName}</option>						      
						</c:forEach>
                        </select>
                      </div>  


                      <div class="form-group filled">
                        <label for="inputEmail" class="control-label"></label>
                        <input type="text" name="product-available-quantity" id="product-available-quantity" class="form-control" placeholder="Product Available Quantity">
                   	 </div>


                      <div class="form-group filled">
                        <label for="inputEmail" class="control-label"></label>
                        <input type="text" name="minimum-sale-qty" id="minimum-sale-qty" class="form-control" placeholder="Minimum Sale Quantity"> </div>


                          <div class="form-group filled">
                        <label class="control-label">Stock Status</label>
                        <select id="status-select" name="status-select" class="form-control">
                          <c:forEach var="status" items="${statusIterable}">
						     <option value="${status.statusId}">${status.statusValue}</option>						      
						</c:forEach>
                        </select>
                      </div>  


                      <div class="form-group filled">
                        <label for="inputEmail" class="control-label"></label>
                        <input type="date" id="available-from-date" name="available-from-date" class="form-control" placeholder="Available From Date"> 
                      </div>

                        


                        <div class="form-group filled">
                           <label class="control-label">Measurement Class</label>
                          <div class="row">
                            <div class="col-md-6 col-md-push-0" id="measurement-select-section">
                           
                        <select class="form-control" id="measurement-select" name="measurement-select">
                          <c:forEach var="measuredUnit" items="${measuredUnitIterable}">
						     <option value="${measuredUnit.unitId}">${measuredUnit.unitName}</option>						      
						</c:forEach>
                        </select>

                            </div>
                            <div class="col-md-6 col-md-push-0" id="measurement-input-section">
                            <input type="text" id="measurement-input" name="measurement-input" class="form-control" placeholder="Measurment Value"> 
                            </div>
                            <div class="col-md-6 col-md-push-0">
                            <input type="text" id="measurement-value" name="measurement-value" class="form-control" placeholder="Measurment Value"> 
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
		              <div class="col-md-3 col-md-push-9">
		                <h5>Product image upload</h5>
		                <p>Minimum one Image is required. To showcase your product batter please uplaod all five images with different angles.</p>
		              </div>
		              <div class="col-md-8 col-md-pull-3">
		                <div class="well white">
		              
		                    <fieldset>
		                      <legend class="m-b-10">File upload</legend>
		                      <div>
		                        <div class="form-group" id="image-upload-section"> 
		                        <span class="btn btn-info fileinput-button">               
		                         	<span>Upload Multiple Image</span>
		                          	<input type="file" name="productImage" accept=".jpg,.jpeg,.png,.gif"  class="fileupload" multiple> 
		                         </span>
		                       
		                        </div>
		                        
		                        <div id="image-display-section">
		                        	<div id="image-display1" class="form-group"> 
			                        </div>
			                        <div id="image-display2" class="form-group"> 
			                       	</div>
			                        <div class="form-group" id="image-display3" >
			                        </div>
			                        <div class="form-group" id="image-display4">
			                        </div>
			                        <div class="form-group" id="image-display5">
			                        </div>
		                       
		                        </div>
		
		                        <ul style="clear:both" class="response list-unstyled"></ul>
		                      </div>
		                    </fieldset>
		                  
		                </div>
		              </div>
		            </div>
		            <div class="row m-b-40" id="wysiwyg">
		              <div class="col-md-3 col-md-push-9">
		                <h5>Add to List</h5>
		                <p><a target="_blank" href="https://github.com/summernote/summernote"></a> is a super cool WYSIWYG Text Editor for Bootstrap.</p>
		              </div>
		              <div class="col-md-8 col-md-pull-3">
		                <div class="well white">
		                  
		                    <fieldset>
		                      
		                      <div class="form-group">
		                        <button type="submit" class="btn btn-primary">Add To Shop</button>
		                        <button type="reset" class="btn btn-default">Cancel</button>
		                      </div>
		                    </fieldset>
		                  
		                </div>
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
<script>

function getProductViaAjax(){
	var categoryId=$("#categorySelect").val();
	var subcategoryId=$("#subcategorySelect").val();
	//alert("top of the methods"+categoryId);
	$.ajax({
		url : '/products/getProductOnSubcategoryChange',
		data : {
			"categoryId":categoryId,
			"subcategoryId":subcategoryId
			},
		type: "POST",
		success : function(data) {
		
		if(data!=null){
			$('#productSelect').empty();
			$('#productSelect').append($('<option>', {
			    value:"" ,
			    text: "Select"
			}));
			for(var i=0;i<data.length;i++)
			 {
				//options += "<option value = "+data[i].productId+">"+data[i].productName+"</option>"; 
				$('#productSelect').append($('<option>', {
				    value:data[i].productId ,
				    text: data[i].productName+" - Brand("+data[i].brand+")"
				}));
			 }
			$('#productSelect').append($('<option>', {
			    value:"other" ,
			    text:"Others"
			}));
			//alert(options);
			  $('#productSelect').append(options);
			}
			else{
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
	var options="";
	//alert("top of the methods"+categoryId);
	$.ajax({
		url : '/products/getSubcategoryOnCategoryChange',
		data : {
			"categoryId":categoryId
			},
		type: "POST",
		success : function(data) {
		
		if(data!=null){
			$('#subcategorySelect').empty();
			$('#subcategorySelect').append($('<option>', {
			    value:"" ,
			    text:"Select"
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
			  $('#subcategorySelect').append(options);
			}
			else{
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
	$('#product-toggle').css("display","block");
	$('#product-toggle-2').css("display","block");
	if(productId.localeCompare("other")!=0){
		//alert("if");
		$("#product-name").css("display","none");
		$("#brand-input").css("display","none");
		$("#brand-select-section").css("display","none");
		$("#measurement-select-section").css("display", "none");
		$("#measurement-input-section").css("display", "block");
		$("#image-upload-section").css("display", "none");
		$("#image-display-section").css("display", "block");
		
	}else{
		//alert("if");
		$('#hidden-product-id').val("null");
		$("#product-name").css("display","block");
		$("#brand-input").css("display","none");
		$("#brand-select-section").css("display","block");
		$("#measurement-select-section").css("display", "block");
		$("#measurement-input-section").css("display", "none");
		$('#measurement-input').attr("readonly", false);
		$('#product-description').attr("readonly", false);
		$('#measurement-value').attr("readonly", false);
		$("#image-upload-section").css("display", "block");
		$("#image-display-section").css("display", "none");
		
	}
	//alert("top of the methods"+categoryId);
	if(productId.localeCompare("other")!=0){
			$.ajax({
				url : '/products/getProductDetails',
				data : {
					"productId":productId
					},
				type: "POST",
				success : function(data) {
				
				if(data!=null){
					$('#product-description').text(data.productDescription);
					$('#measurement-input').val(data.measurementUnit);
					$('#measurement-value').val(data.measurementValue);
					$('#measurement-input').attr("readonly", true);
					$('#product-description').attr("readonly", true);
					$('#measurement-value').attr("readonly", true);
					$('#hidden-product-id').val(data.productId);
					alert(data.imagePath1);
					if(data.imagePath1.localeCompare("null")!=0){
						$('#image-display1').html("<img class='product-img' src='/seller"+data.imagePath1+"'>");
						//alert(data.imagePath2);
						
					}
					
					if(data.imagePath2.localeCompare("null")!=0){
						$('#image-display2').html("<img class='product-img' src='/seller"+data.imagePath2+"'>");
					}
					if(data.imagePath3.localeCompare("null")!=0){
						$('#image-display3').html("<img class='product-img' src='/seller"+data.imagePath3+"'>");
						
					}
					if(data.imagePath4.localeCompare("null")!=0){
						$('#image-display4').html("<img class='product-img' src='/seller"+data.imagePath4+"'>");
						
					}
					if(data.imagePath5.localeCompare("null")!=0){
						$('#image-display5').html("<img class='product-img' src='/seller"+data.imagePath5+"'>");
						
					}
					//alert(options);
					}
					else{
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
		
	}else{
		$("#brand-input").css("display","none");
		$('#brand-input').val("null");
	}
}

</script>

<%@ include file="/master/footer.jsp" %>