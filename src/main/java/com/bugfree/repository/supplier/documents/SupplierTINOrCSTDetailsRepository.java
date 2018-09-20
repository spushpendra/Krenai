package com.bugfree.repository.supplier.documents;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.documents.SupplierAddressProofDetails;
import com.bugfree.model.supplier.documents.SupplierTINOrCSTDetails;

public interface SupplierTINOrCSTDetailsRepository extends PagingAndSortingRepository<SupplierTINOrCSTDetails, Integer> {

	SupplierTINOrCSTDetails findBySupplier(Supplier supplier);

}
