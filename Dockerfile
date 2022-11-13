FROM maven:3.8.6-eclipse-temurin-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM eclipse-temurin:11-jre
MAINTAINER spacecodee.com
COPY COPY --from=build /home/app/target/library_book_backend.jar /usr/local/lib/library_book_backend.jar/
ENTRYPOINT ["java","-jar","library_book_backend.jar"]