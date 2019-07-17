package com.jpmorgan.message;

import com.jpmorgan.message.factory.MessageFactory;
import com.jpmorgan.message.history.MessageLog;
import com.jpmorgan.message.type.Adjustment;
import com.jpmorgan.message.type.AdjustmentMessage;
import com.jpmorgan.message.type.AdjustmentOperation;
import com.jpmorgan.message.type.Message;
import com.jpmorgan.message.type.Sale;
import com.jpmorgan.message.type.SaleMessage;

public class MessageProcessor {

	private static final int THRESHOLD_TO_PRINT_SALES = 10;

	private static final int THRESHOLD_TO_PRINT_ADJ = 50;

	private int processedMessageCount;

	private MessageLog messageLog;

	public MessageProcessor(MessageLog messageLog) {
		this.messageLog = messageLog;
	}

	public void processMessage(String message) {
		Message msg = MessageFactory.getMessage(message);
		processedMessageCount++;

		if (msg instanceof SaleMessage) {
			SaleMessage saleMsg = (SaleMessage) msg;
			Sale sale = new Sale(saleMsg.getProductType(), saleMsg.getQuantity(), saleMsg.getPrice());
			messageLog.addSale(sale);

		} else if (msg instanceof AdjustmentMessage) {
			AdjustmentMessage adjustmentMsg = (AdjustmentMessage) msg;
			Adjustment adjustment = new Adjustment(adjustmentMsg.getProductType(),
					AdjustmentOperation.getAdjustmentOperation(adjustmentMsg.getOperation()),
					adjustmentMsg.getAdjustment());
			messageLog.addAdjustment(adjustment);

		} else {
			throw new RuntimeException("UnExpected!!!");
		}

		printSales();

		printAdjustments();
	}

	private void printAdjustments() {
		if (processedMessageCount % THRESHOLD_TO_PRINT_ADJ == 0) {
			System.out.println(
					"---------Application is pausing for a momemt. The following adjustments were made-----------");
			messageLog.printAdjustments();
		}
	}

	private void printSales() {
		if (processedMessageCount % THRESHOLD_TO_PRINT_SALES == 0) {
			System.out.println("---------------------Sales Report-----------------------------");
			messageLog.printSales();
		}
	}

}
