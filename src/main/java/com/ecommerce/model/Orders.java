package com.ecommerce.model;

public class Orders {

	private String productName;
	private int price,qty;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Orders(String productName, int price, int qty) {
		super();
		this.productName = productName;
		this.price = price;
		this.qty = qty;
	}

	
	
	
}
