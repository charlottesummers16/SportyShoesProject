package com.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Orders;
import com.entity.Shoe;
import com.entity.User;
import com.service.OrderService;
import com.service.ShoeService;
import com.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	ShoeService shoeService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "admin")
	public String showAdminPage(Model mm, Shoe shoe) {
		//mm.addAttribute("shoeSubmitButtonName", "Add shoe");
		displayAll(shoe, mm);
		mm.addAttribute("shoe", shoe);
		
		List<Orders> orders = orderService.findAllOrders();
		mm.addAttribute("orders", orders);
		mm.addAttribute("orderMessage", "Showing all orders");

		return "admin";
	}
	
	@GetMapping(value = "searchShoesByBrandAndType")
	public String searchShoesByBrand(Model mm, Shoe shoe, @RequestParam("shoeBrandSearch") String brand, @RequestParam("shoeTypeSearch") String type) {
		mm.addAttribute("shoeSubmitButtonName", "Add shoe");
		List<Shoe> foundShoes = shoeService.findAllShoes();
		mm.addAttribute("shoeMessage", "Showing all shoes");
		
		if (!brand.isBlank() || !type.isBlank()) {
			foundShoes = shoeService.findShoesBySearch(brand, type);
			mm.addAttribute("shoeMessage", "Showing shoes for search criteria: brand=" + brand + "; type=" + type);
		}
		
		System.out.println("Shoes found " + shoeService.findShoesBySearch(brand, type).toString());
		mm.addAttribute("shoes", foundShoes);
		return "admin";
	}
	
	@PostMapping(value = "saveShoe")
	public String addShoe(Shoe shoe, Model mm, @RequestParam("shoeSubmitButton") String buttonValue) {
		if (buttonValue.equals("Add shoe")) {
			String saveResult = shoeService.createShoe(shoe);
			mm.addAttribute("addShoeMessage", saveResult);
		} else {
			shoeService.updateShoe(shoe);
		}
		displayAll(shoe, mm);
		mm.addAttribute("shoeSubmitButtonName", "Add shoe");
		mm.addAttribute("shoe", new Shoe());
		return "admin";
	}
	
	@GetMapping(value = "deleteShoe")
	public String deleteShoe(Shoe shoe, Model mm, @RequestParam("shoeId") int shoeId) {
		System.out.println("Delete method called");
		shoeService.deleteShoe(shoeId);
		mm.addAttribute("shoe", new Shoe());
		mm.addAttribute("shoeSubmitButtonName", "Add shoe");
		displayAll(shoe, mm);
		return "admin";
	}
	
	@GetMapping(value = "updateShoeInfo")
	public String findShoeInfo(Shoe shoe, Model mm, @RequestParam("shoeId") int shoeId) {
		shoe = shoeService.findShoeById(shoeId);
		mm.addAttribute("shoe", shoe);
		displayAll(shoe, mm);
		mm.addAttribute("shoeSubmitButtonName", "Update shoe");
		return "admin";
	}
	
	private void displayAll(Shoe shoe, Model mm) {
		List<Shoe> shoes = shoeService.findAllShoes();
		mm.addAttribute("shoes", shoes);
		mm.addAttribute("shoeMessage", "Showing all shoes");
		mm.addAttribute("shoeSubmitButtonName", "Add shoe");

		List<Orders> orders = orderService.findAllOrders();
		mm.addAttribute("orders", orders);
		mm.addAttribute("orderMessage", "Showing all orders");
		
		List<User> users = userService.findAllUsers();
		mm.addAttribute("users", users);
		mm.addAttribute("userMessage", "Showing all users");
	}
	
	
	
	@GetMapping(value = "orders")
	public String getAllOrders(Shoe shoe, Model mm) {
		List<Orders> orders = orderService.findAllOrders();
		mm.addAttribute("orders", orders);
		mm.addAttribute("orderMessage", "Showing all orders");
		return "admin";
	}
	
	@GetMapping(value = "findOrdersByEmailId")
	public String getOrdersByEmailId(Shoe shoe, Model mm, @RequestParam("emailIdOrderSearch") String emailId) {
		displayAll(shoe, mm);
		List<Orders> orders = orderService.findAllOrders();
		mm.addAttribute("orderMessage", "Showing all orders");
		mm.addAttribute("orders", orders);
		if (!emailId.isBlank()) {
			orders = orderService.findOrdersByEmailId(emailId);
			mm.addAttribute("orders", orders);
			mm.addAttribute("orderMessage", "Showing all orders for " + emailId);
		}
		return "admin";
	}

	@GetMapping(value = "findOrdersByShoeId")
	public String getOrdersByShoeId(Shoe shoe, Model mm, @RequestParam("shoeIdOrderSearch") int shoeId) {
		displayAll(shoe, mm);
		List<Orders> orders = orderService.findAllOrders();
		mm.addAttribute("orderMessage", "Showing all orders");
		mm.addAttribute("orders", orders);
		
			orders = orderService.findOrdersByShoeId(shoeId);
			mm.addAttribute("orders", orders);
			mm.addAttribute("orderMessage", "Showing all orders for shoe ID " + shoeId);
			
		return "admin";
	}

	@GetMapping(value = "findOrdersByDate")
	public String getOrdersByDate(Shoe shoe, Model mm, @RequestParam("orderStart") LocalDateTime startDate, @RequestParam("orderEnd") LocalDateTime endDate) {
		displayAll(shoe, mm);
			List<Orders> orders = orderService.findOrdersByDate(startDate, endDate);
			mm.addAttribute("orders", orders);
			mm.addAttribute("orderMessage", "Showing orders for selected date range");
		return "admin";
	}
	
	@GetMapping(value = "findUserByEmailId")
	public String getUserByEmailId(Shoe shoe, Model mm, @RequestParam("findUserByEmailId") String emailId) {
		displayAll(shoe, mm);
		List<User> users = userService.findAllUsers();
		mm.addAttribute("userMessage", "Showing all users");
		mm.addAttribute("users", users);
		if (!emailId.isBlank()) {
			users = userService.findUserByEmailId(emailId);
			mm.addAttribute("users", users);
			mm.addAttribute("userMessage", "Showing user for " + emailId);
		}
		return "admin";
	}
	

}
