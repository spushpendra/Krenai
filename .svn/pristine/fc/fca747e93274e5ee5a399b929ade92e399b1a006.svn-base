  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:url var="firstUrl" value="/protected/showAll/1" />
<c:url var="lastUrl" value="/store/products/listing/${totalPages }" />
<c:url var="prevUrl" value="/store/products/listing/${current - 1}" />
<c:url var="nextUrl" value="/store/products/listing/${current + 1}" />
<!DOCTYPE html>
<html lang="en">
  
<head>
    <meta charset="utf-8">
    <!-- <link href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css" rel="stylesheet" /> -->
    
    <link href="https://cdn.datatables.net/1.10.11/css/dataTables.bootstrap.min.css" rel="stylesheet" />
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<c:url value='/assets/css/profile.update.css' />" rel="stylesheet" />
    <link href="<c:url value='/assets/css/data+_table.css' />" rel="stylesheet" />
    <script src="<c:url value='/assets/js/jquery-1.11.1.min.js' />"></script>
    <title>My Product Table - Krenai</title>\
    <style type="text/css">
    div.dataTables_wrapper div.dataTables_filter input{
        width: 90%;  
    }
    </style>
  </head>
  <body scroll-spy="" id="top" class=" theme-template-dark theme-pink alert-open alert-with-mat-grow-top-right">
	<div class="loading-message" id="loading-message">
      <button class="btn btn-warning btn-flat">Updating...<div class="ripple-wrapper"></div></button>
    </div>
   <div id="overlay_pramod"></div>
          <div id="frame_pramod">
            <i style="float:right; padding:0px; font-size:25px; color:#676767; padding:5px" class="fa fa-times-circle"></i>

            <div class="row">
              <div class="col-md-12">
                <div class="card pop-up-card-push bordered">
                  <div class="card-header">
                    <div class="row">
                      <div class="col-md-3">
                        <img id="prod-img" src="" style="height: 100px;">  
                      </div>
                      <div class="col-md-6">
                        <p id="productName"></p>
                        <span class="card-title">Change Product</span><br>
                        <p>Selling Details</p>
                      </div>
                      <div class="col-md-3">
                        <div class="form-group">
                          <div class="switch">
                          <h5 class="text-muted">Inventory Status</h5>
                          <p id="inventoryStatus" class=""></p>
                          </div>
                          </div>
                      </div>
                    </div>
                  </div>
                  <div class="card-content">
                    <div class="row">
                    <form class="form-floating" action="<c:url value="/product/list/modify"/>" onsubmit="return validateChangeProduct();" method="post">
                      <input type="hidden"  name="hidSellerProductListingId" id="hidSellerProductListingId" class="form-control"> 
                        <div class="col-md-6">
                        <div class="form-group filled">
                        <label for="inputEmail" class="control-label">Available Quantity</label>
                        <input type="text"  id="availableQty" name="availableQty" class="form-control" onkeyup="$(this).val($(this).val().replace(/[^0-9/]/g, ''));"> 
                        <div class="help-block with-errors validationText" id="availableQtyValidate"></div>
                        </div>
                      </div>
                      <div class="col-md-6">
                         <div class="form-group filled">
                        <label for="inputEmail" class="control-label">Minimum Selling Quantity</label>
                        <input type="text"  id="minimumSellingQty" name="minimumSellingQty" onchange="return minSaleQuantity();" class="form-control" onkeyup="$(this).val($(this).val().replace(/[^0-9/]/g, ''));"> 
                        <div class="help-block with-errors validationText" id="minimumSellingQtyValidate"></div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group filled">
                        <label for="inputEmail" class="control-label">MRP</label>
                        <input type="text"  id="mrp" name="mrp" class="form-control" onkeyup="$(this).val($(this).val().replace(/[^0-9/]/g, ''));"> 
                        <div class="help-block with-errors validationText" id="mrpValidate"></div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group filled">
                        <label for="inputEmail" class="control-label">Selling Price</label>
                        <input type="text" id="sellingPrice" onchange="return prodSellerPrice();" name="sellingPrice"  class="form-control" onkeyup="$(this).val($(this).val().replace(/[^0-9/]/g, ''));">  
                        <div class="help-block with-errors validationText" id="sellingPriceValidate"></div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="card-action card-action-push clearfix">
                  
                  <div class="pull-right"> 
                  <a class="btn btn-default fa-times-circle">Cancel</a>
                  <button class="btn btn-primary">Update</button>
                  </div>
                  
                  </div>
                  </form>
                </div>
            </div>        
          </div>
          </div>
          </div>
          </div>
             

    <main>
     <%@ include file="/master/sidebar.jsp" %>
      <div class="main-container">
        <nav class="navbar navbar-default navbar-fixed-top">
          <%@ include file="/master/header.jsp" %>
          
        </nav>
        <div class="main-content" autoscroll="true" bs-affix-target="" init-ripples="">
          <section class="todo-app">
            <div class="page-header">
             
                 <div class="row">
              <div class="col-md-3">
              <h1>      <i class="md md-list"></i>     Product List    </h1>
              </div>

              <div class="col-md-6">
                    <button class="btn btn-success btn-border"><i class="md-cloud-download"> </i>  Download</button>
                    <button class="btn btn-success btn-border"><i class="md-cloud-upload"> </i> Upload</button>
                    <a href="<c:url value="/products/add"/>"> 
                    <button class="btn btn-success btn-border"><i class=" md-add-circle-outline"> </i> Add New Products</button>
                    </a>
              </div>
               <div class="col-md-2">

               <div class="btn-group">
                    <button class="btn btn-default">Quick Filter</button>
                    <button class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
                    <ul class="dropdown-menu">
                      <li><a href="#"></a> </li>
                      <li><a href="#">Inventory Left - low to high</a> </li>
                      <li><a href="#">Inventory Left - high to low</a> </li>
                      <li><a href="#"> Selling Price - low to high</a></li>
                      <li><a href="#">Selling Price - high to low</a> </li>
                      <li><a href="#">Created Time - first to last</a> </li>
                      <li><a href="#">Created Time - last to first</a> </li>
                    </ul>
                  </div>

              </div>
             
              
              </div>
            </div>


              <div class="col-md-12 filters well white">
                <div class="btn-group" role="group" aria-label="Filter todo's">
                  
                  <a href="<c:url value="/store/products/listing/1"/>"><button class="btn btn-default filter <c:if test="${underline == 'all'}">active</c:if> ">All</button>
                  <span class="label label-warning">${productListingCount.all}</span>
                  </a>

                  <a href="<c:url value="/products/listing/active/1"/>"><button class="btn btn-default filter <c:if test="${underline == 'active'}">active</c:if> ">Active</button>
                    <span class="label  label-warning">${productListingCount.active}</span>
                  </a>
                  
                  <a href="<c:url value="/products/listing/stock/es"/>"><button class="btn btn-default filter <c:if test="${underline == 'es'}">active</c:if> ">Out Of Stock</button>
                    <span class="label label-warning">${productListingCount.emptyStock}</span>
                  </a>
                  
                  <a href="<c:url value="/products/listing/inactive/1"/>"><button class="btn btn-default filter <c:if test="${underline == 'inactive'}">active</c:if> ">Inactive</button>
                    <span class="label label-warning">${productListingCount.inactive}</span>
                  </a>

                  <a href="<c:url value="/products/listing/stock/gt5"/>"><button class="btn btn-default filter <c:if test="${underline == 'gt5'}">active</c:if> ">More Than 5</button>
                    <span class="label label-warning">${productListingCount.moreThan5}</span>
                  </a>
                  
                  <a href="<c:url value="/products/listing/stock/lt5"/>"><button class="btn btn-default filter <c:if test="${underline == 'lt5'}">active</c:if> ">Less Than 5</button>
                    <span class="label label-warning">${productListingCount.lessThan5}</span>
                  </a>
                  <a href="<c:url value="/products/listing/blocked"/>"><button class="btn btn-default filter <c:if test="${underline == 'blocked'}">active</c:if> ">Bocked</button>
                  <span class=" label label-warning">${productListingCount.blocked}</span>
                  </a>

                </div>
              </div>

            <div class="row m-b-40">

              <div class="col-md-12">
                <div class="card no-margin">

                  <div class="table-responsive white">
                  
                    <!-- <table class="table table-hover table-full table-full-small"> -->
                  <table id="" class="table table-striped table-bordered" cellspacing="0" width="100%">
                      <colgroup>
                        <col class="auto-cell-size">
                      </colgroup>
                      <c:if test="${not empty sellerProductListingMapList}">
                      <thead>
                          <tr>
                            <th>Image</th>
                            <th>Name</th>
                            <th>Cost Type</th>
                            <th>Price</th>
                            <th class="text-right">Status</th>
                         </tr>
                      </thead>
                      <tfoot>
                          <tr>
                            <th>Image</th>
                            <th>Name</th>
                            <th>Cost Type</th>
                            <th>Price</th>
                            <th class="text-right">Status</th>
                          </tr>
                      </tfoot>
                      </c:if>
                      <tbody>
                      <c:forEach var="ProductListing" items="${sellerProductListingMapList}">
						          <tr id="${ProductListing.sellerProductId}">
                          <td ng-bind-html="item.icon" class="f20">
	                         <c:if test="${not empty ProductListing.imageURL}">
	                          	<img width="150px" src="${ProductListing.imageURL}" >
	                         </c:if>
	                           
                          </td>
                          <td class="trx-nm"><h4 class="">${ProductListing.productName} (${ProductListing.brand})</h4></td>
                          
                          <td class="trx-head-lst">
                            <ul>
                              <li><p class="text-muted">MRP</p></li>
                              <li><p class="text-muted">Selling Price</p></li>
                              <li><p class="text-muted">Minimum Selling Quantity</p></li>
                              <li><p class="text-muted">Available Quantity</p></li>
                            </ul>
                          </td>

                          <td class="trx-head-lst">
                            <ul>
                              <input data-image="${ProductListing.imageURL}" type="hidden"  id="productName${ProductListing.sellerProductId}"  value="${ProductListing.productName}" />
                              <input type="hidden" id="hidSellerProductListingId${ProductListing.sellerProductId}"  value="${ProductListing.sellerProductId}" />
                              <li><p id="mrp${ProductListing.sellerProductId}" class="text-success">${ProductListing.mrp}</p></li>
                              <li><p id="sellingPrice${ProductListing.sellerProductId}" class="text-success">${ProductListing.sellingPrice}</p></li>
                              <li><p id="minimumSellingQty${ProductListing.sellerProductId}" class="text-success">${ProductListing.minimumSellingQty}</p></li>
                              <li><p id="availableQty${ProductListing.sellerProductId}" class="text-success">${ProductListing.availableQty}</p></li>
                            </ul>
                          </td>
                          <td class="text-right">

                      		<div class="form-group">
                          <div class="switch">
                          <label class="text-muted">
                            <input id="stockStatusId${ProductListing.sellerProductId}" type="checkbox" <c:if test="${ProductListing.availableQty != '0' && underline != 'inactive'}">value="active" checked="checked"</c:if> <c:if test="${ProductListing.availableQty == '0'}">value="inactive"</c:if><c:if test="${underline != 'inactive'}"> class="pramod" onchange="changeStockStatus('${ProductListing.sellerProductId}');changeProductStatus('${ProductListing.sellerProductId}');"</c:if> <c:if test="${underline == 'inactive'}"> onchange="changeProductStatus('${ProductListing.sellerProductId}');" </c:if>> <span class="lever"></span>
                          Active</label>
                          </div>
                          </div>
                   		  </td>
                        </tr>	
                        					      
					  </c:forEach>
                        <c:if test="${empty sellerProductListingMapList}">
                        <div class="row ">
			              <div class="col-md-12">
			                
			                  <div class="table-responsive"  style="text-align: center; margin-top: 20px;margin-bottom: 20px;">
			
			                    <img style="text-align: center;width: 150px;" src="<c:url value="/assets/img/ic_network_error_icon.png"/>">
			                    <h5>Keep Calm!</h5>
			                    <p><b>No products are here, why don't you try to add more products!!</b></p>
			                  
			                  </div>
			               
			              </div>
			            </div>
                        </c:if>
                      </tbody>
                    </table>
                  </div>
                  
                  <div class="card-action clearfix">
                    <div class="row">
                      <div class="col-lg-4 col-md-4 col-sm-5 col-xs-12"> 
                      
                      </div>
                      <div class="col-lg-8 col-md-8 col-sm-7 col-xs-12 text-right">
                        <ul class="pagination">
                           <c:if test="${not empty current}">
				          
				              <c:choose>
				               <c:when test="${currentIndex == 1}">
				                <li class="disabled"><a href="#">«</a></li>
				               </c:when>
				               <c:otherwise>
				               <li ><a href="${firstUrl}">«</a></li>
				               </c:otherwise>
				              </c:choose>
				              <c:forEach var="i" begin="${begin}" end="${end}">
				               <c:url var="pageUrl" value="/store/products/listing/${i}" />
				               <c:choose>
				                <c:when test="${i == current}">
				                 <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
				                </c:when>
				                <c:otherwise>
				                 <li><a href="${pageUrl}">${i}</a></li>
				                </c:otherwise>
				               </c:choose>
				              </c:forEach>
				              <c:choose>
				               <c:when test="${current == totalPages}">
				                <li class="disabled"><a href="#">»</a></li>
				               </c:when>
				               <c:otherwise>
				                <li><a href="${lastUrl}">»</a></li>
				               </c:otherwise>
				              </c:choose>
				             </ul>
				            </div><br/><br/>
				            </c:if>
				             </ul>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
             
            </div>
           </section>

              <div class="footer-buttons">
              <a href="<c:url value="/products/add"/>">
              <div class="btn btn-primary btn-round btn-lg" data-title="Add New Product" data-toggle="tooltip"><i class="md md-add"></i></div>
              </a>
            </div>

        </div>
      </div>
    </main>

  <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"></script>
