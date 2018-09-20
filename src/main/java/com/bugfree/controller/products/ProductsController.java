package com.bugfree.controller.products;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bugfree.common.KrenaiCONSTANTS;
import com.bugfree.common.amazon.AmazonService;
import com.bugfree.model.brand.Brand;
import com.bugfree.model.category.Category;
import com.bugfree.model.category.CategoryDescription;
import com.bugfree.model.cost.Tax;
import com.bugfree.model.product.Product;
import com.bugfree.model.product.ProductDescription;
import com.bugfree.model.product.ProductImage;
import com.bugfree.model.status.Status;
import com.bugfree.model.subcategory.SubCategory;
import com.bugfree.model.subcategory.SubCategoryDescription;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.product.SellerProductListings;
import com.bugfree.model.unit.Unit;
import com.bugfree.service.FeedsService;
import com.bugfree.service.brand.BrandService;
import com.bugfree.service.category.CategoryDescriptionService;
import com.bugfree.service.category.CategoryService;
import com.bugfree.service.cost.TaxService;
import com.bugfree.service.product.ProductDescriptionService;
import com.bugfree.service.product.ProductImageService;
import com.bugfree.service.product.ProductService;
import com.bugfree.service.status.StatusService;
import com.bugfree.service.subcategory.SubCategoryDescriptionService;
import com.bugfree.service.subcategory.SubCategoryService;
import com.bugfree.service.supplier.SupplierService;
import com.bugfree.service.supplier.product.SellerProductListingsService;
import com.bugfree.service.unit.MeasuredValueService;
import com.bugfree.service.unit.UnitService;


@Controller
public class ProductsController {
	
	@Autowired 
	private ProductService productService;
	@Autowired 
	private CategoryService categoryService;
	@Autowired
	private SubCategoryService subCategoryService;
	@Autowired
	private TaxService taxService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductDescriptionService productDescriptionService;
	@Autowired
	private MeasuredValueService measuredValueService;
	@Autowired
	private SellerProductListingsService sellerProductListingsService; 
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private CategoryDescriptionService categoryDescriptionService;
	@Autowired
	private SubCategoryDescriptionService subcategoryDescriptionService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	FeedsService feedsService;
	

	private File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	        File convFile = new File( multipart.getOriginalFilename());
	        multipart.transferTo(convFile);
	        return convFile;
	}
	
	private Boolean uploadToS3(AmazonS3 s3, String bucketName, String key, MultipartFile multipartProductImages){
		try {
            
            System.out.println("Uploading a new object to S3 from a file\n");
           try{
        	   s3.putObject(new PutObjectRequest(bucketName, key, multipartToFile(multipartProductImages)));
        	   
           }catch(Exception e){
        	   	System.out.println("***************************8exception in converting image ***"+e);
				return false;
           }
                       
            
        } catch (AmazonServiceException ase) {
        	System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            return false;
	           
        } catch (AmazonClientException ace) {
        	System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            return false;
             
        }
		return true;
	}
	
