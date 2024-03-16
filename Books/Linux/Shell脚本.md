# Shell脚本概述

## Shell脚本规范

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

- 在创建shell脚本时，必须在文件的第一行指定要使用的shell。shell脚本中的第一行的`#`并不作为注释，是个例外。

```shell
#!/bin/bash
```

1. 除了第一行的`#`外，其余行如果以`#`开头，则都会用作注释行标识。
2. shell会根据命令在文件中出现的顺序进行执行。命令可以通过“换行”来结束，也可以通过“;”来结束。
3. shell脚本需要执行权限（<code>chmod +x</code>）。

## 单引号、双引号与反引号

<table>
    <tr>
        <td width="15%"><span name="单引号">单引号 '</span></td>
        <td width="85%">将字符串的特殊符号变成普通符号</td>
    </tr>
    <tr>
        <td>双引号 "</td>
        <td>保持字符串的特殊符号的特殊使用</td>
    </tr>
    <tr>
        <td>反引号 `</td>
        <td><a href="#命令替换">命令替换</a>，在字符串中使用命令的输出结果替换命令</td>
    </tr>
</table>

```shell
echo "current position is `pwd`"
# current position is /root/Test

echo 'current position is `pwd`'
# current position is `pwd`
```

## 脚本执行

<table>
    <tr>
        <th width="25%">脚本执行方式</th>
        <th width="25%">路径执行</th>
        <th width="25%">shell解释器</th>
        <th width="25%">source或 .</th>
    </tr>
    <tr>
        <th>是否需要可执行权限（x）</th>
        <td>是</td>
        <td>否</td>
        <td>否</td>
    </tr>
    <tr>
        <th>是否创建子shell来执行</th>
        <td>是</td>
        <td>是</td>
        <td>否，直接在当前shell执行</td>
    </tr>
</table>

### 直接执行（路径执行）

- 直接执行shell脚本文件需要可执行权限（`chmod +x`），该方式会在当前shell内开启一个子shell以执行脚本，脚本结束时关闭子shell并回到当前shell（父shell）。

```shell
./test.sh
```

### bash/sh shell命令语言解释器

- shell命令语言解释器：在大多数的Linux发行版中，sh是bash的别名命令，通过该方式执行shell脚本文件不需要执行权限。会在当前shell中开启一个子shell执行脚本，脚本结束时关闭子shell并回到当前shell（父shell）。

```shell
bash test.sh

sh test.sh
```

<table><tbody><tr><td>-c</td><td>从字符串中读取命令</td></tr><tr><td>-i </td><td>实现脚本交互 </td></tr><tr><td>-n </td><td>进行语法检查 </td></tr><tr><td>-v</td><td>显示执行过程详细信息</td></tr><tr><td>-x </td><td>实现逐条语句的跟踪 </td></tr><tr><td>--help</td><td>显示帮助信息</td></tr><tr><td>--version</td><td>显示版本信息</td></tr></tbody></table>

### . source 从指定文件中读取和执行命令

- source（`.` 点命令）：用于从指定文件（不需要执行权限）中读取和执行命令，通常用于被修改过的文件，使之新参数能够立即生效，而不必重启整台服务器。source使脚本内容在当前shell里执行，而无需打开子shell。

```shell
. test.sh

source test.sh
```

### Service 作为服务执行

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

# [用户自定义变量](./环境变量.md#用户自定义变量)

# Shell命令补充

## <span name="命令替换">命令替换</span>

<table>
    <tr>
        <th>命令替换符</th>
        <th>意义</th>
    </tr>
    <tr>
        <td width="15%">反引号 <code>``</code></td>
        <td rowspan="2" width="85%">shell会执行命令替换符内的命令，从而允许将shell命令的输出赋给变量</td>
    </tr>
    <tr>
        <td><code>$()</code></td>
    </tr>
</table>

- 命令替换<b>会创建出子shell</b>（由脚本shell所生成的一个独立的shell）来运行指定命令，在该子shell中运行的命令无法使用脚本中的变量。
- <a href="#单引号">单引号</a>内的所有特殊符号都会被无效化，因此，命令替换符在单引号字符串内部也失效。


```shell
echo "My name is `whoami`"
echo "My name is $(whoami)"
# My name is zjk

