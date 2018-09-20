package com.bugfree.service.status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.status.Status;
import com.bugfree.repository.status.StatusRepository;

@Service
@Transactional
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	public Iterable<Status> findAll() {
		return statusRepository.findAll();
	}
	public Status findByStatusValue(String string) {
		return statusRepository.findByStatusValue(string);
	}
	
	public List<Status> findByStatusValueIn(List<String> statusValueList) {
		return statusRepository.findByStatusValueIn(statusValueList);
	}
	public Iterable<Status> findByStatusValueIn(String[] statusArray) {
		return statusRepository.findByStatusValueIn(statusArray);
	}
	public Status findOne(int parseInt) {
		// TODO Auto-generated method stub
		return statusRepository.findOne(parseInt);
	}

}
