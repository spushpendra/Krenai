  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!--Custom Css -->
  <link href="<c:url value='/assets/css/custom.css' />" rel="stylesheet" />
  <title>Dashboard - Krenai Seller</title>
  <link href="assets/css/vendors.min.css" rel="stylesheet" />
  <link href="assets/css/styles.min.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="assets/css/push.css">
  <!-- Live  -->
	
		<script type="text/javascript" src="<c:url value="/assets/my-js/live/jquery.urlive.js"/>"> </script>
	    <link href="<c:url value="/assets/css/live/jquery.urlive.css"/>" rel="stylesheet" />
	    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"> </script>
	    <script type="text/javascript" src="<c:url value="/assets/my-js/live/jquery.urlive.min.js"/>"></script>
	<!-- Live  -->
	<script charset="utf-8" src="http://maps.google.com/maps/api/js?sensor=true"></script>
	<style>
	.overlay {
	height: 100%;
    width: 100%;
    position: absolute;
    z-index: 10;
    background-color: rgba(187, 187, 187, 0.32);
    top: 0;
    left: 0;
    opacity: 0.6;
    text-align: center;
    cursor: progress;
	}
	</style>
</head>
<body scroll-spy="" id="top" class=" theme-template-dark theme-pink alert-open alert-with-mat-grow-top-right">
  <div id="overlay_pramod">
          
          
          </div>
          <div id="frame_pramod">
            <i style="float:right; padding:0px; font-size:25px; color:#676767; padding:5px" class="fa fa-times-circle"></i>

            <div class="row">
              <div class="col-md-12">
                <div class="card pop-up-card-push bordered">
                  <div class="card-header">
                    <div class="row">
                      <div class="col-md-12">
                        <p id="productName"></p>
                        <span class="card-title" style="margin-left:340px">Raise Query</span><br>
                      </div>
                      
                  </div>
                  <div class="card-content">
                    <div class="row">

                        <div class="col-md-12">
                        <div class="form-group filled">
                        <input type="hidden" name="queryId" value="0">
                        <input type="hidden" name="supplier" value="${supplier.supplierId}">
                        <label for="raiseQuery" class="control-label">Query</label>
                        <textarea  id="raiseQueryId" name="raiseQuery" onkeyup="enableDisabledPost()" placeholder="Input your Query. Here.." class="form-control"></textarea> 
                        <div class="help-block with-errors validationText" id="availableQtyValidate"></div>
                        </div>

                      </div>
                      
                    </div>
                  </div>
                    
                  <a class="btn btn-default fa-times-circle">Cancel</a>
                  <button class="btn btn-primary" id="submitQuery" disabled onclick="querySave();">Submit</button>
                  </div>
                  
                  </div>
                  
                </div>
            </div>        
          </div>
          </div>
          </div>
          </div>
  <main>
    <%@ include file="master/sidebar.jsp" %>
      <div class="main-container">
        <nav class="navbar navbar-default navbar-fixed-top">
          <%@ include file="master/header.jsp" %>
                </nav>
                <div class="main-content" autoscroll="true" bs-affix-target="" init-ripples="">
                  <div class="dashboard grey lighten-3">
                    <div class="row no-gutter">
                      <div class="col-sm-12 col-md-12 col-lg-9">
                        <div class="p-20 clearfix">
                          <div class="pull-right">
                           <a class="btn btn-round-sm btn-link pramod" data-toggle="tooltip" title="Raise Query">
                           <i class="md md-games"></i>
                           </a>
                           <a href="pages-material-bird.html" target="_blank" class="btn btn-round-sm btn-link" data-toggle="tooltip" title="Write Quick Note">
                           <i class="md md-book"></i>
                           </a>
                           <a href="pages-material-bird.html" target="_blank" class="btn btn-round-sm btn-link" data-toggle="tooltip" title="Add new user">
                           <i class="md md-person-add"></i>
                           </a>
                          </div>
                          <h4 class="grey-text">          <i class="md md-dashboard"></i>          <span class="hidden-xs">Dashboard</span>  <span class="btn-link query-msg" style="text-align:center;margin-left:20%"></span>     </h4>  </div>
                          <div class="p-20 no-p-t">

                          <div class="row gutter-14">
                               <div class="col-md-12">
                            <div class="well white">
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
                             
                                <fieldset>
                                  <h5>Status <span class="btn-link post-msg"></span> </h5> 
                                  <!-- /.row -->
                                  <div class="form-group div-overlay">
                                    <div class="input-group">
                                      <input type="text" class="form-control" id="feedMessage" onkeyup="enableDisabledFeedPost()" placeholder="What's in your mind..."> 
                                      <span class="input-group-btn p-l-10"  id="post-btn">
                                      <button id="postButtonId" class="btn btn-primary" disabled>Post<div class="ripple-wrapper"></div></button></span> 
                                    </div>
                                    <div class="row" id="feeds-action">
                                    <div class="col-sm-6">
                                    
                                    <label id="add-img" for="feedImage"  class="btn btn-lg btn-round btn-link" data-toggle="tooltip" title="Add Photos">
                                   <span class="md-camera-alt" style="margin-top: 35%;"></span>
                                    </label>
                                    <input style=" position: absolute;height: 0px; width: 0px;overflow: hidden; z-index: -1;" onchange="loadFile(event,'user-upload-file');" onkeyup="enableDisabledFeedPost()" accept=".jpg,.jpeg,.png"  type="file" name="feedImage" id="feedImage">
                                    
                                    <button style="display:none;" id="remove-img" type="button" class="btn btn-lg btn-round btn-link" data-toggle="tooltip" title="Remove Photo"><span class="md-close"></span></button>
                                    
                                
								                  <div class="push-icon  pull-right" style="margin-right:50% ;margin-top: 3%;azimuth:grad; ">
								            <!-- <select class="btn btn-default" id="feed-for">
								                <option value="public"><i class="fa fa-map-marker"></i> Public</option>
								                <option value="followers">Followers</option>
								            </select> -->


                            <div class="btn-group">
                              <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> Post Type <span class="caret"></span> <div class="ripple-wrapper"></div></button>
                              <ul class="dropdown-menu">
                                <li><a href="#">Customers Only</a> </li>
                                <li><a href="#">Choose Customer</a> </li>
                                <li><a href="#">Public</a> </li>
                              </ul>
                            </div>
								        </div>                      
                                    </div>
                                    
                                    <div class="col-sm-6 postinfo push-postinfo">
								    </div>
								    
									    <div style="clear: both;"></div>
								        <span class="loading" style="display:none; ">
								        <svg width='24px' height='24px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' class='uil-ring-alt'><rect x='0' y='0' width='100' height='100' fill='none' class='bk'></rect><circle cx='50' cy='50' r='40' stroke='#f0f0f0' fill='none' stroke-width='10' stroke-linecap='round'></circle><circle cx='50' cy='50' r='40' stroke='#BA0021' fill='none' stroke-width='6' stroke-linecap='round'><animate attributeName='stroke-dashoffset' dur='2s' repeatCount='indefinite' from='0' to='502'></animate><animate attributeName='stroke-dasharray' dur='2s' repeatCount='indefinite' values='150.6 100.4;1 250;150.6 100.4'></animate></circle></svg>
								        </span>
								        <div class="urlive-container" id="data" style="margin-top: 10px; width: 93%; padding-left: 12px;"></div>
									    <div  id="user-upload" style="text-align: center;">
									    	<img id="user-upload-file" src="" style="max-width:275px; width: inherit;">
									    </div>
                                    </div>
                                    
                                  </div>
                                  <div class="overlay" style="display:none;">
                                  	<svg style="margin-top: 10.45%;" width='24px' height='24px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' class='uil-ring-alt'><rect x='0' y='0' width='100' height='100' fill='none' class='bk'></rect><circle cx='50' cy='50' r='40' stroke='#f0f0f0' fill='none' stroke-width='10' stroke-linecap='round'></circle><circle cx='50' cy='50' r='40' stroke='#BA0021' fill='none' stroke-width='6' stroke-linecap='round'><animate attributeName='stroke-dashoffset' dur='2s' repeatCount='indefinite' from='0' to='502'></animate><animate attributeName='stroke-dasharray' dur='2s' repeatCount='indefinite' values='150.6 100.4;1 250;150.6 100.4'></animate></circle></svg>
                                  </div>
                                </fieldset>


                            </div>

                          </div>
                          </div>

                            <div class="row gutter-14">
                              <div class="col-md-12">
                                <div class="card">
                                  <div class="card-header relative">
                                    <div class="card-title">Orders & Sales Summary</div>
                                    <div class="small grey-text">Last 30 days</div>
                                    
                                  </div>
                                  <div class="card-content p-10">
                          <div class="row">
                            <div class="col-md-2 text-center" style="border-right: 1px #F0F0F0 solid;">
                              <h3 class="no-margin w300">${orderSaleSummary.saleCompleted}</h3>
                              <p class="grey-text w600">Sale Completed</p>
                            </div>
                            <div class="col-md-2 text-center" style="border-right: 1px #F0F0F0 solid;">
                              <h3 class="no-margin w300">${orderSaleSummary.saleReturned}</h3>
                              <p class="grey-text w600">Sales Returned</p>
                            </div>                 
                            <div class="col-md-2 text-center" style="border-right: 1px #F0F0F0 solid;">
                              <h3 class="no-margin w300">${orderSaleSummary.orderBooked}</h3>
                              <p class="grey-text w600">Orders Booked</p>
                            </div>
                            <div class="col-md-2 text-center" style="border-right: 1px #F0F0F0 solid;">
                              <h3 class="no-margin w300">${orderSaleSummary.processed}</h3>
                              <p class="grey-text w600">Processed</p>
                            </div>
                            <div class="col-md-2 text-center" style="border-right: 1px #F0F0F0 solid;">
                              <h3 class="no-margin w300">${orderSaleSummary.orderReturns}</h3>
                              <p class="grey-text w600">Orders Returned</p>
                            </div>
                            <div class="col-md-2 text-center">
                              <h3 class="no-margin w300">${orderSaleSummary.cancelOrders}</h3>
                              <p class="grey-text w600">Cancel Orders</p>
                            </div>
                          </div>
                        </div>
                                </div>
                              </div>
                            </div>
                            </div>
                          <div class="p-20 no-p-t">
                            <div class="row gutter-14">
                              <div class="col-md-12">
                                <div class="card">
                                  <div class="card-header relative">
                                    <div class="card-title">Orders</div>
                                    <div class="small grey-text">Last 30 days</div>
                                    
                                  </div>
                                  <div class="list-group">
                                          
                                           <section class="icons">
                                            <div class="page-header">
                                                <div class="col-lg-4 col-sm-4 col-xs-6">
                                                <h5>Pending Orders</h5>
                                                </div>
                                                <div class="col-lg-4 col-sm-4 col-xs-6">
                                                <h5>Complete Orders</h5>
                                                </div>
                                                <div class="col-lg-4 col-sm-4 col-xs-6">
                                                <h5>Cancelled Orders</h5>
                                                </div>
                                             </div>
                                            <!-- Icon sets -->
                                            <div class="row icon-set">
                                              <div class="col-md-12">
                                              
                                              <a href="<c:url value="/orders/list/active"/>">
                                              <div class="col-lg-4 col-sm-4 col-xs-6">
                                              <div class="icon-holder"><i class="md md-directions-bike"></i> <span class="trx-py-text">${orderCountSession.active}</span> </div>
                                              </div>
                                              </a>
                                              
                                              <a href="<c:url value="/orders/list/completed"/>">
                                              <div class="col-lg-4 col-sm-4 col-xs-6">
                                                <div class="icon-holder"><i class="md md-done-all"></i> <span class="trx-py-text">${orderCountSession.completed}</span></div>
                                              </div>
                                              </a>
                                              <a href="<c:url value="/orders/list/cancelled"/>">
                                              <div class="col-lg-4 col-sm-4 col-xs-6">
                                                <div class="icon-holder"><i class="md md-highlight-remove"></i><span class="trx-py-text">${orderCountSession.cancelled}</span></div>
                                              </div>
                                              </a>
                                            </div>
                                            </section>
                                  </div>
                                </div>
                              </div>
                            </div>

                            <div class="row gutter-14">
                              <div class="col-md-12">
                                <div class="card">
                                  <div class="card-header relative">
                                    <div class="card-title">PRODUCTS LISTING</div>
                                    <div class="small grey-text">Total Stock, Out of Stock, Inactive Products</div>
                                    
                                  </div>
                                  <div class="list-group">
                                          
                                           <section class="icons">
                                            <div class="page-header">
                                                <div class="col-lg-4 col-sm-4 col-xs-6">
                                                <h5>In Stock</h5>
                                                </div>
                                                <div class="col-lg-4 col-sm-4 col-xs-6">
                                                <h5>Out Of Stock</h5>
                                                </div>
                                                <div class="col-lg-4 col-sm-4 col-xs-6">
                                                <h5>Inactive</h5>
                                                </div>
                                             </div>
                                            <!-- Icon sets -->
                                            <div class="row icon-set">
                                              <div class="col-md-12">
                                              <div class="col-lg-4 col-sm-4 col-xs-6">
                                                  <!-- it is for active product count -->
                                              <a href="<c:url value="/products/listing/active/1"/>">
                                              <div class="icon-holder">
                                              <i class="md  md-inbox"></i> <span class="trx-py-text">${productListingCount.active}</span>
                                              </div>
                                              </a>
                                              </div>
                                              <div class="col-lg-4 col-sm-4 col-xs-6">
                                              <!-- it is for out of stock products -->
                                              <a href="<c:url value="/products/listing/stock/es"/>">
                                              <div class="icon-holder">
                                              <i class="md md-rotate-left"></i> <span class="trx-py-text">${productListingCount.emptyStock}</span>
                                              </div>
                                              </a>
                                              </div>
                                              <div class="col-lg-4 col-sm-4 col-xs-6">
                                              <!-- it is for inactive products -->
                                                <a href="<c:url value="/products/listing/inactive/1"/>">
                                                <div class="icon-holder">
                                                <i class="md md-error"></i><span class="trx-py-text">${productListingCount.blocked}</span>
                                                </div>
                                                </a>
                                              </div>
                                              
                                            </div>
                                            </section>
                                  </div>
                                </div>
                              </div>
                            </div>

                             <div class="row gutter-14">
                              <div class="col-md-12">
                                <div class="card">
                                  <div class="card-header relative">
                                    <div class="card-title">TOTAL PAYMENT</div>
                                    <div class="small grey-text">Last 30 days</div>
                                    
                                  </div>
                                  <div class="card-content p-10">
                          <div class="row">
                            <c:if test="${transactionList.size() == 0}">

                                <div class="col-md-6 text-center" style="border-right: 1px #F0F0F0 solid;">
                                  <h3 class="no-margin w300">&#8377; 0</h3>
                                  <p class="grey-text w600">Total Payment</p>
                                </div>
                                <div class="col-md-6 text-center">
                                  <h3 class="no-margin w300"> 0</h3>
                                  <p class="grey-text w600">Total Orders</p>
                                </div>

                            </c:if>
                            <c:if test="${transactionList.size() >= 1}">
                            <c:forEach var="transactions" items="${transactionList}">
                            <div class="col-md-6 text-center" style="border-right: 1px #F0F0F0 solid;">
                              <h3 class="no-margin w300">&#8377; ${transactions.transactionAmount}</h3>
                              <p class="grey-text w600">Total Payment</p>
                            </div>
                            <div class="col-md-6 text-center">
                              <h3 class="no-margin w300"> ${orderSaleSummary.saleCompleted}</h3>
                              <p class="grey-text w600">Total Orders</p>
                            </div>
                          </c:forEach>
                        </c:if>
                          </div>
                        </div>
                                </div>
                              </div>
                            </div>

                            <div class="row gutter-14">
                              <div class="col-md-12">
                                <div class="card">
                                  <div class="card-header relative">
                                    <div class="card-title">Feeds</div>
                                    <!-- <div class="small grey-text">Last 30 days</div> -->
                                        <div role="tabpanel" class="tab-pane active" id="feed-tabs">

                                        </div>
                                  </div>
                                </div>
                              </div>
                            </div>

                        <div class="row gutter-14">
                          <div class="col-md-12" id="feed-tab">
                            
                        </div>
                      </div>


                            <div class="grey-text small p-t-20">All rights reserved @ Krenai</div>
                          </div>
                        </div>
                        		
						<%@ include file="master/neighbour-sidebar.jsp" %>
                                     <div class="trx-chat-window" style="display: none;">
                                                  
                                                   <div class="trx-chat-head">
                                                        <div class="row">
                                                          <div class="col-md-10">
                                                             <h6>Pushpendra Singh</h6>
                                                          </div>
                                                          <div class="col-md-2">
                                                            <i class="md  md-clear f20 close-chat"></i>
                                                          </div>
                                                        </div>
                                                    </div>

                                                    <div class="chat-footer">

                                                    <textarea rows="1">

                                                    </textarea> 
                                                  </div>

                                                    <div class="trx-chat-body">
                                                      <div class="chat-bubble-user">
                                                        <p>Under Construction</p>
                                                        <div class="ripple-wrapper"></div>
                                                      </div>
                                                      <div class="msg_insert"></div>
                                                      <div class="chat-bubble-neighbour">
                                                        <p>Coming Soon</p>
                                                      </div>

                                                    </div>

                                                  </div>

                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </main>
                      </body>


                                <script type="text/javascript">

                                    $(document).ready(function () {
                                    $(".fa-times-circle").click(function () {

                                        $("#frame_pramod").fadeOut(200);
                                        $("#overlay_pramod").fadeOut(200);
                                    });
                                    });
                                </script>
                                <script type="text/javascript">

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
                 var loadFile = function  (event,img) {
                        var output = document.getElementById(img);
                         output.src = URL.createObjectURL(event.target.files[0]);
                         $("#add-img").css("display", "none");
                         $("#remove-img").css("display", "inline");
                         $("#user-upload").css("display", "block");
                      };
                        
                  $("#remove-img").on('click', function(){
                  $("#user-upload").css("display", "none");
                  $("#remove-img").css("display", "none");
                  $("#add-img").css("display", "inline-block");
                  $("#user-upload-file").attr("src","");
                  $("#feedImage").val("");
                      
                  });
                      
                  
                 $("#post-btn").click(function(objEvent) {
                   $(".overlay").css("display", "block");
                    msg = $.trim($("#feedMessage").val());
                    feedfor = $("#feed-for").val();
                    feedimg = $("#feedImage").val();
                  if(msg.length >= 1 || feedimg !='' ){
                    var objFormData = new FormData();
                    var objFile = null;
                    var imageUrl = null;
                    //if($(".urlive-title").html()==null){
                      // GET FILE OBJECT 
                        objFile = $("#feedImage")[0].files[0];
                        // APPEND FILE TO POST DATA
                        objFormData.append('userfile', objFile);
                        objFormData.append('msg', msg);
                        objFormData.append('feedfor', feedfor);
                        objFormData.append('urlFound','false');
                       
                
                     userImage = '${user.profileImageUrl}';
                       userName = '${user.firstName} ${user.lastName}';
                         $.ajax({
                           url: '/dashboard/social/feeds/add',
                           type: 'POST',
                           contentType: false,
                           data: objFormData,
                           //JQUERY CONVERT THE FILES ARRAYS INTO STRINGS.SO processData:false
                           processData: false,
                           success: function(data) {
                            //alert(data);
                              $(".overlay").css("display", "none");
                            if(data==true){
                              $("#user-upload").css("display", "none");
                          $("#remove-img").css("display", "none");
                          $("#add-img").css("display", "inline-block");
                          $("#feedImage").val("");
                          $("#user-upload-file").attr("src","");
                          $("#feedMessage").val("");
                          $(".post-msg").text("posted successfully.");
                            }
                            
                           }
                       });  
                      }
                      else{
                        $(".overlay").css("display", "none");
                        $(".post-msg").text("Please type your thoughts or select any image.");
                      }
                   });

                        
                               $.ajax({
                                     url: '/dashboard/supplier/feeds/find',
                                     type: 'POST',
                                     // contentType: false,
                                     // data: abc,
                                     //JQUERY CONVERT THE FILES ARRAYS INTO STRINGS.SO processData:false
                                     // processData: false,
                                     success: function(data) {
                                      // alert(data);
                                        // data.length = 3;
                                        for(var i=0; i<data.length; i++){

                                            feeds = data[i];
                                          $('#feed-tab').append("<div class='card bordered'><div class='card-header'><span class='card-title'><img  src='assets/img/pic.jpeg'> Smit Baranwal</span><span style='margin-left: 20px;color: #D2D2D2;'>updated 10 min before</span><div class='pull-right'> <a href='#' class='btn-icon' data-toggle='dropdown'><i class='md md-more-vert'></i></a><ul class='dropdown-menu dropdown-menu-right'><a href='#' class='btn-icon' data-toggle='dropdown'></a><li><a href='#' class='btn-icon' data-toggle='dropdown'></a><a href='#'>Action</a></li><li><a href='#'>Another action</a></li><li><a href='#'>Something else here</a></li><li class='divider'></li><li><a href='#'>Separated link</a></li> </ul> <div class='ripple-wrapper'></div> </div> </div><div class='card-content'>   <span id='here'> "+feeds.feedMessage+" </span><img  width='100%' src='assets/img/2.jpg'></div></div>");



                                        }

                                      }
                                      
                                     });    

                  function querySave(){
                    raiseQueryId = $('#raiseQueryId').val()
                    $.ajax({
                          url : '/raise/query',
                          data : {
                            // "queryId":id,
                            "raiseQuery":raiseQueryId
                            // "supplierId":supplier
                            },
                          type: "POST",
                          success : function(data) {
                          
                            alert('Your query has been successfully submit.');
                            $("#frame_pramod").fadeOut(200);
                            $("#overlay_pramod").fadeOut(200);
                          },
                          error: function(data){
                            //alert();
                          }
                        });  
                      }
                        
                             </script>
                             
                             
                  <script type="text/javascript">
                
                 
              $('#feedMessage').on('input propertychange', function () {
                    $('#data').html("");
                    $(".post-msg").text("");
                    $("#urlive-container").html("");
                    $('#feeds-action').show();
                    $('#user-upload').show();
                    jQuery.noConflict();
                      $('#feedMessage').urlive({
                          callbacks: {
                              onStart: function () {
                                $('.loading').show(); },
                              onSuccess: function (data) { 
                                if(data.description != null){
                                  $('#feeds-action').hide();
                                    $('#user-upload').hide();
                                    $('#feedImage').val('');
                                    $('#user-upload-file').attr('src','');
                                }
                                $('.loading').hide();
                                },
                              noData: function () { alert(); $('.loading').hide();}
                          }
                      });
                  }
          ).trigger('input'); 
                   
          
               function del(id) {
                    $('#feeds-action').show();
                    $(id).remove();
                  }
                     
              </script>
              <script type="text/javascript">

                function enableDisabledPost(){
                    var reviewTxt =$.trim($("#raiseQueryId").val());
                    var enableDis =$("#submitQuery").val();
                    if(reviewTxt.length>=1){
                      $("#submitQuery").attr('disabled', false);
                    }
                    else if(reviewTxt==''){
                      $("#submitQuery").attr('disabled', true);
                    }
                  }

              </script>
                  <script charset="utf-8" src="assets/js/jquery-1.11.1.min.js"></script>
                    <script charset="utf-8" src="assets/js/trx-custom.js"></script>

                                        <%@ include file="master/footer.jsp" %>