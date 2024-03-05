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

## 元数据与UTF-8

- 元数据是“关于数据的数据”，任何描述数据库的东西，而不是数据库的内容，都是元数据。MySQL将元数据存储在Unicode字符集（UTF-8）。

1. 所有元数据必须采用相同的字符集。否则，这些操作结果的同一列中的不同行将采用不同的字符集，导致所有`SELECT`语句和表的`SHOW`语句都不会正常工作。
2. 元数据必须包括所有语言的所有字符。否则，用户将无法使用自己的语言来命名列和表。

# 指定字符集和归类

- 字符集和排序规则有四个级别的默认设置：服务器、数据库、表和列
- `character set`或`charset`是用于指定字符集的子句。

- 字符集问题不仅会影响数据存储，还会影响客户端程序与MySQL服务器之间的通信。

```shell
# 指定utf8mb4为与MySQL服务器通信的字符集
set names 'utf8mb4'
```

## 归类命名约定

1. 归类名称以与其关联的字符集的名称开头，通常后跟一个或多个表示其他归类特征的后缀。例如，`utf8mb4_0900_ai_ci`。
2. 特定于语言的排序规则包括区域设置代码或语言名称。
3. 排序规则后缀指示排序规则是区分大小写、区分重音还是区分假名（或它们的某种组合）或二进制。

<table>
    <colgroup>
        <col style="width: 20%">
        <col style="width: 80%">
    </colgroup>
    <thead>
        <tr>
            <th>后缀</th>
            <th>意义</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><code>_ai</code></td>
            <td>口音不敏感</td>
        </tr><tr>
        <td><code>_as</code></td>
        <td>口音敏感</td>
        </tr><tr>
        <td><code>_ci</code></td>
        <td>不区分大小写</td>
        </tr><tr>
        <td><code>_cs</code></td>
        <td>区分大小写</td>
        </tr><tr>
        <td><code>_ks</code></td>
        <td>假名敏感</td>
        </tr><tr>
        <td><code>_bin</code></td>
        <td>二进制</td>
        </tr></tbody>
</table>
