
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/smartfit-1.0.0.jar app.jar

RUN mkdir -p /app/data

ENTRYPOINT ["java", "-jar", "app.jar"]
