# ====== Etapa 1: Compilación ======
FROM maven:3.9.8-eclipse-temurin-17 AS builder

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml y descargar dependencias (cache eficiente)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y construir el JAR
COPY src ./src
RUN mvn clean package -DskipTests

# ====== Etapa 2: Ejecución ======
FROM eclipse-temurin:17-jdk-jammy

# Directorio donde vivirá la app
WORKDIR /app

# Copiar solo el JAR generado
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto del microservicio
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]