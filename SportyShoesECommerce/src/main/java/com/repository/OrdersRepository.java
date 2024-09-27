package com.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	@Query("select o from Orders o where o.emailId = :emailId")
	public List<Orders> getOrdersByEmailId(@Param("emailId") String emailId);

	@Query("select o from Orders o where o.shoeId = :shoeId")
	public List<Orders> getOrdersByShoeId(@Param("shoeId") int shoeId);
	
	List<Orders> findAllByOrderDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
