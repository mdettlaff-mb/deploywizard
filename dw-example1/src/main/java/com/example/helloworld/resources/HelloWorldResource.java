package com.example.helloworld.resources;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.helloworld.AmqpWrapper;
import com.google.common.base.Optional;

@Path("/example1")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

	private static final String QUEUE_NAME = "dw-example";

	private final AmqpWrapper amqp;

	public HelloWorldResource(AmqpWrapper amqp) {
		this.amqp = amqp;
	}

	@GET
	public String sendMessage(@QueryParam("message") Optional<String> message) throws IOException {
		LOGGER.info("received a request with message content: {}", message);
		if (message.isPresent()) {
			amqp.getChannel().basicPublish("", QUEUE_NAME, null, message.get().getBytes());
			LOGGER.info("sent message with content: {}", message);
		}
		return "OK";
	}
}
