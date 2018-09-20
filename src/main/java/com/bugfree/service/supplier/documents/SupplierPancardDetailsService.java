package com.bugfree.service.supplier.documents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.documents.SupplierPancardDetails;
import com.bugfree.repository.supplier.documents.SupplierPancardDetailsRepository;

@Service
@Transactional
public class SupplierPancardDetailsService {

	@Autowired
	private SupplierPancardDetailsRepository supplierPancardDetailsRepository;
	public SupplierPancardDetails save(SupplierPancardDetails supplierPancardDetails) {

		return supplierPancardDetailsRepository.save(supplierPancardDetails);
	}
	public SupplierPancardDetails findBySupplier(Supplier supplier) {
		return supplierPancardDetailsRepository.findBySupplier( supplier) ;
	}

}
