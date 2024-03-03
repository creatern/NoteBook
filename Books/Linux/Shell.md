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
- 创建子shell的代价较高，需要为该子shell创建新的环境。子shell同样具有CLI提示符并会等待命令输入。

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

##### /etc/bash.bashrc

- <span name="bashrc">/etc/bashrc</span>（Ubuntu：/etc/bash.bashrc）：为每一个运行bash shell的用户执行此文件；bash shell被打开时，该文件被读取。在Ubuntu发行版的/etc/profile文件中涉及到/etc/bash.bashrc文件。

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

###### /etc/bashrc 

- <span name="bashrc">/etc/bashrc</span>（Ubuntu：/etc/bash.bashrc）：为每一个运行bash shell的用户执行此文件；bash shell被打开时，该文件被读取。

###### \$HOME/.bashrc 

- \~/.bashrc：专属于个人bash shell的信息；用户登录以及每次打开一个新的shell时，执行这个文件；在这个文件里可以自定义用户专属的个人信息。

# Linux环境变量

- bash shell使用环境变量来存储shell会话和工作环境的相关信息。环境变量允许在内存中存储数据，以便shell中运行的程序或脚本能够轻松访问到这些数据。

<table>
    <tr>
        <th width="15%">变量类型</th>
        <th width="10%">命名规范</th>
        <th width="50%">可见性</th>
        <th width="25%">声明与定义</th>
    </tr>
    <tr>
        <td>全局环境变量</td>
        <td>大写</td>
        <td>对于shell会话和其所有生成的子shell都是可见的</td>
        <td>export</td>
    </tr>
    <tr>
        <td>局部环境变量</td>
        <td>小写</td>
        <td>仅创建该局部变量的当前shell可见</td>
        <td>大部分方式</td>
    </tr>
</table>

- 局部变量仅在创建其的shell进程中可见。如果进入到其子shell或父shell，则该局部变量不可见；但如果回到创建其的shell中，则可见。

```shell
zjk@zjk-laptop:~$ ihello="hello linux"
zjk@zjk-laptop:~$ echo $ihello
hello linux
zjk@zjk-laptop:~$ bash
zjk@zjk-laptop:~$ echo $ihello

zjk@zjk-laptop:~$ exit
exit
zjk@zjk-laptop:~$ echo $ihello
hello linux
```

## 查看环境变量

### env

- env：显示系统中已存在的环境变量；如果使用env命令在新环境中执行指令时，会因为没有定义环境变量“PATH”（/etc/profile）而提示错误信息，此时，用户可以重新定义一个新的“PATH”或者使用绝对路径。
- env不对输出结果排序，不输出局部变量和用户定义变量；查看全局变量时，使用env、printenv。

### printenv

- printenv：不对输出结果排序，不输出局部变量和用户定义变量；查看个别环境变量时，使用printenv而不是env。

### [set](#set)

- set：显示为某个特定进程设置的所有环境变量，包括局部变量、全局变量、用户定义变量；对输出的结果按照字母顺序来排序。

## \$ 变量符

- `$`表示对指定变量的引用。

<table>
    <tr>
        <th width="10%">特殊变量</th>
        <th width="90%">说明</th>
    </tr>
    <tr>
        <td>$n</td>
        <td>$0代表该脚本文件名；$1~$9代表该命令输入的第1~9个参数<br/>序号为10以上的参数用大括号包含，如${10}</td>
    </tr>
    <tr>
        <td>$#</td>
        <td>获取所有输入参数个数（常用于循环），判断参数的个数是否正确以及加强脚本的健壮性</td>
    </tr>
    <tr>
        <td>$*</td>
        <td>命令行中所有的参数，把所有的参数看成一个整体；被双引号“”包含时，$*以“$1 $2 …$n”的形式输出所有参数</td>
    </tr>
    <tr>
        <td>$@</td>
        <td>命令行中所有的参数，把每个参数区分对待；被双引号“”包含时，$@以“$1” “$2”...“$n”的形式输出所有参数</td>
    </tr>
    <tr>
        <td>$?</td>
        <td>最后一次执行的命令的返回状态<br />若该变量的值为0，则上一个命令执行正确；若该变量的值非0，则上一个命令执行错误</td>
    </tr>
</table>

<img src="../../pictures/20231219083240.png" width="800"/> 

## 变量定义与赋值

- 用户自定义变量：变量名区分大小写，bash shell惯例所有的环境变量均使用大写字母，而用户自定义变量或局部变量则使用小写字母命名。

1. 变量名称可以由字母、数字和下划线组成；但是不能以数字开头，环境变量名建议大写
2. 变量定义和赋值时，等号两侧不能有空格
3. 在 bash 中，变量默认类型都是字符串类型，无法直接进行数值运算
4. 变量的值如果有空格，需要使用双引号或单引号括起来

