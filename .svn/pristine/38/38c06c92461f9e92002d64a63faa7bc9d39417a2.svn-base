 $( document ).ready(function() {
     $('.trx-chat-head').click(function(){
     	$('.trx-chat-body').slideToggle(10);
     	$('.chat-footer').slideToggle(10);
     });

        $('.close-chat').click(function(){
     	$('.trx-chat-window').hide();
     });

      $('.lists').click(function(){
     	$('.trx-chat-window').show();
     	$('.trx-chat-body').show();
     	$('.chat-footer').show();
     	$('.trx-chat-body').scrollTop($('.trx-chat-body')[0].scrollHeight);
     });

       $('.chat-footer textarea').keypress(function(e){
     	if(e.keyCode ==13){
     		var msg=$(this).val();
			$(this).val('');
			$("<div class='msg_insert'>"+msg+"</div>").insertAfter('.chat-bubble-user');
     		$('.trx-chat-body').scrollTop($('.trx-chat-body')[0].scrollHeight);
     	}
     });

});