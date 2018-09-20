package com.bugfree.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.category.Category;
import com.bugfree.model.product.Product;
import com.bugfree.model.subcategory.SubCategory;
import com.bugfree.repository.product.ProductRepository;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	public Iterable<Product> findByCategory(Category category) {
		
		return productRepository.findByCategory(category);
	}
	public Iterable<Product> findByCategoryAndSubCategory(Category category,
			SubCategory subcategory) {
		
		 return productRepository.findByCategoryAndSubCategory(category, subcategory);
	}
	public Product findOne(int parseInt) {
		return productRepository.findOne(parseInt);
	}
	public Product save(Product product) {
		
		return productRepository.save(product);
	}
	

}
