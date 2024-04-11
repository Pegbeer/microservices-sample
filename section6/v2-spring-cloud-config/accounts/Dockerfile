#Start with a base image containing the Java Runtime
FROM openjdk:17-jdk-slim
LABEL authors="Pegbeer"

#Adds the application jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Excecution application commands
ENTRYPOINT ["java", "-jar","accounts-0.0.1-SNAPSHOT.jar"]