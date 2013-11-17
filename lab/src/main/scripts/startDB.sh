#! /bin/bash
java -cp ~/.m2/repository/org/hsqldb/hsqldb/2.3.1/hsqldb-2.3.1.jar:src/main/resources org.hsqldb.Server -database.0 file:testdb -dbname.0 testdb &
