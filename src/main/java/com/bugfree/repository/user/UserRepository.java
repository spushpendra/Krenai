package com.bugfree.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.user.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	User findByEmailId(String currentUser);

	@Query(value="select * from user where user_id in (select  distinct user_id  as UI from cart where supplier_id=?1 and status_id in (select status_id from status where status_value in ( ?2)) );", nativeQuery= true)
	List<User> findBySupplierAndStatusIn(Supplier supplier, String[] strArray);

	@Query(value="select * from user where email_id in (select UAB.user_email_id from user_address_book UAB where get_distance(?,? , UAB.latitude, UAB.longitude) < ?);", nativeQuery= true)
	List<User> findNearestUserFromSupplier(Double latitude, Double longitude, int nearestDistance);

	@Query(value="select U.* from user U left join user_address_book as UAB on UAB.user_address_book_id = U.default_user_addressbook_id where get_distance(UAB.latitude, UAB.longitude, ?, ?) <? and U.user_id not in (select UF.user_id from user_follow as UF where  UF.supplier_id=? and UF.status_value='following') limit 10;", nativeQuery= true)
	List<User> findNearestUserNotInFollowList(Double latitude, Double longitude, int nearestDistance, int supplierId);

	@Query(value="select U.* from user U left join user_address_book as UAB on UAB.user_address_book_id = U.default_user_addressbook_id where get_distance(UAB.latitude, UAB.longitude, ?, ?) <? and U.user_id not in (select UF.user_id from user_follow as UF where  UF.supplier_id=? and UF.status_value='following') and U.user_id>? limit 10;", nativeQuery= true)
	List<User> findNearestUserNotInFollowListUserGt(Double latitude, Double longitude, int nearestDistance, int supplierId, int userId);

}
