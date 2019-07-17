package com.jpmorgan.message.type;

import java.util.ArrayList;
import java.util.Collection;

public class Sale {
	private String productType;
	private int quantity;
	private double price;

	Collection<Adjustment> adjustments = new ArrayList<>();

	public Sale(String productType, int quantity, double price) {
		this.productType = productType;
		this.quantity = quantity;
		this.price = price;
	}

	public String getProductType() {
		return productType;
	}

	public int getQuantity() {
		return quantity;
	}

	public Collection<Adjustment> getAdjustments() {
		return adjustments;
	}

	public void addAdjustment(Adjustment adjustment) {
		adjustments.add(adjustment);
	}

	public double getPrice() {
		return price;
	}

	public double getAdjustedPrice() {
		double adjustedValue = price;

		for (Adjustment adjustment : adjustments) {
			adjustedValue = adjustment.getOperation().getFunction().apply(adjustedValue, adjustment.getAdjustment());
		}

		return adjustedValue;
	}

	public double getTotalPrice() {

		return getQuantity() * getAdjustedPrice();
	}

}
