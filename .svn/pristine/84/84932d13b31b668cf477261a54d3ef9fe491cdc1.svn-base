package com.bugfree.repository.supplierRating;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplierRating.SupplierRating;
import com.bugfree.model.user.User;

public interface SupplierRatingRepository extends
		PagingAndSortingRepository<SupplierRating, Integer> {

	List<SupplierRating> findBySupplier(Supplier supplier);

	SupplierRating findBySupplierAndUser(Supplier supplier, User user);

}
