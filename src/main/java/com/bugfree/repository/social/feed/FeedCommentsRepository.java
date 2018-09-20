package com.bugfree.repository.social.feed;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.social.feed.FeedComments;
import com.bugfree.model.social.feed.Feeds;

public interface FeedCommentsRepository extends PagingAndSortingRepository<FeedComments, Integer> {

	
	List<FeedComments> findByFeeds(Feeds fd);

}
