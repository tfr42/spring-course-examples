# RESTful web service with Spring Web MVC and JAX-RS

This example contains two simple `HelloWorld` resources demonstrating the 
basic capabilities of both APIs.

## Running the web app
Deploy the WAR file to a Servlet container of your choice or run 

Apache Tomcat with:
    
    mvn tomcat7:run

Eclipse Jetty with:    
    
    mvn jetty:run
    
## JAX-RS example

http://localhost:8080/helloWorldSpringREST/jaxrs/helloworld 
 
## Spring Web MVC example
 
http://localhost:8080/helloWorldSpringREST/rest/helloworld

### Swagger2 with Spring REST API
About Swagger: https://swagger.io/

#### Additional dependencies 
Swagger2 with Spring MVC requires additional Maven dependencies:

    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>${swaggerfox.version}</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>${swaggerfox.version}</version>
    </dependency> 

see more at https://springfox.github.io/springfox/docs/current/

####Configuration of Spring MVC Controller
Additional configuration of the `controller-servlet.xml`:

    <mvc:resources mapping="swagger-ui.html"
				 location="classpath:/META-INF/resources/" />
    <mvc:resources mapping="/webjars/**"
				 location="classpath:/META-INF/resources/webjars/" />
    <context:annotation-config/>
    <bean class="springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration"/>

#### Swagger UI
- Swagger UI available at http://localhost:8080/helloWorldSpringREST/rest/swagger-ui.html
- Swagger API at: http://localhost:8080/helloWorldSpringREST/rest/v2/api-docs