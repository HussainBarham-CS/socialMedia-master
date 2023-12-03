FROM maven:3.5.2-jdk-8 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:3.5.2-jdk-8-slim
COPY --from=build /target/social-media-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
