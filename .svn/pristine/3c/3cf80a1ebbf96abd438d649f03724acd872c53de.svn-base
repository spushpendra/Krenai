package com.bugfree.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bugfree.common.amazon.AmazonService;
import com.bugfree.common.validate.ValidateImage;
import com.bugfree.model.social.feed.Feeds;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.product.SellerProductListings;
import com.bugfree.repository.social.feed.FeedsRepository;
import com.bugfree.repository.supplier.SupplierRepository;

@Service
public class FeedsService {

	
	@Autowired FeedsRepository feedsRepository;
	@Autowired SupplierRepository supplierRepository;
	

	private File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	        File convFile = new File( multipart.getOriginalFilename());
	        multipart.transferTo(convFile);
	        return convFile;
	}
	
	public boolean uploadFeed (SellerProductListings spl, String msg, String feedFor){

		Feeds feeds = new Feeds();
		
	feeds.setImageCaptionUrl(spl.getProduct().getProductImage().getImagePath1());
	
	feeds.setImageRedirectUrl("http://www.krenai.com/supplier/store/product?ref=news&title=view&name="+
	spl.getProduct().getProductName()+"&store="+spl.getSupplier().getSupplierShop().getShopName()+
	"&pid="+spl.getProduct().getProductId()+"&ids="+spl.getSupplier().getSupplierId()+
	"&spli="+spl.getSellerProductListingId()+"&lat="+spl.getSupplier().getAddress().getLatitude()+
	"&location="+spl.getSupplier().getAddress().getGoogleAddress());
	feeds.setFeedType("published");
		
		feeds.setAllowedFor(feedFor);
		feeds.setFeedMessage(msg);
		feeds.setPublishedBySupplier(spl.getSupplier());
		feeds.setFeedMessage(msg);
		feeds.setPublishedBySupplier(true);
		feeds.setPublishedBySupplier(true);
		feeds = feedsRepository.save(feeds);
//		resultMap.put("success", "success");
//		resultMap.put("timestamp", String.valueOf(feeds.getFeedPublishedTimestamp()));
//		resultMap.put("feedsId", String.valueOf(feeds.getFeedsId()));
		
		return true;
	}
	
	
}
