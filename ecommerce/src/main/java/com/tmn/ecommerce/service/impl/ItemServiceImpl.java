package com.tmn.ecommerce.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmn.ecommerce.dao.CategoryJpaRepository;
import com.tmn.ecommerce.dao.ItemJpaRepository;
import com.tmn.ecommerce.dto.ItemDto;
import com.tmn.ecommerce.entity.Category;
import com.tmn.ecommerce.entity.Item;
import com.tmn.ecommerce.security.KeyGenerator;
import com.tmn.ecommerce.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemJpaRepository itemRepo;

	@Autowired
	CategoryJpaRepository categoryJpaRepository;
	
	@Override
	public List<Item> getAllItems() {
		return this.itemRepo.findByItems();
	}

	@Override
	public Item createItem(ItemDto dto) {
		UUID key = KeyGenerator.generateType1UUID();
		Item saveItem = new Item(dto);
		saveItem.setUuid(String.valueOf(key));
		Category category = this.categoryJpaRepository.getOne(dto.getCategory_id());
		saveItem.setCategory(category);
		String saveItemCode = dto.getColor()  + category.getName() + dto.getDescription() + System.currentTimeMillis();
		saveItem.setItem_code(saveItemCode);
		saveItem.setPhotos(dto.getPhotos());
		return this.itemRepo.save(saveItem);
	}

	@Override
	public void deleteItemById(long id) {
		this.itemRepo.deleteById(id);
	}

	@Override
	public Item getItemByItemcode(String itemcode) {
		return this.itemRepo.findByItemCodeLike(itemcode);
	}

	@Override
	public Item getItemByItemId(long itemId) {
		return this.itemRepo.getOne(itemId);
	}

	@Override
	public Boolean existsByname(String name) {
		return itemRepo.existsByname(name);
	}

	@Override
	public Boolean existsByItem_code(String item_code) {
		return itemRepo.existsByitem_code(item_code) != null && itemRepo.existsByitem_code(item_code).getItem_code().equals(item_code);
	}
}
