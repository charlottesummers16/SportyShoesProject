package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.entity.OrderForm;
import com.entity.Orders;
import com.entity.Shoe;
import com.entity.User;
import com.service.OrderService;
import com.service.ShoeService;

@Controller
public class CustomerController {

	@Autowired
	ShoeService shoeService;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping(value = "customer")
	public String showCustomerPage(Model mm, Shoe shoe, User user, Orders oo, OrderForm of) {
		System.out.println("showCustomerPage "+ user.toString());
		mm.addAttribute("shoeSubmitButtonName", "Add shoe");
		displayAllShoes(shoe, mm, of, oo);
		mm.addAttribute("shoe", shoe);
		mm.addAttribute("user", user);
		
		List<Orders> orders = orderService.findOrdersByEmailId(user.getEmailId());
		System.out.println(orders);
		mm.addAttribute("orders", orders);

		return "customer";
	}
	
	@PostMapping(value = "createOrder")
	public String createOrder(Model mm, Shoe shoe, User user, OrderForm of, Orders oo) {
		System.out.println("createOrder "+ user.toString());
		mm.addAttribute("user", user);
		oo.setShoeId(of.getShoeId());
		oo.setQuantityRequired(of.getQuantityRequired());
//		User mmUser = (User) mm.getAttribute("user");
		oo.setEmailId(of.getEmailId());
		System.out.println(oo);
		boolean orderCreated = orderService.createOrder(oo);
		if (!orderCreated) {
			// display a message that the order could not be created.
			mm.addAttribute("orderMessage", "Order could not be placed");
		}
		of.setQuantityRequired(0);
		of.setShoeId(0);
		mm.addAttribute("orderForm", of);
		
		displayAllShoes(shoe, mm, of, oo);
		displayAllOrdersByEmailId(user, mm);

		return "customer";
	}

	
//	@GetMapping(value = "orderShoe")
//	public String getShoeInfoForOrder(Shoe shoe, User user, OrderForm of, Model mm, Orders oo, @RequestParam("shoeId") int shoeId) {
//		shoe = shoeService.findShoeById(shoeId);
//		System.out.println("getShoeInfoForOrder "+ user.toString());
//		mm.addAttribute("user", user);
//		displayAllShoes(shoe, mm, of, oo);
//		displayAllOrdersByEmailId(user, mm);
//		of.setShoeId(shoeId);
//		
//		return "customer";
//	}
	
	
	
	private void displayAllShoes(Shoe shoe, Model mm, OrderForm of, Orders oo) {
		List<Shoe> shoes = shoeService.findAllShoes();
		mm.addAttribute("shoes", shoes);
	}
	private void displayAllOrdersByEmailId(User user, Model mm) {
		List<Orders> orders = orderService.findOrdersByEmailId(user.getEmailId());
		mm.addAttribute("orders", orders);
	}
}
