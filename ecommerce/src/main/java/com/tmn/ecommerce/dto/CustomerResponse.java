package com.tmn.ecommerce.dto;

import java.util.List;

import com.tmn.ecommerce.entity.Customer;


public class CustomerResponse {

	private String status;
	private String message;
	private List<Customer> oblist;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Customer> getOblist() {
		return oblist;
	}

	public void setOblist(List<Customer> oblist) {
		this.oblist = oblist;
	}

}
