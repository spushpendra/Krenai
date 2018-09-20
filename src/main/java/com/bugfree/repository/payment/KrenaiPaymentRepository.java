package com.bugfree.repository.payment;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.payment.KrenaiPayment;
import com.bugfree.model.supplier.Supplier;

public interface KrenaiPaymentRepository extends PagingAndSortingRepository<KrenaiPayment, Long> {

	List<KrenaiPayment> findBySupplier(Supplier supplier);

	List<KrenaiPayment> findByPaymentDateAfter(Date date);

	List<KrenaiPayment> findBySupplier(Supplier supplier, Sort sort);

}
