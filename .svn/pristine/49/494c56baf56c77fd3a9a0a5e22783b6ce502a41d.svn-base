<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  
<head>
    <title>Total Orders - Krenai</title>
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
          <section class="tables-data">
            
            <div class="row">

              <div class="col-md-12">
                <div class="card">
                  <div class="card-image"> <img src="<c:url value="/assets/img/bg.png" />" class="p-5">
                  <div class="card-title"><h3> <b>Pushpendra Singh</b></h3></div>
                  </div>
                  
                  <div class="card-content" style="text-align: right;">
                    <div class="card-profile pull-right"><img src="<c:url value="/assets/img/smit.jpg" />" alt="" style="width:92px;"></div>
                  </div>
                
                </div>
              </div>

            </div>

            <div class="row m-b-60">
              <div class="col-md-12">
                <div class="card bordered">
                  <div class="table-responsive">
                    <table class="table table-full table-full-small">
                      <thead>
                        <tr>
                          <th>Order Id</th>
                          <th>Order Date</th>
                          <th>Status</th>
                          <th>Rating</th>
                          <th>Review</th>
                          <th class="text-right">Actions</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach var="cartElement" items="${cartList}">
                        	<tr>
                          <td>${cartElement.orderId}</td>
                          <td>${cartElement.orderDate}</td>
                          <td>${cartElement.status.statusValue}</td>
                          <td>
                            <i class="md md-grade"></i>
                            <i class="md md-grade"></i>
                            <i class="md md-grade"></i>
                            <i class="md md-grade"></i>

                          </td>
                          <td>No reviews available for this order.</td>

                          <td class="text-right">
                            <button type="button" class="btn btn-link btn-round" data-toggle="tooltip" data-placement="top" data-original-title="Reply On Review"><i class="md md-mode-edit"></i></button>

                          <span class="btn btn-link btn-round" data-toggle="tooltip" data-placement="top" data-original-title="Product List">
                          <form action="<c:url value="/orders/user/products"/>" method="post">
            							<input type="hidden" value="${cartElement.orderId}" name="orderId" />
            							<button style="background-color: transparent; border: none;" type="submit"><i class="md  md-shopping-basket"></i></button>
            							</form>
						              </span>
                        
                        </td>
                        </tr>
                        
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                  <div class="card-action clearfix">
                    <div class="row">
                      <div class="col-lg-2 col-md-4 col-sm-5 col-xs-12"> <a href="#" class="btn btn-link btn-icon"><i class="md md-favorite red-text"></i></a> <a href="#" class="btn btn-link btn-icon"><i class="md md-refresh black-text"></i></a> </div>
                      <div class="col-lg-10 col-md-8 col-sm-7 col-xs-12 text-right">
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
                  </div>
                </div>
              </div>
            </div>

          </section>
        </div>
        
        </div>
      </div>
    </main>

    
     
<%@ include file="/master/footer.jsp" %>