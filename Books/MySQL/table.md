# 查询表信息

## show tables

- `show tables`：列出给定数据库中的非`TEMPORARY`表

## describe/desc

- `desc`：列出指定表的结构

# create table

```mysql
create table [if not exists] 表名(
    字段名 数据类型(长度) [约束],
    字段名 数据类型(长度) [约束],
);
```

# alter table

# constraint

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

## `auto_increment`

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