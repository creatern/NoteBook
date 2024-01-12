# 主机名与域名解析

## /etc/hostname

- `/etc/hostname`：本机主机名文件，通过该文件来设置主机名

```shell
# 查看当前主机名
hostname
```

## /etc/hosts

- `/etc/hosts`：主机名查询静态表，定义IP地址和主机名的映射关系。输入域名时，系统会先从hosts文件中寻找对应的IP地址，若没有找到，才将域名发送到DNS服务器进行IP地址解析。

## /etc/systemd/resolved.conf

# 检测DNS

## nslookup

## resolvectl

```shell
# 查看当前域名解析的信息
resolvectl status
```

```shell
# 修改DNS
sudo vim /etc/systemd/resolved.conf
sudo systemctl restart systemd-resolved.service
```

# mDNS

## Avahi-daemon

- Avahi-daemon：开源的mDNS（多播DNS）和DNS-SD（DNS服务发现）实现，提供Zeroconf（即插即用网络）功能。启用Avahi-daemon后，设备会在本地网络中广播其主机名和IP地址信息，同一网络内的其他设备可以通过`.local`域名来访问该设备。

```shell
# 安装avahi-daemon服务
sudo apt install avahi-daemon

# avahi-daemon服务的配置
sudo systemctl status avahi-daemon
sudo systemctl start avahi-daemon
sudo systemctl enable avahi-daemon

# 需要允许mDNS端口 5353/udp
sudo ufw allow mdns

# 测试
ping zjk-pi.local
```

- `/etc/avahi/avahi-daemon.conf`：Avahi的配置文件

### Avahi错误处理

- 若主机A不可以ping B.local，而主机B可以ping A.local，此时应该去查看主机A的配置是否有误
- 通常发生错误的位置，通过查看日志来排查：

1. 网络接口配置与网卡、网卡是否支持mDNS等
2. `.local`域名服务冲突，avahi很可能会与其他的mDNS服务（bind、Bjourine等）发生冲突

```shell
# 日志查看
journalctl -u avahi-daemon

# avahi通常会有可能与其他的mDNS服务发送冲突：`WARNING: Detected another IPv4 mDNS stack running on this host. This makes mDNS unreliabl`
sudo systemctl list-unit-files | grep mdns

# 查看网卡配置文件的错误日志
more /var/log/syslog
```



