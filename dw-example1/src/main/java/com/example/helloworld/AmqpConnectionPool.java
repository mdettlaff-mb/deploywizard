package com.example.helloworld;

import org.apache.commons.pool2.impl.GenericObjectPool;

import com.rabbitmq.client.Connection;

public class AmqpConnectionPool {

	private final GenericObjectPool<Connection> pool;

	public AmqpConnectionPool() {
		pool = new GenericObjectPool<Connection>(new AmqpConnectionFactory());
	}

	public Connection getConnection() {
		try {
			return pool.borrowObject();
		} catch (Exception e) {
			throw new IllegalStateException("Cannot get connection from pool", e);
		}
	}

	public void close() {
		pool.close();
	}
}
