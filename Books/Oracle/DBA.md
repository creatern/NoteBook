# Oracle安装

- 以Oracle Linux 9为例

## 预安装

### 自动配置 oracle-database-preinstall

```shell
# 安装 oracle-database-preinstall的RPM包
# 会自动创建标准（非角色分配）Oracle 安装所有者，并根据 Oracle 安装的需要进行分组和设置其他内核配置设置（不包括grid）
# 具体参考 https://docs.oracle.com/en/database/oracle/oracle-database/19/ladbi/Chunk151261164.html#GUID-C15A642B-534D-4E4A-BDE8-6DC7772AA9C8
dnf install oracle-database-preinstall-19c-1.0-1.el9.x86_64
```

### 手动配置

#### 环境配置

##### 所有者环境


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
usermod -g oinstall -G dba,asmdba,backupdba,dgdba,kmdba,racdba,oper oracle
```

```shell
su - grid
echo umask 022 >> .bash_profile
. ./.bash_profile

# 如果安装了现有的 Oracle 软件，并且使用同一用户来安装此安装
# 请取消设置 $ORACLE_HOME、$ORA_NLS10 和 $TNS_ADMIN 环境变量
```

```shell
id oracle
#uid=54321(oracle) gid=54321(oinstall) #groups=54321(oinstall),54322(dba),54323(oper),54324(backupdba),54325(dgdba),54326(kmdba),54330(racdba)

id grid
#uid=54331(grid) gid=54321(oinstall) groups=54321(oinstall),54322(dba),
#54327(asmdba),54328(asmoper),54329(asmadmin),54330(racdba)
```

##### 资源限制

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

##### 远程显示与X11转发

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

#### 网络配置

