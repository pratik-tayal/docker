# FROM openjdk:17-jdk-alpine

# WORKDIR /app

# COPY target/spring-boot-3-rest-api-example-0.0.1-SNAPSHOT.jar app.jar

# EXPOSE 8080

# ENTRYPOINT ["java", "-jar", "app.jar"]


# Multi-staging

FROM maven:3.9.9-eclipse-temurin-17 AS make
WORKDIR /app
COPY . . 
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=make /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]