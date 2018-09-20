package com.bugfree.service.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.supplier.SupplierActivityMonitor;
import com.bugfree.repository.supplier.SupplierActivityMonitorRepository;

@Service
@Transactional
public class SupplierActivityMonitorService {

	@Autowired
	private SupplierActivityMonitorRepository samr;
	public SupplierActivityMonitor save(SupplierActivityMonitor supplierActivityMonitor) {
		return samr.save(supplierActivityMonitor);
	}

}
