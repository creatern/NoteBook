#!/bin/bash

PS3="Enter option...: "
select opt in "use #1" "use #2" "use #3" "use #4" "exit"
do
	case $opt in
		"use #1") 
			echo "#1" ;;
		"use #2") 
			echo "#2" ;;
		"use #3") 
			echo "#3" ;;
		"use #4") 
			echo "#4" ;;
		"exit")
			echo "exit.."
			break ;;
	esac
done
