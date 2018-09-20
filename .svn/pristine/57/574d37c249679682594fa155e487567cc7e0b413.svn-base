package com.bugfree.service.supplier.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.shop.SupplierShop;
import com.bugfree.repository.supplier.shop.SupplierShopRepository;

@Service
@Transactional
public class SupplierShopService {

	@Autowired
	SupplierShopRepository supplierShopRepository; 
	public SupplierShop save(SupplierShop supplierShop) {
		return supplierShopRepository.save(supplierShop);
	}
	

}
