# Kafka demo at Scalathon #

This repository has the Kafka demos used at Scalathon. 

## How to build ? ##
The demo uses SBT 0.7.5

`./sbt update package`

## How to run ? ##

You need to start a zookeeper and kafka server. See the kafka [quickstart](http://sna-projects.com/kafka/quickstart.php) page for instructions on how to do that

To run the Kafka demo, see the scripts provided in the bin directory

### Kafka producer examples ###

* Run the basic producer examples 

	`bin/basic-producer-example.sh`

	Run the kafka consumer shell [see quickstart](http://sna-projects.com/kafka/quickstart.php)
	`kafka/bin/kafka-consumer-shell.sh --topic test-topic --props config/consumer.properties`

* Run the producer encoder example

	`bin/producer-encoder-example.sh

	Run the kafka consumer shell to consume that data 

	`kafka/bin/kafka-consumer-shell.sh --topic member-records --props config/consumer.properties`

* Run the producer partitioner example

	`bin/producer-partitioner-example.sh`

	Spin up another kafka consumer in the same group and topic to see automatic load balancing amongst the two consumers

	`kafka/bin/kafka-consumer-shell.sh --topic member-records --props config/consumer.properties`

	Run the producer partitioner again and observe, all the "US" location records go to one consumer, while all "EUR" records go to the other consumer

### Kafka consumer examples ###

* Run the consumer decoder examples to consumer all data from the "member-records" topic

	`bin/consumer-decoder-example.sh`

### Want to contribute to Kafka ? ###

For project ideas to contribute to Kafka, see our [projects page](http://sna-projects.com/kafka/projects.php)

Send us a pull request [here](https://github.com/kafka-dev/kafka)
