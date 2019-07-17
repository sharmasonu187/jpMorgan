package com.jpmorgan.message;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class MessageGenerator {

	public static Collection<String> getMessages() {

		Collection<String> messages = new ArrayList<>();

		try (BufferedReader inputFile = new BufferedReader(new FileReader("messages.txt"))) {
			String line;
			while ((line = inputFile.readLine()) != null) {
				messages.add(line);
			}
		} catch (IOException e) {
			System.out.println("Exception while initializing the messages");
		}

		return messages;
	}

}
