package com.example.helloworld;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AmqpWrapper {

	private final ConnectionFactory connectionFactory;
	private Connection connection;
	private Channel channel;

	public AmqpWrapper() {
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
	}

	public void connect() {
		try {
			connection = connectionFactory.newConnection();
			channel = connection.createChannel();
		} catch (IOException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}

	public void close() {
		try {
			channel.close();
		} catch (IOException e) {
			throw new IllegalStateException("Cannot close channel", e);
		}
		try {
			connection.close();
		} catch (IOException e) {
			throw new IllegalStateException("Cannot close connection", e);
		}
	}

	public Channel getChannel() {
		return channel;
	}
}
