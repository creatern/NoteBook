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

- Bash Shell提供了<code>$[运算式]</code> 或 <code>$((运算式))</code>用于执行数学运算（只支持整数运算），并可以将数学运算结果赋给变量。表达式和`$[]`或`$(())`之间不能有空格（表达式内部也不能有空格），否则`syntax error: invalid arithmetic operator`。

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

# 结构化命令

- 结构化命令（structured command）允许脚本根据条件跳过部分命令，改变执行流程。

## 条件测试（测试命令）

### 条件测试命令

<table>
    <tr>
        <td width="20%"><code>test</code></td>
        <td width="80%">提供最基本的比较</td>
    </tr>
    <tr>
        <td><code>[ condition ]</code></td>
        <td><code>[ condition ]</code>等价于（同义词）<code>test</code>命令</td>
    </tr>
    <tr>
        <th colspan="2">bash shell提供的高级特性：</th>
    </tr>
    <tr>
        <td><code>(command)</code></td>
        <td>在子shell中执行命令</td>
    </tr>
    <tr>
        <td><code>(( expression ))</code></td>
        <td>用于数学表达式</td>
    </tr>
    <tr>
        <td><code>[[ expression ]]</code></td>
        <td>高级字符串处理功能</td>
    </tr>
</table>

1. 注意是否需要空格。
2. 重定向符并不会影响命令返回的退出状态码，只是因为重定向的存在可能会导致命令以另一种（意料之外）的方式执行。

```shell
# 错误地使用了重定向，这样，在shell看来，是在测试"abcd"（test "abcd"）（当然是正确的），只不过是额外增加了重定向到edf的操作
test "abcd" > "edf" && echo "1"
# 1 导致在当前目录下创建了一个内容为abcd且文件名为edf的文件
```

#### test 条件测试 与 <code>[ 测试表达式 ]</code>

- test：检查条件是否成立，能够对数值、字符和文件进行条件测试。如果test命令中列出的条件成立，则之后会退出test命令并返回退出状态码0；如果条件不成立，则之后会退出test命令并返回退出状态码非0。

```shell
test condition
```

- <code>[ 测试表达式 ]</code>是一种对<code>test</code>命令的等效替代（同义词）。<code>[]</code> 与测试表达式之间必须存在一个空格。

```shell
test 测试表达式

[ 测试表达式 ]
```

```shell
[ 1  -eq 1 ] && echo "1"
# 1
```

#### <code>(command)</code> 子shell

- <code>(command)</code>允许在if语句中使用子shell。通过该方式，在bash shell执行command之前，会先创建一个子shell，然后在其中执行命令。 如果命令成功结束，则返回退出状态码0。
- 当在if test语句中使用进程列表时，可能会导致意外的结果。哪怕进程列表中除了最后一个命令之外的其他命令全部失败，只要最后一个命令成功了，子shell就仍然会将退出状态码设为0。同理，如果最后一个命令失败，那么子shell就不会将退出状态码设为0。

```shell
# 子shell使用进程列表，且只有最后一个命令成功时：
if (cat /etc/no-file; echo "Hello")
then
        echo "1"
fi
# cat: /etc/no-file: No such file or directory
# Hello
# 1

# 子shell使用进程列表，且最后一个命令失败时：
if (echo "Hello"; cat /ect/no-file)
then
        echo "T"
else
        echo "F"
fi
# Hello
# cat: /ect/no-file: No such file or directory
# F
```

#### <code>(( expression ))</code> 高级数学表达式

- 双括号命令（<code>(( expression ))</code>）允许在比较过程中使用高级数学表达式（可以是任意的数学赋值或比较表达式），而test命令在比较时只能使用简单的算术操作。
- 除了<code>test</code>命令支持的标准数学运算符外，双括号命令还支持以下运算符：

<table>
    <caption>双括号命令支持的运算符</caption>
    <tr>
        <td width="15%">val++</td>
        <td width="33.75%">后增</td>
        <td width="2.5%" rowspan="7"></td>
        <td width="15%">val--</td>
        <td width="33.75%">后减</td>
    </tr>
    <tr>
        <td>++val</td>
        <td>先增</td>
        <td>--val</td>
        <td>先减</td>
    </tr>
    <tr>
        <td>!</td>
        <td>逻辑求反</td>
        <td>~</td>
        <td>位求反</td>
    </tr>
    <tr>
        <td>**</td>
        <td>幂运算</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>&lt;&lt;</td>
        <td>左位移</td>
        <td>&gt;&gt;</td>
        <td>右位移</td>
    </tr>
    <tr>
        <td>&amp;</td>
        <td>位布尔AND</td>
        <td>|</td>
        <td>位布尔OR</td>
    </tr>
    <tr>
        <td>&amp;&amp;</td>
        <td>逻辑AND</td>
        <td>||</td>
        <td>逻辑OR</td>
    </tr>
</table>

1. 双括号命令既可以在if语句中使用，也用在脚本的普通命令来进行赋值。
2. 双括号命令内的特殊符号（如 &lt;、\*等）无需担心转义问题，不会被误解。

```shell
(( a = 10 ** 2 )); echo $a
# 100
```

#### <code>[[ expression ]]</code> 高级字符串比较

- 双方括号命令（<code>[[ expression ]]</code> ）提供了针对字符串比较的高级特性，不仅可以使用<code>test</code>命令中的标准字符串来比较，还提供了模式匹配，可以通过定义通配符或正则表达式来匹配字符串。

