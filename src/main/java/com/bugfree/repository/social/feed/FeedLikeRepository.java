package com.bugfree.repository.social.feed;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.social.feed.FeedLikes;
import com.bugfree.model.social.feed.Feeds;

public interface FeedLikeRepository extends PagingAndSortingRepository<FeedLikes, Integer> {

	
	FeedLikes findByFeedsAndUserId(Feeds feeds, int userId);

}
