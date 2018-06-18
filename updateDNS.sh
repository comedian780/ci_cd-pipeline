#!/bin/sh


ip=192.168.99.100
cat $1 ; echo $ip ; echo send | nsupdate -v
