package com.tmn.ecommerce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmn.ecommerce.dto.CustomerResponse;
import com.tmn.ecommerce.entity.Customer;
import com.tmn.ecommerce.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<CustomerResponse> getAllCategories(){
		CustomerResponse resp = new CustomerResponse();
		try {
			resp.setStatus("200");
			resp.setMessage("Category List");
			resp.setOblist(customerService.getAllCategories());
		} catch (Exception e) {
			resp.setStatus("500");
			resp.setMessage(e.getMessage());
		}	
		return new ResponseEntity<CustomerResponse>(resp,HttpStatus.OK);
	}
	
	@PostMapping(produces = "application/json")
	public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Customer customer) {
		CustomerResponse resp = new CustomerResponse();
		try {
			if (customerService.existsByName(customer.getName())) {
				resp.setStatus("400");
				resp.setMessage("Category Already Exists!");
			} else {
				this.customerService.createCustomer(customer);
				resp.setStatus("200");
				resp.setMessage("Category Saved Successfully!");
			}
			resp.setOblist(customerService.getAllCategories());
		} catch (Exception e) {
			System.out.println(e);
			resp.setStatus("500");
			resp.setMessage(e.getMessage());
		}	
		return new ResponseEntity<CustomerResponse>(resp,HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}",produces = "application/json")
	public ResponseEntity<CustomerResponse> updateCategory(@RequestBody Customer customer) {
		CustomerResponse resp = new CustomerResponse();
		try {
			if (customerService.existsByName(customer.getName())) {
				resp.setStatus("400");
				resp.setMessage("Category Already Exists!");
			} else {
				this.customerService.createCustomer(customer);
				resp.setStatus("200");
				resp.setMessage("Category Updated Successfully!");
			}
			resp.setOblist(customerService.getAllCategories());
		} catch (Exception e) {
			resp.setStatus("500");
			resp.setMessage(e.getMessage());
		}
		return new ResponseEntity<CustomerResponse>(resp,HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<CustomerResponse> deleteCategory(@PathVariable("id") Long id) {
		CustomerResponse resp = new CustomerResponse();
        try {
        	this.customerService.deleteCategoryById(id);
			resp.setStatus("200");
			resp.setMessage("Category Deleted Successfully");
			resp.setOblist(customerService.getAllCategories());
		} catch (Exception e) {
			resp.setStatus("500");
			resp.setMessage("This category has items");
		}
        return new ResponseEntity<CustomerResponse>(resp,HttpStatus.OK);
	}
}
