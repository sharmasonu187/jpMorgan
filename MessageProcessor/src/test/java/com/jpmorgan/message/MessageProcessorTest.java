package com.jpmorgan.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.message.MessageProcessor;
import com.jpmorgan.message.history.MessageLog;
import com.jpmorgan.message.type.Sale;

public class MessageProcessorTest {

	private Collection<String> messages;

	@Before
	public void init() {
		messages = new ArrayList<>();
		messages.add("apple at 10p");
		messages.add("20 sales of apples at 10p each");
		messages.add("Add 20p apples");
		messages.add("apple at 10p");
		messages.add("20 sales of apples at 10p each");
		messages.add("Add 20p apples");
		messages.add("apple at 10p");
	}

	@Test
	public void testSalesLogging() {
		MessageLog messageLog = new MessageLog();
		MessageProcessor processor = new MessageProcessor(messageLog);
		messages.forEach(processor::processMessage);
		Map<String, Collection<Sale>> salesMap = messageLog.getSalesMap();
		assertEquals(1, salesMap.size());
		Collection<Sale> appleSales = salesMap.get("apple");
		assertEquals(5, appleSales.size());
	}

	@Test
	public void testAdjustmentLogging() {
		MessageLog messageLog = new MessageLog();
		MessageProcessor processor = new MessageProcessor(messageLog);
		messages.forEach(processor::processMessage);
		Map<String, Collection<Sale>> salesMap = messageLog.getSalesMap();
		Collection<Sale> appleSales = salesMap.get("apple");
		List<Integer> adjustmentsMade = appleSales.stream().map(sale -> sale.getAdjustments().size())
				.collect(Collectors.toList());
		Collection<Integer> expected = new ArrayList<>();
		expected.add(2);
		expected.add(2);
		expected.add(1);
		expected.add(1);
		expected.add(0);

		assertTrue(expected.equals(adjustmentsMade));
	}

	@Test
	public void testSaleTotalPrice() {
		MessageLog messageLog = new MessageLog();
		MessageProcessor processor = new MessageProcessor(messageLog);
		messages.forEach(processor::processMessage);
		double appleSaleTotalPrice = messageLog.getSalesMap().get("apple").stream().map(Sale::getTotalPrice)
				.mapToDouble(Double::valueOf).sum();
		// (1 * 50) + (20 * 50) + (1 * 30) + (20 * 30) + (1 * 10)
		// 50 + 1000 + 30 + 600 + 10
		assertEquals(1690d, appleSaleTotalPrice, 0d);
	}
}
