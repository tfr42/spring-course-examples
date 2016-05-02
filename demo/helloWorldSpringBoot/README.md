# Spring Boot demo with Docker

## Spring Boot container

To package the Spring Boot application run:

    % mvn package

To run the Spring Boot application execute:
 
    % mvn spring-boot:run
    
Then open http://localhost:8080

## Spring Boot application inside Docker container

To build the Docker image run:

    % cd src/main/config/docker
    % docker build -t tfr42/hello-world-springboot .

Don't forget to copy the JAR file to the Docker source directory so that it can be added to the Docker image.  

### Use the Maven plugin for Docker
    
To build the Docker image with the Maven plugin for Docker run:
        
    % mvn clean package docker:build

To run the Docker container execute:
    
    % docker run -p 8080:8080 --rm tfr42/hello-world-springboot
     
Then open http://localhost:8080 or in case you run Docker with docker-machine then open http://$IP:8080, where $IP
     is taken from ```docker-machine ip <DOCKER_VM_NAME>```.

See the [Docker web site](https://www.docker.com/) for more information about Docker! 