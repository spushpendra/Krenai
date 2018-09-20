package com.bugfree.service.supplier.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.product.Product;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.product.SellerProductListings;
import com.bugfree.repository.supplier.product.SellerProductListingsRepository;

@Service
@Transactional
public class SellerProductListingsService {

	@Autowired
	private SellerProductListingsRepository sellerProductListingsRepository; 
	
	public SellerProductListings save(
			SellerProductListings sellerProductListings) {
		return sellerProductListingsRepository.save(sellerProductListings);
	}

	public Iterable<SellerProductListings> findBySupplier(Supplier supplier) {
		return sellerProductListingsRepository.findBySupplier(supplier);
	}

	public void delete(int parseInt) {
		
		sellerProductListingsRepository.delete(parseInt);
	}

	public SellerProductListings findOne(int parseInt) {
		// TODO Auto-generated method stub
		return sellerProductListingsRepository.findOne(parseInt);
	}

	public Iterable<SellerProductListings> findBySupplierAndStatus(
			Supplier supplier, Status status) {
		return sellerProductListingsRepository.findBySupplierAndStatus( supplier,  status);
	}

	public Iterable<SellerProductListings> findBySupplierAndAvailableQuantityLessThan(
			Supplier supplier, int i) {
		return sellerProductListingsRepository.findBySupplierAndAvailableQuantityLessThan(supplier, i);
	}

	public Iterable<SellerProductListings> findBySupplierAndAvailableQuantityGreaterThan(
			Supplier supplier, int i) {
		 return sellerProductListingsRepository.findBySupplierAndAvailableQuantityGreaterThan(supplier, i);
	}

	public Iterable<SellerProductListings> findBySupplierAndAvailableQuantityLessThanAndAvailableQuantityGreaterThan(
			Supplier supplier, int i, int j) {
		
		return sellerProductListingsRepository.findBySupplierAndAvailableQuantityLessThanAndAvailableQuantityGreaterThan(
				 supplier, i,  j);
	}

	public Iterable<SellerProductListings> findBySupplierAndStatusIn(
			Supplier supplier, List<Status> status) {
		return sellerProductListingsRepository.findBySupplierAndStatusIn(
				 supplier,  status);
	}

	public Page<SellerProductListings> findBySupplier(Supplier supplier, Integer pageNo) {
		// TODO Auto-generated method stub
		Pageable pageable =  new PageRequest(pageNo, 10);
		return sellerProductListingsRepository.findBySupplier(supplier,pageable);
	}

	public Page<SellerProductListings> findBySupplierAndStatus(Supplier supplier, Status activeStatus, Integer pageNo) {
		// TODO Auto-generated method stub
		Pageable pageable =  new PageRequest(pageNo, 10);
		return sellerProductListingsRepository.findBySupplierAndStatus(supplier,activeStatus,pageable);
	}

	public Object findAvailableQtyCountBySupplierId(int supplierId, int statusId) {
		// TODO Auto-generated method stub
		return sellerProductListingsRepository.findAvailableQtyCountBySupplierId(statusId, supplierId);
	}

	public List<SellerProductListings> findBySupplierAndStatusAndSupplierIdGreaterThanLimit10(
			Supplier supplier, Status activeStatus, int index) {
		// TODO Auto-generated method stub
		return sellerProductListingsRepository.findBySupplierAndStatusAndSupplierIdGreaterThanLimit10(
				 supplier.getSupplierId(),  activeStatus.getStatusId(),  index);
	}
	public List<SellerProductListings> findBySupplierAndStatusAndSupplierIdGreaterThanLimit10OrderBySPLI_ID(
			Supplier supplier, Status activeStatus, int index) {
		// TODO Auto-generated method stub
		if(index==0){
			return sellerProductListingsRepository.findBySupplierAndStatusAndSupplierIdGreaterThanLimit10OrderBySPLI_ID(
					 supplier.getSupplierId(),  activeStatus.getStatusId(),  999999999);
		}else
			return sellerProductListingsRepository.findBySupplierAndStatusAndSupplierIdGreaterThanLimit10OrderBySPLI_ID(
				 supplier.getSupplierId(),  activeStatus.getStatusId(),  index);
	}

	public SellerProductListings findBySupplierAndProduct(Supplier supplier, Product product) {
		// TODO Auto-generated method stub
		return sellerProductListingsRepository.findBySupplierAndProduct(supplier, product);
	}

	public SellerProductListings findBySupplierAndSellerProductListingId(Supplier supplier, int productListId) {
		// TODO Auto-generated method stub
		return sellerProductListingsRepository.
				findBySupplierAndSellerProductListingId(supplier, productListId);
	}

	public Iterable<SellerProductListings> findBySupplierAndAvailableQuantityBetween(
			Supplier supplier, int i, int j) {
		// TODO Auto-generated method stub
		return sellerProductListingsRepository.findBySupplierAndAvailableQuantityBetween(supplier,i,j);
	}

	
	

	

}
