package com.bugfree.service.subcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.subcategory.SubCategoryDescription;
import com.bugfree.repository.subcategory.SubCategoryDescriptionRepository;

@Service
@Transactional
public class SubCategoryDescriptionService {

	@Autowired
	private SubCategoryDescriptionRepository subCategoryDescriptionRepositiory; 
	public SubCategoryDescription save(
			SubCategoryDescription subcategoryDescription) {
		return subCategoryDescriptionRepositiory.save(subcategoryDescription);
	}

}
