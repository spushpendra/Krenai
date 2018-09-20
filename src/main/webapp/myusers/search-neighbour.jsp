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
    <title>Validation - Materialism</title>
    <!-- this is a special css for this page -->
    <link href="<c:url value='/assets/css/profile.update.css' />" rel="stylesheet" />
  <script charset="utf-8" src="http://maps.google.com/maps/api/js?sensor=true"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    
  </head>
  <body scroll-spy="" id="top" class=" theme-template-dark theme-pink alert-open alert-with-mat-grow-top-right">
    <main>
      <%@ include file="../master/sidebar.jsp" %>
     
      <div class="main-container">
        <nav class="navbar navbar-default navbar-fixed-top">
          <%@ include file="../master/header.jsp" %>
      
        </nav>
        <div class="main-content" autoscroll="true" bs-affix-target="" init-ripples="">
          <section class="todo-app">
            <div class="page-header">
              <h1>      <i class="md md-camera"></i> Search Neighbour</h1>
              <p class="lead"> Search thousand of neighbours near you. </p>
            </div>
                  
              <div class="row well white">
              <div class="col-md-9 trx-side">
                
                  <h5 class="text-success">Find Neighbours to share your product and thoughts </h5>
                  
                    <div class="form-group input-group">
                      <input id="emailId" class="form-control" onchange="validates();" type="text" placeholder="Enter Email Id to Search">
                      <div id="emailIdValidate"></div>
                      <div class="input-group-btn p-l-10">
                        <button class="btn btn-success" onclick="find();">Find</button>
                      </div>
                    </div>
                  
                  <p></p>
                  <p>All most every neighbour you can find which located in 1000 meters radius</p>
                

              </div>
              <div class="col-md-3 trx-buttons-bulk">
                
                  <ul class="list-unstyled">
                    <h5 class="text-success">Custom Search</h5>
                  </ul>
                  <button class="btn btn-success btn-flat" onclick="find();">Find By Email Id<div class="ripple-wrapper"></div></button>
              </div>
              </div>
            
             <div class="row">
              <h5 class="text-success">Suggestions</h5>
              <div id="sug-div">
            <c:forEach items="${userList}" var="user">
              <div class="col-md-3">
                <div class="card bordered">
                  <div class="card-content">
                    <img src="${user.profileImageUrl}"alt="${user.firstName}" style="width:100px;">
                  </div>
                  <div class="address-neighbour">
                  <h4 style="text-align: center;">${user.firstName} ${user.lastName}</h4>
                  <p  style="text-align: center;"><i class="md-pin-drop"></i>
                  ${user.defaultUserAddressBook.googleAddress}</p>
                  </div>
                  <div class="card-action clearfix"> 
                  <button onclick="invite('${user.userId}',this);" type="button" class="btn btn-lg btn-round btn-flat btn-success">
                  <span class="md md-add"></span><div class="ripple-wrapper"></div>
                  </button>
                  </div>
                </div>
              </div>
            </c:forEach>
				</div>
            </div>
          </section>
        </div>
      </div>
    </main>
  
  </body>
  
<%@ include file="../master/footer.jsp" %>
<script src="<c:url value='assets/js/myFormValidation.js' />"></script>    

<script type="text/javascript">
 

  function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function validates(){
  
  $("#emailIdValidate").text("");
  var email = $("#emailId").val();
  if (validateEmail(email)) {

    $("#emailIdValidate").text("Please input correct email address.").hide();
    $("#emailId").css("border-bottom", "2px  solid #fff");
            return true;
  } else {
    $("#emailIdValidate").text("Please input correct email address.").show();
    $("#emailId").css("border-bottom", "2px  solid #B71C1C");
              return false;
  }

}

function find(email) { 
	var email = $("#emailId").val();
	 if(validates()){
		 $.ajax({
				url : '/find/user/byemail',
				data : {
					"email":email
					},
				type: "POST",
				success : function(data) {
					if(data!='')
						$("#sug-div").prepend("<div class='col-md-3'> <div class='card bordered' style='border: solid;'> <div class='card-content'> <img src='"+data.image+"'alt='"+data.userName+"' style='width:100px;'> </div> <div class='address-neighbour'> <h4 style='text-align: center;'>"+data.userName+"</h4> <p  style='text-align: center;'><i class='md-pin-drop'></i> "+data.address+"</p> </div> <div class='card-action clearfix'> <button onclick=\"invite('"+data.id+"',this);\" type='button' class='btn btn-lg btn-round btn-flat btn-success'> <span class='md md-add'></span><div class='ripple-wrapper'></div> </button> </div> </div> </div>");
					else
						 $("#emailIdValidate").text("No such user found.").css("color","red").show();
						
				},
				error: function(data){
					//alert();
				}
			});
	 }
}


function invite(uid,a){
	$.ajax({
		url : '/supplier/send/invite',
		data : {
			"uid":uid
			},
		type: "POST",
		success : function(data) {
		if(data!='Failed' && data!='Following'){
			$(a).removeClass("btn-round").css("width","100px").css("padding-left","10%").css("font-size","12px").html(data);
			$(a).addClass("btn-success").removeClass("btn-primary");
		}
		
		else{
			$(a).removeClass("btn-round").css("width","100px").css("font-size","12px").css("padding-left","10%").removeAttr("onclick").html("Following").removeClass("btn-success").removeClass("btn-primary").addClass("btn-danger");;
		}
		if(data==='Request Sent'){
			$(a).css("padding-left","5%").removeClass("btn-success").addClass("btn-primary");
		}
		},
		error: function(data){
			//alert();
		}
	});
}
</script>