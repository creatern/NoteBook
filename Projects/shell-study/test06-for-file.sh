#!/bin/bash

for file in /home/zjk/Documents/*
do
	if [ -d "$file" ]
	then
		echo "$file is a directory"
	elif [ -f "$file" ]
	then
		echo "$file is a common file"
	fi
done
