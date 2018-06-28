#!/bin/sh

echo "set server parcel_apis/$vm_name addr $vm_url port 80" | socat stdio tcp4-connect:127.0.0.1:9999
echo "show stat" | socat stdio tcp4-connect:127.0.0.1:9999
