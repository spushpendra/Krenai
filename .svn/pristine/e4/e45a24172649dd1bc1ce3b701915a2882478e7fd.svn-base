package com.bugfree.service.inventory.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.inventory.store.Store;
import com.bugfree.repository.inventory.store.StoreRepository;

@Service
@Transactional
public class StoreService {

	@Autowired 
	private StoreRepository storeRepository;
	public Iterable<Store> findAll() {
		return storeRepository.findAll();
	}
	public Store findOne(int parseInt) {
		return storeRepository.findOne(parseInt);
	}
	public Store findByStoreType(String parameter) {
		return storeRepository.findByStoreType(parameter);
	}

}
