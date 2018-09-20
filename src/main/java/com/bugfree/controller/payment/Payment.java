package com.bugfree.controller.payment;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bugfree.common.KrenaiCONSTANTS;
import com.bugfree.model.cart.Cart;
import com.bugfree.model.cart.CartProducts;
import com.bugfree.model.payment.KrenaiPayment;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.repository.cart.CartRepository;
import com.bugfree.repository.payment.packages.MontlySubscriptionPackageRepository;
import com.bugfree.service.cart.CartProductsService;
import com.bugfree.service.payment.KrenaiPaymentService;
import com.bugfree.service.status.StatusService;
import com.bugfree.service.supplier.SupplierService;

@Controller
public class Payment {

	@Autowired
	private SupplierService supplierService;
	@Autowired
	private KrenaiPaymentService krenaiPaymentService;
	@Autowired
	private MontlySubscriptionPackageRepository montlySubscriptionPackageRepository;
	@Autowired
	private CartProductsService cartProductsService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private CartRepository cartRepository;
	

	private String getByDate(String date){
		char [] charArray = date.toCharArray();
		String months = charArray[5]+""+charArray[6];
			if(Integer.parseInt(months)==1){
				months="january";			
			}
			else if(Integer.parseInt(months)==2){
				months="february";
			}
			else if(Integer.parseInt(months)==3){
				months="march";
			}
			else if(Integer.parseInt(months)==4){
				months="april";
			}
			else if(Integer.parseInt(months)==5){
				months="may";
			}
			else if(Integer.parseInt(months)==6){
				months="june";
			}
			else if(Integer.parseInt(months)==7){
				months="july";
			}
			else if(Integer.parseInt(months)==8){
				months="august";
			}
			else if(Integer.parseInt(months)==9){
				months="september";
			}
			else if(Integer.parseInt(months)==10){
				months="october";
			}
			else if(Integer.parseInt(months)==11){
				months="november";
			}
			else if(Integer.parseInt(months)==12){
				months="december";
			}
			String year = charArray[0]+""+charArray[1]+""+charArray[2]+""+charArray[3];
			date = months+", "+year;
		return months;
	}
	
	@RequestMapping(value="/payment/subscription", method=RequestMethod.GET)
	public  String payment(Model model, HttpServletRequest request){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		
		List<KrenaiPayment> krenaiPaymentList = krenaiPaymentService.findBySupplier(supplier);
		System.out.println("*********krenaiPaymentList********"+krenaiPaymentList.size());
		model.addAttribute("krenaiPaymentList", krenaiPaymentList);
		return "payment/payment";
	}
	

	/*@RequestMapping(value="/payment/transactions", method=RequestMethod.GET)
	public  String onlineTransaction(Model model, HttpServletRequest request){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		Status status = statusService.findByStatusValue("ordered");
		List<Cart> cartList = cartRepository.findBySupplier(supplier);
		List<CartProducts> cartProductList = cartProductsService.findByCartIn(cartList);
		System.out.println("**************list no*******"+cartProductList.size());
		List<Map<String, String>> transactionMapList = new ArrayList<Map<String, String>> ();
		Map<String, String> detailMap ;
		Set<String> monthlyOrderIdSet = new HashSet<String> ();
		float monthlySelling = 0.0f;
		int key=1;
		int iteration=0;
		char [] charArray = null;
		int outerMonth=0;
		int outerYear=0;
		String month="00";
		String year="0000";
		for(CartProducts  cartProduct:cartProductList){
			String date ;
			date = cartProduct.getCreatedDate().toString();
			charArray = date.toCharArray();
			System.out.println("***************if****"+date);
		
			
			if(key==1){
				outerMonth = Integer.parseInt(charArray[5]+""+charArray[6]);
				outerYear = Integer.parseInt(charArray[0]+""+charArray[1]+""+charArray[2]+""+charArray[3]);
				month = charArray[5]+""+charArray[6];
				year = charArray[0]+""+charArray[1]+""+charArray[2]+""+charArray[3];
				System.out.println("***************if****1");
				key++;
			}
			else{
				System.out.println("***************else****2"); 
				month = charArray[5]+""+charArray[6];
				year = charArray[0]+""+charArray[1]+""+charArray[2]+""+charArray[3];
			}
			
			
			if(outerMonth==Integer.parseInt(month)&&outerYear==Integer.parseInt(year)){
					monthlyOrderIdSet.add(cartProduct.getOrderId());
					monthlySelling+=cartProduct.getSellerProductListing().getSellingPrice();
					System.out.println("***************if****3**"+cartProduct.getSellerProductListing().getSellingPrice());
				}
			else{
				monthlySelling+=cartProduct.getSellerProductListing().getSellingPrice();
				detailMap=new HashMap<String, String>();
					detailMap.put("month", getByDate(cartProductList.get(iteration-1).getModifiedDate().toString()));
					detailMap.put("selling", String.valueOf(monthlySelling));
					detailMap.put("transactions", String.valueOf(monthlyOrderIdSet.toArray().length));
					transactionMapList.add(detailMap);
					monthlySelling = 0.0f;
					monthlyOrderIdSet = new HashSet<String>();
					key=1;
					System.out.println("***************else****4");
				}
				
			++iteration;
			if(iteration==cartProductList.size()){
				System.out.println("*****************ifzz***5");
				monthlySelling+=cartProduct.getSellerProductListing().getSellingPrice();
				detailMap=new HashMap<String, String>();
				detailMap.put("month", getByDate(cartProductList.get(iteration-1).getCreatedDate().toString()));
				detailMap.put("selling", String.valueOf(monthlySelling));
				detailMap.put("transactions", String.valueOf(monthlyOrderIdSet.toArray().length));
				transactionMapList.add(detailMap);
			}
		}
		model.addAttribute("transactionList", transactionMapList);
		return "payment/online-transaction";
	}
	
	*/
	@RequestMapping(value="/payment/transactions", method=RequestMethod.GET)
	public  String onlineTransaction(Model model, HttpServletRequest request){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		Status status = statusService.findByStatusValue(KrenaiCONSTANTS.orderDelivered);
		List<Object> objectList = cartRepository.findOrderNosAndOrderAmountMonthlyWise(supplier, status);
		List<Map<String, String>> transactionMapList = new ArrayList<Map<String, String>> ();
		Map<String, String> detailMap ;
		for(Object object : objectList){
			Object[] objArray = (Object[]) object;
			detailMap = new HashMap<String, String>();
			detailMap.put("month", new DateFormatSymbols().getMonths()[Integer.valueOf(String.valueOf(objArray[0]))-1]);
			detailMap.put("year", String.valueOf(objArray[1]));
			detailMap.put("orderNos", String.valueOf(objArray[2]));
			detailMap.put("transactionAmount", String.valueOf(objArray[3]));
			transactionMapList.add(detailMap);
		}
		
		model.addAttribute("transactionList", transactionMapList);
		return "payment/online-transaction";
	}
	
	
	
	
}
