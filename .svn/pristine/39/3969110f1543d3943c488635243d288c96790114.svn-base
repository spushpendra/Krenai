package com.bugfree.repository.subcategory;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.category.Category;
import com.bugfree.model.status.Status;
import com.bugfree.model.subcategory.SubCategory;
import com.bugfree.model.supplier.Supplier;

public interface SubCategoryRepository extends
		PagingAndSortingRepository<SubCategory, Integer> {

	Iterable<SubCategory> findByCategory(Category category);

	Iterable<SubCategory> findBySupplierAndStatusIn(Supplier supplier,
			List<Status> statusList);

	Iterable<SubCategory> findByCategoryAndStatus(Category category,
			Status status);

}
