  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    
    <title>Payments</title>
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
          <section class="todo-app">
            <div class="page-header">
              <h1>      <i class="fa fa-inr f20"></i> Payments</h1>
              <p class="lead"> Quick and easy process to add new products to your store. </p>
            </div>
            <c:forEach var="transactions" items="${transactionList}">
           <div class="row well white">
              <div class="col-md-12">
    
              <div class="row">
              <div class="col-md-8 trx-side">
                  
                  <div class="row">
                    
                    <div class="col-md-6">
                       <h5 class="text-success">${transactions.month}, ${transactions.year}</h5>
                       <p class="text-muted">Payment Month</p>
                    </div>
                    <div class="col-md-6"></div>
                        <h5 class="text-success text-right " style="font-size:28px"><i class="fa fa-inr "></i><strong>  ${transactions.transactionAmount} </strong></h5>
                        <p class="text-muted text-right"><span class="text-success"><i class="fa fa-inr"></i>  ${transactions.transactionAmount}</span>  saving </p>
                  </div>
                 
              </div>
              <div class="col-md-4 trx-buttons-bulk">
                
                <table id="example" class="table table-full table-full-small" cellspacing="0" width="100%">
                  
                   <thead>
                      <tr>
                        <th>Total Transaction</th>
                     </tr>

                    </thead>
                    <tbody>
                      <tr class="first">
                        <td class="text-success" style="font-size:28px">${transactions.orderNos} Transaction(s)</td>
                      </tr>
                    </tbody>
                  </table>
              </div>
              </div>
    
              </div>
            </div>
			</c:forEach>
          </section>
        </div>
      </div>
    </main>
    
    
<%@ include file="/master/footer.jsp" %>