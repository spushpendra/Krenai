package com.bugfree.repository.location;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.location.Location;

public interface LocationRepository extends PagingAndSortingRepository<Location, Integer> {

}
