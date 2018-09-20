package com.bugfree.repository.supplier;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.supplier.SupplierActivityMonitor;

public interface SupplierActivityMonitorRepository extends
		PagingAndSortingRepository<SupplierActivityMonitor, Integer> {

}
