FROM openjdk:8-jre
COPY target/*.jar /app.jar   # Works with Maven-generated JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]
