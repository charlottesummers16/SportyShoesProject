package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Shoe {
	
	@Id
	private int shoeId;
	private String brand;
	private String type;
	private float price;
	private int quantityInStock;
	
	public Shoe() {
		super();
	}
	
	public Shoe(int shoeId, String brand, String type, float price, int quantityInStock) {
		super();
		this.shoeId = shoeId;
		this.brand = brand;
		this.type = type;
		this.price = price;
		this.quantityInStock = quantityInStock;
	}
	
	public int getShoeId() {
		return shoeId;
	}
	public void setShoeId(int shoeId) {
		this.shoeId = shoeId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	@Override
	public String toString() {
		return "Shoe [shoeId=" + shoeId + ", brand=" + brand + ", type=" + type + ", price=" + price
				+ ", quantityInStock=" + quantityInStock + "]";
	}

}
