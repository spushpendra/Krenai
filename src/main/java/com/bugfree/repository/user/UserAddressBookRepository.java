package com.bugfree.repository.user;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.user.UserAddressBook;

public interface UserAddressBookRepository extends
		PagingAndSortingRepository<UserAddressBook, Integer> {

}
