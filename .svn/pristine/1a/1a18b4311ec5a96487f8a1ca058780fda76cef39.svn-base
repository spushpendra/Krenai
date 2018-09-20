package com.bugfree.repository.supplierRating.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplierRating.store.StoreComment;
import com.bugfree.model.supplierRating.store.StoreCommentLike;
import com.bugfree.model.user.User;

public interface StoreCommentLikeRepo extends PagingAndSortingRepository<StoreCommentLike, Long> {

	@Query(value="select SC.store_comment_id, U.user_id, U.first_name, U.last_name, U.profile_image_url, SC.created_timestamp, count(distinct SCL.store_comment_like_id) as like_count, SC.`comment`, count(distinct SSC.store_sub_comment_id) as replies from store_comment SC left join store_sub_comment SSC on SSC.store_comment_id = SC.store_comment_id left join user U on U.user_id=SC.user_id left join store_comment_like SCL on SCL.store_comment_id = SC.store_comment_id where SC.supplier_id=? group by SC.store_comment_id order by store_comment_id DESC limit 10", nativeQuery=true)
	 Iterable<Object> findBySupplier(Supplier supplier);

	 @Query(value="select SC.store_comment_id, U.user_id, U.first_name, U.last_name, U.profile_image_url, SC.created_timestamp, count(distinct SCL.store_comment_like_id) as like_count, SC.`comment`, count(distinct SSC.store_sub_comment_id) as replies from store_comment SC left join store_sub_comment SSC on SSC.store_comment_id = SC.store_comment_id left join user U on U.user_id=SC.user_id left join store_comment_like SCL on SCL.store_comment_id = SC.store_comment_id where SC.supplier_id=? and SC.store_comment_id<? group by SC.store_comment_id order by store_comment_id DESC limit 10", nativeQuery=true)
	 Iterable<Object> findBySupplierAndLimitAndStoreCommentIdGT(
	   Supplier supplier, int lastid);

	 StoreCommentLike findByStoreCommentAndUser(StoreComment storecomment, User user);
}
