<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Materialism Angular Admin Theme">
    <meta name="author" content="Theme Guys - The Netherlands">
    <meta name="msapplication-TileColor" content="#9f00a7">
    <meta name="msapplication-TileImage" content="assets/img/favicon/mstile-144x144.png">
    <meta name="msapplication-config" content="assets/img/favicon/browserconfig.xml">
    <meta name="theme-color" content="#ffffff">
    <title>My Product Table - Krenai</title>
    <link href="assets/css/vendors.min.css" rel="stylesheet" />
    <link href="assets/css/styles.min.css" rel="stylesheet" />
    <link href="assets/css/push.css" rel="stylesheet" />
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
          <section>
              <div class="row well white">
              <div class="col-md-12">

                  
              <div class="row">
              <div class="col-md-7 trx-side">
                
                  <h5 class="text-success">${userOrder.user.firstName} ${userOrder.user.lastName}</h5>
                  <blockquote>
                    <p><b><i class="md md-local-phone"></i> Mobile : </b>${userOrder.user.contactNo}</p>
                    <p><b><i class="md md-place"></i>Google Address : </b>${userOrder.userAddressBookId.googleAddress}</p>
                    <p><b><i class="md md-place"></i>Custom Address : </b>${userOrder.userAddressBookId.customAddress}</p>
                    <p><b><i class="md  md-email"></i> Email : </b><a href="#">${userOrder.user.emailId}</a></p>
                    <p><b><i class="md  md-directions-bike"></i> Delivery Required By : </b><a href="#">${userOrder.userRequiredDate}</a></p>
                    <p><b><i class="md  md-access-time"></i> At App. Time : </b><a href="#">${userOrder.userRequiredTime}</a></p>
                     
                  </blockquote>

              </div>
                  <div class="col-md-5 trx-buttons-bulk">
                    <div id="map-canvas" style="width: 100%;height: 240px;"></div>
                  </div>
                  </div>

              </div>
              
            </div>

            <div class="row m-b-40">
          
              <div class="col-md-12">
                <div class="card">
                  <div class="list-group"> 
                  <c:set var="amount" value="${0}"/>
                  <p class="list-group-item active">
                  	<a href="<c:url value="${request}"/>"><i class="md md-keyboard-backspace blue darken-2 icon-color"> </i></a> &nbsp;&nbsp;  
                  		 Product List 
                  		 <a href="#amount-vi">
                  		 <span class="pull-right">
                  		 <i class="md fa fa-inr md-account-balance-wallet blue darken-2 icon-color">
                  		 </i> </span></a></p>
                  <c:forEach var="cartProductElement" items="${orderProductsList}">
                  	  <c:set var="amount" value="${amount + cartProductElement.sellingPrice * cartProductElement.quantity}"/>
                  	  <p class="list-group-item trx-img-size">
	                  <img src="${cartProductElement.cartProduct.sellerProductListing.product.productImage.imagePath1}">
	                  <span class="badge light-green" style="font-size: 13px;"> @Rs.${cartProductElement.sellingPrice} </span>
	                  <span class="badge light-green" style="font-size: 13px;">Qty: ${cartProductElement.quantity}</span>
	                  ${cartProductElement.cartProduct.sellerProductListing.product.productName}</p>
                  
                  </c:forEach>
				<p id="amount-vi" class="list-group-item active"> &nbsp; <span class="pull-right">Total = <i class="fa fa-inr"></i> <c:out value="${amount}"/> </span></p>
                  
                  </div>
                
                </div>
              </div>
            </div>

          </section>
        </div>
      </div>
    </main>
  
  <!-- <script src="http://maps.googleapis.com/maps/api/js"></script> -->
<script>
function initialize() {
  var myCenter=new google.maps.LatLng(28.496517915990054,77.17973189154054);
  var mapProp = {
    center:new google.maps.LatLng(28.496517915990054,77.17973189154054),
    zoom:16,
    disableDefaultUI: true,
    mapTypeId:google.maps.MapTypeId.ROADMAP
  };
  var map=new google.maps.Map(document.getElementById("map-canvas"),mapProp);

  var marker=new google.maps.Marker({
    icon:'../../assets/img/marker.png',
    position:myCenter,
    });

  marker.setMap(map);

  var infowindow = new google.maps.InfoWindow({
  content: '<img src="../../assets/img/maps-pic.png">'+' <b> Pushpendra Singh</b>'
  });

infowindow.open(map,marker);
  
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>
  
  <%@ include file="/master/footer.jsp" %>