package com.bugfree.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bugfree.model.category.Category;
import com.bugfree.model.product.Product;
import com.bugfree.model.product.ProductImage;
import com.bugfree.service.category.CategoryService;
import com.bugfree.service.product.ProductImageService;

public class UploadImage {

	
	public boolean imageUploadingSettings(String imageFor,
				ProductImage productImage,
					ProductImageService productImageService,
						MultipartFile multipartImages, 
							ServletContext servletContext, 
								Product product, 
									int noPicPath){
		try{
			System.out.println("*******************productid from image method"+product.getProductId());		
			
			String uploadProductFolderPath=(String)servletContext.getInitParameter("uploadProductFolderPath");
			String rootPath=servletContext.getRealPath(uploadProductFolderPath)+"/"+product.getProductId();
			String showProductImgPath=(String)servletContext.getInitParameter("showProductImgPath");
			MultipartFile[] uploadFileArray = {multipartImages};
			//invoke upload image method
			uploadImage(rootPath, productImage, productImageService, product, showProductImgPath, noPicPath, uploadFileArray);// uploadImage also invoked in other controllers
			return true;
		}catch(Exception e){
			System.out.println("*****************exception in uploadsetting method**************"+e);
			return false;
		}
			

		
	}
	
	private void uploadImage(String rootPath,
			ProductImage productImage,
				ProductImageService productImageService, 
					Product product, 
						String showProductImgPath, 
							int noPicPath, 
								MultipartFile... uploadImageArray){
		File dir = new File(rootPath);
		try {
			System.out.println("*****************noPicPath**************"+noPicPath);
			System.out.println("*****************exception in uploadimgae method**************"+product.getProductImage().getProductImageId());
			} catch (Exception e) {
			System.out.println("*****************exception in upload image retrieve productImage method**************"+e);
		}
		//ProductImage productImage=productImageService.findOne(product.getProductImage().getProductImageId());
		if (!dir.exists()) {dir.mkdirs();}
	    // Create the file on server
		
		String picName=null;
		String extension=null;
		String productId=String.valueOf(product.getProductId());
		int i=noPicPath;
		for(MultipartFile file:uploadImageArray){
			if(file.isEmpty())
				{	
					
				 }//end if
				else
					{
						try
							{
								byte[] bytes = file.getBytes();
								
								//InputStream in = new ByteArrayInputStream(bytes);
								//BufferedImage bImageFromConvert = ImageIO.read(in);
								
									picName=product.getProductName()+productId;
									extension = FilenameUtils.getExtension(file.getOriginalFilename());
									String pathToSave=showProductImgPath+product.getProductId()+"/"+ picName+"_"+ i +"."+extension;
								File serverFile = new File(rootPath + "/" + picName+"_"+ i +"."+extension);
								System.out.println("*****************************************pathToSave."+pathToSave);
								System.out.println("*****************************************uploadImageArray."+uploadImageArray.length);
								BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
								stream.write(bytes);
								System.out.println("*********************1********************uploadImageArray."+uploadImageArray.length);
								stream.close();
								System.out.println("**********************3*******************uploadImageArray."+uploadImageArray.length);
								switch (i) {
								case 1:
									productImage.setImagePath1(pathToSave);
									System.out.println("*****************************image 1 saved");
									break;
								case 2:
									productImage.setImagePath2(pathToSave);
									break; 
								case 3:
									productImage.setImagePath3(pathToSave);
									break;
								case 4:
									productImage.setImagePath4(pathToSave);
									break;
								case 5:
									productImage.setImagePath5(pathToSave);
									break;
								case 6:
									productImage.setImagePath6(pathToSave);
									break;
								case 7:
									productImage.setImagePath7(pathToSave);
									break;
								case 8:
									productImage.setImagePath8(pathToSave);
									break;
									

								default:
									break;
								}
								i++;
								System.out.println("***********************2******************uploadImageArray."+uploadImageArray.length);
								
								System.out.println("*****************************************Image uploading success.");
							}
						catch(Exception ex){System.out.println("*********************************Image uploading failed."+ex);}
						//employee.setPicPath(showProductImgPath + employeeId +"/"+ picName+"."+extension);
						
					} //end else
			}//end for loop
		System.out.println("****************************final*************productImage method."+productImage.getImagePath1());
		productImage.setProduct(product);
		productImageService.save(productImage);
//		product.setProductImage(productImage);
//		productService.save(product);
		
	}
	
	
	//------------------------------ for category image ------------------------
	
	public boolean imageUploadingSettingsForCategory(Category category, CategoryService categoryService, MultipartFile multipartImages, ServletContext servletContext){
		try{
			System.out.println("*******************productid from image method"+category.getCategoryId());		
			
			String uploadProductFolderPath=(String)servletContext.getInitParameter("uploadCategoryFolderPath");
			String rootPath=servletContext.getRealPath(uploadProductFolderPath)+"/"+category.getCategoryId();
			String showProductImgPath=(String)servletContext.getInitParameter("showCategoryFolderPath");
			MultipartFile[] uploadFileArray = {multipartImages};
			//invoke upload image method
			uploadImageForCategory(rootPath,category,categoryService, showProductImgPath, uploadFileArray);// uploadImage also invoked in other controllers
			return true;
		}catch(Exception e){
			System.out.println("*****************exception in uploadsetting method**************"+e);
			return false;
			}
		}
		
