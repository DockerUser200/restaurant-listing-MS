# Use official OpenJDK 17 image as base
FROM eclipse-temurin:17-jdk
# Set working directory
WORKDIR /opt
# Copy your Java app (adjust as needed)
COPY target/*.jar /opt/app.jar
# Set entrypoint to run the JAR
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]


