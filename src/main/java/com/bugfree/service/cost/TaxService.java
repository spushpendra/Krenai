package com.bugfree.service.cost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.cost.Tax;
import com.bugfree.repository.cost.TaxRepository;

@Service
@Transactional
public class TaxService {
	
	@Autowired
	private TaxRepository taxRepository;
	public Iterable<Tax> findAll() {
		return taxRepository.findAll();
	}

}
