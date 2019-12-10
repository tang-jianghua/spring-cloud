package com.tang.rabbitmq.producer.core;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

@Configuration
public class RabbitConfig {

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
	@Value("${queue.exchange}")
	private String exchange;
	@Value("${queue.fanoutExchange}")
	private String fanoutExchange;
	@Value("${queue.topic}")
	private String topic;

	@Bean
	public Queue helloQueue() {
		return new Queue(hello);
	}

	@Bean
	public Queue userQueue() {
		return new Queue(user);
	}

	//===============以下是验证topic Exchange的队列==========
	@Bean
	public Queue queueMessage() {
		return new Queue(topic_message);
	}

	@Bean
	public Queue queueMessages() {
		return new Queue(topic_messages);
	}
	//===============以上是验证topic Exchange的队列==========

	//===============以下是验证Fanout Exchange的队列==========
	@Bean
	public Queue AMessage() {
		return new Queue(fanout_A);
	}

	@Bean
	public Queue BMessage() {
		return new Queue(fanout_B);
	}

	@Bean
	public Queue CMessage() {
		return new Queue(fanout_C);
	}
	//===============以上是验证Fanout Exchange的队列==========

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange(fanoutExchange);
	}

	/**
	 * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with(topic_message);
	}

	/**
	 * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
	 * @param queueMessages
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessages).to(exchange).with(topic);
	}

	@Bean
	Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(AMessage).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(BMessage).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(CMessage).to(fanoutExchange);
	}



	/*@Bean
	public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(new MessageConverter() {
			@Override
			public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
				return null;
			}

			@Override
			public Object fromMessage(Message message) throws MessageConversionException {
				try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(message.getBody()))){
					return ois.readObject();
				}catch (Exception e){
					e.printStackTrace();
					return null;
				}
			}
		});

		return factory;
	}*/
}
