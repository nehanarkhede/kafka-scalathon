#!/bin/bash

base_dir=$(dirname $0)
export KSTREAM_OPTS="-Xmx512M -server -Dcom.sun.management.jmxremote -Dlog4j.configuration=file:$base_dir/../config/log4j.properties"
$base_dir/scalathon-kafka-run-class.sh scalathon.kafka.consumer.ConsumerExample $@

