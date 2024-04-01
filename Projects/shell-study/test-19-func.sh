#!/bin/bash

function arrTest(){
	local newArr
	newArr=(`echo "$@"`)
	echo "New Arr：${newArr[*]}"
}

oldArr=(1 2 3)
echo ${oldArr[*]}
arrTest $oldArr
arrTest ${oldArr[*]}
