FROM openjdk:8-jdk-alpine
EXPOSE 8175
COPY target/test_inside-v1.0.jar .
RUN apk add --no-cache bash
ENTRYPOINT ["java", "-jar", "test_inside-v1.0.jar"]