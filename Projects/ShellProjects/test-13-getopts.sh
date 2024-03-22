#!/bin/bash

while getopts :ab:c opt
do
	case "$opt" in
		a) echo "Found -a option" ;;
		b) echo "Found -b option with parameter value $OPTARG" ;;
		c) echo "Found -c option" ;;
		*) echo "Unknown option: $opt"
	esac
done

shift $[ $OPTIND - 1 ]

count=1
for param in "$@"
do
	echo "Parameter $count: $param"
	count=$[ $count + 1 ]
done
