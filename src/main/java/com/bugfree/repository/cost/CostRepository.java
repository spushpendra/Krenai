package com.bugfree.repository.cost;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.cost.Cost;

public interface CostRepository extends PagingAndSortingRepository<Cost, Integer> {

}
