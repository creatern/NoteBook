#!/bin/bash

key=$1
msg=$2
if [ -z "$msg" ]; then
	msg="none"
fi

stat="success"
back_point=`pwd`

cd $BOOK_HOME

echo "'`whoami`','$(date "+%Y-%m-%d %H:%M:%S")','$msg'" >> $BOOK_HOME/ilog/igit_log.csv
git add --all
git commit -m "$msg" --all

if [ $(timeout -k 5s 30s git push gitee) ]; then
	stat="$stat---->gitee push failed"
fi

if [ $(timeout -k 5s 30s git push github) ]; then
	stat="$stat---->github push failed"
fi

# check the log file
while [ $(wc -l < $BOOK_HOME/ilog/igit_log.csv) -ge 100 ]
do
	sed -i '2d' /home/zjk/note-book/ilog/igit_log.csv
done

# backups
rsync -av $BOOK_HOME $I_BACKUPS
echo "================================================"
echo "successfully backup to $I_BACKUPS"
ls $I_BACKUPS
echo "================================================"

# log
echo "'`whoami`','$(date "+%Y-%m-%d %H:%M:%S")','$msg','$stat'" >> $BOOK_HOME/ilog/igit_log_local.csv
echo "================================================"
head -n 1 ~/note-book/ilog/igit_log_local.csv
tail -n 5 ~/note-book/ilog/igit_log_local.csv
echo "================================================"

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
