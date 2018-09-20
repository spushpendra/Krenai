package com.bugfree.controller.dashboard;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bugfree.common.KrenaiCONSTANTS;
import com.bugfree.model.cart.Cart;
import com.bugfree.model.social.feed.Feeds;
import com.bugfree.model.social.network.UserFollow;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.product.SellerProductListings;
import com.bugfree.model.supplierRating.SupplierRating;
import com.bugfree.model.user.User;
import com.bugfree.repository.cart.CartRepository;
import com.bugfree.repository.social.feed.FeedsRepository;
import com.bugfree.repository.social.network.UserFollowRepository;
import com.bugfree.repository.supplierRating.store.StoreCommentLikeRepo;
import com.bugfree.repository.user.UserRepository;
import com.bugfree.service.address.AddressService;
import com.bugfree.service.cart.CartProductsService;
import com.bugfree.service.cart.CartService;
import com.bugfree.service.status.StatusService;
import com.bugfree.service.supplier.SupplierService;
import com.bugfree.service.supplier.product.SellerProductListingsService;
import com.bugfree.service.supplierRating.SupplierRatingService;

@Controller
public class Dashboard {
	
	@Autowired
	private AddressService addressService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private SellerProductListingsService sellerProductListingsService;
	@Autowired
	private CartProductsService cartProductsService;
	@Autowired
	private CartService cartService;
	@Autowired
	private UserFollowRepository userFollowRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FeedsRepository feedsRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private SupplierRatingService storeRatingService;
	@Autowired
	private StoreCommentLikeRepo commentLikeRepo;
	
	private Map<String, String> getProductListingCount(Supplier supplier){
		Map<String, String> productListingMap = new HashMap<String, String>();
		List<SellerProductListings> productList = (List<SellerProductListings>) sellerProductListingsService.findBySupplier(supplier);
		int activeCount=0;
		int emptyStockCount=0;
		int onHoldCount=0;
		int moreThan5Count=0;
		int lessThan5Count=0;
		int blockedCount=0;
		
		for(SellerProductListings spl:productList){
			
			if(spl.getStatus().getStatusValue().equals("active")){
				activeCount++;
				if(spl.getAvailableQuantity()<5)
					lessThan5Count++;
				else
					moreThan5Count++;
			}
			else if(spl.getStatus().getStatusValue().equals("outOfStock")){
				if(spl.getAvailableQuantity()==0)
					emptyStockCount++;
			}
			else if(spl.getStatus().getStatusValue().equals("onHold")){
				onHoldCount++;
			}
			else if(spl.getStatus().getStatusValue().equals("blocked")){
				blockedCount++;
			}
			
		}
		productListingMap.put("all",String.valueOf(moreThan5Count+lessThan5Count));
		productListingMap.put("active",String.valueOf(activeCount));
		productListingMap.put("emptyStock",String.valueOf(emptyStockCount));
		productListingMap.put("onHold",String.valueOf(onHoldCount));
		productListingMap.put("moreThan5",String.valueOf(moreThan5Count));
		productListingMap.put("lessThan5",String.valueOf(lessThan5Count));
		productListingMap.put("blocked",String.valueOf(blockedCount));
		
		return productListingMap;
	}
	
	private Map<String, String> getOrderSaleSummary(Supplier supplier){
		Map<String, String> orderSale = new HashMap<String, String>();
		List<Cart> SaleSummaryList = (List<Cart>) cartService.findBySupplier(supplier);
		int saleCompleted=0;
		int saleReturned=0;
		int orderBooked=0;
		int processed=0;
		int orderReturns=0;
		int cancelOrders=0;
		
		for(Cart spl:SaleSummaryList){
			
			if(spl.getStatus().getStatusValue().equals("delivered")){
				saleCompleted++;
//				if(spl.getAvailableQuantity()<5)
//					lessThan5Count++;
//				else
//					moreThan5Count++;
			}
			else if(spl.getStatus().getStatusValue().equals("returned")){
//				if(spl.getAvailableQuantity()==0)
				saleReturned++;
			}
			else if(spl.getStatus().getStatusValue().equals("ordered")){
				orderBooked++;
			}
			else if(spl.getStatus().getStatusValue().equals("inprocess")){
				processed++;
			}
			else if(spl.getStatus().getStatusValue().equals("returned")){
				orderReturns++;
			}
			else if(spl.getStatus().getStatusValue().equals("cancelled")){
				cancelOrders++;
			}
			
		}
		orderSale.put("saleCompleted",String.valueOf(saleCompleted));
		orderSale.put("saleReturned",String.valueOf(saleReturned));
		orderSale.put("orderBooked",String.valueOf(orderBooked));
		orderSale.put("processed",String.valueOf(processed));
		orderSale.put("orderReturns",String.valueOf(orderReturns));
		orderSale.put("cancelOrders",String.valueOf(cancelOrders));
		
		System.out.println("************orderSalein summary*************"+orderSale);
//		productListingMap.put("lessThan5",String.valueOf(lessThan5Count));
//		productListingMap.put("blocked",String.valueOf(blockedCount));
		
		return orderSale;
	}

