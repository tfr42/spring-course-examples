# Spring Boot demo with Docker

## Spring Boot container

To package the Spring Boot application run:

    % mvn package

To run the Spring Boot application execute:
 
    % mvn spring-boot:run
    
Then open http://localhost:8080

## Spring Boot application inside Docker container

### Use Docker
To build the Docker image with docker itself run:

    % cd src/main/config/docker
    % docker build -t tfr42/hello-world-springboot .

Don't forget to copy the JAR file to the Docker source directory so that it can be added to the Docker image.
Or you can use Maven with the Maven plugin for Docker (see below). 

### Use the Maven plugin for Docker
    
To build the Docker image with the Maven plugin for Docker run:
        
    % mvn clean package docker:build

### Starting the docker container

To run the Docker container execute:
    
    % docker run -p 8080:8080 --rm tfr42/hello-world-springboot
     
Then open http://localhost:8080 or in case you run Docker with docker-machine then open http://$CONTAINER_IP:8080, where $CONTAINER_IP
     is taken from ```docker-machine ip <DOCKER_VM_NAME>```.

See the [Docker web site](https://www.docker.com/) for more information about Docker! 

## Access the Spring Boot Monitoring functions:

* http://$CONTAINER_IP:8080/info
* http://$CONTAINER_IP:8080/health
* http://$CONTAINER_IP:8080/beans
* http://$CONTAINER_IP:8080/mappings
* http://$CONTAINER_IP:8080/metrics
* http://$CONTAINER_IP:8080/dump
* http://$CONTAINER_IP:8080/trace
* http://$CONTAINER_IP:8080/error
* http://$CONTAINER_IP:8080/env
* http://$CONTAINER_IP:8080/configprops
* http://$CONTAINER_IP:8080/autoconfig