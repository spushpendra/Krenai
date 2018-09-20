package com.bugfree.repository.supplier;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.supplier.Supplier;

public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Integer> {

	Supplier findByEmailId(String userEmail);

	@Query(value="select count(*), 'follow' as count_for from user_follow UF where UF.supplier_id=? union all select count(*), 'feeds' as count_for from feeds F where F.published_by_supplier_id=?  union all select count(distinct U.user_id), 'users' as count_for from user  U where U.email_id in (select UAB.user_email_id  from user_address_book UAB where get_distance(?, ?, UAB.latitude, UAB.longitude) < ?) union all select count(*), 'suppliers' as count_for from supplier S left join address A on A.address_id=S.address_id where get_distance(?, ?, A.latitude, A.longitude)<? ;", nativeQuery=true)
	List<Object> findSocialCount(int supplierId, int supplierId2, Double latitude, Double longitude,
			int nearestDistance, Double latitude2, Double longitude2, int nearestDistance2);

	Supplier findByUniqueStoreName(String address);

	List<Supplier> findByUniqueStoreNameIn(String[] strArray);

}
