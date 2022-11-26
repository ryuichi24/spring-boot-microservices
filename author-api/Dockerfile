FROM gradle:jdk17 AS build

ARG BUILD_DIR=/home/build

COPY . ${BUILD_DIR}
WORKDIR ${BUILD_DIR}

RUN gradle author-api:build --no-daemon

FROM openjdk:17.0

RUN mkdir /app

EXPOSE 80

COPY --from=build "/home/build/author-api/build/libs/*.jar" /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
