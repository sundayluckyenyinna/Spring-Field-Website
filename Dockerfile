# Build stage
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Package stage
FROM eclipse-temurin:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/website-1.1.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "website-1.1.jar"]