<table>
    <tr>
        <td width="10%">&lt;</td>
        <td width="90%" rowspan="2">根据系统设置的语言变量（locale）来对字符串进行排序</td>
    </tr>
    <tr>
        <td>&gt;</td>
    </tr>
    <tr>
        <td>==</td>
        <td rowspan="2">正则，支持模式匹配</td>
    </tr>
    <tr>
    <td>=~</td>
    </tr>
</table>

### 测试表达式

#### 数值比较

<table>
    <caption>数值比较（Bash Shell支持比较整数）</caption>
    <tbody>
        <tr>
            <td width="15%">-eq</td>
            <td width="33.75%">等于</td>
            <td width="2.5%" rowspan="3"></td>
            <td width="15%">-ne</td>
            <td width="33.75%">不等于</td>
        </tr>
        <tr>
            <td>-lt</td>
            <td>小于</td>
            <td>-le</td>
            <td>小于等于</td>
        </tr>
        <tr>
            <td>-gt</td>
            <td>大于</td>
            <td>-ge</td>
            <td>大于等于</td>
        </tr>
    </tbody>
</table>

#### 字符串比较

<table>
    <caption>字符串比较</caption>
    <tr>
        <td width="15%">str1 = str2</td>
        <td width="33.75%">相同</td>
        <td width="2.5%" rowspan="3"></td>
        <td width="15%">str1 != str2</td>
        <td width="33.75%">不同</td>
    </tr>
    <tr>
        <td>str1 &lt; str2</td>
        <td>小于</td>
        <td>str1 &gt; str2</td>
        <td>大于</td>
    </tr>
    <tr>
        <td>-n str1</td>
        <td>长度不为0</td>
        <td>-z str1</td>
        <td>长度为0</td>
    </tr>
</table>

- 字符串进行比较时的情况：

1. 字符串相等性：只是比较字符串的值是否相同，例如字符、大小写、标点等。
2. 字符串顺序（不等性）：先比较字符串的长度大小；若长度相等，则再依次比较字符串中的字符的大小顺序。
   1. 比较符<code>&lt;</code>以及<code>&gt;</code>必须进行转义，否则会被认为是重定向。如果误认为是重定向，则会导致尽管字符串的大小判断错误，但返回的退出状态码仍然是0（因为重定向未被认为出错）。
   2. 大于和小于顺序与`sort`命令不同。在比较时使用的是每个字符的Unicode编码值的大小，而不是自然语言顺序（系统的语言环境设置中定义的排序顺序 ）。
3. 在比较字符串长度时，字符串内的空格（<code>" "</code>）也算作一个长度。

```shell
# 字符串相等性
$(test "zjk" = "$(whoami)") && echo "1"
# 1

# 错误地使用了重定向，这样，在shell看来，是在测试"abcd"（test "abcd"）（当然是正确的），只不过是额外增加了重定向到edf的操作
test "abcd" > "edf" && echo "1"
# 1 导致在当前目录下创建了一个内容为abcd且文件名为edf的文件

test "abcd" \> "edf" || echo "1"
# 1

# 比较字符串的顺序
test "zcd" \> "zdf" || echo "1"
# 1

test "zcd" \> "zDf" && echo "1"
# 1 因为大写字母的Unicode编码是0044，而小写字母c的Unicode编码是0063

# 纯空格的字符串也计算有长度
test -n " " && echo "1"
# 1
```

#### 文件比较

<table>
    <caption>文件信息比较</caption>
	<tbody>
        <tr>
            <td>-e file</td>
			<td>file是否存在</td>
            <td width="2.5%" rowspan="4"></td>
			<td width="15%">-r file</td>
			<td width="33.75%">file是否存在且可读</td>
        </tr>
		<tr>
			<td width="15%">-f file</td>
			<td width="33.75%">file是否存在且是文件</td>
			<td>-w file</td>
			<td>file是否存在且可写</td>
		</tr>
		<tr>
			<td>-d file</td>
			<td>file是否存在且是目录</td>
			<td>-x file</td>
			<td>file是否存在且可执行</td>
		</tr>
		<tr>
			<td>-s file</td>
			<td>file是否存在且非空</td>
            <td></td>
            <td></td>
		</tr>
        <tr>
            <td colspan="4"></td>
        </tr>
        <tr>
            <td>-O file</td>
            <td>file是否存在且属当前用户所有</td>
            <td rowspan="2"></td>
            <td>file 1 -nt file2</td>
            <td>file1是否比file2新</td>
        </tr>
        <tr>
            <td>-G file</td>
            <td>file是否存在且默认组与当前用户组相同</td>
            <td>file1 -ot file2</td>
            <td>file1是否比file2旧</td>
        </tr>
	</tbody>
</table>


- <code>-G</code>可以检查文件的属组，如果与用户的默认组匹配，则测试成功。但是，<code>-G</code>只会检查默认组而非用户所属的所有组。
- <code>-nt</code>和<code>-ot</code>都不会检查文件是否存在。只要有不存在的文件参与比较<code>-nt</code>和<code>-ot</code>，则返回的退出状态码就一定是非0的

```shell
# -G 只比较文件的属组是否和用户的默认组匹配
ls -g it.sh
# -rw-r--r--. 1 zjk 0 Mar 17 10:24 it.sh
groups
# zjk wheel docker
test -G it.sh && echo "file's defult group matches" || echo "file's defult group does not match"
# file's defult group matches

chgrp wheel it.sh
ls -g it.sh
# -rw-r--r--. 1 wheel 0 Mar 17 10:24 it.sh
groups
# zjk wheel docker
test -G it.sh && echo "file's defult group matches" || echo "file's defult group does not match"
# file's defult group does not match
```

