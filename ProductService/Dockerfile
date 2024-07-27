# Use the official OpenJDK image as a parent image
FROM openjdk:17

# Copy the JAR file into the container
COPY target/ProductService-0.0.1-SNAPSHOT.jar app.jar


# Expose the port your service is running on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
