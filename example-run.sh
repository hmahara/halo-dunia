#!/bin/bash

docker run \
  --hostname halo-dunia \
  --name halo-dunia \
  -p 2016:2016 \
  -e SPRING_RABBITMQ_HOST=atom.iamhelmi.eu \
  -e SPRING_RABBITMQ_USERNAME=helmi \
  -e SPRING_RABBITMQ_PASSWORD='start2024' \
  -e RABBITMQ_QUEUE_NAME1=q1test1 \
  -e RABBITMQ_QUEUE_NAME2=q1test2 \
  -e RABBITMQ_EXCHANGE_NAME=q1exchange \
  -e RABBITMQ_ROUTING_KEY=queue.q1test \
  hmahara/halo-dunia:0.0.2
