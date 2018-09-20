package com.bugfree.service.supplier.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugfree.model.supplier.query.Query;
import com.bugfree.repository.supplier.query.QueryRepository;

@Service
public class QueryService {

	@Autowired
	private QueryRepository queryRepository;
	public void save(Query query) {
		queryRepository.save(query);
	}

}