echo 'My name is $(whoami)'
# My name is $(whoami)
```

## 重定向

### 标准输入/输出/错误输出

- 执行一个Shell命令通常会自动打开3个标准文件：标准输入文件stdin、标准输出文件stout、标准错误输出文件stderr。进程从标准输入文件得到输入数据，将正常输出数据输出到标准输出文件，而错误信息则打印到标准错误文件。

<img src="../../pictures/153845716239573.png" width="369"/> 

1. 如果给定的文件不止一个，则在显示的每个文件前面加一个文件名标题。
2. 若不指定任何文件名称/给予的文件名为"\-"，则命令从标准输入设备读取数据。

### 重定向操作符

- 重定向操作符：把命令/可执行程序的标准输入/输出重定向到指定的文件或命令的参数输入。

<table>
	<thead>
		<tr>
			<th width="10%">操作符</th>
			<th width="20%">意义</th>
            <th width="70%">描述</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>&lt;</td>
            <td>输入重定向</td>
            <td></td>
		</tr>
		<tr>
			<td>&lt;&lt;</td>
			<td>文档的重定向<br/>内联输入重定向</td>
            <td>内联输入重定向无需使用文件来进行重定向，只需要在命令行中指定用于输入重定向的数据即可（需要<b>文本标记</b>来划分起始）</td>
		</tr>
		<tr>
			<td>&gt;</td>
			<td>输出重定向</td>
            <td>若&gt;后面的文件不存在，则创建该文件；若存在，则将内容覆盖到该文件</td>
		</tr>
		<tr>
			<td>&gt;&gt;</td>
			<td>追加输出重定向</td>
            <td>若&gt;&gt;后面的文件不存在，则创建该文件；若存在，则将内容追加到该文件</td>
		</tr>
		<tr>
			<td rowspan="2">2&gt;</td>
			<td rowspan="2">错误输出重定向</td>
            <td>若<code>2&gt;</code>后面的文件不存在，则创建该文件；若存在，则将内容覆盖到该文件</td>
		</tr>
        <tr>
            <td>如果有错误信息，则不会在屏幕（标准输出文件）输出，而会保存在指定的文件；即使没有错误信息也会创建/覆盖</td>
        </tr>
		<tr>
			<td rowspan="2">2&gt;&gt;</td>
			<td rowspan="2">追加错误输出重定向</td>
            <td>若<code>2&gt;&gt;</code>后面的文件不存在，则创建该文件；若存在，则将内容追加到该文件</td>
		</tr>
        <tr>
            <td>如果有错误信息，则不会在屏幕（标准输出文件）输出，而会保存在指定的文件中；即使没有错误信息也会创建/追加</td>
        </tr>
	</tbody>
</table>

- `&`用于表示该行命令中上一个重定向操作符重定向到的命令或文件。


```shell
# 重定向操作符可以混合使用，如将标准输出和标准错误输出重定向到同一个文件
ls >myOutAndErr.txt 2>&

# 通过输入重定向搭配wc统计文本
wc < mylog.txt
```

#### &gt;&gt; 内联输入重定向 与 文本标记符

- 内联输入重定向（<code>&gt;&gt;</code>）需要搭配文本标记符号使用，文本标记符可以是任意字符或字符串，用于标记多行文本的开头和结束。

```shell
# 内联输入重定向
$ wc << EOF
> this is title
> this is context
> this is end
> EOF
      3       9      42
```

### \| 管道符

- \|（管道符）：连接两个命令（管道连接，piping），将一个程序/命令的输出作为另一个程序/命令的参数输入。一般为输入和输出的结合，一个进程向管道的一端发送数据，而另一个进程从该管道的另一端读取数据。
- 由管道符连接的两个命令并不是依次执行，而是由Linux系统同时运行这两个命令，在系统内部将二者连接起来（实时化）。当第一个命令产生输出时，它会被立即传给第二个命令。此处的数据传输不会用到任何文件或缓冲区。
- 管道符可以串联的命令数量没有限制，可以持续地将命令输出通过管道传给其他命令来细化操作。

```shell
# 列出/etc下匹配模式"*.config"的文件
ll /etc | grep "*.config"

