FROM gradle:jdk17 AS build

ARG BUILD_DIR=/home/build

COPY . ${BUILD_DIR}
WORKDIR ${BUILD_DIR}

RUN gradle book-api:build --no-daemon

FROM openjdk:17.0

RUN mkdir /app

EXPOSE 80

ENV PORT=80
ENV DB_HOST=book-api-mysql
ENV DB_PORT=3306
ENV DB_NAME=prod
ENV DB_USER=mysql
ENV DB_PASSWORD=thisIsReallyStrongPassword123
ENV RABBIT_MQ_HOST=rabbitmq
ENV RABBIT_MQ_PASSWORD=rabbitmq
ENV RABBIT_MQ_USERNAME=rabbitmq

COPY --from=build "/home/build/book-api/build/libs/*.jar" /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
