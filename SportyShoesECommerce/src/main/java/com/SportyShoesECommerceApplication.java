package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.entity.User;
import com.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "com")
@EntityScan(basePackages = "com.entity")
@EnableJpaRepositories(basePackages = "com.repository")
public class SportyShoesECommerceApplication {
	
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SportyShoesECommerceApplication.class, args);
		System.out.println("Sporty Shoes is running");
	}

	@PostConstruct
	public void init() {
		try {
			User user = new User("admin@gmail.com", "admin@123", "Admin", true, null);
			userRepository.save(user);
			System.out.println("Admin user created.");
		} catch (Exception e) {
			System.err.println(e.toString());
			System.out.println("Admin user could not be created.");
		}
	}

}
