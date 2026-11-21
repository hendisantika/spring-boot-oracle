# spring-boot-oracle

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-oracle/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-oracle/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.x-6DB33F?logo=springboot)
![Oracle](https://img.shields.io/badge/Oracle%20Database-23ai%20Free-F80000?logo=oracle)

A simple Employee REST API built with **Spring Boot** + **Spring Data JDBC** on top of **Oracle Database 23ai Free**.

## Tech Stack

| Component        | Version / Tool                                  |
|------------------|-------------------------------------------------|
| Java             | **25** (Temurin recommended)                    |
| Spring Boot      | 4.1.x (Web, Data JDBC, Actuator, Docker Compose) |
| Database         | Oracle Database 23ai Free (`gvenzl/oracle-free`) |
| JDBC Driver      | `ojdbc11` + `ucp`                               |
| Build            | Maven Wrapper (`./mvnw`)                        |
| Testing          | JUnit 5, Testcontainers (Oracle Free)           |
| CI               | GitHub Actions                                  |
| Dependency bots  | Dependabot (Maven, GitHub Actions, Docker Compose) |

## Prerequisites

- JDK **25**
- Docker (used by Spring Boot Docker Compose support and by Testcontainers)

Check your Java version:

```shell
java -version
```

## Running the Application Locally

The project ships with a [`compose.yml`](compose.yml) that starts Oracle Database 23ai Free. Thanks to
`spring-boot-docker-compose`, the container is started automatically when you run the app:

```shell
./mvnw spring-boot:run
```

Or start the database manually first:

```shell
docker compose up -d
./mvnw spring-boot:run
```

Default connection settings (see [`application.properties`](src/main/resources/application.properties)):

| Property | Value                                        |
|----------|----------------------------------------------|
| URL      | `jdbc:oracle:thin:@//localhost:1521/FREEPDB1` |
| Username | `yu71`                                       |
| Password | `53cret`                                     |

Create the `EMPLOYEE` table and sample data using [`script/EMPLOYEE.SQL`](script/EMPLOYEE.SQL).

## Build & Test

```shell
./mvnw clean package
```

Integration tests spin up an Oracle Free container via **Testcontainers** (`gvenzl/oracle-free:23-slim-faststart`)
and wire it automatically with `@ServiceConnection`, so no local database is needed — only Docker.

## REST Endpoints

| Method   | Path              | Description                  |
|----------|-------------------|------------------------------|
| `GET`    | `/employees`      | List all employees           |
| `GET`    | `/employees/{id}` | Get employee by id           |
| `POST`   | `/employees`      | Create a new employee        |
| `PUT`    | `/employees/{id}` | Replace / upsert an employee |
| `DELETE` | `/employees/{id}` | Delete an employee           |

Example:

```shell
curl -X POST http://localhost:8080/employees \
  -H "Content-Type: application/json" \
  -d '{"name":"JOHN","job":"CLERK","salary":7902}'

curl http://localhost:8080/employees
```

Actuator health check: `GET http://localhost:8080/actuator/health`

## CI/CD & Dependency Management

- **GitHub Actions** ([`.github/workflows/maven.yml`](.github/workflows/maven.yml)) builds and tests the project on
  JDK 25 for every push and pull request to `main`, then submits the Maven dependency graph.
- **Dependabot** ([`.github/dependabot.yml`](.github/dependabot.yml)) keeps dependencies up to date:
    - Maven — daily, grouped (Spring Boot, Oracle JDBC, Testcontainers, Maven plugins)
    - GitHub Actions — weekly, grouped
    - Docker Compose images — weekly

## Using the Oracle Enterprise Image (Optional)

If you prefer the official Oracle image, log in to the Oracle Container Registry (navigate to *Database → Enterprise*
and accept the license agreement first), then:

```shell
docker login container-registry.oracle.com -u your.name@wherever.com
docker pull container-registry.oracle.com/database/enterprise:21.3.0.0

docker run -d \
  --name oracle-db \
  -p 1521:1521 \
  -e ORACLE_PWD=Welcome123 \
  -e ORACLE_SID=ORCL \
  -e ORACLE_PDB=PDB1 \
  container-registry.oracle.com/database/enterprise:21.3.0.0

docker logs -f oracle-db   # wait for "DATABASE IS READY TO USE!"
```

You can connect using [Oracle SQLcl](https://download.oracle.com/otn_software/java/sqldeveloper/sqlcl-latest.zip):

```shell
sql sys/Welcome123@//localhost:1521/pdb1 as sysdba
```

## References

- [Oracle Database Free container images](https://github.com/gvenzl/oci-oracle-free)
- [Getting started using JDBC with Oracle](https://www.oracle.com/database/technologies/getting-started-using-jdbc.html)
- [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/reference/testing/testcontainers.html)
