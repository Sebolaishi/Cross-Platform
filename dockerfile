FROM openjdk:8-jdk-alpine

COPY ./target/cross-platform-1.0-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch cross-platform-1.0-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","cross-platform-1.0-SNAPSHOT.jar"]