### declare 声明与显示

- declare：声明和显示已存在的shell变量

```shell
变量名 = 变量值

declare 变量名 = 变量值
```

### set 赋值与显示

- <span name="set">set</span>：显示系统中已经存在的shell变量、设置shell变量的新变量值；不能定义新的shell变量。

### readonly 只读变量

- readonly：定义只读shell变量和shell函数。

### export 全局环境变量

- export：将shell变量、函数输出为全局环境变量。

1. 在子shell中修改（或删除）全局变量，不会影响到父shell中该全局变量的值，只会影响该子shell以及其创建的子shell的全局变量的值。
2. 一个变量创建时，它不会自动地为在它之后创建的shell进程所知；而export命令可以向后面的shell传递变量的值。即一个shell脚本被调用并执行时，它不会自动得到原为脚本（调用者）定义的变量的访问权，除非这些变量已经被显式地设置为可用。

## unset 删除环境变量

- unset：删除已定义的shell变量（包括环境变量）、shell函数；不能删除具有只读属性的shell变量和环境变量。

##  系统环境变量

- 在默认情况下，bash shell会用一些特定的环境变量（已经设置好的）来定义系统环境。

| 系统变量 | 说明                                                         |
| :------- | :----------------------------------------------------------- |
| PATH     | 执行文件查找的路径（/etc/profile 路径文件）；文件查找的顺序与PATH的变量的顺序有关<br />目录与目录中间以:冒号隔开 |
| HOME     | 当前用户主目录                                               |
| USER     | 当前用户                                                     |
| HISTSIZE | 代表可以容纳多少条历史命令                                   |
| LOGNAME  | 当前用户的登录名                                             |
| HOSTNAME | 指主机的名称                                                 |
| SHELL    | 当前用户Shell类型                                            |
| LANG     | 当前的语系类型                                               |
| MAIL     | 当前用户的邮件存放目录                                       |
| PS1      | 基本提示符                                                   |
| RANDOM   | 随机数的变量                                                 |

### PATH环境变量

- PATH环境变量定义了查找命令和程序的目录。当用户在shell CLI中输入一个外部命令时，shell必须搜索系统，从中找到对应的程序。如果命令或程序所在的位置没有包括在PATH变量中，那么在不使用绝对路径的情况小，shell是无法找到的“command not found”。
- PATH环境变量内的目录以冒号（`:`）分隔，shell会在其中查找命令和程序。PATH环境变量无须从头定义，而是在之后追加`:`和需要加入的目录即可。

```shell
# 查看PATH环境变量
zjk@zjk-laptop:~$ echo $PATH
/home/zjk/.local/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/snap/bin:/snap/bin:/usr/software/idea/bin:/usr/lib/jvm/jdk-17.0.9/bin:/opt/apache-maven-3.9.3/bin:/opt/gradle-8.2.1/bin:/opt/groovy-4.0.13/bin:/opt/node20.10.0/bin

# 临时定义PATH
PATH=$PATH:/opt/mysoft/bin

# 在/etc/profile中永久定义PATH
vim /etc/profile
```

## 语系变量 locale、localectl

| 命令      | 说明                           |
| --------- | ------------------------------ |
| locale    | 当前软件的语系，不修改配置文件 |
| localectl | 当前系统的语系，修改配置文件   |

```shell
# 设置当前系统语系 同时修改配置文件
localectl set-locale LANG=en_US.UTF-8 
```

- `LANG`与`LC_ALL`：设置语系变量时，一般建议更改这两个变量，其他语系变量都会替换成`LANG`与`LC_ALL`这两个变量的值。`LANG`直接用=号赋值即可；而`LC_ALL`需要使用export设置。

```shell
LANG=en_US.UTF-8
export LC_ALL=en_US.UTF-8
```

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

# shell脚本

## 脚本执行

### 直接执行

- 需要可执行权限（x），在当前shell内开启一个子shell执行脚本，脚本结束时关闭子shell并回到父shell

```shell
./test.sh
```

### bash/sh shell命令语言解释器

- sh（shell）：sh是bash的别名命令，不需要执行权限，在当前shell中开启一个子shell执行脚本，脚本结束时关闭子shell并回到父shell

```shell
bash test.sh

sh test.sh
```

<table><tbody><tr><td>-c</td><td>从字符串中读取命令</td></tr><tr><td>-i </td><td>实现脚本交互 </td></tr><tr><td>-n </td><td>进行语法检查 </td></tr><tr><td>-v</td><td>显示执行过程详细信息</td></tr><tr><td>-x </td><td>实现逐条语句的跟踪 </td></tr><tr><td>--help</td><td>显示帮助信息</td></tr><tr><td>--version</td><td>显示版本信息</td></tr></tbody></table>

