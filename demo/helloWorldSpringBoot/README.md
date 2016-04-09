# Spring Boot demo with Docker

## Spring Boot container

To package the SpringBoot application run:

    % mvn package

To build the Spring Boot application run:
 
    % mvn spring-boot:run
    
Then open http://localhost:8080

## Spring Boot application inside Docker container

To build the Docker image run:

    % cd src/main/config/docker
    % docker build -t tfr42/hello-world-springboot:1.0.0-SNAPSHOT .

To run the Docker container execute:
    
    % docker run -p 8080:8080 --rm tfr42/hello-world-springboot:1.0.0-SNAPSHOT
     
Then open http://localhost:8080 or in case you run Docker with docker-Machine then open http://$IP:8080, where $IP
     is ```docker-machine ip <DOCKER_VM_NAME>```

See the [Docker web site](https://www.docker.com/) for more information about Docker! 