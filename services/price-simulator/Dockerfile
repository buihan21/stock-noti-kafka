FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/price-simulator-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
ENV SPRING_PROFILES_ACTIVE=docker
