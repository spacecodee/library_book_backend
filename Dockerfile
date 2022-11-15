# AS <NAME> to name this stage as maven
FROM maven:3.8.6-eclipse-temurin-11 AS maven
LABEL MAINTAINER="spacecodee@gmail.com"
WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
ONBUILD RUN mvn clean package
# restart the build process
RUN mvn package -DskipTests

# For Java 11,
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=library_book_backend.jar

WORKDIR /opt/app

# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","library_book_backend.jar"]