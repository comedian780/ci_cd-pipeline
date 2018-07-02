#!/bin/sh

vm_name=$1
if(docker-machine ls -q | grep "^$vm_name\$"); then
  if(docker-machine status $vm_name | grep "^Running\$"); then
    docker-machine stop $vm_name
  fi
  docker-machine rm $vm_name -y
fi
echo "Creating VM for $vm_name"
docker-machine create --driver virtualbox --engine-insecure-registry asset.allgaeu-parcel-service.com:443 --engine-insecure-registry 193.174.205.28:443 $vm_name
echo "Changing settings to VM's docker"
eval $(docker-machine env ${vm_name})
echo "Creating network for containers"
docker network create --driver bridge parcelnetwork
echo "Starting containers"
docker run -d --restart always --network=parcelnetwork --name=parcel-db asset.allgaeu-parcel-service.com:443/parcel-db
docker run -d --restart always --network=parcelnetwork -p 80:80 --name=parcel-frontend asset.allgaeu-parcel-service.com:443/parcel-frontend
docker run -d --restart always --network=parcelnetwork --name=parcel-webservice1 asset.allgaeu-parcel-service.com:443/parcel-api
docker run -d --restart always --network=parcelnetwork --name=parcel-webservice2 asset.allgaeu-parcel-service.com:443/parcel-api
docker run -d --restart always --network=parcelnetwork -p 8443:8443 --name=parcel-proxy asset.allgaeu-parcel-service.com:443/parcel-proxy
echo "Reset to host's docker"
eval $(docker-machine env -u)
echo "VM URL"
echo $(docker-machine url $vm_name | grep -oP "tcp://\K[^:]+")
