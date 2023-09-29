# 安装配置

- 挂载点：指定该分区对应Linux文件系统的哪个目录。Linux允许将不同的物理磁盘上的分区映射到不同的目录，可以实现将不同的服务框架放在不同的物理磁盘上（当一个物理磁盘损坏时，不会影响其他物理磁盘上的数据）。

| 分区  | 说明           |
| ----- | -------------- |
| SWAP  | 虚拟内存交换区 |
| /     | 根分区         |
| /boot | 引导分区       |

- SWAP：硬盘模拟的虚拟内存。当系统内存使用率比较高时，内核会自动使用SWAP分区来存取数据。

| 生产环境          | SWAP大小设置   |
| ----------------- | -------------- |
| 物理内存 < 4G     | 物理内存的2倍  |
| 物理内存  4`~`16G | 与物理内存相等 |
| 物理内存 > 16G    | 物理内存的一半 |

| 文件系统 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| XFS      | 高度可扩高性能文件系统。<br />支持的最大的文件系统为：500TB，最大文件：16TB。<br />支持元数据日志，可以加快崩溃时的恢复速度<br />当挂载使用时，仍可以进行清理碎片和控制文件系统。 |
| ext      |                                                              |
| LVM      | 缓存。<br />允许用户创建逻辑卷(LV)，以小型快速设备作为更大、速度更慢的设备的缓存。 |

> KDUMP：开启后，将会使用一部分内存空间，当系统崩溃时KDUMP会捕获系统的关键信息，以便分析查找出系统崩溃的原因。此功能主要是系统相关的程序员使用，对普通用户而言意义不大，建议关闭。

## 界面

### 图形化

<img src="../../pictures/308405213232583.png" width="407"/>

| 界面系统 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| X Window | 以位图方式显示的软件窗口系统。<br />`startx`进入X Window环境。 |
| KDE      |                                                              |
| GNOME    | 默认安装GNOME桌面环境。                                      |

### 命令行

**Linux系统结构**

<img src="../../pictures/440202214237932.png" width="459"/> 

- `Ctrl + Windows + Alt + F3` 切换到终端3 （命令行界面)
- `Ctrl + Windows + Alt + F1` 切换到终端1 （图形化界面)

# 基本命令

- Linux中的Shell通常默认是bash（Bourne Again Shell），命令本身是一个函数。提供用户与操作系统进行交互操作的接口，方便用户使用系统中的软硬件资源。提供脚本语言编程环境，方便用户完成简单到复杂的任务调度。

- Linux启动时，最先进入内存的是内核，并常驻内存。然后进行系统引导，引导过程中启动所有进程的父进程在后台运行，直到相关的系统资源初始化完毕后，等待用户登录。用户登录时，通过登录进程验证用户的合法性。用户验证通过后根据用户的设置启动相关的Shell，以便接收用户输入的命令并返回执行结果。

<img src="../../pictures/Linux-user-shell-sys.drawio.svg" width="508"/> 

## alias 命令别名

- alias：设置指令的别名。只局限于该次登入的操作（直接在shell里设定的命令别名，在终端关闭或者系统重新启动后都会失效）。将别名写入bash的初始化文件`/etc/bashrc`，每次启动系统都会初始化（持久化）。

- 使用alias时，用户必须使用单引号将原来的命令引起来，防止特殊字符导致错误。

| 命令                     | 说明                                                        |
| ------------------------ | ----------------------------------------------------------- |
| alias<br />alias -p      | 查看已经设置的别名                                          |
| type 别名                | 检查别名是否已被使用                                        |
| alias 别名='命令1;命令2' | 设置别名，多条命令之间使用分号分隔                          |
| unalias 别名             | 撤销别名<br />只是删除本次操作中的别名（/etc/bashrc未更改） |

## history 历史命令

- history默认最大保存1000条历史命令（`$HISTSIZE`），保存在`~/.bash_history`（历史命令文件）。bash命令的历史记录先放在内存中，当shell退出时才被写入到历史文件中。

| 命令                      | 说明                                                         |
| ------------------------- | ------------------------------------------------------------ |
| history [n]<br />上下光标 | 查看历史命令记录                                             |
| history -c                | 清空当前缓冲区中的历史命令（并未对历史命令文件修改）         |
| history -r                | 将历史命令文件中的命令读入当前历史命令缓冲区<br />（否则只有在第一个终端被打开时才会读取历史命令文件）<br />（若同时打开多个终端，history -a 时其他终端的历史记录不会自动更新。） |
| history -a                | 将历史命令缓冲区中的命令写入历史命令文件                     |
| history -w                | 将**当前**历史命令缓冲区中的命令写入历史命令文件             |

### !! 执行上次命令

| 命令   | 说明                         |
| ------ | ---------------------------- |
| !!     | 执行上次命令                 |
| !{n}   | 执行指定序号的历史命令       |
| !{str} | 执行以指定字符开头的历史命令 |

## | 管道符

- `|`（管道符）：连接两个命令，将一个程序/命令的输出作为另一个程序/命令的参数输入。一般为输入和输出的结合，一个进程向管道的一端发送数据，而另一个进程从该管道的另一端读取数据。

## grep 文本过滤

- grep：默认区分大小写、支持正则。若不指定任何文件名称/给予的文件名为"-"，则grep命令从标准输入设备读取数据。

```shell
grep [选项] {"查找目标文本" | 查找目标文本} {被过滤文本}
```

| 选项 |                    |
| ---- | ------------------ |
| -v   | 反向搜索           |
| -n   | 匹配的同时输出行号 |
| -c   | 统计含有匹配的行数 |

### 正则

