FROM openjdk:17.0.2-jdk-slim-buster

ADD https://dl.google.com/linux/linux_signing_key.pub /tmp/

ARG JAR_FILE=build/libs/MaxBot-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]