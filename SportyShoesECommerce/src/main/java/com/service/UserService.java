package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.entity.UserChangePassword;
import com.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User login(String emailId, String password) {
		Optional<User> result = userRepository.findById(emailId);
		User user = new User();
		if (result.isPresent()) {
			if (result.get().getPassword().equals(password)) {
				user = result.get();
			}
		}
		return user;
	}

	public boolean updateUserPassword(UserChangePassword userChangePassword) {
		Optional<User> result = userRepository.findById(userChangePassword.getEmailId());
		if (result.isPresent()) {
			User user = result.get();
			user.setPassword(userChangePassword.getNewPassword());
			userRepository.saveAndFlush(user);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean createUser(String emailId, String password, String name) {
		try {
			if (userRepository.existsById(emailId)) {
				System.out.println("User already exists");
				return false;
			} else {
				User user = new User();
				user.setEmailId(emailId);
				user.setName(name);
				user.setPassword(password);
				user.setAdmin(false);
				user.setOrders(null);
				userRepository.save(user);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
	}
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	public List<User> findUserByEmailId(String emailId) {
		List<User> userList = new ArrayList<User>();
		userList.add(userRepository.findById(emailId).get());
		return userList;
	}

}
