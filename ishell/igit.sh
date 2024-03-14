#!/bin/bash

key=$1
msg=$2
if [ -z "$msg" ]; then
	msg="none"
fi

stat="success"
back_point=`pwd`
BOOK_HOME=/home/zjk/note-book

cd $BOOK_HOME

git add --all
git commit -m "$msg" --all

if [ $(timeout -k 5s 30s git push gitee) ]; then
	stat="$stat---->gitee push failed"
fi

if [ $(timeout -k 5s 30s git push github) ]; then
	stat="$stat---->github push failed"
fi

# log
echo "'`whoami`', '`date`', '$msg' , '$stat'" >> $BOOK_HOME/ilog/igit_log.csv

# ok-0 lock-2 end-3
if [ 0 -eq $key ]; then
	for ((i=5;i>0;i--))
	do
		echo "$i秒之后退出"
		sleep 1s
	done
	exit
fi

if [ 1 -eq $key ]; then
	for ((i=5;i>0;i--))
	do
		echo "$i秒之后锁屏"
		sleep 1s
	done
	systemctl suspend
	exit
fi

if [ 2 -eq $key ]; then
	for ((i=5;i>0;i--))
	do
		echo "$i秒之后制定1分钟的关机计划"
		sleep 1s
	done
	shutdown
fi
