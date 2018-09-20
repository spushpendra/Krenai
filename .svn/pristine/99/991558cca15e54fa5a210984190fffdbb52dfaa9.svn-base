package com.bugfree.service.subcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.category.Category;
import com.bugfree.model.status.Status;
import com.bugfree.model.subcategory.SubCategory;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.repository.subcategory.SubCategoryRepository;

@Service
@Transactional
public class SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	public Iterable<SubCategory> findByCategory(Category category) {
		return subCategoryRepository.findByCategory(category);
	}

	public SubCategory save(SubCategory subcategory) {
		return subCategoryRepository.save(subcategory);
	}

	public Iterable<SubCategory> findBySupplierAndStatusIn(Supplier supplier,
			List<Status> statusList) {
		return subCategoryRepository.findBySupplierAndStatusIn( supplier,
				statusList);
	}

	public SubCategory findOne(int id) {
		return subCategoryRepository.findOne(id);
	}

	public Iterable<SubCategory> findByCategoryAndStatus(Category category,
			Status status) {
		return subCategoryRepository.findByCategoryAndStatus(category,status);
	}

	public List<SubCategory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
