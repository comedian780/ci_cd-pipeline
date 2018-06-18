#!/bin/sh

nsupdate -k tsig-key.private 
server localhost
zone allgaeu-parcel-service.com
update delete www.allgaeu-parcel-service.com. A
update add www.allgaeu-parcel-service.com. 86400 A 192.168.99.101
show
send
exit
