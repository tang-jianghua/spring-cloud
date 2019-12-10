package com.tang.rabbitmq.producer.service.impl;

import com.tang.rabbitmq.producer.service.ProducerService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Override
	public void sendMessage(String queueName, String payload) {
		amqpTemplate.convertAndSend(queueName,payload);
	}

	@Override
	public void sendMessage(String exchange, String queueName, String payload) {
		amqpTemplate.convertAndSend(exchange,queueName,payload);
	}
}