### . source 从指定文件中读取和执行命令

- source（. 点命令）：用于从指定文件（不需要执行权限）中读取和执行命令，通常用于被修改过的文件，使之新参数能够立即生效，而不必重启整台服务器。使脚本内容在当前shell里执行，而无需打开子shell

```shell
. test.sh

source test.sh
```

## 开机自启动的shell脚本

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

# 文本处理

## echo 格式化输出

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

## sed 流编辑器

- sed（stream editor）：批量编辑，执行基本文本输入流（来自管道的文件或输入）上的转换，只对输入进行一次传递。
- 处理时，把当前处理的行存储在临时缓冲区（模式空间 pattern space），接着用sed命令处理缓冲区中的内容，处理完成后，把缓冲区的内容送往屏幕；接着处理下一行，这样不断重复，直到文件末尾；除非使用重定向存储输出，否则文件内容并不改变。

```shell
sed [options] 'command' file(s)
sed [options] -f scriptfile file(s)
```

<table><tbody><tr><td>-e</td><td>使用指定脚本处理输入的文本文件</td><td rowspan="4">&nbsp;</td><td>-n</td><td>仅显示脚本处理后的结果</td></tr><tr><td>-f</td><td>使用指定脚本文件处理输入的文本文件</td><td>-r</td><td>支持扩展正则表达式</td></tr><tr><td>-h</td><td>显示帮助信息</td><td>-V</td><td>显示版本信息</td></tr><tr><td>-i</td><td>直接修改文件内容，而不输出到终端</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table>

## sort 文件数据排序

- sort命令默认会将文本中的数据当成字符来排序按照会话指定的默认语言的升序排序顺序输出，包括数字和时间日期等，而不是按照相应的规则来排序。

| 命令                                       | 说明                                                         |
| ------------------------------------------ | ------------------------------------------------------------ |
| sort -n                                    | 将文本识别为数字来排序                                       |
| sort -M                                    | 将文本识别为Mar形式的月份来排序                              |
| sort -t '分隔字符' -k 指定排序的字符段位置 | -t对每行的字符段进行分隔，然后-k选择每行分隔的其中一段字符进行排序 |
| sort -r                                    | 将排序结果降序输出                                           |

```shell
# sort -t '字符' -k  文件
# 将/etc/passwd按uid来排序
[root@bogon ~]# sort -t ':' -k 3  /etc/passwd | head -n 5
root:x:0:0:root:/root:/bin/bash
operator:x:11:0:operator:/root:/sbin/nologin
bin:x:1:1:bin:/bin:/sbin/nologin
games:x:12:100:games:/usr/games:/sbin/nologin
ftp:x:14:50:FTP User:/var/ftp:/sbin/nologin
```

## grep 文本过滤

- grep：默认区分大小写、支持正则。
- 如果把grep命令当作标准搜索命令，那么egrep则是扩展搜索命令，等价于grep -E命令，支持扩展的正则表达式；而fgrep则是快速搜索命令，等价于grep -F命令，不支持正则表达式，直接按照字符串内容进行匹配。

```shell
grep [选项] {"查找目标文本" | 查找目标文本} {被过滤文本}
```

<table><tbody><tr><td>-b</td><td>显示匹配行距文件头部的偏移量</td><td rowspan="6">&nbsp;</td><td>-o</td><td>显示匹配词距文件头部的偏移量</td></tr><tr><td>-c</td><td>只显示匹配的行数</td><td>-q</td><td>静默执行模式</td></tr><tr><td>-E</td><td>支持扩展正则表达式</td><td>-r</td><td>递归搜索模式</td></tr><tr><td>-F</td><td>匹配固定字符串的内容</td><td>-s</td><td>不显示没有匹配文本的错误信息</td></tr><tr><td>-h</td><td>搜索多文件时不显示文件名</td><td>-v</td><td>显示不包含匹配文本的所有行</td></tr><tr><td>-i</td><td>忽略关键词大小写</td><td>-w</td><td>精准匹配整词</td></tr><tr><td>-l</td><td>只显示符合匹配条件的文件名</td><td>&nbsp;</td><td>-x</td><td>精准匹配整行</td></tr><tr><td>-n</td><td>显示所有匹配行及其行号</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table>

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

## yes

- `yes`：重在输出指定的字符串（默认有换行），直到yes进程被杀死

```shell
# 创建test.txt文件，并使用yes输出hello来填充，直到文件到达100MB大小
touch test.txt; yes "hello" | head -c 100MB > test.txt
```

# shell script语法规范

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
