FROM openjdk:8-jdk-alpine

COPY ./target/cross-platform-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch cross-platform-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","cross-platform-0.0.1-SNAPSHOT.jar"]
