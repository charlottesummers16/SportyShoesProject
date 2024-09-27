package com.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String emailId;
	private int shoeId;
	private int quantityRequired;
	private LocalDateTime orderDateTime;
	
	public Orders() {
		super();
	}

	public Orders(int orderId, String emailId, int shoeId, int quantityRequired, LocalDateTime orderDateTime) {
		super();
		this.orderId = orderId;
		this.emailId = emailId;
		this.shoeId = shoeId;
		this.quantityRequired = quantityRequired;
		this.orderDateTime = orderDateTime;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getShoeId() {
		return shoeId;
	}
	public void setShoeId(int shoeId) {
		this.shoeId = shoeId;
	}
	public int getQuantityRequired() {
		return quantityRequired;
	}
	public void setQuantityRequired(int quantityRequired) {
		this.quantityRequired = quantityRequired;
	}
	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", emailId=" + emailId + ", shoeId=" + shoeId + ", quantityRequired="
				+ quantityRequired + ", orderDateTime=" + orderDateTime + "]";
	}
	
	
}
