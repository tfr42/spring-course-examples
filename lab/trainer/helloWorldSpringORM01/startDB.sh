#! /bin/bash
java -cp ~/.m2/repository/org/hsqldb/hsqldb/2.2.8/hsqldb-2.2.8.jar:src/main/resources org.hsqldb.Server -database.0 file:testdb -dbname.0 testdb &
java -cp ~/.m2/repository/org/hsqldb/hsqldb/2.2.8/hsqldb-2.2.8.jar org.hsqldb.util.DatabaseManagerSwing &

#java -cp ~/.m2/repository/hsqldb/hsqldb/1.8.0.10/hsqldb-1.8.0.10.jar:. org.hsqldb.Server -database.0 file:testdb -dbname.0 testdb &
#java -cp ~/.m2/repository/hsqldb/hsqldb/1.8.0.10/hsqldb-1.8.0.10.jar org.hsqldb.util.DatabaseManagerSwing &
