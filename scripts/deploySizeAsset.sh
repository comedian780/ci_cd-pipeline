#!/bin/sh

asset_server=$1
vm_name=$2

eval $(docker-machine env ${vm_name})
docker run -d --restart always --network=parcelassetnetwork --name=parcel-asset-size "${asset_server}:443/parcel-asset-size"
eval $(docker-machine env -u)
