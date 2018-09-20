package com.bugfree.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.category.Category;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.repository.category.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Iterable<Category> findAll(){
		return categoryRepository.findAll();
	}

	
	public Category save(Category category) {
		return categoryRepository.save(category);
		
	}

	public Iterable<Category> findBySupplierAndStatusIn(Supplier supplier,
			List<Status> statusList) {
		return categoryRepository.findBySupplierAndStatusIn( supplier,
				 statusList);
	}

	public Category findOne(int id) {
		return categoryRepository.findOne(id);
	}

	public Iterable<Category> findByStatus(Status status) {
		// TODO Auto-generated method stub
		return categoryRepository.findByStatus(status);
	}
}
