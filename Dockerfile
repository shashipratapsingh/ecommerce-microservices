# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Set environment variable
ENV APP_HOME=/app
WORKDIR $APP_HOME

# Copy the jar file (make sure your jar is built first!)
COPY target/Profile-Service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on (example: 8083 for Product service)
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
