# Multi-stage build for Spring Boot (Java 17)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
# Copy Maven descriptors first to leverage layer caching
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline || true
# Copy sources
COPY src src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
ENV JAVA_OPTS="-Xms256m -Xmx512m"
# Copy final jar
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]