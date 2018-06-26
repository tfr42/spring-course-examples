# How to run the project with a different JPA implementation

Per default a simple JDBC repository is used. To switch to a JPA based repository implementation the following 
changes to the configuration are required. You may see different behaviour of the JPA implementations and some 
unit tests may fail!

# To run the project with JPA 2.1 and Hibernate
- First change the Spring profile in `net.gfu.seminar.spring.helloworld.GuestDaoTest` to:

```
@ActiveProfiles(profiles = { "jpa","hibernate" })
```

- Then run Maven with:
    
    mvn clean install -P hibernate
    
The default Maven Profile is `hibernate`. So you can ommit the `-P` option.
    
    
# To run the project with JPA 2.1 and EclipseLink  

- First change the Spring profile in `net.gfu.seminar.spring.helloworld.GuestDaoTest` to:

```
@ActiveProfiles(profiles = { "jpa","eclipseLink" })
```
 
- Then run Maven with:

    mvn clean install -P eclipseLink
    
# To run the project with JPA 2.1 and OpenJPA   

- First change the Spring profile in `net.gfu.seminar.spring.helloworld.GuestDaoTest` to:

```
@ActiveProfiles(profiles = { "jpa","openJpa" })
``` 

- Then run Maven with:

    mvn clean install -P openJpa

