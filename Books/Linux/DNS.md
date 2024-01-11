# bind9

- bind9（Berkeley Internet Name Domain，伯克利因特网域名服务器）

```shell
# 安装bind9服务
apt-get install bind9

# 定义特定于这个DNS服务器的区域（zone）配置信息
sudo vim /etc/bind/named.conf.local

# 重启bind9服务
systemctl restart bind9
```

```shell
# DNS工具
sudo apt install dnsutils
```

/etc/bind/named.conf.options：DNS 全局选项配置文件

/etc/bind/named.conf.local：自定义区域配置文件

/etc/bind/named.conf.default-zones：默认区域，例如localhost，其反向和根提示

https://www.jianshu.com/p/bf3f7011b9fe

# Avahi-daemon

- Avahi-daemon：开源的mDNS（多播DNS）和DNS-SD（DNS服务发现）实现，提供Zeroconf（即插即用网络）功能。启用Avahi-daemon后，设备会在本地网络中广播其主机名和IP地址信息，同一网络内的其他设备可以通过`hostname.local`域名来访问该设备。

```shell
# 安装avahi-daemon服务
sudo apt install avahi-daemon

# avahi-daemon服务的配置
sudo systemctl status avahi-daemon
sudo systemctl start avahi-daemon
sudo systemctl enable avahi-daemon

# 需要允许mDNS
sudo ufw allow mdns

# 测试
ping zjk-pi.local
```
