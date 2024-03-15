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
        <td width="15%">单引号 '</td>
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

### 直接执行

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

## 命令替换

# 运算符

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
