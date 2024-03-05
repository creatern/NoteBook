# shell概述

- GNU/Linux shell是一种特殊的交互式工具，为用户提供了启动程序、管理文件系统中的文件以及在Linux系统中的进程的途径。Linux启动时，最先进入内存的是内核，并常驻内存。然后进行系统引导，引导过程中启动所有进程的父进程在后台运行，直到相关的系统资源初始化完毕后，等待用户登录。用户登录时，通过登录进程验证用户的合法性。用户验证通过后根据用户的设置启动相关的shell，以便接收用户输入的命令并返回执行结果。
- shell的核心是命令行提示符（CLI），负责shell的交互部分，运行用户输入文本命令，然后解释命令并在内核中执行。shell包含一组内部命令，可用于完成复制文件、移动文件、重命名文件、显示和终止系统中正在运行的程序这类操作；此外，shell还允许在命令行提示符中输入程序的名称，并由其将程序名称传递给内核以启动程序。也可以将多个shell命令放入文件（shell 脚本）中作为程序执行。

<img src="../../pictures/Linux-user-shell-sys.drawio.svg" width="408"/> 

## shell的类型

<table>
    <tr>
        <td width="15%">默认shell</td>
        <td width="85%">用户登录虚拟控制台终端时默认启动的shell</td>
    </tr>
    <tr>
        <td>默认的系统shell</td>
        <td>用于需要在启动时使用的系统shell脚本</td>
    </tr>
</table>

- bash（Bourne Again Shell，/bin/bash）是所有Linux发行版的默认shell，提供用户与操作系统进行交互操作的接口，提供脚本语言编程环境。即，bash通常作为默认的交互式shell（default interactive shell）也就是登录shell（login shell），只要某个用户登录虚拟控制台终端或在GNU中启动终端仿真器就会启动该shell。

<table>
    <tr>
        <th width="10%">shell</th>
        <th width="90%">描述</th>
    </tr>
    <tr>
        <td>ash</td>
        <td>一种简单的轻量级shell，运行在内存受限环境中，与bash shell完全兼容</td>
    </tr>
    <tr>
        <td>korn</td>
        <td>一种与Bourne shell兼容的编程shell，支持如关联数组和浮点算术等高级编程特性</td>
    </tr>
    <tr>
        <td>tcsh</td>
        <td>一种将C语言中的一些元素引入shell脚本的shell</td>
    </tr>
    <tr>
        <td>zsh</td>
        <td>一种结合了bash、tcsh和korn的特性，同时提供高级编程特性、共享历史文件和主题化提示符的高级shell</td>
    </tr>
    <tr>
        <td>dash</td>
        <td>Debian Almquist Shell，dash比bash小的多，是由bash移植到Debian系列</td>
    </tr>
</table>

- sh是默认的系统shell（default systen shell），用于那些需要在启动时使用的系统shell脚本。

```shell
# sh 通常位于/usr/bin/sh
which sh

# 不同的发行版，sh指向的shell不同。Redhat系使用bash，而Debian系使用dash 
ll /usr/bin/sh
# /usr/bin/sh -> dash*
```

```shell
# 在当前shell进程中查看shell名称的方式
echo $0
```

## shell的父子关系

- 用户登录虚拟控制台终端（Terminal等）时，自动使用并进入默认shell（通常是bash）。此时，输入启动其他shell的命令时（bash、dash等），会启用（进入）指定的shell。此时，默认shell就是这个子shell的父进程（父shell）。
- 创建子shell的代价较高，需要为该子shell创建新的环境。子shell同样可以具有CLI提示符并会等待命令输入。

```
zjk@zjk-laptop:~$ dash
$ bash 
zjk@zjk-laptop:~$ ps -f
UID          PID    PPID  C STIME TTY          TIME CMD
zjk        18945   18919  0 19:37 pts/0    00:00:00 bash
zjk        18951   18945  0 19:37 pts/0    00:00:00 dash
zjk        18952   18951  0 19:37 pts/0    00:00:00 bash
zjk        18958   18952  0 19:37 pts/0    00:00:00 ps -f
zjk@zjk-laptop:~$ exit
exit
$ ps -f
UID          PID    PPID  C STIME TTY          TIME CMD
zjk        18945   18919  0 19:37 pts/0    00:00:00 bash
zjk        18951   18945  0 19:37 pts/0    00:00:00 dash
zjk        18959   18951  0 19:37 pts/0    00:00:00 ps -f
$ exit 
zjk@zjk-laptop:~$ ps -f
UID          PID    PPID  C STIME TTY          TIME CMD
zjk        18945   18919  0 19:37 pts/0    00:00:00 bash
zjk        18960   18945  0 19:37 pts/0    00:00:00 ps -f
```

