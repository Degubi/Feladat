FROM openjdk:23-jdk-slim
EXPOSE 8080
COPY target/Feladat-*.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar", "--spring.profiles.active=production" ]
