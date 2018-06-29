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

JPA 2.2 requires Hibernate 5.3+.

Reference: http://hibernate.org/orm/releases/5.3/
    
    
# To run the project with JPA 2.1 and EclipseLink  

- First change the Spring profile in `net.gfu.seminar.spring.helloworld.GuestDaoTest` to:

```
@ActiveProfiles(profiles = { "jpa","eclipseLink" })
```
 
- Then run Maven with:

    mvn clean install -P eclipseLink
    
JPA 2.2 requires EclipseLink 2.7+.

Reference: http://www.eclipse.org/eclipselink/   
    
# To run the project with JPA 2.1 and OpenJPA   

- First change the Spring profile in `net.gfu.seminar.spring.helloworld.GuestDaoTest` to:

```
@ActiveProfiles(profiles = { "jpa","openJpa" })
``` 

- Then run Maven with:

    mvn clean install -P openJpa

OpenJPA 2.4+ supports JPA 2.0. OpenJPA 3.0 is targetting JPA 2.1. 
Currently there is no development planned for supporting JPA 2.2.

Reference: http://openjpa.apache.org