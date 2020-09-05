package com.tmn.ecommerce.utility;

public class MyPhoneNumberTest {

	public static void main(String[] args) {
		MyPhoneNumber phNumber = new MyPhoneNumber();
		phNumber.getOperatorName("09979599035");
	}
	
	
	public static boolean isStringEmpty(String input) {
//		if (input == null || input.equals("")) {
//			return true;
//		}
//		return false;
		return (input == null || input.equals(""))? true :false;
	}
}
