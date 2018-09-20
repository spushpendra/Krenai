package com.bugfree.repository.social.network;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.social.network.UserNeighbour;
import com.bugfree.model.user.User;

public interface UserNeighbourRepository extends PagingAndSortingRepository<UserNeighbour, Integer> {

	
	@Query(value="SELECT * FROM user_neighbour un1 where un1.neighbour2_id=? union select * from user_neighbour un2 where un2.neighbour1_id=?;", nativeQuery=true)
	Iterable<UserNeighbour> findNearestNeignbourNotInUserFollow(int userId, int userid);

	UserNeighbour findByNeighbour1AndNeighbour2(User user, User neighbour2);

	UserNeighbour findByNeighbour2AndNeighbour1(User neighbour2, User user);
	
	//@Modifying(clearAutomatically = true)
	@Query(value="select * from user_neighbour  where (neighbour1_id=? and neighbour2_id=?) or (neighbour1_id=? and neighbour2_id=?);", nativeQuery=true)
	UserNeighbour findByNeighbour1AndNeighbour2OrNeighbour2AndNeighbour1( User user, User neighbour2, User neighbour22, User user2);

	@Query(value="SELECT count(*) as neighbourCount FROM user_neighbour where (neighbour1_id=? ) or (neighbour2_id=?);",nativeQuery=true)
	Object findFriendCount(int un, int un2);

}
