#!/bin/sh

docker-machine create --driver virtualbox --engine-insecure-registry 193.174.205.28:44 parcel-test
eval \$(docker-machine env parcel-test)
docker network create --driver bridge parcelnetwork
docker run -d --restart always --network=parcelnetwork -p 3306:3306 --name=parcel-db 193.174.205.28:443/parcel-db
docker run -d --restart always --network=parcelnetwork -p 80:80 --name=parcel-frontend 193.174.205.28:443/parcel-frontend
docker run -d --network=parcelnetwork --name=parcel-webservice -p 8443:8443 193.174.205.28:443/parcel-api ./start.sh
