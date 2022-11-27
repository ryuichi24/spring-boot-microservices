FROM gradle:jdk17 AS build

ARG BUILD_DIR=/home/build

COPY ./book-api ${BUILD_DIR}/book-api
COPY ./shared ${BUILD_DIR}/shared
COPY ./build.gradle ${BUILD_DIR}
COPY ./settings.gradle ${BUILD_DIR}

WORKDIR ${BUILD_DIR}

RUN gradle book-api:build

FROM openjdk:17.0

RUN mkdir /app

EXPOSE 80

COPY --from=build "/home/build/book-api/build/libs/*.jar" /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
