FROM maven:3.8.3-openjdk-17-slim AS builder

WORKDIR /build
COPY . /build

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
COPY --from=builder /build/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar", "-Ddebug", "-Xmx128m"]