//	@RequestMapping(value="/products/add", method=RequestMethod.GET)
//	public String addProducts(Model model, HttpServletResponse response){
//		List<ProductJson> categoryMap=new ArrayList<ProductJson>();
//		Iterable<Category> categoryIterable= categoryService.findAll();
//		int id;
//		String value;
//		ProductJson productJson;
//		ObjectMapper objectMapper=new ObjectMapper();
//		for(Category category:categoryIterable){
//			productJson=new ProductJson();
//			id=category.getCategoryId();
//			value=category.getCategoryName();
//			productJson.setId(String.valueOf(id));
//			productJson.setValue(value);
//			categoryMap.add(productJson);
//		}
////		String  jsonObject="";
////		try {
////			jsonObject= objectMapper.writeValueAsString(categoryMap);
////			System.out.println("*******************json********************"+jsonObject);
////		} catch (JsonGenerationException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (JsonMappingException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		try {
////			PrintWriter  out=response.getWriter();
////			response.setContentType("application/json");
////		    response.setHeader("Cache-control", "no-cache, no-store");
////		    response.setHeader("Pragma", "no-cache");
////		    response.setHeader("Expires", "-1");
////		    response.setHeader("Access-Control-Allow-Origin", "*");
////		    response.setHeader("Access-Control-Allow-Methods", "GET,POST");
////		    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
////		    response.setHeader("Access-Control-Max-Age", "86400");
////			out.write(jsonObject);
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////		model.addAttribute("categoryMap", jsonObject);
//		model.addAttribute("categoryMap", categoryMap);
//		
//		return "products/addProducts";
//	}
	

	private Map<String, String> getProductListingCount(Supplier supplier){
		Map<String, String> productListingMap = new HashMap<String, String>();
		List<SellerProductListings> productList = (List<SellerProductListings>) sellerProductListingsService.findBySupplier(supplier);
		int activeCount=0;
		int emptyStockCount=0;
		int inactive=0;
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
			else if(spl.getStatus().getStatusValue().equals("inactive")){
				inactive++;
			}
			else if(spl.getStatus().getStatusValue().equals("blocked")){
				blockedCount++;
			}
			
		}
		productListingMap.put("all",String.valueOf(moreThan5Count+lessThan5Count+inactive+emptyStockCount));
		productListingMap.put("active",String.valueOf(activeCount));
		productListingMap.put("emptyStock",String.valueOf(emptyStockCount));
		productListingMap.put("inactive",String.valueOf(inactive));
		productListingMap.put("moreThan5",String.valueOf(moreThan5Count));
		productListingMap.put("lessThan5",String.valueOf(lessThan5Count));
		productListingMap.put("blocked",String.valueOf(blockedCount));
		
		return productListingMap;
	}

	private Map<String, String> getProductListingCount(List<SellerProductListings> productList){
		Map<String, String> productListingMap = new HashMap<String, String>();
		
		int activeCount=0;
		int emptyStockCount=0;
		int inactive=0;
		int moreThan5Count=0;
		int lessThan5Count=0;
		int blockedCount=0;
		
		for(SellerProductListings spl:productList){
			
			if(spl.getStatus().getStatusValue().equals("active")){
				if(spl.getAvailableQuantity()<5)
					lessThan5Count++;
				else
					moreThan5Count++;
			}
			else if(spl.getStatus().getStatusValue().equals("outOfStock")){
				if(spl.getAvailableQuantity()==0)
					emptyStockCount++;
			}
			else if(spl.getStatus().getStatusValue().equals("inactive")){
				inactive++;
			}
			else if(spl.getStatus().getStatusValue().equals("blocked")){
				blockedCount++;
			}
			
		}
		productListingMap.put("all",String.valueOf(moreThan5Count+lessThan5Count));
		productListingMap.put("active",String.valueOf(activeCount));
		productListingMap.put("emptyStock",String.valueOf(emptyStockCount));
		productListingMap.put("inactive",String.valueOf(inactive));
		productListingMap.put("moreThan5",String.valueOf(moreThan5Count));
		productListingMap.put("lessThan5",String.valueOf(lessThan5Count));
		productListingMap.put("blocked",String.valueOf(blockedCount));
		
		return productListingMap;
	}
	

	@RequestMapping(value="/products/add/search", method=RequestMethod.GET)
	public String addProductsSearch(Model model, HttpServletResponse response){
		
		return "products/selectAddProduct";
	}

	@RequestMapping(value="/products/add", method=RequestMethod.GET)
	public String addProductsNew(Model model, HttpServletResponse response){
		HashMap<Integer, String> categoryMap=new HashMap<Integer, String>();
		Status status = statusService.findByStatusValue("active");
		Iterable<Category> categoryIterable= categoryService.findByStatus(status);
		int id;
		String value;
		for(Category category:categoryIterable){
			id=category.getCategoryId();
			value=category.getCategoryName();
			categoryMap.put(id, value);
			System.out.println("*************map data*********************"+id+" / "+value);
		}
		Iterable<Brand> brandIterable = brandService.findAll();
		String[] statusArray = {"active","inactive"};
		Iterable<Tax> taxIterable=taxService.findAll();
		Iterable<Status> statusIterable=statusService.findByStatusValueIn(statusArray);
		Iterable<Unit> measuredUnitIterable=unitService.findAll();
		model.addAttribute("brandIterable", brandIterable);
		model.addAttribute("categoryMap", categoryMap);
		model.addAttribute("taxIterable", taxIterable);
		model.addAttribute("statusIterable", statusIterable);
		model.addAttribute("measuredUnitIterable", measuredUnitIterable);
		
		return "products/addProducts";
	}
	
	
	@RequestMapping (value="/products/getProductOnSubcategoryChange", method=RequestMethod.POST)
	public @ResponseBody List<Map<String,String>> getProductOnSubcategoryChange(HttpServletRequest request){
		Category category=new Category();
		System.out.println("*************************productList******"+request.getParameter("categoryId"));
		SubCategory subcategory=new SubCategory();
		subcategory.setSubCategoryId(Integer.parseInt(request.getParameter("subcategoryId")));
		category.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		Iterable<Product> productIterable=productService.findByCategoryAndSubCategory(category, subcategory);
		//Iterable<Product> productIterable=productService.findByCategory(category);
		List<Map<String,String>> productList=new ArrayList<Map<String,String>>();
		Map<String,String> mapToAdd;
		for(Product product: productIterable){
			System.out.println("*****************************"+product.getProductName());
			mapToAdd=new HashMap<String,String>();
			mapToAdd.put("productId", String.valueOf(product.getProductId()));
			mapToAdd.put("productName", product.getProductName());
			mapToAdd.put("packagedQuantity", String.valueOf(product.getPackagedQuantity()));
			mapToAdd.put("packagedUnit", product.getPackagedUnit());
			
//			try {
//				mapToAdd.put("image1", product.getProductImage().getImagePath1());
//			} catch (NullPointerException e) {
//				mapToAdd.put("image1", product.getProductName());
//			}
			try {
				mapToAdd.put("brand", product.getBrand().getBrandName());
			} catch (NullPointerException e) {
				mapToAdd.put("brand", "not available");
			}
			System.out.println("*******************brand***"+mapToAdd.get("brand"));
			productList.add(mapToAdd);
			System.out.println("*****************************"+product.getProductId());
			
		}
		
		System.out.println("*************************productList******"+productList.size());
		return productList;
	}
	
	
	@RequestMapping (value="/products/getSubcategoryOnCategoryChange", method=RequestMethod.POST)
	public @ResponseBody  List<Map<String,String>> getSubcategoryOnCategoryChange(HttpServletRequest request){
		List<Map<String,String>> subCategoryList=new ArrayList<Map<String,String>>();
		Category category=new Category();
		Status status = statusService.findByStatusValue("active");
		Iterable <SubCategory> subCategoryIterable = null;
		try {
			category.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
			 subCategoryIterable=subCategoryService.findByCategoryAndStatus(category,status);
			
		} catch (Exception e) {
			System.out.println("**********exception in getting subcategory list****"+e);
			return subCategoryList;
		}
		Map<String, String> mapToAdd;
		for (SubCategory subCategory:subCategoryIterable){
			mapToAdd=new HashMap<String, String>();
			mapToAdd.put("subcategoryId", String.valueOf(subCategory.getSubCategoryId()));
			mapToAdd.put("subcategoryName", subCategory.getSubCategoryName());
			System.out.println("*****************************"+subCategory.getSubCategoryId());
			System.out.println("*****************************"+subCategory.getSubCategoryName());
			
			subCategoryList.add(mapToAdd);
		}
		return subCategoryList;
		
	}
	
	@RequestMapping (value="/products/getProductDetails", method=RequestMethod.POST)
	public @ResponseBody  Map<String,String> getProductDetails(HttpServletRequest request){
		Map<String,String> productDetailsMap=new HashMap<String,String>();
		Product product=productService.findOne(Integer.parseInt(request.getParameter("productId")));
		try {
			productDetailsMap.put("productDescription",product.getProductDescription().getMetaDescription());
		} catch (NullPointerException e) {
			productDetailsMap.put("productDescription","Description Not Available");
		}
		try {
			productDetailsMap.put("measurementUnit",product.getPackagedUnit());
			
		} catch (NullPointerException e) {
			productDetailsMap.put("measurementUnit","No Described Unit Found");
			//productDetailsMap.put("measurementValue","No Certain Value");
		}
		try {
			productDetailsMap.put("measurementValue",product.getPackagedQuantity()+"");
			
		} catch (NullPointerException e) {
			productDetailsMap.put("measurementValue","No Certain Value");
			
		}
		try {
			productDetailsMap.put("imagePath1",product.getProductImage().getImagePath1());
			System.out.println("*****************************image***********************"+product.getProductImage().getImagePath1() );
		} catch (Exception e) {
			productDetailsMap.put("imagePath1","null");
			//System.out.println("*****************************image***********************"+product.getProductImage().getImagePath1() );
		}
		try {
			productDetailsMap.put("imagePath2",product.getProductImage().getImagePath2());
			System.out.println("*****************************image***********************"+product.getProductImage().getImagePath2() );
		} catch (Exception e) {
			productDetailsMap.put("imagePath2","null");
			//System.out.println("*****************************image***********************"+product.getProductImage().getImagePath2() );
		}
		try {
			productDetailsMap.put("imagePath3",product.getProductImage().getImagePath3());
		} catch (Exception e) {
			productDetailsMap.put("imagePath3","null");
		}
		try {
			productDetailsMap.put("imagePath4",product.getProductImage().getImagePath4());
		} catch (Exception e) {
			productDetailsMap.put("imagePath4","null");
		}
		try {
			productDetailsMap.put("imagePath5",product.getProductImage().getImagePath5());
		} catch (Exception e) {
			productDetailsMap.put("imagePath5","null");
		}
		
		productDetailsMap.put("productId", request.getParameter("productId"));
		return productDetailsMap;
		
	}
	
	@RequestMapping(value="/products/ajax/add",method=RequestMethod.POST)
	public @ResponseBody String imagePath(HttpServletRequest request, Model model,@RequestParam(value="content")MultipartFile obj){
		System.out.println("***************1111111111111111*****************");
		
		File tempFile=null;
		
		try{
			byte[] bytes=obj.getBytes();
			tempFile=File.createTempFile("500x500_", ".jpg",new File(request.getServletContext().getRealPath(request.getServletContext().getInitParameter("supplierProductImages"))));
			//serverFile=new File("/"+tempFile);
			BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(tempFile));
			stream.write(bytes);
			stream.close();
			
			System.out.println("********img size****"+obj.getSize());
		}catch(Exception e){
			System.out.println("********error in uploading*********"+e);
		}
			
		return tempFile.getAbsolutePath();
		
	}
	
	@RequestMapping(value="/product/add/mylist", method=RequestMethod.POST)
	public String addProductsToSellerList(Model model,
			RedirectAttributes redirectAttributes,
				HttpServletRequest request, 
					HttpSession session)
