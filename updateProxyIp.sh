#!/bin/sh

vm_name="parcel-loadbalancer"
test_server1_name="parcel-test1"
test_server2_name="parcel-test2"
changeIpScript=$(cat changeProxyIp.sh)
test_server1_url=$(docker-machine url $test_server1_name | grep -oP "tcp://\K[^:]+")
test_server2_url=$(docker-machine url $test_server2_name | grep -oP "tcp://\K[^:]+")
echo "vm_name=test_server1_name &&\ vm_url=$test_server1_url &&\ $changeIpScript" | docker-machine ssh $vm_name
echo "vm_name=test_server2_name &&\ vm_url=$test_server2_url &&\ $changeIpScript" | docker-machine ssh $vm_name
