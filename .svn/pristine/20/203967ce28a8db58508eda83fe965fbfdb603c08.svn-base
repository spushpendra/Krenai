package com.bugfree.repository.status;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.status.Status;

public interface StatusRepository extends PagingAndSortingRepository<Status, Integer> {

	Status findByStatusValue(String string);

	

	List<Status> findByStatusValueIn(List<String> statusValueList);



	Iterable<Status> findByStatusValueIn(String[] statusArray);

}
