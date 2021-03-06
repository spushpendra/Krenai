<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" ng-App="myApp">
  
<head>
    <link href="<c:url value='/assets/css/profile.update.css' />" rel="stylesheet" />

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
    .search-li{
    	background-color:#d6e6f3; !important
    }
    .search-ava{
    width: 100%;
    height: 100%;
    position: absolute;
    background: rgba(255, 193,7, 0.8);
    }
    </style>
  </head>
  <body scroll-spy="" id="top" class=" theme-template-dark theme-pink alert-open alert-with-mat-grow-top-right">
    <div class="loading-message" id="loading-message">
      <button class="btn btn-warning btn-flat">Updating...<div class="ripple-wrapper"></div></button>
    </div>
    <!-- popup dialog -->
    <div id="overlay_pramod"></div>
          <div id="frame_pramod">
            <i onclick="fadeOutPop();" style="float:right; padding:0px; font-size:25px; color:#676767; padding:5px" class="fa fa-times-circle"></i>

            <div class="row">
              <div class="col-md-12">
                <div class="card pop-up-card-push bordered">
                  <div class="card-header">
                    <div class="row">
                      <div class="col-md-3">
                        <img id="frame-img" src="" style="height: 100px;">  
                      </div>
                      <div class="col-md-6">
                        <p id="frame-name"></p>
                       
                      </div>
                      <div class="col-md-3">
                        <div class="form-group">
                          <div class="switch">
                          
                          </div>
                          </div>
                      </div>
                    </div>
                  </div>
                  <div class="card-content">
                    <div class="row">
                    
                      <input type="hidden"  name="hidSellerProductListingId" id="hidSellerProductListingId" class="form-control"> 
                        <div class="col-md-6">
                        <div class="form-group filled">
                        <label for="inputEmail" class="control-label">Available Quantity</label>
                        <input type="hidden"  id="frame-id"> 
                        <input type="text"  id="availableQty" class="form-control" onkeyup="$(this).val($(this).val().replace(/[^0-9/]/g, ''));"> 
                        <div class="help-block with-errors validationText" id="availableQtyValidate"></div>
                        </div>
                      </div>
                      <div class="col-md-6">
                         <div class="form-group filled">
                        <label for="inputEmail" class="control-label">Minimum Selling Quantity</label>
                        <input type="text"  id="minimumSellingQty"  onchange="return minSaleQuantity();" class="form-control" onkeyup="$(this).val($(this).val().replace(/[^0-9/]/g, ''));"> 
                        <div class="help-block with-errors validationText" id="minimumSellingQtyValidate"></div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group filled">
                        <label for="inputEmail" class="control-label">MRP</label>
                        <input type="text"  id="mrp" class="form-control" onkeyup="$(this).val($(this).val().replace(/[^0-9/]/g, ''));"> 
                        <div class="help-block with-errors validationText" id="mrpValidate"></div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group filled">
                        <label for="inputEmail" class="control-label">Selling Price</label>
                        <input type="text" id="sellingPrice" onchange="return prodSellerPrice();"  class="form-control" onkeyup="$(this).val($(this).val().replace(/[^0-9/]/g, ''));">  
                        <div class="help-block with-errors validationText" id="sellingPriceValidate"></div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="card-action card-action-push clearfix">
                  
                  <div class="pull-right"> 
                  <a class="btn btn-default fa-times-circle" onclick="fadeOutPop();">Cancel</a>
                  <button class="btn btn-primary" onclick="addToStore();">Add To Store</button>
                  </div>
                  
                  </div>
                 
                </div>
            </div>        
          </div>
          </div>
    <!-- popup dialog -->
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
                <div class="row well white">
                	 <c:if test="${not empty errorMessage}">
				     		 <div class="row">
				              <div class="col-lg-12">
				                <div class="bs-component">
				                  <div class="alert alert-dismissible alert-danger">
				                    <button type="button" class="close" data-dismiss="alert">�</button>
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
				                    <button type="button" class="close" data-dismiss="alert">�</button>
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
				                    <button type="button" class="close" data-dismiss="alert">�</button>
				                    <h4>Success!</h4>
				                    <p>${successMessage}</p>
				                  </div>
				                </div>
				              </div>
				            </div>
				      </c:if>
      
      				<div class="col-md-12">
		              <div class="row">
		              	<div class="col-md-7">
		                
		                  <h5 class="text-success">Sell an Existing Product</h5>
		                  
		                    <div class="form-group input-group">
		                      <input id="search-inp" class="form-control"  onkeyup="search(this.value);"  type="text" placeholder="Choose from millions of listed products on Krenai. ">
		                       <div id="results" style="position: absolute;z-index: 2; width:88%; margin-top:8%;"></div>
		                      <div class="input-group-btn p-l-10">
		                        <button id="find-btn" class="btn btn-success" onclick="find();">Find</button>
		                      </div>
		                    </div>
		                  
		                  <p></p>
		                  <p>Just add a few details and start selling.</p>
		                
		
		              	</div>
		              	 <div class="col-md-2 trx-side">
		                	<div id="spinner"></div>
		             	 </div>
			              <div class="col-md-3 trx-buttons-bulk">
			                
			                  <ul class="list-unstyled">
			                    <h5 class="text-success">Create new product</h5>
			                  </ul>
			                  <a href="/products/add"><button class="btn btn-success btn-flat">Create New Product<div class="ripple-wrapper"></div></button></a>
			                  <p></p>
			                  
			              </div>
		              </div>
					</div>
                </div>
              </div>
              
              <div class="row col-md-12">
	              <h5 class="text-success">Product Search Name</h5>
	                 
	             <div id="sug-div"></div>

            </div>
            
            </div>
             
           </div>
        
          </section>
        </div>
      </div>
    </main>