# 分页查看排序后的已安装的软件包列表
rpm -qa | sort | more
```

## 数学运算

### expr 表达式计算工具

- expr命令能够识别少量算术运算符和字符串运算符，极其不推荐使用。部分expr的表达式可能会和shell本身的符号定义冲突，有的需要进行转义。
- 为了兼容Bourne shell，bash shell保留了expr命令。

```shell
# Shell将*误解为通配符
expr 5 * 2
# expr: syntax error: unexpected argument ‘test01.sh’

# 防止Shell将*误解为通配符
expr 5 \* 2
# 10
```

<table>
    <thead>
        <tr>
            <th width="30%">Expression</th>
            <th width="70%">Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>ARG1 | ARG2</td>
            <td>Returns ARG1 if it is neither null nor 0, otherwise returns ARG2.</td>
        </tr>
        <tr>
            <td>ARG1 &amp; ARG2</td>
            <td>Returns ARG1 if neither argument is null or 0, otherwise returns 0.</td>
        </tr>
        <tr>
            <td>ARG1 &gt; ARG2</td>
            <td>Tests if ARG1 is less than ARG2.</td>
        </tr>
        <tr>
            <td>ARG1 &gt;= ARG2</td>
            <td>Tests if ARG1 is less than or equal to ARG2.</td>
        </tr>
        <tr>
            <td>ARG1 = ARG2</td>
            <td>Tests if ARG1 is equal to ARG2.</td>
        </tr>
        <tr>
            <td>ARG1 != ARG2</td>
            <td>Tests if ARG1 is unequal to ARG2.</td>
        </tr>
        <tr>
            <td>ARG1 &lt;= ARG2</td>
            <td>Tests if ARG1 is greater than or equal to ARG2.</td>
        </tr>
        <tr>
            <td>ARG1 &lt; ARG2</td>
            <td>Tests if ARG1 is greater than ARG2.</td>
        </tr>
        <tr>
            <td>ARG1 + ARG2</td>
            <td>Returns the arithmetic sum of ARG1 and ARG2.</td>
        </tr>
        <tr>
            <td>ARG1 - ARG2</td>
            <td>Returns the arithmetic difference of ARG1 and ARG2.</td>
        </tr>
        <tr>
            <td>ARG1 * ARG2</td>
            <td>Returns the arithmetic product of ARG1 and ARG2.</td>
        </tr>
        <tr>
            <td>ARG1 / ARG2</td>
            <td>Returns the arithmetic quotient of ARG1 divided by ARG2.</td>
        </tr>
        <tr>
            <td>ARG1 % ARG2</td>
            <td>Returns the arithmetic remainder of ARG1 divided by ARG2.</td>
        </tr>
        <tr>
            <td>STRING : REGEXP</td>
            <td>Performs an anchored pattern match of REGEXP in STRING.</td>
        </tr>
        <tr>
            <td>match STRING REGEXP</td>
            <td>Same as STRING : REGEXP.</td>
        </tr>
        <tr>
            <td>substr STRING POS LENGTH</td>
            <td>Returns a substring of STRING, where POS starts from 1.</td>
        </tr>
        <tr>
            <td>index STRING CHARS</td>
            <td>Returns the index in STRING where any character from CHARS is found, or 0 if not found.</td>
        </tr>
        <tr>
            <td>length STRING</td>
            <td>Returns the length of STRING.</td>
        </tr>
        <tr>
            <td>+ TOKEN</td>
            <td>Interprets TOKEN as a string, even if it's a keyword like 'match' or an operator like '/'. This is often used in situations where such keywords need to be treated as literals.</td>
        </tr>
        <tr>
            <td>( EXPRESSION )</td>
            <td>Returns the value of the enclosed EXPRESSION.</td>
        </tr>
    </tbody>
</table>

### $[运算式] 或 $((运算式)) 算术运算式

- Bash Shell提供了<code>$[运算式]</code> 或 <code>$((运算式))</code>用于执行数学运算（只支持整数运算），并可以将数学运算结果赋给变量。表达式和`$[]`或`$(())`之间不能有空格，否则`syntax error: invalid arithmetic operator`。

```shell
$((运算式)) 

