#! /bin/bash
java -cp ~/.m2/repository/org/hsqldb/hsqldb/2.3.4/hsqldb-2.3.4.jar:src/main/resources org.hsqldb.Server -database.0 file:testdb -dbname.0 testdb &
