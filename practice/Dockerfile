FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR  /app
COPY . .
RUN mvn clean install

FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY --from=build /app/target/practice-3.3.0.jar practice.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar",  "/app/practice.jar" ]