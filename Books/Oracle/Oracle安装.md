# 预安装（自动配置）

- 如果只是简单的安装，则使用环境配置之后（自动配置或手动配置），即可准备进行安装。

```shell
# 自动配置 oracle-database-preinstall
dnf install oracle-database-preinstall-19c-1.0-1.el9.x86_64
```

1. 安装 oracle-database-preinstall的RPM包，准备需要的所有依赖。
2. 会自动创建标准（非角色分配）Oracle 安装所有者（只是Oracle dba等标准角色），并根据 Oracle 安装的需要进行分组和设置其他内核配置设置（不包括grid）

# 预安装（手动配置）

## 依赖准备

```shell
dnf install bc -y
dnf install binutils -y
dnf install compat-openssl11 -y
dnf install elfutils-libelf -y
dnf install fontconfig -y
dnf install glibc -y
dnf install glibc-devel -y
dnf install ksh -y
dnf install libaio -y
dnf install libasan -y
dnf install liblsan -y
dnf install libX11 -y
dnf install libXau -y
dnf install libXi -y
dnf install libXrender -y
dnf install libXtst -y
dnf install libxcrypt-compat -y
dnf install libgcc -y
dnf install libibverbs -y
dnf install libnsl -y
dnf install librdmacm -y
dnf install libstdc++ -y
dnf install libxcb -y
dnf install libvirt-libs -y
dnf install make -y
dnf install policycoreutils -y
dnf install policycoreutils-python-utils -y
dnf install smartmontools -y
dnf install sysstat -y
```

## 环境配置

### 所有者环境


```shell
groupadd -g 54321 oinstall
groupadd -g 54322 dba
groupadd -g 54323 oper
groupadd -g 54324 backupdba
groupadd -g 54325 dgdba
groupadd -g 54326 kmdba
groupadd -g 54327 asmdba
groupadd -g 54328 asmoper
groupadd -g 54330 racdba
useradd -u 54321 -g oinstall -G dba,asmdba,backupdba,dgdba,kmdba,racdba oracle
useradd -u 54331 -g oinstall -G dba,asmdba,backupdba,dgdba,kmdba,racdba grid

id oracle
#uid=54321(oracle) gid=54321(oinstall) #groups=54321(oinstall),54322(dba),54323(oper),54324(backupdba),54325(dgdba),54326(kmdba),54330(racdba)

id grid
#uid=54331(grid) gid=54321(oinstall) groups=54321(oinstall),54322(dba),
#54327(asmdba),54328(asmoper),54329(asmadmin),54330(racdba)
```

```shell
su - oracle
echo umask 022 >> .bash_profile
. ./.bash_profile

# 如果安装了现有的 Oracle 软件，并且使用同一用户来安装此安装
# 请取消设置 $ORACLE_HOME、$ORA_NLS10 和 $TNS_ADMIN 环境变量
```

### 内核参数

#### 内核参数最低要求参考

- 以下为最低的要求：

```shell
# /proc/sys/kernel/sem
# semmsl semmns semopm semmni
250     32000   100     128

# /proc/sys/kernel/shmall
# shmall 要求大于或等于 shmmax 的值（以页为单位）


# /proc/sys/kernel/shmmax
# shmmax 物理内存大小的一半（以字节为单位）


# /proc/sys/kernel/shmmni
# shmmni
4096

# /proc/sys/kernel/panic_on_oops
# panic_on_oops
1

# /proc/sys/fs/file-max
# file-max
6815744

# /proc/sys/fs/aio-max-nr
# aio-max-nr
1048576

# /proc/sys/net/ipv4/ip_local_port_range
# ip_local_port_range
# Minimum Maximum 
9000 65500

# /proc/sys/net/core/rmem_default
# rmem_default
262144

# /proc/sys/net/core/rmem_max
# rmem_max
4194304

# /proc/sys/net/core/wmem_default
# wmem_default
262144

# /proc/sys/net/core/wmem_max
# wmem_max
1048576
```

#### 配置Oracle需要的内核参数

```shell
# /etc/sysctl.d/97-oracle-database-sysctl.conf
fs.aio-max-nr = 1048576
fs.file-max = 6815744
kernel.shmall = 2097152
kernel.shmmax = 4294967295
kernel.shmmni = 4096
kernel.sem = 250 32000 100 128
net.ipv4.ip_local_port_range = 9000 65500
net.core.rmem_default = 262144
net.core.rmem_max = 4194304
net.core.wmem_default = 262144
net.core.wmem_max = 1048576
```

```shell
# 更改内核参数的当前值
/sbin/sysctl --system

# 查看设置的值
/sbin/sysctl -a
```

#### UDP和TCP内核参数

- 确保将UDP和TCP的端口下限范围设置为至少 9000 或更高，以避免使用已知端口，并避免 Oracle 和其他服务器端口常用的“已注册端口”范围内的端口。

