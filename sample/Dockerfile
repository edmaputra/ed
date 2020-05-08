FROM openjdk:11-jdk-slim-buster
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 11000

ENTRYPOINT ["java", "-jar", "/app.jar"]
