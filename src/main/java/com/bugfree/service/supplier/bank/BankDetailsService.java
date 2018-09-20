package com.bugfree.service.supplier.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.bank.BankDetails;
import com.bugfree.repository.supplier.bank.BankDetailsRepository;

@Service
@Transactional
public class BankDetailsService {

	@Autowired
	private BankDetailsRepository bankDetailsRepository;
	
	public BankDetails save(BankDetails bankDetails) {
		return bankDetailsRepository.save(bankDetails);
	}

	public BankDetails findBySupplier(Supplier supplier) {
		return bankDetailsRepository.findBySupplier(supplier);
	}

}
