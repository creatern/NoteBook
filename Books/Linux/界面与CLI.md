# 系统界面

```shell
# 设置启动时进入的界面模式
sudo systemctl set-default multi-user.target
sudo systemctl set-default graphical.target

# 临时进入的界面模式
sudo systemctl start graphical.target

# 查看设置的结果
systemctl get-default

# 更改启动时进入界面的方式
sudo vim /etc/default/grub
# GRUB_CMDLINE_LINUX_DEFAULT="quiet splash text"
```

# tty

- tty1-7（teletypewriter，电传打字机）：`ctrl + alt + F2~F7`对应不同的虚拟控制台
- 登入虚拟控制台进入Linux CLI（command line interface，命令行界面）后，可以在不中断当前活动会话的情况下切换到另一个虚拟控制台，在所有的虚拟控制台之间任意切换，同时拥有多个活动会话。

```shell
# 在tty界面中查看当前的tty编号
tty
```

```shell
# 查看所有的tty进程
w -s
```

## setterm

- `setterm`：修改虚拟控制台的外观。

<table>
    <tr>
        <th wdith="20%">选项</th>
        <th width="40%">参数</th>
        <th width="40%">意义</th>
    </tr>
    <tr>
        <td>--background</td>
        <td>black、red、green、yellow、blue、magenta、cyan、white</td>
        <td>将终端的背景色改为指定颜色</td>
    </tr>
    <tr>
        <td>--foreground</td>
        <td>black、red、green、yellow、blue、magenta、cyan、white</td>
        <td>将终端的前景色（文本）改为指定颜色</td>
    </tr>
    <tr>
        <td>--inversescreen</td>
        <td>on、off</td>
        <td>交换背景色和前景色</td>
    </tr>
    <tr>
        <td>--reset</td>
        <td></td>
        <td>将终端外观恢复成默认设置并清屏</td>
    </tr>
    <tr>
        <td>--store</td>
        <td></td>
        <td>将终端当前的前景色和背景色设置成--reset选项的值</td>
    </tr>
</table>

```shell
# 将虚拟控制台交换背景色和前景色
setterm --inversescreen on

# 修改虚拟控制台的背景和文本颜色
setterm --background green
setterm --foreground black
```

# XDG 桌面系统

```shell
# 查看当前桌面系统
echo $XDG_CURRENT_DESKTOP
echo $DESKTOP_SESSION
echo $GDMSESSION
systemctl status display-manager
```