| 正则参数       | 说明                                                |
| :------------- | :-------------------------------------------------- |
| \              | 转义符                                              |
| ^              | 指定匹配字符串的行首 ；指定行的开始                 |
| $              | 指定匹配字符串的结尾  ； 指定行的结束               |
| *              | 表示0个以上的字符 ；匹配0个或多个先前的字符         |
| +              | 表示1个以上的字符 ；匹配1个或多个先前的字符         |
| ?              | 匹配0个或多个先前的字符                             |
| .              | 匹配一个非换行符的字符                              |
| []             | 匹配一个指定范围内的字符                            |
| [^]            | 匹配一个非指定范围内的字符                          |
| `\(..\)`       | 标记匹配字符                                        |
| <              | 指定单词的开始                                      |
| >              | 指定单词的结束                                      |
| x{m}           | 重复字符x，m次                                      |
| x{m,}          | 重复字符x，至少m次                                  |
| x{m,n}         | 重复字符x，至少m次，至多n次                         |
| w              | 匹配单词和数字 即：[A-Z] [a-z] [0-9]                |
| W              | w的反置形式，匹配一个或多个非单词字符，如点号句号等 |
| b              | 单词锁定符                                          |
| `          | ` |                                                     |
| ()             | 分组符                                              |
| [:alnum:]      | 文字数字字符                                        |
| [:alpha:]      | 文字字符                                            |
| [:digit:]      | 数字字符                                            |
| [:graph:]      | 非空格、控制字符                                    |
| [:lower:]      | 小写字符                                            |
| [:upper:]      | 大写字符                                            |
| [:cntrl:]      | 控制字符                                            |
| [:print:]      | 非空字符（包括空格）                                |
| [:punct:]      | 标点符号                                            |
| [:space:]      | 所有空白字符（换行、空格、制表符）                  |
| [:xdigit:]     | 十六进制数字 0-9 a-f A-F                            |

## awk

```shell
awk [options] 'script' var=value file(s)
awk [options] -f scriptfile var=value file(s)
```

| 选项         | 说明                                                         |
| :----------- | :----------------------------------------------------------- |
| -F fs        | fs指定输入分隔符，fs可以是字符串或正则表达式                 |
| -v var=value | 赋值一个用户定义变量，将外部变量传递给awk                    |
| -f scripfile | 从脚本文件中读取awk命令                                      |
| -m[fr] val   | 对val值设置内在限制<br />-mf选项限制分配给val的最大块数目。<br />-mr选项限制记录的最大数目。 |

## 标准输入与输出

- 执行一个Shell命令通常会自动打开3个标准文件：标准输入文件stdin（对应终端的键盘）、标准输出文件stout、标准错误输出文件stderr（对应终端的屏幕）。
- 进程从标准输入文件得到输入数据，将正常输出数据输出到标准输出文件，而错误信息则打印到标准错误文件。

<img src="../../pictures/153845716239573.png" width="369"/> 

| 命令   | 说明                             |
| ------ | -------------------------------- |
| echo   | 输出指定的字符串/变量            |
| printf | 格式化并输出结果                 |
| cat    | 连接文件并打印到标准输出设备上   |
| tail   | 在屏幕上显示指定文件的末尾若干行 |
| head   | 在屏幕上显示指定文件的头若干行   |
| more   |                                  |
| less   |                                  |

- 如果给定的文件不止一个，则在显示的每个文件前面加一个文件名标题。如果没有指定文件或者文件名为“-”，则读取标准输入。 

```shell
echo -e "\e[编码1;编码2;编码3m内容"
# 文字色：
#颜色码：重置=0，黑色=30，红色=31，绿色=32，黄色=33，蓝色=34，洋红=35，青色=36，白色=37
echo -e "\e[1;31m内容\e[0m"
#\e[1;31m 将颜色设置为红色
#\e[0m 将颜色重新置回
# 背景色 ：
#颜色码：重置=0，黑色=40，红色=41，绿色=42，黄色=43，蓝色=44，洋红=45，青色=46，白色=47
echo -e "\e[1;42m内容\e[0m"
# 文字闪动：
# 0 关闭所有属性、1 设置高亮度（加粗）、4 下划线、5 闪烁、7 反显、8 消隐
echo -e "\033[37;31;5mMySQL Server Stop...\033[39;49;0m"
```

**cat命令 不带参数时**

- 从标准输入中读取数据并显示到标准输出文件中
- 此时，每一行键盘（标准输入文件）输入的数据，都会立刻被输出在屏幕上（标准输出文件）。

### 重定向

重定向操作符：把命令/可执行程序的标准输入/输出重定向到指定的文件。

| 操作符 | 说明                                                         |
| ------ | ------------------------------------------------------------ |
| `<`    | 输入重定向                                                   |
| `<<`   | 文档的重定向                                                 |
| `>`    | 输出重定向<br />若`>`后面的文件不存在，则创建该文件。若存在，则将内容覆盖到该文件。 |
| `>>`   | 追加输出重定向<br />若`>>`后面的文件不存在，则创建该文件。若存在，则将内容追加到该文件。 |
| 2>     | 错误输出重定向<br />若`2>`后面的文件不存在，则创建该文件。若存在，则将内容覆盖到该文件。<br />如果有错误信息，则不会在屏幕（标准输出文件）输出，而会保存在指定的文件中。即使没有错误信息也会创建/覆盖。 |
| 2>>    | 追加错误输出重定向<br />若`2>>`后面的文件不存在，则创建该文件。若存在，则将内容追加到该文件。<br />如果有错误信息，则不会在屏幕（标准输出文件）输出，而会保存在指定的文件中。即使没有错误信息也会创建/追加。 |

- 重定向操作符可以混合使用。

```shell
命令 >输出 2>&1 #将标准输出和标准错误输出重定向到同一个文件
ls >myOutAndErr.txt 2>&
```

## 自动任务

### at 定时任务

- at：在指定时间执行任务，只执行一次。

```
at [选项] [时间]
```

| 选项 | 说明                           |
| ---- | ------------------------------ |
| -f   | 指定包含具体指令的任务文件     |
| -q   | 指定新任务的队列名称           |
| -l   | 显示待执行任务的列表           |
| -d   | 删除指定的待执行任务           |
| -m   | 任务执行完成后向用户发送E-mail |

