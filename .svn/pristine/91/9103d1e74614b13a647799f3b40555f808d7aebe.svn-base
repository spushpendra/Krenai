 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
  
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Approval request - Krenai</title>
    <link href="https://cdn.datatables.net/1.10.11/css/dataTables.bootstrap.min.css" rel="stylesheet" />
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />

    <link href="<c:url value='/assets/css/profile.update.css' />" rel="stylesheet" />
    <script src="<c:url value='/assets/js/jquery-1.11.1.min.js' />"></script>
    <script charset="utf-8" src="http://maps.google.com/maps/api/js?sensor=true"></script>
  </head>
  <body scroll-spy="" id="top" class=" theme-template-dark theme-pink alert-open alert-with-mat-grow-top-right">
    <div class="loading-message" id="loading-message">
      <button class="btn btn-warning btn-flat">Updating...<div class="ripple-wrapper"></div></button>
    </div>
    <div id="overlay_pramod">
          
          
          </div>
          <div id="frame_pramod">
            <i class="fa fa-times-circle track-popup-close"></i>

          <div class="well white remove-bootom-shadow">
                  <form action="<c:url value="/products/category/edit"/>" onsubmit="return validateTrackRequest();" method="post">
                    <fieldset>
                      <legend>Edit Category Details</legend> <span class="help-block"></span>
                      <div class="form-group">
                        <label for="inputEmail" class="control-label">Category Name</label>
                        <input type="text" class="form-control" name="name" id="name"> 
                        <input type="hidden" class="form-control" name="hiddenId" id="hiddenId">
                        <input type="hidden" class="form-control" name="hiddenModule" id="hiddenModule"> 
                        <div class="help-block with-errors validationText" id="nameValidate"></div>
                      </div>
                      <div class="form-group">
                        <label for="textArea" class="control-label">Description about product - Minimum 10 words</label>
                        <textarea name="description" class="form-control vertical" onkeyup="countingChr();" rows="3" maxlength="1000" id="description"></textarea> 
                        <div class="help-block with-errors validationText" id="descriptionValidate"></div>
                      </div>
                      
                      
                      <div class="form-group">
                        <button type="submit" onclick="spinner();" id="add-shop-btn" class="btn btn-primary">Submit</button>
                        <button type="reset" class="fa-times-circle btn btn-default">Cancel</button>
                        <div class="col-md-4" id="spinner" ></div>
                      </div>
                    </fieldset>
                  </form>
                </div>
             
          </div>
    <main>
      <%@ include file="/master/sidebar.jsp" %>
      <div class="main-container">
        <nav class="navbar navbar-default navbar-fixed-top">
         <%@ include file="/master/header.jsp" %>
        </nav>
         <div class="main-content" autoscroll="true" bs-affix-target="" init-ripples="">
          <section>
            <div class="page-header">
              <h1>      <i class="md md-list"></i>      Requested Category    </h1>
              <p class="lead"> Browse your request by category. </p>
            </div>




            <div class="row">
              <div class="col-md-12">
                <div class="card bordered">
                  <div class="card-header"> <span class="card-title">Requested Category Status</span> </div>
                  <div class="table-responsive">
                    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                      <thead>
                          <tr>
                            <th class="td-center">Category Name</th>
                            <th class="td-center">Status</th>
                            <th class="td-center">Reason</th>
                            <th class="text-right">Action</th>
                          </tr> 
                      </thead>
                      <tfoot>
                          <tr>
                            <th class="td-center">Category Name</th>
                            <th class="td-center">Status</th>
                            <th class="td-center">Reason</th>
                            <th class="text-right">Action</th>
                          </tr>
                      </tfoot>
                      <tbody>
                        <c:forEach var="categoryMapObj" items="${categoryMapList}">
                       <tr>
                       <!--  <td ng-bind-html="" class="f20">
                        <c:if test="${not empty categoryMapObj.imageUrl}">
                          <img class="image-size" alt="" src="${categoryMapObj.imageUrl}">
                        </c:if>
                        </td>
                         -->
                          <td>${categoryMapObj.name}</td>
                          <td class="text-success"><span  class="md   md-sort"></span> ${categoryMapObj.status}</td>
                          <td>${categoryMapObj.reason}</td>
                          <td class="text-right">
                            <button type="button" onclick="edit('${categoryMapObj.id}','category')" <c:if test="${categoryMapObj.status == 'approved'}"> data-toggle="tooltip" data-placement="top" data-original-title="Not allowed after Approval"</c:if> class="btn btn-link btn-round <c:if test="${categoryMapObj.status != 'approved'}"> pramod</c:if>" data-title="Edit item"><i class="md md-edit"></i></button>
                            <button type="button" onclick="requestCount('category','${categoryMapObj.id}');" <c:if test="${categoryMapObj.resendActivity == 'no'}">disabled data-toggle="tooltip" data-placement="top" data-original-title="Not allowed before 72 hrs of previous request."</c:if> class="btn btn-link btn-round"  data-title="Delete item"><i class="md md-restore"></i></button>
                            <c:if test="${categoryMapObj.status eq 'pending'}">
                            <button type="button" onclick="remove_cat('${categoryMapObj.id}');" class="btn btn-link btn-round" data-title="Delete item"><i class="md md-delete"></i></button>
                            </c:if>
                          </td> 
                          </tr> 
                                           
                        </c:forEach>
                        <tr>
                        <td style="border-right:none"></td>
	                        <td  style="text-align:center; padding : 48px 0px;">
	                     		<c:if test="${empty categoryMapList}">
                        			<a class="btn btn-success btn-border" href="<c:url value="/product/addcategory"/>">Create New Category</a>
                        		</c:if>   
	                        </td>
	                     </tr> 
                     </tbody>
                    </table>
                  </div>

                </div>
              </div>
            </div>


            <div class="row m-b-60">
              <div class="col-md-12">
                <div class="card bordered">
                  <div class="card-header"> <span class="card-title">Requested Sub-Category Status</span> </div>
                  <div class="table-responsive">
                    <!-- <table class="table table-full table-full-small"> -->
                <table id="example1" class="table table-striped table-bordered" cellspacing="0" width="100%">
                      <thead>
                          <tr>
                            <th class="td-center">SubCategory Name</th>
                            <th class=" td-center">Status</th>
                            <th class="td-center">Reason</th>
                            <th class="text-right">Action</th>
                          </tr> 
                      </thead>
                      <tfoot>
                        <tr>
                            <th class="td-center">SubCategory Name</th>
                            <th class=" td-center">Status</th>
                            <th class="td-center">Reason</th>
                            <th class="text-right">Action</th>
                       </tr>
                    </tfoot>
                      <tbody>
                        <c:forEach var="subCategoryMapObj" items="${subCategoryMapList}">
                          <tr>
                          <td class="td-center">${subCategoryMapObj.name}</td>
                          <td class="td-center text-success">${subCategoryMapObj.status}</td>
                          <td>${subCategoryMapObj.reason}</td>
                          <td class="text-right">
                            <button type="button" onclick="edit('${subCategoryMapObj.id}','subcategory')" <c:if test="${subCategoryMapObj.status == 'approved'}">disabled style="display:none;" </c:if> class="btn btn-link btn-round <c:if test="${subcategoryMapObj.status != 'approved'}"> pramod</c:if>" data-title="Edit item"><i class="md md-edit"></i></button>
                            <button type="button" onclick="requestCount('subcategory','${subCategoryMapObj.id}');" <c:if test="${subCategoryMapObj.resendActivity == 'no'}">disabled</c:if>  class="btn btn-link btn-round" data-title="Delete item"><i class="md md-restore"></i></button>
                            <c:if test="${subCategoryMapObj.status eq 'pending'}">
                            <button type="button" onclick="remove_subcat('${subCategoryMapObj.id}');" class="btn btn-link btn-round" data-title="Delete item"><i class="md md-delete"></i></button>
                            </c:if>
                          </td> 
                          </tr>                   
                        </c:forEach>
                        <tr>
                        <td style="border-right:none"></td>
	                        <td  style="text-align:center; padding : 48px 0px;">
	                     		 <c:if test="${empty subCategoryMapList}">
                        			<a class="btn btn-success btn-border" href="<c:url value="/product/addsubcategory"/>">Create New Sub Category</a>
                        		</c:if>   
	                        </td>
	                     </tr>
                      </tbody>
                    </table>
                  </div>
                  
                </div>
              </div>
            </div>
 

   

