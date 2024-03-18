#!/bin/bash

if [ "$1" -eq "--add" ] && [ -n "$2" ]
then
	echo "$2" >> issh.data
fi

echo < $BOOK_HOME/ishell/issh.data
