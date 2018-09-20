package com.bugfree.repository.social.network;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.social.network.UserFollow;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.user.User;

public interface UserFollowRepository extends PagingAndSortingRepository<UserFollow, Integer>{

	List<UserFollow> findByUserAndStatusValue(User user, String string);

	UserFollow findByUserAndSupplier(User user, Supplier supplier);
	
	@Query(value="SELECT count(*) FROM krenai.user_follow where user_id=?;", nativeQuery=true)
	Object findFollowingCount(int userId);

	List<UserFollow> findByUser(User user);

	List<UserFollow> findBySupplier(Supplier supplier);

}
