package com.tang.rabbitmq.producer.service;

public interface ProducerService {

	void sendMessage(String queueName,String payload);

	void sendMessage(String exchange,String queueName,String payload);
}
