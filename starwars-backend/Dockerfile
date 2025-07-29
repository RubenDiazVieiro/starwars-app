FROM openjdk:21-jdk-slim

WORKDIR /app

# Copiar archivos de Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw

# Descargar dependencias
RUN ./mvnw dependency:go-offline

# Copiar código fuente
COPY src ./src

# Compilar la aplicación
RUN ./mvnw clean package -DskipTests

# Exponer puerto
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "target/starwars-backend-0.0.1-SNAPSHOT.jar"]