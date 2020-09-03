package com.tmn.ecommerce.service;

import java.util.List;

import com.tmn.ecommerce.entity.Customer;

public interface CustomerService {

	List<Customer> getAllCategories();

	Customer createCustomer(Customer customer);

	void deleteCategoryById(long id);

	Boolean existsByName(String name);
}
