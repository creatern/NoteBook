# SSH

- SSH：使用22端口

```shell
# 安装ssh客户端和服务端
sudo apt-get install openssh-client
sudo apt-get install openssh-server

# 启动ssh服务 stop则停止
sudo /etc/init.d/ssh start

# 查看ssh服务状态
systemctl status ssh

# 连接方式 主机名或者ip地址
ssh usr@hostname
ssh usr@ip
```

# OpenSSH

- OpenSSH是保证远程访问安全的一套首要工具，不仅可以加密身份认证和会话期间的所有流量，而且还可以确保数据传输的完整性。

<table>
    <tr>
        <td width="20%">sshd</td>
        <td width="80%">OpenSSH服务器的守护进程</td>
    </tr>
    <tr>
        <td>ssh</td>
        <td>secure shell（安全shell），不包含shell，但为远程系统上的命令shell提供了一个安全通道</td>
    </tr>
    <tr>
        <td>scp</td>
        <td>secure copy（安全复制），用于加密文件传输</td>
    </tr>
    <tr>
        <td>sftp</td>
        <td>Secure File Transfer Protocol（安全文件传输协议），提供文件访问</td>
    </tr>
    <tr>
        <td>ssh-copy-id</td>
        <td>小程序，用于将公钥放入远程SSH服务器的authorized_keys文件中</td>
    </tr>
    <tr>
        <td>ssh-keyscan</td>
        <td>可用于查找和收集网络上的公钥</td>
    </tr>
    <tr>
        <td>ssh-keygen</td>
        <td>生成和管理身份认证密钥</td>
    </tr>
    <tr>
        <td>ssh-add</td>
        <td>将用户身份添加到身份认证代理ssh-agent</td>
    </tr>
    <tr>
        <td>sshfs</td>
        <td>可以将远程文件系统挂载到本地PC上</td>
    </tr>
    <tr>
        <td rowspan="3">ssh-agent</td>
        <td>可以保存SSH私钥的密码（passphrase），从而实现SSH登录时的自动身份认证</td>
    </tr>
    <tr>
        <td>ssh-agent只能绑定到一个登录会话，因此注销或打开另一个终端就要重新输入密码</td>
    </tr>
    <tr>
        <td>Keychain：作为ssh-agent的前端，可以重复使用ssh-agent</td>
    </tr>
</table>

- OpenSSH支持不同类型的身份认证：

<table>
    <tr>
        <td>密码认证</td>
        <td>使用Linux的登录账号和密码进行身份认证，最简单的方式，用户可以从任何机器登录</td>
    </tr>
    <tr>
        <td>公钥认证</td>
        <td>使用个人的SSH公钥进行身份认证，需要创建和分发公钥，且只能从持有私钥的计算机登录</td>
    </tr>
    <tr>
        <td>无密码认证</td>
        <td>不使用私钥密码的公钥认证，需要格外注意保护无密码保护的私钥</td>
    </tr>
    <tr>
        <td>Keychain</td>
        <td>另一种无密码认证，会记住私钥</td>
    </tr>
</table>

- 服务器和客户端是由事务的方向决定的，服务器负责运行SSH守护程序并监听连接请求，而客户端是通过SSH登录到这台机器的任何人。

```shell
# 查看sshd运行情况
sudo systemctl status sshd 
```

##  生成主机密钥

```shell
# 删除旧的密钥
sudo rm /etc/ssh/ssh_host*

# 生成所有密钥，包括RSA DSA ECDSA ED25519
sudo ssh-keygen -A

# 生成无密码的rsa密钥，且位于~/.ssh/id_rsa
ssh-keygen -t rsa -b 4096 -N '' -f ~/.ssh/id_rsa
```

<table>
    <tr>
        <td>RAS</td>
        <td>最古老的密钥，提供了最大的兼容性</td>
    </tr>
    <tr>
        <td>DSA</td>
        <td>尽量不用</td>
    </tr>
    <tr>
        <td>ECDSA<br/>ED25519</td>
        <td>新出现的密钥，计算成本更低；但一些旧的SSH客户端不支持，是OpenSSH6.5一起分布的</td>
    </tr>
</table>
## OpenSSH服务器

### sshd

```shell
# 在sshd运行时执行，可预先检测ssh配置语法是否正确，以便确认是否载入配置
sudo sshd -t

# 重新加载sshd
sudo systemctl reload sshd.server

# 重启sshd
sudo systemctl restart sshd
```

#### /etc/ssh/sshd_config

