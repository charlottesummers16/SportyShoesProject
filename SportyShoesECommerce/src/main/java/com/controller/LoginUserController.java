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
import com.entity.UserChangePassword;
import com.service.OrderService;
import com.service.ShoeService;
import com.service.UserService;

@Controller
public class LoginUserController {
	
	@Autowired
	UserService userService;
	@Autowired
	ShoeService shoeService;
	@Autowired
	OrderService orderService;

	@GetMapping(value = {"/", "", "login"})
	public String showLoginPage(User user, Model mm, Shoe shoe, UserChangePassword userChangePassword) {
		return "login";
	}
	
	@PostMapping(value = "login")
	public String login(User user, Model mm, Shoe shoe, Orders oo, OrderForm of, UserChangePassword userChangePassword) {
		User loginRequest = userService.login(user.getEmailId(), user.getPassword());
		if (loginRequest.getEmailId() == null) {
			System.out.println("User is empty");
			mm.addAttribute("loginMessage", "User not found, please check your details or create an account");
			user.setEmailId(null);
			user.setPassword(null);
			return "login";
		}
		List<Shoe> shoes = shoeService.findAllShoes();
		mm.addAttribute("shoes", shoes);
		mm.addAttribute("orders", oo);
		mm.addAttribute("orderForm", of);
		if (loginRequest.isAdmin()) {
			System.out.println("User is admin");
			mm.addAttribute("shoeSubmitButtonName", "Add shoe");
			mm.addAttribute("shoe", shoe);
			List<Orders> orders = orderService.findAllOrders();
			mm.addAttribute("orders", orders);
			return "admin";
		} else {
			System.out.println("User is customer");
			List<Orders> orders = orderService.findOrdersByEmailId(user.getEmailId());
			mm.addAttribute("orders", orders);
			mm.addAttribute("user", user);
			System.out.println("login "+ user.toString());
			return "customer";
		}
	}
	
	@PostMapping(value = "changePassword")
	public String changePassword(User user, Model mm, Shoe shoe, UserChangePassword userChangePassword) {
		mm.addAttribute("changePasswordMessage", "Password could not be changed");
		if (userService.updateUserPassword(userChangePassword)) {
			mm.addAttribute("changePasswordMessage", "Password changed successfully");
		}
		return "login";
	}
	

}
