# Etapa 1: Usamos uma imagem com Maven + Java para compilar o projeto
FROM maven:3.9.6-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Criamos uma imagem final mais leve apenas com Java
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta 8080 (a da aplicação Spring Boot)
EXPOSE 8080

# Comando que executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]