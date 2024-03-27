#!/bin/bash

trap "echo 'interrupt...'" SIGINT

while [ 1 -eq 1 ]
do
	sleep 3s
	echo "Hello"
done
