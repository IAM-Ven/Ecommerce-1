package com.tmn.ecommerce.service;

import java.util.List;

import com.tmn.ecommerce.dto.ItemDto;
import com.tmn.ecommerce.entity.Item;

public interface ItemService {

	List<Item> getAllItems();

	Item createItem(ItemDto dto);

	void deleteItemById(long id);

	Item getItemByItemcode(String itemcode);

	Item getItemByItemId(long itemId);

	Boolean existsByname(String name);

	Boolean existsByItem_code(String item_code);
}
