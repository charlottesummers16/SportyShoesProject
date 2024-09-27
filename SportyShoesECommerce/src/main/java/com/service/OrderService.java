package com.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Orders;
import com.entity.Shoe;
import com.repository.OrdersRepository;
import com.repository.ShoeRepository;

@Service
public class OrderService {
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	ShoeRepository shoeRepository;
	
	public List<Orders> findAllOrders() {
		return ordersRepository.findAll();
	}
	
	public boolean createOrder(Orders order) {
		try {
			if (shoeRepository.existsById(order.getShoeId())) {
				Shoe shoe = shoeRepository.findById(order.getShoeId()).get();
				if (shoe.getQuantityInStock() < order.getQuantityRequired()) {
					return false;
				} else {
					shoe.setQuantityInStock(shoe.getQuantityInStock() - order.getQuantityRequired());
					shoeRepository.saveAndFlush(shoe);
					order.setOrderDateTime(LocalDateTime.now());
					ordersRepository.save(order);
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Order could not be placed");
			return false;
		}
	}
	
	public List<Orders> findOrdersByEmailId(String emailId) {
		return ordersRepository.getOrdersByEmailId(emailId);
	}

	
	public List<Orders> findOrdersByShoeId(int shoeId) {
		return ordersRepository.getOrdersByShoeId(shoeId);
	}

	
	public List<Orders> findOrdersByDate(LocalDateTime startDate, LocalDateTime endDate) {
		return ordersRepository.findAllByOrderDateTimeBetween(startDate, endDate);
	}

}
