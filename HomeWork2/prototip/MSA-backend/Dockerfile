FROM openjdk:11-jdk-slim
WORKDIR /backend
COPY target/MSA-0.0.1-SNAPSHOT.jar /app/backend-app.jar
EXPOSE 8081
CMD ["java", "-jar", "/backend/backend-app.jar"]