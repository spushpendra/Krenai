package com.bugfree.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.user.User;
import com.bugfree.repository.user.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findOne(int i) {
		return userRepository.findOne(i);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User findByEmailId(String currentUser) {
		return userRepository.findByEmailId(currentUser);
	}

	public List<User> findNearestUserNotInFollowList(Double latitude, Double longitude, int nearestDistance,
			int supplierId) {
		// TODO Auto-generated method stub
		return userRepository.findNearestUserNotInFollowList(latitude, longitude, nearestDistance,supplierId);
	}
	public List<User> findNearestUserNotInFollowListUserGt(Double latitude, Double longitude, int nearestDistance,
			int supplierId, int userId) {
		// TODO Auto-generated method stub
		return userRepository.findNearestUserNotInFollowListUserGt(latitude, longitude, nearestDistance,supplierId, userId);
	}

}