```shell
vim /etc/sysctl.conf
# net.ipv4.ip_local_port_range = 9000 65500

network restart
```

### 资源限制

```shell
# 参考 https://docs.oracle.com/en/database/oracle/oracle-database/19/ladbi/checking-resource-limits-for-oracle-software-installation-users.html#GUID-293874BD-8069-470F-BEBF-A77C06618D5A
su - oracle

# 以下均为至少的

# 文件描述符
ulimit -Sn
1024
ulimit -Hn
65536
# 用户可用的进程数
ulimit -Su
2047
ulimit -Hu
16384
# 堆栈设置
ulimit -Ss
10240
ulimit -Hs
32768

# 注销这些用户并重新登录
```

## 目录与路径配置

- Oracle的目录配置一共有三种可选方式，环境变量、OFA、用户主目录。

1. 环境变量 <code>ORACLE_BASE</code>，Oracle的安装目录。

2. 最佳灵活体系结构 （[OFA](https://docs.oracle.com/en/database/oracle/oracle-database/19/ladbi/optimal-flexible-architecture-file-path-examples.html#GUID-BB3EE4F7-50F4-4A2D-8A0D-96B7CC44029B)） 路径 <code>u[01–99]/app/owner</code>，owner 是运行安装的用户帐户的名称，并且该用户帐户有权写入该路径。其中的owner通常是oracle或grid。

3.  用户主目录 

- 在安装Oracle之前，将通过以上任意一种方式设置的目录的权限设置给oracle <code>775</code>。之后，如果通过Oracle Universal Installer ，则将创建 Oracle Inventory 目录，例如<code>/u01/app/oraInventory</code>或<code>ORACLE_BASE</code>。

```shell
mkdir -p /u01/app/oraInventory
mkdir -p /u01/app/oracle/product/19.0.0/dbhome_1

chown -R oracle:oinstall /u01/app/oracle
chown -R oracle:oinstall /u01/app/oraInventory
chown oracle:oinstall /u01/app/oracle/product/19.0.0/dbhome_1
chmod -R 775 /u01/app

cd /u01/app/oracle/product/19.0.0/dbhome_1
unzip -q /root/backups/LINUX.X64_193000_db_home.zip
chown -R oracle:oinstall /u01/app/oracle/product/19.0.0/*

# su - oracle
cd /u01/app/oracle/product/19.0.0/dbhome_1
./runInstaller
```

# 预安装（可选项）

## 远程显示与X11转发（可选）

```shell
vncserver
export DISPLAY=local_host:0.0
# export DISPLAY=hostname:0
vi ~/.ssh/config
#Host * 
#    ForwardX11 no
```

```shell
# 修改每个 Oracle 安装所有者用户主目录中的隐藏文件，以禁止显示 STDOUT 或 STDERR 上的所有输出
if [ -t 0 ]; then
   stty intr ^C
fi
```

## 网络配置

# ASM

## ASMLIB

- Oracle ASMLIB 维护存储设备上永久存在的权限和磁盘标签，确保每次重新启动系统时都无需重新绑定与 Oracle Automatic Storage Management （Oracle ASM） 一起使用的块磁盘设备。

1. 如果使用 Oracle ASMLIB 配置磁盘，则必须将磁盘发现字符串更改为 `ORCL:*` 。如果磁盘发现字符串被设置为 `ORCL:*` ，或留空 （“”），则安装程序会发现这些磁盘。
2. Oracle ASMLIB已经包含在 Oracle Unbreakable Linux Network 的内核中。
3. 在安装ASMLIB之前，需要先完成预安装，提前准备好 oracle 或 grid 以及 dba 或 asmadmin。  

### 基于Oracle Linux安装

```shell
dnf install -y oracleasm
dnf install -y oracleasm-support
dnf install oracleasmlib

/usr/sbin/oracleasm configure -i
/usr/sbin/oracleasm init
systemctl enable oracleasm
systemctl start oracleasm
```

## 配置磁盘设备

-  Oracle Automatic Storage Management 磁盘组，
- 将磁盘标记为 Oracle Automatic Storage Management 磁盘

1. 指定的磁盘名称可以包含大写字母、数字和下划线字符。它们必须以大写字母开头。
2. 要在安装过程中使用 Oracle Automatic Storage Management library 驱动程序创建数据库，必须将磁盘发现字符串更改为 `ORCL:*` 。

```shell
# 将磁盘标记为 Oracle Automatic Storage Management 磁盘
/usr/sbin/oracleasm createdisk DISK1 /dev/sdb1
```

- root用户在各个节点上进行标识，以确保磁盘在集群中的其他节点上也可用

```shell
# 标识连接到节点的标记为 Oracle ASM 磁盘的共享磁盘
/usr/sbin/oracleasm scandisks
```

## 设置ASM

# OUI Oracle通用安装程序

- OUI（Oracle Universal Installer）会自动为Oracle的安装配置环境变量、创建相关目录。

1. 选择数据库的安装类：
   1. Desktop Class（桌面类）：最小的安装配置，入门级。
   2. Server Class（服务器类）：&#127775;。
2. 数据库版本：
   1. Enterprise Edition（企业版）：最齐全的功能&#127775;。
   2. Standard Edition 2（标准版 2）：中小型企业。
3. Oracle清单目录：`/oraInventory` （之前配置的oinstall）。如果之前未创建该目录，则在OUI中会自动创建在`/home/oracle/oraInventory`目录（也可以设置在`/oraInventory`，但需要有相关的权限来创建），也就是启动GUI程序的用户目录下。
4. Oracle安装目OUI安装选择合适的安装程序（Enter the full path of the file representing the products you want to install）： 解压后的Oracle安装文件中的runInstaller 。
5. 数据库文件位置（存储管理方式）：
   1. 文件系统：也就是使用当前系统的文件系统来管理。
   2. ASM（自动存储管理）：需要通过Oracle Grid框架先配置ASM磁盘组，在使用Oracle安装程序。
6. 数据库标识符：
   1. SID 是一个唯一标识符，用于将此实例与稍后可能创建并在系统上并发运行的其他 Oracle 数据库实例区分开来。
   2. 全局数据库名称是数据库的全名，它唯一地将其与任何其他数据库区分开来。全局数据库名称的格式为`database_name.database_domain`。
7. 安装选项：
   1. 典型安装：由Oracle自动完成后续的配置，不推荐。
   2. 高级安装：继续配置。

# Oracle DBCA 创建数据库

# 我的安装脚本

## 简单的单实例

### 预先准备

```shell
dnf install bc binutils compat-openssl11 elfutils-libelf fontconfig glibc glibc-devel ksh libaio libasan liblsan libX11 libXau libXi libXrender libXtst libxcrypt-compat libgcc libibverbs libnsl librdmacm libstdc++ libxcb libvirt-libs make policycoreutils policycoreutils-python-utils smartmontools sysstat -y
```

```shell
groupadd -g 54321 oinstall
groupadd -g 54322 dba
useradd -u 54321 -g oinstall -G dba oracle
echo umask 022 >> /home/oracle/.bash_profile

echo "fs.aio-max-nr = 1048576
fs.file-max = 6815744
kernel.shmall = 2097152
kernel.shmmax = 4294967295
kernel.shmmni = 4096
kernel.sem = 250 32000 100 128
net.ipv4.ip_local_port_range = 9000 65500
net.core.rmem_default = 262144
net.core.rmem_max = 4194304
net.core.wmem_default = 262144
net.core.wmem_max = 1048576" > /etc/sysctl.d/97-oracle-database-sysctl.conf

/sbin/sysctl --system
# /sbin/sysctl -a

mkdir -p /u01/app/oraInventory
mkdir -p /u01/app/oracle/product/19.0.0/dbhome_1

chown -R oracle:oinstall /u01/app/oracle
chown -R oracle:oinstall /u01/app/oraInventory
chown oracle:oinstall /u01/app/oracle/product/19.0.0/dbhome_1
chmod -R 775 /u01/app
```

- 此处是将<code>db_home</code>安装包解压到<code>dbhome_1</code>

```shell
cd /u01/app/oracle/product/19.0.0/dbhome_1
unzip -q /root/backups/LINUX.X64_193000_db_home.zip
chown -R oracle:oinstall /u01/app/oracle/product/19.0.0/*
```

### 图形化

```shell
passwd oracle
```

- 切换至oracle用户执行：

```shell
export CV_ASSUME_DISTID=OEL9.3
cd /u01/app/oracle/product/19.0.0/dbhome_1
./runInstaller
```

### 静默

# 安装常见错误

## INS-08101

```
[INS-08101] Unexpected error while executing the action at state: 'supportedOSCheck
Cause - No additional information available.  
Action - Contact Oracle Support Services or refer to the software manual.
```

```shell
export CV_ASSUME_DISTID=OEL9.3
# echo CV_ASSUME_DISTID=OEL9.3 >> /home/oracle/.bash_profile
```

## Error in invoking target ...

```
Error in invoking target 'libasmclntsh19.ohso libasmperl19.ohso client_sharedlib' of makefile '/u01/app/oracle/product/19.0.0/dbhome_1/rdbms/lib/ins_rdbms.mk'. See '/tmp/InstallActions2024-04-05_12-52-22PM/installActions2024-04-05_12-52-22PM.log' for details
```

- 下载补丁[35775632](https://updates.oracle.com/Orion/PatchDetails/process_form?aru=25398250&patch_password=&no_header=0)

