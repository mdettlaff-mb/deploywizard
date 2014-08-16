package com.example.helloworld;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.IOException;

import com.example.helloworld.health.Example1HealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

	private static final String QUEUE_NAME = "dw-example";

	public static void main(String[] args) throws Exception {
		new HelloWorldApplication().run(args);
	}

	@Override
	public String getName() {
		return "example1";
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle());
	}

	@Override
	public void run(HelloWorldConfiguration configuration, Environment environment) throws ClassNotFoundException,
			IOException {
		environment.healthChecks().register("example1", new Example1HealthCheck());

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.close();
		connection.close();
		environment.jersey().register(new HelloWorldResource(new AmqpTemplate(factory)));
	}
}
