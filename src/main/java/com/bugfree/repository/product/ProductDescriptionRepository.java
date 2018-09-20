package com.bugfree.repository.product;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.product.ProductDescription;

public interface ProductDescriptionRepository extends
		PagingAndSortingRepository<ProductDescription, Integer> {

}