- 在Linux系统中，出于安全考虑，通常会禁用直接通过SSH以root用户身份登录，通过编辑`/etc/ssh/sshd_config`文件来实现。

```shell
# 不允许root用户通过输入密码的方式进行登录
PermitRootLogin prohibit-password

# 完全禁止root
PermitRootLogin no

# 允许root用户通过输入密码的方式进行登录
PermitRootLogin y
```

- /etc/ssh/sshd_config的其他配置

```shell
# 严格检查模式 确保用户接受登录之前，首先检查用户文件和主目录的文件模式与所有权是否正确
# 若文件权限不正确，则不允许登录
StrictModes yes

# 若计算机有多个IP地址，则需要指定监听的地址
ListenAddress 192.168.10.15
ListenAddress 192.168.10.16

# SSH的默认端口为22，可以自行配置使用其他端口
# 先在/etc/services配置监听端口，再将对应的端口信息添加到/etc/ssh/sshd_config文件
Port 2022
Port 2023

# 将访问设置为仅限指定的组（/etc/group）
AllowGroups mybackupman mywebman
# 或者设置 DenyGroups 设置禁止访问的组

# 禁止所有用户通过密码登录，只允许公钥认证
PasswordAuthentication no

# 拒绝特定的用户，需要指定用户名或主机名或IP地址
DenyUsers myuserman myhost tash@example.com cagney@182.168.10.25

# 限制服务器等待用户登录并完成连接的时长，默认120秒，单位：秒
LoginGraceTime 90

# 限制尝试连接的最大失败次数，默认6次
MaxAuthTries 4
```

#### /etc/services 监听端口

```shell
# 为sshd分配非标准的监听端口，但只能使用1024以上的端口
# 首先检查/etc/services 找到未使用的端口，再将新端口添加到/etc/services中
sshd 2022
sshd 2023
```

### 设置密码验证

- 密码认证是最简单的设置远程SSH访问的方法，需要完成下列任务以设置：

1. 安装并正确配置OpenSSH服务器。
2. 在远程机器上运行SSH守护进程（sshd），并确保任何sshd使用的端口（默认22端口）没有被防火墙拦截。
3. 在客户端机器上安装SSH客户端。
4. 登录远程计算机的用户账户。
5. 服务器上的主机密钥，主机密钥中的公钥必须分发给客户端。最简单的方式是从客户端登录，然后由OpenSSH传输密钥。主机密钥交换只发生一次（第一次登录时），之后除非更换密钥或删除`~/.ssh/kown_hosts`中的密钥，否则ssh不会再询问密钥。

```shell
# 通过`用户名@主机名或IP地址`的方式来指定登录到SSH服务器的用户
ssh username@hostname|ipaddress

# 如果SSH客户端的用户名和SSH服务器的用户名相同，则只需要输入主机名或IP地址即可
ssh hostname|ipaddress
```

### 获取密钥指纹

```shell
# 服务器运行，指定主机密钥
ssh-keygen -lf /etc/ssh/ssh_host_rsa_key
```

### 使用公钥认证

- Linux用户可以创建任意数量的SSH密钥。可以使用一个密钥来访问多个远程主机，或者为每个远程主机创建一个唯一的密钥。

```shell
# 创建一对RSA密钥
# ssh-keygen -C "注释信息" -f 密钥名称 -t rsa -b 4096
ssh-keygen -C "zjk-pi's rsa key" -f ~/.ssh/pi-key -t rsa -b 4096

# 使用ssh-copy-id 将新密钥复制到远程计算机
# ssh-copy-id -i 密钥名称 用户名@远程主机
ssh-copy-id -i ~/.ssh/pi-key zjk@zjk-pi.local

# 使用RAS密钥登录
# ssh -i 密钥名称 用户名@远程主机
ssh -i pi-key zjk@zjk-pi.local
```

#### 查看私钥与公钥

```shell
# 确保私钥归root用户所有，权限为只读；而公钥归root用户所有，权限为读写，其余用户可读。
ls -l /etc/ssh
```

#### ssh-copy-id

- `ssh-copy-id`工具可确保公钥以正确的格式、正确的权限、复制到正确的位置（`~/.ssh/authorized_keys`）。

<table>
    <tr>
        <td width="5%">-C</td>
        <td width="42.5%">向密钥添加注释</td>
        <td width="5%" rowspan="3"></td>
        <td width="5%">-f</td>
        <td width="42.5%">密钥的名称</td>
    </tr>
    <tr>
        <td>-t</td>
        <td>密钥类型，ras、ecdsa、ed25519</td>
        <td>-b</td>
        <td>位强度，只有rsa类型接受该选项，默认为2048，最大为4096。位越多意味着处理所耗费的资源越多</td>
    </tr>
    <tr>
        <td>-i</td>
        <td>告诉SSH客户端应该使用哪个密钥；当客户端有多个密钥时必须指定该选项</td>
        <td></td>
        <td></td>
    </tr>