```shell
# 对于不存在的文件进行比较-nt和-ot，返回的退出状态码一定都是非0的
# it.sh是存在的，而qq.sh qaf.txt是不存在的
test it.sh -nt qq.sh && echo "it.sh is new then qq.sh" || echo "it.sh is old then qq.sh"
# it.sh is old then qq.sh

test qq.sh -nt it.sh && echo "it.sh is new then qq.sh" || echo "it.sh is old then qq.sh"
# it.sh is old then qq.sh

test qaf.txt -nt qq.sh && echo "qaf.txt is new then qq.sh" || echo "qaf.txt is old then qq.sh"
# it.sh is old then qq.sh
```

#### 布尔逻辑（复合条件测试）

- 布尔逻辑是一种将可能的返回值简化（reduce）为真（TRUE）或假（FALSE）的方法。

<table>
    <tr>
        <td rowspan="2" width="30%">[ condition1 ] &amp;&amp; [ condition2 ]</td>
        <td width="70%">使用<b>AND</b>来组合两个条件，必须两个条件都为真才返回退出状态码0</td>
    </tr>
    <tr>
        <td>短路与：在使用<code>&amp;&amp;</code>时，如果condition1为真，那么就执行condition2；否则，不执行condition2。只有最后一个condition为真时，才会返回退出状态码0</td>
    </tr>
    <tr>
        <td rowspan="2" >[ condition1 ] || [ condition2 ]</td>
        <td>使用<b>OR</b>来组合两个条件，只要有一个条件为真就返回退出状态码0</td>
    </tr>
    <tr>
        <td> 短路或：在使用<code>||</code>时，如果condition1为真，那么就不执行condition2，直接返回退出状态码0；否则，继续执行condition2</td>
    </tr>
</table>
<img src="../../pictures/Linux-短路与和短路或.drawio.svg" width="550"/> 

## 条件判断

### if-then、if-then-else

1. bash shell的if语句会运行if之后的命令（并不一定要是条件判断），如果该命令的退出状态码为0，那么位于then部分的命令（或代码块）就会被执行；非0则跳过then部分。

2. 如果之前所有语句的then部分都没有被执行（退出状态码非0），且存在elif语句且elif之后的命令（并不一定要是条件判断）的退出状态码是0，则bash shell会执行elif对应的then语句部分的命令（或代码块）。但是，<b>只有第一个返回退出状态码0的语句中的then部分会被执行</b>。
3. 如果以上部分都没有返回退出状态码0，且存在else语句，则执行else语句部分的命令（或代码块）。

<img src="../../pictures/Linux-if-then-else.drawio.svg" width="600"/> 

```shell
if command
then
	commands
fi

if command; then
	commands
fi
```

```shell
if command; then
	commands
elif command; then
	commands
else
	commands
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

### case

- <code>case</code>命令采用列表格式来检查变量的多个值。<code>case</code>命令会将指定变量与不同的模式进行比较，如果变量和模式匹配，则shell就会执行该模式指定的命令。可以通过竖线运算符（<code>|</code>）在一行中分隔出多个模式（只要有一个匹配即可）；星号（<code>*</code>）会捕获所有与已知模式不匹配的值。

```shell
case variable in
pattern1 | pattern2) commands1;;
pattern3) commands2;;
*) default commands;;
esac

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
case $1 in 
"1") 
	echo "选项1";;
"2" | hello | 3)
	echo "选项2";;
*) echo
	"其他选项";;
esac
```

## 循环

### for

#### 标准的for命令

```shell
# $var包含着对应于此次迭代的列表list的当前值
for var in list
do
	commands
done

for var in list; do
	commands
done
```

```shell
#!/bin/bash

for i in 第一名 第二名 第三名
do 
   echo $i 
done 
```

##### for命令-通配符读取目录

- 文件名通配符匹配（file globbing）：for命令也可以用于自动遍历目录中的文件，而为此必须在文件名或路径名中使用通配符，这会强制shell使用文件名通配符匹配。文件名通配符匹配是生成与指定通配符匹配的文件名或路径名的过程。
- 可以在值列表中放入任何东西，即使文件或目录不存在，for语句也会尝试将列表处理完。并不会提示任何错误信息。

```shell
#!/bin/bash

