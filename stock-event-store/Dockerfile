FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=/build/libs/stock-event-store-0.0.1.jar
COPY ${JAR_FILE} stock-event-store-0.0.1.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/stock-event-store-0.0.1.jar"]