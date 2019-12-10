package com.tang.rabbitmq.producer.controller;

import com.tang.rabbitmq.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rabbit")
public class ProducerController {

	@Autowired
	private ProducerService producerService;

	@PostMapping("/send")
	public String send(@RequestBody List<Map<String,String>> param){
		param.forEach(
			stringStringMap -> {
				producerService.sendMessage(stringStringMap.get("queueName"),stringStringMap.get("payload"));
			}
		);
		return "success";
	}
	@PostMapping("/send/exchange")
	public String sendExchange(@RequestBody List<Map<String,String>> param){
		param.forEach(
			stringStringMap -> {
				producerService.sendMessage(stringStringMap.get("exchange"),stringStringMap.get("queueName"),stringStringMap.get("payload"));
			}
		);
		return "success";
	}
}
