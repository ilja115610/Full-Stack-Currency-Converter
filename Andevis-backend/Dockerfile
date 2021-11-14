# Build stage
FROM maven:3.8.3-jdk-11-slim AS build
WORKDIR /backend-app
RUN chgrp -R 0 /backend-app && \
    chmod -R g=u /backend-app
COPY pom.xml .
COPY src ./src
RUN mvn install
# Package stage
FROM openjdk:11
USER root
WORKDIR /backend-app
RUN chgrp -R 0 /backend-app && \
    chmod -R g=u /backend-app
COPY --from=build /backend-app/target/Andevis-backend-0.0.1.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Andevis-backend-0.0.1.jar"]
