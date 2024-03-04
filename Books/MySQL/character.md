# 字符集概述

- MySQL包括字符集支持，使用户能够使用各种字符集存储数据并根据各种排序规则进行比较。默认的MySQL服务器字符集和排序规则是`utf8mb4`和`utf8mb4_0900_ai_ci`，但可以在服务器、数据库、表、列和字符串文字级别指定字符集。

## 字符集和排序规则

### 字符集

- 字符集（character set）是一组符号和编码；而排序规则（collation）是一组用于比较字符集中字符的规则。

1. 使用各种字符集存储字符串。
2. 使用各种排序规则比较字符串。
3. 在同一个服务器、同一个数据库甚至同一个表中混合使用不同字符集或排序规则的字符串。
4. 在任何级别启用字符集和排序规则规范。

- MySQL 服务器支持多种字符集，包括多种 Unicode 字符集。

```shell
# 显示所有可用的字符集
show character set;

# 通过like或where来指定范围
show character set like 'utf%'
+---------+------------------+--------------------+--------+
| Charset | Description      | Default collation  | Maxlen |
+---------+------------------+--------------------+--------+
| utf16   | UTF-16 Unicode   | utf16_general_ci   |      4 |
| utf16le | UTF-16LE Unicode | utf16le_general_ci |      4 |
| utf32   | UTF-32 Unicode   | utf32_general_ci   |      4 |
| utf8mb3 | UTF-8 Unicode    | utf8mb3_general_ci |      3 |
| utf8mb4 | UTF-8 Unicode    | utf8mb4_0900_ai_ci |      4 |
+---------+------------------+--------------------+--------+
```

```shell
# 查看当前MySQL使用的字符集
mysql> show variables like 'character_%';
+--------------------------+----------------------------+
| Variable_name            | Value                      |
+--------------------------+----------------------------+
| character_set_client     | utf8mb4                    |
| character_set_connection | utf8mb4                    |
| character_set_database   | utf8mb4                    |
| character_set_filesystem | binary                     |
| character_set_results    | utf8mb4                    |
| character_set_server     | utf8mb4                    |
| character_set_system     | utf8mb3                    |
| character_sets_dir       | /usr/share/mysql/charsets/ |
+--------------------------+----------------------------+
```

### 排序规则

- 排序规则（collation）：一个给定的字符集总是至少有一个排序规则，而大多数字符集有多个排序规则。排序规则具有以下一般特征：

1. 两个不同的字符集不能具有相同的排序规则。
2. 每个字符集都有一个默认排序规则。
3. 归类名称以与其关联的字符集的名称开头，通常后跟一个或多个表示其他归类特征的后缀。

```shell
# 显示所有可用的排序规则
show collation;

# 可选like或where来指定，以下两个命令等价
show collation where charset like 'utf%';
show collation like 'utf%';
+-----------------------------+---------+-----+---------+----------+---------+---------------+
| Collation                   | Charset | Id  | Default | Compiled | Sortlen | Pad_attribute |
+-----------------------------+---------+-----+---------+----------+---------+---------------+
| utf16le_bin                 | utf16le |  62 |         | Yes      |       1 | PAD SPACE     |
```

## 字符集指令表

## 元数据的 UTF-8

- 元数据是“关于数据的数据”，任何描述数据库的东西，而不是数据库的内容，都是元数据。