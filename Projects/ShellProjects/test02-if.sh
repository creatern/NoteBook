#!/bin/bash

if ( echo "Hello"; cat /ect/no-file)
then
	echo "T"
else
	echo "F"
fi
