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

### 查看私钥与公钥

```shell
# 确保私钥归root用户所有，权限为只读；而公钥归root用户所有，权限为读写，其余用户可读。
ls -l /etc/ssh
```

### sshd

```shell
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



