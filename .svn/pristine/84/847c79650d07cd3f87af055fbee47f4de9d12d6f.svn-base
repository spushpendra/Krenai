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
    <title>Store Address Update </title>
     <style>
      #googleMap {
        height: 387px;
        width:100%;
        margin: 0px;
        padding: 0px
      }
      .google-maps input{
        background-color: rgba(256,256,256,1);
        padding: 10px !important;
        border-radius: 5px !important;
      }
      .maps-mar .col-md-9,.col-md-3{
        padding-right: 0px;
      }
    </style>
      <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCB66R-eXXPBIipXknW7c5_YaDMqOQaEcM&libraries=places&callback=initMap"
        async defer></script>

         <script>
      // This example requires the Places library. Include the libraries=places
      // parameter when you first load the API. For example:
      // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

      function initMap() {
        var $latitude = document.getElementById('latitude');
        var $longitude = document.getElementById('longitude');
        var latitude = 50.715591133433854
        var longitude = -3.53485107421875;
        var myCenter=new google.maps.LatLng(28.496517915990054,77.17973189154054);
        var map = new google.maps.Map(document.getElementById('googleMap'), {
          center: {lat: 28.6139, lng: 77.2090},
          zoom: 13
        });
        var dMarker=new google.maps.Marker({
          map: map,
          icon:'../ assets/img/marker.png',
          position:myCenter,
          draggable:true,
        });
        var input = /** @type {!HTMLInputElement} */(
              document.getElementById('pac-input'));

        // map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
       
        var autocomplete = new google.maps.places.Autocomplete(input);
        autocomplete.bindTo('bounds', map);

        var infowindow = new google.maps.InfoWindow();
        var marker = new google.maps.Marker({
          map: map,
          icon:'../ assets/img/marker.png',
          draggable:true,
        });


        google.maps.event.addListener(marker, 'dragend', function(marker){
        var latLng = marker.latLng;
        $('#lat').val(latLng.lat());
        $('#lng').val(latLng.lng());
        $('#add').val($('#pac-input').val());
        lat = $('#lat').val();
    	lng = $('#lng').val();
    	if(lat!=null && lng!=null){
    		$('#updateBtn').prop("disabled", false);
    	}else{
    		$('#updateBtn').prop("disabled", true);
    	}
      // alert($('#pac-input').val());
    	// alert(latLng.lat()+ ' :::::::: '+latLng.lng());
        // alert(place.name);
        });
    
        autocomplete.addListener('place_changed', function() {
          infowindow.close();
          marker.setVisible(false);
          var place = autocomplete.getPlace();
          if (!place.geometry) {
            window.alert("Autocomplete's returned place contains no geometry");
            return;
          }

          // If the place has a geometry, then present it on a map.
          if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
          } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);  // Why 17? Because it looks good.
          }
          // marker.setIcon(/** @type {google.maps.Icon} */({
          //   url: place.icon,
          //   size: new google.maps.Size(71, 71),
          //   origin: new google.maps.Point(0, 0),
          //   anchor: new google.maps.Point(17, 34),
          //   scaledSize: new google.maps.Size(35, 35)
          // }));
          marker.setPosition(place.geometry.location);
          marker.setVisible(true);

          infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
          infowindow.open(map, marker);
        });
     

      }
    </script>

  </head>
  <body scroll-spy="" id="top" class=" theme-template-dark theme-pink alert-open alert-with-mat-grow-top-right">
    <div class="loading-message" id="loading-message">
      <button class="btn btn-warning btn-flat">Updating...<div class="ripple-wrapper"></div></button>
    </div>
    <main>
       <%@ include file="../master/sidebar.jsp" %>
      <div class="main-container">
        <nav class="navbar navbar-default navbar-fixed-top">
           <%@ include file="../master/header.jsp" %>
        </nav>
        <div class="main-content" autoscroll="true" bs-affix-target="" init-ripples="">
          <section>
            <div class="page-header">
              <h1>      <i class="md md-place"></i>      Store Location    </h1>
              <p class="lead"> Update location by dragging the map. </p>
            </div>
             <div class="row maps-mar">
                <div class="col-md-9 google-maps">
                  <input class="form-control" id="pac-input" class="controls" type="text" placeholder="Enter a location"/>
                </div>
                <form action="<c:url value="/profile/address/update"/>" method="post">
                <input type="hidden" name="lat" id="lat"/>
                <input type="hidden" name="lng" id="lng" />
                <input type="hidden" name="add" id="add" />
                
                <div class="col-md-3">
                  <button style="width: 100%;" id="updateBtn" disabled="1" class="btn btn-success btn-flat">Update Store Location<div class="ripple-wrapper"></div></button>
                </div>
                </form>
              </div>

            <div class="row">
              <div class="col-md-12">
                 <div id="googleMap"></div>
              </div>
            </div>
          </section>
        </div>
      </div>
    </main>
    <div class="jvectormap-label"></div>
    <script type="text/javascript" src="<c:url value="/assets/js/jquery-1.11.1.min.js"/>"></script>
    <script src="<c:url value='/assets/js/vendors.min.js' />"></script>
	<script src="<c:url value='/assets/js/app.min.js' />"></script>
    
    <script type="text/javascript">
    
    </script>
  </body>
</html>