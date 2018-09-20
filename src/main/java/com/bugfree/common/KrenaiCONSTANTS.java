package com.bugfree.common;

import java.net.InetAddress;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class KrenaiCONSTANTS {

	public static  String customerSupport = "7053579030";
	public static String sellerContext = "localhost:3333/seller";
	public static String errorDisplay="ErrorMessageDisplay";
	public static String productAddedSuccessMessage="Product is Successfully Added";
	//public static String userApplication="http://localhost:3333";
	public static String userApplication="https://www.krenai.com";
	public static String sellerApplication="http://localhost:4444/seller";
	public static String productDefaultImage="http://localhost:4444/user/assets/img/2.jpg";
	public static int nearestDistance=10;
	public static String india = "India";
	public static String blockedByKrenai = "blockedByKrenai";
	public static String loggedOutStatus = "loggedoutstatus";
	public static String activeStatus = "active";
	public static String inactiveStatus = "inactive";
	public static String pendingStatus = "pending";
	public static String rejectStatus = "rejected";
	
	
	public static String userNotificationEmail = "email";
	public static String userNotificationMobile = "mobile";
	
	
	public static String status_debutLogin = "debutLogin";
	public static String status_debutLoginWithoutAddress = "debutLoginWithoutAddress";
	
	public static String accessPermission_public = "public";
	public static String feedLiked = "liked";
	public static String feedCommented = "commented";
	public static String feedShared = "shared";
	public static String friendRequest = "friend_request";
	public static String send = "sends";
	public static String responded = "responded";
	public static String friendStatusValue = "friend";
	public static String friendRequestAccepted = "accepted";
	public static String friendRequestRequested = "requested";
	public static String supplierFollowing = "following";
	public static String status_removed = "removed";
	public static String orderOrdered = "ordered";
	public static String ordercancelled = "cancelled";
	public static String orderOutForDelivery = "outForDelivery";
	public static String orderDelivered = "delivered";
	public static String orderInprocess = "inprocess";
	public static String orderReturned = "returned";
	public static String supplierFeedForFollowers = "followers";
	public static String feedforPublic = "public";
	public static String bookmarkRequested = "requested";
	public static String elasticIP = "192.168.0.13";
	
	public static Date getCurrentDate(){
		DateTimeZone zone = DateTimeZone.forID("Asia/Kolkata");
		DateTime dt = new DateTime(zone);
		System.out.println("----------------------------new date---------"+new Date(dt.getMillis()));
		return new Date(dt.getMillis());
		
	}
	
	public static String statusIncomplete = "incomplete";
	
	
	public static String firebaseServerKey = "AAAA4HRtFb4:APA91bH8XhJZ6P8BaEPUpVd39G6WR61zlnV3oPlTMqOjmVtueqQ-O0pZ50g0ZY8WED5jNlO-vuCAWSZTeqqmN4achahY4atiXhDLFDrZsZpZCzjQV_fP8ScPTjozuPhZ27ZASYhvxE31rRJD6zaLyO75sS1JX0lFjA";
	public static String gupshupPromoUser = "2000169586";
	public static String gupshupPromoPass = "krenai#2o17";
	
	
	
	public static String paymentStatusPaid = "PAID";
	public static String paymentStatusUnpaid = "UNPAID";
	public static String paymentStatusFree = "FREE";
	
	public static String paymentModeOnline = "ONLINE";
	public static String paymentModeCash = "CASH";
	public static String paymentModePrepaid = "PREPAID";
	public static String paymentModePaytm = "PAYTM";
	public static String paymentModeWallet = "WALLET";
	public static String paymentModeCheque = "CHEQUE";
	public static String paymentModeNeft = "NEFT";
	public static String paymentModePromo = "PROMO_APPLIED";
	
	

	
}
