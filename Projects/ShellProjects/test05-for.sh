#!/bin/bash

IFS=$'\n;"'
for i in `cat test01-demo.sh`
do
	echo $i
done
