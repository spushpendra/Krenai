package com.bugfree.controller.order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bugfree.common.KrenaiCONSTANTS;
import com.bugfree.common.firebase.SendNotification;
import com.bugfree.model.cart.Cart;
import com.bugfree.model.cart.CartProducts;
import com.bugfree.model.cart.OrderedProductDetail;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.user.User;
import com.bugfree.repository.cart.OrderedProductDetailRepository;
import com.bugfree.service.cart.CartProductsService;
import com.bugfree.service.cart.CartService;
import com.bugfree.service.status.StatusService;
import com.bugfree.service.supplier.SupplierService;
import com.google.android.gcm.server.Message;

@Controller
public class OrderController {

	@Autowired
	private CartService cartService;
	@Autowired
	private CartProductsService cartProductsService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private OrderedProductDetailRepository orderedProductDetailRepo;
	
	/*
	 @RequestMapping(value="/orders/list", method=RequestMethod.GET)
	public String orderList(Model model){
		String supplierEmail = (String)SecurityUtils.getSubject().getPrincipal();
		Supplier supplier = supplierService.findByEmailId(supplierEmail);
		List<CartProducts> cartProductsList = cartProductsService.findBySupplier(supplier);
		Set<Integer> cartNoSet = new HashSet<Integer> ();
		for(CartProducts cp:cartProductsList){
			cartNoSet.add(cp.getCart().getCartId());
		}
		Cart cart = new Cart();
		List<Integer> tempList = new ArrayList<Integer>();
		tempList.addAll(cartNoSet);
		List<Cart> cartList = cartService.findByCartIdIn(tempList);
		model.addAttribute("cartList", cartList);
		model.addAttribute("underline", "all");
		return "/orders/order-list";
	}
	*/
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
	

	@RequestMapping(value="/orders/list/{byStatus}", method=RequestMethod.GET)
	public String orderListWithUrlParam(Model model, @PathVariable String byStatus, HttpServletRequest request){
		String supplierEmail = (String)SecurityUtils.getSubject().getPrincipal();
		Supplier supplier = supplierService.findByEmailId(supplierEmail);
		//List<CartProducts> cartProductsList =null;
		List<Cart> cartList = null;
		
		if(byStatus.equals(KrenaiCONSTANTS.activeStatus)
				||byStatus.equals("completed")
				||byStatus.equals(KrenaiCONSTANTS.ordercancelled)){
			String[] strArray = null;
			if(byStatus.equals("active")){
				byStatus = KrenaiCONSTANTS.orderOrdered;
				strArray = new String[] {KrenaiCONSTANTS.orderOutForDelivery, KrenaiCONSTANTS.orderOrdered, KrenaiCONSTANTS.orderInprocess};
				model.addAttribute("underline", "active");
			}
			else if(byStatus.equals("completed")){
				strArray = new String[] {KrenaiCONSTANTS.orderDelivered};
						model.addAttribute("underline", "completed");
			}
			else if(byStatus.equals("cancelled")){
				strArray = new String[] {KrenaiCONSTANTS.orderReturned, KrenaiCONSTANTS.ordercancelled};
				model.addAttribute("underline", "cancelled");
			}
			System.out.println("************productListsize1**********");
			cartList = cartService.findBySupplierAndStatusIn(supplier, strArray);
			//cartProductsList = cartProductsService.findByCartIn(cartList);
			
			
		}
		else if(byStatus.equals("all")){
			String[] strArray = {KrenaiCONSTANTS.ordercancelled, 
					KrenaiCONSTANTS.orderOutForDelivery, 
					KrenaiCONSTANTS.orderDelivered, 
					KrenaiCONSTANTS.orderOrdered, 
					KrenaiCONSTANTS.orderInprocess, 
					KrenaiCONSTANTS.orderReturned};
			cartList = cartService.findBySupplierAndStatusIn(supplier, strArray);
			//cartProductsList = cartProductsService.findByCartIn(cartList);
			model.addAttribute("underline", "all");
		}
		
		/*if(cartProductsList.size()>0){

			Set<Integer> cartNoSet = new HashSet<Integer> ();
			for(CartProducts cp:cartProductsList){
				cartNoSet.add(cp.getCart().getCartId());
			}
			List<Integer> tempList = new ArrayList<Integer>();
			tempList.addAll(cartNoSet);
			cartList = cartService.findByCartIdIn(tempList);
		}*/
		Map<String, String> orderCountMap = calculateOrdersCount(supplier);
		System.out.println("****************ordercountservice*************"+orderCountMap.size());
		HttpSession session = request.getSession();
		session.setAttribute("orderCountSession", orderCountMap);
		
		model.addAttribute("orderCount",orderCountMap);
		model.addAttribute("cartList", cartList);
		return "/orders/order-list";
	}
	

