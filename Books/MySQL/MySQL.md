# MySQL命令行

<table><colgroup><col style="width: 10%"><col style="width: 80%"></colgroup><thead><tr>
          <th>提示符</th>
          <th>意义</th>
        </tr></thead><tbody><tr>
          <td><code class="literal">mysql&gt;</code></td>
          <td>准备好接受新查询</td>
        </tr><tr>
          <td><code class="literal">-&gt;</code></td>
          <td>等待下一行多行查询</td>
        </tr><tr>
          <td><code class="literal">'&gt;</code></td>
          <td>等待下一行，等待以单引号 ( <code class="literal">'</code>)开头的字符串的完成；可以通过<code class="literal">'\c</code>来断开</td>
        </tr><tr>
          <td><code class="literal">"&gt;</code></td>
          <td>等待下一行，等待以双引号 ( <code class="literal">"</code>)开头的字符串的完成</td>
        </tr><tr>
          <td><code class="literal">`&gt;</code></td>
          <td>等待下一行，等待以反引号 ( <code class="literal">`</code>)开头的标识符的完成</td>
        </tr><tr>
          <td><code class="literal">/*&gt;</code></td>
          <td>等待下一行，等待以 开头的评论完成<code class="literal">/*</code></td>
</tr></tbody></table>
## 连接


```shell
# 连接MySQL
mysql [-h 主机地址] [-u 用户 -p] [数据库名] [< 文件 | 文件 | 文件] [> 文件]
```

<table>
    <tr>
        <th width="20%">连接选项</th>
        <th width="80%">意义</th>
    </tr>
    <tr>
        <td>-h 主机地址</td>
        <td>指定连接的服务器主机地址；若忽略，则使用本地地址</td>
    </tr>
    <tr>
        <td>-u 用户 -p</td>
        <td>指定通过用户密码方式登录；若忽略，则尝试匿名登录</td>
    </tr>
    <tr>
        <td><span name="连接时指定数据库">数据库名</span></td>
        <td>选择待会使用的数据库；若忽略，则需要通过<code>use database</code>选择使用的数据库</td>
    </tr>
    <tr>
        <td rowspan="3">&lt; 文件 | 文件 | 文件</td> 
        <td>批处理模式，MySQL从该文件中读取要执行的命令</td>
    </tr>
    <tr>
        <td><code>mysql -t</code>：以批处理模式获取交互式输出格式</td>
    </tr>
    <tr>
        <td><code>mysql -v</code>将执行的语句回显到输出中</td>
    </tr>
    <tr>
        <td>&gt; 文件</td>
        <td>捕获输出到指定文件</td>
    </tr>
</table>

```mysql
-- 断开连接的3种方式
exit
quit
\q
```

## 脚本与文件

```mysql
-- MySQL执行脚本文件的两种方式
source 文件
\. 文件
```

```mysql
select * from mytb
into outfile '导出到的文件位置';
```

- `--secure-file-priv`：MySQL的一个安全选项，用于限制数据的导入和导出；该选项被设置后，MySQL只能在指定的目录下进行文件的操作

```mysql
# 查看变量名包含secure_file_priv的变量
show variables like 'secure_file_priv'
```

<table>
    <tr>
        <th width="25%"><Code>--secure-file-priv</Code>的值</th>
        <th width="65%">意义</th>
    </tr>
    <tr>
        <td>null</td>
        <td>禁止所有文件导入和导出</td>
    </tr>
    <tr>
        <td>具体的目录路径</td>
        <td>只允许在该目录及其子目录下进行文件操作</td>
    </tr>
    <tr>
        <td>空字符串 <code>''</code></td>
        <td>不限制文件操作的范围，但某些版本的MySQL可能不允许该选项</td>
    </tr>
</table>

# database

## show databases

- `show databases`：只显示当前用户具有权限的数据库；需要注意该命令是databases，而不是database

### database()

- `database()`：该函数将当前数据库名作为`utf8mb3`字符集中的字符串返回；若没有，则返回null

```mysql
select database();
```

## create database

- `create database`：创建数据库，并没有立刻选择使用该数据库

```mysql
create database 数据库名;
```

## use database

- `use database`：选择并使用指定的数据库；此外，可以在<a href="#连接时指定数据库">连接时添加数据库选项</a>来选择数据库并使用

```mysql
use database mydb;
```

# user\&grant

## grant

```mysql
grant 权限选项 on 数据库名.表等对象
to '被授权者的用户名'@'被授权者的主机地址';
```

# table

## show tables

- `show tables`：列出给定数据库中的非`TEMPORARY`表

## describe/desc

- `desc`：列出指定表的结构

## create table

```mysql
create table [if not exists] 表名(
    字段名 数据类型(长度) [约束],
    字段名 数据类型(长度) [约束],
);
```

## alter table

## constraint

<table>
    <tr>
        <td rowspan="4" width="10%">primary key</td>
        <td colspan="3">主键约束</td>
    </tr>
    <tr>
        <td rowspan="2" width="15%">create table</td>
        <td width="10%">列级</td>
        <td width="65%"><code>primary key</code></td>
    </tr>
    <tr>
        <td>表级</td>
        <td><code>constraint '约束名' primary key(列名, 列名, ..)</code></td>
    </tr>
    <tr>
        <td>alter table</td>
        <td colspan="2"></td>
    </tr>
</table>


# crud

## insert

```mysql
insert into 表名(字段名, 字段名)
values
('数据',123),
('数据',123);
```

<table>
    <tr>
        <td rowspan="3" width="20%">表名(字段名, 字段名)</td>
        <td>若只给出表名，则需要严格按照表中的字段顺序来插入数据</td>
    </tr>
    <tr>
        <td>若只给出部分字段名，则按照给出的字段顺序来插入数据，未给出的字段则视为null；但如果违背了约束，则将插入失败</td>
    </tr>
    <tr>
        <td>若给出了全部字段名，则按照给出的字段顺序来插入数据</td>
    </tr>
    <tr>
        <td rowspan="4">values(数据, 数据,..)</td>
        <td>允许进行数据的多行插入，插入的规则如上所述</td>
    </tr>
    <tr>
        <td>日期类型的数据需要按照指定的格式和单引号（<code>'yyyy-mm-dd'</code>）插入</td>
    </tr>
    <tr>
        <td>字符类型的数据需要按照单引号（<code>'str'</code>）插入</td>
    </tr>
    <tr>
        <td>数字类型的数据可以直接插入（<code>123</code>）</td>
    </tr>
</table>


### load

```mysql
load data local infile '/path/demo.txt' into table 表名
lines terminated by '行终止符';
```

> 行终止符：Linux和Windows使用`\r\n`；而macOS使用`\r`

- `demo.txt`文件中的每一行对应表的一行数据；每个字段之间以制表符`\t`分隔，且必须严格按照表中字段的顺序；对于null则使用`\N`来表示

```
Whistler        Gwen    bird    \N      1997-12-09      \N
```

## select

```mysql
select 字段
[from 数据源]
[where 谓词]
[order by 排序字段,排序字段 asc|desc]
[limit n]
```

<table>
    <tr>
        <td>select 字段</td>
        <td>需要查询的字段，允许通过<code>*</code>选择所有字段</td>
    </tr>
    <tr>
        <td>from 数据源</td>
        <td>数据源可以是表、子查询等；若不需要数据源，则可以忽略</td>
    </tr>
    <tr>
        <td>where 谓词</td>
        <td>查询条件</td>
    </tr>
    <tr>
        <td rowspan="2">order by</td>
        <td>指定字段排序，可以指定多个字段依次进行多级排序</td>
    </tr>
    <tr>
        <td>默认以升序排序<code>asc</code>（ascending），可选降序<code>desc</code>（descending）</td>
    </tr>
    <tr>
        <td>limit n</td>
        <td>限定只要前n行数据</td>
    </tr>
</table>

### null

- `null`：缺失的未知值

<table>
    <tr>
        <th width="20%">使用null值</th>
        <th width="80%" colspan="2">结果</th>
    </tr>
    <tr>
        <td>is [not] null</td>
        <td colspan="2">正确的结果</td>
    </tr>
    <tr>
        <td>算术比较符</td>
        <td colspan="2">与null的任何算术比较的结果只会是null</td>
    </tr>
    <tr>
        <td rowspan="3">布尔运算 and or</td>
        <td><code>0 and null</code>：0</td>
        <td><code>1 and null</code>：null</td>
    </tr>
    <tr>
        <td><code>0 or null</code>：null</td>
        <td><code>1 or null</code>：1</td>
    </tr>
    <tr>
        <td>order by</td>
        <td colspan="2">在<code>asc</code>排序下，null优先显示；而<code>desc</code>相反</td>
    </tr>
</table>

### like

- `like`：模式匹配

<table>
    <tr>
        <td rowspan="2" width="10%"><code>[not] like</code></td>
        <td width="80%"><code>_</code>匹配任何单个字符</td>
    </tr>
    <tr>
        <td><code>%</code>匹配任意数量字符</td>
    </tr>
    <tr>
        <td>regexp_like()</td>
        <td rowspan="3">正则扩展</td>
    </tr>
    <tr>
        <td>regexp</td>
    </tr>
    <tr>
        <td>rlike</td>
    </tr>
</table>

```mysql
select name from sys_menu where regexp_like(name, '^..管理');
```

### 常用的几个查询函数

<table>
    <tr>
        <td width="15%"><code>count()</code></td>
        <td width="85%">计算行数，间接地表示某种类型的数据在表中出现的频率</td>
    </tr>
    <tr>
        <td><code>max()</code></td>
        <td>列的最大值</td>
    </tr>
    <tr></tr>
</table>

### join

### limit

### group by

## update

```mysql
update 表
set 要更新的字段名=新数据
where 谓词
```

# 常用函数

## 日期时间函数

<table>
    <tr>
        <th width="320px">函数</th>
        <th width="140px">返回值类型</th>
        <th colspan="2">意义与参数</th>
    </tr>
    <tr>
        <td>curdate()</td>
        <td>date</td>
        <td colspan="2">当前的日期</td>
    </tr>
    <tr>
        <td>curtime()</td>
        <td>time</td>
        <td colspan="2">当前的时间</td>
    </tr>
    <tr>
        <td rowspan="4">timestampdiff(时间格式, 时间1, 时间2)</td>
        <td rowspan="4">时间格式决定</td>
        <td colspan="2">以指定的时间格式，计算两个时间之间的时间段</td>
    </tr>
    <tr>        
        <td width="160px">时间格式</td>
        <td></td>
    </tr>
    <tr>
        <td>时间1/2的类型</td>
        <td></td>
    </tr>
</table>

```mysql
select name, timestampdiff(year, birth_day, curdate()) as age
from pet;
```

## 窗口函数

<table>
    <tr>
        <th width="35%">函数</th>
        <th width="65%" colspan="2">意义</th>
    </tr>
    <tr>
        <td rowspan="3" width="35%"><code>partion by 列 order by 列 asc|desc</code></td>
        <td colspan="2" width="65%">提供分窗函数需要的参数</td>
    </tr>
    <tr>        
        <td width="25%"><code>partion by</code></td>
        <td wdith="40%">分窗的标准</td>
    </tr>
    <tr>
        <td>order by</td>
        <td>每个窗口内部的排序规则</td>
    </tr>
    <tr>
        <td><code>rank over(参数)</code></td>
        <td colspan="2">分窗</td>
    </tr>
</table>
