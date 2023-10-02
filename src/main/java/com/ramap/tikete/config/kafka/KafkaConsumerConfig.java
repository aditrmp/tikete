package com.ramap.tikete.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.ramap.tikete.dto.request.PurchaseReq;

/*
 * https://reflectoring.io/spring-boot-kafka/
 */

@Configuration
public class KafkaConsumerConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	  private String bootstrapServers;

	  @Bean
	  public Map<String, Object> consumerConfigs() {
	    Map<String, Object> props = new HashMap<>();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
	      bootstrapServers);
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
	      StringDeserializer.class);
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
	    		JsonDeserializer.class);
	    props.put(JsonDeserializer.TRUSTED_PACKAGES, 
	    		"*");
	    return props;
	  }


		 @Bean
		 public ConsumerFactory<String, PurchaseReq> consumerFactory() {
			 Map<String, Object> props = new HashMap<>();
				props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
				props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
			    props.put(ConsumerConfig.GROUP_ID_CONFIG, "topics123-group");
//		    return new DefaultKafkaConsumerFactory<>(consumerConfigs());
			 return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			            new JsonDeserializer<>(PurchaseReq.class, false));
		 }
		 
//	  @Bean
//	  public ConsumerFactory<String, String> consumerFactory() {
//	    return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//	  }

	  @Bean
	  public ConcurrentKafkaListenerContainerFactory<String, PurchaseReq> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, PurchaseReq> factory =
	      new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());
	    return factory;
	  }
}
