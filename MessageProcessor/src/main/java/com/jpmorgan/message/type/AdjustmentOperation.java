package com.jpmorgan.message.type;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BinaryOperator;

public enum AdjustmentOperation {

	ADD("Add", (a, b) -> a + b), SUBTRACT("Subtract", (a, b) -> a - b), MULTIPLY("Multiply", (a, b) -> a * b);

	private String value;
	private BinaryOperator<Double> function;

	AdjustmentOperation(String value, BinaryOperator<Double> function) {
		this.value = value;
		this.function = function;
	}

	public String getValue() {
		return value;
	}

	public BinaryOperator<Double> getFunction() {
		return function;
	}

	public static AdjustmentOperation getAdjustmentOperation(String adjustment) {
		Optional<AdjustmentOperation> optional = Arrays.stream(AdjustmentOperation.values())
				.filter(p -> p.getValue().equals(adjustment)).findFirst();
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

}
