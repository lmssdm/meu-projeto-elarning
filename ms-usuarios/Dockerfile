# Estágio 1: Build com Maven (usando a imagem oficial do Maven com Temurin 17)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar o cache do Docker
COPY pom.xml .
# Baixa as dependências
RUN mvn -q -DskipTests dependency:go-offline || true

# Copia o código-fonte
COPY src src

# Compila o projeto e empacota o .jar
RUN mvn -q -DskipTests package

# Estágio 2: Imagem final (usando apenas o JRE, que é menor)
FROM eclipse-temurin:17-jre
WORKDIR /app

# Variáveis de ambiente para otimizar a JVM (opcional, mas recomendado)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Copia o .jar final do estágio de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta interna que o Spring Boot usa
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]