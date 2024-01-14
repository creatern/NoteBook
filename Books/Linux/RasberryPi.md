# Rasberry Pi 基础

## Rasberry Pi 版本

<table>
    <tr>
        <td>Rasberry Pi Zero</td>
        <td>最小的树莓派设备</td>
        <td></td>
    </tr>
    <tr>
        <td>Rasberry Pi Zero W</td>
        <td>包含WiFi的Zero版本</td>
        <td></td>
    </tr>
</table>

### Rasberry Pi 4B

Rasberry Pi 4B的配置：

- 2个USB2端口、2个USB3端口
- 2个微型HDMI端口，支持2个4K显示器
- 1个专用千兆以太网端口
- 支持2~8GB的内存（树莓派的内存是难以更替的，在购买时需要谨慎选择）
- 博通BCM2711，1.5GHz四核Cortex-A72（ARM v8）64位片上系统
- 2.4GHz与5.0GHz IEEE802.11ac WiFi
- 蓝牙
- 40针GPIO接口（`pinout`命令可查看）

<img src="../../pictures/ab0fc189513bd2cf689725f8360d960c.png" width="400"/> 

#### 基本安装

- 将需要的系统通过镜像工具刷入SD卡即可

##### 散热

- Rasberry Pi运行期间的温度不应超过70&#8451;，保持在40&#8451;\~60&#8451;

<img src="../../pictures/20240101182816.png" width="400"/> 

- 风扇的红黑线接反，则风扇不会转；分别插入到GPIO接口的2、4号引脚（5V PWR）
- 风扇的吹风方向：封闭式则抽风（向外）；开放式则吹风（向内）

# 软路由