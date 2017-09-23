# Docker Images für Datenbanken
## MySQL

    docker pull mysql
    docker run --name mysql -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 -d mysql:5.7
    
## PostgreSQL

    docker pull postgres
    docker run --name postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:9.6

## MS SQL Server
    
    docker pull microsoft/mssql-server-windows-express
    docker pull microsoft/mssql-server-linux
    docker run --name mssql -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=password' -p 1433:1433 -d microsoft/mssql-server-linux    

## Oracle

    docker pull wnameless/oracle-xe-11g
    docker run --name oracle -e ORACLE_ALLOW_REMOTE=true -p 49160:22 -p 1521:1521 -d wnameless/oracle-xe-11g
    