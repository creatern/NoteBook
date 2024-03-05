# 脚本执行

## 直接执行

- 需要可执行权限（x），在当前shell内开启一个子shell执行脚本，脚本结束时关闭子shell并回到父shell

```shell
./test.sh
```

## bash/sh shell命令语言解释器

- sh（shell）：sh是bash的别名命令，不需要执行权限，在当前shell中开启一个子shell执行脚本，脚本结束时关闭子shell并回到父shell

```shell
bash test.sh

sh test.sh
```

<table><tbody><tr><td>-c</td><td>从字符串中读取命令</td></tr><tr><td>-i </td><td>实现脚本交互 </td></tr><tr><td>-n </td><td>进行语法检查 </td></tr><tr><td>-v</td><td>显示执行过程详细信息</td></tr><tr><td>-x </td><td>实现逐条语句的跟踪 </td></tr><tr><td>--help</td><td>显示帮助信息</td></tr><tr><td>--version</td><td>显示版本信息</td></tr></tbody></table>

## . source 从指定文件中读取和执行命令

- source（. 点命令）：用于从指定文件（不需要执行权限）中读取和执行命令，通常用于被修改过的文件，使之新参数能够立即生效，而不必重启整台服务器。使脚本内容在当前shell里执行，而无需打开子shell

```shell
. test.sh

source test.sh
```

# 开机自启动的shell脚本

- 加入到[Systemed](./服务单元控制.md)的服务单元中，作为服务被启动

```shell
# 创建一个systemd服务单元文件
sudo vim /etc/systemd/system/oled-stats.service

# systemd识别新的服务
sudo systemctl daemon-reload

# 加入开机自启动
sudo systemctl enable oled-stats.service
```

```shell
[Unit]
Description=My OLED stats.py
After=network.target

[Service]
ExecStart=/home/zjk/oled-stats.sh
# simple 隐含了后台运行，无须在脚本加入&等后台运行符
Type=simple
Restart=always
RestartSec=10s

[Install]
WantedBy=default.target
```