FROM openjdk:17-jdk-alpine
COPY build/libs/currency-rate-service-1.0.0.jar currency-rate-service-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/currency-rate-service-1.0.0.jar"]