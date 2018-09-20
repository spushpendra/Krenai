package com.bugfree.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.product.ProductDescription;
import com.bugfree.repository.product.ProductDescriptionRepository;

@Service
@Transactional
public class ProductDescriptionService {

	@Autowired 
	private ProductDescriptionRepository productDescriptionRepository; 
	public ProductDescription save(ProductDescription productDesc) {
		return productDescriptionRepository.save(productDesc);
	}

	
}
