<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html lang="en">
  
<head>
    <title>Total Orders - Krenai</title>

    <link href="https://cdn.datatables.net/1.10.11/css/dataTables.bootstrap.min.css" rel="stylesheet" />
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/css/vendors.min.css" rel="stylesheet" />
    <link href="assets/css/styles.min.css" rel="stylesheet" />
    <link href="assets/css/push.css" rel="stylesheet" />
    <script charset="utf-8" src="http://maps.google.com/maps/api/js?sensor=true"></script>
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
    <main>
      <%@ include file="/master/sidebar.jsp" %>
      <div class="main-container">
        <nav class="navbar navbar-default navbar-fixed-top">
           <%@ include file="/master/header.jsp" %>
        </nav>
        <div class="main-content" autoscroll="true" bs-affix-target="" init-ripples="">
          <section class="todo-app tables-data">
             
             <div class="page-header">
              
                  <div class="row">
              <div class="col-md-10">
              <h1>      <i class="md md-list"></i>     Orders    </h1>
              </div>
            
              <div class="col-md-2">

                  <div class="btn-group">
                        <button class="btn btn-default dropdown-toggle" data-toggle="dropdown"> Order Filter <span class="caret"></span> </button>
                        <ul class="dropdown-menu">
                          <li><a href="#">Order Cost - Low to high</a> </li>
                          <li><a href="#">Order Cost - High to low</a> </li>
                          <li><a href="#">Order Date - Old to ew</a> </li>
                          <li><a href="#">Order Date - New to old</a> </li>
                        </ul>
                      </div>
                  </div>
              
              </div>

              </div>
                
                  <div class="col-md-12 filters well white">
                <div class="btn-group" role="group" aria-label="Filter todo's">
                  
                  <a href="<c:url value="/orders/list/all"/>"><button class="btn btn-default filter <c:if test="${underline == 'all' }">active</c:if> ">All</button>
                  <span class="label label-warning">${orderCount.all}</span>
                  </a>
					
                  <a href="<c:url value="/orders/list/active"/>"><button class="btn btn-default filter <c:if test="${underline == 'active' }">active</c:if>">New Orders</button>
                    <span class="label label-warning">${orderCount.active}</span>
                  </a>
                  
                  <a href="<c:url value="/orders/list/completed"/>"><button class="btn btn-default filter <c:if test="${underline == 'completed' }">active</c:if>">Delivered</button>
                    <span class="label  label-warning">${orderCount.completed}</span>
                  </a>
                  
                  <a href="<c:url value="/orders/list/cancelled"/>"><button class="btn btn-default filter <c:if test="${underline == 'cancelled' }">active</c:if>"> Cancelled/Returned</button>
                    <span class="label label-warning">${orderCount.cancelled}</span>
                  </a>


                </div>
              </div>

           <div class="row m-b-60">
              <div class="col-md-12">
                <div class="card bordered">
                  <div class="table-responsive">
                    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                      <c:if test="${not empty cartList}">
	                      <thead>
	                        <tr>
	                          <th>Profile</th>
	                          <th>Order ID</th>
	                          <th>Customer</th>
	                          <th>Status</th>
	                          <th>Date</th>
	                          <th class="text-right">Actions</th>
	                        </tr>
	                      </thead>
                        <tfoot>
                          <tr>
                            <th>Profile</th>
                            <th>Order ID</th>
                            <th>Customer</th>
                            <th>Status</th>
                            <th>Date</th>
                            <th class="text-right">Actions</th>
                          </tr>
                        </tfoot>
	                      <tbody>
                      </c:if>
                         <c:forEach var="cartItem" items="${cartList}">
                         	<tr>
                          <td>
                            <form action="<c:url value="/orders/user/profile"/>" method="post">
                            <input type="hidden" value="${cartItem.orderId}" name="orderId"/>
                            <button style="background-color: transparent;border: none;" type="submit"><img src="${cartItem.user.profileImageUrl}" class="trx-user-image"></button>
                            </form>
                          </td>  
                          
                          <td>
                          <form action="<c:url value="/orders/user/products"/>" method="post">
            							<input type="hidden" value="${cartItem.orderId}" name="orderId"/>
            							<input style="background-color: transparent;border: none;" type="submit" value="${cartItem.orderId}" />
            							</form>
            						  </td>
                          <td>${cartItem.user.firstName} ${cartItem.user.lastName}</td>
                          <td>${cartItem.status.statusValue}</td>
                          <td>${cartItem.orderDate}</td>
                          <td class="text-right">
                            
                           <span class="btn btn-link btn-round" data-toggle="tooltip" data-placement="top" data-original-title="Product List">
                           <form action="<c:url value="/orders/user/products"/>" method="post">
                           <input type="hidden" value="${cartItem.orderId}" name="orderId"/>
                           <button type="submit" style="background-color: transparent;border: none;"><i class="md  md-shopping-basket"></i></button>
                           </form>
                           </span>

              				<button 
              					<c:if test="${cartItem.status.statusValue != 'ordered'}">style="color:#9c9c9c;" onclick="$('#msg-${cartItem.orderId}').text('Order cannot be accepted after ${cartItem.status.statusValue} status.');"</c:if>  
              					<c:if test="${cartItem.status.statusValue == 'ordered'}"> onclick="$('#hidOrderId').val('${cartItem.orderId}')" </c:if> 
              					type="button" class="btn btn-link btn-round"  data-toggle="modal" data-target="#orderProcess"  data-placement="top" title="Accept and Keep order in progress">
              					<i class="md  md-autorenew"></i>
              				</button>
							
							<button <c:if test="${cartItem.status.statusValue != 'ordered' && cartItem.status.statusValue != 'inprocess'}">style="color:#9c9c9c;"  onclick="$('#msg-${cartItem.orderId}').text('Order cannot be made out for delivery after ${cartItem.status.statusValue} status.');"</c:if>  <c:if test="${cartItem.status.statusValue == 'ordered' || cartItem.status.statusValue == 'inprocess'}">onclick="outdel('${cartItem.orderId}');"</c:if> type="button" class="btn btn-link btn-round" data-toggle="tooltip" data-placement="top" data-original-title="Order out for delivery.">
								<i class="md md-directions-bike"></i>
							</button>
                           
                            <button <c:if test="${cartItem.status.statusValue != 'ordered' && cartItem.status.statusValue != 'inprocess' && cartItem.status.statusValue != 'outForDelivery'}">style="color:#9c9c9c;"  onclick="$('#msg-${cartItem.orderId}').text('Order cannot be made delivered after ${cartItem.status.statusValue} status.');"</c:if>  <c:if test="${cartItem.status.statusValue == 'ordered' || cartItem.status.statusValue == 'inprocess' || cartItem.status.statusValue == 'outForDelivery'}">onclick="deliv('${cartItem.orderId}');"</c:if> type="button" class="btn btn-link btn-round" data-toggle="tooltip" data-placement="top" data-original-title="Delivered">
                             	<i class="md  md-done-all"></i>
                            </button>
                          
                           <button <c:if test="${cartItem.status.statusValue == 'cancelled' || cartItem.status.statusValue == 'delivered'  || cartItem.status.statusValue == 'returned'}">style="color:#9c9c9c;"</c:if>  <c:if test="${cartItem.status.statusValue != 'cancelled' || cartItem.status.statusValue != 'returned' || cartItem.status.statusValue != 'delivered'}">onclick="returned('${cartItem.orderId}');"</c:if> type="button" class="btn btn-link btn-round" data-toggle="tooltip" data-placement="top" data-original-title="Returned">
                             	<i class="md  md-keyboard-return"></i>
                            </button>
                          
                           <button 
                           <c:if test="${cartItem.status.statusValue == 'delivered' || cartItem.status.statusValue == 'outForOrder' || cartItem.status.statusValue == 'returned' || cartItem.status.statusValue == 'cancelled'}">style="color:#9c9c9c;"</c:if>   
                           <c:if test="${cartItem.status.statusValue != 'delivered' || cartItem.status.statusValue != 'outForOrder' || cartItem.status.statusValue != 'returned' || cartItem.status.statusValue != 'cancelled'}">onclick="$('#hidOrderCanId').val('${cartItem.orderId}')"</c:if> 
                           type="button" class="btn btn-link btn-round" 
                             data-toggle="modal" data-target="#orderCancel"  data-placement="top" title="Cancel Order">
                             	<i class="md  md-cancel"></i>
                            </button>
                          
                          	<span class="btn btn-link btn-round"  data-toggle="tooltip" data-placement="top" data-original-title="Customer Details">
                            <form action="<c:url value="/orders/user/profile"/>" method="post">
              				<input type="hidden" value="${cartItem.orderId}" name="orderId"/>
              				<button type="submit" style="background-color: transparent;border: none"><i class="md md-location-history"></i></button>
              				</form>
                            </span>
							
							<p class="btn-link pull-left" id="msg-${cartItem.orderId}"></p>
                            
                          </td>
                        </tr>
                         </c:forEach>
                         <c:if test="${empty cartList}">
                        <div class="row m-b-60">
			              <div class="col-md-12">
			                <div class="card bordered">
			                  <div class="card-header"> <span class="card-title">Pending Orders</span><span style="float:right"><input type="text" placeholder="Find By Order-Id" autofocus="">
			                  <button type="button" class="btn btn-link btn-round" data-toggle="tooltip" data-placement="top" data-original-title="Order Filter"><i class="md md-search"></i></button>
			                  </span> </div>
			                  <div class="table-responsive"  style="text-align: center; margin-top: 20px;margin-bottom: 20px;">
			
			                    <img style="text-align: center;width: 150px;" src="<c:url value="/assets/img/ic_network_error_icon.png"/>">
			                    <h5>Keep Calm!</h5>
			                    <p><b>No Orders are here, why don't you try to give some exciting offers!!</b></p>
			                  
			                  </div>
			                </div>
			              </div>
			            </div>
                        </c:if>
                      </tbody>
                    </table>
                  </div>
                  <!-- <div class="card-action clearfix">
                   <c:if test="${not empty cartList}">
                    <div class="row">
                      <div class="col-lg-4 col-md-4 col-sm-5 col-xs-12"> 
                      <a href="#" class="btn btn-link btn-icon" title="Refresh Table"><i class="md md-refresh pink-text"></i></a> 
                      <a href="#" class="btn btn-link btn-icon" title="Add Order"><i class="md md-add-circle pink-text"></i></a>
                      <a href="#" class="btn btn-link btn-icon" title="View Order"><i class="md md-mode-edit pink-text"></i></a>
                      <a href="#" class="btn btn-link btn-icon" title="Print Invoice"><i class="md md-print pink-text"></i></a>
                      <a href="#" class="btn btn-link btn-icon" title="Delete Order"><i class="md  md-delete pink-text"></i></a>
                      </div>
                      <div class="col-lg-8 col-md-8 col-sm-7 col-xs-12 text-right">
                        <ul class="pagination">
                          <li class="disabled"><a href="#">«</a></li>
                          <li class="active"><a href="#">1</a></li>
                          <li><a href="#">2</a></li>
                          <li><a href="#">3</a></li>
                          <li><a href="#">4</a></li>
                          <li class="hidden-xs"><a href="#">5</a></li>
                          <li><a href="#">»</a></li>
                        </ul>
                      </div>
                    </div>
                   </c:if>
                  </div> -->
                </div>
              </div>
            

            </div>
          </section>
        </div>
      </div>
    </main>
    
    
  	<!-- Modal create cart-->
  <div class="modal fade " id="orderProcess" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Choose Expected Delivery time & save</h4>
        </div>
        <div class="modal-body">
          <input type="hidden" id="hidOrderId">
         	<table class="table">
			<thead>
			<tr>
			<th style="width:10%" class="text-left">Select Delivery Time</th>
			</tr>
			</thead>
			<tbody>
			
			<tr>
			
			<td>
			<div class="form-group">
              
              <div class=" right">
                 <i class=""></i>
                  
                <input  id="getpoptime" type="text" class="input form-control" placeholder="Enter expected delivery time, Ex:1Hrs 30Mins">
               <span id="getpoptimespan"></span>
               </div>
           </div>
			
			</td>
			
			
			</tr>
			
			</tbody></table>
          	
          </div>
          <div class="modal-footer">
        
          <button type="button" class="btn btn-default" onclick="inprocess($('#hidOrderId').val())">Save</button> 
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
		</div>
        
      </div>
      
    </div>



