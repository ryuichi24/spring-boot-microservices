#!/bin/sh

DEV_CONTAINER_DIR=./.devcontainer
cp ${DEV_CONTAINER_DIR}/.env.example ${DEV_CONTAINER_DIR}/.env
rm ./make_env.sh
