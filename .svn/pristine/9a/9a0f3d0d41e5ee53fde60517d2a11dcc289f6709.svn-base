package com.bugfree.common.amazon;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;


public class AmazonService {

	
	public static  AmazonS3 getAmazonS3(){

		 AmazonS3 s3 = new AmazonS3Client(new AWSCredentials() {
			 /*public String getAWSSecretKey() {
				// TODO Auto-generated method stub
				return "VV0UaFjCkynHKZ7qUW6BKv2ZotlYTFE8zn2KOIES";
			}
			
			public String getAWSAccessKeyId() {
				// TODO Auto-generated method stub
				return "AKIAIPMM7IJNYUNWDVWA";
			}*/
			 public String getAWSSecretKey() {
					// TODO Auto-generated method stub
					return "AE3JbFHGtnVzybFhgwG+waHjvr/WAw+I5mbUb9k7";
				}
				
				public String getAWSAccessKeyId() {
					// TODO Auto-generated method stub
					return "AKIAIGGDPMYBITZUAW4Q";
				}
			});
	        
	        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
	        s3.setRegion(usWest2);
	       
	        return s3;
	}
	
	
}
