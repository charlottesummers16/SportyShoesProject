package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.Shoe;

public interface ShoeRepository extends JpaRepository<Shoe, Integer>{

	@Query("select s from Shoe s where s.brand = :brand")
	public List<Shoe> findShoeByBrand(@Param("brand") String brand);

	@Query("select s from Shoe s where s.type = :type")
	public List<Shoe> findShoeByType(@Param("type") String type);
	
	@Query("select s from Shoe s where s.brand = :brand and s.type = :type")
	public List<Shoe> findShoeByBrandAndType(@Param("brand") String brand, @Param("type") String type);
}
