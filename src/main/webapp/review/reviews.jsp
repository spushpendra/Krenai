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
         
          <div class="dashboard grey lighten-3">
            <div class="row no-gutter">
            <div class="col-sm-12 col-md-12 col-lg-9 review-sec" style="background:#F9F9F9;">
                        <div class="p-20 clearfix">
	                        <div class="col-xs-2 pull-right">
		                      <div id="chart-gauge-1" class="c3" style="max-height: 80px; position: relative;"><svg width="93.203125" height="80" style="overflow: hidden;"><defs><clipPath id="c3-1476530700418-clip"><rect width="93.203125" height="76"></rect></clipPath><clipPath id="c3-1476530700418-clip-xaxis"><rect x="-31" y="-20" width="155.203125" height="20"></rect></clipPath><clipPath id="c3-1476530700418-clip-yaxis"><rect x="-29" y="-4" width="20" height="100"></rect></clipPath><clipPath id="c3-1476530700418-clip-grid"><rect width="93.203125" height="76"></rect></clipPath><clipPath id="c3-1476530700418-clip-subchart"><rect width="93.203125"></rect></clipPath></defs><g transform="translate(0.5,4.5)"><text class="c3-text c3-empty" text-anchor="middle" dominant-baseline="middle" x="46.6015625" y="38" style="opacity: 0;"></text><rect class="c3-zoom-rect" width="93.203125" height="76" style="opacity: 0;"></rect><g clip-path="url(file:///home/sm-it/Templates/seller/reviews.html#c3-1476530700418-clip)" class="c3-regions" style="visibility: hidden;"></g><g clip-path="url(file:///home/sm-it/Templates/seller/reviews.html#c3-1476530700418-clip-grid)" class="c3-grid" style="visibility: hidden;"><g class="c3-xgrid-focus"><line class="c3-xgrid-focus" x1="-10" x2="-10" y1="0" y2="76" style="visibility: hidden;"></line></g></g><g clip-path="url(file:///home/sm-it/Templates/seller/reviews.html#c3-1476530700418-clip)" class="c3-chart"><g class="c3-event-rects c3-event-rects-single" style="fill-opacity: 0;"><rect class=" c3-event-rect c3-event-rect-0" x="0.3984375" y="0" width="93.203125" height="76"></rect></g><g class="c3-chart-bars"><g class="c3-chart-bar c3-target c3-target-data" style="opacity: 1; pointer-events: none;"><g class=" c3-shapes c3-shapes-data c3-bars c3-bars-data" style="cursor: pointer;"></g></g></g><g class="c3-chart-lines"><g class="c3-chart-line c3-target c3-target-data" style="opacity: 1; pointer-events: none;"><g class=" c3-shapes c3-shapes-data c3-lines c3-lines-data"></g><g class=" c3-shapes c3-shapes-data c3-areas c3-areas-data"></g><g class=" c3-selected-circles c3-selected-circles-data"></g><g class=" c3-shapes c3-shapes-data c3-circles c3-circles-data" style="cursor: pointer;"></g></g></g><g class="c3-chart-arcs" transform="translate(46.6015625,61)"><text class="c3-chart-arcs-title" style="text-anchor: middle; opacity: 1;"></text><path class="c3-chart-arcs-background" d="M-44.271484375,-5.421693163334581e-15A44.271484375,44.271484375 0 0,1 44.271484375,0L41.271484375,0A41.271484375,41.271484375 0 0,0 -41.271484375,-5.054299123590375e-15Z"></path><text class="c3-chart-arcs-gauge-unit" dy=".75em" style="text-anchor: middle; pointer-events: none;"></text><text class="c3-chart-arcs-gauge-min" dx="-42.771484375px" dy="1.2em" style="text-anchor: middle; pointer-events: none;">0</text><text class="c3-chart-arcs-gauge-max" dx="42.771484375px" dy="1.2em" style="text-anchor: middle; pointer-events: none;">100</text><g class="c3-chart-arc c3-target c3-target-data"><g class=" c3-shapes c3-shapes-data c3-arcs c3-arcs-data"><path class=" c3-shape c3-shape c3-arc c3-arc-data" transform="" d="M-44.271484375,-5.421693163334581e-15A44.271484375,44.271484375 0 0,1 35.81638322557995,-26.02212561272166L33.389332242455104,-24.25876985584424A41.271484375,41.271484375 0 0,0 -41.271484375,-5.054299123590375e-15Z" style="fill: rgb(233, 30, 99); cursor: pointer; opacity: 1;"></path></g><text dy="-.1em" class="c3-gauge-value" transform="" style="opacity: 1; text-anchor: middle; pointer-events: none; font-size: 9px;">80.0%</text></g></g><g class="c3-chart-texts"><g class="c3-chart-text c3-target c3-target-data" style="opacity: 1; pointer-events: none;"><g class=" c3-texts c3-texts-data"></g></g></g></g><g clip-path="url(file:///home/sm-it/Templates/seller/reviews.html#c3-1476530700418-clip-grid)" class="c3-grid c3-grid-lines"><g class="c3-xgrid-lines"></g><g class="c3-ygrid-lines"></g></g><g class="c3-axis c3-axis-x" clip-path="url(file:///home/sm-it/Templates/seller/reviews.html#c3-1476530700418-clip-xaxis)" transform="translate(0,76)" style="visibility: visible; opacity: 0;"><text class="c3-axis-x-label" transform="" x="93.203125" dx="-0.5em" dy="-0.5em" style="text-anchor: end;"></text><g class="tick" transform="translate(47, 0)" style="opacity: 1;"><line y2="6" x1="0" x2="0"></line><text y="9" x="0" transform="" style="text-anchor: middle; display: block;"><tspan x="0" dy=".71em" dx="0">0</tspan></text></g><path class="domain" d="M0,6V0H93.203125V6"></path></g><g class="c3-axis c3-axis-y" clip-path="url(file:///home/sm-it/Templates/seller/reviews.html#c3-1476530700418-clip-yaxis)" transform="translate(0,0)" style="visibility: visible; opacity: 0;"><text class="c3-axis-y-label" transform="rotate(-90)" x="0" dx="-0.5em" dy="1.2em" style="text-anchor: end;"></text><g class="tick" transform="translate(0,70)" style="opacity: 1;"><line x2="-6"></line><text x="-9" y="0" style="text-anchor: end;"><tspan x="-9" dy="3">0</tspan></text></g><g class="tick" transform="translate(0,62)" style="opacity: 1;"><line x2="-6"></line><text x="-9" y="0" style="text-anchor: end;"><tspan x="-9" dy="3">10</tspan></text></g><g class="tick" transform="translate(0,55)" style="opacity: 1;"><line x2="-6"></line><text x="-9" y="0" style="text-anchor: end;"><tspan x="-9" dy="3">20</tspan></text></g><g class="tick" transform="translate(0,47)" style="opacity: 1;"><line x2="-6"></line><text x="-9" y="0" style="text-anchor: end;"><tspan x="-9" dy="3">30</tspan></text></g><g class="tick" transform="translate(0,39)" style="opacity: 1;"><line x2="-6"></line><text x="-9" y="0" style="text-anchor: end;"><tspan x="-9" dy="3">40</tspan></text></g><g class="tick" transform="translate(0,31)" style="opacity: 1;"><line x2="-6"></line><text x="-9" y="0" style="text-anchor: end;"><tspan x="-9" dy="3">50</tspan></text></g><g class="tick" transform="translate(0,23)" style="opacity: 1;"><line x2="-6"></line><text x="-9" y="0" style="text-anchor: end;"><tspan x="-9" dy="3">60</tspan></text></g><g class="tick" transform="translate(0,16)" style="opacity: 1;"><line x2="-6"></line><text x="-9" y="0" style="text-anchor: end;"><tspan x="-9" dy="3">70</tspan></text></g><g class="tick" transform="translate(0,8)" style="opacity: 1;"><line x2="-6"></line><text x="-9" y="0" style="text-anchor: end;"><tspan x="-9" dy="3">80</tspan></text></g><path class="domain" d="M-6,1H0V76H-6"></path></g><g class="c3-axis c3-axis-y2" transform="translate(93.203125,0)" style="visibility: hidden; opacity: 0;"><text class="c3-axis-y2-label" transform="rotate(-90)" x="0" dx="-0.5em" dy="-0.5em" style="text-anchor: end;"></text><g class="tick" transform="translate(0,76)" style="opacity: 1;"><line x2="6" y2="0"></line><text x="9" y="0" style="text-anchor: start;"><tspan x="9" dy="3">0</tspan></text></g><g class="tick" transform="translate(0,69)" style="opacity: 1;"><line x2="6" y2="0"></line><text x="9" y="0" style="text-anchor: start;"><tspan x="9" dy="3">0.1</tspan></text></g><g class="tick" transform="translate(0,62)" style="opacity: 1;"><line x2="6" y2="0"></line><text x="9" y="0" style="text-anchor: start;"><tspan x="9" dy="3">0.2</tspan></text></g><g class="tick" transform="translate(0,54)" style="opacity: 1;"><line x2="6" y2="0"></line><text x="9" y="0" style="text-anchor: start;"><tspan x="9" dy="3">0.3</tspan></text></g><g class="tick" transform="translate(0,46)" style="opacity: 1;"><line x2="6" y2="0"></line><text x="9" y="0" style="text-anchor: start;"><tspan x="9" dy="3">0.4</tspan></text></g><g class="tick" transform="translate(0,39)" style="opacity: 1;"><line x2="6" y2="0"></line><text x="9" y="0" style="text-anchor: start;"><tspan x="9" dy="3">0.5</tspan></text></g><g class="tick" transform="translate(0,32)" style="opacity: 1;"><line x2="6" y2="0"></line><text x="9" y="0" style="text-anchor: start;"><tspan x="9" dy="3">0.6</tspan></text></g><g class="tick" transform="translate(0,24)" style="opacity: 1;"><line x2="6" y2="0"></line><text x="9" y="0" style="text-anchor: start;"><tspan x="9" dy="3">0.7</tspan></text></g><g class="tick" transform="translate(0,16)" style="opacity: 1;"><line x2="6" y2="0"></line><text x="9" y="0" style="text-anchor: start;"><tspan x="9" dy="3">0.8</tspan></text></g><g class="tick" transform="translate(0,9)" style="opacity: 1;"><line x2="6" y2="0"></line><text x="9" y="0" style="text-anchor: start;"><tspan x="9" dy="3">0.9</tspan></text></g><g class="tick" transform="translate(0,1)" style="opacity: 1;"><line x2="6" y2="0"></line><text x="9" y="0" style="text-anchor: start;"><tspan x="9" dy="3">1</tspan></text></g><path class="domain" d="M6,1H0V76H6"></path></g></g><g transform="translate(0.5,80.5)" style="visibility: hidden;"><g clip-path="url(file:///home/sm-it/Templates/seller/reviews.html#c3-1476530700418-clip-subchart)" class="c3-chart"><g class="c3-chart-bars"></g><g class="c3-chart-lines"></g></g><g clip-path="url(file:///home/sm-it/Templates/seller/reviews.html#c3-1476530700418-clip)" class="c3-brush" style="pointer-events: all; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><rect class="background" x="0" width="93.203125" style="visibility: hidden; cursor: crosshair;"></rect><rect class="extent" x="0" width="0" style="cursor: move;"></rect><g class="resize e" transform="translate(0,0)" style="cursor: ew-resize; display: none;"><rect x="-3" width="6" height="6" style="visibility: hidden;"></rect></g><g class="resize w" transform="translate(0,0)" style="cursor: ew-resize; display: none;"><rect x="-3" width="6" height="6" style="visibility: hidden;"></rect></g></g><g class="c3-axis-x" transform="translate(0,0)" clip-path="url(file:///home/sm-it/Templates/seller/reviews.html#c3-1476530700418-clip-xaxis)" style="opacity: 0;"><g class="tick" transform="translate(47, 0)" style="opacity: 1;"><line y2="6" x1="0" x2="0"></line><text y="9" x="0" transform="" style="text-anchor: middle; display: block;"><tspan x="0" dy=".71em" dx="0">0</tspan></text></g><path class="domain" d="M0,6V0H93.203125V6"></path></g></g><g transform="translate(0,80)" style="visibility: hidden;"></g></svg><div class="c3-tooltip-container" style="position: absolute; pointer-events: none; display: none;"></div></div>
		                      <div class="text-center small">${averageRating} out of 5</div>
		                    </div>
		                    <h4 class="grey-text"><i class="md md-dashboard"></i>          
		                    <span class="hidden-xs">Reviews</span>
					        </h4> 
					      </div>




                        </div>
                        <%@ include file="/master/neighbour-sidebar.jsp" %>
            </div>
         </div>
         
        </div>
      </div>
    </main>
  
  </body>
  <script>
  var last=0;


var feed_inprocess = false;

$(window).scroll(function () {
	if ($(document).height()-600 <= $(window).scrollTop() + $(window).height()) {
		//alert(_id);
		getCom();
    }
});

function getCom(){
	if(!feed_inprocess){
		feed_inprocess = true;
		$.ajax({
		    url: '/store/nextcomment',
		    type: 'POST',
		    data: {
		    	"last": last
		    },
		    success: function(dataArray) {
		      feed_inprocess = false;
		       for(var i=0; i<dataArray.length; i++){
		    	   data=dataArray[i];
		    	   last=data.commentid;
		         $(".review-sec").append("<div class='col-lg-12'> <div class='bs-component'> <div class='alert alert-dismissible alert-warning'> <button type='button' class='close' data-dismiss='alert' data-toggle='tooltip' title='' data-original-title='Remove from Store'>×</button> <h4 style='color:#222'><img style='max-width:32px;max-height:32px;' src='"+data.imageurl+"'>"+data.firstname+" "+ data.lastname+"</h4> <p style='color:#222'>"+data.comment+"</p> <div class='card-action clearfix'> <a href='#' class='btn btn-link btn-icon pull-right'><i class='md md-favorite red-text'>"+data.count+"</i></a> <a href='#' class='btn btn-link btn-icon' data-toggle='tooltip' title='' data-original-title='Reply'>"+data.replycount+" comments<i class='md-reply black-text'></i><div class='ripple-wrapper'></div></a> </div> </div> </div> </div>");
		       }

		     }
		     
		    });

	}
}
getCom();
</script>
<%@ include file="../master/footer.jsp" %>
<script src="<c:url value='assets/js/myFormValidation.js' />"></script>    

<script type="text/javascript">
  
</script>