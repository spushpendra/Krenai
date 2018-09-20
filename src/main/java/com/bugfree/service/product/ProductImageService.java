package com.bugfree.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.product.ProductImage;
import com.bugfree.repository.product.ProductImageRepository;

@Service
@Transactional
public class ProductImageService {

	@Autowired
	private ProductImageRepository productImageRepository;
	public ProductImage findOne(int productImageId) {
		
		return productImageRepository.findOne(productImageId);
	}
	public ProductImage save(ProductImage productImage) {
		return productImageRepository.save(productImage);
	}

}
