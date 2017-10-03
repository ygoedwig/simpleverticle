FROM maven:3.3-jdk-8-onbuild
EXPOSE 8430
COPY . /sources/vertex/simpleverticle/simple-verticle-1.0-SNAPSHOT-fat.jar
ENTRYPOINT ["java","-jar","simple-verticle-1.0-SNAPSHOT-fat.jar"]