		private void uploadImageForCategory(String rootPath,Category category,CategoryService categoryService,  String showProductImgPath, MultipartFile... uploadImageArray){
			File dir = new File(rootPath);
			
			//ProductImage productImage=productImageService.findOne(product.getProductImage().getProductImageId());
			if (!dir.exists()) {dir.mkdirs();}
		    // Create the file on server
			
			String picName=null;
			String extension=null;
			String categoryId=String.valueOf(category.getCategoryId());
			for(MultipartFile file:uploadImageArray){
				if(file.isEmpty())
					{	
						
					 }//end if
					else
						{
							try
								{
									byte[] bytes = file.getBytes();
									
									//InputStream in = new ByteArrayInputStream(bytes);
									//BufferedImage bImageFromConvert = ImageIO.read(in);
									
										picName=category.getCategoryName()+categoryId;
										extension = FilenameUtils.getExtension(file.getOriginalFilename());
										String pathToSave=showProductImgPath+categoryId+"/"+ picName+"."+extension;
									File serverFile = new File(rootPath + "/" + picName+"."+extension);
									System.out.println("*****************************************pathToSave."+pathToSave);
									System.out.println("*****************************************uploadImageArray."+uploadImageArray.length);
									BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
									stream.write(bytes);
									System.out.println("*********************1********************uploadImageArray."+uploadImageArray.length);
									stream.close();
									System.out.println("**********************3*******************uploadImageArray."+uploadImageArray.length);
									
										category.setImageUrl(pathToSave);
										
									System.out.println("***********************2******************uploadImageArray."+uploadImageArray.length);
									
									System.out.println("*****************************************Image uploading success.");
								}
							catch(Exception ex){System.out.println("*********************************Image uploading failed."+ex);}
							//employee.setPicPath(showProductImgPath + employeeId +"/"+ picName+"."+extension);
							
						} //end else
				}//end for loop
			System.out.println("****************************final*************productImage method."+category.getImageUrl());
			
			categoryService.save(category);

			
		}//imageUploadingSettingsForSubCategory
	
		
		//------------------------------ for common image ------------------------
		
		public String imageUploadingSettingsCommon(RawImageData rawImageData, MultipartFile multipartImages){
			try{
				System.out.println("*******************productid from image method"+rawImageData.toSaveId);		
				int toSaveId=rawImageData.toSaveId;
				String picName=rawImageData.imageNamePrefix;
				String rootPath=rawImageData.rootPath;
				String showProductImgPath=rawImageData.toShowImagePath;
				MultipartFile[] uploadFileArray = {multipartImages};
				//invoke upload image method
				String returned=uploadImageForCommon(rootPath, showProductImgPath,toSaveId,picName, uploadFileArray);// uploadImage also invoked in other controllers
				return returned;
			}catch(Exception e){
				System.out.println("*****************exception in uploadsetting method**************"+e);
				return "exception";
				}
			}
			
			private String uploadImageForCommon(String rootPath,  String showProductImgPath,int toSaveId, String imageNamePrefix, MultipartFile... uploadImageArray){
				File dir = new File(rootPath+"/"+toSaveId);
				System.out.println("*************************1***************************");
				//ProductImage productImage=productImageService.findOne(product.getProductImage().getProductImageId());
				if (!dir.exists()) {dir.mkdirs();}
			    // Create the file on server
				String pathToSave = null;
				System.out.println("*************************2***************************");
				String picName=null;
				String extension=null;
				for(MultipartFile file:uploadImageArray){
					if(file.isEmpty())
						{	
							
						 }//end if
						else
							{
								try
									{
										byte[] bytes = file.getBytes();
										
										//InputStream in = new ByteArrayInputStream(bytes);
										//BufferedImage bImageFromConvert = ImageIO.read(in);
										System.out.println("*************************3***************************");
										
											picName=imageNamePrefix+toSaveId;
											extension = FilenameUtils.getExtension(file.getOriginalFilename());
											pathToSave=showProductImgPath+"/"+toSaveId+"/"+ picName+"."+extension;
										File serverFile = new File(rootPath + "/"+toSaveId+ "/" + picName+"."+extension);
										System.out.println("*****************************************pathToSave."+pathToSave);
										System.out.println("*****************************************extension."+extension);
										System.out.println("*****************************************serverFile."+serverFile);
										System.out.println("*****************************************uploadImageArray."+uploadImageArray.length);
										BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
										stream.write(bytes);
										System.out.println("*********************1********************uploadImageArray."+uploadImageArray.length);
										stream.close();
										System.out.println("**********************3*******************uploadImageArray."+uploadImageArray.length);
										
											//category.setImageUrl(pathToSave);
											
										System.out.println("***********************2******************uploadImageArray."+uploadImageArray.length);
										
										System.out.println("*****************************************Image uploading success.");
									}
								catch(Exception ex){System.out.println("*********************************Image uploading failed."+ex);}
								//employee.setPicPath(showProductImgPath + employeeId +"/"+ picName+"."+extension);
								
							} //end else
					}//end for loop
				System.out.println("****************************final*************productImage method."+pathToSave);
				
				//categoryService.save(category);

				return pathToSave;
			}
		
		
		
}
