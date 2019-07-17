package com.jpmorgan.message.factory;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jpmorgan.message.type.AdjustmentMessage;
import com.jpmorgan.message.type.Message;
import com.jpmorgan.message.type.MessageType;
import com.jpmorgan.message.type.SaleMessage;

public class MessageFactory {

	public static Message getMessage(String message) {

		MessageType messageType = getMessageType(message);

		Pattern p = Pattern.compile(messageType.getPattern());
		Matcher m = p.matcher(message);
		String productType = null;
		int quantity;
		double price;
		String operation = null;
		switch (messageType) {
		case TYPE1:
			m.matches();
			productType = pluralToSingular(m.group(1));
			quantity = 1;
			price = Double.valueOf(m.group(2));
			return new SaleMessage(productType.toLowerCase(), quantity, price);
		case TYPE2:
			m.matches();
			productType = pluralToSingular(m.group(2));
			quantity = Integer.valueOf(m.group(1));
			price = Double.valueOf(m.group(3));
			return new SaleMessage(productType.toLowerCase(), quantity, price);
		case TYPE3:
			m.matches();
			operation = m.group(1);
			productType = pluralToSingular(m.group(3));
			price = Double.valueOf(m.group(2));
			return new AdjustmentMessage(productType.toLowerCase(), price, operation);
		}

		return null;
	}

	private static MessageType getMessageType(String message) {
		Optional<MessageType> optional = Arrays.stream(MessageType.values())
				.filter(type -> message.matches(type.getPattern())).findFirst();
		MessageType messageType = optional.orElseThrow(() -> new RuntimeException("Invalid Message Format " + message));
		return messageType;
	}

	private static String pluralToSingular(String productType) {
		if (productType.endsWith("s")) {
			return productType.substring(0, productType.length() - 1);
		} else {
			return productType;
		}
	}

}
