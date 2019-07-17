package com.jpmorgan.message.type;

public class Adjustment {
	private String productType;
	private AdjustmentOperation operation;
	private double adjustment;

	public Adjustment(String productType, AdjustmentOperation operation, double adjustment) {
		this.productType = productType;
		this.operation = operation;
		this.adjustment = adjustment;
	}

	public String getProductType() {
		return productType;
	}

	public AdjustmentOperation getOperation() {
		return operation;
	}

	public double getAdjustment() {
		return adjustment;
	}

}
