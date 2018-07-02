#!/bin/sh

mode=$1

echo "Changing DNS to $mode environment"
cat ~/dnsconfig/$mode > /etc/bind/parcel/db.allgaeu-parcel-service.com

#rndc reload
