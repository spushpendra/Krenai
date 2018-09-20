package com.bugfree.service.inventory.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugfree.model.inventory.store.StoreFeature;
import com.bugfree.repository.inventory.store.StoreFeatureRepository;

@Service
public class StoreFeatureService {

	@Autowired
	private StoreFeatureRepository storeFeatureRepository;
	public StoreFeature save(StoreFeature storeFeature) {
		return storeFeatureRepository.save(storeFeature);
	}

	
}
