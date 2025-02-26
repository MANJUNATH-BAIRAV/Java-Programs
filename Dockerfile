FROM openjdk:8-jre

# Copy the exact JAR file (Replace 'your-app.jar' with the actual JAR name)
COPY target/sqroott-1.0.jar /app.jar  

ENTRYPOINT ["java", "-jar", "/app.jar"]
