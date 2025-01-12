FROM openjdk:17-jdk-slim
WORKDIR /microservice
COPY target/MSA-0.0.1-SNAPSHOT.jar microservice-app.jar
EXPOSE 8082
CMD ["java", "-jar", "microservice-app.jar"]