| 指定时间的格式 | 说明                                                         |
| -------------- | ------------------------------------------------------------ |
| 绝对计时法     | 当天的hh:mm。若该时间已过去，那么就放在第二天执行。<br />midnight（深夜），noon（中午），teatime（一般是下午4点）等比较模糊的词语来指定时间。<br />12小时计时制，时间后面加上AM（上午）、PM（下午）。<br />具体日期，指定格式为month day、mm/dd/yy、dd.mm.yy。指定的日期必须跟在指定时间的后面。 |
| 相对计时法     | now + count time-units：now当前时间、time-units是时间单位 minutes（分钟）、hours（小时）、days（天）、weeks（星期）、count是时间的数量，究竟是几天，还是几小时，等等。<br />直接使用today（今天）、tomorrow（明天）。 |

```shell
[root@localhost ~]# at 23:43 today
at> date >/root/today.log            
at> <EOT>
job 3 at Sun Nov 27 23:43:00 2022
```

#### atq 显示当前所有定时任务

```shell
[root@localhost ~]# atq
4       Wed Nov 30 08:40:00 2022 a root
```

#### atrm 删除指定序号的定时任务

```shell
atrm 任务序号
```

<img src="../../pictures/Snipaste_2022-11-27_23-49-33.png" width="300"/> 

### crontab 周期任务

- crontab：提交和管理用户需要周期性执行的任务。当安装完成操作系统后，默认会安装此服务工具，并且会自动启动crond进程，crond进程每分钟会定期检查是否有要执行的任务，如果有要执行的任务，则自动执行该任务。

```shell
crontab [选项] [crontab 的任务列表文件]
```

| 选项           | 说明                       |
| -------------- | -------------------------- |
| -e             | 编辑该用户的计时器设置     |
| -l             | 列出该用户的计时器设置     |
| -r             | 删除该用户的计时器设置     |
| `-u<用户名称>` | 指定要设置定时器的用户名称 |

#### 系统任务调度、用户任务调度

**系统任务调度**

- 系统周期性所要执行的工作，比如写缓存数据到硬盘、日志清理等。
- 系统任务调度的配置文件`/etc/crontab`

<img src="../../pictures/Snipaste_2022-11-28_00-01-43.png" width="600"/> 

```shell
[root@localhost etc]# ll | grep cron
-rw-------.  1 root root      541 Mar 30  2017 anacrontab
drwxr-xr-x.  2 root root       54 Nov 13 21:16 cron.d
drwxr-xr-x.  2 root root       70 Nov 13 21:16 cron.daily
-rw-------.  1 root root        0 Mar 30  2017 cron.deny
drwxr-xr-x.  2 root root       41 Nov 13 21:16 cron.hourly
drwxr-xr-x.  2 root root        6 Dec 28  2013 cron.monthly
-rw-r--r--.  1 root root      451 Dec 28  2013 crontab
drwxr-xr-x.  2 root root        6 Dec 28  2013 cron.weekly
```

**用户任务调度**

- 用户定期要执行的工作，比如用户数据备份、定时邮件提醒等。
- 所有用户定义的crontab文件都被保存在`/var/spool/cron`目录中。其文件名与用户名一致，
- 使用者权限文件如下：
  - `/etc/cron.deny `     该文件中所列用户不允许使用crontab命令
  - `/var/spool/cron/`   所有用户crontab文件存放的目录,以用户名命名 

<img src="../../pictures/Snipaste_2022-11-28_00-12-54.png" width="800"/> 

```shell
# 每1分钟执行一次command
* * * * * command

# 每小时的第3和第15分钟执行
3,15 * * * * command

# 在上午8点到11点的第3和第15分钟执行
3,15 8-11 * * * command

# 每隔两天的上午8点到11点的第3和第15分钟执行
3,15 8-11 */2 * * command
# 每个星期一的上午8点到11点的第3和第15分钟执行
3,15 8-11 * * 1 command

# 每晚的21:30重启smb 
30 21 * * * /etc/init.d/smb restart

# 每月1、10、22日的4 : 45重启smb 
45 4 1,10,22 * * /etc/init.d/smb restart

# 每周六、周日的1:10重启smb
10 1 * * 6,0 /etc/init.d/smb restart

# 每天18 : 00至23 : 00之间每隔30分钟重启smb 
0,30 18-23 * * * /etc/init.d/smb restart

# 每星期六的晚上11:00 pm重启smb 
0 23 * * 6 /etc/init.d/smb restart

# 每一小时重启smb 
* */1 * * * /etc/init.d/smb restart

# 晚上11点到早上7点之间，每隔一小时重启smb
* 23-7/1 * * * /etc/init.d/smb restart

# 每月的4号与每周一到周三的11点重启smb 
0 11 4 * mon-wed /etc/init.d/smb restart

# 一月一号的4点重启smb
0 4 1 jan * /etc/init.d/smb restart

# 每小时执行/etc/cron.hourly目录内的脚本
01 * * * * root run-parts /etc/cron.hourly
```

#### crond服务

```shell
service crond status # 查看crond服务状态
/sbin/service crond start    # 启动服务
/sbin/service crond stop     # 关闭服务
/sbin/service crond restart  # 重启服务
/sbin/service crond reload   # 重新载入配置
ntsysv # 查看crond服务是否已经设置为开机启动
chkconfig –level 35 crond on # 加入开机自动启动
```

# 文件管理

## Linux目录结构

- Linux将文件存储在单个目录结构中（虚拟目录），虚拟目录将安装在PC上的所有存储设备的文件路劲纳入单个目录结构中。Linux虚拟目录结构只包含一个根目录 / 的基础目录。根目录下的目录和文件会按照访问的目录路径一一列出。
- 根驱动器：在Linux PC上安装的第一个硬盘，包含了虚拟目录的核心，从此开始构造其他目录。
- 挂载点：在根驱动器上创建的目录，是虚拟目录中用于分配额外存储设备的目录。虚拟目录会让（该额外存储设备上的）文件和目录出现在这些挂载点的目录上。

