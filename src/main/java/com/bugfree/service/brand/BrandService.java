package com.bugfree.service.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.brand.Brand;
import com.bugfree.repository.brand.BrandRepository;

@Service
@Transactional
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;
	public Brand save(Brand brand) {
		return brandRepository.save(brand);
	}
	public Iterable<Brand> findAll() {
		// TODO Auto-generated method stub
		return brandRepository.findAll();
	}
	public Brand findByBrandName(String brandInput) {
		// TODO Auto-generated method stub
		return brandRepository.findByBrandName(brandInput);
	}

}
