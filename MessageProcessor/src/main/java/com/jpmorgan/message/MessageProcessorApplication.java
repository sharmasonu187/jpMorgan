package com.jpmorgan.message;

import java.util.Collection;

import com.jpmorgan.message.history.MessageLog;

public class MessageProcessorApplication {

	public static void main(String[] args) {
		Collection<String> messages = MessageGenerator.getMessages();
		MessageProcessor messageProcessor = new MessageProcessor(new MessageLog());
		for (String message : messages) {
			messageProcessor.processMessage(message);
		}
	}

}
