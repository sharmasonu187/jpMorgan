package com.jpmorgan.message.type;

public class SaleMessage extends Message {
	private int quantity;
	private double price;

	public SaleMessage(String productType, int quantity, double price) {
		super(productType);
		this.quantity = quantity;
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

}