	public Map<String, String> calculateOrdersCount(Supplier supplier){
		Map<String, String> orderDetailsMap = new HashMap<String, String>();
		
		//List<CartProducts> cartProductsList = cartProductsService.findBySupplier(supplier);
		
		int activeCount=0;
		int cancelledCount=0;
		int completedCount=0;
		
		int allCount=0;
		
			//Set<Integer> cartNoSet = new HashSet<Integer> ();
			/*for(CartProducts cp:cartProductsList){
				cartNoSet.add(cp.getCart().getCartId());
			}*/
			//List<Integer> tempList = new ArrayList<Integer>();
			//tempList.addAll(cartNoSet);
			String[] strArray = {KrenaiCONSTANTS.ordercancelled, 
					KrenaiCONSTANTS.orderOutForDelivery,
					KrenaiCONSTANTS.orderDelivered, 
					KrenaiCONSTANTS.orderOrdered, 
					KrenaiCONSTANTS.orderInprocess,
					KrenaiCONSTANTS.orderReturned};
			List<Cart> cartList = cartService.findBySupplierAndStatusIn(supplier, strArray);
			String tempStatus;
			for(Cart cart: cartList){
				tempStatus = cart.getStatus().getStatusValue();
				if(tempStatus.equals(KrenaiCONSTANTS.orderOrdered) || tempStatus.equals(KrenaiCONSTANTS.orderInprocess) || tempStatus.equals(KrenaiCONSTANTS.orderOutForDelivery)){
					activeCount++;
				}
				if(tempStatus.equals(KrenaiCONSTANTS.ordercancelled) || tempStatus.equals(KrenaiCONSTANTS.orderReturned)){
					cancelledCount++;
					
				}
				if(tempStatus.equals(KrenaiCONSTANTS.orderDelivered)){
					completedCount++;
					
				}
			}
			allCount=activeCount+cancelledCount+completedCount;
			orderDetailsMap.put("active", String.valueOf(activeCount));
			orderDetailsMap.put("cancelled", String.valueOf(cancelledCount));
			orderDetailsMap.put("completed", String.valueOf(completedCount));
			orderDetailsMap.put("all", String.valueOf(allCount));
		
		return orderDetailsMap;
	}
	

	
	