**基于文件系统层级标准FHS**

| 目录          | 名称       | 说明                                                                                                                                                |
|:----------- |:-------- |:------------------------------------------------------------------------------------------------------------------------------------------------- |
| /           | 根目录      | 文件的最顶端，/ect、/bin、/dev、/lib、/sbin应该和根目录放置在一个分区中，而类似/usr/local可以单独位于另一个分区                                                                           |
| /bin        | 二进制目录    | 存放系统所需要的重要命令，比如文件或目录操作的命令ls、cp、mkdir等。另外/usr/bin也存放了一些系统命令，这些命令对应的文件都是可执行的，普通用户可以使用大部分的命令                                                         |
| /boot       | 启动目录     | 这是存放Linux启动时内核及引导系统程序所需要的核心文件，内核文件和gub系统引导管理器（或称引导装载程序）都位于此目录                                                                                     |
| /dev        | 设备目录     | 存放Linux系统下的设备文件，如光驱、磁盘等。访问该目录下某个文件相当于访问某个硬件设备，常用的是挂载光驱                                                                                            |
| /etc        | 系统配置文件目录 | 一般存放系统的配置文件，作为一些软件启动时默认配置文件读取的目录，如/etc/fstab存放系统分区信息                                                                                              |
| /home       | 主目录      | 系统默认的用户主目录。如果添加用户时不指定用户的主目录，默认在/home下创建与用户名同名的文件夹。代码中可以用HOME环境变量表示当前用户的主目录                                                                        |
| /lib        | 库目录      | 64位系统有/lib64文件夹，主要存放动态链接库。类似的目录有/usr/1ib、/usr/local/lib等                                                                                          |
| /lost+found | 媒体目录     | 存放一些当系统意外崩溃或机器意外关机时产生的文件碎片                                                                                                                        |
| /mnt        | 挂载目录     | 用于存放挂载储存设备的挂载目录，如光驱等                                                                                                                              |
| /proc       | 进程目录     | 存放操作系统运行时的信息，如进程信息、内核信息、网络信息等。此目录的内容存在于内存中，实际不占用磁盘空间，如/etc/cpuinfo存放CPU的相关信息                                                                      |
| /root       | -        | Linux超级权限用户root的主目录                                                                                                                               |
| /sbin       | 系统二进制目录  | 存放一些系统管理的命令，一般只能由超级权限用户root执行。大多数命令普通用户一般无权限执行，类似/sbin/ifconfig，普通用户使用绝对路径也可执行，用于查看当前系统的网络配置。类似的目录有usr/sbin、usr/local/sbin                        |
| /tmp        | 临时目录     | 临时文件目录，任何人都可以访问。系统软件或用户运行程序（如MySQL)时产生的临时文件存放到这里。此目录数据需要定期清除。重要数据不可放置在此目录下，此目录空间不易过小                                                              |
| /usr        | 用户二进制目录  | 应用程序存放目录，如命令、帮助文件等。安装Linux软件包时默认安装到/usr/local目录下。比如/usr/share/fonts存放系统字体，/usr/share/man存放帮助文档，/usr/include存放软件的头文件等。/usr/local目录建议单独分区并设置较大的磁盘空间 |
| /var        | 可变目录     | 这个目录的内容是经常变动的，如/var/log用于存放系统日志、/var/lib用于存放系统库文件等                                                                                                |
| /sys        | 系统目录     | 目录与/proc类似，是一个虚拟的文件系统，主要记录与系统核心相关的信息，如系统当前已经载入的模块信息等。这个目录实际不占硬盘容量                                                                                 |
| /srv        | 服务目录     | 存放本地服务的相关文件                                                                                                                                       |
| /sbin       | 系统二进制目录  | 存放许多GNU管理员级工具                                                                                                                                     |
| /run        | 运行目录     | 存放系统运行时的运行数据                                                                                                                                      |
| /opt        | 可选目录     | 常用于存放第三方软件包和数据文件                                                                                                                                  |
| /media      | 媒体目录     | 可移动媒体设备的常用挂载点                                                                                                                                     |

### cd 切换文件目录

```shell
## 到达指定目录
cd 绝对路径/相对路径
## 当前目录
.
## 切换到当前目录的父目录
cd .. 
## 返回到/home
cd ~
```

### pwd 显示当前的绝对路径

### ls （按行）列出当前目录下的文件和目录

```shell
## 列出当前的文件和目录
ls
## 列出所有，包括隐藏的内容
ls -a 
## 递归输出当前目录的所有子目录中的文件
ls -R
## ll 显示长列表
ls -l
## 搭配通配符使用
ls -l 字符串通配符匹配
## 显示文件大小
ls -i
```

### ll 显示长列表

```shell
ll  #等于 ls -l
```

### file 查看文件类型

```shell
## 对普通目录
[root@bogon /]# file boot
boot: directory  
## 对软连接文件
[root@bogon /]# file bin
bin: symbolic link to usr/bin
## 对脚本文件，而且可以确定文件的字符集
[root@bogon shellTest]# file if_demo01.sh
if_demo01.sh: Bourne-Again shell script, UTF-8 Unicode text executable
## 对二进制文件 可以确定该程序编译时所面向的平台以及需要何种类型的库
[root@bogon shellTest]# file /bin/ls
/bin/ls: ELF 64-bit LSB shared object, x86-64, version 1 (SYSV), dynamically linked, interpreter /lib64/ld-linux-x86-64.so.2, for GNU/Linux 3.2.0, BuildID[sha1]=bccb4c17516c6a9ad59c3ec19b347c83236c04c2, stripped
```

## 文件信息

<img src="../pictures/Snipaste_2022-11-28_20-30-27.png" width="700"/> 

| 参数 | 文件类型                               |
| :--- | :------------------------------------- |
| -    | 普通文件                               |
| d    | 目录文件                               |
| l    | 符号链接文件                           |
| b/c  | 设备文件；b-块设备文件；c-字符设备文件 |
| p    | 管道文件                               |

