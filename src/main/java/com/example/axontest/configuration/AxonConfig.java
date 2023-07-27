package com.example.axontest.configuration;

import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Value("${axon.aggregate.order.snapshot-threshold:5}")
    int threshold;

    @Bean
    public SnapshotTriggerDefinition orderAggregateSnapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, threshold);
    }

/*
    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.usingTrackingEventProcessors(); // 기본 Event Processor를 "tracking" 유형으로 설정

    }

    @Bean
    @Primary
    public Serializer serializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return JacksonSerializer.builder().objectMapper(objectMapper).build();
    }*/
}
