# Alpine because it's lighter
FROM openjdk:8-jdk-alpine
MAINTAINER Swapnil Ganjale

# Set ENV variables
ENV PORT=8070
ENV DISCOVERY_URL="http://localhost:8761/"

# Add JAR file and run it as entrypoint
ADD target/auth-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port
EXPOSE 8070
EXPOSE 5432