<!-- order cancel popup -->

	<!-- Modal create cart-->
  <div class="modal fade " id="orderCancel" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Enter Cancel Reason & save</h4>
        </div>
        <div class="modal-body">
          <input type="hidden" id="hidOrderCanId">
         	<table class="table">
			<thead>
			<tr>
			<th style="width:50%" class="text-left">Enter Reason</th>
			</tr>
			</thead>
			<tbody>
			
			<tr>
			
			<td>
			<div class="form-group">
              
              <div class=" right">
                 <i class=""></i>
                  
                <select id="cacelReason" style="width: 100%;"  onchange='changeOrderStatus(this.value)'>
					<option value="">Enter Reason, Ex:Dont have stock</option>
					<option value="Dont have stock"  selected>Dont have stock</option>
					<option value="Delivery Boy is not available" >Delivery Boy is not available</option>
					<option value="Store is closed today"  >Store is closed today</option>
				</select>
               <span id="cacelReasonspan"></span>
               </div>
           </div>
			
			</td>
			
			
			</tr>
			
			</tbody></table>
          	
          </div>
          <div class="modal-footer">
        
          <button type="button" class="btn btn-default" onclick="cancel($('#hidOrderCanId').val())">Save</button> 
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
		</div>
        
      </div>
      
    </div>