<!-- <script src="<c:url value='/assets/js/dataTable.js' />"></script> -->
  <script type="text/javascript">
  $('#example').DataTable();
    // $('#example tfoot th').each( function () {
    //     var title = $(this).text();
    //     $(this).html( '<input type="text" placeholder="Search '+title+'" />' );

    // } );
 
    // // DataTable
    // var table = $('#example').DataTable();
    // // Apply the search
    // table.columns().every( function () { 
    //     var that = this;
 
    //     $( 'input', this.footer() ).on( 'keyup change', function () {
    //         if ( that.search() !== this.value ) { 
    //             that
    //                 .search( this.value )
    //                 .draw();
    //         }
    //     } );
    // } );
   // $('#example').DataTable();
   // $('#example tfoot th').each( function () {
   //      var title = $(this).text();
   //      $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
   //  } );
 
   //  // DataTable
   //  var table = $('#example').DataTable();
 
   //  // Apply the search
   //  table.columns().every( function () {
   //      var that = this;
 
   //      $( 'input', this.footer() ).on( 'keyup change', function () {
   //          if ( that.search() !== this.value ) {
   //              that
   //                  .search( this.value )
   //                  .draw();
   //          }
   //      } );
   //  } );

   </script> 
   <script type="text/javascript">

   function changeProductStatus(_spli){
      //alert(_spli);
      $.ajax({
      url : '/products/sellerProductChangeStatus',
      data : {
        "_spli":_spli
        },
      type: "POST",
      success : function(data) {
      
      if(data!=null){
        if(data.localeCompare("success")!=0){
          alert(data);
        }
        }
        
      },
      error: function(data){
        //alert();
      }
    });
    }

   function productDelete(id){
	   alert("delete");
   	$.ajax({
			url : '/products/sellerProductDelete',
			data : {
				"sellerProductId":id
				},
			type: "POST",
			success : function(data) {
			
			if(data!=null){
				alert(data);
				if(data.localeCompare("deleted")==0){
					$("#"+id+"").html("");
					$("#"+id+"").slideUp(500);
					
				}
				}
				else{
					alert("problem in deleting the current product.");
				
				}
			},
			error: function(data){
				//alert();
			}
		});
   }
   
   function changeLink(){
	   var value=$('#inputLink').val();
	   var link="/products/listing/stock/"+value;
	  $('#dynLink').attr("href", link);
   }
   
   function changeStockStatus(prodListId){
	 leverValue =  $("#stockStatusId"+prodListId).val();
	 //alert(leverValue);
	 minimumSellingQty=$("#minimumSellingQty"+prodListId).val();
	 mrp=$("#mrp"+prodListId).val();
	 availableQty=$("#availableQty"+prodListId).val();
	 sellingPrice=$("#sellingPrice"+prodListId).val();
	 minimumSellingQty=$("#minimumSellingQty"+prodListId).text();
	 mrp=$("#mrp"+prodListId).text();
	 availableQty=$("#availableQty"+prodListId).text();
	 sellingPrice=$("#sellingPrice"+prodListId).text();
	 productName=$("#productName"+prodListId).val();
	 productImage = $("#productName"+prodListId).attr("data-image");
	 // alert(productImage);
	 
	 if(leverValue.localeCompare("inactive")==0){
		 $("#minimumSellingQty").val("");
		 $("#mrp").val("");
		 $("#availableQty").val("");
		 $("#sellingPrice").val("");
		 $("#hidSellerProductListingId").val("");
		 $("#productName").text("");
		 
		 $("#minimumSellingQty").val(minimumSellingQty);
		 $("#mrp").val(mrp);
		 $("#availableQty").val(availableQty);
		 $("#sellingPrice").val(sellingPrice);
		 $("#hidSellerProductListingId").val(prodListId);
		 $("#inventoryStatus").text("Active");
		 $("#productName").text(productName);
		 $("#prod-img").attr("src", productImage);
		 
		 $("#stockStatusId"+prodListId).removeClass("pramod");
		 $("#stockStatusId"+prodListId).val("active");
	 }
	 else{
		 $("#minimumSellingQty").val("");
		 $("#mrp").val("");
		 $("#availableQty").val("");
		 $("#sellingPrice").val("");
		 $("#hidSellerProductListingId").val("");
		 $("#productName").text("");
		 
		 $("#minimumSellingQty").val(minimumSellingQty);
		 $("#mrp").val(mrp);
		 $("#availableQty").val("0");
		 $("#sellingPrice").val(sellingPrice);
		 $("#hidSellerProductListingId").val(prodListId);
		 $("#inventoryStatus").text("In Active");
		 $("#productName").text(productName);
		 $("#prod-img").attr("src", productImage);
		 
		 $("#stockStatusId"+prodListId).addClass("pramod");
		 $("#stockStatusId"+prodListId).val("inactive");
	 }
   }
   </script>

   <!--Pop Up-->
  
    <script type="text/javascript">
      $(document).ready(function () {
    $(".fa-times-circle").click(function () {

        $("#frame_pramod").fadeOut(200);
        $("#overlay_pramod").fadeOut(200);
    });
    });


  $(document).ready(function () {


    $(".pramod").click(function () {

        $("#frame_pramod").fadeIn(200);
        $("#overlay_pramod").fadeIn(200);
    });



    $("#overlay_pramod").click(function () {

        $("#frame_pramod").fadeOut(200);
        $("#overlay_pramod").fadeOut(200);
    });

});

    </script>
    <script type="text/javascript">

    function quantAvail(){
         var availQuant=$('#availableQty').val();
           if(availQuant==''){ 
                        $("#availableQtyValidate").text("Please input Available Quantity").show();
                            $("#availableQty").css("border-bottom", "2px  solid #B71C1C");
                            $("#availableQty").focus();
                            return false;
                      }
            else{
                        $("#availableQtyValidate").text("Please input Available Quantity").hide();
                            $("#availableQty").css("border-bottom", "2px  ");
                            return true;
            }
    }
    function quantitySale(){
       var minSaleQuant=$('#minimumSellingQty').val();
            if(minSaleQuant==''){
                        $("#minimumSellingQtyValidate").text("Please input Selling Quantity").show();
                            $("#minimumSellingQty").css("border-bottom", "2px  solid #B71C1C");
                            $("#minimumSellingQty").focus();
                            return false;
                      }
            else{
                        $("#minimumSellingQtyValidate").text("Please input Selling Quantity").hide();
                            $("#minimumSellingQty").css("border-bottom", "2px  ");
                            return true;

            }
    }

    function priceOfMrp(){
      var mrPrice=$('#mrp').val();
        if(mrPrice==''){
                        $("#mrpValidate").text("Please input mrp").show();
                            $("#mrp").css("border-bottom", "2px  solid #B71C1C");
                            $("#mrp").focus();
                            return false;
                      }
        else{
                       $("#mrpValidate").text("Please input mrp").hide();
                            $("#mrp").css("border-bottom", "2px  ");
                            return true;
        }
    }

    function priceSelling () {
        var sellPrice=$('#sellingPrice').val();
          if(sellPrice==''){
                        $("#sellingPriceValidate").text("Please input selling Price").show();
                            $("#sellingPrice").css("border-bottom", "2px  solid #B71C1C");
                            $("#sellingPrice").focus();
                            return false;
                      }
          else{
             $("#sellingPriceValidate").text("Please input selling Price").hide();
                            $("#sellingPrice").css("border-bottom", "2px  ");
                            return true;
          }
    }


        function validateChangeProduct(){

          quantAvail();
          quantitySale();
          priceOfMrp();
          priceSelling();
          prodSellerPrice();
          minSaleQuantity();
                   if(quantAvail()){
                   if(quantitySale()){
                    if(priceOfMrp){
                      if(priceSelling){
                      if(prodSellerPrice()){
                          if(minSaleQuantity()){
                            return true;
                          }
                      }
                    }
                  }
                }
              }
                      return false;
        }


        function minSaleQuantity(){
      var maxValue=parseFloat($('#minimumSellingQty').val());
      var prodVal =parseFloat($('#availableQty').val());

      if( $('#minimumSellingQty').val() > 10 ){
       
        $("#minimumSellingQtyValidate").text("Sale Quantity's value must be from 1-10.").show();

        $("#minimumSellingQty").css("border-bottom", "2px  solid #FF0000");
        $("#minimumSellingQty").focus();
        return false;
      }
      else if(maxValue > prodVal){
        $("#minimumSellingQtyValidate").text("Sale Quantity's value must be less than available quantity").show();
        $("#availableQty").css("border-bottom", "2px  solid #FF0000");
        $("#minimumSellingQty").css("border-bottom", "2px  solid #FF0000");
      }

      else{
          $("#minimumSellingQtyValidate").text("Sale Quantity's value must be 1 - 10").hide();
          $("#minimumSellingQty").css("border-bottom", "2px ");
          $("#availableQty").css("border-bottom", "2px  ");
          return true;
      }
    }


    function prodSellerPrice(){

      var prd=parseFloat($('#mrp').val());
      var sellPrc=parseFloat($('#sellingPrice').val());
      // var sellPrc=parseFloat($('#abcd').val(30/100*prd));
      var thirtyMax= prd-(30/100*prd);
      if($('#sellingPrice').val()<1){
          $("#sellingPriceValidate").text("Selling Price value must be greater than zero").show();
        $("#sellingPrice").css("border-bottom", "2px  solid #FF0000");
        $("#sellingPrice").focus();
        return false;
      }
      else if(sellPrc<thirtyMax )
      { 
         $("#sellingPriceValidate").text("Selling Price discount is not more than 30%").show();
        $("#sellingPrice").css("border-bottom", "2px  solid #FF0000");
        $("#sellingPrice").focus();
        return false;
            
      }
      else if(sellPrc>prd )
      { 
         $("#sellingPriceValidate").text("Selling Price discount is not more than mrp").show();
        $("#sellingPrice").css("border-bottom", "2px  solid #FF0000");
        $("#sellingPrice").focus();
        return false;
            
      }
      else{
        $("#sellingPriceValidate").text("Selling Price discount is not more than 30% Or Selling Price value is not greater than MRP Price").hide();
        $("#sellingPrice").css("border-bottom", "2px  ");
        return true;
      }
}
    

    </script>

<%@ include file="/master/footer.jsp" %>