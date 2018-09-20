package com.bugfree.repository.supplier.android;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.android.SupplierLoginSession;

public interface SupplierLoginSessionRepository extends PagingAndSortingRepository<SupplierLoginSession, String> {

	List<SupplierLoginSession> findBySupplierAndStatus(Supplier supplier, Status status);

	SupplierLoginSession findBySessionIdAndStatus(String sessionId, Status activeStatus);

}
