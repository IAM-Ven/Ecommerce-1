package com.tmn.ecommerce.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmn.ecommerce.dao.CustomerJpaRepository;
import com.tmn.ecommerce.entity.Customer;
import com.tmn.ecommerce.security.KeyGenerator;
import com.tmn.ecommerce.service.CustomerService;
import com.tmn.ecommerce.utility.MyPhoneNumber;
import com.tmn.ecommerce.utility.Operator;

@Service
public class CustomerServiceImpl implements CustomerService {

	public static final String MM_PHONE_NUMBER = "^((09|\\+?950?9|\\+?95950?9)\\d{7,9})$";
	
	@Autowired
	CustomerJpaRepository customerRepo;
	
	@Override
	public List<Customer> getAllCustomers() {
		return this.customerRepo.findAll();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		UUID key = KeyGenerator.generateType1UUID();
		customer.setUuid(key);
		return customerRepo.save(customer);
	}

	@Override
	public void deleteCategoryById(long id) {
		this.customerRepo.deleteById(id);
	}

	@Override
	public Boolean existsByName(String name) {
		return null;
	}

	@Override
	public boolean isMyanmarPhoneNumber(String phone_no) {
		return (phone_no != null) ? phone_no.matches(MM_PHONE_NUMBER) :  false;
	}
	
	@Override
	public String getOperatorName(String phone_no) {
		if (phone_no == null) {
			throw new NullPointerException();
		}
		String operatorName = UNKNOWN;
		if (isMyanmarPhoneNumber(phone_no)) {
			for (Operator operator : Operator.values()) {
				if (phone_no.matches(operator.getRegex())) {
					operatorName = operator.getName();
					break;
				}
			}
		}
		return operatorName;
	}
	
}
