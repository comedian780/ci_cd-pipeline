#!/bin/sh

asset_server=$1
vm_name=$2
version=$3

if (echo "cat ./state" | docker-machine ssh $vm_name | grep -s "^green\$"); then
  deployment_color="blue"
  echo "echo blue > state" | docker-machine ssh $vm_name
else
  deployment_color="green"
  echo "echo green > state" | docker-machine ssh $vm_name
fi


eval $(docker-machine env ${vm_name})
if (docker ps | grep parcel-webservice-${deployment_color}-1); then
  docker stop parcel-webservice-${deployment_color}-1
fi
if (docker container ls -a | grep parcel-webservice-${deployment_color}-1); then
  docker rm -f parcel-webservice-${deployment_color}-1
fi
if (docker ps | grep parcel-webservice-${deployment_color}-2); then
  docker stop parcel-webservice-${deployment_color}-2
fi
if (docker container ls -a | grep parcel-webservice-${deployment_color}-2); then
  docker rm -f parcel-webservice-${deployment_color}-2
fi
docker run -d --restart always --network=parcelnetwork --name=parcel-webservice-${deployment_color}-1 ${asset_server}:443/parcel-api:0.$version
docker run -d --restart always --network=parcelnetwork --name=parcel-webservice-${deployment_color}-2 ${asset_server}:443/parcel-api:0.$version
if (docker ps | grep parcel-proxy); then
  docker stop parcel-proxy
fi
if (docker container ls -a | grep parcel-proxy); then
  docker rm -f parcel-proxy
fi
docker run -d --restart always --network=parcelnetwork -p 8443:8443 -e STATE=${deployment_color} --name=parcel-proxy ${asset_server}:443/parcel-proxy
eval $(docker-machine env -u)
