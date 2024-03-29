#!/bin/bash

set -- `getopt -q ab:cd "$@"`

while [ -n "$1" ]
do
	case "$1" in
		-a) echo "-a option" ;;
		-b) param=$2
			echo "-b option has parameter=$param"
			shift ;;
		-c) echo "-c option" ;;
		--) shift
			break ;;
		*)
			echo "$1 is not a option" ;;
	esac
	shift
done
