#!/bin/sh

vm_name=$1
echo "Checking if machine exists"
if(docker-machine ls -q | grep "^$vm_name\$"); then
  echo "Checking if machine is running"
  if(docker-machine status $vm_name | grep "^Running\$"); then
    echo "Stopping machine"
    docker-machine stop $vm_name
  fi
fi
