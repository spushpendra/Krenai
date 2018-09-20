package com.bugfree.service.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.unit.MeasuredValue;
import com.bugfree.repository.unit.MeasuredValueRepository;

@Service
@Transactional
public class MeasuredValueService {

	@Autowired
	private MeasuredValueRepository measuredValueRepository;
	public MeasuredValue save(MeasuredValue measuredValue) {
		return measuredValueRepository.save(measuredValue);
	}

}
