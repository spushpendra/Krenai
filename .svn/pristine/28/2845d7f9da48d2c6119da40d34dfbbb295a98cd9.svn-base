// This JS is used for addProducts.jsp

function enablePrice(value,id)
           {

              if(value=='')

              { 
                $("#sellPriceId").attr('readonly', true);
              }
              else{
                $("#sellPriceId").attr('readonly', false);
              }

           }

function restrictedPastDate(){
                    var d = new Date();
                    var m_names = new Array("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
                    var curr_date = d.getDate();
                    var curr_month = d.getMonth();
                    var curr_year = d.getFullYear();
                    if (curr_date<10) {curr_date="0"+curr_date;}
                    date=curr_year + "-" + m_names[curr_month]+ "-" + curr_date;
                    
                    var selectedDate=$("#available-from-date").val();



                      if( selectedDate.localeCompare(date)<=0||selectedDate.localeCompare(date)>0 ){
                          
                       
                         //$("#available-from-dateValidation").text("Selected Date must be current or future date.").show();
                        // $("#available-from-date").val(date);
                        //$("#available-from-date").css("border-bottom", "2px  solid #B71C1C");
                       // $("#available-from-date").focus();
                        return true;
                      }
                      else{
                        $("#available-from-dateValidation").text("Selected Date must be current or future date.").hide();
                        $("#available-from-date").css("border-bottom", "2px ");
                        return true;
                      }

                // $('#available-from-date').datepicker('setStartDate', curr);
                  
                  }






// function prodSellPrice(){

//       var prd=parseFloat($('#mrp').val());
//       var sellPrc=parseFloat($('#sellPriceId').val());
//       // var sellPrc=parseFloat($('#abcd').val(30/100*prd));
//       var thirtyMax= prd-(100/100*prd);
//       if($('#sellPriceId').val()<0){
//           $("#sellPriceIdValidation").text("Selling Price value must be greater than zero").show();
//         $("#sellPriceId").css("border-bottom", "2px  solid #FF0000");
//         $("#sellPriceId").focus();
//         return false;
//       }
//       else if(sellPrc<thirtyMax )
//       { 
//          $("#sellPriceIdValidation").text("Selling Price discount is not more than 30%").show();
//         $("#sellPriceId").css("border-bottom", "2px  solid #FF0000");
//         $("#sellPriceId").focus();
//         return false;
            
//       }
//       /*else if(sellPrc>prd){
//           $("#sellPriceIdValidation").text("Selling Price value is not greater than MRP Price").show();
//         $("#sellPriceId").css("border-bottom", "2px  solid #FF0000");
//         $("#sellPriceId").focus();
//         return false;
//       }*/
//       else{
//         $("#sellPriceIdValidation").text("Selling Price discount is not more than 30% Or Selling Price value is not greater than MRP Price").hide();
//         $("#sellPriceId").css("border-bottom", "2px  ");
//         return true;
//       }
// }



// This part is used for index file.

      function userPasswardValue(){
          var user=$('#inputUsername').val();
          var paswrd=$('#inputPassword').val();
             var textbox = document.getElementById("inputPassword");
          $("#inputUsernameValidation").text("user").hide();
          $("#inputPasswordValidation").text("passwords").hide();
          $("#inputUsername").css("border-bottom", "1px ");
          $("#inputPassword").css("border-bottom", "1px ");
          if(user=='')
          {
            $("#inputUsernameValidation").text("Please input username").show();
            $("#inputUsername").css("border-bottom", "2px  solid #B71C1C");
            $("#inputUsername").focus();
            return false;
          }
          if(paswrd=='')
          {
            $("#inputPasswordValidation").text("Please input Password.").show();
            $("#inputPassword").css("border-bottom", "2px  solid #B71C1C");
            $("#inputPassword").focus();
            return false; 
          }
          if(textbox.value.length < 8){
            $("#inputPasswordValidation").text("Password length must be atleast 8 characters.").show();
            $("#inputPassword").css("border-bottom", "2px  solid #B71C1C");
            $("#inputPassword").focus();
            return false; 
      }
      else{
        return true;
      }
}


// This part is used for update-profile.jsp



function enableNDisableAll()
{
  if($('#checkAll').is(':checked'))
  {

    $("#sunCheck").prop('checked', true);
    $("input[id='monCheck']").prop('checked', true);
    $("input[id='tueCheck']").prop('checked', true);
    $("input[id='wedCheck']").prop('checked', true);
    $("input[id='thuCheck']").prop('checked', true);
    $("input[id='friCheck']").prop('checked', true);
    $("input[id='satCheck']").prop('checked', true);
  }
  else
  {
    $("input[id='sunCheck']").prop('checked', false);
    $("input[id='monCheck']").prop('checked', false);
    $("input[id='tueCheck']").prop('checked', false);
    $("input[id='wedCheck']").prop('checked', false);
    $("input[id='thuCheck']").prop('checked', false);
    $("input[id='friCheck']").prop('checked', false);
    $("input[id='satCheck']").prop('checked', false);
  }
}
