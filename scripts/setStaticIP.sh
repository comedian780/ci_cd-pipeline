#!/bin/sh

vm_name=$1
vm_ip=$2

echo "ifconfig eth1 192.168.99.${vm_ip} netmask 255.255.255.0 broadcast 192.168.99.255 up" | docker-machine ssh $vm_name sudo tee /var/lib/boot2docker/bootsync.sh > /dev/null
docker-machine restart $vm_name
docker-machine regenerate-certs $vm_name -f
