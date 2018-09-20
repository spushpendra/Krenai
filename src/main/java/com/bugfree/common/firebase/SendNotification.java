package com.bugfree.common.firebase;

import java.util.ArrayList;
import java.util.List;

import com.bugfree.common.KrenaiCONSTANTS;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.user.User;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class SendNotification extends Thread {

	//String msg ;
	String deviceKey;
	Message message = null;
	@Override
	public void run(){

        try {
            Sender sender = new FCMSender(KrenaiCONSTANTS.firebaseServerKey);
            
            // Use the same token(or registration id) that was earlier
            // used to send the message to the client directly from
            // Firebase Console's Notification tab.
            Result result = sender.send(message, deviceKey, 1);
            
            System.out.println("****************Result********" + result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	public SendNotification(String msg, String deviceKey){
		//String serverKey = "AIzaSyBKuumkKvyi-FmHJKu3UenifLYdO1S-j0A";
		//String serverKey = "AIzaSyAwsCTo8eSCipjCc6iLSYouwkYQq8Ug3LM";
		//String serverKey = "AIzaSyA6jK9z1RRIQldvuRVM7uR5U4iM0PzAnWw";
		//this.msg=msg;
		message = new Message.Builder()
                .collapseKey("message")
                .timeToLive(3)
                .delayWhileIdle(true)
                .addData("message", msg)
                .build();  

		this.deviceKey=deviceKey;
		run();
		    
}
	
	public SendNotification(Message msg, String deviceKey){
		message=msg;
		this.deviceKey=deviceKey;
		run();
		    
}
	
	public SendNotification(Message msg, User user){
		message=msg;
		this.deviceKey=user.getDeviceToken();
		run();
		    
}
	
	public SendNotification(Message msg, List<User> userList){
		message=msg;
		List<String> stringList = new ArrayList<String>();
		for(User user : userList){
			stringList.add(user.getDeviceToken());
		}
		 try {
	            Sender sender = new FCMSender(KrenaiCONSTANTS.firebaseServerKey);
	            
	            MulticastResult result = sender.send(message, stringList, 1);
	            
	            System.out.println("****************Result********" + result.toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		    
}
	public SendNotification(Message msg, Supplier supplier){
		message=msg;
		this.deviceKey=supplier.getDeviceToken();
		run();
		    
}
	


}
