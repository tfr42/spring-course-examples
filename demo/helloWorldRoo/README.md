Building a web app with Spring Roo
==================================
Preparation:
Install STS with Spring IDE Roo Support or Spring Roo Shell. See [Spring Roo Project](http://projects.spring.io/spring-roo/)
for more information.

Then run in roo shell:

```
hint
project --topLevelPackage net.gfu.seminar.spring.roo --projectName helloWorldSpringRoo --java 7 --packaging JAR
jpa setup --provider HIBERNATE --database HYPERSONIC_IN_MEMORY
entity jpa --class ~.domain.Guest --testAutomatically
field string --fieldName firstname --notNull --sizeMin 2
field string --fieldName lastname --notNull --sizeMin 2
hint
perform package
perform test
web mvc setup
web mvc all --package ~.web
perform test
```
