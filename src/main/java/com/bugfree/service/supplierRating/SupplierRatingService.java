package com.bugfree.service.supplierRating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplierRating.SupplierRating;
import com.bugfree.model.user.User;
import com.bugfree.repository.supplierRating.SupplierRatingRepository;

@Service
@Transactional
public class SupplierRatingService {

	@Autowired
	private SupplierRatingRepository srr;
	public SupplierRating save(SupplierRating supplierRating) {
		
		return srr.save(supplierRating);
	}
	public List<SupplierRating> findBySupplier(
			com.bugfree.model.supplier.Supplier supplier) {
		return srr.findBySupplier(supplier);
	}
	public SupplierRating findBySupplierAndUser(Supplier supplier, User user) {
		return srr.findBySupplierAndUser(supplier, user);
	}
	
	
	

}