| 权限 | 说明               |
| :--- | :----------------- |
| r    | 读权限             |
| w    | 写权限             |
| x    | 执行权限           |
| 777  | 绝对权限 rwxrwxrwx |

## 文件权限

- 对root用户而言，rw权限是可以无视的，而可执行文件则必须要有x权限（包括root用户）。

### chown 改变文件所有者

| 命令                  | 说明                         |
|:------------------- |:-------------------------- |
| chown -R 用户 文件/目录文件 | 将目录及其下面的所有文件、子目录的文件主改成指定用户 |

### chgrp 改变文件所属组

- 文件所属组的组名可以是用户组的id、组名。

```shell
chgrp [选项][组群][文件|目录]
```

```shell
[root@bogon zipTest]# chgrp -R root /root/Test/zipTest
```

### chmod 变更文件或目录的权限

## 文件操作

### touch 创建新的空文件

- touch：把已存在文件的时间标签更新为系统当前的时间（默认方式）（不改变数据）。若文件不存在，则创建新的空文件。

### sed 流编辑器

- sed流编辑器处理时，把当前处理的行存储在临时缓冲区（模式空间 pattern space），接着用sed命令处理缓冲区中的内容，处理完成后，把缓冲区的内容送往屏幕。接着处理下一行，这样不断重复，直到文件末尾。文件内容并没有改变，除非使用重定向存储输出。

```shell
sed [options] 'command' file(s)
sed [options] -f scriptfile file(s)
```

```shell
[root@localhost ~]# head -3 /etc/passwd |sed -n 2p
bin:x:1:1:bin:/bin:/sbin/nologin
[root@localhost ~]# head -3 /etc/passwd |sed -n 2,3p
bin:x:1:1:bin:/bin:/sbin/nologin
daemon:x:2:2:daemon:/sbin:/sbin/nologin
```

| 命令 | sed替换标记                                              |
| :--- | :------------------------------------------------------- |
| g    | 行内全面替换。                                           |
| p    | 打印行。                                                 |
| w    | 把行写入一个文件。                                       |
| x    | 互换模板块中的文本和缓冲区中的文本。                     |
| y    | 把一个字符翻译为另外的字符<br />（但是不用于正则表达式） |
| \1   | 子串匹配标记                                             |
| &    | 已匹配字符串标记                                         |

**sed元字符集**

