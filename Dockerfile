FROM maven:3.9.6-sapmachine-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean install

FROM openjdk:21-slim
ARG JAR_FILE=target/*.jar
COPY /target/code-challenge-swat-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]