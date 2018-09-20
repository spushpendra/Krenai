package com.bugfree.controller.profile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.bugfree.common.amazon.AmazonService;
import com.bugfree.common.validate.ValidateImage;
import com.bugfree.model.address.Address;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.SupplierActivityMonitor;
import com.bugfree.model.supplier.bank.BankDetails;
import com.bugfree.model.supplier.documents.SupplierAddressProofDetails;
import com.bugfree.model.supplier.documents.SupplierPancardDetails;
import com.bugfree.model.supplier.documents.SupplierTINOrCSTDetails;
import com.bugfree.model.supplier.shop.SupplierShop;
import com.bugfree.service.address.AddressService;
import com.bugfree.service.status.StatusService;
import com.bugfree.service.supplier.SupplierActivityMonitorService;
import com.bugfree.service.supplier.SupplierService;
import com.bugfree.service.supplier.bank.BankDetailsService;
import com.bugfree.service.supplier.documents.SupplierAddressProofDetailsService;
import com.bugfree.service.supplier.documents.SupplierPancardDetailsService;
import com.bugfree.service.supplier.documents.SupplierTINOrCSTDetailsService;
import com.bugfree.service.supplier.shop.SupplierShopService;


@Controller
public class Profile {
	
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private SupplierShopService supplierShopService;
	@Autowired
	private SupplierActivityMonitorService supplierActivityMonitorService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private BankDetailsService bankDetailsService;
	@Autowired
	private SupplierPancardDetailsService supplierPancardDetailsService;
	@Autowired
	private 
	SupplierTINOrCSTDetailsService supplierTINOrCSTDetailsService;
	@Autowired
	private SupplierAddressProofDetailsService supplierAddressProofDetailsService;
	
	
	private File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	        File convFile = new File( multipart.getOriginalFilename());
	        multipart.transferTo(convFile);
	        return convFile;
	}
	
	@RequestMapping(value="/profile/update", method=RequestMethod.GET)
	public String profileUpdate(Model model, HttpServletResponse response){
		System.out.println("******************profile update");
		String userEmail = SecurityUtils.getSubject().getPrincipal().toString();
		Supplier supplier= supplierService.findByEmailId(userEmail);
		model.addAttribute("supplier", supplier);
		
		return "profile/update-profile";
	}
	@RequestMapping(value="/profile/update", method=RequestMethod.POST)
	public String profileUpdatePOST(HttpServletRequest request, Model model, HttpServletResponse response){
		System.out.println("******************profile update post");
		String userEmail = SecurityUtils.getSubject().getPrincipal().toString();
		Supplier supplier= supplierService.findByEmailId(userEmail);
		
		SupplierActivityMonitor supplierActivityMonitor = new SupplierActivityMonitor();
		supplierActivityMonitor.setSupplier(supplier);
		supplierActivityMonitor.setDate(new Date());
		
		Supplier newSupplier = supplier;
		
		String supplierName=request.getParameter("fullname");
		String email=request.getParameter("emailId");
		String contact=request.getParameter("contactNo");
		String shopName=request.getParameter("shopName");
		String shopContact=request.getParameter("shopContactNo");
		String streetAddress=request.getParameter("addressLine1")+" "+request.getParameter("addressLine2")+", "+request.getParameter("city");
		String [] workingDays=request.getParameterValues("workingdays");
		String openingtime=request.getParameter("openingtime");
		String closingtime=request.getParameter("closingtime");
		System.out.println("********0ld open time"+supplier.getSupplierShop().getShopOpeningTime());
		System.out.println("********0ld close time"+supplier.getSupplierShop().getShopClosingTime());
		System.out.println("********0ld getShopContactNo "+supplier.getSupplierShop().getShopContactNo());
		System.out.println("********0ld getShopName "+supplier.getSupplierShop().getShopName());
		SupplierShop supplierShop = supplier.getSupplierShop();
		Address address = supplier.getAddress();
		
		Date openTime = new Date();
		Date closeTime = new Date();
		try{
			String[] close = closingtime.replaceAll(" PM", "").replaceAll("PM", "").replaceAll(" AM", "").replaceAll("AM", "").split(":");
			System.out.println("==========="+close[0]+"======="+close[1]);
			String[] open = openingtime.replaceAll(" PM", "").replaceAll("PM", "").replaceAll(" AM", "").replaceAll("AM", "").split(":");
			openTime.setHours(Integer.parseInt(open[0]));
			openTime.setMinutes(Integer.parseInt(open[1]));
			closeTime.setHours(Integer.parseInt(close[0])+12);
			closeTime.setMinutes(Integer.parseInt(close[1]));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		supplierShop.setShopClosingTime(closeTime);
		supplierShop.setShopOpeningTime(openTime);
		
		
		if(!supplier.getFullName().equals(supplierName)){
			newSupplier.setFullName(supplierName);
			supplierActivityMonitor.setActivityChangedOrRequested("fullname");
			System.out.println("*************oldname********"+supplier.getFullName());
			supplierActivityMonitor.setOldValue(supplier.getFullName());
			supplierActivityMonitor.setNewValue(supplierName);
			supplierActivityMonitor.setIpAddress(request.getRemoteAddr());
			supplierActivityMonitorService.save(supplierActivityMonitor);
		}
		if(!supplier.getEmailId().equals(email)){
			newSupplier.setEmailId(email);
			supplierActivityMonitor.setActivityChangedOrRequested("email");
			supplierActivityMonitor.setOldValue(supplier.getEmailId());
			supplierActivityMonitor.setNewValue(email);
			supplierActivityMonitor.setIpAddress(request.getRemoteAddr());
			supplierActivityMonitorService.save(supplierActivityMonitor);
		}
		if(!(supplier.getContactNo().equals(contact))){
			newSupplier.setContactNo(contact);
			supplierActivityMonitor.setActivityChangedOrRequested("contactNo");
			supplierActivityMonitor.setOldValue(String.valueOf(supplier.getContactNo()));
			supplierActivityMonitor.setNewValue(contact);
			supplierActivityMonitor.setIpAddress(request.getRemoteAddr());
			supplierActivityMonitorService.save(supplierActivityMonitor);
		}
		if(!supplier.getSupplierShop().getShopName().equals(shopName)){
			supplierShop.setShopName(shopName);
			supplierActivityMonitor.setActivityChangedOrRequested("shopName");
			supplierActivityMonitor.setOldValue(supplier.getSupplierShop().getShopName());
			supplierActivityMonitor.setNewValue(shopName);
			supplierActivityMonitor.setIpAddress(request.getRemoteAddr());
			supplierActivityMonitorService.save(supplierActivityMonitor);
		}
		if(!supplier.getSupplierShop().getShopContactNo().equals(shopContact)){
			supplierShop.setShopContactNo(shopContact);
			supplierActivityMonitor.setActivityChangedOrRequested("shopContact");
			supplierActivityMonitor.setOldValue(supplier.getSupplierShop().getShopContactNo());
			supplierActivityMonitor.setNewValue(shopContact);
			supplierActivityMonitor.setIpAddress(request.getRemoteAddr());
			supplierActivityMonitorService.save(supplierActivityMonitor);
		}
		
		/*if(!supplier.getSupplierShop().getShopOpeningTime().equals(openingtime)){
			supplierShop.setShopOpeningTime(openingtime);
			supplierActivityMonitor.setActivityChangedOrRequested("openingtime");
			supplierActivityMonitor.setOldValue(supplier.getSupplierShop().getShopOpeningTime());
			supplierActivityMonitor.setNewValue(openingtime);
			supplierActivityMonitor.setIpAddress(request.getRemoteAddr());
			supplierActivityMonitorService.save(supplierActivityMonitor);
		}
		if(!supplier.getSupplierShop().getShopClosingTime().equals(closingtime)){
			supplierShop.setShopClosingTime(closingtime);
			supplierActivityMonitor.setActivityChangedOrRequested("closingtime");
			supplierActivityMonitor.setOldValue(supplier.getSupplierShop().getShopClosingTime());
			supplierActivityMonitor.setNewValue(closingtime);
			supplierActivityMonitor.setIpAddress(request.getRemoteAddr());
			supplierActivityMonitorService.save(supplierActivityMonitor);
		}*/
		try{
			String oldAddress = supplier.getAddress().getAddressLine1()+" "+supplier.getAddress().getAddressLine2()+", "+supplier.getAddress().getCity();
			if(!oldAddress.equals(streetAddress)){
				address.setAddressLine1(request.getParameter("addressLine1"));
				address.setAddressLine2(request.getParameter("addressLine2"));
				address.setCity(request.getParameter("city"));
				supplierActivityMonitor.setActivityChangedOrRequested("address");
				supplierActivityMonitor.setOldValue(oldAddress);
				supplierActivityMonitor.setNewValue(streetAddress);
				supplierActivityMonitor.setIpAddress(request.getRemoteAddr());
				supplierActivityMonitorService.save(supplierActivityMonitor);
			}
		}catch(Exception e){
			
		}
		System.out.println("days array"+workingDays.length);
		supplierShop.setSunday(false);
		supplierShop.setMonday(false);
		supplierShop.setTuesday(false);
		supplierShop.setWednesday(false);
		supplierShop.setThursday(false);
		supplierShop.setFriday(false);
		supplierShop.setSaturday(false);
		for(String day:workingDays){
			if (day.equals("sunday")){
				supplierShop.setSunday(true);
			}
			if (day.equals("monday")){
				supplierShop.setMonday(true);
			}
			if (day.equals("tuesday")){
				supplierShop.setTuesday(true);
			}
			if (day.equals("wednesday")){
				supplierShop.setWednesday(true);
			}
			if (day.equals("thursday")){
				supplierShop.setThursday(true);
			}
			if (day.equals("friday")){
				supplierShop.setFriday(true);
			}
			if (day.equals("saturday")){
				supplierShop.setSaturday(true);
			}
		}
		newSupplier.setAddress(address);
		addressService.save(address);
		supplierShopService.save(supplierShop);
		newSupplier.setSupplierShop(supplierShop);
		supplierService.save(newSupplier);
		
		
		
		return "redirect:"+request.getHeader("Referer");
	}

	@RequestMapping(value="/address/update", method=RequestMethod.GET)
	public String addressUpdate(Model model, HttpServletResponse response){
		System.out.println("******************addresss update");
		
		return "profile/update-address";
	}
	@RequestMapping(value="/bank/details", method=RequestMethod.GET)
	public String bankDetails( Model model, HttpServletResponse response){
		System.out.println("******************bank details");
		String userEmail = SecurityUtils.getSubject().getPrincipal().toString();
		Supplier supplier= supplierService.findByEmailId(userEmail);
		try {
			BankDetails bankDetails = bankDetailsService.findBySupplier(supplier);
			model.addAttribute("bankDetails", bankDetails);
		} catch (Exception e) {

		}
		return "profile/update-bank-details";
	}
	@RequestMapping(value="/bank/details/update", method=RequestMethod.POST)
	public String bankDetailsInsert(RedirectAttributes redirectAttributes, @ModelAttribute("bank-details") BankDetails bankDetails, Model model, HttpServletResponse response){
		System.out.println("******************bank details"+bankDetails.getBranchName());
		Status status = statusService.findByStatusValue("not verified");
		String userEmail = SecurityUtils.getSubject().getPrincipal().toString();
		Supplier supplier= supplierService.findByEmailId(userEmail);
		bankDetails.setStatus(status);
		bankDetails.setSupplier(supplier);
		bankDetailsService.save(bankDetails);
		redirectAttributes.addFlashAttribute("successMessage","Bank Details Uploaded Successfully.");
		return "redirect:/dashboard";
	}
	@RequestMapping(value="/registration/documents", method=RequestMethod.GET)
	public String documentUpdate( Model model, HttpServletResponse response){
		System.out.println("******************bank details");
		String userEmail = SecurityUtils.getSubject().getPrincipal().toString();
		Supplier supplier= supplierService.findByEmailId(userEmail);
		
		SupplierPancardDetails supplierPancardDetails = supplierPancardDetailsService.findBySupplier(supplier);
		model.addAttribute("supplierPancardDetails", supplierPancardDetails);
		
		SupplierTINOrCSTDetails supplierTINOrCSTDetails = supplierTINOrCSTDetailsService.findBySupplier(supplier);
		model.addAttribute("supplierTINOrCSTDetails", supplierTINOrCSTDetails);
		
		SupplierAddressProofDetails supplierAddressProofDetails = supplierAddressProofDetailsService.findBySupplier(supplier);
		model.addAttribute("supplierAddressProofDetails", supplierAddressProofDetails);
		
		return "profile/update-documents";
	}
	@RequestMapping(value="/registration/document/upload/{documentType}", method=RequestMethod.POST)
	public String uploadDocuments(@PathVariable("documentType") String documentType,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request, 
			Model model, 
			@RequestParam("document_image") MultipartFile multipartDocumentImage){
		
		System.out.println("******************profile update");
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		String bucketName = "krenai.seller";
		 String key = "profile/"+String.valueOf(supplier.getSupplierId()*23.08f)+"/documents/";
		 String ext = FilenameUtils.getExtension(multipartDocumentImage.getOriginalFilename());
//		 Boolean uploadResult = false;
		 AmazonS3 s3 = AmazonService.getAmazonS3();
	     
//		ServletContext servletContext = request.getServletContext();
//		RawImageData rawImageData=null;

		if(documentType.equals("pancard")){
			 key = key+"pancard."+ext;
			 //rawImageData = new RawImageData(supplier.getSupplierId(), documentType, documentType, "uploadDocumentPath","showDocumentPathPan", servletContext);
		}
		else if(documentType.equals("tin")){
			key = key+"tin."+ext;
			 //rawImageData = new RawImageData(supplier.getSupplierId(), documentType, documentType, "uploadDocumentPath","showDocumentPathTin", servletContext);
		}
		else if(documentType.equals("address")){
			key = key+"address."+ext;
			//rawImageData = new RawImageData(supplier.getSupplierId(), documentType, documentType, "uploadDocumentPath","showDocumentPathAddress", servletContext);
		}
		
		try {
            
            System.out.println("Uploading a new object to S3 from a file\n");
           try{
        	   s3.putObject(new PutObjectRequest(bucketName, key, multipartToFile(multipartDocumentImage)));
        	   supplier.setImagePath("https://s3-us-west-2.amazonaws.com/krenai.seller/"+key);
   				model.addAttribute("successMessage", "Your profile picture is uploaded successfully");
				
           }catch(Exception e){
        	   redirectAttributes.addFlashAttribute("failedMessage", "Unable to upload file in selected format. Please try to upload file with .jpg or .png extension");
				 System.out.println("***************************8exception in converting image ***"+e);
				 return "redirect:/registration/documents";
        	   
           }
           
            
        } catch (AmazonServiceException ase) {
        	redirectAttributes.addFlashAttribute("failedMessage", "Unable to process selected file this time. Please try after some time.");
			 System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            return "redirect:/registration/documents";
	           
        } catch (AmazonClientException ace) {
        	redirectAttributes.addFlashAttribute("failedMessage", "Unable to process selected file this time. Please try after some time.");
			  System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            return "redirect:/registration/documents";
	           
        }
		
		//String imagePath = new UploadImage().imageUploadingSettingsCommon(rawImageData, multipartDocumentImage);
		
		Status status = statusService.findByStatusValue("not verified");
		
		if(documentType.equals("pancard")){
			SupplierPancardDetails supplierPancardDetails = new SupplierPancardDetails();
			supplierPancardDetails.setSupplier(supplier);
			supplierPancardDetails.setDocumentPath("https://s3-us-west-2.amazonaws.com/krenai.seller/"+key);
			supplierPancardDetails.setStatus(status);
			supplierPancardDetails.setPancardNo(supplier.getSupplierId()+" fake number");
			supplierPancardDetailsService.save(supplierPancardDetails);
			
		}
		else if(documentType.equals("tin")){
			SupplierTINOrCSTDetails supplierTINOrCSTDetails = new SupplierTINOrCSTDetails();
			supplierTINOrCSTDetails.setSupplier(supplier);
			supplierTINOrCSTDetails.setDocumentPath("https://s3-us-west-2.amazonaws.com/krenai.seller/"+key);
			supplierTINOrCSTDetails.setStatus(status);
			supplierTINOrCSTDetails.setTinOrCstDocumentNo(supplier.getSupplierId()+" fake number");
			supplierTINOrCSTDetailsService.save(supplierTINOrCSTDetails);
		}
		else if(documentType.equals("address")){
			SupplierAddressProofDetails supplierAddressProofDetails =new SupplierAddressProofDetails();
			supplierAddressProofDetails.setSupplier(supplier);
			supplierAddressProofDetails.setDocumentPath("https://s3-us-west-2.amazonaws.com/krenai.seller/"+key);
			supplierAddressProofDetails.setStatus(status);
			supplierAddressProofDetails.setAddressProofDocumentId(supplier.getSupplierId()+" fake number");
			supplierAddressProofDetailsService.save(supplierAddressProofDetails);
		}
		System.out.println("**********upload document to path ***"+"https://s3-us-west-2.amazonaws.com/krenai.seller/"+key);
		redirectAttributes.addFlashAttribute("successMessage", "Document Uploaded Successfully..");
		return "redirect:/registration/documents";
	}
	
	@RequestMapping(value="/profile/theme", method=RequestMethod.GET)
	public String themeUpdatePage( HttpServletResponse response, HttpServletRequest request){
		System.out.println("******************addresss update");
		
		return "/profile/change-banner";
	}
	
	@RequestMapping(value="/seller/ajax/supplierImage/save",method=RequestMethod.POST)
	public @ResponseBody String saveSupplierImage(HttpServletRequest request, Model model,@RequestParam(value="content")MultipartFile obj,RedirectAttributes redirectAttributes){
		System.out.println("***************1111111111111111*****************");
		
		File serverFile=null;
		String imagePath = request.getParameter("profileimage");
		
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		AmazonS3 s3 = AmazonService.getAmazonS3();
		try{
			byte[] bytes=obj.getBytes();
			File tempFile=File.createTempFile("500x500_", ".jpg",new File(request.getServletContext().getRealPath(request.getServletContext().getInitParameter("uploadTheme"))));
			serverFile=new File("/"+tempFile);
			BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			
			System.out.println("********img size****"+obj.getSize());
		}catch(Exception e){
			System.out.println("********error in uploading*********"+e);
		}
		
		if(imagePath!=null){
			System.out.println("************enter in theme img***********");
			String bucketName = "krenai.seller";
			
			String key = "profile/"+String.valueOf(supplier.getSupplierId()*23.08f)+"/"+new Date().getTime();
			String ext = FilenameUtils.getExtension(imagePath);
			System.out.println("*************ext**********"+ext);
			 Boolean isImageValidated = ValidateImage.validateImageExtension(ext);
			 if(isImageValidated)
				 key+="."+ext;
			
			 else{
				 redirectAttributes.addFlashAttribute("failedMessage", "Unable to upload file in selected format. Please try to upload file with .jpg or .png extension");
				 redirectAttributes.addFlashAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
				 return "";
			 }
			 
		        try {
		            File newFileJPG = new File(imagePath);
		            System.out.println("Uploading a new object to S3 from a file\n");
		           try{
		    	       FileInputStream input = new FileInputStream(newFileJPG);
		    	       MultipartFile multi = new MockMultipartFile("file",
		    	         newFileJPG.getName(), "image/jpg", IOUtils.toByteArray(input));
		        	   s3.putObject(new PutObjectRequest(bucketName, key, multipartToFile(multi)));
		        	   supplier.setImagePath("https://s3-us-west-2.amazonaws.com/krenai.seller/"+key);
		        	  
		        	   redirectAttributes.addFlashAttribute("successMessage", "Your Profile pohot updated successfully..");
		           }catch(Exception e){
		        	   redirectAttributes.addFlashAttribute("failedMessage", "Unable to upload file in selected format. Please try to upload file with .jpg or .png extension");
		        	   redirectAttributes.addFlashAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
						 System.out.println("***************************8exception in converting image ***"+e);
						 return "";
		        	   
		           }
		            //s3.putObject(new PutObject);

		            
		            
		            
		        } catch (AmazonServiceException ase) {
		        	redirectAttributes.addFlashAttribute("failedMessage", "Unable to process selected file this time. Please try after some time.");
		        	redirectAttributes.addFlashAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
					 System.out.println("Caught an AmazonServiceException, which means your request made it "
		                    + "to Amazon S3, but was rejected with an error response for some reason.");
		            System.out.println("Error Message:    " + ase.getMessage());
		            System.out.println("HTTP Status Code: " + ase.getStatusCode());
		            System.out.println("AWS Error Code:   " + ase.getErrorCode());
		            System.out.println("Error Type:       " + ase.getErrorType());
		            System.out.println("Request ID:       " + ase.getRequestId());
		            return "";
			           
		        } catch (AmazonClientException ace) {
		        	redirectAttributes.addFlashAttribute("failedMessage", "Unable to process selected file this time. Please try after some time.");
		        	redirectAttributes.addFlashAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
					  System.out.println("Caught an AmazonClientException, which means the client encountered "
		                    + "a serious internal problem while trying to communicate with S3, "
		                    + "such as not being able to access the network.");
		            System.out.println("Error Message: " + ace.getMessage());
		            return "";
			           
		        }
		        supplierService.save(supplier);
		}
		return "";
		
	}
	
	@RequestMapping(value="/themeMySeller/ajax",method=RequestMethod.POST)
	public @ResponseBody String imagePath(HttpServletRequest request, Model model,@RequestParam(value="content")MultipartFile obj){
		
		File serverFile=null;

		try{
			byte[] bytes=obj.getBytes();
			File tempFile=File.createTempFile("500x500_", ".jpg",new File(request.getServletContext().getRealPath(request.getServletContext().getInitParameter("uploadTheme"))));
			serverFile=new File("/"+tempFile);
			BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			
			System.out.println("********img size****"+obj.getSize());
		}catch(Exception e){
			System.out.println("********error in uploading*********"+e);
		}
		
		return serverFile.getAbsolutePath();
		
	}
	
	@RequestMapping(value="/profile/image/upload", method=RequestMethod.POST)
	public String imageUpdateChange(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request, HttpSession session){

		System.out.println("*****************url** "+request.getParameter("imagePath"));
		String imagePath=request.getParameter("imagePath");
		System.out.println("************multipartProductImages theme img***********"+imagePath);
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		
		
		AmazonS3 s3 = AmazonService.getAmazonS3();
		
		if(imagePath!=null){
			System.out.println("************enter in theme img***********");
			String bucketName = "krenai.seller";
			
			String key = "profile/"+String.valueOf(supplier.getSupplierId()*23.08f)+"/"+new Date().getTime();
			String ext = FilenameUtils.getExtension(imagePath);
			System.out.println("*************ext**********"+ext);
			 Boolean isImageValidated = ValidateImage.validateImageExtension(ext);
			 if(isImageValidated)
				 key+="."+ext;
			
			 else{
				 redirectAttributes.addFlashAttribute("failedMessage", "Unable to upload file in selected format. Please try to upload file with .jpg or .png extension");
				 redirectAttributes.addFlashAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
				 return "";
			 }
			 
		        try {
		            File newFileJPG = new File(imagePath);
		            System.out.println("Uploading a new object to S3 from a file\n");
		           try{
		    	       FileInputStream input = new FileInputStream(newFileJPG);
		    	       MultipartFile multi = new MockMultipartFile("file",
		    	         newFileJPG.getName(), "image/jpg", IOUtils.toByteArray(input));
		        	   s3.putObject(new PutObjectRequest(bucketName, key, multipartToFile(multi)));
		        	   supplier.setImagePath("https://s3-us-west-2.amazonaws.com/krenai.seller/"+key);
		        	   session.setAttribute("loggedUser", supplier);
		        	   redirectAttributes.addFlashAttribute("successMessage", "Your Profile pohot updated successfully..");
		           }catch(Exception e){
		        	   redirectAttributes.addFlashAttribute("failedMessage", "Unable to upload file in selected format. Please try to upload file with .jpg or .png extension");
		        	   redirectAttributes.addFlashAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
						 System.out.println("***************************8exception in converting image ***"+e);
						 return "";
		        	   
		           }
		            //s3.putObject(new PutObject);

		            
		            
		            
		        } catch (AmazonServiceException ase) {
		        	redirectAttributes.addFlashAttribute("failedMessage", "Unable to process selected file this time. Please try after some time.");
		        	redirectAttributes.addFlashAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
					 System.out.println("Caught an AmazonServiceException, which means your request made it "
		                    + "to Amazon S3, but was rejected with an error response for some reason.");
		            System.out.println("Error Message:    " + ase.getMessage());
		            System.out.println("HTTP Status Code: " + ase.getStatusCode());
		            System.out.println("AWS Error Code:   " + ase.getErrorCode());
		            System.out.println("Error Type:       " + ase.getErrorType());
		            System.out.println("Request ID:       " + ase.getRequestId());
		            return "";
			           
		        } catch (AmazonClientException ace) {
		        	redirectAttributes.addFlashAttribute("failedMessage", "Unable to process selected file this time. Please try after some time.");
		        	redirectAttributes.addFlashAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
					  System.out.println("Caught an AmazonClientException, which means the client encountered "
		                    + "a serious internal problem while trying to communicate with S3, "
		                    + "such as not being able to access the network.");
		            System.out.println("Error Message: " + ace.getMessage());
		            return "";
			           
		        }
		        
		        supplierService.save(supplier);
		        redirectAttributes.addFlashAttribute("successMessage", "Banner updated successfully..");
		
		}
		else
		redirectAttributes.addFlashAttribute("successMessage", "Image path is null..");
		
		return "redirect:/profile/update";
	}

	
	@RequestMapping(value="/ajax/update/SupplierImage",method=RequestMethod.POST)
	public @ResponseBody String imagePathAjax(HttpServletRequest request, Model model,@RequestParam(value="content")MultipartFile obj, HttpSession session){

		File tempFile=null;
		
		try{
			byte[] bytes=obj.getBytes();
			System.out.println("***********enter in try*********");
			 tempFile=File.createTempFile("500x500_", ".jpg",new File(request.getServletContext().getRealPath(request.getServletContext().getInitParameter("uploadSupplierImage"))));
			BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(tempFile));
			stream.write(bytes);
			stream.close();
			
		}catch(Exception e){
			System.out.println("********error in uploading*********"+e);
		}
	
//		int supplierId=Integer.parseInt(request.getParameter("supplier"));
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
//		Supplier supplier=supplierService.findOne(supplierId);
		
		 AmazonS3 s3 = AmazonService.getAmazonS3();
	       
	        
			System.out.println("***********enter in multipartProductImages*********");
		        try {
		        	
		            System.out.println("Uploading a new object to S3 from a file\n");
		           try{
		        	   System.out.println("***********try catch in multipartProductImages*********");
		        	   FileInputStream input = new FileInputStream(tempFile);
		        	   MultipartFile multi = new MockMultipartFile("file",
		        			   tempFile.getName(), "image/jpg", IOUtils.toByteArray(input));
		        	 
		        	 String bucketName = "krenai.seller";
		  			 String key = "profile/"+String.valueOf(supplier.getSupplierId()*23.08f)+"/"+new Date().getTime();
		  			 String ext = FilenameUtils.getExtension(multi.getOriginalFilename());
		  			 Boolean isImageValidated = ValidateImage.validateImageExtension(ext);
		  			 if(isImageValidated)
		  				 key+="."+ext;
		  			 
		        	   s3.putObject(new PutObjectRequest(bucketName, key, multipartToFile(multi)));
		        	   supplier.setImagePath("https://s3-us-west-2.amazonaws.com/krenai.seller/"+key);
		        	   
		        	   input.close();
		        	   if(tempFile.isFile()){
	        		   tempFile.delete();
	        	       System.out.println("*******file deleted main**********");
	        	      }
	        	      else{
	        	       System.out.println("**********error cannot deleted***********");
	        	      }
		        	   model.addAttribute("successMessage", "Your profile picture is uploaded successfully");
						
		           }
		           catch(FileNotFoundException f){
		        	   f.printStackTrace();
		           }
		           catch(Exception e){
		        	   	 model.addAttribute("failedMessage", "Unable to upload file in selected format. Please try to upload file with .jpg or .png extension");
						 model.addAttribute("supplierId", String.valueOf(supplier));
						 System.out.println("***************************8exception in converting image ***"+e);
						 return "registration/supplier-image-upload";
		        	   
		           }
		            //s3.putObject(new PutObject);

		            
		            
		            
		        } catch (AmazonServiceException ase) {
		        	 model.addAttribute("failedMessage", "Unable to process selected file this time. Please try after some time.");
					 model.addAttribute("supplierId", String.valueOf(supplier));
					 System.out.println("Caught an AmazonServiceException, which means your request made it "
		                    + "to Amazon S3, but was rejected with an error response for some reason.");
		            System.out.println("Error Message:    " + ase.getMessage());
		            System.out.println("HTTP Status Code: " + ase.getStatusCode());
		            System.out.println("AWS Error Code:   " + ase.getErrorCode());
		            System.out.println("Error Type:       " + ase.getErrorType());
		            System.out.println("Request ID:       " + ase.getRequestId());
		            return "registration/supplier-image-upload";
			           
		        } catch (AmazonClientException ace) {
		        	model.addAttribute("failedMessage", "Unable to process selected file this time. Please try after some time.");
					 model.addAttribute("supplierId", String.valueOf(supplier));
					  System.out.println("Caught an AmazonClientException, which means the client encountered "
		                    + "a serious internal problem while trying to communicate with S3, "
		                    + "such as not being able to access the network.");
		            System.out.println("Error Message: " + ace.getMessage());
		            return "registration/supplier-image-upload";
			           
		        }
			
//		}
//		        if(serverFile.delete()){
//	        		   serverFile.delete();
//		   	        	tempFile.delete();
//		   	            System.out.println("*******file deleted main**********");
//	        	   }
//	        	   else{
//	        	       System.out.println("**********error cannot deleted***********");
//	        	   }
		supplier=supplierService.save(supplier);
		System.out.println("**********sup id 3*********"+supplier.getSupplierId());
		model.addAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
		session.setAttribute("loggedUser", supplier);
		return supplier.getImagePath();
	}
	
	
	@RequestMapping(value="/profile/theme/upload", method=RequestMethod.POST)
	public String themeUpdateChange(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request){
		File serverFile=null;
		System.out.println("*****************url** "+request.getParameter("themeUrl"));
		String multipartProductImages=request.getParameter("themeImage");
		System.out.println("************multipartProductImages theme img***********"+multipartProductImages);
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		String themeUrl = request.getParameter("themeUrl");
		SupplierShop supplierShop = supplier.getSupplierShop();
		
		
		AmazonS3 s3 = AmazonService.getAmazonS3();
		
		
		if(themeUrl!=null){
//			String contextPath = (String)servletContext.getInitParameter("sellerContextPath");
//			contextPath+=themeUrl;
			supplierShop.setShopTheme(themeUrl);
			redirectAttributes.addFlashAttribute("successMessage", "Banner updated successfully..");
			supplierShopService.save(supplierShop);
		}
		else{
			if(multipartProductImages!=null){
				System.out.println("************enter in theme img***********");
				String bucketName = "krenai.seller";
				
				String key = "profile/"+String.valueOf(supplier.getSupplierId()*23.08f)+"/"+new Date().getTime()+"_banner";
				String ext = FilenameUtils.getExtension(multipartProductImages);
				System.out.println("*************ext**********"+ext);
				 Boolean isImageValidated = ValidateImage.validateImageExtension(ext);
				 if(isImageValidated)
					 key+="."+ext;
				
				 else{
					 redirectAttributes.addFlashAttribute("failedMessage", "Unable to upload file in selected format. Please try to upload file with .jpg or .png extension");
					 redirectAttributes.addFlashAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
					 return "";
				 }
				 
			        try {
			            File newFileJPG = new File(multipartProductImages);
			            System.out.println("Uploading a new object to S3 from a file\n" +newFileJPG);
			           try{
			    	       FileInputStream input = new FileInputStream(newFileJPG);
			    	       MultipartFile multi = new MockMultipartFile("file",
			    	         newFileJPG.getName(), "image/jpg", IOUtils.toByteArray(input));
			        	   s3.putObject(new PutObjectRequest(bucketName, key, multipartToFile(multi)));
			        	   supplierShop.setShopTheme("https://s3-us-west-2.amazonaws.com/krenai.seller/"+key);
			        	   System.out.println("************suppliershop img***********"+supplierShop);
			        	   
			        	   input.close();
			        	   if(newFileJPG.isFile()){
			        		   newFileJPG.delete();
		        	       System.out.println("*******file deleted main**********");
		        	      }
		        	      else{
		        	       System.out.println("**********error cannot deleted***********");
		        	      }
					       
			   	            System.out.println("*******file deleted main**********");
		        	   
			   			
			        	   redirectAttributes.addFlashAttribute("successMessage", "Your Banner updated successfully..");
			           }catch(Exception e){
			        	   redirectAttributes.addFlashAttribute("failedMessage", "Unable to upload file in selected format. Please try to upload file with .jpg or .png extension");
			        	   redirectAttributes.addFlashAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
							 System.out.println("***************************8exception in converting image ***"+e);
							 return "";
			        	   
			           }
			            //s3.putObject(new PutObject);

			            
			            
			            
			        } catch (AmazonServiceException ase) {
			        	redirectAttributes.addFlashAttribute("failedMessage", "Unable to process selected file this time. Please try after some time.");
			        	redirectAttributes.addFlashAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
						 System.out.println("Caught an AmazonServiceException, which means your request made it "
			                    + "to Amazon S3, but was rejected with an error response for some reason.");
			            System.out.println("Error Message:    " + ase.getMessage());
			            System.out.println("HTTP Status Code: " + ase.getStatusCode());
			            System.out.println("AWS Error Code:   " + ase.getErrorCode());
			            System.out.println("Error Type:       " + ase.getErrorType());
			            System.out.println("Request ID:       " + ase.getRequestId());
			            return "";
				           
			        } catch (AmazonClientException ace) {
			        	redirectAttributes.addFlashAttribute("failedMessage", "Unable to process selected file this time. Please try after some time.");
			        	redirectAttributes.addFlashAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
						  System.out.println("Caught an AmazonClientException, which means the client encountered "
			                    + "a serious internal problem while trying to communicate with S3, "
			                    + "such as not being able to access the network.");
			            System.out.println("Error Message: " + ace.getMessage());
			            return "";
				           
			        }
				
				
//				RawImageData RawImageData=new RawImageData(supplier.getSupplierId(), supplier.getFullName(),
//						String.valueOf(supplier.getSupplierId()),"uploadTheme","themeImageToSave", servletContext); 
//				String imageUrl=new UploadImage().imageUploadingSettingsCommon( RawImageData, multipartProductImages);
//				supplierShop.setShopTheme(multipartProductImages);
				
				
			}
			supplierShopService.save(supplierShop);
			redirectAttributes.addFlashAttribute("successMessage", "Banner updated successfully..");
		}
		
		redirectAttributes.addFlashAttribute("successMessage", "Banner updated successfully..");
		return "redirect:/profile/update";
	}
	
	@RequestMapping(value="/profile/theme", method=RequestMethod.POST)
	public String themeUpdateChangeGet(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		String themeUrl = request.getParameter("themeUrl");
		//themeUrl = KrenaiCONSTANTS.sellerContext+themeUrl;
		System.out.println("*****************url** "+request.getParameter("themeUrl"));
		SupplierShop supplierShop = supplier.getSupplierShop();
		supplierShop.setShopTheme(themeUrl);
		supplierShopService.save(supplierShop);
		redirectAttributes.addFlashAttribute("successMessage", "Banner Updated Successfully.");
		return "redirect:/profile/update";
	}
	


	@RequestMapping(value="/profile/address/update", method=RequestMethod.POST)
	public String addressUpdate(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		String lat = request.getParameter("lat");
		String lng = request.getParameter("lng");
		String googleAddress = request.getParameter("add");
		Address address = supplier.getAddress();
		address.setLatitude(Double.valueOf(lat));
		address.setLongitude(Double.valueOf(lng));
		address.setGoogleAddress(googleAddress);
		addressService.save(address);
		redirectAttributes.addFlashAttribute("successMessage", "Address Updated Successfully.");
		return "redirect:/profile/update";
	}


	@RequestMapping(value="/store/update/web-url", method=RequestMethod.GET)
	public String updateWebsiteAddress(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		
		return "/profile/update-store-name-for-web-url";
	}

	@RequestMapping(value="/supplier/store/findweb", method=RequestMethod.POST)
	public @ResponseBody Object verifyWebAddress(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		String address = request.getParameter("or_").replace(" ", "_").toLowerCase();
		Supplier supplierVer = supplierService.findByUniqueStoreName(address);
		if (supplierVer==null){
			return "available";
		}
		else{
			List<String> list = new ArrayList<String>();
			String str1 = address.replaceAll("_", "");
			String str2 = address.replaceAll("-", "");
			String str3 = address.replaceAll("_", ".");
			String str5 = address.replaceAll("-", ".");
			String str4 = address.replaceAll("=", "");
			//String str6 = address.replaceAll(".", "*");
			String[] strArray = {str1,str2,str3,str4,str5};
			List<Supplier> supplierList = supplierService.findByUniqueStoreNameIn(strArray);
			if(supplierList.size()<5){
				for(Supplier sup: supplierList){
					for(int i=0; i<5; i++ ){
						if(!strArray[i].equalsIgnoreCase(sup.getUniqueStoreName())){
							list.add(strArray[i].toLowerCase());
						}
					}
				}
			}
			else
				return "notAvailable";
			
			return list;
		}

	}
	@RequestMapping(value="/supplier/store/webname/update", method=RequestMethod.POST)
	public @ResponseBody Object updateWebName(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request){
		Supplier supplier = supplierService.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		supplier.setUniqueStoreName(request.getParameter("or_").replaceAll(" ", "_"));
		try{
			supplier = supplierService.save(supplier);
			return supplier.getUniqueStoreName();
		}catch (Exception e) {
			return "failed";
		}

	}

	@RequestMapping(value="/under-construction", method=RequestMethod.GET)
	public String underCostruction(Model model, HttpServletResponse response){
		System.out.println("******************addresss update");
		return "under-construction";
	}
	
}
