package com.example.helloworld;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AmqpTemplate {

	private final ConnectionFactory connectionFactory;

	public AmqpTemplate(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public void send(String queue, String message) throws IOException {
		Connection connection = connectionFactory.newConnection();
		try {
			Channel channel = connection.createChannel();
			try {
				channel.basicPublish("", queue, null, message.getBytes());
			} finally {
				channel.close();
			}
		} finally {
			connection.close();
		}
	}
}