<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"></script>

<script type="text/javascript">

 $('#example').DataTable();
 $('#example1').DataTable();
   // oTable.fnFilter('');



//      $(document).ready(function() {
//     $('#example').DataTable({
//         "columnDefs":[
//         {
//             "targets":[0],
//             "visible":false,
//             "searchable":false
//         }
//         ]
//     });
//     $('#example tfoot th').each(function(){
//     var title=$('#example thead th').eq($(this).index()).text();
//     $(this).html('<input type="text" placeholder="search'+title+'"/>');

// });
// table.columns().eq( 0 ).each(function (colIdx){
//     $('input', table.column(colIdx).footer()).on('keyup change',function(){
//         table
//             .column(colIdx)
//             .search(this.value)
//             .draw();
//     });
// });



// });
</script>

                    </div>
                  </div>
                </div>
              </div>
            </div>
            </section>
        </div>
      </div>
    </main>
    <script>
    function edit(id,module){
      if(module.localeCompare('category')==0){
        module="category";
      }else{
        module="subcategory";
      }
      //alert("top of the methods"+module);
      $.ajax({
        url : '/products/getCategoryDetailsToEdit',
        data : {
          "id":id,
          "module":module
          },
        type: "POST",
        success : function(data) {
        
        if(data!=null){
          //alert(data.name);
          $('#name').val(data.name);
          $('#hiddenId').val(data.id);
          $('#hiddenModule').val(data.module);
          $('#description').text(data.description);
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
    
    function requestCount(module,id){
      if(module.localeCompare('category')==0){
        module="category";
      }else{
        module="subcategory";
      }
      //alert("top of the methods"+module);
      $.ajax({
        url : '/products/setRequestCount',
        data : {
          "module":module,
          "id":id
          },
        type: "POST",
        success : function(data) {
        
        if(data!=null){
          //alert(data);
          if(data.localeCompare('success')!=0){
            alert("Unable to process!!");
          }
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

    function remove_cat(_ri){
  	  alert(_ri);
  	$.ajax({
        url : '/category/reqested/pending/delete',
        data : {
          "_ri":_ri
          },
        type: "POST",
        success : function(data) {
        
        if(data!=null){
          //alert(data);
          if(data.localeCompare('success')!=0){
            alert("Unable to process!!");
          }else{
        	  alert("success");
          }
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

    function remove_subcat(_ri){
  	  alert(_ri);
  	$.ajax({
        url : '/subcategory/reqested/pending/delete',
        data : {
          "_ri":_ri
          },
        type: "POST",
        success : function(data) {
        
        if(data!=null){
          //alert(data);
          if(data.localeCompare('success')!=0){
            alert("Unable to process!!");
          }else{
        	  alert("success");
          }
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
    
    </script>

<script type="text/javascript">
                          function categoryName(){
                            var categ=$('#name').val();
                            if(categ==''){
                        $("#nameValidate").text("Please input Category Name").show();
                            $("#name").css("border-bottom", "2px  solid #B71C1C");
                            $("#name").focus();
                            return false;
                        }
                      else{
                        $("#nameValidate").text("Please input Category Name").hide();
                            $("#name").css("border-bottom", "2px  ");
                            return true;

                            }
                          }
                          function descriptionProd(){
                            var categ=$('#description').val();
                            
                            
                            if(categ==''){
                                  $("#descriptionValidate").text("Please input description about category Name").show();
                                  $("#description").css("border-bottom", "2px  solid #B71C1C");
                                  $("#description").focus();
                                  return false;
                              }
                      else{
                        $("#descriptionValidate").text("Please input Category Name").hide();
                            $("#description").css("border-bottom", "2px  ");
                            return true;

                            }
                          }

                          function descValue(){
                              var textDesc = document.getElementById("description");
                              if(textDesc.value.length < 10){
                              $("#descriptionValidate").text("Description must be atleast 10 characters.").show();
                              $("#description").css("border-bottom", "2px  solid #B71C1C");
                              $("#description").focus();
                              return false; 
                            }
                            else{
                              $("#descriptionValidate").text("Description must be atleast 100 characters.").hide();
                              $("#description").css("border-bottom", "2px ");
                                return true; 
                            }

                          }

                          function validateTrackRequest(){

                            categoryName();
                            descriptionProd();
                            descValue();
                            if(categoryName()){
                              if(descriptionProd()){
                                if(descValue()){
                                  $("#add-shop-btn").attr("disabled",true);
                                return true;
                              }
                            }
                            }
                            $("#spinner").empty();
                            return false;
                          }
                          
                      </script>

                      <script type="text/javascript">


                            var text_max = 1000;

                              function countingChr(){

                            $('#descriptionValidate').text('1000 characters left');
                                  var max = 1000;
                                  var min = 10;
                                    var len = $.trim($('#description').val()).length;
                                    if (len <= min) {
                                        $('#descriptionValidate').text(' Input 10 to 1000 characters for Product Description. ');
                                        $('#descriptionValidate').css("color","#B71C1C");
                                    }

                                    else if(len >= max){
                                      $('#descriptionValidate').text(' You have reached the limit');
                                       $('#descriptionValidate').css("color","#B71C1C");
                                    }

                                    else {
                                        var ch = max - len;
                                        $('#descriptionValidate').text(ch + ' characters left');
                                         $('#descriptionValidate').css("color","#37B50D");
                                    }


                                // var text_length = $('#product-description').val().length;
                                // var text_remaining = text_max - text_length;

                                // $('#product-descriptionValidation').html(text_remaining + ' characters remaining');
                              }
                        </script>      


 </body>
 </html>
   <%@ include file="/master/footer.jsp" %>

   <script type="text/javascript">

      function spinner(){
         // $("#add-shop-btn").attr("disabled",true);
         // $("#").submit();
         $("#spinner").css("text-align","right");
         
        $("#spinner").append("<svg width='32px' height='32px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' class='uil-ring-alt'><rect x='0' y='0' width='100' height='100' fill='none' class='bk'></rect><circle cx='50' cy='50' r='40' stroke='#f0f0f0' fill='none' stroke-width='10' stroke-linecap='round'></circle><circle cx='50' cy='50' r='40' stroke='#e91e63' fill='none' stroke-width='6' stroke-linecap='round'><animate attributeName='stroke-dashoffset' dur='2s' repeatCount='indefinite' from='0' to='502'></animate><animate attributeName='stroke-dasharray' dur='2s' repeatCount='indefinite' values='150.6 100.4;1 250;150.6 100.4'></animate></circle></svg>");
        }

   </script>