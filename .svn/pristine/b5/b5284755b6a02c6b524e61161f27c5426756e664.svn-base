/* 
    Author     : Tomaz Dragar
    Mail       : <tomaz@dragar.net>
    Homepage   : http://www.dragar.net
*/
(function ($) {

    $.fn.simpleCropper = function (onComplete) {
        var image_dimension_x = 500;
        var image_dimension_y = 500;
        var scaled_width = 0;
        var scaled_height = 0;
        var x1 = 0;
        var y1 = 0;
        var x2 = 0;
        var y2 = 0;
        var current_image = null;
        var image_filename = null;
        var aspX = 1;
        var aspY = 1;
        var file_display_area = null;
        var ias = null;
        var original_data = null;
        var jcrop_api;
        var formData = new FormData();
        var userImageData = new FormData();
        // alert("inner");
        var bottom_html = "<input type='file'  data-img='myCanvas' id='fileInputmyCanvas' name='files[]'/ accept='image/*'><canvas id='myCanvas' style='display:none;'></canvas><div id='modal'></div><div class='preview' id='previewmyCanvas'><div class='buttons'><div class='cancel'></div><div class='ok' data-img='myCanvas'></div></div></div>";
        $('body').append(bottom_html);

        // var bottom_html1 = "<input type='file'  data-img='myCanvas1' id='fileInputmyCanvas1' name='files[]'/ accept='image/*'><canvas id='myCanvas1' style='display:none;'></canvas><div id='modal'></div><div class='preview' id='previewmyCanvas1'><div class='buttons'><div class='cancel'></div><div class='ok' data-img='myCanvas1'></div></div></div>";
        // $('body').append(bottom_html1);
        
        // var bottom_html2 = "<input type='file'  data-img='myCanvas2' id='fileInputmyCanvas2' name='files[]'/ accept='image/*'><canvas id='myCanvas2' style='display:none;'></canvas><div id='modal'></div><div class='preview' id='previewmyCanvas2'><div class='buttons'><div class='cancel'></div><div class='ok' data-img='myCanvas2'></div></div></div>";
        // $('body').append(bottom_html2);


// $('#fileInput').val(0);
        //add click to element
        this.click(function () {
            console.log(this.id);
            aspX = $(this).width();
            aspY = $(this).height();
            file_display_area = $(this);
            canData = $(this).attr("data-img");
            //alert(canData);
            $('#fileInput'+canData).click();
        });

        $(document).ready(function () {
            //capture selected filename
            $("[id^=fileInput]").change(function (click) {
                prevD = $("#"+this.id).attr("data-img");
               // alert($('#preview').get(0) +" preview");
                imageUpload($('#preview'+prevD).get(0), this.id, prevD);
                //alert("fileInput data "+ prevD);
                // Reset input value
                $(this).val("");
            });

            //ok listener
            $('.ok').click(function () {
                can = $(this).attr("data-img");
                //alert(can+'hey ok');
                preview(can);
                //alert();
                $('#preview'+can).delay(100).hide();
                $('#modal').hide();
                spinner();
                //jcrop_api.destroy();
               // reset();
            });

            //cancel listener
            $('.cancel').click(function (event) {
                $('#previewmyCanvas').delay(100).hide();
                $('#modal').hide();
                jcrop_api.destroy();
                reset();
            });
        });

        function reset() {
            scaled_width = 0;
            scaled_height = 0;
            x1 = 0;
            y1 = 0;
            x2 = 0;
            y2 = 0;
            current_image = null;
            image_filename = null;
            original_data = null;
            aspX = 1;
            aspY = 1;
            file_display_area = null;
        }

        function imageUpload(dropbox, inp, prevDiv) {
            //alert(inp);
            var file = $("#"+inp).get(0).files[0];

             
            //alert(prevDiv+"+prevDiv");
            var imageType = /image.*/;

            if (file.type.match(imageType)) {
                var reader = new FileReader();
                image_filename = file.name;

                reader.onload = function (e) {
                    // Clear the current image.
                    $('#photo').remove();
                    //alert('enter Onload');
                    original_data = reader.result;

                    // Create a new image with image crop functionality
                    current_image = new Image();
                    current_image.src = reader.result;
                    current_image.id = "photo";
                    current_image.style['maxWidth'] = image_dimension_x + 'px';
                    current_image.style['maxHeight'] = image_dimension_y + 'px';
                    current_image.onload = function () {
                        // Calculate scaled image dimensions
                        if (current_image.width > image_dimension_x || current_image.height > image_dimension_y) {
                            if (current_image.width > current_image.height) {
                                scaled_width = image_dimension_x;
                                scaled_height = image_dimension_x * current_image.height / current_image.width;
                            }
                            if (current_image.width < current_image.height) {
                                scaled_height = image_dimension_y;
                                scaled_width = image_dimension_y * current_image.width / current_image.height;
                            }
                            if (current_image.width == current_image.height) {
                                scaled_width = image_dimension_x;
                                scaled_height = image_dimension_y;
                            }
                        }
                        else {
                            scaled_width = current_image.width;
                            scaled_height = current_image.height;
                        }

                        // set the image size to the scaled proportions which is required for at least IE11
                        current_image.style['width'] = scaled_width + 'px';
                        current_image.style['height'] = scaled_height + 'px';

                        // Position the modal div to the center of the screen
                        $('#modal').css('display', 'block');
                        var window_width = $(window).width() / 2 - scaled_width / 2 + "px";
                        var window_height = $(window).height() / 2 - scaled_height / 2 + "px";

                        //alert('before preview');
                        // Show image in modal view
                        $("#preview"+prevDiv).css("top", window_height);
                        $("#preview"+prevDiv).css("left", window_width);
                        $('#preview'+prevDiv).show(500);
                        //alert('after preview');


                        // Calculate selection rect
                        var selection_width = 0;
                        var selection_height = 0;

                        var max_x = Math.floor(scaled_height * aspX / aspY);
                        var max_y = Math.floor(scaled_width * aspY / aspX);


                        if (max_x > scaled_width) {
                            selection_width = scaled_width;
                            selection_height = max_y;
                        }
                        else {
                            selection_width = max_x;
                            selection_height = scaled_height;
                        }

                        ias = $(this).Jcrop({
                            onSelect: showCoords,
                            onChange: showCoords,
                            bgColor: '#747474',
                            bgOpacity: .4,
                            aspectRatio: aspX / aspY,
                            setSelect: [0, 0, selection_width, selection_height]
                        }, function () {
                            jcrop_api = this;
                        });
                    }

                    // Add image to dropbox element
                    dropbox.appendChild(current_image);
                }

                reader.readAsDataURL(file);
            } else {
                dropbox.innerHTML = "File not supported!";
            }
        }

        function showCoords(c) {
            x1 = c.x;
            y1 = c.y;
            x2 = c.x2;
            y2 = c.y2;
        }

        function preview(can) {
            // Set canvas

            // alert('can '+can);
           // var canvas = document.getElementById(can);
            var canvas = document.getElementById(can);
            age = $("#fileInput"+can);
           // alert('age'+age); 
            // document.getElementById('fileInput');
            age.remove();
            // alert('1 '+canvas);
             var context = canvas.getContext('2d');
// alert('2 '+canvas);
// clickX.length = 0;
// clickY.length = 0;
// clickDrag.length = 0;
// clickColor.length = 0;
// var context = $('#canvas')[0].getContext('2d');
// context.width = context.width;
// context.height = context.height;

            // context.clearRect(0, 0, canvas.width, canvas.height);

            // Delete previous image on canvas
            // context.clearRect(0, 0, canvas.width, canvas.height);

            // Set selection width and height
            var sw = x2 - x1;
            var sh = y2 - y1;

            //alert($("#myCanvas")+" canvas");

            // Set image original width and height
            var imgWidth = current_image.naturalWidth;
            var imgHeight = current_image.naturalHeight;

            // Set selection koeficient
            var kw = imgWidth / $("#preview"+can).width();
            var kh = imgHeight / $("#preview"+can).height();

            // Set canvas width and height and draw selection on it
            canvas.width = aspX;
            canvas.height = aspY;
            context.drawImage(current_image, (x1 * kw), (y1 * kh), (sw * kw), (sh * kh), 0, 0, aspX, aspY);

            // Convert canvas image to normal img
            var dataUrl = canvas.toDataURL();
            // alert(dataUrl);
            canvas.toBlob(function(blob){
                //$("#newimage").attr("src",blob);
                //  // alert(blob.size);
                 
                // // alert(formData);
                var productImagemyimg = $('#productImagemyCanvas').val();
                var supplierIdImgId = $('#supplierIdImg').val();

                var userIdImage = $('#userIdImg').val();


                 formData.append('content',blob);
                 formData.append('profileimage',productImagemyimg);
                 formData.append('supplier',supplierIdImgId);

                 // userImageData.append('contentUser',blob);
                 // userImageData.append('profileimage',productImagemyimg);
                 // userImageData.append('user',userIdImage);
                //alert(formData.get('content-500-500'));
                $.ajax({
                    url:'/ajax/update/SupplierImage',
                    type:'POST',
                    data: formData,
                    contentType:false,
                    processData:false,
                    success:function(data){
                        $("#updateImagePath").attr('src', data );
                        $("#spinner").empty(300);

                $("#imguploadmessage").append("<div class='row'><div class='col-lg-12'><div class='bs-component'><div class='alert alert-dismissible alert-success'><button type='button' class='close' data-dismiss='alert'>x</button><h4>Success!</h4><p>Your Profile photo is successfully updated.</p></div></div></div></div>");

                        formData = new FormData();
                    },
                    error:function(data){
                        alert("error find in upload of image"+data);
                    }
                    
                })
                
            },'image/jpeg',0.95);
            //alert(blobdata);
            var imageFoo = document.createElement('img');
            imageFoo.style["border-radius"] = "50%";
            imageFoo.style["width"] = "150px";
            imageFoo.style["height"] = "150px";
            imageFoo.src = dataUrl;
            //alert(imageFoo);
            //$("#newimage").attr("src",blobdata);
            
            // Append it to the body element
            $('#preview'+can).delay(100).hide();
            $('#modal').hide();
            file_display_area.html('');
            file_display_area.append(imageFoo);

            if (onComplete) onComplete(
                {                    
                    "original": { "filename": image_filename, "base64": original_data, "width": current_image.width, "height": current_image.height },
                    "crop": { "x": (x1 * kw), "y": (y1 * kh), "width": (sw * kw), "height": (sh * kh) }
                }
               );  
        }

    // $('#set-button').click(function (blob) {
    //         //var formData = new FormData();

    //         //formData.append('content',blob);
    //             $.ajax({
    //                 url:'/themeMyUser/ajax/SupplierImage',
    //                 type:'POST',
    //                 data: formData,
    //                 contentType:false,
    //                 processData:false,
    //                 success:function(data){
    //                     // alert('data');
    //                     $("#productImagemyCanvas").val(data);
    //                      // alert("path -->"+data);
    //                      formData = new FormData();
    //                 },
    //                 error:function(data){
    //                     alert("error find in upload of image"+data);
    //                 }
                    
    //             })
    // });

    // $('.ok').click(function (blob) {
    //     alert('uesr');
    //         //var formData = new FormData();
            
    //         //formData.append('content',blob);
    //             $.ajax({
    //                 url:'/themeMyUser/ajax/Userimage',
    //                 type:'POST',
    //                 data: userImageData,
    //                 contentType:false,
    //                 processData:false,
    //                 success:function(data){
    //                     alert('data');
    //                     $("#productImagemyCanvas").val(data);
    //                      // alert("path -->"+data);
    //                      userImageData = new FormData();
    //                 },
    //                 error:function(data){
    //                     alert("error find in upload of image"+data);
    //                 }
                    
    //             })
    // });

        $(window).resize(function () {
            // Position the modal div to the center of the screen
            var window_width = $(window).width() / 2 - scaled_width / 2 + "px";
            var window_height = $(window).height() / 2 - scaled_height / 2 + "px";

            // Show image in modal view
            $("#previewImg").css("top", window_height);
            $("#previewImg").css("left", window_width);
        });
    }
}(jQuery));
