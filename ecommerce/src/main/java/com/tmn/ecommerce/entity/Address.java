package com.tmn.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	@Column(nullable = false)
	private String no;
	private String room_no;
	private String floor;
	@Column(nullable = false)
	private String street;
	@Column(nullable = false)
	private String quarter;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String state;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getRoom_no() {
		return room_no;
	}

	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Address [no=" + no + ", room_no=" + room_no + ", floor=" + floor + ", street=" + street + ", quarter="
				+ quarter + ", city=" + city + ", state=" + state + "]";
	}

}
