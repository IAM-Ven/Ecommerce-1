package com.tmn.ecommerce.utility;

public enum Operator {

	OOREDOO("Ooredoo","^(09|\\+?959)9(5|7|6)\\d{7}$"),
	TELENOR("Telenor","^(09|\\+?959)7([5-9])\\d{7}$"),
	MYTEL("Mytel","^(09|\\+?959)6(8|9)\\d{7}$"),
	MPT("MPT","^(09|\\+?959)(5\\d{6}|4\\d{7,8}|2\\d{6,8}|3\\d{7,8}|6\\d{6}|8\\d{6}|7\\d{7}|9(0|1|9)\\d{5,6}|2[0-4]\\d{5}|5[0-6]\\d{5}|8[13-7]\\d{5}|3[0-369]\\d{6}|34\\d{7}|4[1379]\\d{6}|73\\d{6}|91\\d{6}|25\\d{7}|26[0-5]\\d{6}|40[0-4]\\d{6}|42\\d{7}|45\\d{7}|89[6789]\\d{6}|)$");
	
	private String name;
	private String regex;

	private Operator(String name, String regex) {
		this.name = name;
		this.regex = regex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	
}
