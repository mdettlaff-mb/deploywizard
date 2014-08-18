package com.example.helloworld;

import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AmqpClient {

	private final ConnectionFactory connectionFactory;
	private Connection connection;

	public AmqpClient() {
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
	}

	public void connect() {
		try {
			connection = connectionFactory.newConnection();
		} catch (IOException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}

	public void close() {
		try {
			connection.close();
		} catch (IOException e) {
			throw new IllegalStateException("Cannot close connection", e);
		}
	}

	public CloseableChannel createChannel() {
		try {
			return new CloseableChannel(connection.createChannel());
		} catch (IOException e) {
			throw new IllegalStateException("Cannot create channel", e);
		}
	}
}
