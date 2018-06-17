#!/bin/sh

vm_name="parcel-test"
#test_server1_name="parcel-test1"
#test_server2_name="parcel-test2"
#echo "Starting Test VM 1"
#sh initializeVM.sh $test_server1_name
#echo "Starting Test VM 2"
#sh initializeVM.sh $test_server2_name
echo "Checking if Loadbalancer exists"
if(docker-machine ls -q | grep "^$vm_name\$"); then
  if(docker-machine status $vm_name | grep "^Running\$"); then
    docker-machine stop $vm_name
  fi
  docker-machine rm $vm_name -y
fi
echo "Starting Load Balancer"
docker-machine create --driver virtualbox --engine-insecure-registry 193.174.205.28:443 $vm_name
echo "Changing settings to VM's docker"
eval $(docker-machine env $vm_name)
echo "Creating network for containers"
docker network create --driver bridge parcelnetwork
echo "Starting containers"
docker run -d --restart always --network=parcelnetwork --name=parcel-db 193.174.205.28:443/parcel-db
docker run -d --restart always --network=parcelnetwork -p 80:80 --name=parcel-frontend 193.174.205.28:443/parcel-frontend
docker run -d --restart always --network=parcelnetwork --name=parcel-webservice1 193.174.205.28:443/parcel-api
docker run -d --restart always --network=parcelnetwork --name=parcel-webservice2 193.174.205.28:443/parcel-api
docker run -d --restart always --network=parcelnetwork -p 8443:8443 --name=parcel-proxy 193.174.205.28:443/parcel-proxy
echo "Reset to host's docker"
eval $(docker-machine env -u)
#echo "Installing socat"
#echo "tce-load -wi socat.tcz" | docker-machine ssh $vm_name
#echo "Setting IPs for backend servers"
#changeIpScript=$(cat changeProxyIp.sh)
#test_server1_url=$(docker-machine url $test_server1_name | grep -oP "tcp://\K[^:]+")
#test_server2_url=$(docker-machine url $test_server2_name | grep -oP "tcp://\K[^:]+")
#echo "vm_name=$test_server1_name &&\ vm_url=$test_server1_url &&\ $changeIpScript" | docker-machine ssh $vm_name
#echo "vm_name=$test_server2_name &&\ vm_url=$test_server2_url &&\ $changeIpScript" | docker-machine ssh $vm_name
echo "Loadbalancer's URL"
echo $(docker-machine url $vm_name | grep -oP "tcp://\K[^:]+")
