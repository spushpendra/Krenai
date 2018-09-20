package com.bugfree.repository.address;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.address.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address, Integer> {

}
