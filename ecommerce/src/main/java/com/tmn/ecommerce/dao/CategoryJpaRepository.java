package com.tmn.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tmn.ecommerce.entity.Category;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Long>{

	@Query(value = "SELECT * FROM Category "
			+"WHERE name LIKE ?1%",nativeQuery=true)
	Category findByNameLike(@Param("name")String name);
	Boolean existsByname(String name);
}
