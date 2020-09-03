package com.tmn.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmn.ecommerce.dao.CategoryJpaRepository;
import com.tmn.ecommerce.entity.Category;
import com.tmn.ecommerce.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryJpaRepository catRepo;

	@Override
	public List<Category> getAllCategories() {
		return this.catRepo.findAll();
	}

	@Override
	public Category createCategory(Category cat) {
		return this.catRepo.save(cat);
	}

	@Override
	public void deleteCategoryById(long id) {
		this.catRepo.deleteById(id);
	}

	@Override
	public Boolean existsByName(String name) {
		return catRepo.existsByname(name);
	}

}
