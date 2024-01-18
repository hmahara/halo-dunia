# halo-dunia
Halo Dunia, Hello World

## Build the docker and tag

docker build . -t halo-dunia:0.0.1 -t halo-dunia:latest

## Run the application

docker run -p 2016:2016 hmahara/halo-dunia:latest

docker run -e SPRING_RABBITMQ_HOST=atom.iamhelmi.eu -e SPRING_RABBITMQ_USERNAME=lincsafe -e 'SPRING_RABBITMQ_PASSWORD=start2024' hmahara/halo-dunia:0.
