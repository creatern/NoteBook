# MySQL数据类型

- MySQL支持SQL数据类型的几个类别：数字类型、日期和时间类型、字符串（字符和字节）类型、空间类型和 JSON数据类型。
- 数据类型描述使用这些约定：

1. 对于整数类型，M指示最大显示宽度。对于浮点和定点类型， M是可以存储的总位数（精度）。对于字符串类型， M是最大长度。的最大允许值M取决于数据类型。
2. D适用于浮点数和定点数类型，表示小数点后的位数（刻度）。最大可能值为 30，但不应大于 M−2。
3. fsp适用于 TIME、 DATETIME和 TIMESTAMP类型并表示小数秒精度；即秒的小数部分小数点后的位数。该 fsp值（如果给定）必须在 0 到 6 的范围内。值 0 表示没有小数部分。如果省略，则默认精度为 0。（这与标准SQL默认值 6 不同）
4. 方括号 （\[和\]） 表示类型定义的可选部分。

# 数值

- MySQL支持所有标准的SQL数字数据类型。这些类型包括精确数值数据类型（INTEGER、SMALLINT、DECIMAL、NUMERIC），以及近似数值数据类型（FLOAT、REAL、DOUBLE PRECISION）。

## 整数 INTEGER

- MySQL支持SQL标准整数类型 `INTEGER` （或 `INT` ）和 `SMALLINT` 。作为标准的扩展，MySQL还支持整数类型 `TINYINT` 、 `MEDIUMINT` 和 `BIGINT` 。

<table><colgroup><col style="width: 16%"><col style="width: 16%"><col style="width: 16%"><col style="width: 16%"><col style="width: 16%"><col style="width: 16%"></colgroup><thead><tr>
            <th scope="col">Type</th>
            <th scope="col">Storage (Bytes)</th>
            <th scope="col">Minimum Value Signed</th>
            <th scope="col">Minimum Value Unsigned</th>
            <th scope="col">Maximum Value Signed</th>
            <th scope="col">Maximum Value Unsigned</th>
          </tr></thead><tbody><tr>
            <th scope="row"><code>TINYINT</code></th>
            <td>1</td>
            <td><code>-128</code></td>
            <td><code>0</code></td>
            <td><code>127</code></td>
            <td><code>255</code></td>
          </tr><tr>
            <th scope="row"><code>SMALLINT</code></th>
            <td>2</td>
            <td><code>-32768</code></td>
            <td><code>0</code></td>
            <td><code>32767</code></td>
            <td><code>65535</code></td>
          </tr><tr>
            <th scope="row"><code>MEDIUMINT</code></th>
            <td>3</td>
            <td><code>-8388608</code></td>
            <td><code>0</code></td>
            <td><code>8388607</code></td>
            <td><code>16777215</code></td>
          </tr><tr>
            <th scope="row"><code>INT</code></th>
            <td>4</td>
            <td><code>-2147483648</code></td>
            <td><code>0</code></td>
            <td><code>2147483647</code></td>
            <td><code>4294967295</code></td>
          </tr><tr>
            <th scope="row"><code>BIGINT</code></th>
            <td>8</td>
            <td><code>-2<sup>63</sup></code></td>
            <td><code>0</code></td>
            <td><code>2<sup>63</sup>-1</code></td>
            <td><code>2<sup>64</sup>-1</code></td>
</tr></tbody></table>
## 定点数 DECIMAL、NUMERIC

> In MySQL, `NUMERIC` is implemented as `DECIMAL`, so the following remarks about `DECIMAL` apply equally to `NUMERIC`.

- `DECIMAL` 和 `NUMERIC` 类型存储精确的数值数据值（定点数，fixed-point），在MySQL中，这两个类型是等价的同义词。
- `DECIMAL(M,D)`：在 `DECIMAL` 列声明中，通常可以指定精度（M）和小数位数（D），即存储M位数字位（包括D位小数位）的任意数。其中，M的默认值为10。 

## 浮点数 FLOAT、DOUBLE

