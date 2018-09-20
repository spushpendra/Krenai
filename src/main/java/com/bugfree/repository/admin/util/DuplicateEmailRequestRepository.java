package com.bugfree.repository.admin.util;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.admin.util.DuplicateEmailRequest;

public interface DuplicateEmailRequestRepository extends PagingAndSortingRepository<DuplicateEmailRequest, Integer> {

}
