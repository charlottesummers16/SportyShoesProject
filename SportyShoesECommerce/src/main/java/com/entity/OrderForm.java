package com.entity;

public class OrderForm {
	private int shoeId;
	private int quantityRequired;
	private String emailId;
	
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
	
	@Override
	public String toString() {
		return "OrderForm [shoeId=" + shoeId + ", quantityRequired=" + quantityRequired + ", emailId=" + emailId + "]";
	}
	public OrderForm(int shoeId, int quantityRequired, String emailId) {
		super();
		this.shoeId = shoeId;
		this.quantityRequired = quantityRequired;
		this.emailId = emailId;
	}
	public OrderForm() {
		super();
	}
	

}