$[运算式]
```

```shell
s=(1+2)     # 1+2
s1=$((1+2)) # 3
s2=$[1+2]   # 3

echo $[ 9.9 * 9 ]
#-bash: 9.9 * 9 : syntax error: invalid arithmetic operator (error token is ".9 * 9 ")
```

### bc Bash计算器

- <code>bc</code>命令用于访问Bash计算器。Bash计算器实际上是一种编程语言语言，允许在命令行输入浮点数表达式，然后解释并计算该表达式，最后返回结果。

```shell
# 算术操作高级运算bc命令它可以执行浮点运算和一些高级函数：
echo "1.212*3" | bc 
3.636

$ bc << EOF
> 5*2
> EOF
10
```

- Bash计算器能够识别以下内容：数字（整数和浮点数）、变量（简单变量和数组）、注释（<code>#</code>或C语言的<code>/**/</code>）、表达式、编程语句（如 if-then等语句）、函数。
- Bash计算器的浮点数运算的位数是由内建变量`scale`控制的，`scale`控制浮点数运算结果的小数位位数，默认值为0，也就是默认不包含小数位（截断）。

```shell
# scale 设定小数精度（数值范围）
echo "scale=2;3/8" | bc # 参数scale=2是将bc输出结果的小数位设置为2位。
0.37

$ bc -q
3.44 / 5
0
scale=4
3.44 / 5
.6880
```

- Bash计算器支持变量的使用，变量值一旦被定义就可以在整个Bash计算器中使用，可以通过Bash计算器中的`print var`命令打印变量。

```shell
# bc 支持变量
echo "a=1;b=2;a+b;print a" | bc
# 3
# 1

$ bc -q
a=1
b=2
a*b
2
a+b
3
a+1
2
```

```shell
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

## 退出脚本

### <code><a href="./环境变量.md#退出状态码"><span name="退出状态码1">$?</span></a></code> 退出状态码

- Linux提供了专门的变量`$?`来保存最后一个已执行命令的退出状态码。Linux的退出状态码没有标准，但有一些可用的指南：

<table><thead><tr><th width="10%">状态码</th><th width="90%">意义</th></tr></thead><tbody><tr><td>0</td><td>命令成功结束</td></tr><tr><td>1</td><td>一般性未知错误</td></tr><tr><td>2</td><td>不适合的shell命令</td></tr><tr><td>126</td><td>命令不可执行</td></tr><tr><td>127</td><td>没有找到命令</td></tr><tr><td>128</td><td>无效的退出参数</td></tr><tr><td>128+x</td><td>与Linux信号x相关的严重错误</td></tr><tr><td>130</td><td>通过<code>Ctrl + C</code>终止的命令</td></tr><tr><td>255</td><td>正常范围之外的退出状态码。对于超过正常范围（0~255）的退出状态码，shell会自动对其进行模运算（%256）并将结果作为退出状态码</td></tr></tbody></table>

### exit

- 在默认情况下，shell脚本会以脚本中最后一个命令的退出状态码退出。可以通过<code>exit</code>命令在结束shell脚本时指定一个退出状态码。

```shell
exit 0

# 对于超过正常范围（0~255）的退出状态码，会自动对其进行模运算（%256）
(exit 300);echo $?
# 44
```

## alias 命令别名

- alias：设置指令的别名，只在当前shell起作用，未更改[/etc/bashrc](./Shell.md#bashrc)；对于个人专用的命令别名，应该在[\~/.bashrc](./Shell.md#bashrc)设置。

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

## date 日期时间

- `date`：显示或设置系统时间与日期。

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

# 运算符

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

# 流程控制

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
elif [ 条件判断式 ]
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

# 自定义函数

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
