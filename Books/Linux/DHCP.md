# DHCP服务器

- DHCP服务器是为客户端机器分配IP地址的，所有分配的IP地址都保存在DHCP服务器的数据库中。为了在子网中实现DHCP分配IP地址，需要在目标主机上安装配置DHCP服务（`isc-dhcp-server`）

```shell
# 安装DHCP服务
apt install isc-dhcp-server

# 配置可用的网络接口，如 INTERFACESv4="wlp1s0"
sudo vim /etc/default/isc-dhcp-server

# DHCP服务配置文件 附件1
sudo vim /etc/dhcp/dhcpd.conf

# 重启DHCP服务
sudo systemctl isc-dhcp-server restart

# 检测DHCP服务是否正常运行 program name列中出现dhcpd服务
sudo netstat -uap
# udp        0      0 0.0.0.0:bootps          0.0.0.0:*                           12664/dhcpd

# 若未正常运行，则查看日志
cat /var/log/syslog | tail -20
```

> 附件1 [dhcpd.conf](./dhcpd_conf.md)

# DHCP客户端

- 配置DHCP客户端以广播其主机名给DHCP服务器，并且让DHCP服务器根据主机名分配IP地址（包括可能的DNS注册）：

1. 确保Linux DHCP客户端支持发送主机名`/etc/dhcp/dhclient.conf`中的`send host-name = gethostname();`告诉dhclient程序在DHCP请求中附带主机名
2. DHCP服务器还需要配置与DNS服务器交互，以便自动注册和更新DNS记录；在DHCP服务器上，需要配置它接收并处理来自客户端的主机名信息
