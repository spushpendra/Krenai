package com.bugfree.repository.social.feed;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.social.feed.Feeds;
import com.bugfree.model.supplier.Supplier;

public interface FeedsRepository extends PagingAndSortingRepository<Feeds, Integer> {

	
	@Query(value = "SELECT a.* FROM feeds as a where  a.feeds_id<=? and  a.published_by_user_id in ( SELECT b.neighbour1_id FROM user_neighbour as b where b.neighbour2_id=? and status_value='friend' ) or a.published_by_user_id in ( SELECT c.neighbour2_id FROM user_neighbour as c where c.neighbour1_id=?  and status_value='friend' ) or  a.published_by_user_id = ?  order by a.feeds_id desc limit ? ;", nativeQuery=true)
	List<Feeds> findFeedList(int feedsBeforeId, int userId, int userId2,  int userId3, int limit);

	@Query(value = "SELECT a.* FROM feeds as a where  a.feeds_id<=(select count(*) from feeds)  and  a.published_by_user_id in ( SELECT b.neighbour1_id FROM user_neighbour as b where b.neighbour2_id=? and status_value='friend' ) or a.published_by_user_id in ( SELECT c.neighbour2_id FROM user_neighbour as c where c.neighbour1_id=?  and status_value='friend' ) or  a.published_by_user_id = ?  order by a.feeds_id desc limit ? ;", nativeQuery=true)
	List<Feeds> findFeedListFromLastFeed( int userId, int userId2,  int userId3, int limit);

	@Query(value = "SELECT a.* FROM feeds as a where  a.feeds_id<=(select count(*) from feeds) and  a.published_by_user_id = ?  order by a.feeds_id desc limit ? ;", nativeQuery=true)
	List<Feeds> findPersonalFeedListFromLastFeed( int userId, int limit);

	@Query(value = "SELECT a.* FROM feeds as a where  a.feeds_id<=? and a.published_by_user_id = ?  order by a.feeds_id desc limit ? ;", nativeQuery=true)
	List<Feeds> findPersonalFeedList(int feedsId, int userId, int limit);

	@Query(value = "SELECT a.* FROM feeds as a where  a.feeds_id<=(select count(*) from feeds) and (  a.published_by_user_id=? or a.shared_by_user_id=? ) order by a.feeds_id desc limit 5 ;", nativeQuery=true)
	List<Feeds> findFeedListMyFeeds(int userId, int userId2);

	
	@Query(value="select F.feeds_id,  count(FC.feed_comments_id), count(FL.feed_likes_id), S.full_name, F.image_caption_url, S.image_path, F.image_redirect_url, F.feed_published_timestamp, F.feed_message, F.share_count  from feeds  F left join feed_comments FC on FC.feed_id=F.feeds_id left join feed_likes FL on FL.feed_id=F.feeds_id left join supplier S on S.supplier_id=F.published_by_supplier_id where F.published_by_supplier_id=?  and F.feeds_id<? group by F.feeds_id order by F.feeds_id desc limit 10 ",nativeQuery=true)
	List<Object> findByPublishedBySupplierSupplierId(int supplierId, int feedsBefore);
	
	@Query(value="select F.feeds_id,  count(FC.feed_comments_id), count(FL.feed_likes_id), S.full_name, F.image_caption_url, S.image_path, F.image_redirect_url, F.feed_published_timestamp, F.feed_message, F.share_count  from feeds  F left join feed_comments FC on FC.feed_id=F.feeds_id left join feed_likes FL on FL.feed_id=F.feeds_id left join supplier S on S.supplier_id=F.published_by_supplier_id where F.published_by_supplier_id=? group by F.feeds_id order by F.feeds_id desc limit 10 ",nativeQuery=true)
	List<Object> findByPublishedBySupplierSupplierIdDesc(int supplierId);

	@Query(value = "SELECT * from feeds where published_by_supplier_id=? order by feeds_id desc limit 3", nativeQuery=true)
	List<Feeds> findTop3ByPublishedBySupplierOrderByFeedsIdDesc(
			Supplier supplier);
	
//	@Query(value = "SELECT a.* FROM feeds as a where a.published_by_user_id in ( SELECT b.neighbour1_id FROM user_neighbour as b where b.neighbour2_id=? )or a.published_by_user_id in(SELECT c.neighbour2_id FROM user_neighbour as c where c.neighbour1_id=?) order by a.feeds_id desc;", nativeQuery=true)
//	Page<Feeds> findFeedListPage(PageRequest pageRequest,int userId, int userId2);

}
