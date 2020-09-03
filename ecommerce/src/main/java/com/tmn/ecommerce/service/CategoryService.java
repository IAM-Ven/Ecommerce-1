package com.tmn.ecommerce.service;

import java.util.List;

import com.tmn.ecommerce.entity.Category;

public interface CategoryService {

	List<Category> getAllCategories();

	Category createCategory(Category cat);

	void deleteCategoryById(long id);

	Boolean existsByName(String name);
}
