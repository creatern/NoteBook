#!/bin/bash

if mountpoint $I_MOUNTPOINT
then
	echo 1
else
	echo 2
fi
