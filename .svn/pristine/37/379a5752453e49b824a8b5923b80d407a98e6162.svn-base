package com.bugfree.repository.supplier.bank;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.bank.BankDetails;

public interface BankDetailsRepository extends PagingAndSortingRepository<BankDetails, Integer> {

	BankDetails findBySupplier(Supplier supplier);

}
