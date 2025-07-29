FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY build/libs/*.jar kotlin_practice.jar

ENTRYPOINT ["java", "-jar", "kotlin_practice.jar"]
