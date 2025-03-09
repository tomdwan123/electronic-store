# Overview
This is a Spring Boot application that provides a cloud service for Electronic Store. The application is written in Java and uses H2 memory for database operations. It uses Maven for dependency management.

## Building
The service requires Java 21. While the project can be built with pre-installed Maven, we
encourage using Maven Wrapper instead, as this is what we use in Docker build

## Prerequisites
- Java 21
- Maven
- H2 database

## Built with libs
- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
- [Maven](https://maven.apache.org/) - Dependency Management
- [Flyway](https://flywaydb.org/) - Database Migrations

## Start app
### 1. Use command
- Step 1: Create a JAR file inside the target/ directory

```shell
$ mvn clean package
```

- Step 2: Start app with JAR file
```shell
$ java -jar target/electronic-store.jar
```

### 2. User docker
Electronic Store Service is supposed to be packaged as Docker image. We prepared a `Dockerfile` which performs
build step automatically. All necessary files are located in `src/main/deploy` folder.

- Step 1: Build docker image with name `electronic-store:latest`
```shell
$ docker build -t electronic-store:latest -f src/main/deploy/Dockerfile .
```

- Step 2: Run a docker container from image `electronic-store:latest` to start app
```shell
docker run -d -p 8081:8080 --name electronic-store electronic-store:latest
```

- Step 3: Verify the container is running
```shell
docker ps
```

- Step 4: Check container's logs
```shell
docker logs -f electronic-store
```

# Swagger
http://localhost:8081/electronic-store/api/swagger-ui/index.html

## Database Migrations
The project uses Flyway for database migrations. The migration scripts are located in `src/main/resources/db/migration`. Spring Boot automatically runs these migrations on startup.

## Testing
- Run all unit test
```shell
mvn test
```
- Run tests during the build process
```shell
mvn clean package
```
- Skip tests (if needed)
```shell
mvn clean package -DskipTests
```

## Deployment



