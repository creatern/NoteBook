#!/bin/bash

case $1 in
"1")
	echo "选项1";;
"2" | hello | 3)
	echo "选项2";;
*) echo
	"其他选项";;
esac
