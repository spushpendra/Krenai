package com.bugfree.repository.supplierRating.store;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.supplierRating.store.StoreComment;

public interface StoreCommentRepo extends PagingAndSortingRepository<StoreComment, Integer> {

}
