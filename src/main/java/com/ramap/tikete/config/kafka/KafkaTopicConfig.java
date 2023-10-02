package com.ramap.tikete.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig {
	  @Bean
	  public NewTopic topic1() {
	    return TopicBuilder.name("purchase-queue").build();
	  }

//	  @Bean
//	  public NewTopic topic2() {
//	    return TopicBuilder.name("reflectoring-2").build();
//	  }
}
