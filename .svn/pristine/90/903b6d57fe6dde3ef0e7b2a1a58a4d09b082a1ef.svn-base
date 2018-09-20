package com.bugfree.service.supplier.documents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.documents.SupplierAddressProofDetails;
import com.bugfree.model.supplier.documents.SupplierTINOrCSTDetails;
import com.bugfree.repository.supplier.documents.SupplierTINOrCSTDetailsRepository;

@Service
@Transactional
public class SupplierTINOrCSTDetailsService {

	@Autowired
	SupplierTINOrCSTDetailsRepository supplierTINOrCSTDetailsRepository;
	
	public SupplierTINOrCSTDetails save(SupplierTINOrCSTDetails supplierTINOrCSTDetails) {

		return supplierTINOrCSTDetailsRepository.save(supplierTINOrCSTDetails);
	}
	public SupplierTINOrCSTDetails findBySupplier(Supplier supplier) {
		return supplierTINOrCSTDetailsRepository.findBySupplier( supplier);
	}

}
