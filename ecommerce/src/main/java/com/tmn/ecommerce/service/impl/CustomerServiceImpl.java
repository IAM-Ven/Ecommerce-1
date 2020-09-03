package com.tmn.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmn.ecommerce.dao.CustomerJpaRepository;
import com.tmn.ecommerce.entity.Customer;
import com.tmn.ecommerce.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerJpaRepository customerRepo;
	
	@Override
	public List<Customer> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public void deleteCategoryById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean existsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
