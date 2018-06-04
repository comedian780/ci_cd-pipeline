#!/bin/sh

echo "Creating VM"
docker-machine create --driver virtualbox --engine-insecure-registry 193.174.205.28:443 parcel-test
echo "Changing settings to VM's docker"
eval $(docker-machine env parcel-test)
echo "Creating network for containers"
docker network create --driver bridge parcelnetwork
echo "Starting containers"
docker run -d --restart always --network=parcelnetwork -p 3306:3306 --name=parcel-db 193.174.205.28:443/parcel-db
docker run -d --restart always --network=parcelnetwork -p 80:80 --name=parcel-frontend 193.174.205.28:443/parcel-frontend
docker run -d --restart always --network=parcelnetwork --name=parcel-webservice -p 8443:8443 193.174.205.28:443/parcel-api
echo "Reset to host's docker"
eval $(docker-machine env -u)
echo "URL des Testservers:"
docker-machine url parcel-test
