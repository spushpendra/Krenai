package com.bugfree.service.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.repository.supplier.SupplierRepository;

@Service
@Transactional
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;
	
	
	public Supplier findByEmailId(String userEmail) {
		return supplierRepository.findByEmailId (userEmail);
	}


	public Supplier save(Supplier newSupplier) {
		return supplierRepository.save(newSupplier);
	}


	public List<Object> findSocialCount(Supplier supplier, int nearestDistance) {
		// TODO Auto-generated method stub
		return supplierRepository.findSocialCount(
				supplier.getSupplierId(),
				supplier.getSupplierId(),
				supplier.getAddress().getLatitude(),
				supplier.getAddress().getLongitude(),
				nearestDistance,
				supplier.getAddress().getLatitude(),
				supplier.getAddress().getLongitude(),
				nearestDistance
				);
	}


	public Supplier findOne(int supplierId) {
		// TODO Auto-generated method stub
		return supplierRepository.findOne(supplierId);
	}


	public Supplier findByUniqueStoreName(String address) {
		// TODO Auto-generated method stub
		return supplierRepository.findByUniqueStoreName(address);
	}


	public List<Supplier> findByUniqueStoreNameIn(String[] strArray) {
		// TODO Auto-generated method stub
		return supplierRepository.findByUniqueStoreNameIn(strArray);
	}

}
