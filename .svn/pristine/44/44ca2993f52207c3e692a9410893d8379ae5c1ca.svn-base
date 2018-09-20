package com.bugfree.service.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.unit.Unit;
import com.bugfree.repository.unit.UnitRepository;

@Service
@Transactional
public class UnitService {
	
	@Autowired
	private UnitRepository unitRepository;
	public Iterable<Unit> findAll() {
		return unitRepository.findAll();
	}
	public Unit findOne(int parseInt) {
		return unitRepository.findOne(parseInt);
	}

}
