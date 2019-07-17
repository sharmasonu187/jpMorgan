package com.jpmorgan.message.type;

public class Message {

	protected String productType;

	public Message(String productType) {
		this.productType = productType;
	}

	public String getProductType() {
		return productType;
	}

}
