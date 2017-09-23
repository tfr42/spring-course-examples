## Swagger2 with Spring REST API
About Swagger at https://swagger.io/

# Additional dependencies 
Swagger2 for Spring MVC requires:

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

## Configuration of Spring MVC Controller
Additional configuration of the controller:

    <mvc:resources mapping="swagger-ui.html"
				 location="classpath:/META-INF/resources/" />
    <mvc:resources mapping="/webjars/**"
				 location="classpath:/META-INF/resources/webjars/" />
    <context:annotation-config/>
    <bean class="springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration"/>

Swagger UI available at [http://localhost:8080/helloWorldSpringREST/rest/swagger-ui.html]
Swagger API at: [http://localhost:8080/helloWorldSpringREST/rest/v2/api-docs]