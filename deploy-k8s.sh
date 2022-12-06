#!/bin/bash

# install ingress controller
helm upgrade --install ingress-nginx ingress-nginx \
  --repo https://kubernetes.github.io/ingress-nginx \
  --namespace ingress-nginx --create-namespace

CONTAINER_REGISTRY="docker.io"
ACCOUNT_NAME=<DOCKER_HUB_USERNAME>
AUTHOR_IMAGE_TAG=<IMAGE_TAG>
BOOK_IMAGE_TAG=<IMAGE_TAG>
COMMENT_IMAGE_TAG=<IMAGE_TAG>

AUTHOR_SERVICE_CONTAINER_IMAGE="${CONTAINER_REGISTRY}/${ACCOUNT_NAME}/bookdb-author-service:${AUTHOR_IMAGE_TAG}"
BOOK_SERVICE_CONTAINER_IMAGE="${CONTAINER_REGISTRY}/${ACCOUNT_NAME}/bookdb-book-service:${BOOK_IMAGE_TAG}"
COMMENT_SERVICE_CONTAINER_IMAGE="${CONTAINER_REGISTRY}/${ACCOUNT_NAME}/bookdb-comment-service:${COMMENT_IMAGE_TAG}"

# databases
kubectl apply -f k8s/author-api-mysql-depl.yml
kubectl apply -f k8s/book-api-mysql-depl.yml

# message broker
kubectl apply -f k8s/rabbitmq-depl.yml

# services
sed "s,__DOCKER_IMAGE__,${AUTHOR_SERVICE_CONTAINER_IMAGE}," k8s/author-api-depl.yml | kubectl apply -f -
sed "s,__DOCKER_IMAGE__,${BOOK_SERVICE_CONTAINER_IMAGE}," k8s/book-api-depl.yml | kubectl apply -f -
sed "s,__DOCKER_IMAGE__,${COMMENT_SERVICE_CONTAINER_IMAGE}," k8s/comment-api-depl.yml | kubectl apply -f -

# api gateway
kubectl apply -f k8s/ingress-srv.yml
