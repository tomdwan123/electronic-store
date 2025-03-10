FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven wrapper and pom.xml to leverage Docker caching
COPY pom.xml .

# Download dependencies separately to speed up builds
RUN mvn dependency:go-offline -B

# Copy source code
COPY src src

# Build the application
RUN mvn clean package -DskipTests

# Create runtime image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose application port
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
