#!/bin/bash

# init script
read -p "please input which user you want to setup: (default zjk)" CURR_USER

if [ -z $CURR_USER ]
then
	CURR_USER=zjk
fi

CURR_USER_HOME=/home/$CURR_USER
BOOK_HOME=$CURR_USER_HOME/note-book
ISHELL_POSITION=$BOOK_HOME/ishell
I_PROJECTS=$BOOK_HOME/Projects

if git --version
then
	echo "git ok"
else
	if dnf --version
	then
        	dnf install git -y
	elif yum --version
	then
        	yum install git -y
	elif apt --version
	then
        	apt install git -y
	else
		echo "no suitable installer=====git install failed"
		exit 127
	fi
fi

# git init
read -p "Do you need init git ? (n/default y)" GIT_INIT_KEY

if [ "n" -eq $GIT_INIT_KEY ]
then
	echo "=====cancel mask git init"
else
	git config --global user.name $CURR_USER
	git config --global user.email $CURR_USER@null.com
fi

echo "=====git ok"

if [ -e $CURR_USER_HOME/note-book ]
then
	echo "=====git clone ok"
else
	cd $CURR_USER_HOME
	git clone https://gitee.com/zjk1054860443/note-book.git
fi

# prepare for I_MOUNTPOINT
mkdir $CURR_USER_HOME/MyDisk
chown -R $CURR_USER $CURR_USER_HOME/MyDisk

# set env
echo "export BOOK_HOME=$BOOK_HOME" >> $CURR_USER_HOME/.bashrc
echo 'export I_PROJECTS=$BOOK_HOME/Projects' >> $CURR_USER_HOME/.bashrc
echo "export I_MOUNTPOINT=$CURR_USER_HOME/MyDisk" >> $CURR_USER_HOME/.bashrc
echo 'alias ok="source $BOOK_HOME/ishell/igit.sh 0"' >> $CURR_USER_HOME/.bashrc
echo 'alias lock="source $BOOK_HOME/ishell/igit.sh 1"' >> $CURR_USER_HOME/.bashrc
echo 'alias end="$BOOK_HOME/ishell/igit.sh 2"' >> $CURR_USER_HOME/.bashrc
echo 'alias issh="$BOOK_HOME/ishell/issh.sh"' >> $CURR_USER_HOME/.bashrc

echo "=====env ok"

# set git
cd $BOOK_HOME
git remote add gitee git@gitee.com:zjk1054860443/note-book.git
git remote add github git@github.com:creatern/NoteBook.git
git remote remove origin
chown -R $CURR_USER $BOOK_HOME

echo "=====git remote ok"

# set backups
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

# 4. set Avahi
if avahi-daemon --version
then
	echo "avahi-daemon already setup"
else
	if apt --version
	then 
		apt install avahi-daemon -y
		systemctl start avahi-daemon
		systemctl enable avahi-daemon
	elif dnf --version
	then
		dnf install avahi-daemon -y
		systemctl start avahi-daemon
		systemctl enable avahi-daemon
	else
		echo "no suitable installer======avahi-daemon install failed"
		exit 127
	fi
fi


# set Samba
if dnf --version
then
	dnf install samba samba-common smbclient -y
elif apt --version
then
	apt install samba samba-common smbclient -y
else
	echo "no suitable installer=====Samba install faild"
	exit 127
fi

mkdir $CURR_USER_HOME/samba
chmod 777 $CURR_USER_HOME/samba
echo "======you need to input your password for samba user ($CURR_USER)========"
sleep 3s
smbpasswd -a $CURR_USER
echo "[share]" >> /etc/samba/smb.conf
echo "comment = share folder " >> /etc/samba/smb.conf
echo "browseable = yes" >> /etc/samba/smb.conf
echo "path = $CURR_USER_HOME/samba" >> /etc/samba/smb.conf
echo "create mask = 0700" >> /etc/samba/smb.conf
echo "directory mask = 0700" >> /etc/samba/smb.conf
echo "valid users = $CURR_USER" >> /etc/samba/smb.conf
echo "force users = $CURR_USER" >> /etc/samba/smb.conf
echo "force group = $CURR_USER" >> /etc/samba/smb.conf
echo "public = yes" >> /etc/samba/smb.conf
echo "avaliable = yes" >> /etc/samba/smb.conf
echo "writable = yes" >> /etc/samba/smb.conf

if ufw --version
then
	ufw allow smaba
	systemctl enable smbd
	systemctl start smbd
else
	firewall-cmd --permanent --add-service=samba
	firewall-cmd --reload
	systemctl enable smb
	systemctl start smb
fi


# set ssh: rsa.pub
cd $CURR_USER_HOME;

if [ -e $CURR_USER_HOME/.ssh/id_rsa.pub ]
then
	echo "=====id.rsa.pub already created"
else
	ssh-keygen -t rsa -b 4096 -N '' -f $CURR_USER_HOME/.ssh/id_rsa
fi

echo "=============== rsa.pub ==============="
cat $CURR_USER_HOME/.ssh/id_rsa.pub
echo "======================================="
echo "save this ssh-key to your gitee and your github"
echo "then you should try to use 'git push' by yourself at the first time"
