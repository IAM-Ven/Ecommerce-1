package com.tmn.ecommerce.dto;

import java.util.List;

import com.tmn.ecommerce.entity.Item;

public class ItemResponse {

	private String status;
	private String message;
	private List<Item> oblist;
	private Item item;

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

	public List<Item> getOblist() {
		return oblist;
	}

	public void setOblist(List<Item> oblist) {
		this.oblist = oblist;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
