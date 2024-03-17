#!/bin/bash"

if git --version
then
	echo "git ok"
else
	if dnf --version
	then
        	dnf install git
	elif yum --version
	then
        	yum install git
	elif apt --version
	then
        	apt install git
	else
		echo "no sutible installer"
		exit 127
	fi
fi

echo "=====git ok"

if (cd ~/note-book)
then
	echo "=====git clone ok"
else
	cd ~
	git clone https://gitee.com/zjk1054860443/note-book.git
fi

cd ~/note-book/ishell
ISHELL_POSITION=$(pwd)
cd $ISHELL_POSITION;
cd ..;
BOOK_HOME=$(pwd)
cd $BOOK_HOME/Projects
I_PROJECTS=$(pwd)

# set env
echo 'export I_PROJECTS=$BOOK_HOME/Projects' >> ~/.bashrc
echo 'alias ok="$BOOK_HOME/ishell/igit.sh 0"' >> ~/.bashrc
echo 'alias lock="$BOOK_HOME/ishell/igit.sh 1"' >> ~/.bashrc
echo 'alias end="$BOOK_HOME/ishell/igit.sh 2"' >> ~/.bashrc

echo "=====env ok"

# set git
cd $BOOK_HOME

git remote add gitee git@gitee.com:zjk1054860443/note-book.git
git remote add github github git@github.com:creatern/NoteBook.git
git remote remove origin

echo "=====git remote ok"

# set ssh: rsa.pub
cd ~;
ssh-keygen -t rsa -b 4096 -N '' -f ~/.ssh/id_rsa
echo "=============== rsa.pub ==============="
cat ~/.ssh/.rsa.pub
echo "======================================="
echo "save this ssh-key to your gitee and your github"