//						@RequestParam("productImage1") MultipartFile multipartProductImages1, 
//							@RequestParam("productImage2") MultipartFile multipartProductImages2,
//								@RequestParam("productImage3") MultipartFile multipartProductImages3,
//									@RequestParam("productImage4") MultipartFile multipartProductImages4,
//											@RequestParam("productImage5") MultipartFile multipartProductImages5,
//												@RequestParam("productImage6") MultipartFile multipartProductImages6,
//													@RequestParam("productImage7") MultipartFile multipartProductImages7,
//														@RequestParam("productImage8") MultipartFile multipartProductImages8)
					{

		String hiddenproductid=request.getParameter("hidden-product-id");
		String categoryId=request.getParameter("categorySelect");
		String subCategoryId=request.getParameter("subcategorySelect");
		String productName=request.getParameter("product-name");
		String brandInput=request.getParameter("brand-input");
		String brandSelected=request.getParameter("brand-select");
		String productDescription=request.getParameter("product-description");
		String tradeType=request.getParameter("trade-type");
		String mrp=request.getParameter("mrp");
		String sellingPrice=request.getParameter("selling-price");
		String productAvailableQty=request.getParameter("product-available-quantity");
		String minimumSaleQty=request.getParameter("minimum-sale-qty");
		String stockStatus=request.getParameter("status-select");
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String stockAvailabilityFrom=request.getParameter("available-from-date");
		String measuredinput=request.getParameter("measurement-value");
		String unitId=request.getParameter("measurement-select");
		String taxId=request.getParameter("tax-select");
		Date stockAvailabilityDate=new Date();
		
		String multipartProductImages1=request.getParameter("productImage1");
		String multipartProductImages2=request.getParameter("productImage2");
		String multipartProductImages3=request.getParameter("productImage3");
		
		AmazonS3 s3 = AmazonService.getAmazonS3();
		
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		//Unit unit=unitService.findOne(Integer.parseInt(unitId));
		///System.out.println("*****************unit name****"+unit.getUnitName());
		System.out.println("**********************category input***"+categoryId);
		System.out.println("**********************brand input***"+brandInput);
		System.out.println("**********************brand brandSelected***"+brandSelected);
		
		try {
			stockAvailabilityDate=df.parse(stockAvailabilityFrom);
		} catch (Exception e) {
			
		}
		Product product=new Product();
		if(hiddenproductid==null || hiddenproductid.equals("null")){
			//---------------------creating if new brand inserted -----------------------
			Brand brand= new Brand();;
			if(brandInput==null||brandInput=="null"||brandInput.equals("null")){
				brand.setBrandId(Integer.parseInt(brandSelected));
				
			}else{
				brand=brandService.findByBrandName(brandInput);
				if(brand==null){
					brand= new Brand();
					brand.setBrandName(brandInput);
					brand=brandService.save(brand);
				}
				
			}
			System.out.println("**********************brandName***"+brand.getBrandName());
			
			//--------------------- creating product description -------------------------
			ProductDescription productDesc=new ProductDescription();
			productDesc.setDescription(productDescription);
			productDesc=productDescriptionService.save(productDesc);
			
			//---------------------------- measurement value ---------------------------
//			MeasuredValue measuredValue=new MeasuredValue();
//			measuredValue.setMeasuredValue(measuredinput);
//			measuredValue=measuredValueService.save(measuredValue);
//			
			//-------------------creating new product -----------------------
			
			Category category=categoryService.findOne(Integer.parseInt(categoryId));
			
			SubCategory subCategory=new SubCategory();
			subCategory.setSubCategoryId(Integer.parseInt(subCategoryId));
			
			product.setSupplier(supplier);
			
			product.setCategory(category);
			product.setPackagedUnit(unitId);
			product.setPackagedQuantity(Float.parseFloat(measuredinput));
			
			product.setSubCategory(subCategory);
			product.setBrand(brand);
			//product.setPackagedUnit(measuredinput);
			product.setPackagedUnit(unitId);
			product.setProductDescription(productDesc);
			product.setProductName(productName);
				//-------------------- creating blank product image Object for getting id in advance -------------
				ProductImage productImage=new ProductImage();
				productImage=productImageService.save(productImage);
			product.setProductImage(productImage);
			System.out.println("*****************controller 1*******************"+product.getProductImage().getProductImageId());
			product=productService.save(product);
			
//			productImage.setImagePath1(multipartProductImages1);
//			productImage.setImagePath2(multipartProductImages2);
//			productImage.setImagePath3(multipartProductImages3);
			//------------------------ image uploading settings --------------------------
			System.out.println("*****************controller 2*******************"+product.getProductImage().getProductImageId());
			
			String bucketName = "krenai.seller";
			String key = category.getCategoryName()+category.getCategoryId()*23.08f+"/"+product.getProductId()+"/";
			String ext = null;
			Boolean uploadResult = false;
			System.out.println("*******************productid from controller"+product.getProductId());
			
			if(multipartProductImages1 != null){
				System.out.println("***********multipartProductImages1 1 image*******"+multipartProductImages1);
				
				ext = FilenameUtils.getExtension(multipartProductImages1);
				System.out.println("***********ext img 1*******"+ext);
				
				
				
				
				
				 if(ext.equals("jpeg")||ext=="jpeg"||ext.equals("png")||ext=="png"||ext.equals("jpg")||ext=="jpg"){
					
					 
				try {
					File newFileJPG = new File(multipartProductImages1);
					 String imageKey = key+product.getProductName()+"_"+product.getProductId()+"_1."+ext;
					 FileInputStream input = new FileInputStream(newFileJPG);
		    	       MultipartFile multi = new MockMultipartFile("file",
		    	         newFileJPG.getName(), "image/jpg", IOUtils.toByteArray(input));
		    	       s3.putObject(new PutObjectRequest(bucketName, key, multipartToFile(multi)));
		    	       System.out.println("***********multi img 1*******"+multi);
		    	       System.out.println("***********s3 img 1*******"+s3);
					 uploadResult = uploadToS3(s3, bucketName, imageKey, multi);
					 
					 System.out.println("***********uploadResult img 1*******"+uploadResult);
					 if(uploadResult)
						 productImage.setImagePath1("https://s3-us-west-2.amazonaws.com/krenai.seller/"+imageKey);
					 
					 input.close();
		        	   if(newFileJPG.isFile()){
		        		   newFileJPG.delete();
	        	       System.out.println("*******file deleted main**********");
	        	      }
	        	      else{
	        	       System.out.println("**********error cannot deleted***********");
	        	      }
				}
				catch(Exception e){
					System.out.println("***********Exception e prod 1 image*******"+e);
				}
				 }
				 else{
					 model.addAttribute("dataErrorMessage", "There are some files which are not with .jpg, .jpeg, .png extension.");
				 }
				
//				 new UploadImage().imageUploadingSettings("product", productImage, productImageService, multipartProductImages1, servletContext, product, 1);
			}
			
			if(multipartProductImages2 != null){
				ext = FilenameUtils.getExtension(multipartProductImages2);
				

				 if(ext.equals("jpeg")||ext=="jpeg"||ext.equals("png")||ext=="png"||ext.equals("jpg")||ext=="jpg"){
					 
					 
				try {
					File newFileJPG2 = new File(multipartProductImages2);
					String imageKey = key+product.getProductName()+"_"+product.getProductId()+"_2."+ext;
					 FileInputStream input = new FileInputStream(newFileJPG2);
		    	       MultipartFile multi = new MockMultipartFile("file",
		    	         newFileJPG2.getName(), "image/jpg", IOUtils.toByteArray(input));
		    	       s3.putObject(new PutObjectRequest(bucketName, key, multipartToFile(multi)));
				  
					 uploadResult = uploadToS3(s3, bucketName, imageKey, multi);
					 if(uploadResult)
						 productImage.setImagePath2("https://s3-us-west-2.amazonaws.com/krenai.seller/"+imageKey);
					
					 input.close();
		        	   if(newFileJPG2.isFile()){
		        		   newFileJPG2.delete();
	        	       System.out.println("*******file deleted main**********");
	        	      }
	        	      else{
	        	       System.out.println("**********error cannot deleted***********");
	        	      }
				}
				catch(Exception e){
					System.out.println("***********Exception e prod 1 image*******"+e);
				}
				 }
				 else{
					 model.addAttribute("dataErrorMessage", "There are some files which are not with .jpg, .jpeg, .png extension.");
				 }
				
//				 new UploadImage().imageUploadingSettings("product", productImage, productImageService, multipartProductImages1, servletContext, product, 1);
			}
			
			if(multipartProductImages3 != null){
				System.out.println("***********multipartProductImages3 1 image3*******"+multipartProductImages3);
				ext = FilenameUtils.getExtension(multipartProductImages3);
				
				
				 if(ext.equals("jpeg")||ext=="jpeg"||ext.equals("png")||ext=="png"||ext.equals("jpg")||ext=="jpg"){
					 
					 
						try {
							File newFileJPG3 = new File(multipartProductImages3);
							String imageKey = key+product.getProductName()+"_"+product.getProductId()+"_3."+ext;
							 FileInputStream input = new FileInputStream(newFileJPG3);
				    	       MultipartFile multi = new MockMultipartFile("file",
				    	         newFileJPG3.getName(), "image/jpg", IOUtils.toByteArray(input));
				    	       s3.putObject(new PutObjectRequest(bucketName, key, multipartToFile(multi)));
						  
							 uploadResult = uploadToS3(s3, bucketName, imageKey, multi);
							 if(uploadResult)
								 productImage.setImagePath3("https://s3-us-west-2.amazonaws.com/krenai.seller/"+imageKey);
							 
							 input.close();
				        	   if(newFileJPG3.isFile()){
				        		   newFileJPG3.delete();
			        	       System.out.println("*******file deleted main**********");
			        	      }
			        	      else{
			        	       System.out.println("**********error cannot deleted***********");
			        	      }
						}
						catch(Exception e){
							System.out.println("***********Exception e prod 1 image*******"+e);
						}
				 }
				 else{
					 model.addAttribute("dataErrorMessage", "There are some files which are not with .jpg, .jpeg, .png extension.");
				 }
				
//				 new UploadImage().imageUploadingSettings("product", productImage, productImageService, multipartProductImages1, servletContext, product, 1);
			}
//			if(!multipartProductImages2.isEmpty()==true){
//				ext = FilenameUtils.getExtension(multipartProductImages2.getOriginalFilename());
//				 if(ext.equals("jpeg")||ext=="jpeg"||ext.equals("png")||ext=="png"||ext.equals("jpg")||ext=="jpg"){
//					 String imageKey = key+product.getProductName()+"_"+product.getProductId()+"_2."+ext;
//					 uploadResult = uploadToS3(s3, bucketName, imageKey, multipartProductImages2);
//					 if(uploadResult)
//						 productImage.setImagePath2("https://s3-us-west-2.amazonaws.com/krenai.seller/"+imageKey);
//				 }
//				 else{
//					 model.addAttribute("dataErrorMessage", "There are some files which are not with .jpg, .jpeg, .png extension.");
//				 }
//				
//				//new UploadImage().imageUploadingSettings("product", productImage, productImageService, multipartProductImages2, servletContext, product, 2);
//			}
//			if(!multipartProductImages3.isEmpty()==true){
//				ext = FilenameUtils.getExtension(multipartProductImages3.getOriginalFilename());
//				 if(ext.equals("jpeg")||ext=="jpeg"||ext.equals("png")||ext=="png"||ext.equals("jpg")||ext=="jpg"){
//					 String imageKey = key+product.getProductName()+"_"+product.getProductId()+"_3."+ext;
//					 uploadResult = uploadToS3(s3, bucketName, imageKey, multipartProductImages3);
//					 if(uploadResult)
//						 productImage.setImagePath3("https://s3-us-west-2.amazonaws.com/krenai.seller/"+imageKey);
//				 }
//				 else{
//					 model.addAttribute("dataErrorMessage", "There are some files which are not with .jpg, .jpeg, .png extension.");
//				 }
//				
//				//new UploadImage().imageUploadingSettings("product", productImage, productImageService, multipartProductImages3, servletContext, product, 3);
//			}
//			if(!multipartProductImages4.isEmpty()==true){
//				ext = FilenameUtils.getExtension(multipartProductImages4.getOriginalFilename());
//				 if(ext.equals("jpeg")||ext=="jpeg"||ext.equals("png")||ext=="png"||ext.equals("jpg")||ext=="jpg"){
//					 String imageKey = key+product.getProductName()+"_"+product.getProductId()+"_4."+ext;
//					 uploadResult = uploadToS3(s3, bucketName, imageKey, multipartProductImages4);
//					 if(uploadResult)
//						 productImage.setImagePath4("https://s3-us-west-2.amazonaws.com/krenai.seller/"+imageKey);
//				 }
//				 else{
//					 model.addAttribute("dataErrorMessage", "There are some files which are not with .jpg, .jpeg, .png extension.");
//				 }
//				
//				//new UploadImage().imageUploadingSettings("product", productImage, productImageService, multipartProductImages4, servletContext, product, 4);
//			}
//			if(!multipartProductImages5.isEmpty()==true){
//				ext = FilenameUtils.getExtension(multipartProductImages5.getOriginalFilename());
//				 if(ext.equals("jpeg")||ext=="jpeg"||ext.equals("png")||ext=="png"||ext.equals("jpg")||ext=="jpg"){
//					 String imageKey =  key+product.getProductName()+"_"+product.getProductId()+"_5."+ext;
//					 uploadResult = uploadToS3(s3, bucketName, imageKey, multipartProductImages5);
//					 if(uploadResult)
//						 productImage.setImagePath5("https://s3-us-west-2.amazonaws.com/krenai.seller/"+imageKey);
//				 }
//				 else{
//					 model.addAttribute("dataErrorMessage", "There are some files which are not with .jpg, .jpeg, .png extension.");
//				 }
//				
//				//new UploadImage().imageUploadingSettings("product", productImage, productImageService, multipartProductImages5, servletContext, product, 5);
//			}
//			if(!multipartProductImages6.isEmpty()==true){
//				ext = FilenameUtils.getExtension(multipartProductImages6.getOriginalFilename());
//				 if(ext.equals("jpeg")||ext=="jpeg"||ext.equals("png")||ext=="png"||ext.equals("jpg")||ext=="jpg"){
//					 String imageKey = key+product.getProductName()+"_"+product.getProductId()+"_6."+ext;
//					 uploadResult = uploadToS3(s3, bucketName, imageKey, multipartProductImages6);
//					 if(uploadResult)
//						 productImage.setImagePath6("https://s3-us-west-2.amazonaws.com/krenai.seller/"+imageKey);
//				 }
//				 else{
//					 model.addAttribute("dataErrorMessage", "There are some files which are not with .jpg, .jpeg, .png extension.");
//				 }
//				
//				//new UploadImage().imageUploadingSettings("product", productImage, productImageService, multipartProductImages6, servletContext, product, 6);
//			}
//			if(!multipartProductImages7.isEmpty()==true){
//				ext = FilenameUtils.getExtension(multipartProductImages7.getOriginalFilename());
//				 if(ext.equals("jpeg")||ext=="jpeg"||ext.equals("png")||ext=="png"||ext.equals("jpg")||ext=="jpg"){
//					 String imageKey = key+product.getProductName()+"_"+product.getProductId()+"_7."+ext;
//					 uploadResult = uploadToS3(s3, bucketName, imageKey, multipartProductImages7);
//					 if(uploadResult)
//						 productImage.setImagePath7("https://s3-us-west-2.amazonaws.com/krenai.seller/"+imageKey);
//				 }
//				 else{
//					 model.addAttribute("dataErrorMessage", "There are some files which are not with .jpg, .jpeg, .png extension.");
//				 }
//				
//				//new UploadImage().imageUploadingSettings("product", productImage, productImageService, multipartProductImages7, servletContext, product, 7);
//			}
//			if(!multipartProductImages8.isEmpty()==true){
//				ext = FilenameUtils.getExtension(multipartProductImages8.getOriginalFilename());
//				 if(ext.equals("jpeg")||ext=="jpeg"||ext.equals("png")||ext=="png"||ext.equals("jpg")||ext=="jpg"){
//					 String imageKey = key+product.getProductName()+"_"+product.getProductId()+"_8."+ext;
//					 uploadResult = uploadToS3(s3, bucketName, imageKey, multipartProductImages8);
//					 if(uploadResult)
//						 productImage.setImagePath8("https://s3-us-west-2.amazonaws.com/krenai.seller/"+imageKey);
//				 }
//				 else{
//					 model.addAttribute("dataErrorMessage", "There are some files which are not with .jpg, .jpeg, .png extension.");
//				 }
//				
//				//new UploadImage().imageUploadingSettings("product", productImage, productImageService, multipartProductImages8, servletContext, product, 8);
//			}
			productImageService.save(productImage);
			
		}
		else{
			product.setProductId(Integer.parseInt(hiddenproductid));
			
			/*product = productService.findOne(Integer.parseInt(hiddenproductid));
			product.setPackagedUnit(product.getPackagedUnit());
			product.setPackagedQuantity(Integer.parseInt(measuredinput));
			try {
				product.setProductId(0);
				product = productService.save(product);
			} catch (Exception e) {
				product.setProductId(Integer.parseInt(hiddenproductid));
			}*/
		}
		//Supplier supplier=new Supplier();
		SellerProductListings sellerProductListings=new SellerProductListings();
		
		Status status= new Status();
		status.setStatusId(Integer.parseInt(stockStatus));
		//supplier.setSupplierId(Integer.parseInt((String)session.getAttribute("loginUserId")));
		Status activeStatus = statusService.findByStatusValue("active");
		
		int availableQty = Integer.parseInt(productAvailableQty);
		
		if(status.getStatusId()==activeStatus.getStatusId()){
			if(availableQty>0){
				sellerProductListings.setStatus(activeStatus);
				sellerProductListings.setAvailableQuantity(availableQty);
				
			}
			else{
				Status inactiveStatus = statusService.findByStatusValue("inactive");
				sellerProductListings.setStatus(inactiveStatus);
				sellerProductListings.setAvailableQuantity(0);
				
			}
		}
		
		else{
			Status inactiveStatus = statusService.findByStatusValue("inactive");
			sellerProductListings.setStatus(inactiveStatus);
			sellerProductListings.setAvailableQuantity(0);
			
		}
		
		Tax tax =new Tax();
		tax.setTaxId(Integer.parseInt(taxId));
		
		try {
			sellerProductListings.setAvailabilityDate(stockAvailabilityDate);
		} catch (Exception e) {
			sellerProductListings.setAvailabilityDate(new Date());
		}
		sellerProductListings.setMinimumSellQuantity(Integer.parseInt(minimumSaleQty));
		sellerProductListings.setMrp(Float.valueOf(mrp));
		sellerProductListings.setProduct(product);
		sellerProductListings.setSellingPrice(Float.valueOf(sellingPrice));
		sellerProductListings.setTradeType(tradeType);
		sellerProductListings.setTax(tax);
		sellerProductListings.setSupplier(supplier);
		try {
			sellerProductListings=sellerProductListingsService.save(sellerProductListings);
			String msg = "Product ("+sellerProductListings.getProduct().getProductName()+" ) is now available in my store @Rs."+sellerProductListings.getSellingPrice();
			if((sellerProductListings.getMrp()-sellerProductListings.getSellingPrice())>1){
				msg += "  and you save Rs."+(sellerProductListings.getMrp()-sellerProductListings.getSellingPrice());
			}
			feedsService.uploadFeed(sellerProductListings, msg, KrenaiCONSTANTS.supplierFeedForFollowers);
			redirectAttributes.addFlashAttribute("successMessage", "Product uploaded successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Product already exists in your list.");
			
		}
		
		return "redirect:/products/add";
	}
	

	@RequestMapping(value="/add/product/tostore", method=RequestMethod.POST)
	public @ResponseBody String addProductsToStore(HttpServletRequest request){
		Product product = new Product();
		String hiddenproductid=request.getParameter("pi");
		product.setProductId(Integer.parseInt(hiddenproductid));
		
		String mrp=request.getParameter("mrp");
		String sellingPrice=request.getParameter("sellingPrice");
		String productAvailableQty=request.getParameter("availableQty");
		String minimumSaleQty=request.getParameter("minimumSellingQty");
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		
		
	//Supplier supplier=new Supplier();
	SellerProductListings sellerProductListings=new SellerProductListings();
	
	//supplier.setSupplierId(Integer.parseInt((String)session.getAttribute("loginUserId")));
	Status activeStatus = statusService.findByStatusValue("active");
	
	int availableQty = Integer.parseInt(productAvailableQty);
	sellerProductListings.setStatus(activeStatus);
	
	Tax tax =new Tax();
	tax.setTaxId(3);
	
	sellerProductListings.setAvailabilityDate(new Date());
	sellerProductListings.setMinimumSellQuantity(Integer.parseInt(minimumSaleQty));
	sellerProductListings.setMrp(Float.valueOf(mrp));
	sellerProductListings.setProduct(product);
	sellerProductListings.setSellingPrice(Float.valueOf(sellingPrice));
	sellerProductListings.setTradeType("supplier");
	sellerProductListings.setTax(tax);
	sellerProductListings.setAvailableQuantity(availableQty);
	sellerProductListings.setSupplier(supplier);
	try {
		sellerProductListings=sellerProductListingsService.save(sellerProductListings);
		String msg = "Product ("+sellerProductListings.getProduct().getProductName()+" ) is now available in my store @Rs."+sellerProductListings.getSellingPrice();
		if((sellerProductListings.getMrp()-sellerProductListings.getSellingPrice())>1){
			msg += "  and you save Rs."+(sellerProductListings.getMrp()-sellerProductListings.getSellingPrice());
		}
		feedsService.uploadFeed(sellerProductListings, msg, KrenaiCONSTANTS.supplierFeedForFollowers);
		return "success";
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "failed";
		
	}
	
	@RequestMapping(value="/product/add/mylist", method=RequestMethod.GET)
	public String addProductsToSellerListViaGet(Model model, HttpServletResponse response){
		return "redirect:/products/add";
	}
	
	@RequestMapping(value="/store/products/listing/{pageNo}", method=RequestMethod.GET)
	public String showMyList(@PathVariable Integer pageNo, Model model,HttpSession session, HttpServletResponse response){
		Supplier supplier= supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		//supplier.setSupplierId(Integer.parseInt((String)session.getAttribute("loginUser")));
//		supplier.setSupplierId(1);
		if(pageNo<1||pageNo==null){
			pageNo = 1;
		}
		//Iterable<SellerProductListings> sellerProductListing=sellerProductListingsService.findBySupplier(supplier);
		Page<SellerProductListings> pageList = sellerProductListingsService.findBySupplier(supplier,pageNo-1);
		int currentPage = pageList.getNumber();
		int begin = Math.max(1, currentPage-5);
		int end = Math.min(begin+10, pageList.getTotalPages());
		System.out.println("***********currentPage**************"+currentPage);
		System.out.println("*************begin************"+begin);
		System.out.println("***************end**********"+end);
		model.addAttribute("begin", begin);
		model.addAttribute("current", pageNo);
		model.addAttribute("end", end);
		model.addAttribute("totalPages", pageList.getTotalPages());
		List<SellerProductListings> sellerProductListing= pageList.getContent();
//		int productId;
//		String productName;
//		String productDescription;
//		float sellingPrice;
//		int availableQty;
		
		List<Map<String,String>> sellerProductListingMapList=new ArrayList<Map<String,String>>();
		Map<String,String> productListMap;
//		ProductSellerListingForDescAndCost productSellerListingForDescAndCost;
		for(SellerProductListings sellerProductListingObj: sellerProductListing){
			 productListMap=new HashMap<String,String>();
//			productId=sellerProductListingObj.getProduct().getProductId();
//			productName=sellerProductListingObj.getProduct().getProductName();
//			productDescription=sellerProductListingObj.getProduct().getProductDescription().getDescription();
//			sellingPrice=sellerProductListingObj.getSellingPrice();
//			availableQty=sellerProductListingObj.getAvailableQuantity();
//			productSellerListingForDescAndCost=new ProductSellerListingForDescAndCost(productId,productName,productDescription,sellingPrice,availableQty);
			productListMap.put("sellerProductId",String.valueOf(sellerProductListingObj.getSellerProductListingId()) );
			productListMap.put("productName", sellerProductListingObj.getProduct().getProductName());
			productListMap.put("brand",sellerProductListingObj.getProduct().getBrand().getBrandName() );
			productListMap.put("minimumSellingQty", String.valueOf(sellerProductListingObj.getMinimumSellQuantity()));
			productListMap.put("mrp",String.valueOf(sellerProductListingObj.getMrp()) );
			System.out.println("**************************productId 1*************"+sellerProductListingObj.getProduct().getProductId());
			try {
				productListMap.put("productDescription",sellerProductListingObj.getProduct().getProductDescription().getDescription() );
			} catch (Exception e) {
				productListMap.put("productDescription","Description of the product not available." );
			}
			productListMap.put("sellingPrice",String.valueOf(sellerProductListingObj.getSellingPrice()) );
			System.out.println("**************************producct listing 2*************"+sellerProductListingObj.getSellerProductListingId());
			productListMap.put("availableQty",String.valueOf(sellerProductListingObj.getAvailableQuantity()) );
			try {
				productListMap.put("imageURL",sellerProductListingObj.getProduct().getProductImage().getImagePath1() );
			} catch (Exception e) {
				productListMap.put("imageURL","");
				System.out.println("***************************image url error*************"+e);
			}
			System.out.println("***************************productListMap1*************"+productListMap.get("productName"));
			sellerProductListingMapList.add(productListMap);
		}
		model.addAttribute("sellerProductListingMapList", sellerProductListingMapList);
		model.addAttribute("underline", "all");
		
		Map<String, String> productListingCountMap = getProductListingCount(supplier);
		session.setAttribute("productListingCount", productListingCountMap);
		
		return "products/product-listing";
	}
	
	@RequestMapping(value="/products/sellerProductDelete", method=RequestMethod.POST)
	public @ResponseBody String addSellerProductDelete(HttpServletRequest request, Model model, HttpServletResponse response){
		String sellerProductId=request.getParameter("sellerProductId");
		try {
			Status status=statusService.findByStatusValue("deleted");
			SellerProductListings sellerProductListings=sellerProductListingsService.findOne(Integer.parseInt(sellerProductId));
			sellerProductListings.setStatus(status);
			sellerProductListingsService.save(sellerProductListings);
		} catch (Exception e) {
			return "failed";
		}
		return "deleted";
	}
	
	@RequestMapping(value="/products/listing/{varStatus}/{pageNo}", method=RequestMethod.GET)
	public String showMyListInStatus(@PathVariable(value="varStatus") String varStatus,
			@PathVariable(value="pageNo") Integer pageNo,
				HttpServletRequest request, 
					Model model,
						HttpServletResponse response){
		//Supplier supplier=new Supplier();
		//supplier.setSupplierId(Integer.parseInt((String)session.getAttribute("loginUser")));
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		Status status=statusService.findByStatusValue(varStatus);
		try {
			 status.getStatusValue();
			 model.addAttribute("underline", status.getStatusValue());
		} catch (Exception e) {
			model.addAttribute("headerFailedSuccess","WRONG PARAMETER REQUESTED!!!");
			model.addAttribute("underline", "active");
			status=statusService.findByStatusValue("active");
			System.out.println("******status(act or inact)*******"+status);
			return "redirect:/store/products/listing/1";
		}
		
		if(pageNo<1){
			pageNo = 1;
		}
		//Iterable<SellerProductListings> sellerProductListing=sellerProductListingsService.findBySupplier(supplier);
		Page<SellerProductListings> pageList = sellerProductListingsService.findBySupplierAndStatus(supplier,status,pageNo-1);
		int currentPage = pageList.getNumber();
		int begin = Math.max(1, currentPage-5);
		int end = Math.min(begin+10, pageList.getTotalPages());
		System.out.println("***********currentPage**************"+currentPage);
		System.out.println("*************begin************"+begin);
		System.out.println("***************end**********"+end);
		model.addAttribute("begin", begin);
		model.addAttribute("current", pageNo);
		model.addAttribute("end", end);
		model.addAttribute("totalPages", pageList.getTotalPages());
		List<SellerProductListings> sellerProductListing= pageList.getContent();
//		int productId;
		
//		Iterable<SellerProductListings> sellerProductListing=sellerProductListingsService.findBySupplierAndStatus(supplier, status);
		System.out.println("**********status :"+status.getStatusValue());
		List<Map<String,String>> sellerProductListingMapList=new ArrayList<Map<String,String>>();
		Map<String,String> productListMap;
		for(SellerProductListings sellerProductListingObj: sellerProductListing){
			 productListMap=new HashMap<String,String>();
			productListMap.put("sellerProductId",String.valueOf(sellerProductListingObj.getSellerProductListingId()) );
			productListMap.put("minimumSellingQty", String.valueOf(sellerProductListingObj.getMinimumSellQuantity()));
			productListMap.put("productName", sellerProductListingObj.getProduct().getProductName());
			System.out.println("**************************productId 1*************"+sellerProductListingObj.getProduct().getProductId());
			
			try {
				productListMap.put("productDescription",sellerProductListingObj.getProduct().getProductDescription().getDescription() );
			} catch (Exception e) {
				productListMap.put("productDescription","Description of the product not available." );
			}
			
			productListMap.put("mrp",String.valueOf(sellerProductListingObj.getMrp()) );
			
			productListMap.put("sellingPrice",String.valueOf(sellerProductListingObj.getSellingPrice()) );
			System.out.println("**************************producct listing 2*************"+sellerProductListingObj.getSellerProductListingId());
			
			productListMap.put("availableQty",String.valueOf(sellerProductListingObj.getAvailableQuantity()) );
			
			try {
				productListMap.put("imageURL",sellerProductListingObj.getProduct().getProductImage().getImagePath1() );
			} catch (Exception e) {
				System.out.println("***************************image url error*************"+e);
			}
			System.out.println("***************************productListMap1*************"+productListMap.get("productName"));
			sellerProductListingMapList.add(productListMap);
		}
		model.addAttribute("sellerProductListingMapList", sellerProductListingMapList);
		
		Map<String, String> productListingCountMap = getProductListingCount(supplier);
		HttpSession session = request.getSession();
		session.setAttribute("productListingCount", productListingCountMap);
		
		
		System.out.println("*******************list size : "+sellerProductListingMapList.size());
		return "products/product-listing";
	}


	@RequestMapping(value="/products/listing/stock/{varStock}", method=RequestMethod.GET)
	public String showMyListInStock(@PathVariable String varStock,HttpServletRequest request, Model model, HttpServletResponse response){
		Supplier supplier=supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		//supplier.setSupplierId(Integer.parseInt((String)session.getAttribute("loginUser")));
		List<Map<String,String>> sellerProductListingMapList=new ArrayList<Map<String,String>>();
//		supplier.setSupplierId(1);
		Iterable<SellerProductListings> sellerProductListing = null;
		if(varStock.equals("lt5")||varStock.equals("gt5")||varStock.equals("es")){
			System.out.println("****************************if cond***************");
			if(varStock.equals("lt5")){
				sellerProductListing=sellerProductListingsService.findBySupplierAndAvailableQuantityBetween(supplier, 1, 5);
				model.addAttribute("underline", "lt5");
				System.out.println("****************************lt5***************");
			}if(varStock.equals("gt5")){
				sellerProductListing=sellerProductListingsService.findBySupplierAndAvailableQuantityGreaterThan(supplier, 5);
				model.addAttribute("underline", "gt5");
				System.out.println("****************************gt5***************");
			}
			if(varStock.equals("es")){
				sellerProductListing=sellerProductListingsService.findBySupplierAndAvailableQuantityLessThan(supplier, 1);
				model.addAttribute("underline", "es");
				System.out.println("****************************es***************"+sellerProductListing);
			}
			Map<String,String> productListMap;
			for(SellerProductListings sellerProductListingObj: sellerProductListing){
				System.out.println("****************************IN LOOP***************"+sellerProductListingObj.getAvailableQuantity());
				 productListMap=new HashMap<String,String>();
				productListMap.put("sellerProductId",String.valueOf(sellerProductListingObj.getSellerProductListingId()) );
				productListMap.put("productName", sellerProductListingObj.getProduct().getProductName());
				productListMap.put("minimumSellingQty", String.valueOf(sellerProductListingObj.getMinimumSellQuantity()));
				System.out.println("**************************productId 1*************"+sellerProductListingObj.getProduct().getProductId());
				try {
					productListMap.put("productDescription",sellerProductListingObj.getProduct().getProductDescription().getDescription() );
				} catch (Exception e) {
					productListMap.put("productDescription","Description of the product not available." );
				}
				productListMap.put("sellingPrice",String.valueOf(sellerProductListingObj.getSellingPrice()) );
				productListMap.put("mrp",String.valueOf(sellerProductListingObj.getMrp()) );
				System.out.println("**************************producct listing 2*************"+sellerProductListingObj.getSellerProductListingId());
				productListMap.put("availableQty",String.valueOf(sellerProductListingObj.getAvailableQuantity()) );
				try {
					productListMap.put("imageURL",sellerProductListingObj.getProduct().getProductImage().getImagePath1() );
				} catch (Exception e) {
					System.out.println("***************************image url error*************"+e);
				}
				System.out.println("***************************productListMap1*************"+productListMap.get("productName"));
				sellerProductListingMapList.add(productListMap);
			}
		}
		else{
//			char[] chArray=varStock.toCharArray();
//			if(chArray[0]=='>'|| chArray[0]=='<'||chArray[0]=='='||chArray[0]=='+'||chArray[0]=='-'){
//				char[] chArray2 = Arrays.copyOfRange(chArray, 1, chArray.length);
//				try {
//					int limit=Integer.parseInt(chArray2.toString());
//					if(chArray[0]=='>'||chArray[0]=='+'){
//						sellerProductListing=sellerProductListingsService.findBySupplierAndAvailableQuantityGreaterThan(supplier, limit);
//					}
//					if(chArray[0]=='<'||chArray[0]=='-'){
//						sellerProductListing=sellerProductListingsService.findBySupplierAndAvailableQuantityLessThan(supplier, limit);
//					}
//					if(chArray[0]=='='){
//						sellerProductListing=sellerProductListingsService.findBySupplierAndAvailableQuantityLessThanAndAvailableQuantityGreaterThan(supplier, limit+1, limit-1);
//					}
//					
//					
//				} catch (Exception e) {
//					model.addAttribute("headerFailedSuccess","WRONG PARAMETER REQUESTED!!!");
//				}
//				
//			}else{
				model.addAttribute("headerFailedSuccess","WRONG PARAMETER REQUESTED!!!");
//			}
			
		}

		Map<String, String> productListingCountMap = getProductListingCount(supplier);
		HttpSession session = request.getSession();
		session.setAttribute("productListingCount", productListingCountMap);
		
		
		
		model.addAttribute("sellerProductListingMapList", sellerProductListingMapList);
		return "products/product-listing";
	}

	
	@RequestMapping(value="/product/addcategory", method=RequestMethod.GET)
	public String addCategoryJsp(Model model, HttpServletResponse response){
		return "products/request-category";
	}
	
	
	
	@RequestMapping(value="/product/addcategory/requested", method=RequestMethod.POST)
	public String addCategoryRequest(RedirectAttributes redirectAttributes, Model model,HttpServletRequest request, HttpServletResponse response){
		String categoryName=request.getParameter("category-name");
		String productDesc=request.getParameter("product-description");
		String tradeType=request.getParameter("trade-type");
		String availability=request.getParameter("availability");
		String isService=request.getParameter("isService");
		
		//System.out.println("******************isService****************"+isService );
//		System.out.println("***************stockStatus*******************"+productDesc );
//		System.out.println("******************stockAvailabilityFrom****************"+tradeType );
//		System.out.println("****************measurementSelect************************"+ multipartProductImages);
//		System.out.println("*********************measurementInput*******************"+availability );
		
		Supplier supplier=supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		
		CategoryDescription categoryDescription=new CategoryDescription();
		categoryDescription.setDescription(productDesc);
		categoryDescription=categoryDescriptionService.save(categoryDescription);
		
		Category category=new Category();
		category.setCategoryName(categoryName);
		Status status=statusService.findByStatusValue("pending");
		category.setStatus(status);
		category.setCurrentStatus(status);
		category.setRequestCount(1);
		category.setSupplier(supplier);
		category.setCategoryDescription(categoryDescription);
		if(availability.equals("yes")){
			category.setAvailability(true);
		}else{
			category.setAvailability(false);
		}
		if(isService.equals("service"))
			category.setService(true);
		else
			category.setService(false);
		
		try {
			category=categoryService.save(category);
			redirectAttributes.addFlashAttribute("successMessage", "Request sent successfully");
			
		} catch (Exception e) {
			System.out.println("error in category upgate"+e);
			redirectAttributes.addFlashAttribute("errorMessage", category.getCategoryName()+" is already exists.");
			
		}
		return "redirect:/product/addcategory";
	}

	@RequestMapping(value="/product/addsubcategory", method=RequestMethod.GET)
	public String addSubCategoryJsp(Model model, HttpServletResponse response){
		HashMap<Integer, String> categoryMap=new HashMap<Integer, String>();
		Status status = statusService.findByStatusValue("active");
		Iterable<Category> categoryIterable= categoryService.findByStatus(status);
		int id;
		String value;
		for(Category category:categoryIterable){
			id=category.getCategoryId();
			value=category.getCategoryName();
			categoryMap.put(id, value);
			System.out.println("*************map data*********************"+id+" / "+value);
		}
		model.addAttribute("categoryMap", categoryMap);
		return "products/request-subcategory";
	}
	
	@RequestMapping(value="/product/addsubcategory/requested", method=RequestMethod.POST)
	public String addSubCategoryRequest(RedirectAttributes redirectAttributes, Model model,HttpServletRequest request, HttpServletResponse response){
		String categoryId=request.getParameter("categorySelect");
		String subcategoryName=request.getParameter("subcategory-name");
		String productDesc=request.getParameter("product-description");
		String[] availability=request.getParameterValues("availability");
		
		/*System.out.println("******************categoryId****************"+categoryId );
		System.out.println("***************productDesc*******************"+productDesc );
		System.out.println("******************subcategoryName****************"+subcategoryName );
		System.out.println("****************productDesc************************"+ productDesc);
		System.out.println("*********************availability*******************"+availability );*/
		
		Supplier supplier=supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		
		SubCategoryDescription subcategoryDescription=new SubCategoryDescription();
		subcategoryDescription.setDescription(productDesc);
		subcategoryDescription=subcategoryDescriptionService.save(subcategoryDescription);
		
		Status status=statusService.findByStatusValue("pending");
		
		Category category=new Category();
		category.setCategoryId(Integer.parseInt(categoryId));
		
		SubCategory subcategory=new SubCategory();
		subcategory.setSubCategoryName(subcategoryName);
		subcategory.setStatus(status);
		subcategory.setCurrentStatus(status);
		subcategory.setRequestCount(1);
		subcategory.setSupplier(supplier);
		subcategory.setCategory(category);
		subcategory.setSubCategoryDescription(subcategoryDescription);
		if(availability==null){
				subcategory.setAvailability(false);
			}
		else{
				subcategory.setAvailability(true);
			}
		
		System.out.println("******************************before sub save**************************");
		try {
			subcategory=subCategoryService.save(subcategory);
			redirectAttributes.addFlashAttribute("successMessage", "Request sent successfully");
			
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", subcategory.getSubCategoryName()+" is already exists.");
			
		}
		
		return "redirect:/product/addsubcategory";
	}
	
	@RequestMapping(value="/product/track", method=RequestMethod.GET)
	public String trackRequest(Model model, HttpSession session, HttpServletResponse response){
		Supplier supplier=supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		List<String> statusValueList=new ArrayList<String>();
		statusValueList.add("pending");
		statusValueList.add("disapproved");
		statusValueList.add("rejected");
		statusValueList.add("approved");
		List <Status> statusList=statusService.findByStatusValueIn(statusValueList);
		System.out.println("**************************11****************");
		for(Status status:statusList){
			System.out.println("**************************aa****************"+status.getStatusValue());
		}
		Iterable<Category> categoryIterable=categoryService.findBySupplierAndStatusIn(supplier, statusList);
		List<Map<String, String>> categoryMapList=new ArrayList<Map<String, String>>();
		List<Map<String, String>> subCategoryMapList=new ArrayList<Map<String, String>>();
		Map<String, String> tempMap;
		for(Category category:categoryIterable){
			tempMap=new HashMap<String, String>();
			tempMap.put("name", category.getCategoryName());
			System.out.println("***********************status id***"+category.getStatus().getStatusId());
			System.out.println("***********************category id***"+category.getCategoryId());
			tempMap.put("status", category.getCurrentStatus().getStatusValue());
			tempMap.put("reason", category.getReason());
			tempMap.put("imageUrl", category.getImageUrl());
			long timeDifference=(new Date().getTime()-category.getDateAdded().getTime())/(1000*60*60*60);
			if(timeDifference>72){
				tempMap.put("resendActivity","yes" );	
			}else{
				tempMap.put("resendActivity","no" );
				
			}
			tempMap.put("id", String.valueOf(category.getCategoryId()));
			categoryMapList.add(tempMap);
		}
		Iterable<SubCategory> subCategoryIterable=subCategoryService.findBySupplierAndStatusIn(supplier, statusList);
		for(SubCategory subcategory:subCategoryIterable){
			tempMap=new HashMap<String, String>();
			tempMap.put("name", subcategory.getSubCategoryName());
			tempMap.put("status", subcategory.getCurrentStatus().getStatusValue());
			tempMap.put("reason", subcategory.getReason());
			tempMap.put("id", String.valueOf(subcategory.getSubCategoryId()));
			subCategoryMapList.add(tempMap);
		}
		model.addAttribute("subCategoryMapList", subCategoryMapList);
		model.addAttribute("categoryMapList", categoryMapList);
		return "products/track-request";
	}
	
	
	@RequestMapping(value="/products/getCategoryDetailsToEdit", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> getCategoryDetailsToEdit(Model model, HttpServletRequest request){
		
		String module=request.getParameter("module");
		int id=Integer.parseInt(request.getParameter("id"));
		Map<String,String> resultMap=new HashMap<String,String>();
		if(module.equals("category")){
			Category category=categoryService.findOne(id);
			resultMap.put("name", category.getCategoryName());
			resultMap.put("id", String.valueOf(category.getCategoryId()));
			resultMap.put("description", category.getCategoryDescription().getDescription());
			resultMap.put("module", "category");
			System.out.println("************************************if category***************************");
			
		}else{
			SubCategory subcategory=subCategoryService.findOne(id);
			resultMap.put("name", subcategory.getSubCategoryName());
			resultMap.put("id", String.valueOf(subcategory.getSubCategoryId()));
			resultMap.put("description", subcategory.getSubCategoryDescription().getDescription());
			resultMap.put("module", "subcategory");
			System.out.println("************************************if subcategory***************************");
		}
			
		
		return resultMap;
		
	}
	
	@RequestMapping(value="/products/category/edit", method=RequestMethod.POST)
	public String categoryEdit(Model model, HttpServletRequest request){
		System.out.println("****************"+request.getParameter("name"));
		System.out.println("****************"+request.getParameter("hiddenId"));
		System.out.println("****************"+request.getParameter("module"));
		if(request.getParameter("hiddenModule").equals("category")){
			Category category=categoryService.findOne(Integer.parseInt(request.getParameter("hiddenId")));
			category.setCategoryName(request.getParameter("name"));
			CategoryDescription categoryDescription=category.getCategoryDescription();
			categoryDescription.setDescription(request.getParameter("description"));
			category.setCategoryDescription(categoryDescription);
			categoryService.save(category);
		}
		else{
			SubCategory subcategory=subCategoryService.findOne(Integer.parseInt(request.getParameter("hiddenId")));
			subcategory.setSubCategoryName(request.getParameter("name"));
			SubCategoryDescription subcategoryDescription=subcategory.getSubCategoryDescription();
			subcategoryDescription.setDescription(request.getParameter("description"));
			subcategory.setSubCategoryDescription(subcategoryDescription);
			subCategoryService.save(subcategory);
		}
		return "redirect:/product/track";
	}
	
	@RequestMapping(value="/products/setRequestCount", method=RequestMethod.POST)
	public @ResponseBody String setRequestCount(Model model, HttpServletRequest request){
		if(request.getParameter("module").equals("category")){
			Category category=categoryService.findOne(Integer.parseInt(request.getParameter("id")));
			category.setRequestCount(category.getRequestCount()+1);
			categoryService.save(category);
		}else{
			SubCategory subcategory=subCategoryService.findOne(Integer.parseInt(request.getParameter("id")));
			subcategory.setRequestCount(subcategory.getRequestCount()+1);
			subCategoryService.save(subcategory);
		}
		return "success";
	}
	
	
	

	@RequestMapping(value="/product/list/modify", method=RequestMethod.POST)
	public String productListModify(Model model, HttpServletRequest request){
		int productListId = Integer.parseInt(request.getParameter("hidSellerProductListingId"));
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		SellerProductListings sellerProductListing =  sellerProductListingsService
				.findBySupplierAndSellerProductListingId(supplier, productListId);
		/*
		 * adding feed on price modication specially decreasing price.
		 */
		if(sellerProductListing.getSellingPrice()>Float.parseFloat(request.getParameter("sellingPrice"))){
			String msg = sellerProductListing.getProduct().getProductName()+" is now available @Rs."+request.getParameter("sellingPrice")+
			"  and you save Rs "+(sellerProductListing.getSellingPrice()-Float.parseFloat(request.getParameter("sellingPrice")));
			
			feedsService.uploadFeed(sellerProductListing, msg, KrenaiCONSTANTS.feedforPublic);
		}
		
		sellerProductListing.setAvailableQuantity(Integer.parseInt(request.getParameter("availableQty")));
		sellerProductListing.setSellingPrice(Float.parseFloat(request.getParameter("sellingPrice")));
		sellerProductListing.setMrp(Float.parseFloat(request.getParameter("mrp")));
		sellerProductListing.setMinimumSellQuantity(Integer.parseInt(request.getParameter("minimumSellingQty")));
		sellerProductListingsService.save(sellerProductListing);
		String referer = request.getHeader("Referer");
		return "redirect:"+referer;
	}

	@RequestMapping(value="/category/reqested/pending/delete", method=RequestMethod.POST)
	public @ResponseBody String deleteCategoryRequested(HttpServletRequest request){
		Category category = categoryService.findOne(Integer.parseInt(request.getParameter("_ri")));
		Status status = statusService.findByStatusValue("deletedBySupplier");
		try {
			category.setCurrentStatus(status);
			category.setStatus(status);
			categoryService.save(category);
			System.out.println("*********************deleted by supplier***"+category.getCategoryId());
			return "success";
		} catch (Exception e) {
			return "failed";
		}
	}

	@RequestMapping(value="/subcategory/reqested/pending/delete", method=RequestMethod.POST)
	public @ResponseBody String deleteSubCategoryRequested(HttpServletRequest request){
		SubCategory subCategory = subCategoryService.findOne(Integer.parseInt(request.getParameter("_ri")));
		Status status = statusService.findByStatusValue("deletedBySupplier");
		try {
			subCategory.setCurrentStatus(status);
			subCategory.setStatus(status);
			subCategoryService.save(subCategory);
			System.out.println("*********************deleted by supplier***"+subCategory.getSubCategoryId());
			return "success";
		} catch (Exception e) {
			return "failed";
		}
	}

	@RequestMapping(value="/products/sellerProductChangeStatus", method=RequestMethod.POST)
	public @ResponseBody String changeSupplierProductStatus(HttpServletRequest request){
		SellerProductListings product = sellerProductListingsService.findOne(Integer.parseInt(request.getParameter("_spli")));
		if(product.getSupplier().getEmailId().equals((String)SecurityUtils.getSubject().getPrincipal())){
			if(product.getStatus().getStatusValue().equals("active")){
				Status status =statusService.findByStatusValue("inactive");
				System.out.println("*************status inactive*********"+status);
				product.setStatus(status);
				sellerProductListingsService.save(product);
				return "success";
			}
			else if(product.getStatus().getStatusValue().equals("inactive")&&product.getAvailableQuantity()>0){
				Status status = statusService.findByStatusValue("active");
				System.out.println("*************status active*********"+status);
				product.setStatus(status);
				System.out.println("*************product save*********"+product);
				sellerProductListingsService.save(product);
				return "success";
			}
			else if(product.getStatus().getStatusValue().equals("inactive")&&product.getAvailableQuantity()==0){
				Status status = statusService.findByStatusValue("outOfStock");
				product.setStatus(status);
				sellerProductListingsService.save(product);
				return "Product moved to out of stock because 0 quantity is available.";
			}
			
		}
		
		return "error";
	}
	
}
