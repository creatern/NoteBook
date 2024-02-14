```shell
# option definitions common to all supported networks...
# 客户端获取的域名选项：当DHCP客户端从服务器获取IP地址和其他网络配置参数时，也会接收到这个域名信息；即所有通过该DHCP服务器获得IP地址的设备将自动配置其DNS后缀为"example.org"，也就是"hostname.example.org"
option domain-name "example.org";
#option domain-name-servers ns1.example.org, ns2.example.org;

# DHCP客户端默认的IP地址租约时间，单位是秒
default-lease-time 600;
# DHCP客户端可以获取的最大IP地址租约时间，单位是秒
max-lease-time 7200;

# The ddns-updates-style parameter controls whether or not the server will
# attempt to do a DNS update when a lease is confirmed. We default to the
# behavior of the version 2 packages ('none', since DHCP v2 didn't
# have support for DDNS.)
# 定义动态DNS更新的风格或策略
ddns-update-style none;
# none：DHCP服务器不会执行任何与DNS（域名系统）相关的更新操作，即关闭动态DNS更新，当DHCP客户端获取或释放IP地址时，DNS服务器上的相关主机记录将不会被自动创建、修改或删除。
```

```shell
# 定义了一个子网段 网络地址是192.168.90.0，并且使用的子网掩码是255.255.255.0
subnet 192.168.90.0 netmask 255.255.255.0 {
    # 指定了DHCP服务可以分配给客户端的有效IP地址范围
    range 192.168.90.70 192.168.90.253; 
    # 默认网关选项，当DHCP客户端获取IP地址时，也会同时得到默认路由器的地址为192.168.90.1，所有发送至该子网之外的数据包都将通过此路由器转发。
    option routers 192.168.90.1;
    # 子网掩码
    option subnet-mask 255.255.255.0;
    # 广播地址
    option broadcast-address 192.168.90.255;
    # 域名服务器(DNS)
    option domain-name-servers 192.168.90.1;
    # 网络时间协议(NTP)服务器
    option ntp-servers 192.168.90.1;
    # NetBIOS名称服务器
    option netbios-name-servers 192.168.90.1;
    # 设置了NetBIOS节点类型为8，这代表混合模式节点（H-node）
    option netbios-node-type 8;
}

```