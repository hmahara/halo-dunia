# halo-dunia
Halo Dunia, Hello World

## Build the docker and tag

docker build . -t halo-dunia:0.0.1 -t halo-dunia:latest

## Run the application

docker run -p 2016:2016 hmahara/halo-dunia:latest

### Run RabbitMq 

docker run --name helmi-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management
