package com.example.axontest;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.extensions.kafka.KafkaProperties;
import org.axonframework.extensions.kafka.eventhandling.consumer.ConsumerFactory;
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.StreamableKafkaMessageSource;
import org.axonframework.extensions.kafka.eventhandling.producer.ProducerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@SpringBootApplication
public class AxonTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonTestApplication.class, args);
    }

    @Configuration
    public class PooledStreamingConfiguration {

        private static final String KAFKA_GROUP = "kafka-group";


        @Autowired
        public void configureStreamableKafkaSource(
                EventProcessingConfigurer configurer,
                StreamableKafkaMessageSource<String, Byte[]> streamableKafkaMessageSource
        ) {
            configurer.registerPooledStreamingEventProcessor(KAFKA_GROUP, configuration ->
            streamableKafkaMessageSource);
        }


    }
}
