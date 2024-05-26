# Stage 1: Build the application
FROM openjdk:17-jdk-slim as builder

# Set the working directory in the container
WORKDIR /app

# Copy the project files into the container
COPY ./gradle_basic_demo .
WORKDIR /app
RUN chmod +x gradlew

# Build the application
RUN ./gradlew clean build

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built jar file from the builder stage
COPY --from=builder /app/build/libs/basic_demo-0.1.0.jar /app/basic_demo-0.1.0.jar

# Expose the port the application runs on
EXPOSE 59001

# Run the application
ENTRYPOINT ["java", "-cp", "/app/basic_demo-0.1.0.jar", "basic_demo.ChatServerApp", "59001"]
