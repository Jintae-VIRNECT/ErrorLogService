# Build stage
FROM openjdk:11-jdk as build
WORKDIR /usr/app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN ./gradlew build -x test


# Run stage
FROM openjdk:11-jre-slim
VOLUME /tmp
COPY --from=build /usr/app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8765