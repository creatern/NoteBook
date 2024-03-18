# KVM概述

- KVM（Kernel-based Virtual Machine，基于内核的虚拟机）是一种用于虚拟化的开源硬件虚拟化技术，使用Linux内核的虚拟化模块，将物理服务器划分为多个虚拟机。
- KVM在Linux内核之上构建一个虚拟化层，该虚拟化层允许多个虚拟机共享物理服务器上的计算资源（如 CPU、内存和I/O设备）。在此虚拟化层中，每个虚拟机都被视为单独的计算机，每个虚拟机可以运行不同的操作系统和应用程序。

```shell
# 测试是否支持虚拟化技术（结果大于0即可）
egrep -c '(vmx|svm)' /proc/cpuinfo

# 使用 lsmod 命令检查 KVM 内核模块是否已加载
lsmod | grep -i kvm
```

## KVM&Fedora39

### 软件包安装

```shell
# 安装虚拟化主包
sudo dnf install -y qemu-kvm libvirt virt-install bridge-utils

# 基于 qt 的图形界面，用于通过 libvirt 守护进程管理虚拟机
sudo dnf install -y virt-manager

# 安装额外的虚拟化模块
sudo dnf install -y libvirt-devel virt-top libguestfs-tools guestfs-tools
```

<table>
  <tr>
    <th width="15%">软件包</th>
    <th width="85%">描述</th>
  </tr>
  <tr>
    <td>qemu-kvm</td>
    <td>开源的仿真器和虚拟化软件包，提供硬件级别的模拟，支持KVM（Kernel-based Virtual Machine）技术，允许在Linux内核中直接运行虚拟机。</td>
  </tr>
  <tr>
    <td>libvirt</td>
    <td>一个软件包，它包含了管理和控制虚拟化环境所需的库以及libvirtd守护进程所需的配置文件。Libvirt为多种虚拟化技术（包括但不限于KVM、QEMU等）提供了统一的管理接口。</td>
  </tr>
  <tr>
    <td>virtinst</td>
    <td>一组命令行工具集合，用于创建、修改及配置虚拟机实例，是libvirt虚拟化架构下进行虚拟机部署和管理的重要组件之一。</td>
  </tr>
  <tr>
    <td>Virt-install</td>
    <td>基于命令行的工具，专为libvirt环境设计，用于从命令行界面便捷地安装和配置新的虚拟机，支持多种参数选项以定义虚拟机的各种资源和属性。</td>
  </tr>
  <tr>
    <td>bridge-utils</td>
    <td>包含了一系列用于创建、配置和管理网络桥接设备的实用程序，在虚拟化环境中，常用于建立虚拟机与宿主机或外部网络之间的网络连接，实现网络桥接模式下的通信。</td>
  </tr>
</table>
### libvirtd 虚拟化守护进程


- 启动并启用虚拟化守护进程：

```shell
sudo systemctl start libvirtd
sudo systemctl enable libvirtd
sudo systemctl status libvirtd
```

### virbr0 网桥 failed

- virbr0（虚拟网桥）：安装KVM时，会自动创建一个名为<b>virbr0</b>的虚拟网桥，用于提供 NAT（网络地址转换）服务。因此，如果虚拟机的网卡被绑定到虚拟网桥virbr0上，虚拟机就可以通过DHCP获取IP，从而可以连接到外部（Internet）。但默认情况下，该虚拟机不能从外部访问。
- 可以通过<code>nmcli</code>命令创建网桥，使得虚拟机能够被外部访问：
- 如果过程中：`cant add wlan0 to bridge br0: Operation not supported`，别挣扎了，这是协议中硬性规定不允许的。

```shell
nmcli connection show
# 配置与创建网桥br0
sudo nmcli connection add type bridge autoconnect yes con-name br0 ifname br0
sudo nmcli connection modify br0 ipv4.addresses 192.168.1.189/24 gw4 192.168.1.1 ipv4.method manual
sudo nmcli connection modify br0 ipv4.dns 192.168.1.1
# 删除一些其余的网络连接配置
sudo nmcli connection del wlp1s0
# 将wlp1s0加入到br0网桥
sudo nmcli connection add type bridge-slave autoconnect yes con-name wlp1s0 ifname wlp1s0 master br0
sudo nmcli connection up br0
# sudo ifconfig wlp1s0 up
# sudo brctl addif br0 wlp1s0
# sudo ifconfig br0 up

# 验证连接和桥接 如果成功wlp1s0应该作为br0的接口（Interface）
nmcli connection show br0
ip add show br0
brctl show
```

### 创建虚拟机

- 在KVM上创建虚拟机有两种方法，可以在命令行上使用<code>virt-install</code>命令行工具或使用virt-manager GUI 实用程序完成此操作。
- 选择Brige方式，以及之前创建的br0（Driver name）。

