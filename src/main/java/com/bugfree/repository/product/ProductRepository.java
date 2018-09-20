package com.bugfree.repository.product;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.category.Category;
import com.bugfree.model.product.Product;
import com.bugfree.model.subcategory.SubCategory;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

	Iterable<Product> findByCategory(Category category);

	Iterable<Product> findByCategoryAndSubCategory(Category category,
			SubCategory subcategory);

}
