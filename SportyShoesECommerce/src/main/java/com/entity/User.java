package com.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	private String emailId;
	private String password;
	private String name;
	private boolean isAdmin;
	@OneToMany
	@JoinColumn(name = "emailId")
	private List<Orders> orders;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
	public User() {
		super();
	}
	
	public User(String emailId, String password, String name, boolean isAdmin, List<Orders> orders) {
		super();
		this.emailId = emailId;
		this.password = password;
		this.name = name;
		this.isAdmin = isAdmin;
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "User [emailId=" + emailId + ", password=" + password + ", name=" + name + ", isAdmin=" + isAdmin
				+ ", orders=" + orders + "]";
	}

}
