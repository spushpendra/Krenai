package com.bugfree.repository.supplier.product;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.product.Product;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.product.SellerProductListings;

public interface SellerProductListingsRepository extends
		PagingAndSortingRepository<SellerProductListings, Integer> {

	Iterable<SellerProductListings> findBySupplier(Supplier supplier);

	Iterable<SellerProductListings> findBySupplierAndStatus(Supplier supplier,
			Status status);

	Iterable<SellerProductListings> findBySupplierAndAvailableQuantityLessThan(
			Supplier supplier, int i);

	Iterable<SellerProductListings> findBySupplierAndAvailableQuantityGreaterThan(
			Supplier supplier, int i);

	Iterable<SellerProductListings> findBySupplierAndAvailableQuantityLessThanAndAvailableQuantityGreaterThan(
			Supplier supplier, int i, int j);

	Iterable<SellerProductListings> findBySupplierAndStatusIn(
			Supplier supplier, List<Status> status);

	
	Page<SellerProductListings> findBySupplier(Supplier supplier, Pageable pageable);
	Page<SellerProductListings> findBySupplierAndStatus(Supplier supplier, Status activeStatus, Pageable pageable);

	@Query(value = "SELECT a.supplier_id, (SELECT COUNT(*) FROM seller_product_listings WHERE available_quantity>0 and supplier_id=a.supplier_id ) as available_qty,(SELECT COUNT(*) FROM seller_product_listings WHERE available_quantity=0 and supplier_id=a.supplier_id) as out_of_stock_qty, (SELECT COUNT(*) FROM seller_product_listings WHERE status_id=? and supplier_id=a.supplier_id) as blocked_by_krenai  from supplier a where supplier_id=? ;", nativeQuery=true)
    Object findAvailableQtyCountBySupplierId(int statusId, int supplierId);

	@Query(value = "select * from seller_product_listings where supplier_id=? and status_id=? and seller_product_listing_id>?  limit 10;", nativeQuery=true)
    List<SellerProductListings> findBySupplierAndStatusAndSupplierIdGreaterThanLimit10(int supplierId, int statusId,
			int index);

	@Query(value = "select * from seller_product_listings where supplier_id=? and status_id=? and seller_product_listing_id<? order by seller_product_listing_id desc limit 10;", nativeQuery=true)
    List<SellerProductListings> findBySupplierAndStatusAndSupplierIdGreaterThanLimit10OrderBySPLI_ID(int supplierId, int statusId,
			int index);

	SellerProductListings findBySupplierAndProduct(Supplier supplier, Product product);

	SellerProductListings findBySupplierAndSellerProductListingId(Supplier supplier, int productListId);

	Iterable<SellerProductListings> findBySupplierAndAvailableQuantityBetween(
			Supplier supplier, int i, int j);

	@Query(value = "select P.product_id, P.product_name, P.packaged_quantity, P.packaged_unit, B.brand_name, PI.image_path1, SPL.seller_product_listing_id , C.category_name, SC.sub_category_name from product P left join seller_product_listings SPL on SPL.product_id=P.product_id and SPL.supplier_id=?1 left join product_image PI on PI.product_image_id=P.image_id left join brand B on B.brand_id=P.brand_id left join category C on C.category_id=P.category_id left join sub_category SC on SC.sub_category_id = P.sub_category_id where P.product_id in (?2);", nativeQuery=true)
    List<Object> findSearchReasultsWithSplExisting(int supplierId, Set<Integer> set);
	
}
