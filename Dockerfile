# Faza 1: Kompajliranje Java aplikacije pomoću Maven-a
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package

# Faza 2: Pokretanje aplikacije
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /build/target/smartfit-1.0.0.jar app.jar

RUN mkdir -p /app/data

ENTRYPOINT ["java", "-jar", "app.jar"]
