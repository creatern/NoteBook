# DNS相关命令

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
