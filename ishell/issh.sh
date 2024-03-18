#!/bin/bash

ISSH_DATA=$BOOK_HOME/ishell/issh.data

if [ "$1" = "--add" ] && [ -n "$2" ]
then
	echo $2 >> issh.data
fi

case $1 in
	"")
		cat $ISSH_DATA
		;;
	"add" | "-a")
		if [ -n $2 ]
		then
			echo $2 >> $ISSH_DATA
			cat $ISSH_DATA
		else
			echo "Error: please input your user@host|ip"
		fi
		;;
	"del" | "-d")
		if [ -n $2 ]
		then
			sed -i "${2}d" $ISSH_DATA
			cat $ISSH_DATA
		else
			echo echo "Error: please choise witch lone you want to delete"
		fi
		;;
	*)
		echo "no commond found..."
		;;
esac