	/*
	 	private Map<String, String> getProductListingCount(Supplier supplier){
		Map<String, String> productListingMap = new HashMap<String, String>();
		List<SellerProductListings> productList = (List<SellerProductListings>) sellerProductListingsService.findBySupplier(supplier);
		productListingMap.put("productListingCount",String.valueOf(productList.size()));
		return productListingMap;
	}*/
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String welcomeName( ModelMap model, HttpServletRequest request, HttpSession session,RedirectAttributes redirectAttributes) {
		
		Supplier supplier= supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		
		Map<String, String> productListingCountMap = getProductListingCount(supplier);
		
		Map<String, String> orderSaleSummary = getOrderSaleSummary(supplier);
		
		session.setAttribute("productListingCount", productListingCountMap);
		
		session.setAttribute("orderSaleSummary", orderSaleSummary);
		session.setAttribute("loggedUser", supplier);
		
		Map<String, String> orderCountMap = calculateOrdersCount(supplier);
		
		System.out.println("****************ordercountservice*************"+orderCountMap.size());
		
		Status status = statusService.findByStatusValue(KrenaiCONSTANTS.orderDelivered);
		List<Object> objectList = cartRepository.findOrderNosAndOrderAmountMonthlyWise(supplier, status);
		List<Map<String, String>> transactionMapList = new ArrayList<Map<String, String>> ();
		Map<String, String> detailMap ;
		for(Object object : objectList){
			Object[] objArray = (Object[]) object;
			detailMap = new HashMap<String, String>();
			try{
				detailMap.put("month", new DateFormatSymbols().getMonths()[Integer.valueOf(String.valueOf(objArray[0]))-1]);
				detailMap.put("year", String.valueOf(objArray[1]));
				detailMap.put("orderNos", String.valueOf(objArray[2]));
				detailMap.put("transactionAmount", String.valueOf(objArray[3]));
				transactionMapList.add(detailMap);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
//		redirectAttributes.addFlashAttribute("transactionList", transactionMapList);
		session.setAttribute("transactionList", transactionMapList);
		System.out.println("****************transactionMapList*************"+transactionMapList.size());
		
		session = request.getSession();
		session.setAttribute("orderCountSession", orderCountMap);
//		List<UserFollow> userFollowList = userFollowRepository.findBySupplier(supplier);
//		model.addAttribute("userFollowList", userFollowList);
		
		return "dashboard";

	}
	
	@RequestMapping(value = "/dashboard/supplier/neighbours/find", method = RequestMethod.POST)
		public @ResponseBody List<UserFollow> neighbourList( ModelMap model, HttpServletRequest request, HttpSession session) {
				Supplier supplier= supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
				
				System.out.println("******************userFollowList supplier***************"+supplier);
				
				List<UserFollow> userFollowList = userFollowRepository.findBySupplier(supplier);
				model.addAttribute("userFollowList", userFollowList);
				
				System.out.println("******************userFollowList***************"+userFollowList);
				return userFollowList;
		}
	
	@RequestMapping(value = "/dashboard/supplier/feeds/find", method = RequestMethod.POST)
	public @ResponseBody List<Feeds> findFeedsList( ModelMap model, HttpServletRequest request, HttpSession session, Feeds feeds) {
			Supplier supplier= supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
			
			System.out.println("******************feeds user supplier***************"+supplier);
			
			
			
//			int feedsid = feeds.getFeedsId();

			List<Feeds> userFeedsList = feedsRepository.findTop3ByPublishedBySupplierOrderByFeedsIdDesc(supplier);

			//model.addAttribute("userFeedsList", userFeedsList);
			
			System.out.println("******************userFeedsList***************"+userFeedsList.size());
			return userFeedsList;
	}
	
	
	@RequestMapping(value = "/dashboard/my-users", method = RequestMethod.GET)
	public String myUsers( ModelMap model, HttpServletRequest request, HttpSession session) {
		
		Supplier supplier= supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		model.addAttribute("underline", "subscribers");
		List<UserFollow> userFollowList = userFollowRepository.findBySupplier(supplier);
		model.addAttribute("userFollowList", userFollowList);
		int recentlyAdded = 0;
		
		Date date = new Date();
		date.setDate(date.getDate()-16);
		for(UserFollow userFollow : userFollowList){
			if(userFollow.getFollowedDate().after(date)){
				recentlyAdded++;
			}
		}
		model.addAttribute("recentlyAddedUsersCount", recentlyAdded);
		model.addAttribute("allUsersCount", userFollowList.size());
		return "myusers/customers";

	}

	@RequestMapping(value = "/dashboard/user/find", method = RequestMethod.GET)
	public String nearestUsers( ModelMap model, HttpServletRequest request) {
		
		Supplier supplier= supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		List<User> userList = userRepository.findNearestUserFromSupplier(supplier.getAddress().getLatitude(),
				supplier.getAddress().getLongitude(), KrenaiCONSTANTS.nearestDistance);
		model.addAttribute("userList", userList);
		return "myusers/search-neighbour";

	}


	@RequestMapping(value = "/supplier/send/invite", method = RequestMethod.POST)
	public @ResponseBody String inviteUser(
			@RequestParam(value="uid",required=true) int userId) {
		
		Supplier supplier= supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		UserFollow uf  = new UserFollow();
		User user = new User();
		user.setUserId(userId);
		uf.setUser(user);
		uf.setSupplier(supplier);
		uf.setRequestedDate(new Date());
		uf.setStatusValue(KrenaiCONSTANTS.bookmarkRequested);
		try{
			userFollowRepository.save(uf);
			return "Request Sent";
		}catch(Exception e){
			uf = userFollowRepository.findByUserAndSupplier(user, supplier);
			if(uf.getStatusValue().equals(KrenaiCONSTANTS.bookmarkRequested)){
				userFollowRepository.delete(uf);
				return "Removed";
			}
			else if(uf.getStatusValue().equals(KrenaiCONSTANTS.supplierFollowing)){
				return "Following";
			}
			else{
				return "Failed";
			}
		}
	}

	
	@RequestMapping(value = "/dashboard/my-users/recent", method = RequestMethod.GET)
	public String myRecentUsers( ModelMap model, HttpServletRequest request, HttpSession session) {
		
		Supplier supplier= supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		model.addAttribute("underline", "recent");
		List<UserFollow> userFollowList = userFollowRepository.findBySupplier(supplier);
		List<UserFollow> recentUserFollowList = new ArrayList<UserFollow>();
		model.addAttribute("allUsersCount", userFollowList.size());
		
		Date date = new Date();
		date.setDate(date.getDate()-16);
		for(UserFollow userFollow : userFollowList){
			if(userFollow.getFollowedDate().after(date)){
				recentUserFollowList.add(userFollow);
			}
		}
		model.addAttribute("userFollowList", recentUserFollowList);
		model.addAttribute("recentlyAddedUsersCount", recentUserFollowList.size());
		return "myusers/customers";

	}


	@RequestMapping(value = "/dashboard/my-users/vendee", method = RequestMethod.GET)
	public String myVendee( ModelMap model, HttpServletRequest request, HttpSession session) {
		
		Supplier supplier= supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		model.addAttribute("underline", "vendee");
		List<UserFollow> userFollowList = userFollowRepository.findBySupplier(supplier);
		List<UserFollow> recentUserFollowList = new ArrayList<UserFollow>();
		model.addAttribute("allUsersCount", userFollowList.size());
		int count = 0;
		Date date = new Date();
		date.setDate(date.getDate()-16);
		for(UserFollow userFollow : userFollowList){
			if(userFollow.getFollowedDate().after(date)){
				count++;
			}
		}
		
		model.addAttribute("userFollowList", recentUserFollowList);
		model.addAttribute("recentlyAddedUsersCount", count);
		
		String[] strArray = {KrenaiCONSTANTS.ordercancelled, 
				KrenaiCONSTANTS.orderOutForDelivery,
				KrenaiCONSTANTS.orderDelivered, 
				KrenaiCONSTANTS.orderOrdered, 
				KrenaiCONSTANTS.orderInprocess,
				KrenaiCONSTANTS.orderReturned};
		List<User> userList = userRepository.findBySupplierAndStatusIn(supplier, strArray);
		
		
		for(User user : userList){
			UserFollow uf = new UserFollow();
			uf.setUser(user);
			recentUserFollowList.add(uf);
		}
		
		model.addAttribute("userFollowList", recentUserFollowList);
		
		return "myusers/customers";

	}
	

	@RequestMapping(value = "/find/user/byemail", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> userFind(@RequestParam(value="email")String email) {
		Map<String,String> map = new HashMap<String, String>();
		User user = userRepository.findByEmailId(email);
		if(user!=null){
			map.put("userName", user.getFirstName()+" "+user.getLastName());
			map.put("address", user.getDefaultUserAddressBook().getGoogleAddress());
			map.put("id", user.getUserId()+"");
			map.put("image", user.getProfileImageUrl());
		}
		else{
			return null;
		}
		
		return map;

	}


	@RequestMapping(value="/store/nextcomment", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> next(HttpServletRequest request, @RequestParam(value="last")int lastid){
		Supplier supplier= supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		System.out.println("*********supplierid**********"+supplier.getSupplierId());
		System.out.println("*********lastid********"+lastid);
		Iterable<Object> commentlist =  null;
		if(lastid>0){
			commentlist =  commentLikeRepo.findBySupplierAndLimitAndStoreCommentIdGT(supplier, lastid);
		}
		else{
			commentlist = commentLikeRepo.findBySupplier(supplier);
		}
		List<Map<String, String>> listmap = new ArrayList<Map<String,String>>();
		Object[] obj=null;
		System.out.println("**************size**********"+((List<Object>)commentlist).size());
		for(Object objlist:commentlist){
			Map<String, String> map = new HashMap<String, String>();
			obj=(Object[])objlist;
			map.put("firstname", obj[2].toString());
			map.put("lastname", obj[3].toString());
			map.put("imageurl", obj[4].toString());
			map.put("timestamp", obj[5].toString());
			map.put("count", obj[6].toString());
			map.put("comment", obj[7].toString());
			map.put("commentid", obj[0].toString());
			map.put("replycount", obj[8].toString());
			listmap.add(map);
		}
		
		return listmap;
	}
	

	@RequestMapping(value = "/dashboard/reviews", method = RequestMethod.GET)
	public String searchneighbour( ModelMap model, HttpServletRequest request, HttpSession session) {
		
		Supplier supplier= supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		List<SupplierRating> supplierRatingList=storeRatingService.findBySupplier(supplier);
		int totalRating = 0;
		float averageRating=0.0f;
		int audienceCount=0;
		try {
			for(SupplierRating supplierRating:supplierRatingList){
				if(supplierRating.isOneStarAudience()){
					//s1++;
					totalRating++;
					audienceCount++;
				}
				else if(supplierRating.isTwoStarAudience()){
					//s2++;
					totalRating++;
					audienceCount++;
				}
				else if(supplierRating.isThreeStarAudience()){
					///s3++;
					totalRating++;
					audienceCount++;
				}
				else if(supplierRating.isFourStarAudience()){
					//s4++;
					totalRating++;
					audienceCount++;
				}
				else if(supplierRating.isFiveStarAudience()){
					//s5++;
					totalRating++;
					audienceCount++;
				}
				
		
			}
			averageRating=totalRating/audienceCount;
			
		} catch (Exception e) {
			System.out.println("error in shopping contoller supplier rating"+e);
		}
		System.out.println("************averageRating**********"+averageRating);
		model.addAttribute("averageRating", averageRating);
		return "review/reviews";

	}

}
