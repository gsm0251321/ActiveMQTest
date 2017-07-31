package com.test.activemq.request2Response;

public class MessageProtocol {
	public String handleProtocolMessage(String messageText) {
		String responseText;
		if ("MyProtocolMessage".equalsIgnoreCase(messageText)) {
			responseText = "I recognize your protocol message";
		} else {
			responseText = "Unknown protocol message: " + messageText;
		}
		System.out.println(messageText);
		return responseText;
	}
}