	@RequestMapping(value="/orders/user/products", method=RequestMethod.POST)
	public String userOrderProduct(HttpServletRequest request, Model model,  @RequestParam(value="orderId", required=false) String orderId){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		Cart cart = cartService.findByOrderIdAndSupplier(orderId, supplier);
		List<CartProducts> cartProductsList = cartProductsService.findByCart(cart);
		List<OrderedProductDetail> productDetailList = orderedProductDetailRepo.findByCartProductIn(cartProductsList);
		System.out.println("********************cart products list size** "+cartProductsList.size());
		System.out.println("********************order Id** "+orderId);
		model.addAttribute("orderProductsList", productDetailList);
		model.addAttribute("userOrder", cart);
		model.addAttribute("request", request.getHeader("Referer"));
		return "/orders/user-order-product";
	}
	

	@RequestMapping(value="/supplier/order/update/accepted", method=RequestMethod.POST)
	public @ResponseBody String updateInProcessOrder( 
				@RequestParam(value="or_") String orderId, 
				HttpServletRequest request){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		Cart cart = cartService.findByOrderIdAndSupplier(orderId, supplier);
		if(cart!=null && cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderOrdered)){
			Status status = statusService.findByStatusValue(KrenaiCONSTANTS.orderInprocess);
			cart.setStatus(status);
			String msg = "Your order is now processed and expected +"
            		+ " duration to deliver your order is "+request.getParameter("getpoptime")+" (appr.)";
			Message message = new Message.Builder()
                    .collapseKey("data")
                    .timeToLive(3)
                    .delayWhileIdle(true)
                    //.addData("neighbourName", userNeighbour.getNeighbour1().getFirstName()+userNeighbour.getNeighbour1().getLastName())
                    .addData("imageUrl", "\""+""+"\"")
                    .addData("timestamp",  "\""+KrenaiCONSTANTS.getCurrentDate()+ "\"")
                    .addData("title", "\""+"inprocess"+"\"")
                    .addData("message",  "\""+msg+"\"")
                    .build();  
			
				new SendNotification(message , cart.getUser());
				cart.setStatusInProcessDate(KrenaiCONSTANTS.getCurrentDate());
				cart.setStatusInProcessMessage(msg);
			cart = cartService.save(cart);
			return "success";
		}
		return "failed";
	}
	

	@RequestMapping(value="/supplier/order/update/outdeli", method=RequestMethod.POST)
	public @ResponseBody String updateOutOfDeliveryOrder( @RequestParam(value="or_") String orderId){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		Cart cart = cartService.findByOrderIdAndSupplier(orderId, supplier);
		if(cart!=null && (cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderOrdered) || cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderInprocess))){
			Status status = statusService.findByStatusValue(KrenaiCONSTANTS.orderOutForDelivery);
			cart.setStatus(status);
			if(cart.getStatusInProcessDate()==null)
				cart.setStatusInProcessDate(new Date());
			cart.setStatusOutForDeliveryDate(new Date());
			cart = cartService.save(cart);
			return "success";
		}
		return "failed";
	}
	
	@RequestMapping(value="/supplier/order/update/deliv", method=RequestMethod.POST)
	public @ResponseBody String updateOrderDelivered(HttpServletRequest request, @RequestParam(value="or_") String orderId){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		Cart cart = cartService.findByOrderIdAndSupplier(orderId, supplier);
		if(cart!=null && (cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderOrdered) 
				|| cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderInprocess) 
				|| cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderOutForDelivery))){
			Status status = statusService.findByStatusValue(KrenaiCONSTANTS.orderDelivered);
			cart.setStatus(status);
			if(cart.getStatusInProcessDate()==null)
				cart.setStatusInProcessDate(new Date());
			if(cart.getStatusOutForDeliveryDate()==null)
				cart.setStatusOutForDeliveryDate(new Date());
			cart.setDeliveredTime(new Date());
			cart.setDeliveredByAgentBoy(request.getParameter("agent"));
			cart = cartService.save(cart);
			return "success";
		}
		return "failed";
	}
	
	@RequestMapping(value="/supplier/order/update/returned", method=RequestMethod.POST)
	public @ResponseBody String updateOrderReturned(HttpServletRequest request, @RequestParam(value="or_") String orderId){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		Cart cart = cartService.findByOrderIdAndSupplier(orderId, supplier);
		if(cart!=null && (cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderOrdered) 
				|| cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderInprocess) 
				|| cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderOutForDelivery))){
			Status status = statusService.findByStatusValue(KrenaiCONSTANTS.orderReturned);
			cart.setStatus(status);
			if(cart.getStatusInProcessDate()==null)
				cart.setStatusInProcessDate(new Date());
			if(cart.getStatusOutForDeliveryDate()==null)
				cart.setStatusOutForDeliveryDate(new Date());
			cart.setStatusReturnedDate(new Date());
			cart.setCancellationReason(request.getParameter("reason"));
			cart = cartService.save(cart);
			return "success";
		}
		return "failed";
	}
	

	@RequestMapping(value="/supplier/order/update/cancel", method=RequestMethod.POST)
	public @ResponseBody String updateOrderCancelled(HttpServletRequest request, @RequestParam(value="or_") String orderId){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		Cart cart = cartService.findByOrderIdAndSupplier(orderId, supplier);
		if(cart!=null && (cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderOrdered) 
				|| cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderInprocess) 
				|| cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderOutForDelivery))){
			Status status = statusService.findByStatusValue(KrenaiCONSTANTS.ordercancelled);
			cart.setStatus(status);
			if(cart.getStatusInProcessDate()==null)
				cart.setStatusInProcessDate(new Date());
			if(cart.getStatusOutForDeliveryDate()==null)
				cart.setStatusOutForDeliveryDate(new Date());
//			cart.setStatusReturnedDate(new Date());
			Message message = new Message.Builder()
                    .collapseKey("data")
                    .timeToLive(3)
                    .delayWhileIdle(true)
                    .addData("imageUrl", "\""+""+"\"")
                    .addData("timestamp",  "\""+KrenaiCONSTANTS.getCurrentDate()+ "\"")
                    .addData("title", "\""+"cancelled"+"\"")
                    .addData("message",  "\""+"Your order("+cart.getOrderId()+") +"
                    		+ "is cancelled due to "+request.getParameter("cacelReason")+"\"")
                    .build();  
			    
				new SendNotification(message , cart.getUser());
				cart.setCancellationReason(request.getParameter("cacelReason"));
			cart = cartService.save(cart);
			return "success";
		}
		return "failed";
	}
	
	
	@RequestMapping(value="/orders/user/profile", method=RequestMethod.POST)
	public String userProfile(Model model, @RequestParam(value="orderId", required=false) String orderId){
		User user = cartService.findByOrderId(orderId).getUser();
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		List<Cart> cartList = cartService.findByUserAndSupplier(user, supplier);
		/*List<CartProducts> cartProductList = cartProductsService.findByCartInAndSupplier(cartList, supplier);
		Set<Integer> tempCartIds =new HashSet<Integer>();
		for(CartProducts cp: cartProductList){
			tempCartIds.add(cp.getCart().getCartId());
		}
		List<Integer> tempList = new ArrayList<Integer>();
		tempList.addAll(tempCartIds);
		cartList = cartService.findByCartIdIn(tempList); */
		model.addAttribute("cartList", cartList);
		//System.out.println("***************cartProductList length "+cartProductList.size());
		return "/orders/user-profile";
	}
}
