FROM gradle:jdk17 AS build

ARG BUILD_DIR=/home/build

COPY ./comment-api ${BUILD_DIR}/comment-api
COPY ./shared ${BUILD_DIR}/shared
COPY ./build.gradle ${BUILD_DIR}
COPY ./settings.gradle ${BUILD_DIR}

WORKDIR ${BUILD_DIR}

RUN gradle comment-api:build

FROM openjdk:17.0

RUN mkdir /app

EXPOSE 80

COPY --from=build "/home/build/comment-api/build/libs/*.jar" /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
