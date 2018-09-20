package com.bugfree.controller.dashboard.social;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bugfree.common.amazon.AmazonService;
import com.bugfree.common.validate.ValidateImage;
import com.bugfree.model.social.feed.Feeds;
import com.bugfree.model.social.network.UserFollow;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.repository.social.feed.FeedsRepository;
import com.bugfree.repository.supplier.SupplierRepository;


@Controller
public class SocialController {

	
	@Autowired
	private FeedsRepository feedsRepository;
	@Autowired
	private SupplierRepository supplierRepository;

	private File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	        File convFile = new File( multipart.getOriginalFilename());
	        multipart.transferTo(convFile);
	        return convFile;
	}	
	

	@RequestMapping(value = "/dashboard/social/feeds/add", method = RequestMethod.POST)
	public @ResponseBody boolean addressBook(Model model, HttpServletRequest request,
			@RequestParam("msg")String msg,
			@RequestParam("feedfor") String feedFor, 
			@RequestParam(value="userfile", required=false) MultipartFile feedImage) {
		//HttpSession session = request.getSession();
		//User user = (User)session.getAttribute("user");
		Supplier supplier = supplierRepository.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		Feeds feeds = new Feeds();
		
		String urlFound = request.getParameter("urlFound");
		System.out.println("********url found****"+urlFound);
		System.out.println("****************msg*********"+msg);
		//Map<String, String> resultMap = new HashMap<String, String>();
		
			if (feedImage!=null) {
				//String imageName = supplier.getSupplierId()+new Date().getTime()+"";
				System.out.println("userFile8***************"+feedImage.getSize());
				
//				RawImageData RawImageData=new RawImageData(String.valueOf(user.getUserId()),imageName, String.valueOf(user.getUserId()) ,"uploadUserFeedsImages","showUserfeedsImagesPath", servletContext); 
//				
//				String imageUrl=new UploadImage().imageUploadingSettingsCommon( RawImageData, feedImage);
//				System.out.println("************image url********"+imageUrl);
				
				 AmazonS3 s3 = AmazonService.getAmazonS3();
			       
			        
					if(feedImage!=null){
						 String bucketName = "krenai.seller";
						 String key = "feeds/"+String.valueOf(supplier.getSupplierId())+"/"+new Date().getTime();
						 String ext = FilenameUtils.getExtension(feedImage.getOriginalFilename());
						 Boolean isImageValidated = ValidateImage.validateImageExtension(ext);
						 if(isImageValidated)
							 key+="."+ext;
						
						 else{
							 model.addAttribute("failedMessage", "Unable to upload file in selected format. Please try to upload file with .jpg or .png extension");
							// model.addAttribute("supplierId", String.valueOf(supplier.getSupplierId()));
							 
						 }
					        try {
					            
					            System.out.println("Uploading a new object to S3 from a file\n");
					           try{
					        	   s3.putObject(new PutObjectRequest(bucketName, key, multipartToFile(feedImage)));
					        	   feeds.setImageCaptionUrl("https://s3-us-west-2.amazonaws.com/krenai.seller/"+key);
					        	   //resultMap.put("image", feeds.getImageCaptionUrl());
					        	   model.addAttribute("successMessage", "Your profile picture is uploaded successfully");
									
					           }catch(Exception e){
					        	   	 model.addAttribute("failedMessage", "Unable to upload file in selected format. Please try to upload file with .jpg or .png extension");
									 System.out.println("***************************8exception in converting image ***"+e);
									 
					           }
					            
					            
					        } catch (AmazonServiceException ase) {
					        	 model.addAttribute("failedMessage", "Unable to process selected file this time. Please try after some time.");
								 System.out.println("Caught an AmazonServiceException, which means your request made it "
					                    + "to Amazon S3, but was rejected with an error response for some reason.");
					            System.out.println("Error Message:    " + ase.getMessage());
					            System.out.println("HTTP Status Code: " + ase.getStatusCode());
					            System.out.println("AWS Error Code:   " + ase.getErrorCode());
					            System.out.println("Error Type:       " + ase.getErrorType());
					            System.out.println("Request ID:       " + ase.getRequestId());
					               
					        } catch (AmazonClientException ace) {
					        	model.addAttribute("failedMessage", "Unable to process selected file this time. Please try after some time.");
								  System.out.println("Caught an AmazonClientException, which means the client encountered "
					                    + "a serious internal problem while trying to communicate with S3, "
					                    + "such as not being able to access the network.");
					            System.out.println("Error Message: " + ace.getMessage());
					               
					        }
						
					}
				
				
			}
			System.out.println("*******name*******"+request.getParameter("name"));
			System.out.println("*******request*******"+request.toString());
			
			feeds.setFeedType("published");
			
		/*if(urlFound.equals("true")){
			feeds.setFeedType("shared");
			System.out.println("****************image url*****"+request.getParameter("imageUrl"));
			feeds.setImageCaptionUrl(request.getParameter("imageUrl"));
			feeds.setImageRedirectUrl(request.getParameter("redirectUrl"));
			//resultMap.put("image", feeds.getImageCaptionUrl());
			//resultMap.put("imageRedirectUrl", feeds.getImageRedirectUrl());
			
		}*/
		feeds.setAllowedFor(feedFor);
		feeds.setFeedMessage(msg);
		feeds.setPublishedBySupplier(supplier);
		feeds.setFeedMessage(msg);
		feeds.setPublishedBySupplier(true);
		
		feeds = feedsRepository.save(feeds);
//		resultMap.put("success", "success");
//		resultMap.put("timestamp", String.valueOf(feeds.getFeedPublishedTimestamp()));
//		resultMap.put("feedsId", String.valueOf(feeds.getFeedsId()));
		
		return true;

	}	
	
}