- `FLOAT` 和 `DOUBLE` 类型表示[近似数值](./CRUD.md#浮点数的精确度问题)数据值（浮点数，floating-point）。MySQL 对单精度值使用 4 个字节，对双精度值使用 8 个字节。
- 对于 `FLOAT` ，SQL 标准允许在括号 `FLOAT` 中的关键字后面以位为单位的精度（但不是指数的范围）的可选规范，即 `FLOAT(p)` 。MySQL也支持此可选的精度规范，但精度 `FLOAT(p)` 值仅用于确定存储大小。精度从 0 到 23 将生成一个 4 字节的单精度 `FLOAT` 列。精度从 24 到 53 将生成一个 8 字节的双精度 `DOUBLE` 列。

## 位值 BIT

- `BIT` 数据类型用于存储位值。 `BIT(M)`允许存储M bit长度的二进制值， `M` 范围为`1~64`。
- 如果将小于M位的值赋值给`BIT(M)`类型的列，则会在该值的左边进行<b>零填充</b>，直到长度达到M bit。

```mysql
# BIT类型可以使用特殊的表达方式（二进制串）： b'100101' 等
select b'111' = 7;
# 1
```

- 在NDB Cluster（NDB集群）中，给定 `NDB` 表中使用的所有 `BIT` 列的最大组合大小不得超过 4096 位。

## 数值类型总结

### UNSIGNED

- 所有整数类型都可以具有可选的（非标准） `UNSIGNED` 属性。无符号类型可用于在列中仅允许非负数，或者当需要列的较大数值范围时。例如，如果一个 `INT` 类型的列是 `UNSIGNED` ，则该列的范围大小相同，但其端点向上移动，从 `-2147483648` 和 `2147483647` 移动到 `0` 和 `4294967295` 。

```mysql
# 将值转换为UNSIGNED：CAST(value AS UNSIGNED)
SELECT CAST(9223372036854775807 AS UNSIGNED) + 1;
```

- 如果开启了`NO_UNSIGNED_SUBTRACTION` SQL 模式，则

```mysql
SET sql_mode = 'NO_UNSIGNED_SUBTRACTION	';
```

### <code>AUTO_INCREMENT</code>

- 整数可以具有`AUTO_INCREMENT`属性，当向具有`AUTO_INCREMENT`属性的列插入空值（NULL）时，会自动在该位置填入下一个序列值，通常为`value+1`。（浮点数类型也可以具有，但在MySQL8.3开始弃用）

1. `AUTO_INCREMENT`不支持负值，默认从1开始，步长默认为1。

2. 除非声明了`NO_AUTO_VALUE_ON_ZERO`的SQL模式，否则向`AUTO_INCREMENT`列存入`0`或`NULL`都是等价的，会自动被填充为下一个序列值。

- `AUTO_INCREMENT`对约束的要求：

1. 为了使得`AUTO_INCREMENT`列能够将空值转换为自动填充序列值，`AUTO_INCREMENT`列必须被声明为拥有`NOT NULL`约束。而对于声明为NULL的`AUTO_INCREMENT`列，插入NULL就仍然是NULL，而不会是序列值。
2. `CHECK` 约束不能引用 `AUTO_INCREMENT` 列，也不能将`AUTO_INCREMENT` 属性添加到使用`CHECK`约束的列中。

### 超过范围

- 当MySQL存储一个超过指定的数值类型允许的范围的数值时，结果取决于当时有效的SQL模式：

<table>
    <tr>
        <td width="30%">严格SQL模式<br/><code>SET sql_mode = 'TRADITIONAL';</code></td>
        <td width="70%">MySQL会根据SQL标准拒绝超过范围的值并显示错误信息、插入失败。<code>ERROR 1264 (22003): Out of range value for column 'i1' at row 1</code></td>
    </tr>
    <tr>
        <td>未启用限制性模式<br/><code>SET sql_mode = '';</code></td>
        <td>MySQL会将该值（带有警告的）截断到相应的数值类型范围的端点（最值），储存的是这个被截断后的值</td>
    </tr>
</table>
### 溢出处理

#### 数值表达式计算过程的溢出

- 数值表达式计算过程中的溢出会导致错误，但是否发生溢出取决于操作数的范围。

```mysql
SELECT 9223372036854775807 + 1;
# ERROR 1690 (22003): BIGINT value is out of range in '(9223372036854775807 + 1)' 
```

#### UNSIGNED与负数结果

- 默认情况下（未启用 `NO_UNSIGNED_SUBTRACTION` SQL 模式），整数值之间的减法（其中一个是`UNSIGNED` 类型）会生成无符号结果。此时，如果结果为负数，则会导致溢出。而如果启用了 `NO_UNSIGNED_SUBTRACTION` SQL 模式，则结果就算是负数也不会导致溢出。

```mysql
# 未启用`NO_UNSIGNED_SUBTRACTION` SQL 模式，无符号数的负数结果导致溢出
SET sql_mode = '';
SELECT CAST(0 AS UNSIGNED) - 1;

ERROR 1690 (22003): BIGINT UNSIGNED value is out of range in '(cast(0 as unsigned) - 1)'

# 启用`NO_UNSIGNED_SUBTRACTION` SQL 模式，无符号数的负数结果不会导致溢出，而是正确的结果
SET sql_mode = 'NO_UNIGNED_SUBTRACTION';
SELECT CAST(0 AS UNSIGNED) - 1;

+------------------------+
| CAST(0 AS UNSIGNED) -1 |
+------------------------+
|                     -1 |
+------------------------+
```

- 如果在进行UPDATE操作时，对UNSIGNED整数类型的列发生了上述情况（试图存入负数)，则：

