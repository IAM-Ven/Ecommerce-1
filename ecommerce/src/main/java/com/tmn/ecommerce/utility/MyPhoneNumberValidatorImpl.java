package com.tmn.ecommerce.utility;

public class MyPhoneNumberValidatorImpl implements MyPhoneNumberValidator{

	public static final String MM_PHONE_NUMBER = "^((09|\\+?950?9|\\+?95950?9)\\d{7,9})$";
	
	@Override
	public boolean isMyanmarPhoneNumber(String phoneNumber) {
		return (phoneNumber != null) ? phoneNumber.matches(MM_PHONE_NUMBER) :  false;
	}

}
