# database

## show databases

- `show databases`：只显示当前用户具有权限的数据库；需要注意该命令是databases，而不是database。

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
        <td rowspan="4" width="15%">primary key</td>
        <td colspan="3">主键约束</td>
    </tr>
    <tr>
        <td rowspan="2" width="15%">create table</td>
        <td width="10%">列级</td>
        <td width="60%"><code>primary key</code></td>
    </tr>
    <tr>
        <td>表级</td>
        <td><code>constraint '约束名' primary key(列名, 列名, ..)</code></td>
    </tr>
    <tr>
        <td>alter table</td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td rowspan="6" width="15%">foreign key</td>
        <td colspan="3">外键约束</td>
    </tr>
    <tr>
        <td rowspan="2" width="15%">create table</td>
        <td width="10%">列级</td>
        <td width="60%"><code>references 主键表(主键列名)</code></td>
    </tr>
    <tr>
        <td>表级</td>
        <td><code>constraint '约束名' foreign key(当前表列名, 当前表列名, ..) references 主键表(主键列名, 主键列名, ..)</code></td>
    </tr>
    <tr>
        <td>alter table</td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td>级联更新</td>
        <td colspan="2"><code>on update cascade</code></td>
    </tr>
    <tr>
        <td>级联删除</td>
        <td colspan="2"><code>on delete cascade</code></td>
    </tr>
    <tr>
        <td rowspan="3">not null</td>
        <td colspan="3">非空约束</td>
    <tr>
        <td width="15%">create table</td>
        <td width="10%">列级</td>
        <td width="60%"><code>not null</code></td>
    </tr>
    <tr>
        <td>alter table</td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td rowspan="4">unique</td>
        <td colspan="3">唯一性约束</td>
    <tr>
        <td width="15%" rowspan="2">create table</td>
        <td width="10%">列级</td>
        <td width="60%">列唯一：<code>unique</code></td>
    </tr>
    <tr>
        <td>表级</td>
        <td>组唯一：<code>unique(列名, 列名, ..)</code></td>
    </tr>
    <tr>
        <td>alter table</td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td rowspan="4">default</td>
        <td colspan="3">默认值约束</td>
    <tr>
        <td width="15%">create table</td>
        <td width="10%">列级</td>
        <td width="60%"><code>default 默认值</code></td>
    </tr>
    <tr>
        <td rowspan="2">alter table</td>
        <td colspan="2">修改默认值：<code>alter table 表名 modify column 列名 数据类型 default 新的默认值</code></td>
    </tr>
    <tr>
        <td colspan="2">删除默认值：<code>alter table 表名 modify column 列名 数据类型</code></td>
    </tr>
    <tr>
        <td rowspan="2">check</td>
        <td colspan="3">检查约束</td>
    </tr>
    <tr>
        <td colspan="3">MySQL本身并不强制执行标准SQL的CHECK约束，即不完全支持</td>
    </tr>
</table>


### `auto_increment`

- `auto_increment`：自增列（自增约束，严格上来说这并不是约束）；一个表只能存在一个该属性，且该属性所在列必须存在unique约束，故通常用于主键

1. 该属性所在列的数据类型必须是整数（integer、int、smallint、tinyint、mediumint、bigint），对于不同的整数类型，`auto_increment`的最大值范围也不同
2. 向表中插入新记录时，如果指定为 `auto_increment` 的列没有显式赋值或赋值为null，则MySQL会自动为这个列分配下一个可用的唯一整数值
3. 如果删除了具有最高 `auto_increment` 值的行并插入新行，则新行的 `auto_increment` 值将是已删除行的值加1，即不会重新使用之前删除的ID；而手动插入一个大于当前 `auto_increment` 值的数字时，InnoDB会更新其内部的自增计数器以确保下一次自动生成的值不会冲突
4. InnoDB存储引擎从MySQL 8.0开始将自增计数器持久化存储，避免了因服务器重启导致的计数问题
5. 如果开启`NO_AUTO_VALUE_ON_ZERO`配置，则插入0将被视为明确指定该列值为0，而非触发自增功能；而如果禁用该配置，则插入0会触发自增功能

```mysql
create table tb_demo (
	id int auto_increment,
	name varchar(255)
);
```

```mysql
-- 重设auto_increment的初值；因为auto_increment属性在一张表是唯一的，故不需要指明字段
alter table tb_demo auto_increment = 100;
```

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

## delete

