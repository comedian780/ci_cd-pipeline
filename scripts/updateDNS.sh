#!/bin/sh

mode=$1
cat ~/dnsconfig/$mode > /etc/bind/parcel/db.allgaeu-parcel-service.com
