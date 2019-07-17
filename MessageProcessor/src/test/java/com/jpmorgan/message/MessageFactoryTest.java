package com.jpmorgan.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jpmorgan.message.factory.MessageFactory;
import com.jpmorgan.message.type.AdjustmentMessage;
import com.jpmorgan.message.type.Message;
import com.jpmorgan.message.type.SaleMessage;

public class MessageFactoryTest {

	@Test
	public void testMessageType1() {
		String typ1Msg = "apple at 10p";
		Message message = MessageFactory.getMessage(typ1Msg);

		assertTrue(message instanceof SaleMessage);

		SaleMessage saleMsg = (SaleMessage) message;
		assertEquals("apple", saleMsg.getProductType());
		assertEquals(10d, saleMsg.getPrice(), 0d);
		assertEquals(1, saleMsg.getQuantity());

	}

	@Test
	public void testMessageType2() {
		String typ1Msg = "20 sales of apples at 10p each";
		Message message = MessageFactory.getMessage(typ1Msg);

		assertTrue(message instanceof SaleMessage);

		SaleMessage saleMsg = (SaleMessage) message;
		assertEquals("apple", saleMsg.getProductType());
		assertEquals(10d, saleMsg.getPrice(), 0d);
		assertEquals(20, saleMsg.getQuantity());

	}

	@Test
	public void testMessageType3() {
		String typ1Msg = "Add 20p apples";
		Message message = MessageFactory.getMessage(typ1Msg);

		assertTrue(message instanceof AdjustmentMessage);

		AdjustmentMessage adjustmentMsg = (AdjustmentMessage) message;
		assertEquals("apple", adjustmentMsg.getProductType());
		assertEquals(20d, adjustmentMsg.getAdjustment(), 0d);

	}

}
