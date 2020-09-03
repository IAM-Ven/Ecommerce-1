package com.tmn.ecommerce.dto;

import java.util.List;

import com.tmn.ecommerce.entity.Category;

public class CategoryResponse {

	private String status;
	private String message;
	private List<Category> oblist;

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

	public List<Category> getOblist() {
		return oblist;
	}

	public void setOblist(List<Category> list) {
		this.oblist = list;
	}

	@Override
	public String toString() {
		return "CategoryResponse [status=" + status + ", message=" + message 
				+ ", oblist=" + oblist + "]";
	}

}