<!-- order cancel popup -->
    
    
  <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"></script>

  <script type="text/javascript">

   $('#example').DataTable();
   // var tableTools = new $.fn.dataTable.TableTools( table, {
   //      "buttons": [
   //          "copy",
   //          "csv",
   //          "xls",
   //          "pdf",
   //          { "type": "print", "buttonText": "Print me!" }
   //      ]
   //  } );
      
   //  $( tableTools.fnContainer() ).insertAfter('div.info');

   function inprocess(car){
	   getpoptime = $('#getpoptime').val();
	   if(getpoptime.length<1){
		   $('#getpoptime').css('border', '1px solid #ff0000');
		   $('#getpoptimespan').text('Enter proper expected time')
		   return;
	   }
	   $.ajax({
		   url: "/supplier/order/update/accepted",
		   type: "POST",
		   data: {
			   "or_": car,
			   "getpoptime": getpoptime
		   },
		   success: function(data){
			   //alert(data);
			   $('#orderProcess').modal('hide');
			   $('#getpoptimespan').text("");
			   if(data.localeCompare("failed")==0){
				   location.reload();
			   }
		   },
		   error: function(data){
			   
		   }
	   });
   }
   
   function outdel(car){
	   //alert(car);
	   $.ajax({
		   url: "/supplier/order/update/outdeli",
		   type: "POST",
		   data: {
			   "or_": car
		   },
		   success: function(data){
			   //alert(data);
			   if(data.localeCompare("failed")==0){
				   location.reload();
			   }
		   },
		   error: function(data){
			   
		   }
	   });
   }
   
   function deliv(car){
	   //alert(car);
	   agent = $("#agent-name").val();
	   agent = "Anand";
	   $.ajax({
		   url: "/supplier/order/update/deliv",
		   type: "POST",
		   data: {
			   "or_": car,
			   "agent": agent
		   },
		   success: function(data){
			   //alert(data);
			   if(data.localeCompare("failed")==0){
				   location.reload();
			   }
		   },
		   error: function(data){
			   
		   }
	   });
   }
   
   function returned(car){
	   //alert(car);
	   reason = $("#reason").val();
	   reason = "Not Specified";
	   $.ajax({
		   url: "/supplier/order/update/returned",
		   type: "POST",
		   data: {
			   "or_": car,
			   "reason": reason
		   },
		   success: function(data){
			  // alert(data);
			   if(data.localeCompare("failed")==0){
				   //location.reload();
				   //alert("");
				   $("#msg-"+car).text("Order status can not be returned once it is delivered or cancelled.");
			   }
		   },
		   error: function(data){
			   
		   }
	   });
   }
   
   function cancel(car){
	   cacelReason = $('#cacelReason').val();
	   if(cacelReason.length<3){
		   $('#cacelReason').css('border', '1px solid #ff0000');
		   $('#cacelReasonspan').text("Enter proper reason");
		   return;
	   }
	   $.ajax({
		   url: "/supplier/order/update/cancel",
		   type: "POST",
		   data: {
			   "or_": car,
			   "cacelReason": cacelReason
		   },
		   success: function(data){
			  // alert(data);
			  $('#cacelReasonspan').text();
			  $('#orderCancel').modal('hide');
			   if(data.localeCompare("failed")==0){
				   //location.reload();
				   $("#msg-"+car).text("Order cannot be cancelled once it is returned or already cancelled.");
			   }
		   },
		   error: function(data){
			   
		   }
	   });
   }
   
   </script>   
   
  
<%@ include file="/master/footer.jsp" %>