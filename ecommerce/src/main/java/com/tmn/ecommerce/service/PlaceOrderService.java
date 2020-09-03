package com.tmn.ecommerce.service;

import com.tmn.ecommerce.entity.User;

public interface PlaceOrderService {

	void saveOrder(User loggedUser);
}
