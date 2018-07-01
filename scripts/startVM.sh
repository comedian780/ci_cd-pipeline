vm_name=$1
docker-machine ls
echo "Checking if machine exists"
if(docker-machine ls -q | grep "^$vm_name\$"); then
  echo "Checking if machine is stopped"
  if(docker-machine status $vm_name | grep "^Stopped\$"); then
    echo "Starting machine"
    docker-machine start $vm_name
  fi
  echo "Machine is running"
fi
docker-machine ls
echo "Machine's URL"
echo $(docker-machine url $vm_name | grep -oP "tcp://\K[^:]+")
