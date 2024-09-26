
FROM maven:3.9.9-eclipse-temurin-22 AS build


WORKDIR /app


COPY pom.xml .
COPY src ./src


RUN mvn clean package



FROM eclipse-temurin:22-jdk-alpine


WORKDIR /app


COPY --from=build /app/target/*.jar app.jar


EXPOSE 8080


CMD ["java", "-jar", "app.jar"]