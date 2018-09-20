package com.bugfree.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.address.Address;
import com.bugfree.repository.address.AddressRepository;

@Service
@Transactional
public class AddressService {

	@Autowired 
	private AddressRepository addressRepository;
	
	public Address findOne(int addressId){
		return addressRepository.findOne(addressId);
	}

	public Address save(Address address) {

		return addressRepository.save(address);
	}
}
