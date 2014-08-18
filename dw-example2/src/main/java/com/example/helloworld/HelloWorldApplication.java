package com.example.helloworld;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.helloworld.health.Example2HealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldApplication.class);

	private static final String QUEUE_NAME = "dw-example";

	public static void main(String[] args) throws Exception {
		new HelloWorldApplication().run(args);
	}

	@Override
	public String getName() {
		return "example2";
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle());
	}

	@Override
	public void run(HelloWorldConfiguration configuration, Environment environment) throws ClassNotFoundException,
			IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		environment.healthChecks().register("example2", new Example2HealthCheck());

		final AmqpWrapper amqp = new AmqpWrapper();
		environment.lifecycle().manage(new Managed() {

			@Override
			public void start() throws Exception {
				LOGGER.info("starting example2");
				amqp.connect();
				LOGGER.info("connected to AMQP server");
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Channel channel = amqp.getChannel();
							LOGGER.info("waiting for messages");
							QueueingConsumer consumer = new QueueingConsumer(channel);
							channel.basicConsume(QUEUE_NAME, true, consumer);
							while (true) {
								QueueingConsumer.Delivery delivery = consumer.nextDelivery();
								String message = new String(delivery.getBody());
								LOGGER.info("consumer received message: {}", message);
							}
						} catch (IOException | ShutdownSignalException | ConsumerCancelledException
								| InterruptedException e) {
							LOGGER.error("cannot receive messages", e);
						}
					}
				});
				thread.setName("message_consumer");
				thread.start();
				LOGGER.info("started {} thread", thread.getName());
			}

			@Override
			public void stop() throws Exception {
				LOGGER.info("stopping example2");
				amqp.close();
				LOGGER.info("disconnected from AMQP server");
			}
		});

		environment.jersey().register(new HelloWorldResource());
	}
}