<script src="<c:url value='/assets/js/angular.min.js' />"></script>
<script src="<c:url value='/assets/js/myApp.js' />"></script>
<script src="<c:url value='/assets/js/myFormValidation.js' />"></script>
 <script type="text/javascript">
                        in_process = false;
                       
                        function search(q){
                        	//alert(q);
                        	if(!in_process && q.length>2){
    						in_process = true;
	                        	$.ajax({
	                        		url : '/directory/search/sug?q='+q,
	                        		data : {
	                        			"q":q
	                        			},
	                        		type: "GET",
	                        		success : function(dataArray) {
	                        		//alert(data.length);
	                        		console.log(dataArray);
	                        			$("#results").empty();
                                $("#results").append("<p>&nbsp;</p><p></p><p></p><ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu' style='display: block; position: static;'></ul>")
	                        			for(i=0;i<dataArray.length;i++){
	                        				data = dataArray[i];
	                        				$("#results ul").append("<li style='cursor: pointer;'><a onclick=\"getList('"+data.url+"');\"><img style='max-width: 44px;margin-top: -6px;padding:4px;' src='"+data.image+"'>"+data.name+"</a></li>");
	                        			}

	                        			in_process = false;
	                        		},
	                        		error: function(data){
	                        			$("#results").empty();
	                        			in_process = false;
	                        		}
	                        	});
                        	}
                        	
                        }
                        
                        function getList(a){
                        	//alert(a);
                        	$("#spinner").css("margin-top","50%");
                            $("#spinner").append("<svg width='32px' height='32px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' class='uil-ring-alt'><rect x='0' y='0' width='100' height='100' fill='none' class='bk'></rect><circle cx='50' cy='50' r='40' stroke='#f0f0f0' fill='none' stroke-width='10' stroke-linecap='round'></circle><circle cx='50' cy='50' r='40' stroke='#e91e63' fill='none' stroke-width='6' stroke-linecap='round'><animate attributeName='stroke-dashoffset' dur='2s' repeatCount='indefinite' from='0' to='502'></animate><animate attributeName='stroke-dasharray' dur='2s' repeatCount='indefinite' values='150.6 100.4;1 250;150.6 100.4'></animate></circle></svg>");
                        	$("#find-btn").prop("disabled",true);
                            $("#results").empty();
                        	$("#search-inp").val(a);
                        	$.ajax({
                        		url : '/directory/getlist?q='+a,
                        		data : {
                        			"q":a
                        			},
                        		type: "GET",
                        		success : function(dataArray) {
                        		//alert(data.length);
                        		$("#sug-div").empty();
                        		for(i=0; i<dataArray.length; i++){
                        			data=dataArray[i];
                        			title = " Name: "+data.productName+"\n Category: "+data.category+"\n Sub Category: "+data.subcategory+"\n Qty: "+data.packagedQty+"\n Unit: "+data.packagedUnit;
                        			if(data.spl!='null'){
                        				available="<div class='search-ava'> <h4 style='text-align: center; max-height: 100%; max-width:100%;font-size: 18px;     overflow: hidden;'>Available in your store</h4>   </div>";
                        				add=" <button disabled='disabled' type='button' class='btn  btn-round btn-flat btn-success'> <span class='md md-add'></span><div class='ripple-wrapper'></div> </button>";
                        			}
                        			else{
                        				available="";
                        				add=" <button type='button' onclick=\"fadeInPop('"+data.image+"','"+data.productName+"','"+data.productId+"');\" class='btn  btn-round btn-flat btn-success'> <span class='md md-add'></span><div class='ripple-wrapper'></div> </button>";
                        			}
                        			$("#sug-div").append(" <div class='col-md-3' title='"+title+"'> <div class='card bordered'> "+available+" <div class='card-content'><img src='"+data.image+"' style='max-width: 100%; max-height:190px;'> </div> <div class='address-neighbour'> <h4 style='text-align: center;  max-height: 12px;font-size: 14px;    overflow: hidden;'>"+data.productName+"</h4> <p style='text-align: center;'>Brand: "+data.brand+"</p> </div> <div class='card-action clearfix'>"+add+" </div> </div> </div>");
                        		}
                        		$("#spinner").html("");
                            	$("#find-btn").prop("disabled",false);
                        		},
                        		error: function(data){
                        			$("#results").empty();
                        			$("#spinner").html("");
                                	$("#find-btn").prop("disabled",false);
                        		}
                        	});
                        }
                        
                        function find(){
                        	getList($("#search-inp").val());
                        }

                        function fadeInPop(a,b,c){
                        	$("#frame-img").attr("src",a);
                        	$("#frame-name").text(b);
                        	$("#frame-id").val(c);
                        	$("#frame_pramod").fadeIn(200);
                            $("#overlay_pramod").fadeIn(200);
                        }

                        function fadeOutPop(){
                            $("#frame_pramod").fadeOut(200);
                            $("#overlay_pramod").fadeOut(200);
                        }
                        
                        function addToStore(){
                        	availableQty=$("#availableQty").val();
                        	minimumSellingQty=$("#minimumSellingQty").val();
                        	sellingPrice=$("#sellingPrice").val();
                        	mrp=$("#mrp").val();
                        	pi=$("#frame-id").val();
                        	$.ajax({
                        		url : '/add/product/tostore',
                        		data : {
                        			"availableQty":availableQty,
                        			"minimumSellingQty":minimumSellingQty,
                        			"sellingPrice":sellingPrice,
                        			"mrp":mrp,
                        			"pi":pi
                        			},
                        		type: "POST",
                        		success : function(data) {
                        			if(data=='success'){
                        				 $("#frame-id").val("");
                        				 $("#availableQty").val("");
                                    	 $("#minimumSellingQty").val("");
                                    	 $("#sellingPrice").val("");
                                    	 $("#mrp").val("");
                        				 $("#frame_pramod").fadeOut(200);
                                         $("#overlay_pramod").fadeOut(200);
                        			}
                        		},
                        		error: function(data){
                        			$("#results").empty();
                        		}
                        	});
                        	
                        }
                        </script>                 
<%@ include file="/master/footer.jsp" %>


    <!-- <link href="<c:url value='/assets/css/select2.min.css' />" rel="stylesheet" /> -->

<script src="<c:url value='/assets/js/select2.min.js' />"></script>

<script type="text/javascript">
  
    $(".js-example-basic-single").select2();

</script>