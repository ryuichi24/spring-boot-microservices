#!/bin/bash

# all => all resources 
# --all => all objects
kubectl delete all --all -n default

kubectl delete all --all -n ingress-nginx
