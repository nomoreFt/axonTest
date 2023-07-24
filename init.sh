#!/bin/bash

# Wait for Kafka to be ready
# ...

# Create Kafka topic
/opt/kafka/bin/kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

# Start Kafka
# ...
