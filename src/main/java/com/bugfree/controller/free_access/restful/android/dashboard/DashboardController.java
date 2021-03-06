package com.bugfree.controller.free_access.restful.android.dashboard;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bugfree.common.KrenaiCONSTANTS;
import com.bugfree.common.firebase.SendNotification;
import com.bugfree.controller.free_access.restful.android.service.AndroidControllerService;
import com.bugfree.controller.payment.Payment;
import com.bugfree.model.address.Address;
import com.bugfree.model.cart.Cart;
import com.bugfree.model.cart.CartProducts;
import com.bugfree.model.cart.OrderedProductDetail;
import com.bugfree.model.category.Category;
import com.bugfree.model.payment.KrenaiPayment;
import com.bugfree.model.product.Product;
import com.bugfree.model.social.network.UserFollow;
import com.bugfree.model.status.Status;
import com.bugfree.model.subcategory.SubCategory;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.product.SellerProductListings;
import com.bugfree.model.supplierRating.SupplierRating;
import com.bugfree.model.user.User;
import com.bugfree.repository.cart.CartRepository;
import com.bugfree.repository.cart.OrderedProductDetailRepository;
import com.bugfree.repository.category.CategoryRepository;
import com.bugfree.repository.payment.KrenaiPaymentRepository;
import com.bugfree.repository.payment.packages.MontlySubscriptionPackageRepository;
import com.bugfree.repository.social.feed.FeedsRepository;
import com.bugfree.repository.social.network.UserFollowRepository;
import com.bugfree.repository.supplierRating.store.StoreCommentLikeRepo;
import com.bugfree.repository.supplierRating.store.StoreCommentRepo;
import com.bugfree.service.address.AddressService;
import com.bugfree.service.cart.CartProductsService;
import com.bugfree.service.cart.CartService;
import com.bugfree.service.category.CategoryService;
import com.bugfree.service.encrypt.EncryptService;
import com.bugfree.service.product.ProductService;
import com.bugfree.service.status.StatusService;
import com.bugfree.service.subcategory.SubCategoryService;
import com.bugfree.service.supplier.SupplierService;
import com.bugfree.service.supplier.product.SellerProductListingsService;
import com.bugfree.service.supplier.shop.SupplierShopService;
import com.bugfree.service.supplierRating.SupplierRatingService;
import com.bugfree.service.user.UserService;
import com.google.android.gcm.server.Message;

@Controller
public class DashboardController {
	@Autowired
	private  StatusService statusService;

	@Autowired
	private SupplierService supplierService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private SupplierShopService supplierShopService;
	@Autowired
	private UserService userService;
	@Autowired
	private EncryptService encryptService;
	@Autowired
	private SellerProductListingsService sellerProductListingsService;
	@Autowired
	private AndroidControllerService androidControllerService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubCategoryService subCategoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private CartProductsService cartProductsService;
	@Autowired
	private OrderedProductDetailRepository orderedProductDetailRepo;
	@Autowired
	private UserFollowRepository userFollowRepository;
	@Autowired
	private SupplierRatingService storeRatingService;
	@Autowired
	private StoreCommentRepo storeCommentRepo;
	@Autowired
	private StoreCommentLikeRepo commentLikeRepo;
	@Autowired
	private FeedsRepository feedsRepository;
	@Autowired
	private MontlySubscriptionPackageRepository subscriptionPackRepo;
	@Autowired private KrenaiPaymentRepository krenaiPayRepo;
	