<img src="../../pictures/Linux-shell的父子关系.drawio.svg" width="870"/> 

```shell
# 查看当前shell的子shell数量
echo $BASH_SUBSHELL
```

### 命令列表与进程列表

<table>
    <tr>
        <td width="12%">进程列表</td>
        <td width="20%">(命令; 命令; 命令)</td>
        <td width="68%">生成一个子shell，并由其按括号内的命令依次执行，执行完成之后，该子shell自动被杀死</td>
    </tr>
    <tr>
        <td>命令列表</td>
        <td>命令; 命令</td>
        <td>依次执行命令</td>
    </tr>
    <tr>
        <td>coproc协程</td>
        <td>{命令; 命令}</td>
        <td>同时处理两件事情，后台生成一个子shell并在该子shell中执行命令</td>
    </tr>
</table>

### 子shell与后台模式

- 将子shell搭配[后台模式](./进程管理.md#后台模式)（`&`或`bg`）或协程（[coproc](./进程管理.md#coproc)）可以发挥出类似多线程的效果。

### 外部命令与内建命令

<table>
    <tr>
        <td width="10%" rowspan="3">外部命令</td>
        <td width="90%">文件系统命令，是存在于bash shell之外的程序，并不属于shell程序的一部分</td>
    </tr>
    <tr>
        <td>外部命令执行时会产生一个子shell（衍生，forking），因此外部命令系统开销较大</td>
    </tr>
    <tr>
        <td>外部命令通常位于/bin、/usr/bin、/usr/sbin</td>
    </tr>
    <tr>
        <td>内建命令</td>
        <td>不需要子进程执行，已和shell编译成一体，不需要借助外部程序文件来执行</td>
    </tr>
</table>

- 对于具有多种实现的命令，如果想使用其外部命令实现，则直接指明对应的文件即可。

#### type 查看命令类型

```shell
# type -a 显示每个命令的两种实现
type -a pwd
#pwd is a shell builtin
#pwd is /usr/bin/pwd
#pwd is /bin/pwd
```

<table><tbody><tr><th>常用类型</th><th>信息说明</th></tr><tr><td>builtin</td><td>内部指令</td></tr><tr><td>file</td><td>文件</td></tr><tr><td>function</td><td>函数</td></tr><tr><td>keyword</td><td>关键字</td></tr><tr><td>alias</td><td>别名</td></tr><tr><td>unfound</td><td>没有找到</td></tr></tbody></table>

```shell
type ls
# ls is aliased to `ls --color=auto'

type mv
# mv is /usr/bin/mv

type source
# source is a shell builtin
```

#### which 查找命令文件

- which：查找命令文件，能够快速搜索二进制程序所对应的位置。

- which只显示文件命令文件。对于有多种方式实现的命令，如果需要指定为外部命令的方式实现，可以通过which找到相应的文件，直接执行该文件即可。

>如果我们既不关心同名文件（find与locate），也不关心命令所对应的源代码和帮助文件（whereis），仅仅是想找到命令本身所在的路径，那么这个which命令就太合适了。

<table><tbody><tr><td>-a</td><td>显示PATH变量中所有匹配的可执行文件</td></tr><tr><td>-n</td><td>设置文件名长度（不含路径）</td></tr><tr><td>-p</td><td>设置文件名长度（含路径）</td></tr><tr><td>-V</td><td>显示版本信息</td></tr><tr><td>-w&nbsp;</td><td>设置输出时栏位的宽度</td></tr><tr><td>--help</td><td>显示帮助信息</td></tr><tr><td>--read-functions</td><td>从标准输入中读取Shell函数定义</td></tr><tr><td>--show-tilde</td><td>使用波浪线代替路径中的家目录</td></tr><tr><td>--skip-dot</td><td>跳过PATH变量中以点号开头的目录</td></tr></tbody></table>

```shell
# 查找多个指定命令文件的位置
which ls who
#/usr/bin/ls
#/usr/bin/who  
```

## 启动文件

- 启动文件（环境文件）：用户登录Linux系统启动bash shell，默认情况下bash会在几个文件（启动文件）中查找命令。
- bash进程的启动文件取决于用户启动bash的方式：

1. 登录时作为默认登录shell。
2. 作为交互式shell，通过生成子shell启动。
3. 作为运行脚本的非交互式shell。

### 登录shell

- bash通常作为登录shell启动，登录shell会从5个不同的启动文件中读取命令：

<table>
    <tr>
        <td width="15%">/etc/profile</td>
        <td width="85%">系统默认的bash shell主启动文件</td>
    </tr>
    <tr>
        <td>$HOME/.bash_profile</td>
        <td></td>
    </tr>
    <tr>
        <td>$HOME/.bashrc</td>
        <td></td>
    </tr>
    <tr>
        <td>$HOME/.bash_login</td>
        <td></td>
    </tr>
    <tr>
        <td>$HOME/.profile</td>
        <td></td>
    </tr>
</table>

#### /etc/profile

- <code><span name="/etc/profile">/etc/profile</span></code>：bash shell默认的主启动文件，只要登录Linux系统，bash就会执行`/etc/profile`文件中的命令。 不同的发行版对该文件的设置也不同。

##### /etc/profile.d

- `/etc/profile.d`：大多数的Linux发行版的/etc/profile文件都使用for语句来迭代/etc/profile.d目录下的所有文件。/etc/profile.d目录为Linux系统提供了一个放置特定应用程序启动文件和管理员自定义启动文件的地方，shell会在用户登录时执行这些文件。

```shell
# /etc/profile文件内的部分for语句
if [ -d /etc/profile.d ]; then
  for i in /etc/profile.d/*.sh; do
    if [ -r $i ]; then
      . $i
    fi
  done
  unset i
fi
```

- 大部分的应用程序会在/etc/profile.d目录中创建两个启动文件：一个供bash shell使用（.sh），一个供C shell使用（.csh）。

```shell
zjk@zjk-laptop:~$ ls /etc/profile.d
01-locale-fix.sh       debuginfod.csh            vte-2.91.sh
apps-bin-path.sh       debuginfod.sh             vte.csh
bash_completion.sh     gnome-session_gnomerc.sh  xdg_dirs_desktop_session.sh
cedilla-portuguese.sh  im-config_wayland.sh
```

- 对于环境变量的持久化等设置，可以在`/etc/profile.d`目录下创建一个`.sh`文件，把所有新的或修改过的全局环境变量的设置都放在该文件中，而保留个人用户永久性bash shell变量的最佳位置是<code><a href="#bashrc">$HOME/.bashrc</a></code>文件。

##### /etc/bash.bashrc

- <span name="bashrc">/etc/bashrc</span>（Ubuntu：/etc/bash.bashrc）：为每一个运行bash shell的用户执行此文件；bash shell被打开时，该文件被读取。在Ubuntu发行版的`/etc/profile`文件中涉及到`/etc/bash.bashrc`文件。

#### $HOME目录下的启动文件

- `$HOME`目录下的启动文件都用于提供用户专属的启动文件来定义该用户所用到的环境变量，每个用户都可以对其编辑并添加自己的环境变量，其中的环境变量会在每次启动bash shell会话时生效。大部分的Linux发行版只使用以下四种启动文件的其中一种：

<table>
    <tr>
        <td width="15%">$HOME/.bash_profile</td>
        <td width="85%"></td>
    </tr>
    <tr>
        <td>$HOME/.bashrc</td>
        <td></td>
    </tr>
    <tr>
        <td>$HOME/.bash_login</td>
        <td></td>
    </tr>
    <tr>
        <td>$HOME/.profile</td>
        <td></td>
    </tr>
</table>

- shell会按照如下顺序执行第一个被找到的文件，余下的则被忽略：`$HOME/.bash_profile`、`$HOME/.bash_login`、`$HOME/.profile`。而`$HOME/.bashrc`通常通过其他文件运行。

##### $HOME/.bashrc 个性化bash

- `.bashrc`：存储用户的个性化 Bash shell 设置，这些设置将在每次用户打开一个新的交互式 Shell 时自动加载执行。在该文件中，用户可以定义各种配置选项。每当用户登录到系统并在终端中打开一个新的 Bash shell 时，Bash 会读取并执行 `.bashrc` 文件中的命令。

###### \$HOME/.bashrc 

- \~/.bashrc：专属于个人bash shell的信息；用户登录以及每次打开一个新的shell时，执行这个文件；在这个文件里可以自定义用户专属的个人信息。

### 交互式shell

- 作为交换式shell启动的bash并不处理/etc/profile文件，而是只检查`$HOME/.bashrc`文件。此时，`$HOME/.bashrc`文件会做两件事：（1）检查/etc目录下的通用bashrc文件；（2）为用户提供一个定制自己的命令别名和脚本函数。

### 非交互式shell

- 系统执行shell脚本时使用的shell，没有CLI。

- `BASH_ENV`环境变量：bash shell设置了该环境变量，当shell启动一个非交换式shell进程时，会检查这个`BASH_ENV`环境变量以查看要执行的启动文件名。如果该环境变量有指定的文件，则shell会执行该文件里的命令，通常包括shell脚本变量设置。

```shell
# 查看BASH_ENV变量设置的启动文件，没有则空
echo $BASH_ENV
```

- 子shell会继承父shell的导出变量（不包括局部变量等），如果父shell是登录shell，在`/etc/profile`、`/etc/profile.d/*`、`$HOME/.bashrc`文件中设置并导出了变量，那么执行脚本的子shell就能继承这些变量。

# shell相关命令

## alias 命令别名

- alias：设置指令的别名，只在当前shell起作用，未更改[/etc/bashrc](#bashrc)；对于个人专用的命令别名，应该在[\~/.bashrc](#bashrc)设置。

```shell
# 查看已经设置的别名
alias
alias -p

# 检查别名是否已被使用
type 别名

# 设置/更改别名，多条命令之间使用分号分隔
alias 别名='命令1;命令2'

# 撤销别名，只在当前shell起作用，未更改/etc/bashrc
unalias 别名
```

## history 历史命令

- `history`：查看历史命令记录。bash shell会跟踪最近使用过的命令。
- `$HISTSIZE`：最大保存的历史命令条数（默认1000条）。
- `~/.bash_history`：历史命令文件；在CLI会话期间，bash命令的历史记录会先放在内存中，等到shell退出时才被写入到历史文件。

<table><tbody><tr><td>-a</td><td>保存命令记录</td></tr><tr><td>-c</td><td>清空命令记录</td></tr><tr><td>-d</td><td>删除指定序号的命令记录</td></tr><tr><td>-n</td><td>读取命令记录</td></tr><tr><td>-r</td><td>读取命令记录到缓冲区</td></tr><tr><td>-s</td><td>添加命令记录到缓冲区</td></tr><tr><td>-w</td><td>将缓冲区信息写入到历史文件</td></tr></tbody></table>

```shell
# 查看指定的历史命令记录
history [n]
上下光标

# 清空当前缓冲区中的历史命令（并未对历史命令文件修改）
history -c

# 将历史命令文件中的命令读入当前历史命令缓冲区，否则只有在第一个终端被打开时才会读取历史命令文件
history -r

# 将历史命令缓冲区中的命令写入历史命令文件，同时打开多个终端时，其他终端的历史记录不会自动更新
history -a

# 将当前历史命令缓冲区中的命令写入历史命令文件，同时打开多个终端时，其他终端的历史记录也会自动更新
history -w
```

### \!\! 执行上次命令

```shell
# 执行上次命令
!!

# 执行指定序号的历史命令
!{n}

# 执行以指定字符开头的历史命令
!{prex}
```

## timeout 限时任务

- timeout：运行指定命令，若在指定时间后，该命令仍然在运行，则结束该命令的执行，并返回退出状态码`124`（\$? 获取）（未超时则正常返回）；以执行下一条命令（如果存在）。

<table><tbody><tr><td>-s&lt;信号&gt;</td><td>指定在超时时发送的信号，信号可以是类似“HUP”的信号名或是信号数</td></tr><tr><td>-k&lt;时间&gt;</td><td>达到给定的时间限制后会强制结束</td></tr><tr><td>-- -foreground</td><td>在前台运行</td></tr></tbody></table>

```shell
# 5秒钟后发送SIGKILL信号给ping命令以终止该命令
timeout -s SIGKILL 5s top

# 运行命令一分钟，如果命令没有结束，将在10秒后终止该命令
timeout -k 10s 1m top
```

## \| 管道符

- \|（管道符）：连接两个命令，将一个程序/命令的输出作为另一个程序/命令的参数输入。一般为输入和输出的结合，一个进程向管道的一端发送数据，而另一个进程从该管道的另一端读取数据。

```shell
ll /etc | grep "*.config"
```

## 标准输入与输出、重定向操作符

- 执行一个Shell命令通常会自动打开3个标准文件：标准输入文件stdin、标准输出文件stout、标准错误输出文件stderr；进程从标准输入文件得到输入数据，将正常输出数据输出到标准输出文件，而错误信息则打印到标准错误文件。

<img src="/home/zjk/Desktop/note-book/pictures/153845716239573.png" width="369"/> 

1. 如果给定的文件不止一个，则在显示的每个文件前面加一个文件名标题
2. 若不指定任何文件名称/给予的文件名为"\-"，则命令从标准输入设备读取数据

- 重定向操作符：把命令/可执行程序的标准输入/输出重定向到指定的文件

| 操作符 | 说明                                                         |
| ------ | ------------------------------------------------------------ |
| \<     | 输入重定向                                                   |
| \<\<   | 文档的重定向                                                 |
| \>     | 输出重定向；若\>后面的文件不存在，则创建该文件；若存在，则将内容覆盖到该文件。 |
| \>\>   | 追加输出重定向；若\>\>后面的文件不存在，则创建该文件；若存在，则将内容追加到该文件。 |
| 2\>    | 错误输出重定向<br />若`2>`后面的文件不存在，则创建该文件；若存在，则将内容覆盖到该文件。<br />如果有错误信息，则不会在屏幕（标准输出文件）输出，而会保存在指定的文件；即使没有错误信息也会创建/覆盖。 |
| 2\>\>  | 追加错误输出重定向<br />若`2>>`后面的文件不存在，则创建该文件；若存在，则将内容追加到该文件。<br />如果有错误信息，则不会在屏幕（标准输出文件）输出，而会保存在指定的文件中；即使没有错误信息也会创建/追加。 |

```shell
# 重定向操作符可以混合使用，如将标准输出和标准错误输出重定向到同一个文件
ls >myOutAndErr.txt 2>&
```

## date 日期时间

```shell
date "+%Y-%m-%d %H:%M:%S"
# 2023-12-31 10:24:53
```

## read 读取变量值

- read：该命令可以一次读取多个变量的值，变量和输入的值都需要使用空格隔开；如果没有指定变量名，读取的数据将被自动赋值给特定的变量`REPLY`

```shell
#!/bin/bash

read -t 10 -p "请在10秒内输入: " x
echo "输出：$x"
```

# shell语法规范

## 基础规范

### 脚本规范

```shell
#!/bin/bash
# 解释器

# 注释 <<关键字
<<END
 注释
END

# 执行的命令
echo "Hello World"
```

### 单引号、双引号与反引号

| 引号      | 说明                       |
| --------- | -------------------------- |
| 单引号 '  | 将特殊符号变成普通符号     |
| 双引号 "  | 保持特殊符号的特殊使用     |
| 反引号 \` | 使用命令的输出结果替换命令 |

```shell
echo "current position is `pwd`"
# current position is /root/Test

echo 'current position is `pwd`'
# current position is `pwd`
```

## 运算符

### 算术运算式

```shell
$((运算式)) 

$[运算式]
```

```shell
s=(1+2)     # 1+2
s1=$((1+2)) # 3
s2=$[1+2]   # 3
```

#### bc 任意精度运算

- bash内置对整数四则运算的支持，但并不支持浮点运算，需要借助bc命令。

```shell
echo $[ 9.9 * 9 ]
#-bash: 9.9 * 9 : syntax error: invalid arithmetic operator (error token is ".9 * 9 ")

# 算术操作高级运算bc命令它可以执行浮点运算和一些高级函数：
echo "1.212*3" | bc 
3.636
# 设定小数精度（数值范围）
echo "scale=2;3/8" | bc # 参数scale=2是将bc输出结果的小数位设置为2位。
0.37

# 进制转换
abc=192
echo "obase=2;$abc" | bc
#执行结果为：11000000，这是用bc将十进制转换成二进制。
abc=11000000
echo "obase=10;ibase=2;$abc" | bc
#执行结果为：192，这是用bc将二进制转换为十进制。

# 计算平方和平方根：
echo "10^10" | bc
echo "sqrt(100)" | bc
```

### 条件判断

#### <code>[ 条件表达式 ]</code>

```shell
test 条件表达式

[条件表达式]
# [非空] true
# [] false
```

| 整数比较 | 说明 | 整数比较 | 说明     |
| -------- | ---- | -------- | -------- |
| -eq      | 等于 | -ne      | 不等于   |
| -lt      | 小于 | -le      | 小于等于 |
| -gt      | 大于 | -ge      | 大于等于 |

| 文件权限 | 说明           | 文件类型 | 说明                 |
| -------- | -------------- | -------- | -------------------- |
| -r       | 可读           | -e       | 文件存在             |
| -w       | 可写           | -f       | 文件存在且是常规文件 |
| -x       | 可执行         | -d       | 文件存在且是目录     |
| 以上     | 也判断文件存在 | -s       | 文件存在且非空       |

| 逻辑 | 说明 |
| ---- | ---- |
| !    | 取反 |
| -a   | \&\& |
| -o   | \|\| |

- -z 、-n 判断字符串是否为空：最好使用"$变量"的方式判断（空格也被看作不为空）；也可以通过`[ ! $1 ]`的方式判断空，若空则true

#### \[\[\]\]

| 判断符         | \[\[\]\]                               | \[\]               |
| -------------- | -------------------------------------- | ------------------ |
| \&\&<br />\|\| | 支持                                   | 不支持             |
| ==             | 模式匹配<br />通配符`*、?、[..]`       | 匹配字符串是否相同 |
| 正则           | 支持=\~                                | 不支持             |
| 运算           | 允许使用\(\)                           | 仅支持部分         |
| \<<br />\>     | 排序操作<br />（本地的locale语言顺序） |                    |

#### test

- test：检查条件是否成立，能够对数值、字符和文件进行条件测试

## 流程控制

### 判断

#### if

```shell
if [ 条件判断式 ]; then
	程序
fi

if [ 条件判断式 ] # if的条件判断式与[]之间必须留有空格
then
	程序
fi
```

```shell
if [ 条件判断式 ]
then
	程序
elif[ 条件判断式 ]
then
	程序
else
	程序
fi
```

```shell
#!/bin/bash

if [ $1 -eq $2 ]
then
        echo "$1等于$2"
elif [ $1 -lt $2 ]
then
        echo "$1小于$2"
else
        echo "$1大于$2"
fi
```

#### case

```shell
case $变量名 in
"值1") # 相当于case
	若变量的值等于值1，则执行该程序
;; # 相当于break
"值2")
	若变量的值等于值2，则执行该程序
;;
	..其他分支..
*) # 相当于default
	若变量的值都不是以上的值，则执行该程序，相当于default
;;
esac
```

```shell
#!/bin/bash

case $1 in
"1")
    echo "选项1"
;;
"2")
    echo "选项2"
;;
*)
    echo "其他选项"
;;
esac
```

### 循环

#### for

```shell
for (( 初始值; 循环控制条件; 变量变化 ))
do
	程序
done
```

```shell
for 变量 in 值1 值2 值3...
do
	程序
done
```

```shell
#!/bin/bash

for ((i=0;i<10;i++))
do
    echo $i
done

for i in 第一名 第二名 第三名
do 
   echo $i 
done 
```

#### while

```shell
while [ 条件判断式 ]
do
	程序
done
```

```shell
#!/bin/bash

i=0

while [ $i -lt 3 ]
do
    i=$[ $i+1 ]
    echo $i
done
```

#### until

```shell
until [ condition ]
do
    程序段落
done
```

## 自定义函数

| 函数类型   | 说明                    |
| ---------- | ----------------------- |
| 系统函数   | Linux的命令，如ls、mv等 |
| 自定义函数 | 由用户自定义编程        |

```shell
[ function ] 函数名[()]
{
    执行语句;
    [return int;]
}
```

1. 必须在调用函数地方之前，先声明函数，shell 脚本是逐行运行。
2. 函数返回值只能通过`$?`系统变量获得。return语句设置返回值，return关键字后跟数值n（0\~255）；若没有return语句，则以最后一条命令运行结果，作为返回值。

```shell
#!/bin/bash

function sum()
{
    sumValue=$[ $1 + $2 ]
    echo "sum=$sumValue"
}

avg()
{
    echo "avg=$[ ($1 + $2) / 2 ]"
}

function minus
{
    echo "minus=$[ $1 - $2 ]"
}

read -t 10 -p "请输入值1: " num1;
read -t 10 -p "请输入值2：" num2;

sum $num1 $num2;
avg $num1 $num2;
minus $num1 $num2;
```
