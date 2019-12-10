package com.tang.rabbitmq.consumer.core;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiver {

	@Value("${queue.hello}")
	private String hello;
	@Value("${queue.user}")
	private String user;
	@Value("${queue.topic.message}")
	private String topic_message;
	@Value("${queue.topic.messages}")
	private String topic_messages;
	@Value("${queue.fanout.A}")
	private String fanout_A;
	@Value("${queue.fanout.B}")
	private String fanout_B;
	@Value("${queue.fanout.C}")
	private String fanout_C;
/*	@Value("${queue.topic}")
	private String topic;*/


	//	@RabbitHandler
	@RabbitListener(queues = "${queue.hello}")
	public void process(String message) {
		System.out.println(hello+" : " + message);
	}

	@RabbitListener(queues = "${queue.user}")
	public void processUser(String message) {
		System.out.println(user+" : " + message);
	}

/*	@RabbitListener(queues = "${queue.topic}")
	public void processTopic(String message) {
		System.out.println(topic+" : " + message);
	}*/

	@RabbitListener(queues = "${queue.topic.message}")
	public void processTopicMessage(String message) {
		System.out.println(topic_message+" : " + message);
	}

	@RabbitListener(queues = "${queue.topic.messages}")
	public void processTopicMessages(String message) {
		System.out.println(topic_messages+" : " + message);
	}

	@RabbitListener(queues = "${queue.fanout.A}")
	public void processFanoutA(String message) {
		System.out.println(fanout_A+" : " + message);
	}

	@RabbitListener(queues = "${queue.fanout.B}")
	public void processFanoutB(String message) {
		System.out.println(fanout_B+" : " + message);
	}

	@RabbitListener(queues = "${queue.fanout.C}")
	public void processFanoutC(String message) {
		System.out.println(fanout_C+" : " + message);
	}



}
