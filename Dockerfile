#FROM maven:3.3-jdk-8-onbuild
FROM java:8
EXPOSE 8430
COPY target/simple-verticle-1.0-SNAPSHOT-fat.jar /
ENTRYPOINT ["java","-jar","simple-verticle-1.0-SNAPSHOT-fat.jar"]
