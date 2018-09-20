package com.bugfree.service.supplier.documents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.documents.SupplierAddressProofDetails;
import com.bugfree.repository.supplier.documents.SupplierAddressProofDetailsRepository;

@Service
@Transactional
public class SupplierAddressProofDetailsService {

	@Autowired
	private SupplierAddressProofDetailsRepository supplierAddressProofDetailsRepository;
	
	public SupplierAddressProofDetails save(SupplierAddressProofDetails supplierAddressProofDetails) {
		return supplierAddressProofDetailsRepository.save(supplierAddressProofDetails);
	}

	public SupplierAddressProofDetails findBySupplier(Supplier supplier) {
		return supplierAddressProofDetailsRepository.findBySupplier( supplier) ;
	}

}