for file in /home/zjk/Documents/*
do
        # 加入""来包含$file是为了防止文件名中存在空格的情况
        if [ -d "$file" ]
        then
                echo "$file is a directory"
        elif [ -f "$file" ]
        then
                echo "$file is a common file"
        fi
done
```

#### C语言风格的for命令

- 在满足给定条件（condition）的情况下，仿C语言的for循环通过定义好的变量（variable assignment）来迭代执行命令。在每次迭代中，该变量都包含for循环中赋予的值。在每次迭代后，循环的迭代过程（iteration process）会作用于变量。
- C语言风格的for命令与标准的bash shell的for命令不一致，在C语言风格下：

1. 变量赋值可以有空格
2. 迭代过程中的变量不以美元符号`$`开头（但在程序中使用的话还是要`$`来引用）
3. 迭代过程的算式不使用`expr`命令格式

- C语言风格的for命令允许为迭代使用多个变量（之间通过`,`分隔），循环会单独处理每个变量，可以为每个变量定义不同的迭代过程（ iteration process）（之间通过`,`分隔）。但在for循环中只能定义一种迭代条件（condition）。

```shell
for (( variable assignment ; condition ; iteration process ))

for (( 初始值; 循环控制条件; 变量变化 ))
do
	程序
done
```

```shell
#!/bin/bash

for (( i=0; i < 10; i++ ))
do
    echo $i
done

# C语言风格的for命令允许为迭代使用多个变量
for (( a=0, b=10; a<=10; a++,b-- ))
do
	echo $a | $b"
done
```

### while

- <code>while</code>命令允许定义一个用于测试的命令（test command），只要该命返回的退出状态码是0，就会循环执行一组命令。会在每次迭代开始前执行测试命令，如果测试命令返回非0的退出状态码，while命令就会停止执行循环（停止这一次和之后的循环）。

```shell
while test commands
do
	other commands
done
```

```shell
i=0
while [ $i -lt 3 ]
do
    i=$[ $i+1 ]
    echo $i
done
```

- while命令允许在while语句行定义多个测试命令，但只有最后一个测试命令的退出状态码会被用于决定是否结束循环。

```shell
i=10
while echo "outside $i"
        i=$[$i+1]
        [ $i -ge 0 ]
do
        echo "inside $i"
        i=$[$i-2]
done
```

### until

- <code>until</code>命令和<code>while</code>命令测试方式相反，<code>until</code>命令要求指定返回一个非0退出状态码的测试命令，只要测试命令的退出状态码不为0，bash shell就会执行循环中的一组命令；反之则退出循环。
- <code>untile</code>命令和<code>while</code>命令一样支持多个测试命令，但也是只有最后一个测试命令的退出状态码会被用于决定是否结束循环。

```shell
until test commands
do
	other commands
done
```

## 循环的控制

### 循环控制

#### break

- <code>break</code>命令可以退出任意类型的循环：

1. 跳出单个循环：shell在执行break命令时会尝试跳出当前正在执行的循环。
2. 跳出内层循环：在处理多个循环时，break命令会自动结束其所在的那一层循环，而其外层的循环仍然会继续执行。
3. 跳出外层循环：break命令接受单个命令行参数n，该参数指定了要跳出的循环层级。默认情况下，n为1，表明跳出的是当前循环。如果将n设为2，那么就会跳出下一级的循环（也就是结束相对于break命令所在循环的外面一层循环），以此类推。

#### continue

- <code>continue</code>命令可以提前中止某次循环（跳过该循环剩余的命令并进入下一个循环），但不会结束整个循环。
- <code>continue</code>命令也接受单个命令行参数n，该参数指定了要提前中止的循环层级。默认情况下，n为1，表明提前中止的是当前循环。如果将n设为2，那么就会提前中止下一级的循环（也就是结束相对于<code>continue</code>命令所在循环的外面一层循环），以此类推。

### <span name="IFS环境变量">IFS环境变量</span> 内部字段分隔符

- for循环中的值列表list，可以是一系列的值，也可以是命令的输出。

1. 当使用一系列的值时，for循环假定各个值之间是以空格（内部字段分隔符）分隔的，如果某个值含有空格（多单词值，multiword value），则需使用单引号或双引号来区分（这些引号不会被当成值的一部分）。
2. 当使用命令替换时，只要输出中的数据中含有空格，即使使用了单引号或双引号来区分，for循环也仍然会用空格来分隔值，也就是将引号视为普通字符处理。

- IFS（internal field separator，内部字段分隔符）环境变量定义了bash shell中用于字段分隔符的一些列字符。默认情况下bash shell会将如下字符视为字段分隔符：空格、制表符<code>\t</code>、换行符（<code>\n</code>）。内部字段分隔符是不会被当作值而显示的。
- 可以在当前shell环境中临时更改IFS的值，以此指定特定的字段分隔符，而之前的默认字段分隔符不再使用。

```shell
# 指定使用换行作为字段分隔符
IFS=$'\n'

# 如果要指定多个字段分隔符，只需要将这些字符写在一起并赋值即可
# 如下会将 换行\n、冒号:、分号;、双引号" 作为内部字段分隔符
IFS=$'\n:;"'
```

### 循环输出的重定向

- 循环的输出可以通过重定向符或管道符来进行重定向。对于每一层循环的输出，会在该层所有的循环都执行完毕后，将整个循环一起输出，而不是每循环一次就输出一次。

```shell
i=10
while [ $i -ge 0 ]
do
        echo "inside $i"
        i=$[$i-2]
done > tmp.data

cat tmp.data
inside 10
inside 8
inside 6
inside 4
inside 2
inside 0
```

### 循环的数据源

```shell
# 将input的值作为while循环的数据源
input="users.csv"
while IFS=',' read -r loginname name
do
	echo "adding $logingname"
	useradd -C "$name" -m "$loginame"
done < "$input"
```

# 用户输入

## 命令行参数

- 向shell脚本传递数据的最基本方法是使用命令行参数，命令行参数允许运行脚本时在命令行中添加数据。
- 命令行参数是在命令/脚本名出现的各个单词，而<a href="./环境变量.md#变量引用">位置参数</a>是用于保存命令行参数（以及函数参数）的变量。bash shell会将所有的命令行参数（包括脚本参数）都指派给称作<a href="./环境变量.md#变量引用">位置参数（positional parameter）</a>的特殊变量（<code>$0~$9</code>）

### <a href="./环境变量.md#变量引用"><code>$</code> 特殊参数（位置参数等）</a>

### shift 移动位置参数

- <code>shift</code>命令会根据命令行参数的相对位置移动，默认情况下会将每个位置的变量值都向左移动一个位置。

1. 在向左移动的过程中，<code>$1</code>原先的值会被移除（<code>$2</code>替代），而不是替代<code>$0</code>的值。<code>$0</code>的值不会<code>shfit</code>命令被影响。
2. 如果某个位置参数被移出了，那么它原先的值就无法再获取到，而是被之后移入的位置参数的值所替代
3. 可以指定<code>shift</code>命令向左移动的位数，默认是1。

```shell
# ./test10-shift 1 2 3
for i in $*
do
	echo $1
	shift
done
echo $1
# 1
# 2
# 3
# （空）
```

### 选项与参数的分离

#### 自定义处理

- 通常情况下，我们自己定义的命令行选项一般（规范）是以连字符（<code>-</code>）或双连字符（<code>--</code>）开头的命令行参数，用于改变命令的行为。

```shell
while [ -n "$1" ]
do
        case "$1" in
                -a) echo "-a option" ;;
                -b) param=$2
                        echo "-b option has parameter=$param"
                        shift ;;
                -c) echo "-c option" ;;
                --) shift
                        break ;;
                *)
                        echo "$1 is not a option" ;;
        esac
        shift
done
```

#### getopt

- <code>getopt</code>可以接受一系列任意形式的命令行选项和参数，并自动将其转换为适当的形式。

```shell
getopt optstring parameters
```

- `optstring`定义了有效的命令行选项字母（只能是单个字母），以及哪些选项字母需要参数值（在字母之后加上<code>:</code>），<code>getopt</code>会基于该选项的设置来解析提供的参数。
- 如果在`parameters`中出现作为选项的字母连用（`-cd`、`-ac`等），且如果是`optstring`中指定为选项的，那么在解析时会自动将他们拆分为单字母选项。（即使在`optstring`中使用`{}`来界定也是如此）
- <code>getopt</code>命令不善于处理带空格和引号的参数值，会将空格当作分隔符，且不会依据引号的指示来将引号内的内容作为一个整体。

```shell
# 指定命令行选项-a、-b、-c、-d，其中-b选项带参数值
getopt ab:cd -a -b Hello -cd
# -a -b Hello -c -d --
getopt ab:{cd} -ad -b Hello -cd
# -a -d -b Hello -c -d --

# 获取命令行的所有参数并进行格式化解析
getopt "$@"

# 难以处理带空格的值
getopt a:b -a "test1 test2" -b
```

```shell
# getopt命令不善于处理带空格和引号的参数值
set -- `getopt -q ab:cd "$@"`

while [ -n "$1" ]
do
        case "$1" in
                -a) echo "-a option" ;;
                -b) param=$2
                        echo "-b option has parameter=$param"
                        shift ;;
                -c) echo "-c option" ;;
                --) shift
                        break ;;
                *)
                        echo "$1 is not a option" ;;
        esac
        shift
done

# $ ./test12-getopt-set.sh -a -b "test1 test2" -c -d
# -a option
# -b option has parameter='test1
# test2' is not a option
# -c option
# -d is not a option
```

#### <span name="替换位置参数"><a href="./环境变量.md#set"><code>set --</code></a> 替换位置参数</span>

- <code>set --</code>可以将位置参数的值替换称<code>set</code>命令所指定的值。

```shell
# 使用getopt命令的输出替换位置参数的值
# ./test12-getopt-set.sh -a -b Hello -c -d
set -- `getopt -q ab:cd "$@"`
echo "$*"
# -a -b 'Hello' -c -d --
```

#### getopts

- <code>getopts</code>是bash shell的内建命令，<code>getopts</code>命令每次只处理一个检测到的命令行参数，在处理完所有参数后，<code>getopts</code>会退出并返回一个大于0的退出状态码。

```shell
getopts optstring variable
```

1. <code>getopts</code>命令能够和已有的shell位置变量更好的配合；而<code>getopt</code>命令在将命令行中选项和参数处理后只生成一个输出。
2. optstring参数和<code>getopt</code>命令内的用法类似，有效的选项字母会在optstring中列出，如果要求选项字母有参数值，则在其后加上一个冒号（<code>:</code>）。<code>getopts</code>命令会将当前参数保存到命令行中定义的variable中。
3. <code>getopts</code>命令可以在参数值中加入空格，而不会像<code>getopt</code>命令一样无法处理。同时，<code>getopts</code>命令也可以将选项字母和参数值写在一起，二者之间可以没有空格。
4. <code>getopts</code>命令会将在命令行中找到的所有未定义的选项统一输出为问号<code>?</code>。也就是说，optstring中未定义的选项字母会以<code>?</code>的形式传递给脚本。
5. <code>getopts</code>命令知道何时停止处理选项，并将参数留给用户来处理。在处理每个选项时，<code>getopts</code>会将<code>OPTIND</code>环境变量值增加1，处理完选项后，可以使用<code>shift</code>命令和<code>OPTIND</code>环境变量的值来移动参数。

- <code>getopts</code>命令要用到两个环境变量：

<table>
    <caption>getopts使用到的环境变量</caption>
    <tr>
        <td width="15%">OPTARG</td>
        <td width="85%">保存<code>getopts</code>中选项字母需要加带的参数值</td>
    </tr>
    <tr>
        <td>OPTIND</td>
        <td>保存参数列表中<code>getopts</code>正在处理的参数位置</td>
    </tr>
</table>

```shell
while getopts :ab:c opt
do
        case "$opt" in
                a) echo "Found -a option" ;;
                b) echo "Found -b option with parameter value $OPTARG" ;;
                c) echo "Found -c option" ;;
                *) echo "Unknown option: $opt"
        esac
done

shift $[ $OPTIND - 1 ]

count=1
for param in "$@"
do      
        echo "Parameter $count: $param"
        count=$[ $count + 1 ]
done 

# $ ./test-13-getopts.sh  -ab BValue -c -d p1 p2 p3
# Found -a option
# Found -b option with parameter value BValue
# Found -c option
# Unknown option: ?
# Parameter 1: p1
# Parameter 2: p2
# Parameter 3: p3
```

### 选项标准化

- Linux中一些常用的选项规范（非必须）：

<table>
    <tr>
        <th width="5%">选项</th>
        <th width="42.5%">描述</th>
        <th width="5%" rowspan="9"></th>
        <th width="5%">选项</th>
        <th width="42.5%">描述</th>
    </tr>
    <tr>
        <td>-a</td>
        <td>显示所有对象</td>
        <td>-n</td>
        <td>使用批处理模式</td>
    </tr>
    <tr>
        <td>-c</td>
        <td>生成计数</td>
        <td>-o</td>
        <td>将所有输出重定向到指定文件</td>
    </tr>
    <tr>
        <td>-d</td>
        <td>指定目录</td>
        <td>-q</td>
        <td>以静默模式运行</td>
    </tr>
    <tr>
        <td>-e</td>
        <td>扩展对象</td>
        <td>-r</td>
        <td>递归处理目录和文件</td>
    </tr>
    <tr>
        <td>-f</td>
        <td>指定读入数据的文件</td>
        <td>-s</td>
        <td>以静默模式运行</td>
    </tr>
    <tr>
        <td>-h</td>
        <td>显示命令的帮助信息</td>
        <td>-v</td>
        <td>生成详细输出</td>
    </tr>
    <tr>
        <td>-i</td>
        <td>忽略文本大小写</td>
        <td>-x</td>
        <td>排除某个对象</td>
    </tr>
    <tr>
        <td>-l</td>
        <td>产生长格式输出</td>
        <td>-y</td>
        <td>对所有问题回答yes</td>
    </tr>
</table>

## read 读取命令

- <code>read</code>命令从标准输入或另一个文件描述符中接受输入，获取输入后，<code>read</code>命令会将数据存入变量。

### read获取用户输入

1. <code>read</code>命令可以一次读取多个变量的值，变量和输入的值都需要使用空格隔开。
2. 如果指定多个变量，则输入的每个数据值都会分配给变量列表中的下一个变量。如果变量数量不够，那么剩下的数据就全部分配给最后一个变量。
3. 如果没有指定变量名，<code>read</code>命令读取的数据将被自动赋值给特定的`REPLY`环境变量。

<table>
    <tr>
        <td width="10%">-p</td>
        <td width="90%">指定读取值时的提示符</td>
    </tr>
    <tr>
        <td>-t</td>
        <td>指定读取值时等待的时间（秒）。未指定该选项前，read命令默认一直等待输入。如果计时器超时，则read命令会返回非0退出状态码</td>
    </tr>
    <tr>
        <td>-n</td>
        <td>指定read命令在收到指定个数的字符后退出并传递给变量</td>
    </tr>
    <tr>
        <td>-s</td>
        <td>无显示读取，read命令会将读取到的输入数据上的颜色设置地和背景色一致</td>
    </tr>
</table>


```shell
# 要求在10秒内输入，并且有提示信息
read -t 10 -p "请在10秒内输入: " x
echo "输出：$x"

# 指定read接受一个字符的输入，且会传x递给REPLY
read -n 1
echo $REPLY 
```

### read读取文件

- <code>read</code>命令可用于读取文件，每次调用read命令都会从指定文件中读取一行文本，通常搭配while命令循环使用。当文件中没有内容可读时，read命令会退出并返回非0退出状态码。

```shell
count=1
tail -n 8 /etc/profile | while read
do
        echo "Line-$count：$REPLY"
        count=$[ $count + 1 ]
done
echo "Finish read file."

#  ./test-14-read.sh
# Line-1：# GRADLE_HOME
# Line-2：export GRADLE_HOME=/opt/gradle-8.6
# Line-3：export PATH=$GRADLE_HOME/bin:$PATH
# Finish read file.
```

# 重定向

## 重定向操作符

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

```shell
# 通过输入重定向搭配wc统计文本
wc < mylog.txt
```

### &lt;&lt; 内联输入重定向 与 文本标记符

- 内联输入重定向（<code>&lt;&lt;</code>）需要搭配文本标记符号使用，文本标记符可以是任意字符或字符串，用于标记多行文本的开头和结束。

```shell
# 内联输入重定向
wc << EOF
this is title
this is context
this is end
EOF
      3       9      42
```

## 文件描述符

### 标准文件描述符

- 文件描述符：Linux系统会将每个对象当作文件来处理，包括输入和输出。Linux是由<b>文件描述符</b>来标识每个文件对象。文件描述符是一个非负整数，唯一会标识的是会话中打开的文件。
- 每个进程一次最多可以打开9个文件描述符，而bash shell保留了前3个文件描述符（0、1、2），并通过这3个特殊的文件描述符来处理文件的输入和输出。进程从标准输入文件得到输入数据，将正常输出数据输出到标准输出文件，而错误信息则打印到标准错误文件。

<table>
    <tr>
        <td width="5%">0</td>
        <td width="10%">STDIN</td>
        <td width="10%">标准输入</td>
        <td width="75%">STDIN（标准输入文件）代表shell的标准输入（对于终端而言，通常是键盘），shell会从键盘中读取输入并进行处理</td>
    </tr>
    <tr>
        <td>1</td>
        <td>STDOUT</td>
        <td>标准输出</td>
        <td>STDOUT（标准输出文件）代表shell的标准输出（在终端界面上，屏幕就是标准输出）</td>
    </tr>
    <tr>
        <td>2</td>
        <td>STDERR</td>
        <td>标准错误</td>
        <td>STDERR（标准错误输出文件）代表了shell的标准错误消息输出（默认也输出到标准输出，即屏幕上），shell或运行在shell中的程序和脚本报错时产生的错误消息就送到该文件处</td>
    </tr>
</table>

<img src="../../pictures/153845716239573.png" width="369"/> 

1. 如果给定的文件不止一个，则在显示的每个文件前面加一个文件名标题。
2. 若不指定任何文件名称/给予的文件名为"\-"，则命令从标准输入设备读取数据。

### &amp; 文件描述符引用

<table>
    <tr>
        <td width="10%"><code>&amp;n</code></td>
        <td width="90%"><code>&amp;n</code>用于表示该行命令中上一个重定向操作符重定向到的命令或文件或文件描述符。</td>
    </tr>
    <tr>
        <td rowspan="2">&amp;-</td>
        <td>用于关闭文件描述符，只要文件描述符重定向到该符号<code>&amp;-</code>，那么该文件描述符就会被关闭</td>
    </tr>
    <tr>
        <td>一旦文件描述符被关闭，那么就不能其再接收输入或输出，否则shell会发出错误消息</td>
    </tr>
</table>

```shell
# 重定向操作符可以混合使用，如将标准输出和标准错误输出重定向到同一个文件
ls >myOutAndErr.txt 2>&

# 将输出重定向到标准错误文件
echo "hello" >&2 

# 关闭文件描述符3
exec 3>&-
```

### exec 更改文件描述符

- <code>exec</code>命令可用于改变shell进程中的标准输入、输出与错误，这样，在当前的整个shell中的这些标准文件就被修改到指定的文件了。

```shell
# 将文件描述符3重定向到标准输出（2）
exec 3>&1
exec 1>i_stdout.txt

echo "Hello_1"

# 将标准输入文件（2）重定向到 文件描述符3
exec 1>&3

echo "Hello_2"

# 输出结果：
# Hello_2
```

```shell
# 将文件描述符号3的读写重定向到test.txt
exec 3<>test.txt
```

### lsof 文件描述符列表

- <code>lsof</code>命令会列出整个Linux系统（包括后台进程和登录用户）的打开的所有文件描述符。
- 因为文件描述符（STDIN、STDOUT、STDERR）都指向终端，所以输出文件名就是终端的设备名。（虽然有点奇怪，但这3各文件都支持读写）

<table>
    <thead>
        <tr>
            <th width="15%">选项</th>
            <th width="85%">描述</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><code>-a</code></td>
            <td>列出所有打开文件的进程信息</td>
        </tr>
        <tr>
            <td><code>-c &lt;进程名&gt;</code></td>
            <td>列出指定进程名所打开的所有文件</td>
        </tr>
        <tr>
            <td><code>-g</code></td>
            <td>列出基于GID号的进程详细信息</td>
        </tr>
        <tr>
            <td><code>-d &lt;文件号&gt;</code></td>
            <td>列出占用指定文件描述符的进程</td>
        </tr>
        <tr>
            <td><code>+d &lt;目录&gt;</code></td>
            <td>列出指定目录下被打开的所有文件</td>
        </tr>
        <tr>
            <td><code>+D &lt;目录&gt;</code></td>
            <td>递归列出指定目录及其子目录下被打开的所有文件</td>
        </tr>
        <tr>
            <td><code>-n &lt;目录&gt;</code></td>
            <td>列出使用NFS（网络文件系统）的文件及其相关进程</td>
        </tr>
        <tr>
            <td><code>-i &lt;条件&gt;</code></td>
            <td>列出符合特定条件（如协议、端口号、IP地址）的进程</td>
        </tr>
        <tr>
            <td><code>-p &lt;进程号&gt;</code></td>
            <td>列出指定进程号所打开的所有文件</td>
        </tr>
        <tr>
            <td><code>-u</code></td>
            <td>列出基于UID号的进程详细信息</td>
        </tr>
        <tr>
            <td><code>-h</code></td>
            <td>显示帮助信息</td>
        </tr>
        <tr>
            <td><code>-v</code></td>
            <td>显示命令版本信息</td>
        </tr>
    </tbody>
</table>

```shell

lsof -a -p $$ -d 0,1,2
# bash    53478  zjk    0u   CHR  136,4      0t0    7 /dev/pts/4
# bash    53478  zjk    1u   CHR  136,4      0t0    7 /dev/pts/4
# bash    53478  zjk    2u   CHR  136,4      0t0    7 /dev/pts/4
```

## tee 多目标输出

- <code>tee</code>命令：read from standard input and write to standard output and files. Copy standard input to each FILE, and also to standard output.（默认覆盖）

```shell
tee [OPTION]... [FILE]...
```

<table>
    <thead>
        <tr>
            <th width="30%">Option</th>
            <th width="70%">Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><code>-a, --append</code></td>
            <td>append to the given FILEs, do not overwrite</td>
        </tr>
        <tr>
            <td><code>-i, --ignore-interrupts</code></td>
            <td>ignore interrupt signals</td>
        </tr>
        <tr>
            <td><code>-p</code></td>
            <td>operate in a more appropriate MODE with pipes.</td>
        </tr>
        <tr>
            <td><code>--output-error[=MODE]</code></td>
            <td>set behavior on write error. See MODE below</td>
        </tr>
        <tr>
            <td><code>--help</code></td>
            <td>display this help and exit</td>
        </tr>
        <tr>
            <td><code>--version</code></td>
            <td>output version information and exit</td>
        </tr>
    </tbody>
</table>

- The  default  MODE  for  the <code>-p</code> option is '<code>warn-nopipe</code>'.  With "<Code>nopipe</Code>" MODEs, exit immediately if all outputs become broken pipes.  The default operation when <code>--out‐put-error</code> is not specified, is to exit immediately on error writing to a pipe, and diagnose errors writing to non pipe outputs.

<table>
    <thead>
        <tr>
            <th width="20%">Output Error Mode</th>
            <th width="80%">Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>warn</td>
            <td>diagnose errors writing to any output</td>
        </tr>
        <tr>
            <td>warn-nopipe</td>
            <td>diagnose errors writing to any output that is not a pipe</td>
        </tr>
        <tr>
            <td>exit</td>
            <td>exit on error writing to any output</td>
        </tr>
        <tr>
            <td>exit-nopipe</td>
            <td>exit on error writing to any output that is not a pipe</td>
        </tr>
    </tbody>
</table>

```shell
# 将"Hello"输出到STDOUT以及t1.txt和t2.txt文件
echo "Hellp" | tee t1.txt t2.txt
```

## \| 管道符

- \|（管道符）：连接两个命令（管道连接，piping），将一个程序/命令的输出作为另一个程序/命令的参数输入。一般为输入和输出的结合，一个进程向管道的一端发送数据，而另一个进程从该管道的另一端读取数据。
- 由管道符连接的两个命令并不是依次执行，而是由Linux系统同时运行这两个命令，在系统内部将二者连接起来（实时化）。当第一个命令产生输出时，它会被立即传给第二个命令。此处的数据传输不会用到任何文件或缓冲区。
- 管道符可以串联的命令数量没有限制，可以持续地将命令输出通过管道传给其他命令来细化操作。

```shell
# 列出/etc下匹配模式"*.config"的文件
ll /etc | grep "*.config"

# 分页查看排序后的已安装的软件包列表
rpm -qa | sort | more
```

## <code>/dev/null</code> 黑洞 &#x1F573;

- 所有送往黑洞（<code>/dev/null</code>）的文件都是有去无回的&#9924;，以达到抑制消息输出的目的。

```shell
echo "I'm here" no-back
sudo mv no-back /dev/null
cat /dev/null
# 是空的，而不是 I'm here
```

# 函数 function

- bash shell提供函数的创建和调用。

```shell
[ function ] 函数名[()]
{
    执行语句;
    [echo "输出"]
    [return 0~255]
}
```

1. 必须在调用函数地方之前，先定义函数，因为shell 脚本是逐行运行的。
2. <b>bash shell将函数视为一个小型脚本</b>，运行结束后会返回一个退出状态码。函数执行结束后，可以通过标准变量`$?`来确定函数的退出状态码。
   1. 在默认情况下函数的退出状态码会是函数中最后一个命令返回的退出状态码。
   2. 通过return语句可以设置函数的退出状态码（0\~255），任何超过该范围的数值都会产生错误的值。
3. 读取函数的输出
   1. 如果是直接调用函数<code>func0</code>，则bash shell会将所有在函数内部的<code>echo</code>语句的输出当作在当前脚本中执行一样正常输出。
   2. 如果是命令调用的方式<code>$(func0)</code>，则bash shell会将所有在函数内部的<code>echo</code>语句的输出都合并为一个字符串输出到外部作为一个值，例如<code>$(func1)</code>会读取到func1内部的echo输出的值。
   3. bash shell能够识别函数内的输出，以保证正确的输出。例如<code>read</code>命令的消息提示会正常输出到外部，而不是作为值被返回。
4. 函数可以使用标准的位置变量（<code>$0, $1, ...</code>），将参数和函数名放在同一行，之后可以通过位置变量来调用参数。函数内部使用的位置变量并不是这个脚本的位置变量，而是传递给函数的参数，也就是函数只能通过参数传递的方式来获取脚本的位置变量。
5. 函数中使用变量，需要考虑变量的作用域
   1. 默认情况下，脚本中定义的变量，对于该脚本而言是全局变量，在函数内可以使用这种变量。
   2. 如果变量（全局变量）在函数内部被赋予了新值，那么在这个脚本中，该变量的值就被改变为这个新值，除非又被修改了。
   3. 函数内部可以定义<b>局部变量<code><b>local</b></code></b>，局部变量保证了变量仅在该函数中有效，且如果局部变量和脚本的全局变量同名，shell会保证这两个同名变量的值互不干扰，也就是保证函数使用的是局部变量而不是脚本的全局变量。
6. 局部函数变量（`local`）自成体系（self-containment），除了获取函数参数，自成体系的函数不需要使用任何外部资源。使得函数可以递归地调用。
7. 向函数传递[数组变量](./环境变量.md)，或函数返回数组变量
   1. 如果试图将数组变量作为函数参数来传递，则函数只会提取数组变量的第一个元素。因此，必须先拆分数组再于函数内部组合。
   2. 如果想要函数返回数组，则只能是在函数内部将数组拆分再一起输出，最后交由脚本来进行组合。

```shell
function arrTest(){
	local newArr
	newArr=(`echo "$@"`)
	# 数组合并
	echo "New Arr：${newArr[*]}"
}

oldArr=(1 2 3)
arrTest $oldArr
arrTest ${oldArr[*]} # 数组拆分

#1 2 3
#New Arr：1
#New Arr：1 2 3
```

# 函数库

- bash shell允许创建函数库文件，然后在多个脚本中引用此库文件。

1. 函数库文件与脚本不同的是，函数库文件内只定义了函数，而且没有以`#!/bin/bash`开头。
2. 函数库文件可以在脚本中通过`source`命令或`.`命令来执行，以在当前的脚本shell内定义函数。

```shell
# 函数库文件 /mysh/myfuncs.sh
function add(){
        echo $(($1 + $2))
}

function hi(){
        echo "Hi!"
}
```

```shell
#!/bin/bash

# 使用函数库的脚本
source /mysh/myfuncs.sh

add 1 2
hi
```