</table>

#### 管理多个公钥

- `~/.ssh/config`文件可以配置各种远程主机的登录账户，以便管理公钥。按如下格式可配置多个公钥的登录信息：

```
Host 登录时使用的标签
  HostName SSH服务器的主机名或IP地址
  User SSH服务器上的用户名
    IdentityFile 使用的公钥（~/.ssh/密钥名称）
    # 仅使用~/.ssh/config中的设置
    IdentitiesOnly yes
    Port 服务器SSH使用的端口号
```

```shell
Host zjk-pi
        HostName zjk-pi.local
        User zjk
                IdentityFile ~/.ssh/pi-key
                IdentitiesOnly no
```

#### 修改私钥密码

- 私钥的密码是不可恢复的，如果密码丢失，只能重新创建一个私钥并设置密码。

```shell
# 修改私钥密码
ssh-keygen -p -f ~/.ssh/私钥名
```

#### 删除密钥

```shell
# 删除对应主机（zjk-pi.local）的密钥
ssh-keygen -R zjk-pi.local
```

1. 删除本地密钥对

```shell
# 删除私钥
rm ~/.ssh/pi-key

# 删除私钥对应的公钥（默认情况下，公钥名为私钥名加上.pub后缀）
rm ~/.ssh/pi-key.pub
```

2. 如果已将公钥添加到SSH代理或配置文件中，也需移除：

```shell
# 如果使用了ssh-agent，可以先清理掉（假设你已经添加到了ssh-agent中）
ssh-add -d ~/.ssh/pi-key

# 检查并编辑 SSH 配置文件（如需要）
vi ~/.ssh/config
# 在配置文件中查找并删除与 zjk_pi_ssh_rsa_key 相关的行
```

3. 从远程主机 `zjk-pi.local` 上移除公钥： 登录到远程主机，并在远程主机上操作，找到存放`authorized_keys`文件的位置（通常是`~/.ssh/authorized_keys`），然后从该文件中删除对应的公钥。

```shell
# 登录到远程主机
ssh zjk@zjk-pi.local

# 切换到.zshrc或.bashrc等合适的shell配置文件，检查是否有关于此密钥的别名或其他引用，如果有则删除
vi ~/.bashrc # 或者是 ~/.zshrc 等

# 找到 authorized_keys 文件
cd ~/.ssh
vi authorized_keys

# 在 authorized_keys 文件中找到并删除对应公钥行
# 通常这可以通过复制粘贴或手动比对公钥内容来完成

# 保存并退出编辑器
:wq

# 可选地，重新加载 shell 配置以确保更改生效
source ~/.bashrc
```

## Keychain 自动管理密码

- Keychain是ssh-agent和gpg-agent的管理器，只要计算机处于开机状态，就可以缓存SSH和GPG的密码，只有重启后才需要重新输入密码。

- Keychain可以一直保持私钥有效直到计算机关闭，因此每次启动计算机都需要输入私钥密码。对于图形化界面，可能需要打开终端再输入私钥密码。

```shell
# 添加配置到.bashrc文件，如下配置：
keychain ~/.ssh/密钥1名称 ~/.ssh/密钥2名称 . ~/.keychain/$HOSTNAME-sh
```

### 为Cron提供私钥密码

# X会话隧道

# sshfs

- sshfs ：挂载整个远程文件系统，以像本地文件系统一样使用。

```shell
# 安装sshfs的同时会安装 FUSE（Filesystem in Userspace，用户空间中的文件系统）
sudo apt install sshfs

# 创建本地挂载点
mkdir ~/MyDisk/mysshfs

# 将远程目录挂载到本地sshfs目录中，之后可像本地文件系统一样使用
# sshfs 用户名@主机:/目录 本地挂载点/
sshfs zjk@zjk-pi:/home/zjk/Docs ~/MyDisk/mysshfs

# 卸载远程文件系统
fusermount -u ~/MyDisk/mysshfs/

# 若网络连接不稳定，可以告诉sshfs在中断后自动重新连接
# sshfs 用户名@主机:/目录 本地挂载点/ -o reconnect
sshfs zjk@zjk-pi:/home/zjk/Docs ~/MyDisk/mysshfs/ -o reconnect
```

# /etc/ssh/sshd_config

```shell

```

