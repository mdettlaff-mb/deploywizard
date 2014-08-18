package com.example.helloworld;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.helloworld.health.Example1HealthCheck;
import com.example.helloworld.resources.HelloWorldResource;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldApplication.class);

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

		final AmqpClient amqp = new AmqpClient();
		environment.lifecycle().manage(new Managed() {

			@Override
			public void start() throws Exception {
				LOGGER.info("starting example1");
				amqp.connect();
				LOGGER.info("connected to AMQP server");
				try (CloseableChannel channel = amqp.createChannel()) {
					channel.queueDeclare(QUEUE_NAME, false, false, false, null);
				}
				LOGGER.info("declared queue {}", QUEUE_NAME);
			}

			@Override
			public void stop() throws Exception {
				LOGGER.info("stopping example1");
				amqp.close();
				LOGGER.info("disconnected from AMQP server");
			}
		});

		environment.jersey().register(new HelloWorldResource(amqp));
	}
}
