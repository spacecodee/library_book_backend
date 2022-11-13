FROM adoptopenjdk:11-jre-hotspot
MAINTAINER spacecodee.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/library_book_backend-0.0.1-SNAPSHOT.jar"]