| 命令       | 说明                                                        |
|:-------- |:--------------------------------------------------------- |
| ^        | 匹配行开始，如：/^sed/匹配所有以sed开头的行。                               |
| $        | 匹配行结束，如：/sed$/匹配所有以sed结尾的行。                               |
| .        | 匹配一个非换行符的任意字符，如：/s.d/匹配s后接一个任意字符，最后是d。                    |
| *        | 匹配0个或多个字符，如：/*sed/匹配所有模板是一个或多个空格后紧跟sed的行。                 |
| []       | 匹配一个指定范围内的字符，如/[ss]ed/匹配sed和Sed。                          |
| [^]      | 匹配一个不在指定范围内的字符，如：/[^A-RT-Z]ed/匹配不包含A-R和T-Z的一个字母开头，紧跟ed的行。 |
| \(..\)   | 匹配子串，保存匹配的字符，如s/\(love\)able/\1rs，loveable被替换成lovers。     |
| &        | 保存搜索字符用来替换其他字符，如s/love/ **&** /，love这成 **love** 。         |
| \<       | 匹配单词的开始，如:/\<love/匹配包含以love开头的单词的行。                       |
| \>       | 匹配单词的结束，如/love\>/匹配包含以love结尾的单词的行。                        |
| x\{m\}   | 重复字符x，m次，如：/0\{5\}/匹配包含5个0的行。                             |
| x\{m,\}  | 重复字符x，至少m次，如：/0\{5,\}/匹配至少有5个0的行。                         |
| x\{m,n\} | 重复字符x，至少m次，不多于n次，如：/0\{5,10\}/匹配5~10个0的行。                 |

### tar 打包/解包 归档

```shell
tar 选项 参数
```

| 扩展名                | 说明            |
| --------------------- | --------------- |
| `.tar`                |                 |
| `.tar.gz`<br />`.tgz` | gzip压缩的tar包 |
| `.tar.bz2`            |                 |

- 归档：使用-c创建一个空的归档文件（不是文件的压缩），再通过-f 文件 的方式覆盖新的内容到该归档文件，或使用-r 文件 的方式追加新的文件到该归档文件中。

```shell
-c # 创建空的归档文件
-f # 将指定的文件覆盖到归档文件中
-r # 将指定的文件追加到归档文件中
```

| 组合                                          | 作用           |
|:------------------------------------------- |:------------ |
| tar -cvf 归档文件 目标文件                          | 仅打包          |
| tar -zcvf 输出文件 目标文件                         | 打包，gzip压缩    |
| tar -jcvf 输出文件 目标文件                         | 打包，bzip2压缩   |
| tar -ztvf 目标文件                              | 查看压缩包文件列表    |
| tar -zxvf 目标文件                              | 解压压缩包到当前路径   |
| tar -zxvf 目标文件 指定文件                         | 只压缩指定文件      |
| tar -zxvpf 输出文件 目标文件                        | 建立压缩包时保留文件属性 |
| tar --exclude 排除的文件1 排除的文件2 ... 执行的选项/组合 参数 | 排除某些文件       |

```shell
tar -cvf /tmp/etc.tar /etc
tar -zcvf /tmp/etc.tar.gz /etc
tar -zcvf /tmp/etc.tar.bz2 /etc
tar -ztvf /tmp/etc.tar.bz2
tar -zxvf /tmp/etc.tar.gz etc/passwd
tar --exclude /home/*log -zcvf /tmp/etc2.tar.gz etc/passwd
```

### zip / unzip 压缩/解压缩 .zip

```shell
zip [参数] [打包后的文件名] [打包的目录路径]
```

| 压缩常用组合               | 作用       |
| :------------------------- | :--------- |
| zip 压缩文件名.zip 文件名  | 压缩文件   |
| zip -r 压缩包名.zip 文件夹 | 压缩文件夹 |

| 解压缩常用组合                         | 作用                     |
| :------------------------------------- | :----------------------- |
| unzip 压缩文件名.zip                   | 解压文件                 |
| unzip 压缩文件名.zip -d 目标文件夹路径 | 解压到目标文件夹         |
| unzip -v 压缩包名.zip                  | 查看压缩包内容，而不解压 |

```shell
## zip 压缩包.zip 文件
[root@bogon zipTest]# zip test1.zip testZip1.txt
updating: testZip1.txt (deflated 23%)
[root@bogon zipTest]# ll
total 12
-rw-r--r--. 1 root root 424 Nov 27 20:27 test1.zip
-rw-r--r--. 1 root root 323 Nov 27 20:25 testZip1.txt
-rw-r--r--. 1 root root 323 Nov 27 20:26 testZip2.txt

## zip -r 压缩包.zip 文件夹
[root@bogon Test]# zip -r zipTest1.zip zipTest
  adding: zipTest/ (stored 0%)
  adding: zipTest/testZip1.txt (deflated 23%)
  adding: zipTest/testZip2.txt (deflated 23%)
  adding: zipTest/test1.zip (stored 0%)
[root@bogon Test]# ll
total 4
drwxr-xr-x. 2 root root   63 Nov 27 20:27 zipTest
-rw-r--r--. 1 root root 1588 Nov 27 20:29 zipTest1.zip

## unzip 压缩包.zip
[root@bogon Test]# unzip zipTest1.zip
Archive:  zipTest1.zip
replace zipTest/testZip1.txt? [y]es, [n]o, [A]ll, [N]one, [r]ename: y
  inflating: zipTest/testZip1.txt    
replace zipTest/testZip2.txt? [y]es, [n]o, [A]ll, [N]one, [r]ename: y
  inflating: zipTest/testZip2.txt    
replace zipTest/test1.zip? [y]es, [n]o, [A]ll, [N]one, [r]ename: y
 extracting: zipTest/test1.zip       
[root@bogon Test]# ll
total 4
drwxr-xr-x. 2 root root   63 Nov 27 20:32 zipTest
-rw-r--r--. 1 root root 1588 Nov 27 20:29 zipTest1.zip

## unzip 压缩包.zip -d ./Test
[root@bogon Test]# unzip zipTest1.zip -d /root/Test/zipTest
Archive:  zipTest1.zip
   creating: /root/Test/zipTest/zipTest/
  inflating: /root/Test/zipTest/zipTest/testZip1.txt  
  inflating: /root/Test/zipTest/zipTest/testZip2.txt  
 extracting: /root/Test/zipTest/zipTest/test1.zip  
[root@bogon Test]# ll
total 4
drwxr-xr-x. 3 root root   78 Nov 27 20:35 zipTest
-rw-r--r--. 1 root root 1588 Nov 27 20:29 zipTest1.zip
[root@bogon Test]# cd zipTest
[root@bogon zipTest]# ll
total 12
-rw-r--r--. 1 root root 424 Nov 27 20:27 test1.zip
-rw-r--r--. 1 root root 323 Nov 27 20:25 testZip1.txt
-rw-r--r--. 1 root root 323 Nov 27 20:26 testZip2.txt
drwxr-xr-x. 2 root root  63 Nov 27 20:27 zipTest

## unzip -v 压缩包.zip
[root@bogon Test]# unzip -v zipTest1.zip
Archive:  zipTest1.zip
 Length   Method    Size  Cmpr    Date    Time   CRC-32   Name
--------  ------  ------- ---- ---------- ----- --------  ----
       0  Stored        0   0% 11-27-2022 20:27 00000000  zipTest/
     323  Defl:N      250  23% 11-27-2022 20:25 eb905491  zipTest/testZip1.txt
     323  Defl:N      250  23% 11-27-2022 20:26 eb905491  zipTest/testZip2.txt
     424  Stored      424   0% 11-27-2022 20:27 a68b676d  zipTest/test1.zip
--------          -------  ---                            -------
    1070              924  14%                            4 files
```

### gzip 压缩 .gz

#### gzcat 查看压缩文件的内容

#### gunzip 解压

### sort 对文件数据排序

- sort命令默认会将文本中的数据当成字符来排序按照会话指定的默认语言的升序排序顺序输出，包括数字和时间日期等，而不是按照相应的规则来排序。

| 命令                           | 说明                                  |
| ---------------------------- | ----------------------------------- |
| sort -n                      | 将文本识别为数字来排序                         |
| sort -M                      | 将文本识别为Mar形式的月份来排序                   |
| sort -t '分隔字符' -k 指定排序的字符段位置 | -t对每行的字符段进行分隔，然后-k选择每行分隔的其中一段字符进行排序 |
| sort -r                      | 将排序结果降序输出                           |

```shell
## sort -t '字符' -k  文件
## 将/etc/passwd按uid来排序
[root@bogon ~]# sort -t ':' -k 3  /etc/passwd | head -n 5
root:x:0:0:root:/root:/bin/bash
operator:x:11:0:operator:/root:/sbin/nologin
bin:x:1:1:bin:/bin:/sbin/nologin
games:x:12:100:games:/usr/games:/sbin/nologin
ftp:x:14:50:FTP User:/var/ftp:/sbin/nologin
```

### mv 移动/重命名

- 用来对文件或目录重新命名，或者将文件从一个目录移到另一个目录中。
- 如果将一个文件移到一个已经存在的目标文件中，则目标文件的内容将被覆盖。

**源文件被移至目标文件有两种不同的结果：**

1. 如果目标文件是到某一目录文件的路径，源文件会被移到此目录下，且文件名不变。
2. 如果目标文件不是目录文件，则源文件名（只能有一个）会变为此目标文件名，并覆盖己存在的同名文件。

| 组合        | 说明         |
|:--------- |:---------- |
| mv 源文件 目标 | 将源文件移到目标位置 |

### rm 删除

### cp 复制

```shell
cp 复制的文件路径 目标
```

### mkdir 新建文件夹

```shell
## 在当前路径下创建目录
mkdir 目录
## 创建目录及其上层目录
mkdir /目录1/目录2
## 创建目录的同时指定权限
mkdir -m 777 目录
```

### ln 文件链接

| 链接   | 说明                                                         |
| ------ | ------------------------------------------------------------ |
| 硬链接 | 如果源文件被删除，硬链接仍然可以正常使用、读写数据。<br />不可以跨区/磁盘创建硬链接。<br />硬链接与源文件使用的是相同的设备、inode编号、文件属性和源文件相同。<br />`ln 源路径 目标路径` |
| 软链接 | 如果源文件被删除，则无法继续使用软链接。<br />可以跨分区/磁盘创建软链接。<br />软链接的文件属性被标记为`l`;同时有指针`->`指向源文件<br />`ln -s 完整源路径 目标路径` |

## 文件查看

| 命令 | 说明                                                         |
| ---- | ------------------------------------------------------------ |
| cat  | 一次性显示文本文件的所有内容<br />不适用于内容大的文件，一旦运行就无法控制后面的操作 |
| more | 分页显示文本内容<br />使用空格或回车来逐行查看文件内容，只支持文本文件中的基本查看 |
| less | 在more的基础上，能够使用上下和翻页键                         |
| tail | 显示文件的最后几行，默认显示文件的最后10行                   |
| head | 显示文件的头几行                                             |

```shell
## 指定显示的末尾n行
tail -n 行数 文件
## 保持活动状态，不断显示文件中更改的内容
tail -f 文件
```

# 应用管理

## rpm

| 组合                            | 说明                                                         |
| :------------------------------ | :----------------------------------------------------------- |
| rpm -ivh RPM包                  | 安装时显示信息                                               |
| rpm -ivh --force RPM包          | 强制安装                                                     |
| rpm -ivh --nodeps --force RPM包 | 无视依赖，强制安装                                           |
| rpm -qpl RPM包                  | 查看软件包的详细信息                                         |
| rpm -Uvh RPM包                  | 更新已安装的软件包                                           |
| rpm -qa                         | 查看已安装的软件包                                           |
| rpm -e                          | 卸载软件包<br />如果存在依赖关系，需要先卸载需要其依赖的软件包 |
| rpm -qf                         | 查看文件所属软件包                                           |
| rpm -qip                        | 获取说明信息                                                 |

## tar.gz 源码安装

> 源码包的安装具体需要参考提供者的规范。

> RedHat7.4的Linux系统：保存源代码的位置主要有 /usr/src、/usr/local/src。
> 
> - /usr/src：保存内核源代码。
> 
> - /usr/local/src：保存用户下载的源代码。

1. 下载源码包，tar 解压缩，cd 进入解压目录。
2. `./configure` 软件配置与检查 。
3. make 编译。
4. make install 正式开始安装软件。
7. PATH 添加到环境变量。

- configure：源码包软件自带的一个脚本程序，必须采用`./configure`方式执行。通过系统环境的检测结果和定义好的功能选项生成编译规则文件 Makefile ，后续的编译和安装需要依赖这个文件的内容。

| 命令                        | 说明        |
|:------------------------- |:--------- |
| ./configure --prefix=安装路径 | 指定安装路径。   |
| ./configure --help        | 查询其支持的功能。 |

- 源码包的卸载：找到软件的安装位置，直接删除所在目录即可，不会遗留任何垃圾文件。（在删除软件之前，应先将软件停止服务）

> RedHat7.4：源码包方式安装的各个软件，其安装文件独自保存在 /usr/local/ 目录下的各子目录中。

## Linux函数库

- 函数库是一个文件，包含已经编译好的代码和数据，供程序使用。

| 函数库         | 说明                                                         |
| -------------- | ------------------------------------------------------------ |
| 静态函数库     | 在编译程序时，如果指定静态函数库文件，则编译时将这些静态函数库一起加载到最终的可执行文件中，在程序执行前加入目标程序。<br />文件后缀 .a、所在目录 /usr/lib、通常只有一个程序使用。 |
| 共享函数库     | 在程序启动时加载到程序，可以被不同的程序共享。<br />当一个可执行程序启动时被加载、所在目录 /lib 和 /usr/lib、被多个程序使用。 |
| 动态加载函数库 | 可以在程序运行的任何时候动态的加载。<br />文件后缀 .so 、一般都是共享函数库 。 |

- 缓存文件（/etc/ld.so.cache）：可以找到相关的库文件信息。

```shell
## 修改 /etc/ld.so.conf 文件，将该库的文件路径添加到文件
echo "/usr/local/ssl/lid/" >> /etc/ld.so.conf

## 让系统升级ld.so.cache文件
ldconfig
```

- LD_LIBRARY_PATH环境变量

```shell
export LD_LIBRARY_PATH=/usr/local/ssl/lib:$LD_LIBRARY_PATH:.
```

- ldd 查看程序使用了哪些动态库文件

## yum

## deb

### apt

- 由Linux查找相应的软件包，并自动安装（包括依赖）。

```shell
sudo apt install <软件名>
```

- 查询相关的软件包。

```shell
sudo apt search <keyword>
```

### dpkg

```shell
sudo dpkg -i *.deb
```

- 如果需要安装依赖，则在之后输入：

```shell
sudo apt-get -f -y install
```

## Flatpak

## Snap

```shell
sudo snap install <app>
```

# 用户管理

| 类型     | 说明                                                         |
| :------- | :----------------------------------------------------------- |
| 超级用户 | root 或 UID为0 的用户                                        |
| 系统用户 | 正常运行时系统使用的用户，每个进程在系统中都有一个相应的属主。系统用户不能用来登录（bin、daemon、mail等） |
| 普通用户 | user                                                         |

## 用户管理机制

| 文件         | 说明         |
| :----------- | :----------- |
| /etc/passswd | 用户账号文件 |
| /etc/shadow  | 用户密码文件 |
| /etc/group   | 用户组文件   |

### /etc/passwd

<img src="../../pictures/Snipaste_2022-11-30_13-43-52.png" width="500"/> 

<img src="../../pictures/Snipaste_2022-11-30_14-12-25.png" width="800"/> 

- 普通用户的UID默认1000以上的数字

### /etc/shadow

<img src="../../pictures/Snipaste_2022-11-30_16-21-03.png" width="800"/> 

<img src="../../pictures/Snipaste_2022-11-30_16-35-15.png" width="1000"/> 

### /etc/group

- 用户登录时默认的组放在/etc/passwd中

<img src="../../pictures/Snipaste_2022-11-30_18-46-41.png" width="200"/> 

<img src="../../pictures/Snipaste_2022-11-30_18-52-02.png" width="280"/> 

## 用户管理命令

### useradd 添加用户

```shell
[root@bogon ~]# useradd user1
[root@bogon ~]# cat /etc/passwd | grep user1
user1:x:1001:1001::/home/user1:/bin/bash
[root@bogon ~]# cat /etc/shadow | grep user1
user1:!!:19326:0:99999:7:::
[root@bogon ~]# cat /etc/group | grep user1
user1:x:1001:
```

### usermod 修改用户

- usermod 命令不允许你改变正在线上的使用者帐号名称。当 usermod 命令用来改变user id，必须确认这名user没在电脑上执行任何程序。你需手动更改使用者的 crontab 档。也需手动更改使用者的 at 工作档。采用 NIS server 须在server上更动相关的NIS设定。

```shell
[root@bogon ~]# usermod -l newuser user1 
[root@bogon ~]# cat /etc/passwd | grep newuser
newuser:x:1001:1001::/home/user1:/bin/bash
[root@bogon ~]# cat /etc/passwd | grep user1
newuser:x:1001:1001::/home/user1:/bin/bash
```

### userdel 删除用户

```shell
# 删除用户，（-r）包含该用户的相关任务和文件
[root@bogon ~]# userdel -r newuser
```

### passwd 用户密码

- 用于设置用户的认证信息，包括用户密码、密码过期时间等。
- 系统管理者则能用它管理系统用户的密码。只有管理者可以指定用户名称，一般用户只能变更自己的密码。

```shell
[root@bogon ~]# passwd
Changing password for user root.
New password: 
BAD PASSWORD: The password is shorter than 8 characters
Retype new password: 
passwd: all authentication tokens updated successfully.
[root@bogon ~]# passwd zjk
Changing password for user zjk.
New password: 
BAD PASSWORD: The password is shorter than 8 characters
Retype new password: 
passwd: all authentication tokens updated successfully.
```

### su 切换用户

| 组合            | 说明                                             |
| :-------------- | :----------------------------------------------- |
| su 用户         | 切换用户，不改变环境变量（不指定用户则默认root） |
| su - 用户       | 切换用户，改变环境变量（不指定用户则默认root）   |
| su -c 命令 用户 | 切换用户执行命令之后，回原来用户                 |

```shell
[root@bogon ~]# su
[root@bogon ~]# su zjk
[zjk@bogon root]$ pwd
/root
[root@bogon ~]# su - zjk
Last login: Wed Nov 30 19:16:03 CST 2022 on pts/0
[zjk@bogon ~]$ pwd
/home/zjk
[zjk@bogon ~]$ su -c ls root
Password: 
Desktop  Documents  Downloads  Music  Pictures  Public  Templates  Videos
```

### sudo 普通用户获取超级权限

- sudo用来以其他身份来执行命令，预设的身份为root。/etc/sudoers中设置了可执行sudo指令的用户。若其未经授权的用户企图使用sudo，则会发出警告的邮件给管理员。用户使用sudo时，必须先输入密码，之后有5分钟的有效期限，超过期限则必须重新输入密码。

### id 查看用户信息

- id：可以显示真实有效的用户ID(UID)和组ID(GID)。

```shell
[root@bogon ~]# id
uid=0(root) gid=0(root) groups=0(root) context=unconfined_u:unconfined_r:unconfined_t:s0-s0:c0.c1023
[root@bogon ~]# id root
uid=0(root) gid=0(root) groups=0(root)
```

## 用户组

- 每个用户都有一个用户组，一个用户可以属于多个用户组，一个用户组可以有多个用户。系统对一个用户组的用户集中管理，赋予用户组的权限可以被该用户获取。用户的权限为所在的所有用户组的权限之和。
- /etc/passwd定义的用户组为基本组，其他的为附加组、/etc/group文件的更新实现对用户组的操作

### groupadd 添加用户组

```shell
[root@bogon ~]# groupadd group1 -p tiger
[root@bogon ~]# cat /etc/group | grep group1
group1:x:1002:
```

### groupdel 删除用户组

- 命令要修改的系统文件包括/ect/group和/ect/shadow。
- 若该群组中仍包括某些用户，则必须先删除这些用户后，方能删除群组。

```shell
[root@bogon ~]# groupadd group1 -p tiger
[root@bogon ~]# useradd user1 -g group1
[root@bogon ~]# groupdel group1
groupdel: cannot remove the primary group of user 'user1'
[root@bogon ~]# userdel -r user1
[root@bogon ~]# groupdel group1
```

### groupmod 修改用户组

```shell
[root@bogon ~]# groupadd group0
[root@bogon ~]# groupmod -g 1033 group0
[root@bogon ~]# groupmod -n group2 group0
[root@bogon ~]# cat /etc/group | grep group2
group2:x:1033:
[root@bogon ~]# cat /etc/group | grep group0
[root@bogon ~]# groupdel group2
```

### groups 查看用户所在用户组

```shell
[root@bogon ~]# groups zjk
zjk : zjk wheel
[root@bogon ~]# groups root
root : root
```

# 系统管理
