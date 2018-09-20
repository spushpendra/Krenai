package com.bugfree.repository.category;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.category.Category;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

	Iterable<Category> findBySupplierAndStatusIn(Supplier supplier,
			List<Status> statusList);

	Iterable<Category> findByStatus(Status status);

}
