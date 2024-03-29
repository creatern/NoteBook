#!/bin/bash

i=10
while
	[ $i -ge 0 ]
do
	echo "inside $i"
	i=$[$i-2]
done > tmp.data
