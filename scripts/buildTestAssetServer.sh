#!/bin/sh

asset_ip=$1
vm_name=$2



if(docker-machine ls -q | grep "^$vm_name\$"); then
  echo "Checking if $vm_name already exists"
  if(docker-machine status $vm_name | grep "^Running\$"); then
    echo "Stopping $vm_name"
    docker-machine stop $vm_name
  fi
  echo "Removing $vm_name"
  docker-machine rm $vm_name -y
fi
echo "Creating VM for $vm_name"
docker-machine create --driver virtualbox --engine-insecure-registry asset.allgaeu-parcel-service.com:443 --engine-insecure-registry $asset_ip:443 $vm_name
eval $(docker-machine env ${vm_name})
docker run -d -p 443:5000 --restart=always --name registry registry:2
echo "Starting asset-CDN"
docker network create --driver bridge parcelassetnetwork
docker pull "${asset_ip}:443/parcel-asset-option"
docker pull "${asset_ip}:443/parcel-asset-address"
docker pull "${asset_ip}:443/parcel-asset-price"
#docker pull "${asset_ip}:443/parcel-asset-size"
docker pull "${asset_ip}:443/parcel-asset"
docker run -d --restart always --network=parcelassetnetwork --name=parcel-asset-option "${asset_ip}:443/parcel-asset-option"
docker run -d --restart always --network=parcelassetnetwork --name=parcel-asset-address "${asset_ip}:443/parcel-asset-address"
docker run -d --restart always --network=parcelassetnetwork --name=parcel-asset-price "${asset_ip}:443/parcel-asset-price"
#docker run -d --restart always --network=parcelassetnetwork --name=parcel-asset-size "${asset_ip}:443/parcel-asset-size"
docker run -d --restart always --network=parcelassetnetwork -p 8444:80 --name=parcel-asset "${asset_ip}:443/parcel-asset"
docker ps
echo "Migrating images to $vm_name"
docker pull $asset_ip:443/parcel-db
docker tag $asset_ip:443/parcel-db asset.allgaeu-parcel-service.com:443/parcel-db
docker push asset.allgaeu-parcel-service.com:443/parcel-db
docker pull $asset_ip:443/parcel-frontend
docker tag $asset_ip:443/parcel-frontend asset.allgaeu-parcel-service.com:443/parcel-frontend
docker push asset.allgaeu-parcel-service.com:443/parcel-frontend
docker pull $asset_ip:443/parcel-proxy
docker tag $asset_ip:443/parcel-proxy asset.allgaeu-parcel-service.com:443/parcel-proxy
docker push asset.allgaeu-parcel-service.com:443/parcel-proxy
#docker pull $asset_ip:443/parcel-api
#docker tag $asset_ip:443/parcel-api asset.allgaeu-parcel-service.com:443/parcel-api
#docker push asset.allgaeu-parcel-service.com:443/parcel-api
curl -X GET asset.allgaeu-parcel-service.com:443/v2/_catalog
eval $(docker-machine env -u)
