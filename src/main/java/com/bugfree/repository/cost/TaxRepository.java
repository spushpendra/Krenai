package com.bugfree.repository.cost;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.cost.Tax;

public interface TaxRepository extends PagingAndSortingRepository<Tax, Integer> {

}
