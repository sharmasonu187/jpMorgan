package com.jpmorgan.message.type;

public class AdjustmentMessage extends Message {

	private double adjustment;
	private String operation;

	public AdjustmentMessage(String productType, double adjustment, String operation) {
		super(productType);
		this.adjustment = adjustment;
		this.operation = operation;
	}

	public double getAdjustment() {
		return adjustment;
	}

	public String getOperation() {
		return operation;
	}

}
