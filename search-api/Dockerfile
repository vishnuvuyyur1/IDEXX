### STAGE 1: Build ###
FROM maven:3-openjdk-11 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
### STAGE 2: Run ###
FROM openjdk:11
EXPOSE 8080
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/search-api.jar /app/ 
ENTRYPOINT ["java","-jar","search-api.jar"]