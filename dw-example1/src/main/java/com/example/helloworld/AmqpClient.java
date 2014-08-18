package com.example.helloworld;

import java.io.IOException;

import com.rabbitmq.client.Connection;

public class AmqpClient {

	private final AmqpConnectionPool connectionPool;

	public AmqpClient() {
		connectionPool = new AmqpConnectionPool();
	}

	public CloseableChannel createChannel() {
		try {
			Connection connection = connectionPool.getConnection();
			return new CloseableChannel(connection.createChannel());
		} catch (IOException e) {
			throw new IllegalStateException("Cannot create channel", e);
		}
	}

	public void close() {
		connectionPool.close();
	}
}
