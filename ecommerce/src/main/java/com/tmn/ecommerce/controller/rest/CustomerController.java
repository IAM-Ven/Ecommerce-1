package com.tmn.ecommerce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerResponse> getAllCategories(){
		CustomerResponse resp = new CustomerResponse();
		try {
			resp.setStatus(HttpStatus.OK.toString());
			resp.setMessage("Customer List is here");
			resp.setOblist(customerService.getAllCustomers());
		} catch (Exception e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			resp.setMessage(e.getMessage());
		}	
		return new ResponseEntity<CustomerResponse>(resp,HttpStatus.OK);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Customer customer) {
		CustomerResponse resp = new CustomerResponse();
		try {
			if(customerService.getOperatorName(customer.getPhone_no()).equals(CustomerService.UNKNOWN)) {
				resp.setStatus("400");
				resp.setMessage("Check Your Phone Number!");
			} else {
				this.customerService.createCustomer(customer);
				resp.setStatus(HttpStatus.OK.toString());
				resp.setMessage("Customer Saved Successfully!");
			}
			resp.setOblist(customerService.getAllCustomers());
		} catch (Exception e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			resp.setMessage(e.getMessage());
		}	
		return new ResponseEntity<CustomerResponse>(resp,HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerResponse> updateCategory(@RequestBody Customer customer) {
		CustomerResponse resp = new CustomerResponse();
		try {
			if(customerService.getOperatorName(customer.getPhone_no()).equals(CustomerService.UNKNOWN)) {
				resp.setStatus("400");
				resp.setMessage("Check Your Phone Number!");
			} else {
				this.customerService.createCustomer(customer);
				resp.setStatus(HttpStatus.OK.toString());
				resp.setMessage("Customer Updated Successfully!");
			}
			resp.setOblist(customerService.getAllCustomers());
		} catch (Exception e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			resp.setMessage(e.getMessage());
		}
		return new ResponseEntity<CustomerResponse>(resp,HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerResponse> deleteCategory(@PathVariable("id") Long id) {
		CustomerResponse resp = new CustomerResponse();
        try {
        	this.customerService.deleteCategoryById(id);
			resp.setStatus(HttpStatus.OK.toString());
			resp.setMessage("Customer Deleted Successfully");
			resp.setOblist(customerService.getAllCustomers());
		} catch (Exception e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			resp.setMessage("This customer has orders");
		}
        return new ResponseEntity<CustomerResponse>(resp,HttpStatus.OK);
	}
}