	@RequestMapping(value="/application-json/android/user/supplier/dashboard/data")
	private @ResponseBody Map<String,Map<String,String>> generateLoginSession(
			@RequestParam(value="sessionId",required=true) String sessionId,
			HttpServletRequest request){
		
		Map<String,Map<String,String>> mapMap = new HashMap<String,Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		
		System.out.println("********************"+sessionId);
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		System.out.println("***sup status***"+supplier.getStatus().getStatusId()+"*****status***"+activeStatus.getStatusId()+"*****************supplier*****"+supplier.getSupplierId());
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			Status krenaiRejectedStatus = statusService.findByStatusValue(KrenaiCONSTANTS.blockedByKrenai);
			Object objectCount = sellerProductListingsService.findAvailableQtyCountBySupplierId(supplier.getSupplierId(),krenaiRejectedStatus.getStatusId());
			Object[] objectArray = (Object[])objectCount;
			map.put("active", String.valueOf(objectArray[1]));
			map.put("inActive", String.valueOf(objectArray[2]));
			map.put("blockedByKrenai", String.valueOf(objectArray[3]));
			map.put("profileImage", supplier.getImagePath());
			map.put("bannerImage", supplier.getSupplierShop().getShopTheme());
			map.put("supplierName", supplier.getFullName());
			map.put("supplierAddress", supplier.getAddress().getGoogleAddress());
			mapMap.put("data", map);
			return mapMap;
			
		}
		map.put("failed", "failed");
		mapMap.put("failed", map);
		return mapMap;
		
	}
	

	
	@RequestMapping(value="/application-json/android/user/supplier/products/listing/instock")
	private @ResponseBody Map<String,List<Map<String,String>>> inStockProducts(
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="index",required=true) int index,
			HttpServletRequest request){
		
		System.out.println("********************"+sessionId);
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapListMap = new HashMap<String,List<Map<String,String>>> ();
		List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;

		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			List<SellerProductListings> productList = 
					sellerProductListingsService.findBySupplierAndStatusAndSupplierIdGreaterThanLimit10OrderBySPLI_ID(supplier, activeStatus, index);
			System.out.println("************************list size******"+productList.size());
			
			if(productList.size()>0){
				
				for(SellerProductListings spl : productList){
					map = new HashMap<String,String>();
					map.put("productName", spl.getProduct().getProductName());
					map.put("sellerProductId", String.valueOf(spl.getSellerProductListingId()));
					map.put("sellingPrice", String.valueOf(spl.getSellingPrice()));
					map.put("packageQty", spl.getProduct().getPackagedQuantity()+"");
					map.put("packageUnit", spl.getProduct().getPackagedUnit());
					try{
						map.put("productDescription", spl.getProduct().getProductDescription().getDescription());
					}catch(Exception e){
						map.put("productDescription", "description not available");
					}
					map.put("productMrp", String.valueOf(spl.getMrp()));
					map.put("minimumSellingQty", String.valueOf(spl.getMinimumSellQuantity()));
					try {
						map.put("imageUrl", spl.getProduct().getProductImage().getImagePath1().replace(" ", "+"));
					} catch (Exception e) {
						map.put("imageUrl", "");
					}
					map.put("availableQty", String.valueOf(spl.getAvailableQuantity()));
					listMap.add(map);
					
					System.out.println("************************************gfgfg********"+spl.getProduct().getProductImage().getImagePath1());
				}
				mapListMap.put("data", listMap);
				return mapListMap;
			}
		}
		mapListMap.put("data", listMap);
		
		return mapListMap;
	}
	


	@RequestMapping(value="/application-json/android/user/supplier/dashboard/payment/lastmonth")
	private @ResponseBody Map<String, Map<String, String>> inStockProducts(
			@RequestParam(value="sessionId",required=true) String sessionId,
			HttpServletRequest request){
		
		System.out.println("********************"+sessionId);
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,Map<String,String>> mapListMap = new HashMap<String,Map<String,String>> ();
		Map<String,String> map = null;

		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			Status status = statusService.findByStatusValue(KrenaiCONSTANTS.orderDelivered);
			List<Object> objectList = cartRepository.findOrderNosAndOrderAmountMonthlyWise(supplier, status);
			
			for(Object object : objectList){
				Object[] objArray = (Object[]) object;
				Date date = new Date();
				
				if(date.getMonth()-1==Integer.valueOf(String.valueOf(objArray[0]))-1 &&
						date.getYear()+1900==Integer.parseInt(String.valueOf(objArray[1]))){
					map = new HashMap<String, String>();
					map.put("month", new DateFormatSymbols().getMonths()[Integer.valueOf(String.valueOf(objArray[0]))-1]);
					map.put("year", String.valueOf(objArray[1]));
					map.put("orderNos", String.valueOf(objArray[2]));
					map.put("transactionAmount", String.valueOf(objArray[3]));
					mapListMap.put("lastMonthTransaction", map);
				}
				
			}
			
		}
		mapListMap.put("lastMonthTransaction", map);
		return mapListMap;
	}
	



	@RequestMapping(value="/application-json/android/user/supplier/dashboard/order/count")
	private @ResponseBody Map<String, Map<String, String>> dashboardOrderCount(
			@RequestParam(value="sessionId",required=true) String sessionId,
			HttpServletRequest request){
		
		System.out.println("********************"+sessionId);
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,Map<String,String>> mapMap = new HashMap<String,Map<String,String>> ();
		Map<String,String> map = null;

		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			int count=0;
			List<Cart> cartTempList = new ArrayList<Cart>();
			
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
					count++;
					cartTempList.add(cart);
				}
				
			}
			if(cartTempList.size()>0){
				Object object = orderedProductDetailRepo.findTotalOrderAmountByCartIn(cartTempList);
				if(object!=null){
					map=new HashMap<String, String>();
					map.put("amount",String.valueOf(object));
					map.put("count", String.valueOf(count));
					mapMap.put("orderDetails", map);
				}
			}
		}
		mapMap.put("orderDetails", map);
		return mapMap;
	}
	



	@RequestMapping(value="/application-json/android/user/supplier/dashboard/social/count")
	private @ResponseBody Map<String, Map<String, String>> getSocialCount(
			@RequestParam(value="sessionId",required=true) String sessionId){
		
		System.out.println("********************"+sessionId);
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,Map<String,String>> mapListMap = new HashMap<String,Map<String,String>> ();
		Map<String,String> map = new HashMap<String, String>();
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			List<Object> objectList = supplierService.findSocialCount(supplier,KrenaiCONSTANTS.nearestDistance);
			for(Object obj: objectList){
				Object[] objArray = (Object[]) obj;
				map.put(String.valueOf(objArray[1]), String.valueOf(objArray[0]));
			}
			map.put("messages", String.valueOf(0));
			map.put("rating", String.valueOf(0));
			
		}
		mapListMap.put("dashboardCountDetails", map);
		return mapListMap;
	}
	

	
	
	@RequestMapping(value="/application-json/android/user/supplier/products/listing/outofstock")
	private @ResponseBody Map<String,List<Map<String,String>>> outOfStockStockProducts(
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="index",required=true) int index,
			HttpServletRequest request){
		
		System.out.println("********************"+sessionId);
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapListMap = new HashMap<String,List<Map<String,String>>> ();
		List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;

		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		Status inactiveStatus = statusService.findByStatusValue(KrenaiCONSTANTS.inactiveStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			List<SellerProductListings> productList = 
					sellerProductListingsService.findBySupplierAndStatusAndSupplierIdGreaterThanLimit10OrderBySPLI_ID(supplier, inactiveStatus, index);
			System.out.println("************************list size******"+productList.size());
			
			if(productList.size()>0){
				
				for(SellerProductListings spl : productList){
					map = new HashMap<String,String>();
					map.put("productName", spl.getProduct().getProductName());
					map.put("sellerProductId", String.valueOf(spl.getSellerProductListingId()));
					map.put("sellingPrice", String.valueOf(spl.getSellingPrice()));
					try{
						map.put("productDescription", spl.getProduct().getProductDescription().getDescription());
					}catch(Exception e){
						map.put("productDescription", "description not available");
					}
					map.put("productMrp", String.valueOf(spl.getMrp()));
					map.put("minimumSellingQty", String.valueOf(spl.getMinimumSellQuantity()));
					try {
						map.put("imageUrl", spl.getProduct().getProductImage().getImagePath1().replace(" ", "+"));
					} catch (Exception e) {
						map.put("imageUrl", "");
					}
					map.put("availableQty", String.valueOf(spl.getAvailableQuantity()));
					listMap.add(map);
				}
				mapListMap.put("outOfStockProducts", listMap);
				return mapListMap;
			}
		}
//		map = new HashMap<String,String>();
//		map.put("failed", "failed");
		listMap=new ArrayList<Map<String,String>>();
		mapListMap.put("outOfStockProducts",listMap );
		
		return mapListMap;
	}
	

	@RequestMapping(value="/application-json/android/user/supplier/products/listing/blockedByKrenai")
	private @ResponseBody Map<String,List<Map<String,String>>> blockedStockStockProducts(
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="index",required=true) int index,
			HttpServletRequest request){
		
		System.out.println("********************"+sessionId);
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapListMap = new HashMap<String,List<Map<String,String>>> ();
		List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;

		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		Status blockedByKrenai = statusService.findByStatusValue(KrenaiCONSTANTS.blockedByKrenai);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			List<SellerProductListings> productList = 
					sellerProductListingsService.findBySupplierAndStatusAndSupplierIdGreaterThanLimit10OrderBySPLI_ID(supplier, blockedByKrenai, index);
			System.out.println("************************list size******"+productList.size());
			
			if(productList.size()>0){
				
				for(SellerProductListings spl : productList){
					map = new HashMap<String,String>();
					map.put("productName", spl.getProduct().getProductName());
					map.put("sellerProductId", String.valueOf(spl.getSellerProductListingId()));
					map.put("sellingPrice", String.valueOf(spl.getSellingPrice()));
					try{
						map.put("productDescription", spl.getProduct().getProductDescription().getDescription());
					}catch(Exception e){
						map.put("productDescription", "description not available");
					}
					map.put("productMrp", String.valueOf(spl.getMrp()));
					map.put("minimumSellingQty", String.valueOf(spl.getMinimumSellQuantity()));
					try {
						map.put("imageUrl", spl.getProduct().getProductImage().getImagePath1().replace(" ", "+"));
					} catch (Exception e) {
						map.put("imageUrl", "");
					}
					map.put("availableQty", String.valueOf(spl.getAvailableQuantity()));
					listMap.add(map);
				}
				mapListMap.put("inActiveProducts", listMap);
				return mapListMap;
			}
		}
