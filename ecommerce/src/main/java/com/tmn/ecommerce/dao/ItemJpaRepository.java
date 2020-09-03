package com.tmn.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tmn.ecommerce.entity.Item;

@Repository
public interface ItemJpaRepository extends JpaRepository<Item, Long>{

	@Query(value = "Select * FROM Item i "
			+"inner join Category c WHERE i.category_id = c.id "
			+"and i.item_code LIKE ?1%",nativeQuery=true)
	Item findByItemCodeLike(@Param("itemcode")String item_code);
	
	@Query(value = "Select * from Item i "+
			"inner join Category c "+
			"where i.category_id = c.id",nativeQuery=true)
	List<Item> findByItems();
	
	@Query(value = "Select * FROM Item i "
			+"inner join Category c WHERE i.category_id = c.id "
			+"and i.item_code = ?1%",nativeQuery=true)
	Item existsByitem_code(@Param("category_code")String item_code);
	
//	@Query(value = "Select * FROM Item i "
//			+"WHERE i.name = ?1%",nativeQuery=true)
//	Item existsByname(String name);
	
	Boolean existsByname(String name);
}
