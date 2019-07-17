package com.jpmorgan.message.type;

public enum MessageType {
	TYPE1("^([a-zA-Z]*) at ([0-9]*)p$"), TYPE2("^([0-9]*) sales of ([a-zA-Z]*) at ([0-9]*)p each$"),
	TYPE3("^(Add|Subtract|Multiply) ([0-9]*)p ([a-zA-Z]*)$");
	private String pattern;

	MessageType(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

}
