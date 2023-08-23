# todo give name for image

# Use an official OpenJDK base image with the desired version
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /build

# Copy the project's JAR file into the container
COPY target/movieland-0.0.1-SNAPSHOT.jar /build/movieland-0.0.1-SNAPSHOT.jar

# Expose the port that your web service will listen on
EXPOSE 8080

# Define the command to run when the container starts
CMD ["java", "-jar", "movieland-0.0.1-SNAPSHOT.jar"]





