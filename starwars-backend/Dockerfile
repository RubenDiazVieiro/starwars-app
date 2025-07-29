FROM openjdk:21-jdk-slim

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "-jar", "target/starwars-backend-0.0.1-SNAPSHOT.jar"]