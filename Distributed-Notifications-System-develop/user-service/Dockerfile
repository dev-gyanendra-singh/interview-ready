# Stage 1: Build Stage
FROM gradle:7.5.1-jdk17 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy source code into the container
COPY . /app

# Build the application using Gradle
RUN gradle clean build -x test --no-daemon

# Stage 2: Runtime Stage
FROM openjdk:24-slim-bullseye

# Set the working directory for the runtime container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=builder /app/build/libs/user-service.jar /app/user-service.jar

# Expose the application port
EXPOSE 3001

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/user-service.jar"]
