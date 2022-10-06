#!/bin/sh

git clone https://github.com/ryuichi24/spring-boot-microservices.git \
    && cd spring-boot-microservices \
    && git checkout starter-template \
    && rm -rf .git \
    && ./make_env.sh \
    && rm ./init.sh
