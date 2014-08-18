package com.example.helloworld;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AmqpConnectionFactory extends BasePooledObjectFactory<Connection> {

	@Override
	public Connection create() throws Exception {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		return connectionFactory.newConnection();
	}

	@Override
	public PooledObject<Connection> wrap(Connection connection) {
		return new DefaultPooledObject<Connection>(connection);
	}

	@Override
	public boolean validateObject(PooledObject<Connection> p) {
		return p.getObject().isOpen();
	}

	@Override
	public void destroyObject(PooledObject<Connection> p) throws Exception {
		p.getObject().close();
	}
}