<table>
    <tr>
        <td width="40%">默认SQL模式<br/><code>SET sql_mode = '';</code></td>
        <td width="60%">将结果截断为该UNSIGNED整数列的最大值（端点）之后存入</td>
    </tr>
    <tr>
        <td>NO_UNSIGNED_SUBTRACTION<br/><code>SET sql_mode = 'NO_UNSIGNED_SUBTRACTION';</code></td>
        <td>将结果截断为0后再存入</td>
    </tr>
    <tr>
        <td>严格SQL模式<br/><code>SET sql_mode = 'TRADITIONAL';</code></td>
        <td>发生错误（更新失败），并且列保持不变（不执行这次更新）</td>
    </tr>
</table>

# 日期和时间

- 用于表示时态值的日期和时间数据类型为 `DATE` 、 `TIME` 、 `DATETIME`、 `TIMESTAMP`、 `YEAR` 。
- 每个时态类型都有一个有效值范围，以及一个“零”值。当指定了MySQL无法表示的无效值（invalid value）时，可以使用零值来代替。

<table><colgroup><col style="width: 30%"><col style="width: 40%"></colgroup><thead><tr>
    <th>Data Type 数据类型</th>
    <th>“Zero” Value “零”值</th>
    </tr></thead><tbody><tr>
    <td><code>DATE</code></a></td>
<td><code>'0000-00-00'</code></td>
</tr><tr>
    <td><code>TIME</code></td>
    <td><code>'00:00:00'</code></td>
</tr><tr>
    <td><code>DATETIME</code></td>
    <td><code>'0000-00-00 00:00:00'</code></td>
</tr><tr>
    <td><code>TIMESTAMP</code></td>
    <td><code>'0000-00-00 00:00:00'</code></td>
</tr><tr>
    <td><code>YEAR</code></td>
    <td><code>0000</code></td>
</tr></tbody></table>

- `TIMESTAMP` 和 `DATETIME` 类型具有特殊的自动更新行为。

- 使用日期和时间类型时的一般注意事项：

1. MySQL以标准输出格式检索给定日期或时间类型的值，但MySQL也会尝试解释用户提供的输入值的各种格式，但应提供有效值。如果使用其他格式的值，则可能会出现不可预知的结果。
2. MySQL中的日期部分必须始终按“年-月-日“顺序给出。（`STR_TO_DATE()`函数可用于转换）
3. “年”部分只使用2-digit（“yy”）的，由于并为给出哪一世纪，从而导致日期未知，MySQL使用以下规则来进行解释：
   1. `70-99`范围的年份值转换为`1970-1999`。
   2. `00-69`范围的年份值转换为`2000-2069`。
4. 一种时态数据可以根据一定的规则转换为另一种时态数据（如，日期与时间）。
5. 如果在数字上下文中使用日期或时间值，MySQL会自动将该值转换为数字，反之亦然。
6. 默认情况下，当MySQL遇到超出范围或对该类型无效的日期或时间类型的值时，它会将该值转换为该类型的“零”值。例外情况是， `TIME` 类型的超出范围的值会被被截断到 `TIME` 范围的相应终结点（endpoint）。
7. 通过将 SQL 模式设置为适当的值，可以更准确地指定希望 MySQL 支持的日期类型。例如，`ALLOW_INVALID_DATES` SQL模式仅验证月份是否在1到12之间，日期是否在1到31之间（也就是允许一些可能错误的日期）。
8. MySQL允许在 `DATE` 或 `DATETIME` 列中存储“日”或“月日”为零的日期，例如'2009-00-00' 或 '2009-01-00'。可通过启用`NO_ZERO_IN_DATE`模式来阻止该行为。
9. MySQL允许将“零”值 `'0000-00-00'` 存储为“虚拟日期”（dummy date）。在某些情况下，这比使用 `NULL` 值更方便，并且使用更少的数据和索引空间。可通过启用 `NO_ZERO_DATE` 模式来阻止该行为。
10. 通过Connector/ODBC，使用的“零”日期或时间值会自动转换为 `NULL` ，因为 ODBC 无法处理此类值。

# 字符串

# 空间

# JSON