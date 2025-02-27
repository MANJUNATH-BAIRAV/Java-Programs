# Use OpenJDK as base image
FROM openjdk:8-jdk-alpine

# Set working directory
WORKDIR /app

# Copy JAR file from build context
ARG JAR_FILE=target/java-app-1.0.jar
COPY ${JAR_FILE} app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
