package com.bugfree.service.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugfree.model.payment.KrenaiPayment;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.repository.payment.KrenaiPaymentRepository;

@Service
public class KrenaiPaymentService {
	
	@Autowired
	private KrenaiPaymentRepository krenaiPaymentRepository;

	public List<KrenaiPayment> findBySupplier(Supplier supplier) {
		return krenaiPaymentRepository.findBySupplier(supplier);
	}

	public KrenaiPayment save(KrenaiPayment kp) {
		return krenaiPaymentRepository.save(kp);
	}

}
