package com.tmn.ecommerce.utility;

import org.springframework.stereotype.Component;

@Component
public class MyPhoneNumber {

	public final String UNKNOWN = "unknown";
	
	private MyPhoneNumberValidator myPhoneNumberValidator;
	
	public MyPhoneNumber() {
		this.myPhoneNumberValidator = new MyPhoneNumberValidatorImpl();
	}
	
	public String getOperatorName(String phone_no) {
		if (phone_no == null) {
			throw new NullPointerException();
		}
		String operatorName = UNKNOWN;
		if (myPhoneNumberValidator.isMyanmarPhoneNumber(phone_no)) {
			for (Operator operator : Operator.values()) {
				if (phone_no.matches(operator.getRegex())) {
					operatorName = operator.getName();
				}
			}
		}
		return operatorName;
	}
}
