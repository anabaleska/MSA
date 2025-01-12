FROM openjdk:11-jdk-slim
WORKDIR /microservice
COPY target/MSA-0.0.1-SNAPSHOT.jar /app/microservice-app.jar
EXPOSE 8082
CMD ["java", "-jar", "/microservice/microservice-app.jar"]