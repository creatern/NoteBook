#!/bin/bash

$CURR_USER=root
$CURR_USER_HOME=/root
# rsync
# 1. mkdir
cd $CURR_USER_HOME;
mkdir backups
chown -R $CURR_USER $CURR_USER_HOME/backups 
chmod 0700 $CURR_USER_HOME/backups
echo "export I_BACKUPS=$CURR_USER_HOME/backups" >> $CURR_USER_HOME/.bashrc
# 2. rsync
if rsync --version
then
	echo "rsync already installed"
else
	if dnf --version
	then
		dnf install rsync -y
	elif yum --version
	then
		yum install rsync -y
	elif apt --version
	then
		apt install rsync -y
	else
		echo "no suitable installer=====rsync install failed"
	fi
fi
echo "setup rsync configuration"
echo "port = 873
address = 0.0.0.0
pid file = /var/run/rsyncd.pid
log file = /var/log/rsyncd.log
motd file = /etc/rsyncd.motd

[mybackup_01]
	path = $CURR_USER_HOME/backups
	comment = 'server public archive'
	list = yes
	read only = no
	use chroot = no
	uid = 0
	gid = 0
	secrets file = /etc/rsyncd.secrets
	auth users = backupman" > /etc/rsyncd.conf
echo "backupman:tiger" > /etc/rsyncd.secrets
chmod 600 /etc/rsyncd.secrets
echo "welcome" > /etc/rsyncd-motd

# 3. rsyncd
systemctl start rsync
systemctl enable rsync
if firewall-cmd --version
then
	firewall-cmd --add-port=873/tcp --permanent
	firewall-cmd --reload
elif ufw --version
then
	ufw allow rsync
else
	echo "===========no firewalld can be used================"
	exit 127
fi

echo "=================rsync user================"
echo "backupman:tiger"
echo "==========================================="
