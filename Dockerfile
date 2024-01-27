FROM eclipse-temurin:17.0.9_9-jdk
WORKDIR /app
COPY target/Fiara-amidy-0.0.1-SNAPSHOT.jar voiture.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "voiture.jar"]
