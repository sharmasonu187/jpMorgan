package com.jpmorgan.message;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jpmorgan.message.type.AdjustmentOperation;

public class AdjustmentOperationTest {

	@Test
	public void testAdd() {
		double result = AdjustmentOperation.ADD.getFunction().apply(10d, 20d);
		assertEquals(30d, result, 0d);
	}

	@Test
	public void testSubtract() {
		double result = AdjustmentOperation.SUBTRACT.getFunction().apply(10d, 20d);
		assertEquals(-10d, result, 0d);
	}

	@Test
	public void testMutliply() {
		double result = AdjustmentOperation.MULTIPLY.getFunction().apply(3d, 20d);
		assertEquals(60d, result, 0d);
	}

}
