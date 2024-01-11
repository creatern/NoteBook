# Samba概述

- Samba：基于Server Message Block协议（SMB信息服务块），SMB是客户机/服务器型协议，是在局域网上共享文件和打印的通信协议，客户通过SMB可访问服务器上的共享文件系统和打印机等资源。

| 服务单元  | 说明                                                         |
| :-------- | :----------------------------------------------------------- |
| smbd      | SMB服务器，为客户机提供文件和打印服务                        |
| nmbd      | NetBIOS名字服务器，可以提供浏览器支持                        |
| smbclient | SMB客户程序，用于从Linux或其他操作系统上访问SMB服务器上的资源 |
| smbmount  | 挂载SMB文件系统的工具，对应的卸载工具：smbunmount            |
| smbpasswd | 用户增删服务器的用户和密码                                   |

# samba 服务器

```shell
# 安装samba服务器
sudo apt-get install samba samba-common

# 配置共享目录
mkdir /home/zjk/samba
sudo chmod 777 /home/zjk/samba

# 添加Samba用户（需要是Linux中已经存在的用户）
sudo smbpasswd -a zjk

# 修改配置文件
sudo vim /etc/samba/smb.conf

# 重启samba服务以应用修改后的配置文件
sudo service smbd restart

# 需要打开部分的防火墙来允许访问samba
sudo ufw allow samba

# 1. 在文件管理器、浏览器等地方输入该主机的地址即可
\\127.0.0.1
# 2. 通过smbclient访问
```

- smb.conf：在该文件末尾追加如下：

```shell
[share]
# 共享资源配置
comment = share folder 
# 注释信息
browseable = yes
# yes表示允许其他用户在网络邻居中看到并访问这个共享文件夹
path = /home/zjk/MyDisk/samba
# 指定共享的实际路径
create mask = 0700
# 新建文件的默认权限掩码
directory mask = 0700
# 新建目录的默认权限掩码
valid users = zjk
# 指定合法用户列表，只有这些用户可以访问此共享
force user = zjk 
force group = zjk
# 强制以用户zjk及其所属组的身份来访问此共享。无论哪个用户连接到此共享，对文件的操作都会映射到用户zjk和zjk组
public = yes 
# yes表示任何（valid users范围内的）用户都可以尝试访问此共享，但受到valid users的限制
available = yes
# yes表示表明共享始终可用
writable = yes
# yes表示所有被允许访问的用户都具有对此共享目录的写入权限
```

# smbclient 客户端

```shell
# 安装smbclient客户端
sudo apt install smbclient

# 连接到samba服务器：IP地址后接共享目录；必须是先到share（顶级共享）再自行选择实际的共享目录
smbclient //10.22.210.51/share

# 查看可用的共享目录
smbclient -L 192.168.90.71
```

```shell
# 错误1：用户名或密码错误
session setup failed: NT_STATUS_LOGON_FAILURE
# 错误2：共享目录不存在或用户对该目录无权限
tree connect failed: NT_STATUS_BAD_NETWORK_NAME
```

### 
