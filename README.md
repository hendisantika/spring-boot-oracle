# spring-boot-oracle

Run the Spring Boot application locally

```shell
mvnw clean package -Dnet.bytebuddy.experimental=true
mvnw clean spring-boot:run
```

### Oracle Database

And last, but not least, you will need the Oracle Database container image so we can run a local database to test
against.
If you don’t already have it, you will need to go to Oracle Container Registry first, and navigate to “Database,” then
“Enterprise,” and accept the license agreement, then pull the image with these commands:

```shell
docker login container-registry.oracle.com -u your.name@wherever.com
docker pull container-registry.oracle.com/database/enterprise:21.3.0.0
```

Then you can start a database with this command:

```shell
docker run -d \
--name oracle-db \
-p 1521:1521 \
-e ORACLE_PWD=Welcome123 \
-e ORACLE_SID=ORCL \
-e ORACLE_PDB=PDB1 \
container-registry.oracle.com/database/enterprise:21.3.0.0
```

The first time you start it up, it will create a database instance for you. This takes a few minutes, you can watch the
logs to see when it is done:

```shell
docker logs -f oracle-db
```

You will see this message in the logs when it is ready:

```shell
#########################
DATABASE IS READY TO USE!
#########################
```

You can then stop and start the database container as needed – you won’t need to wait for it to create the database
instance each time, it will stop and start in just a second or two.

```shell
docker stop oracle-db
docker start oracle-db
```

You are going to want to grab its IP address for later on, you can do that with this command:

```shell
docker inspect oracle-db | grep IPAddress
```

This container image has SQL*Plus in it, and you can use that as a database command line client, but I prefer the new
Oracle SQLcl which is a lot nicer – it has completion and arrow key navigation and lots of other cool new features.
Here’s a permalink for the latest version: https://download.oracle.com/otn_software/java/sqldeveloper/sqlcl-latest.zip

You can just unzip this and add it to your path too, like Maven and Java.

You can connect to the database using SQLcl like this (use the IP address you got above):

```shell
sql sys/Welcome123@//172.12.0.2:1521/pdb1 as sysdba
```

Well, that’s about everything we need! In the next post we’ll get started building a Spring Boot microservice!