//		map = new HashMap<String,String>();
//		map.put("failed", "failed");
		listMap=new ArrayList<Map<String,String>>();
		mapListMap.put("inActiveProducts",listMap );
		
		return mapListMap;
	}
	
	@RequestMapping(value="/application-json/android/user/supplier/product/list/modify")
	public @ResponseBody  String productListModify(Model model, HttpServletRequest request,
			@RequestParam(value="hidSellerProductListingId",required=true) String productListId,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="availableQty",required=true) int availableQty,
			@RequestParam(value="sellingPrice",required=true) float sellingPrice,
			@RequestParam(value="mrp",required=true) float mrp,
			@RequestParam(value="minimumSellingQty",required=true) int minimumSellingQty
			
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
		SellerProductListings sellerProductListing =  sellerProductListingsService.findOne(Integer.parseInt(productListId));
		sellerProductListing.setAvailableQuantity(availableQty);
		sellerProductListing.setSellingPrice(sellingPrice);
		sellerProductListing.setMrp(mrp);
		sellerProductListing.setMinimumSellQuantity(minimumSellingQty);
		try {
			sellerProductListingsService.save(sellerProductListing);
			
		} catch (Exception e) {
			return "failed";
		}
		
	}
		return "success";
}


	@RequestMapping(value="/application-json/android/user/supplier/store/category/list")
	public @ResponseBody  Map<String,List<Map<String,String>>>  categoryList(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId
			
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapMap = new HashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			List<Category> categoryList = (List<Category>) categoryService.findByStatus(activeStatus);
			Map<String,String> map = null;
			for(Category category : categoryList){
				map = new HashMap<String,String>();
				map.put("categoryId", String.valueOf(category.getCategoryId()));
				map.put("categoryName", category.getCategoryName());
				map.put("description", category.getCategoryDescription().getDescription());
				map.put("imageUrl", category.getWebIcon());
				mapList.add(map);
			}
			try {
				mapMap.put("categoryList", mapList);
				
			} catch (Exception e) {
				map = new HashMap<String,String>();
				map.put("failed", "failed");
				mapList.add(map);
				mapMap.put("failure", mapList);
				
			}
		
		}
		return mapMap;
	}
	


	@RequestMapping(value="/application-json/android/user/supplier/store/subcategory/list")
	public @ResponseBody  Map<String,List<Map<String,String>>>  subCategoryList(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="categoryId",required=true) int categoryId
			
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapMap = new HashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			Category category = new Category();
			category.setCategoryId(categoryId);
			List<SubCategory> subCategoryList = (List<SubCategory>) subCategoryService.findByCategoryAndStatus(category, activeStatus);
			
			Map<String,String> map = null;
			for(SubCategory subcategory : subCategoryList){
				map = new HashMap<String,String>();
				map.put("subCategoryId", String.valueOf(subcategory.getSubCategoryId()));
				map.put("categoryId", String.valueOf(subcategory.getCategory().getCategoryId()));
				map.put("subCategoryName", subcategory.getSubCategoryName());
				map.put("description", subcategory.getSubCategoryDescription().getDescription());
				mapList.add(map);
			}
			try {
				mapMap.put("subCategoryList", mapList);
				
			} catch (Exception e) {
				map = new HashMap<String,String>();
				map.put("failed", "failed");
				mapList.add(map);
				mapMap.put("failure", mapList);
				
			}
		
		}
		return mapMap;
	}
	

	@RequestMapping(value="/application-json/android/user/supplier/productsoncategoryandsubcategory")
	public @ResponseBody  Map<String,List<Map<String,String>>> productsoncategoryandsubcategory(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="categoryId",required=true) int categoryId,
			@RequestParam(value="subCategoryId",required=true) int subCategoryId
			
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapMap = new HashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			try {
			Category category = new Category();
			category.setCategoryId(categoryId);
			SubCategory subCategory = new SubCategory();
			subCategory.setSubCategoryId(subCategoryId);
			List <Product> productList = (List<Product>) productService.findByCategoryAndSubCategory(category, subCategory);
			
			for(Product product : productList){
				map = new HashMap<String,String>();
				map.put("productId", String.valueOf(product.getProductId()));
				map.put("productName", product.getProductName());
				try{
					map.put("productDescription", product.getProductDescription().getDescription());
				}catch(Exception e){
					map.put("productDescription", "description not available");
				}
				map.put("packagedQty", String.valueOf(product.getPackagedQuantity()));
				try{
					map.put("image", product.getProductImage().getImagePath1().replace(" ", "+"));
				}
				catch(Exception e){
					map.put("image", null);
				}
				map.put("brand", product.getBrand().getBrandName());
				mapList.add(map);
			}
			mapMap.put("productList", mapList);
			
			} catch (Exception e) {
				e.printStackTrace();
				map = new HashMap<String,String>();
				map.put("failed", "failed");
				mapList.add(map);
				mapMap.put("productList", mapList);
			}
		
		}
		return mapMap;
	}


	@RequestMapping(value="/application-json/android/user/supplier/product/add")
	public @ResponseBody  String AddStoreProductAlreadyInList(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="mrp",required=true) float mrp,
			@RequestParam(value="sellingPrice",required=true) float sellingPrice,
			@RequestParam(value="availableQty",required=true) int availableQty,
			@RequestParam(value="minimumSellingQty",required=true) int minimumSellingQty,
			@RequestParam(value="productId",required=true) int productId,
			@RequestParam(value="tradeType",required=true) String tradeType,
			@RequestParam(value="statusId",required=true) String statusValue
			
			
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			try {
			Product product = new Product();
			product.setProductId(productId);
			
			Status status = statusService.findByStatusValue(statusValue);
			
			SellerProductListings splVerify = 
					sellerProductListingsService.findBySupplierAndProduct(supplier, product);
			if(splVerify!=null){
				return "already exists in your selling list with "+splVerify.getStatus().getStatusValue()+" status";
			}
			SellerProductListings spl = new SellerProductListings();
			spl.setAvailabilityDate(new Date());
			spl.setAvailableQuantity(availableQty);
			spl.setMinimumSellQuantity(minimumSellingQty);
			spl.setMrp(mrp);
			spl.setProduct(product);
			spl.setSellingPrice(sellingPrice);
			spl.setStatus(status);
			spl.setSupplier(supplier);
			spl.setTradeType(tradeType);
			
			sellerProductListingsService.save(spl);
			
			} catch (Exception e) {return "failed";}
		
		}
		return "success";
	}


	@RequestMapping(value="/application-json/android/user/supplier/profile/setting")
	public @ResponseBody  Map<String,Map<String,String>>  methodSetting(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId
			
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,Map<String,String>> mapMap = new HashMap<String,Map<String,String>>();
		//List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			String days = "";
			try {
			map = new HashMap<String,String>();
			map.put("supplierName", supplier.getFullName());
			map.put("emailId", supplier.getEmailId());
			map.put("contactNo", supplier.getContactNo());
			map.put("storeName", supplier.getSupplierShop().getShopName());
			map.put("storeContactNo", supplier.getSupplierShop().getShopContactNo());
			map.put("storeAddress", supplier.getAddress().getGoogleAddress());
			map.put("storeType", supplier.getStoreType());
			map.put("bannerImage", supplier.getSupplierShop().getShopTheme());
			if(supplier.getSupplierShop().isSunday()){
				days += "sun";
			}
			if(supplier.getSupplierShop().isMonday()){
				days += ", mon";
			}
			if(supplier.getSupplierShop().isTuesday()){
				days += ", tues";
			}
			if(supplier.getSupplierShop().isWednesday()){
				days += ", wed";
			}
			if(supplier.getSupplierShop().isThursday()){
				days += ", thurs";
			}
			if(supplier.getSupplierShop().isFriday()){
				days += ", fri";
			}
			if(supplier.getSupplierShop().isSaturday()){
				days += ", sat";
			}
			days += ".";
			map.put("storeOpeningDays", days);
			map.put("storeOpenTiming", String.valueOf(supplier.getSupplierShop().getShopOpeningTime()));
			map.put("storeCloseTiming", String.valueOf(supplier.getSupplierShop().getShopClosingTime()));
			map.put("profileImage", supplier.getImagePath());
			
			mapMap.put("settingData", map);
			} catch (Exception e) {
				e.printStackTrace();
				map = new HashMap<String, String>();
				map.put("failed", "failed");
				mapMap.put("settingData", map);
				return mapMap;
			}
		
		}
		return  mapMap;
	}
	

	@RequestMapping(value="/application-json/android/user/supplier/store/product/outofstock/requested")
	public @ResponseBody  String outOfStock(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="productListingId",required=true) int spli
			
			
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			try {
			SellerProductListings spl = sellerProductListingsService.findOne(spli);
			spl.setAvailableQuantity(0);
			Status inactiveStatus = statusService.findByStatusValue(KrenaiCONSTANTS.inactiveStatus);
			spl.setStatus(inactiveStatus);
			sellerProductListingsService.save(spl);
			return "success";
			} catch (Exception e) {return "failed";}
		
		}
		return "success";
	}


	@RequestMapping(value="/application-json/android/user/supplier/call/request")
	public @ResponseBody  String callRequest(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="contactNo",required=true) String contact
			
			
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			try {
				supplier.setRequestedCall(contact);
				supplier.setRequestedCallDate(KrenaiCONSTANTS.getCurrentDate());
				supplierService.save(supplier);
				
			return "success";
			} catch (Exception e) {return "failed";}
		
		}
		return "success";
	}


	@RequestMapping(value="/application-json/android/user/supplier/order/{status}")
	public @ResponseBody  Map<String, List<Map<String, String>>>  myOrders(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@PathVariable(value="status") String byStatus
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapMap = new HashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		
		if(supplier!=null && supplier.getStatus().getStatusValue().equals(KrenaiCONSTANTS.activeStatus)){
			try {
			
				List<Object> cartList = null;
				
				if(byStatus.equals(KrenaiCONSTANTS.activeStatus)
						||byStatus.equals("completed")
						||byStatus.equals(KrenaiCONSTANTS.ordercancelled)){
					String[] strArray = null;
					if(byStatus.equals("active")){
						byStatus = KrenaiCONSTANTS.orderOrdered;
						strArray = new String[] {KrenaiCONSTANTS.orderOutForDelivery, KrenaiCONSTANTS.orderOrdered, KrenaiCONSTANTS.orderInprocess};
						
					}
					else if(byStatus.equals("completed")){
						strArray = new String[] {KrenaiCONSTANTS.orderDelivered};
								
					}
					else if(byStatus.equals("cancelled")){
						strArray = new String[] {KrenaiCONSTANTS.orderReturned, KrenaiCONSTANTS.ordercancelled};
						
					}
					System.out.println("========================="+strArray);
					cartList = cartService.findBySupplierAndStatusIn(supplier, Arrays.asList(strArray));
					//cartProductsList = cartProductsService.findByCartIn(cartList);
					
					
				}
				else if(byStatus.equals("all")){
					String[] strArray = {KrenaiCONSTANTS.ordercancelled, 
							KrenaiCONSTANTS.orderOutForDelivery, 
							KrenaiCONSTANTS.orderDelivered, 
							KrenaiCONSTANTS.orderOrdered, 
							KrenaiCONSTANTS.orderInprocess, 
							KrenaiCONSTANTS.orderReturned};
					cartList = cartService.findBySupplierAndStatusIn(supplier, Arrays.asList(strArray));
					//cartProductsList = cartProductsService.findByCartIn(cartList);
					
				}
				//SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
				System.out.println("************cart**********"+cartList.size());
				System.out.println("************supplier**********"+supplier.getSupplierId());
				Object[] objArray = null;
				for(Object object : cartList){
					objArray = (Object[]) object;
					map=new HashMap<String,String>();
					map.put("cartId", String.valueOf(objArray[0]));
					map.put("orderId", String.valueOf(objArray[1]));
					map.put("orderDate", String.valueOf(objArray[2]));
					map.put("userRequiredDate", String.valueOf(objArray[3]));
					map.put("userRequiredTime", String.valueOf(objArray[4]));
					map.put("status", String.valueOf(objArray[5]));
					map.put("user", String.valueOf(objArray[6])+" "+String.valueOf(objArray[7]));
					map.put("deliveryAddress", String.valueOf(objArray[8]));
					map.put("googleAddress", String.valueOf(objArray[9]));
					map.put("userImage", String.valueOf(objArray[10]));
					map.put("userContactRequest", String.valueOf(objArray[11]));
					map.put("userContact", /*String.valueOf(objArray[11])*/KrenaiCONSTANTS.customerSupport);
					map.put("amount", String.valueOf(objArray[12]));
					map.put("latitude", String.valueOf(objArray[13]));
					map.put("longitude", String.valueOf(objArray[14]));
					map.put("paymentStatus", String.valueOf(objArray[15]));
					map.put("paymentMode", String.valueOf(objArray[16]));
					System.out.println("************longitude**********"+String.valueOf(objArray[14]));
					mapList.add(map);
				}
				
				mapMap.put("orderList", mapList);
				
				
			} catch (Exception e) {
				e.printStackTrace();
				mapMap.put("orderList", mapList);
			}
		
		}
		return  mapMap;
	}
	


	@RequestMapping(value="/application-json/android/user/supplier/payment/monthwise")
	public @ResponseBody  Map<String, List<Map<String, String>>>  paymentList(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapMap = new HashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		
		if(supplier!=null && supplier.getStatus().getStatusValue().equals(KrenaiCONSTANTS.activeStatus)){
			try {
			
				Status status = statusService.findByStatusValue(KrenaiCONSTANTS.orderDelivered);
				List<Object> objectList = cartRepository.findOrderNosAndOrderAmountMonthlyWise(supplier, status);
				for(Object object : objectList){
					Object[] objArray = (Object[]) object;
					map = new HashMap<String, String>();
					map.put("month", new DateFormatSymbols().getMonths()[Integer.valueOf(String.valueOf(objArray[0]))-1]);
					map.put("year", String.valueOf(objArray[1]));
					map.put("orderNos", String.valueOf(objArray[2]));
					map.put("transactionAmount", String.valueOf(objArray[3]));
					mapList.add(map);
				}
				mapMap.put("paymentsList", mapList);
				
			} catch (Exception e) {
				e.printStackTrace();
				mapMap.put("paymentsList", mapList);
			}
		
		}
		return  mapMap;
	}
	



	@RequestMapping(value="/application-json/android/user/supplier/order/details")
	public @ResponseBody  Map<String, List<Map<String, String>>>  orderProduct(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="orderId") String orderId
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapMap = new HashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		
		System.out.println("****************************");
		if(supplier!=null && supplier.getStatus().getStatusValue().equals(KrenaiCONSTANTS.activeStatus)){
			try {
				Cart cart = cartService.findByOrderIdAndSupplier(orderId, supplier);
				System.out.println("*************cart id***************"+cart.getCartId());
				List<CartProducts> cartProductsList = cartProductsService.findByCart(cart);
				System.out.println("*************cart product size***************"+cartProductsList.size());
				List<OrderedProductDetail> productDetailList = orderedProductDetailRepo.findByCartProductIn(cartProductsList);
				System.out.println("*************productDetailList**************"+productDetailList.size());
				
				for(OrderedProductDetail opd : productDetailList){
					map = new HashMap<String, String>();
					map.put("productName", opd.getCartProduct().getSellerProductListing().getProduct().getProductName());
					map.put("packagedUnit", opd.getCartProduct().getSellerProductListing().getProduct().getPackagedUnit());
					map.put("packagedQty", opd.getCartProduct().getSellerProductListing().getProduct().getPackagedQuantity()+"");
					map.put("quantity", opd.getQuantity()+"");
					map.put("sellingPrice", opd.getSellingPrice()+"");
					map.put("productImage", opd.getCartProduct().getSellerProductListing().getProduct().getProductImage().getImagePath1().replace(" ", "+"));
					map.put("mrp", opd.getMrp()+"");
					mapList.add(map);
				}
				mapMap.put("orderDetails", mapList);
				
			} catch (Exception e) {
				e.printStackTrace();
				mapMap.put("orderDetails", mapList);
			}
		
		}
		return  mapMap;
	}
	



	@RequestMapping(value="/application-json/android/user/supplier/subscribers/list")
	public @ResponseBody  Map<String, List<Map<String, String>>>  supplierSubscribers(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapMap = new HashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		
		if(supplier!=null && supplier.getStatus().getStatusValue().equals(KrenaiCONSTANTS.activeStatus)){
			try {
			List<UserFollow> followList = userFollowRepository.findBySupplier(supplier);
			for(UserFollow uf : followList){
				map = new HashMap<String, String>();
				map.put("userId", uf.getUser().getUserId()+"");
				map.put("userName", uf.getUser().getFirstName()+" "+uf.getUser().getLastName());
				map.put("address", uf.getUser().getDefaultUserAddressBook().getGoogleAddress());
				map.put("profileImage", uf.getUser().getProfileImageUrl());
				mapList.add(map);
			}
			mapMap.put("usersList", mapList);
			} catch (Exception e) {
				e.printStackTrace();
				mapMap.put("usersList", mapList);
			}
		
		}
		return  mapMap;
	}


	@RequestMapping(value="/application-json/android/user/supplier/subscribers/reviews")
	public @ResponseBody  Map<String, List<Map<String, String>>>  supplierSubscribersReviews(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="commentId",required=true) int lastId
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapMap = new HashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		
		if(supplier!=null && supplier.getStatus().getStatusValue().equals(KrenaiCONSTANTS.activeStatus)){
			try {

				List<Object> commentlikelist = new ArrayList<Object> ();
				if(lastId>0)
					commentlikelist =(List<Object>) commentLikeRepo.findBySupplierAndLimitAndStoreCommentIdGT(supplier,lastId);
				else
					commentlikelist =(List<Object>) commentLikeRepo.findBySupplier(supplier);
				Object[] objarray=null;
				for(Object object: commentlikelist){
					objarray=(Object[])object;
					map = new HashMap<String, String>();
					map.put("firstname", objarray[2].toString());
					map.put("lastname", objarray[3].toString());
					map.put("imageurl", objarray[4].toString());
					map.put("timestamp", objarray[5].toString());
					map.put("count", objarray[6].toString());
					map.put("comment", objarray[7].toString());
					map.put("commentid", objarray[0].toString());
					map.put("replycount", objarray[8].toString());
					mapList.add(map);
				}
				mapMap.put("reviewList", mapList);
				
			} catch (Exception e) {
				e.printStackTrace();
				mapMap.put("reviewList", mapList);
			}
		
		}
		return  mapMap;
	}


	@RequestMapping(value="/application-json/android/user/supplier/nearest/users/list")
	public @ResponseBody  Map<String, List<Map<String, String>>>  supplierNeatestUsers(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="userId",required=true) int userId
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapMap = new HashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		
		if(supplier!=null && supplier.getStatus().getStatusValue().equals(KrenaiCONSTANTS.activeStatus)){
			try {

				List<User> userList = new ArrayList<User> ();
				if(userId==0)
					userList = userService.findNearestUserNotInFollowList(supplier.getAddress().getLatitude(),
								supplier.getAddress().getLongitude(),KrenaiCONSTANTS.nearestDistance,supplier.getSupplierId());
				else
					userList =userService.findNearestUserNotInFollowListUserGt(supplier.getAddress().getLatitude(),
							supplier.getAddress().getLongitude(),KrenaiCONSTANTS.nearestDistance,supplier.getSupplierId(), userId);
				
				for(User user : userList){
					map = new HashMap<String,String>();
					map.put("userName", user.getFirstName()+" "+user.getLastName());
					map.put("profileImage", user.getProfileImageUrl());
					map.put("address", user.getDefaultUserAddressBook().getGoogleAddress());
					map.put("userId", user.getUserId()+"");
					mapList.add(map);
				}
				mapMap.put("nearestUserList", mapList);
				
			} catch (Exception e) {
				e.printStackTrace();
				mapMap.put("nearestUserList", mapList);
			}
		
		}
		return  mapMap;
	}
	


	@RequestMapping(value="/application-json/android/user/supplier/feeds/list")
	public @ResponseBody  Map<String, List<Map<String, String>>>  supplierSubscribersFeedsList(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="feedId",required=true) int feedsBefore
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,List<Map<String,String>>> mapMap = new HashMap<String,List<Map<String,String>>>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		List<Object> feedsList= null;
		
		if(supplier!=null && supplier.getStatus().getStatusValue().equals(KrenaiCONSTANTS.activeStatus)){
			try {


		    	if(feedsBefore == 0){
		    		System.out.println("***********feedsBefore****"+feedsBefore);
		    		feedsList = feedsRepository.findByPublishedBySupplierSupplierIdDesc(supplier.getSupplierId());
		    	}
		    	else
		    		feedsList = feedsRepository.findByPublishedBySupplierSupplierId(supplier.getSupplierId(), feedsBefore);
		    	
		        	for(Object f :feedsList){
		        		Object[] objectArray = (Object[])f;
		        		map=new HashMap<String, String>();
		        		System.out.println("***********feedsId****"+String.valueOf(objectArray[0]));
		        		map.put("feedsId", String.valueOf(objectArray[0]));
		        		map.put("likesCount",String.valueOf(objectArray[2]));
		        		map.put("publishedBy", String.valueOf(objectArray[3]));
		        		if(String.valueOf(objectArray[4])==null||String.valueOf(objectArray[4])==""){
		        			map.put("hasImage", "no");
		        		}
		        		else
		        			map.put("hasImage", "yes");
		        		//map.put("publishedByUserId", String.valueOf(f.getPublishedByUser().getUserId()));
		        		map.put("postedByUserImage", String.valueOf(objectArray[5]));
		        		
		        		map.put("imageRedirectUrl", String.valueOf(objectArray[6]));
		        		map.put("feedPublishedTimestamp",String.valueOf(objectArray[7]));
		        		map.put("imageCaptionUrl", String.valueOf(objectArray[4]).replace(' ', '+'));
		        		map.put("feedMessage", String.valueOf(objectArray[8]));
		        		map.put("shareCount", String.valueOf(objectArray[9]));
		        		map.put("commentCount",String.valueOf(objectArray[1]));
		        		mapList.add(map);
		        	}
		        	mapMap.put("feedsList", mapList);
				
			} catch (Exception e) {
				e.printStackTrace();
				mapMap.put("feedsList", mapList);
			}
		
		}
		return  mapMap;
	}
	

	@RequestMapping(value="/application-json/android/user/supplier/rating/details")
	public @ResponseBody  Map<String, Map<String, String>>  subscribeDetails(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Map<String,Map<String,String>> mapMap = new HashMap<String,Map<String,String>>();
		Map<String,String> map = null;
		
		if(supplier!=null && supplier.getStatus().getStatusValue().equals(KrenaiCONSTANTS.activeStatus)){
			try {
				List<SupplierRating> supplierRatingList=storeRatingService.findBySupplier(supplier);
				int totalRating = 0;
				float averageRating=0.0f;
				
				/*int s1=0;
				int s2=0;
				int s3=0;
				int s4=0;
				int s5=0;*/
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
				map = new HashMap<String, String>();
				map.put("totalRating", totalRating+"");
				map.put("averageRating", averageRating+"");
				mapMap.put("ratingDetails", map);
				
			} catch (Exception e) {
				e.printStackTrace();
				mapMap.put("ratingDetails", map);
			}
		
		}
		return  mapMap;
	}
	

	@RequestMapping(value="/application-json/android/user/supplier/profile/address/update")
	public @ResponseBody   String updateAddress(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="lat",required=true) Double lat,
			@RequestParam(value="lng",required=true) Double lng,
			@RequestParam(value="googleAddress",required=true) String googleAddress
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			try {
				Address address = supplier.getAddress();
				address.setLatitude(lat);
				address.setLongitude(lng);
				address.setGoogleAddress(googleAddress);
				addressService.save(address);
				
				return "success";
			
			} catch (Exception e) {return "failed";}
		
		}
		return "failed";
	}
		

	@RequestMapping(value="/application-json/android/user/supplier/order/accept")
	public @ResponseBody   String orderAccept(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="orderId",required=true) String orderId
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			try {
				Cart cart = cartService.findByOrderIdAndSupplier(orderId, supplier);
				if(cart!=null && cart.getStatus().getStatusValue().equals(KrenaiCONSTANTS.orderOrdered)){
					Status status = statusService.findByStatusValue(KrenaiCONSTANTS.orderInprocess);
					cart.setStatus(status);
					String msg = "Your order is now processed and expected +"
		            		+ " duration to deliver your order is "+request.getParameter("time")+" (appr.)";
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
				else{
					return "failed";
				}
			
			} catch (Exception e) {return "failed";}
		
		}
		return "failed";
	}

	@RequestMapping(value="/application-json/android/user/supplier/order/outfordelivery")
	public @ResponseBody   String orderOutForDelivery(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="orderId",required=true) String orderId
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			try {
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
				else{
					return "failed";
				}
			
			} catch (Exception e) {return "failed";}
		
		}
		return "failed";
	}
		
	@RequestMapping(value="/application-json/android/user/supplier/order/delivered")
	public @ResponseBody   String orderDelivered(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="orderId",required=true) String orderId
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			try {
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
					//cart.setDeliveredByAgentBoy("");
					cart = cartService.save(cart);
					
					try{
						if(request.getParameter("paymentDone")!="" && request.getParameter("paymentDone")!=null 
								&& cart.getPaymentMode()!=KrenaiCONSTANTS.paymentModeOnline && request.getParameter("paymentDone")!=KrenaiCONSTANTS.paymentModeOnline
								&& !request.getParameter("paymentDone").equals(KrenaiCONSTANTS.paymentModeOnline)
								&& cart.getPaymentMode()!=KrenaiCONSTANTS.paymentModePromo && request.getParameter("paymentDone")!=KrenaiCONSTANTS.paymentModePromo
								&& !request.getParameter("paymentDone").equals(KrenaiCONSTANTS.paymentModePromo)
								&& cart.getPaymentMode()!=KrenaiCONSTANTS.paymentModeWallet && request.getParameter("paymentDone")!=KrenaiCONSTANTS.paymentModeWallet
								&& !request.getParameter("paymentDone").equals(KrenaiCONSTANTS.paymentModeWallet)){
							System.out.println("-------------------into the payment done-----------------------"+request.getParameter("paymentDone"));
							if(request.getParameter("selectedPaymentStatuss")==KrenaiCONSTANTS.paymentStatusUnpaid || 
									request.getParameter("selectedPaymentStatuss")==KrenaiCONSTANTS.paymentStatusFree ||
										request.getParameter("selectedPaymentStatuss").equals(KrenaiCONSTANTS.paymentStatusUnpaid)||
											request.getParameter("selectedPaymentStatuss").equals(KrenaiCONSTANTS.paymentStatusFree)){
								
								cart.setPaymentStatus(request.getParameter("selectedPaymentStatuss"));
								
							}else{
								
								cart.setPaymentStatus(request.getParameter("selectedPaymentStatuss"));
								cart.setPaymentMode(request.getParameter("selectedPaymentModes"));
								cart.setAmountReceived(Float.parseFloat(request.getParameter("amountReceived")));
								cart.setExtraAmountRemarks(request.getParameter("extraAmountReceivedRemark"));
								
								
								
							}
							cartService.save(cart);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					
					return "success";
				}
				else{
					return "failed";
				}
			
			} catch (Exception e) {return "failed";}
		
		}
		return "failed";
	}
		
	@RequestMapping(value="/application-json/android/user/supplier/order/returned")
	public @ResponseBody   String orderReturned(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="orderId",required=true) String orderId,
			@RequestParam(value="reason",required=true) String reason
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			try {
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
					cart.setCancellationReason(reason);
					cart = cartService.save(cart);
					return "success";
				}
				else{
					return "failed";
				}
			
			} catch (Exception e) {return "failed";}
		
		}
		return "failed";
	}
	

	@RequestMapping(value="/application-json/android/user/supplier/request/bookmark")
	public @ResponseBody  String requestBookmark(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId,
			@RequestParam(value="userId",required=true) int userId
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
		
		try {
			UserFollow uf  = new UserFollow();
			User user = new User();
			user.setUserId(userId);
			uf.setUser(user);
			uf.setSupplier(supplier);
			uf.setRequestedDate(new Date());
			uf.setStatusValue(KrenaiCONSTANTS.bookmarkRequested);
			try{
				userFollowRepository.save(uf);
				return "success";
			}catch(Exception e){
				uf = userFollowRepository.findByUserAndSupplier(user, supplier);
				if(!uf.getStatusValue().equals(KrenaiCONSTANTS.supplierFollowing)){
					uf.setRequestedDate(new Date());
					uf.setStatusValue(KrenaiCONSTANTS.bookmarkRequested);
					userFollowRepository.save(uf);
					return "success";
				}
				else{
					return "failed";
				}
			}
		} catch (Exception e) {
			return "failed";
		}
		
	}
		return "failed";
}

	

	@RequestMapping(value="/application-json/android/user/supplier/get/payment/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getpaymentList(
			@RequestParam(value="sessionId",required=true) String sessionId
			){
		List<KrenaiPayment> paymentList = null;
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		Map<String,Object> map  = new HashMap<String, Object>();
		Map<String,Object> mp = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			try {
				paymentList = krenaiPayRepo.findBySupplier(supplier);
				for(KrenaiPayment payment: paymentList){
					mp = new HashMap<String, Object>();
					mp.put("paymentId", payment.getKrenaiPaymentId());
					mp.put("package", payment.getMontlySubscriptionPackage().getPackageName());
					mp.put("packageMon", payment.getMontlySubscriptionPackage().getSubscriptionMonths());
					mp.put("supplier", payment.getSupplier().getFullName());
					mp.put("storeName", payment.getSupplier().getSupplierShop().getShopName());
					mp.put("storeContact", payment.getSupplier().getContactNo());
					mp.put("amount", payment.getPaidAmount());
					mp.put("paymentDate",payment.getPaymentDate());
					mp.put("serviceStart", payment.getServiceStartDate());
					mp.put("serviceEnd", payment.getServiceEndDate());
					mp.put("paymentRecieved", payment.getPaymentRecievedBy());
					mp.put("invoice", payment.getKrenaiInvoice());
					mp.put("mode", payment.getPaymentMode());
					list.add(mp);
				}
				map.put("data", list);
				map.put("result", "success");
				return map;
				
			} catch (Exception e) {
				
				map.put("result", "failed");
				return map;
			}
		
		}
		map.put("result", "failed");
		return map;
	}
	
	@Autowired
	private KrenaiPaymentRepository paymentRepository;

	@RequestMapping(value="/application-json/android/user/supplier/subscription/payments")
	public @ResponseBody  Map<String, Object> methodSubscription(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId
			
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		Map<String,Object> mp = null;
		Map<String,Object> returnMap = new HashMap<String, Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			try {
				List<KrenaiPayment> paymentList = paymentRepository.findBySupplier(supplier, new Sort(Sort.Direction.DESC, "krenaiPaymentId"));
				for(KrenaiPayment payment : paymentList){
					mp = new HashMap<String, Object>();
					mp.put("paymentId", payment.getKrenaiPaymentId());
					mp.put("cheque", payment.getChequeNo());
					mp.put("discount", payment.getDiscountApplied());
					mp.put("invoice", payment.getKrenaiInvoice());
					mp.put("paidAmount", payment.getPaidAmount());
					mp.put("paymentDate", payment.getPaymentDate());
					mp.put("paymentMode", payment.getPaymentMode());
					mp.put("startDate",payment.getServiceStartDate());
					mp.put("endDate",payment.getServiceEndDate());
					mp.put("transactionId", payment.getTransactionId());
					list.add(mp);
				}
				
				returnMap.put("data", list);
				returnMap.put("result", "success");
				
			} catch (Exception e) {
				returnMap.put("result", "failed");
				return returnMap;
			}
		
		}
		else{
			returnMap.put("result", "failed");
			returnMap.put("reason", "login failed");
		}
		return returnMap;
	}
	

	@RequestMapping(value="/application-json/android/user/supplier/template")
	public @ResponseBody  String methodTemplate(Model model, HttpServletRequest request,
			@RequestParam(value="sessionId",required=true) String sessionId
			
			){
		Supplier supplier = androidControllerService.verifySupplier(sessionId);
		Map<String,Object> mp = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		if(supplier!=null && supplier.getStatus().getStatusId()==activeStatus.getStatusId()){
			
			try {
			
			
			} catch (Exception e) {return "failed";}
		
		}
		return "success";
	}
	
	

}
