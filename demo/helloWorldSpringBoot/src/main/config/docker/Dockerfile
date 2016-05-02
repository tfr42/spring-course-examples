FROM java:8
MAINTAINER Torsten Friebe <tfr@users.sourceforge.net>
VOLUME /tmp
EXPOSE 8080
ADD helloWorldSpringBoot.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]