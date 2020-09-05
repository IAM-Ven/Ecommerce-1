package com.tmn.ecommerce.service;

import java.util.List;

import com.tmn.ecommerce.entity.Customer;

public interface CustomerService {
	
	public final String UNKNOWN = "unknown";

	List<Customer> getAllCustomers();

	Customer createCustomer(Customer customer);

	void deleteCategoryById(long id);

	Boolean existsByName(String name);
	
	boolean isMyanmarPhoneNumber(String phone_no);
	
	String getOperatorName(String phone_no);
}
