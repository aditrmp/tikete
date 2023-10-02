package com.ramap.tikete.config.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {
	private KafkaTemplate<String, Object> kafkaTemplate;
//	private RoutingKafkaTemplate routingKafkaTemplate;
//	private KafkaTemplate<String, User> userKafkaTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(KafkaSender.class);
	
	@Autowired
	KafkaSender(KafkaTemplate<String, Object> kafkaTemplate
//			, RoutingKafkaTemplate routingKafkaTemplate
			) {
		this.kafkaTemplate = kafkaTemplate;
//		this.routingKafkaTemplate = routingKafkaTemplate;
//		this.userKafkaTemplate = userKafkaTemplate;
	}
	
	public KafkaSender(String userId, String kafkaTopic1) {
		// TODO Auto-generated constructor stub
	}

	public void sendMessage(String topicName, Object message) {
		LOG.info("Sending : {}", message);
		LOG.info("--------------------------------");

		kafkaTemplate.send(topicName, message);
	}
	
}
