package com.bugfree.repository.supplier.documents;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.documents.SupplierAddressProofDetails;

public interface SupplierAddressProofDetailsRepository extends PagingAndSortingRepository<SupplierAddressProofDetails, Integer> {

	SupplierAddressProofDetails findBySupplier(Supplier supplier);

}
