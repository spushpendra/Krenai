package com.bugfree.repository.product;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.product.ProductImage;

public interface ProductImageRepository extends
PagingAndSortingRepository<ProductImage, Integer> {

}
