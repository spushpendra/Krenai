package com.bugfree.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.category.CategoryDescription;
import com.bugfree.repository.category.CategoryDescriptionRepository;

@Service
@Transactional
public class CategoryDescriptionService {

	@Autowired
	private CategoryDescriptionRepository categoryDescriptionRepository;
	public CategoryDescription save(CategoryDescription categoryDescription) {
		
		return categoryDescriptionRepository.save(categoryDescription);
	}

}
