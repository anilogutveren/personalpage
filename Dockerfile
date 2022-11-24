FROM eclipse-temurin:17-jre-focal
COPY ./target/personalpage-1.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","personalpage-1.jar"]