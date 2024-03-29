#!/bin/bash

count=1
tail -n 3 /etc/profile | while read
do
	echo "Line-$count：$REPLY"
	count=$[ $count + 1 ]
done
echo "Finish read file."
