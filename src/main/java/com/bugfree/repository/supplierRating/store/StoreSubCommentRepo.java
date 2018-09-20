package com.bugfree.repository.supplierRating.store;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplierRating.store.StoreComment;
import com.bugfree.model.supplierRating.store.StoreSubComment;
import com.bugfree.model.user.User;

public interface StoreSubCommentRepo extends PagingAndSortingRepository<StoreSubComment, Integer> {

	 List<StoreSubComment> findByStoreCommentAndUser(
	   StoreComment storecomment, User user);

	 @Query(value="select SC.store_sub_comment_id, U.user_id, U.first_name, U.last_name, U.profile_image_url, SC.reply_timestamp,  SC.sub_comment from store_sub_comment SC left join user U on U.user_id=SC.user_id left join store_comment SCL on SCL.store_comment_id=SC.store_comment_id  where SCL.supplier_id=? And SCL.store_comment_id=? group by  SC.store_sub_comment_id, SCL.store_comment_id", nativeQuery=true)
	 Iterable<Object> findBySupplierAndStoreCommentId(Supplier supplier,
	   StoreComment storecomment);
}
